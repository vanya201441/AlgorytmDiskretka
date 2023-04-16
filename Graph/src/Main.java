
import java.util.*;



class Main {

    public static void DFS(Graph graph, int v, boolean[] discovered) {

        discovered[v] = true;


        for (int u: graph.adjList.get(v))
        {

            if (!discovered[u]) {
                DFS(graph, u, discovered);
            }
        }
    }


    public static Graph buildTranspose(Graph graph, int n) {
        Graph g = new Graph(n);
        for (int u = 0; u < n; u++)
        {

            for (int v: graph.adjList.get(u)) {
                g.addEdge(v, u);
            }
        }
        return g;
    }


    public static boolean isVisited(Graph graph, boolean[] visited) {
        for (int i = 0; i < visited.length; i++)
        {
            if (graph.adjList.get(i).size() > 0 && !visited[i]) {
                return false;
            }
        }
        return true;
    }


    public static boolean isSC(Graph graph, int n) {

        boolean[] visited = new boolean[n];


        int i;
        for (i = 0; i < n; i++)
        {
            if (graph.adjList.get(i).size() > 0)
            {
                DFS(graph, i, visited);
                break;
            }
        }


        if (!isVisited(graph, visited)) {
            return false;
        }


        Arrays.fill(visited, false);


        Graph g = buildTranspose(graph, n);


        DFS(g, i, visited);


        return isVisited(g, visited);
    }


    public static boolean hasEulerianCycle(Graph graph, int n) {

        for (int i = 0; i < n; i++) {
            if (graph.adjList.get(i).size() != graph.in.get(i)) {
                return false;
            }
        }


        return isSC(graph, n);
    }

    public static void main(String[] args) {

        List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(1, 2),
                new Edge(2, 3), new Edge(3, 1), new Edge(1, 4),
                new Edge(4, 3), new Edge(3, 0));


        System.out.println("Введите кол-во узлов");

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());




        Graph graph = new Graph(edges, n);

        if (hasEulerianCycle(graph, n)) {
            System.out.println("Граф является циклом Эйлера");
        }
        else {
            System.out.println("Граф не является циклом Эйлера");
        }


        for (Edge ed: edges) {
            System.out.println(ed);
        }

    }
}