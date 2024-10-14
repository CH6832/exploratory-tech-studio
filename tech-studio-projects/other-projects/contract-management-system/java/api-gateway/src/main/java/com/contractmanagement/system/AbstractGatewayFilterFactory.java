package com.contractmanagement.system;

import org.apache.logging.log4j.message.AbstractMessageFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public abstract class AbstractGatewayFilterFactory<GatewayFilter> extends AbstractMessageFactory {

    public void CustomLoggingFilter() {
        super(Config.class); // Call the constructor of the parent class with the Config class
    }
	
    public static class Config {
        // Configuration properties can be defined here if needed
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }



    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Log the request
            System.out.println("Request Path: " + exchange.getRequest().getURI().getPath());
            System.out.println("Message from config: " + config.getMessage());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Log the response status
                System.out.println("Response Status Code: " + exchange.getResponse().getStatusCode());
            }));
        };
    }
}