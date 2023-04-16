import java.util.*;

public class EulerCycle {

    public static void main(String[] args) {
        int[][] graph = {{0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0}};
        List<Integer> cycle = findEulerCycle(graph);
        System.out.println("Эйлеров цикл: " + cycle);
    }

    public static List<Integer> findEulerCycle(int[][] graph) {
        List<Integer> cycle = new ArrayList<>();
        int n = graph.length, start = 0;
        while (start < n && countEdges(graph[start]) % 2 == 0)
            start++;
        if (start == n) // если все вершины имеют чётную степень
            start = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int v = stack.peek(), u = 0;
            while (u < n && graph[v][u] == 0)
                u++;
            if (u == n) {
                cycle.add(stack.pop());
            } else {
                stack.push(u);
                graph[v][u] = graph[u][v] = 0;
            }
        }
        return cycle;
    }

    public static int countEdges(int[] row) {
        int count = 0;
        for (int v : row) {
            if (v == 1) count++;
        }
        return count;
    }
}
