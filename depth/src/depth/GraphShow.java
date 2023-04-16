package depth;

import depth.Graph;

public class GraphShow {
    public static void main(String[] args) {

        Graph graph = new Graph(true);
        Node zero = new Node(0, "0");
        Node one = new Node(1, "1");
        Node two = new Node(2, "2");
        Node three = new Node(3, "3");
        Node four = new Node(4, "4");
        Node five = new Node(5, "5");
        Node six = new Node(6, "6");
        Node seven = new Node(7, "7");
        Node eight = new Node(8, "8");
        Node nine = new Node(9, "9");

        graph.addEdge(one,zero);
        graph.addEdge(three,one);
        graph.addEdge(two,seven);
        graph.addEdge(two,four);
        graph.addEdge(five,two);
        graph.addEdge(five,zero);
        graph.addEdge(six,five);
        graph.addEdge(six,three);
        graph.addEdge(six,eight);
        graph.addEdge(seven,five);
        graph.addEdge(seven,six);
        graph.addEdge(seven,eight);
        graph.addEdge(eight,nine);

        graph.depthFirstSearch(seven);
    }
}