import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Graph {

    List<List<Integer>> adjList;


    List<Integer> in;


    Graph(int n) {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }


        in = new ArrayList<>(Collections.nCopies(n, 0));
    }


    Graph(List<Edge> edges, int n) {
        this(n);


        for (Edge edge: edges) {
            addEdge(edge.source, edge.dest);
        }
    }


    void addEdge(int u, int v) {

        adjList.get(u).add(v);


        in.set(v, in.get(v) + 1);
    }
}