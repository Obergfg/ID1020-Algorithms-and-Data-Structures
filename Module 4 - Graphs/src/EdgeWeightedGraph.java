/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 191005
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code represent a graph that is not dense, a so called adjacency-list data structure. That is a data structure where
 *  edges adjacent to each vertex are being kept track of on a linked list. The vertices
 *  are maintained in an array of lists so that, given a vertex, that vertex can immediately be accessed in its list. Every edge
 *  of the graph appears twice: if an edge connects vertex1 and vertex2, then vertex2 appears in vertex1’s list and vice versa.
 *  Each edge of the  graph is given a unique weight which gives the graph the properties that algorithms can compute different
 *  paths in the graph with concern to the edges weights.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as the "Edge-weighted Graph" data type on page 611
 *  in the course literature "Algorithms" by Sedgewick and Wayne.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a graph with weighted edges.
 *
 * @param <Key> is the vertices of the edges.
 */
public class EdgeWeightedGraph<Key extends Comparable<Key>> {

    private final int vertices;
    private int edges;
    private Bag<WeightedEdge>[] adjacencies;
    private Key[] indexes;

    /**
     * Creates an instance with a given number of vertices and zero edges.
     *
     * @param vertices is hte number of vertices of this instance.
     */
     EdgeWeightedGraph(int vertices) {

        this.vertices = vertices;
        this.edges = 0;
        this.adjacencies = (Bag<WeightedEdge>[]) new Bag[vertices];
        this.indexes = (Key[]) new Comparable[vertices];

        for (int v = 0; v < vertices; v++)
            this.adjacencies[v] = new Bag<>();
    }

    /**
     * Creates an instance and takes input from stdin.
     *
     * @param reader is the object taking input from stdin.
     * @throws IOException is thrown when there is an error concerning the input.
     */
     EdgeWeightedGraph(BufferedReader reader) throws IOException {

        this(Integer.parseInt(reader.readLine()));

        int edges = Integer.parseInt(reader.readLine());

        String line;
        String[] items;

        for (int i = 0; i < edges; i++) {

            line = reader.readLine();
            items = line.split(" ");

            WeightedEdge<Key> edge = new WeightedEdge<>((Key) items[0], (Key) items[1], Double.parseDouble(items[2]));

            addEdge(edge);
        }
    }

    /**
     * Returns the current graphs number of vertices.
     *
     * @return is this graphs number of vertices.
     */
     int getVertices() {
        return this.vertices;
    }

    /**
     * Returns the number of edges of the current graph.
     *
     * @return is the graphs number of edges
     */
     int getEdges() {
        return this.edges;
    }

    Key getKey(int index) {
         return this.indexes[index];
    }

    /**
     * Adds an edge to the graph.
     *
     * @param edge is teh egde being added.
     */
     void addEdge(WeightedEdge<Key> edge) {

        Key vertex1 = edge.either(), vertex2 = edge.other(vertex1);

        setIndex(vertex1);
        setIndex(vertex2);

        this.adjacencies[getIndex(vertex1)].add(edge);
        this.adjacencies[getIndex(vertex2)].add(edge);
        this.edges++;
    }

    /**
     * Gives the vertices of the graph an index value.
     *
     * @param vertex is the vertex getting an index value.
     */
    private void setIndex(Key vertex) {

        int index = 0;
        boolean found = false;

        while(null != this.indexes[index] && index < this.vertices) {

            if (0 == this.indexes[index].compareTo(vertex)) {
                found = true;
                break;
            }

            index++;
        }

        if (!found)
            this.indexes[index] = vertex;
    }

    /**
     * Returns the instance variable "adjacencies" index of a given vertex.
     *
     * @param vertex is the given vertex.
     * @return is the index of the given vertex.
     */
    int getIndex(Key vertex) throws java.lang.IllegalArgumentException{

        int index = 0;

        while(null != indexes[index] && index < this.vertices) {


            if (0 == this.indexes[index].compareTo(vertex))
                return index;

            index++;

            if(this.indexes.length <= index )
                throw new IllegalArgumentException("Vertex " + vertex + " is not i the graph");
        }

        throw new IllegalArgumentException("Vertex " + vertex + " not found");
    }

    /**
     * Returns the adjacencies of a given vertex.
     *
     * @param vertex is the vertex of interest.
     * @return is the given vertices adjacencies.
     */
     Iterable<WeightedEdge> getAdjacencies(Key vertex) {
        return adjacencies[getIndex(vertex)]; }

    /**
     * Returns the edges of the current graph.
     *
     * @return is the edges of the current graph.
     */
     Iterable<WeightedEdge> edges(){

        Bag<WeightedEdge> edges = new Bag<>();
        Key vertex;

        for (int v = 0; v < this.vertices; v++) {
            vertex = this.indexes[v];
            for (WeightedEdge e : this.adjacencies[v])
                if (e.other(vertex).compareTo(vertex) > 0) edges.add(e);
        }

        return edges;
    }

    /**
     * Contains unit testing which validates the functionality of the class.
     *
     * @param args is a set of arguments in form of a <>String</> array received from the command line.
     */
    public static void main(String[] args) throws IOException {

        EdgeWeightedGraph<String> graph1 = new EdgeWeightedGraph<>(5);
        FileReader fr = new FileReader("the database - weighted.txt");
        BufferedReader reader = new BufferedReader(fr);

        graph1.addEdge(new WeightedEdge<>("A", "B", 1));
        graph1.addEdge(new WeightedEdge<>("B", "C", 2));
        graph1.addEdge(new WeightedEdge<>("D", "B", 3));
        graph1.addEdge(new WeightedEdge<>("A", "E", 4));
        try {
            graph1.addEdge(new WeightedEdge<>("Q", "E", 5));
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        System.out.println("Nr of edges: " + graph1.getEdges());
        System.out.println("A: " + graph1.getAdjacencies("A"));
        System.out.println("B: " + graph1.getAdjacencies("B"));
        System.out.println("C: " + graph1.getAdjacencies("C"));
        System.out.println("D: " + graph1.getAdjacencies("D"));
        System.out.println("E: " + graph1.getAdjacencies("E"));
        System.out.println("Edges: " + graph1.edges());

        EdgeWeightedGraph graph2 = new EdgeWeightedGraph(reader);

        System.out.println("Nr of edges: " + graph2.getEdges());
        System.out.println("Edges: " + graph2.edges());
        System.out.println("Vertices: " + graph2.getVertices());
    }
}
