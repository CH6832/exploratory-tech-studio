import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaftServer implements Runnable {
    private enum State {
        FOLLOWER, CANDIDATE, LEADER
    }

    private final int id;
    private int term;
    private int votedFor;
    private State state;
    private int leaderId;
    private boolean running;
    private Thread electionThread;
    private final List<RaftServer> peers;
    private final Lock mtx;

    public RaftServer(int id, int totalServers) {
        this.id = id;
        this.term = 0;
        this.votedFor = -1;
        this.state = State.FOLLOWER;
        this.leaderId = -1;
        this.running = false;
        this.peers = new ArrayList<>();
        this.mtx = new ReentrantLock();

        for (int i = 0; i < totalServers; ++i) {
            if (i != id) {
                peers.add(new RaftServer(i, totalServers));
            }
        }
    }

    // Start the server
    public void start() {
        running = true;
        electionThread = new Thread(this);
        electionThread.start();
    }

    // Stop the server
    public void stop() {
        running = false;
        if (electionThread != null && electionThread.isAlive()) {
            try {
                electionThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Handle timeout events
    public void onTimeout() {
        mtx.lock();
        try {
            if (state == State.FOLLOWER) {
                becomeCandidate();
            }
        } finally {
            mtx.unlock();
        }
    }

    // Handle heartbeat messages
    public void onHeartbeat() {
        mtx.lock();
        try {
            if (state == State.CANDIDATE) {
                becomeFollower();
            }
        } finally {
            mtx.unlock();
        }
    }

    // Handle vote requests
    public void onRequestVote(int candidateId, int term) {
        // Handle vote request
    }

    // Handle log entries
    public void onAppendEntries(int leaderId, int term, List<Integer> entries) {
        // Handle log replication
    }

    // Become leader
    private void becomeLeader() {
        System.out.println("Server " + id + " became Leader");
        state = State.LEADER;
        leaderId = id;
        sendHeartbeat();
        replicateLog();
    }

    // Become follower
    private void becomeFollower() {
        System.out.println("Server " + id + " became Follower");
        state = State.FOLLOWER;
        leaderId = -1;
    }

    // Become candidate
    private void becomeCandidate() {
        System.out.println("Server " + id + " became Candidate");
        state = State.CANDIDATE;
        term++;
        votedFor = id;
        requestVotes();
    }

    // Election routine
    @Override
    public void run() {
        Random rng = new Random();
        while (running) {
            try {
                Thread.sleep(rng.nextInt(150) + 150); // Sleep for 150 to 300 milliseconds
                onTimeout();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Se
