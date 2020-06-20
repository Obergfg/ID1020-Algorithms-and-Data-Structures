/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 191002
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code represent a graph that is not dense, a so called adjacency-list data structure. That is a data structure where
 *  vertices adjacent to each vertex on a linked list that are associated with that vertex are being kept track of. The vertices
 *  are maintained in an array of lists so that, given a vertex, that vertex can immediately be accessed in its list. Every edge
 *  of the graph appears twice: if an edge connects vertex1 and vertex2, then vertex2 appears in vertex1’s list and vice versa.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as the "Graph Data Type" on page 526
 *  in the course literature "Algorithms" by Sedgewick and Wayne.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A not dense graph implemented as an adjacency-lists data structure. It maintains a vertex-indexed array of lists of integers.
 *
 * @param <Key> is the identifier to the objects stored in the graph.
 */
public class Graph<Key extends Comparable<Key>> {

    private final int vertices;
    private int edges;
    private Bag<Key>[] adjacencies;
    private Key[] indexes;

    /**
     * Contains unit tests validating the functionality of the class.
     *
     * @param args is a set of commands in the form a <code>String</code> array received from the command line.
     */
    public static void main(String[] args) {

        try {
            Graph<String> graph = new Graph<>(5);
            FileReader fr = new FileReader("the database.txt");
            BufferedReader br = new BufferedReader(fr);

            graph.addEdge("A", "B");
            graph.addEdge("A", "C");
            graph.addEdge("E", "B");
            graph.addEdge("D", "E");

            try {
                graph.addEdge("T", "C");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }

            System.out.println(graph.adjacencies("A"));
            System.out.println(graph.adjacencies("B"));
            System.out.println(graph.adjacencies("C"));
            System.out.println(graph.adjacencies("E"));

            Graph<String> graph2 = new Graph<>(br);

            System.out.println(graph2.adjacencies("AL"));
            System.out.println(graph2.adjacencies("AR"));
            System.out.println(graph2.adjacencies("CA"));
            System.out.println(graph2.adjacencies("TN"));

            try {
                System.out.println(graph2.adjacencies("HI"));
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }catch (IOException e){
            System.out.println("File not found");
        }
    }

    /**
     * Creates a <>Graph</> with no edges.
     *
     * @param vertices is the number of vertices in the graph.
     */
     private Graph(int vertices) {

        this.vertices = vertices;
        this.edges = 0;
        this.adjacencies = (Bag<Key>[]) new Bag[vertices];
        this.indexes = (Key[]) new Comparable[vertices];

        for (int v = 0; v < vertices; v++)
            this.adjacencies[v] = new Bag<>();

    }

    /**
     * Constructs a <>Graph</> that takes input from stdIn.
     *
     * @param reader is the object receiving input from stdIn.
     */
     Graph(BufferedReader reader) throws IOException {

             this(Integer.parseInt(reader.readLine()));

             int edges = Integer.parseInt(reader.readLine());

             String line;
             String[] items;

             for (int i = 0; i < edges; i++) {

                 line = reader.readLine();
                 items = line.split(" ");
                 Key vertex1 = (Key) items[0];
                 Key vertex2 = (Key) items[1];

                 addEdge(vertex1, vertex2);
             }
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return is the number of vertices in the graph.
     */
     int vertices() {
        return vertices;
     }

    /**
     * States the number of edges in the graph.
     *
     * @return is teh number of edges in teh graph.
     */
    public int edges() {
        return this.edges;
    }

    /**
     * Adds an edge to the graph.
     *
     * @param vertex1 is the first vertex being connected to the second vertex.
     * @param vertex2 is the second vertex being connected to the first vertex.
     */
   private void addEdge(Key vertex1, Key vertex2) throws java.lang.IndexOutOfBoundsException{

        if(null != this.indexes[vertices() -1])
            throw new IndexOutOfBoundsException("Graph is full");

        setIndex(vertex1);
        setIndex(vertex2);

        this.adjacencies[getIndex(vertex1)].add(vertex2);
        this.adjacencies[getIndex(vertex2)].add(vertex1);
        this.edges++;
    }

    /**
     * Gives the vertices of the graph an index value.
     *
     * @param vertex is the vertex getting an index value.
     */
    private void setIndex(Key vertex) {

        int index = 0;

        while(null != this.indexes[index]) {

            if (0 == this.indexes[index].compareTo(vertex))
                break;

            index++;
        }

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
        }

        throw new IllegalArgumentException("Vertex " + vertex + " not found");
    }

    /**
     * Returns the number of adjacencies of a given vertex.
     *
     * @param vertex is the vertex of interest.
     * @return is the number of adjacencies of the given vertex.
     */
     Iterable<Key> adjacencies(Key vertex) {

         try {
           getIndex(vertex);
         }catch (IllegalArgumentException e){
            throw e;
         }

         return this.adjacencies[getIndex(vertex)];
     }
}
