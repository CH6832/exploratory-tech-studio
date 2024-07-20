import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Graph {
    private int V;
    private List<Edge> graph;

    public Graph(int vertices) {
        V = vertices;
        graph = new ArrayList<>();
    }

    public void addEdge(int u, int v, int w) {
        graph.add(new Edge(u, v, w));
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public void kruskalMST() {
        List<Edge> result = new ArrayList<>();
        int i = 0;
        int e = 0;

        Collections.sort(graph, Comparator.comparingInt(Edge::getWeight));

        int[] parent = new int[V];
        int[] rank = new int[V];
        for (int node = 0; node < V; ++node) {
            parent[node] = node;
        }

        while (e < V - 1) {
            Edge edge = graph.get(i++);
            int u = edge.getU();
            int v = edge.getV();
            int x = find(parent, u);
            int y = find(parent, v);
            if (x != y) {
                e++;
                result.add(edge);
                union(parent, rank, x, y);
            }
        }

        int minimumCost = 0;
        System.out.println("Edges in the constructed MST:");
        for (Edge edge : result) {
            int u = edge.getU();
            int v = edge.getV();
            int weight = edge.getWeight();
            minimumCost += weight;
            System.out.println(u + " -- " + v + " == " + weight);
        }
        System.out.println("Minimum Spanning Tree: " + minimumCost);
    }

    static class Edge {
        private int u;
        private int v;
        private int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int getU() {
            return u;
        }

        public int getV() {
            return v;
        }

        public int getWeight() {
            return weight;
        }
    }
}
