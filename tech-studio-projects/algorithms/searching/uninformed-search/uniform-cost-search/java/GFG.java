import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GFG {

    // graph
    static List<List<Integer>> graph = new ArrayList<List<Integer>>();

    // map to store cost of edges
    static HashMap<List<Integer>, Integer> cost = new HashMap<List<Integer>, Integer>();

    // returns the minimum cost in a vector( if
    // there are multiple goal states)
    static List<Integer> uniform_cost_search(List<Integer> goal, int start) {

        // minimum cost upto
        // goal state from starting
        // state
        List<Integer> answer = new ArrayList<Integer>();

        // create a priority queue
        List<Tuple<Integer, Integer>> queue = new ArrayList<Tuple<Integer, Integer>>();

        // set the answer vector to max value
        for (int i = 0; i < goal.size(); i++)
            answer.add(Integer.MAX_VALUE);

        // insert the starting index
        queue.add(new Tuple<Integer, Integer>(0, start));

        // map to store visited node
        HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();

        // count
        int count = 0;

        // while the queue is not empty
        while (!queue.isEmpty()) {

            // get the top element of the
            // priority queue
            Tuple<Integer, Integer> q = queue.get(0);
            Tuple<Integer, Integer> p = new Tuple<Integer, Integer>(-q.x, q.y);

            // pop the element
            queue.remove(0);

            if (goal.contains(p.y)) {
                // get the position
                int index = goal.indexOf(p.y);
                // if a new goal is reached
                if (answer.get(index) == Integer.MAX_VALUE)
                    count++;
                // if the cost is less
                if (answer.get(index) > p.x)
                    answer.set(index, p.x);
                // pop the element
                queue.remove(0);
                // if all goals are reached
                if (count == goal.size())
                    return answer;
            }
            if (!visited.containsKey(p.y))
                for (int i = 0; i < graph.get(p.y).size(); i++) {
                    // value is multiplied by -1 so that
                    // least priority is at the top
                    queue.add(new Tuple<Integer, Integer>((p.x + (cost.containsKey(Arrays.asList(p.y, graph.get(p.y).get(i))) ? cost.get(Arrays.asList(p.y, graph.get(p.y).get(i))) : 0)) * -1,
                            graph.get(p.y).get(i)));
                }

            // mark as visited
            visited.put(p.y, 1);
        }

        return answer;
    }
}
