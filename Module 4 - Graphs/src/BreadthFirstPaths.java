/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 191004
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code uses a Breadth First Search algorithm to find paths to all the vertices in a given graph that contains
 *  a given source vertex. It marks every vertex visited in a boolean array called "marked" which marks all vertices that
 *  are connected to the source vertex. The client can use the instance method pathTo() to iterate through the vertices
 *  on a path from a given vertex to any vertex connected to the given.
 *  The instance variable array "edgeTo" is an array of <>int</> values that serves the purpose of a ball of string(a method
 *  used in the so called Tremaux exploration of a maze which is an old method used for exploring a maze, or graph in this case).
 *  The "edgeTo" array therefore helps the code to find a path back to the source vertex for every vertex connected to the source.
 *  The breadth in "Breadth First Search" implies that the algorithm iterates through all the adjacent vertices of the source
 *  before moving deeper in to the graph. This makes it so that the path found always is the shortest.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as the algorithm 4.2
 *  in the course literature "Algorithms" by Sedgewick and Wayne.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Finds paths between vertices of a graph using the "Breadth First Search" algorithm
 *
 * @param <Key> is the generalised vertex type of the graph.
 */
public class BreadthFirstPaths<Key extends Comparable<Key>> {

    private boolean[] marked;
    private Key[] edgeTo;
    private final Key source;

    /**
     * Contains unit tests validating the functionality of the class.
     *
     * @param args is a set of commands in the form a <code>String</code> array received from the command line.
     */
    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("the database.txt");
            BufferedReader br = new BufferedReader(fr);
            Graph<String> graph = new Graph<>(br);
            BreadthFirstPaths<String> depth = new BreadthFirstPaths<>(graph, "CA");

            System.out.println(depth.pathTo(graph, "NV"));
            System.out.println(depth.pathTo(graph, "ID"));

        }catch (IOException e){
            System.out.println("File not found");
        }

        try {
            FileReader fr = new FileReader("the database.txt");
            BufferedReader br = new BufferedReader(fr);
            Graph<String> graph = new Graph<>(br);
            BreadthFirstPaths<String> depth = new BreadthFirstPaths<>(graph, "NY");

            System.out.println(depth.pathTo(graph, "WY"));
            System.out.println(depth.pathTo(graph, "UT"));

            try {
                System.out.println(depth.pathTo(graph, "HI"));
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }catch (IOException e){
            System.out.println("File not found");
        }
    }

    /**
     * Creates an instance.
     *
     * @param graph is the graph of interest.
     * @param source is the vertex from which the path search initiates.
     */
    private BreadthFirstPaths(Graph<Key> graph, Key source) {

        this.marked = new boolean[graph.vertices()];
        this.edgeTo = (Key[]) new Comparable[graph.vertices()];
        this.source = source;
        BFS(graph, source);
    }

    /**
     * Searches for paths in a given graph using the "Breadth First Search" algorithm.
     *
     * @param graph is the graph of interest.
     * @param source is the vertex from which the search originates.
     */
    private void BFS(Graph<Key> graph, Key source) {

        Queue<Key> queue = new Queue<>();
        int index = graph.getIndex(source);

        this.marked[index] = true;

        queue.enqueue(source);

        while (!queue.isEmpty()) {

            Key vertex = queue.dequeue().getKey();

            for (Object v : graph.adjacencies(vertex)) {

                int i = graph.getIndex((Key) v);

                if (!this.marked[i]) {
                     this.edgeTo[i] = vertex;
                     this.marked[i] = true;
                     queue.enqueue((Key) v);
                }
            }
        }
    }

    /**
     * States if there is a path between two vertices.
     *
     * @param index is the index of the vertex of interest.
     * @return is true if there is a path. False if not.
     */
    private boolean hasPathTo(int index) {
        return this.marked[index];
    }

    /**
     * Shows the path between the source and a given vertex.
     *
     * @param vertex is the given vertex of interest.
     * @return is a <>Iterable</> object of integers containing the path of interest
     */
    private Iterable<Key> pathTo(Graph<Key> graph, Key vertex) {

        if (!hasPathTo(graph.getIndex(vertex)))
            return null;

        Stack<Key> path = new Stack<>();

        for (Key x = vertex; x != this.source; x = this.edgeTo[graph.getIndex(x)])
            path.push(x);

        path.push(this.source);

        return path;
    }
}