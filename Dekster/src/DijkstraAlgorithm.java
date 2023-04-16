import java.util.*;

public class DijkstraAlgorithm {

    private static int INF = Integer.MAX_VALUE / 2;

    private static class Vertex {
        String name;
        List<Edge> edges;

        @Override
        public String toString() {
            return name;
        }
    }

    private static class Edge {
        Vertex source;
        Vertex destination;
        int weight;

        Edge(Vertex source, Vertex destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Graph {
        List<Vertex> vertices;
        List<Edge> edges;
    }

    public static Map<Vertex, Integer> dijkstra(Graph graph, Vertex start, Vertex end) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> parents = new HashMap<>();
        List<Vertex> unvisited = new ArrayList<>(graph.vertices);

        for (Vertex vertex : graph.vertices) {
            distances.put(vertex, INF);
        }
        distances.put(start, 0);

        while (!unvisited.isEmpty()) {
            Vertex current = null;
            int minDistance = INF;
            for (Vertex vertex : unvisited) {
                int distance = distances.get(vertex);
                if (distance < minDistance) {
                    current = vertex;
                    minDistance = distance;
                }
            }

            if (current == null) {
                break;
            }

            unvisited.remove(current);

            for (Edge edge : current.edges) {
                Vertex neighbour = edge.destination;
                if (!unvisited.contains(neighbour)) {
                    continue;
                }

                int distance = distances.get(current) + edge.weight;
                if (distance < distances.get(neighbour)) {
                    distances.put(neighbour, distance);
                    parents.put(neighbour, current);
                }
            }
        }

        Map<Vertex, Integer> path = new HashMap<>();
        Vertex current = end;
        while (current != null && !current.equals(start)) {
            path.put(current, distances.get(current));
            current = parents.get(current);
        }
        path.put(start, 0);

        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex a = new Vertex();
        a.name = "A";
        Vertex b = new Vertex();
        b.name = "B";
        Vertex c = new Vertex();
        c.name = "C";
        Vertex d = new Vertex();
        d.name = "D";
        Vertex e = new Vertex();
        e.name = "E";
        a.edges = Arrays.asList(new Edge(a, b, 10), new Edge(a, c, 5), new Edge(a, d, 2));
        b.edges = Arrays.asList(new Edge(b, e, 3));
        c.edges = Arrays.asList(new Edge(c, b, 1), new Edge(c, d, 9), new Edge(c, e, 2));
        d.edges = Arrays.asList(new Edge(d, c, 4), new Edge(d, e, 6));
        e.edges = new ArrayList<>();
        graph.vertices = Arrays.asList(a, b, c, d, e);
        graph.edges = new ArrayList<>();
        for (Vertex vertex : graph.vertices) {
            for (Edge edge : vertex.edges) {
                graph.edges.add(edge);
            }
        }

        Map<Vertex, Integer> path = dijkstra(graph, a, e);
        for (Vertex vertex : path.keySet()) {
            System.out.println(vertex + ": " + path.get(vertex));
        }
    }
}
