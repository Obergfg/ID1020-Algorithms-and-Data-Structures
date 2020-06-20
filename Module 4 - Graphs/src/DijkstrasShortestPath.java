/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 191008
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  This code finds the shortest path between two vertices in an edge weighted graph whose edges are non-negative.
 *  The algorithm used is called Dijkstra's algorithm which grows the shortest path by adding an edge at a time,
 *  updating the shortest path if the edge from an already investigated vertex to a non investigated has a lower
 *  weight than the already known weight between the source and the current vertex. It then outputs the path to stdout
 *  with the total associated weight of the path.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as the algorithm 4.9
 *  in the course literature "Algorithms" by Sedgewick and Wayne.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Computes the shortest paths between vertices of a graph using Dijkstras algorithm and prints the shortest
 * paths of interest to stdout.
 *
 * @param <Key> is the vertices of the given graph.
 */
public class DijkstrasShortestPath<Key extends Comparable<Key>> {

    private WeightedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> priorityQueue;
    private Key source;

    /**
     * Creates an instance and computes the shortest paths between vertices.
     *
     * @param graph is the graph of interest.
     * @param source is the vertex from which the shortest path search initiates.
     */
     DijkstrasShortestPath(EdgeWeightedGraph<Key> graph, Key source) {

         this.source = source;
         this.edgeTo = new WeightedEdge[graph.getVertices()];
         this.distTo = new double[graph.getVertices()];
         this.priorityQueue = new IndexMinPQ<>(graph.getVertices());

         for (int v = 0; v < graph.getVertices(); v++)
            this.distTo[v] = Double.POSITIVE_INFINITY;

         this.distTo[graph.getIndex(source)] = 0.0;

         this.priorityQueue.insert(graph.getIndex(source), this.distTo[graph.getIndex(source)]);

         while (!this.priorityQueue.isEmpty()) {

            int minKey = this.priorityQueue.deleteMin();
            Key vertex = graph.getKey(minKey);

            for (WeightedEdge edge: graph.getAdjacencies(vertex))
                relax(graph, edge, vertex);
         }
    }

    /**
     * Updates information about the smallest weight between a given vertex and and its associated edge
     * if the path between th source and the given vertex has a lower weight than the current known.
     * An operation known as relaxation.
     *
     * @param graph is the graph which contains the vertices.
     * @param edge is the current edge who's weight is being compared to other edges weight.
     * @param vertex is the vertex who's associated vertices weights are being compared to other edges weights.
     */
    private void relax(EdgeWeightedGraph<Key> graph, WeightedEdge<Key> edge, Key vertex) {

        Key other = edge.other(vertex);
        int vertexIndex = graph.getIndex(vertex);
        int otherIndex = graph.getIndex(other);

        if (this.distTo[otherIndex] > this.distTo[vertexIndex] + edge.getWeight()) {

            this.distTo[otherIndex] = this.distTo[vertexIndex] + edge.getWeight();
            this.edgeTo[otherIndex] = edge;

            if (this.priorityQueue.contains(otherIndex))
                this.priorityQueue.decreaseKey(otherIndex, this.distTo[otherIndex]);
            else
                this.priorityQueue.insert(otherIndex, distTo[otherIndex]);
        }
    }

    /**
     * Returns the distance between the source and a given vertex.
     *
     * @param index is the index of the vertex of interest.
     * @return is teh distance between the source vertex and the given vertex.
     */
     double distTo(int index) {

        return distTo[index];
    }

    /**
     * States if there is a path from the source and a given vertex.
     *
     * @param index is the index of the given vertex.
     * @return is true if there exist a path. False if not.
     */
     boolean hasPathTo(int index) {

        return distTo[index] < Double.POSITIVE_INFINITY;
    }

    /**
     * Prints the shortest path from the source and a given vertex to stdout.
     *
     * @param graph is the graph containing the vertices of interest.
     * @param index is the index of the given vertex.
     * @throws IllegalArgumentException is thrown if there doesnt exist a path between the vertices of interest.
     */
     void outputPathTo(EdgeWeightedGraph<Key> graph, int index) throws IllegalArgumentException{

        if (!hasPathTo(index))
           throw new IllegalArgumentException("No path between the vertices");

        Stack<WeightedEdge> path = new Stack<>();

        int x = index;

        for (WeightedEdge edge = this.edgeTo[index]; edge != null; edge = this.edgeTo[x]) {
            path.push(edge);
            x = graph.getIndex((Key) edge.other(graph.getKey(x)));
        }

        System.out.println("Shortest path between " + this.source + " and "+ graph.getKey(index) + " is: " + path +
                            "\nSum of associated weights: " + distTo(index) + "\n");
    }

    /**
     * Contains unit tests validating the functionality of the code.
     *
     * @param args is a set of command received from the command lina in form of an array of type <>String</>.
     * @throws IOException if there is no path the given file to the <>FileReader</> object.
     */
    public static void main(String[] args) throws IOException {

        EdgeWeightedGraph<String> graph1 = new EdgeWeightedGraph<>(10);
        FileReader fr = new FileReader("the database - weighted.txt");
        BufferedReader reader = new BufferedReader(fr);
        EdgeWeightedGraph<String> graph2 = new EdgeWeightedGraph<>(reader);

        graph1.addEdge(new WeightedEdge<>("A", "B", 1));
        graph1.addEdge(new WeightedEdge<>("B", "C", 2));
        graph1.addEdge(new WeightedEdge<>("D", "C", 3));
        graph1.addEdge(new WeightedEdge<>("A", "F", 0));
        graph1.addEdge(new WeightedEdge<>("D", "F", 1));

        DijkstrasShortestPath<String> shortestPath = new DijkstrasShortestPath<>(graph1, "A");
        shortestPath.outputPathTo(graph1, graph1.getIndex("C"));
        shortestPath.outputPathTo(graph1, graph1.getIndex("D"));

        DijkstrasShortestPath<String> shortestPath2 = new DijkstrasShortestPath<>(graph2, "GA");
        shortestPath2.outputPathTo(graph2, graph2.getIndex("KY"));
        shortestPath2.outputPathTo(graph2, graph2.getIndex("ND"));

        DijkstrasShortestPath<String> shortestPath3 = new DijkstrasShortestPath<>(graph2, "FL");
        shortestPath3.outputPathTo(graph2, graph2.getIndex("ND"));
        shortestPath3.outputPathTo(graph2, graph2.getIndex("OK"));

        try {
            shortestPath3.outputPathTo(graph2, graph2.getIndex("Z"));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
