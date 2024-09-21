use hyper::service::{make_service_fn, service_fn};
use hyper::{Body, Request, Response, Server};
use std::convert::Infallible;
use std::fs::File;
use std::io::Read;
use std::net::SocketAddr;
use std::sync::{Arc, Mutex};
use tokio::sync::mpsc::{self, UnboundedSender};
use tokio::task;
use std::collections::HashMap;
use openssl::ssl::{SslAcceptor, SslFiletype, SslMethod};
use hyper::server::conn::AddrIncoming;
use hyper_openssl::HttpsConnector;
use tokio::time::{Duration, Instant};

// --- TokenBucket struct for rate-limiting ---
#[derive(Clone)]
struct TokenBucket {
    rate: usize,
    burst_size: usize,
    tokens: Arc<Mutex<usize>>,
    last_refill: Arc<Mutex<Instant>>,
}

impl TokenBucket {
    fn new(rate: usize, burst_size: usize) -> Self {
        TokenBucket {
            rate,
            burst_size,
            tokens: Arc::new(Mutex::new(burst_size)),
            last_refill: Arc::new(Mutex::new(Instant::now())),
        }
    }

    fn allow(&self) -> bool {
        let mut tokens = self.tokens.lock().unwrap();
        let mut last_refill = self.last_refill.lock().unwrap();
        let now = Instant::now();
        let time_passed = now.duration_since(*last_refill).as_secs_f64();
        let new_tokens = (time_passed * self.rate as f64) as usize;

        *tokens = std::cmp::min(*tokens + new_tokens, self.burst_size);
        *last_refill = now;

        if *tokens > 0 {
            *tokens -= 1;
            true
        } else {
            false
        }
    }
}

// --- Main request handler ---
async fn handle_request(
    req: Request<Body>,
    rate_limiters: Arc<Mutex<HashMap<String, TokenBucket>>>,
) -> Result<Response<Body>, Infallible> {
    let client_ip = req
        .headers()
        .get("x-forwarded-for")
        .and_then(|header| header.to_str().ok())
        .unwrap_or("unknown");

    let rate_limiters = rate_limiters.lock().unwrap();
    let bucket = rate_limiters.get(client_ip).unwrap_or_else(|| {
        panic!("Rate limiter for {} not found", client_ip);
    });

    if !bucket.allow() {
        return Ok(Response::builder()
            .status(429)
            .body(Body::from("Too Many Requests"))
            .unwrap());
    }

    match req.uri().path() {
        "/" => Ok(serve_static_file("/index.html").await),
        path => Ok(serve_static_file(path).await),
    }
}

// --- Static file handler ---
async fn serve_static_file(path: &str) -> Response<Body> {
    let path = format!(".{}", path); // Ensure it's a relative path
    match tokio::fs::read(path).await {
        Ok(contents) => {
            let mime_type = get_mime_type(path.as_str());
            Response::builder()
                .header("Content-Type", mime_type)
                .body(Body::from(contents))
                .unwrap()
        }
        Err(_) => Response::builder().status(404).body(Body::from("404 Not Found")).unwrap(),
    }
}

// --- MIME type detection ---
fn get_mime_type(path: &str) -> &str {
    if path.ends_with(".html") {
        "text/html"
    } else if path.ends_with(".css") {
        "text/css"
    } else if path.ends_with(".js") {
        "application/javascript"
    } else if path.ends_with(".png") {
        "image/png"
    } else if path.ends_with(".jpg") || path.ends_with(".jpeg") {
        "image/jpeg"
    } else {
        "application/octet-stream"
    }
}

// --- SSL setup ---
fn get_ssl_acceptor() -> SslAcceptor {
    let mut builder = SslAcceptor::mozilla_intermediate(SslMethod::tls()).unwrap();
    builder
        .set_private_key_file("key.pem", SslFiletype::PEM)
        .unwrap();
    builder.set_certificate_chain_file("cert.pem").unwrap();
    builder.build()
}

// --- Rate-limiter initialization ---
fn initialize_rate_limiters() -> Arc<Mutex<HashMap<String, TokenBucket>>> {
    let mut rate_limiters = HashMap::new();
    rate_limiters.insert("client_ip_1".to_string(), TokenBucket::new(5, 10));
    rate_limiters.insert("client_ip_2".to_string(), TokenBucket::new(2, 5));
    Arc::new(Mutex::new(rate_limiters))
}

// --- Main function ---
#[tokio::main]
async fn main() {
    // Initialize rate limiters
    let rate_limiters = initialize_rate_limiters();

    let addr = SocketAddr::from(([127, 0, 0, 1], 8088));
    let acceptor = get_ssl_acceptor();
    let https = HttpsConnector::with_tls(acceptor);

    let make_svc = make_service_fn(move |_conn| {
        let rate_limiters = Arc::clone(&rate_limiters);
        async {
            Ok::<_, Infallible>(service_fn(move |req| {
                handle_request(req, Arc::clone(&rate_limiters))
            }))
        }
    });

    let server = Server::builder(AddrIncoming::bind(&addr).unwrap()).serve(make_svc);
    println!("Listening on https://{}", addr);

    if let Err(e) = server.await {
        eprintln!("server error: {}", e);
    }
}
