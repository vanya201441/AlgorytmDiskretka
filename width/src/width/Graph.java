package width;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {


    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<>();
        tmp.add(b);
        adjacencyMap.put(a,tmp);
    }

    public void addEdge(Node source, Node destination) {


        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);


        if (!directed) {
            addEdgeHelper(destination, source);
        }
    }

    public void printEdges() {
        for (Node node : adjacencyMap.keySet()) {
            System.out.print("The " + node.name + " has an edge towards: ");
            for (Node neighbor : adjacencyMap.get(node)) {
                System.out.print(neighbor.name + " ");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }

    public void resetNodesVisited(){
        for(Node node : adjacencyMap.keySet()){
            node.unvisit();
        }
    }


    void breadthFirstSearch(Node node) {


        if (node == null)
            return;


        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentFirst = queue.removeFirst();


            if (currentFirst.visited)
                continue;


            currentFirst.visit();
            System.out.print(currentFirst.name + " ");

            LinkedList<Node> allNeighbors = adjacencyMap.get(currentFirst);


            if (allNeighbors == null)
                continue;

            for (Node neighbor : allNeighbors) {
                if (!neighbor.visited) {
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

}