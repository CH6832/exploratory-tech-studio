import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // graph
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        // cost
        HashMap<List<Integer>, Integer> cost = new HashMap<List<Integer>, Integer>();
        // create the graph
        graph = new ArrayList<List<Integer>>();

        for (int i = 0; i < 7; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // add edges
        graph.get(0).add(1);
        graph.get(0).add(3);
        graph.get(3).add(1);
        graph.get(3).add(6);
        graph.get(3).add(4);
        graph.get(1).add(6);
        graph.get(4).add(2);
        graph.get(4).add(5);
        graph.get(2).add(1);
        graph.get(5).add(2);
        graph.get(5).add(6);
        graph.get(6).add(4);

        // add the cost
        cost.put(Arrays.asList(0, 1), 2);
        cost.put(Arrays.asList(0, 3), 5);
        cost.put(Arrays.asList(1, 6), 1);
        cost.put(Arrays.asList(3, 1), 5);
        cost.put(Arrays.asList(3, 6), 6);
        cost.put(Arrays.asList(3, 4), 2);
        cost.put(Arrays.asList(2, 1), 4);
        cost.put(Arrays.asList(4, 2), 4);
        cost.put(Arrays.asList(4, 5), 3);
        cost.put(Arrays.asList(5, 2), 6);
        cost.put(Arrays.asList(5, 6), 3);
        cost.put(Arrays.asList(6, 4), 7);

        // goal state
        List<Integer> goal = new ArrayList<Integer>();
        goal.add(6);

        List<Integer> answer = GFG.uniform_cost_search(goal, 0);

        // print the answer
        System.out.print("Minimum cost from 0 to 6 is = " + answer.get(0));
    }
}