import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Number of servers in the Raft cluster
        final int totalServers = 5;

        // Create and start all Raft servers
        List<RaftServer> servers = new ArrayList<>();
        for (int i = 0; i < totalServers; i++) {
            servers.add(new RaftServer(i, totalServers));
        }

        // Start all servers
        for (RaftServer server : servers) {
            server.start();
        }

        // Run for a while to let the servers interact
        try {
            Thread.sleep(10000); // Sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop all servers
        for (RaftServer server : servers) {
            server.stop();
        }
    }
}
