/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 191003
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code uses a depth first search algorithm to find paths to all the vertices in a given graph that contains
 *  a given source vertex. It marks every vertex visited in a boolean array called "marked" which marks all vertices that
 *  are connected to the source vertex. The client can use the instance method pathTo() to iterate through the vertices
 *  on a path from a given vertex to any vertex connected to the given.
 *  The instance variable array "edgeTo" is an array of <>int</> values that serves the purpose of a ball of string(a method
 *  used in the so called Tremaux exploration of a maze which is an old method used for exploring a maze, or graph in this case).
 *  The "edgeTo" array therefore helps the code to find a path back to the source vertex for every vertex connected to the source.
 *  The depth in "Depth First Search" implies that the algorithm chooses one path from the source vertex and itertes for as long as
 *  that route has unmarked adjacent vertices. It goes as deep as possible in the graph before trying the next possible
 *  root from the source.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as the algorithm 4.1
 *  in the course literature "Algorithms" by Sedgewick and Wayne.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *  Computes the paths between the source vertex and the vertices in a given graph using
 *  the algorithm Depth first search.
 */
public class DepthFirstPath<Key extends Comparable<Key>> {

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
            DepthFirstPath<String> depth = new DepthFirstPath<>(graph, "CA");

            System.out.println(depth.pathTo(graph, "NM"));

            System.out.println(depth.pathTo(graph, "ND"));

        }catch (IOException e){
            System.out.println("File not found");
        }

        try {
            FileReader fr = new FileReader("the database.txt");
            BufferedReader br = new BufferedReader(fr);
            Graph<String> graph = new Graph<>(br);
            DepthFirstPath<String> depth = new DepthFirstPath<>(graph, "DE");

            System.out.println(depth.pathTo(graph, "CO"));

            try {
                System.out.println(depth.pathTo(graph, "AK"));
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }catch (IOException e){
            System.out.println("File not found");
        }
    }

    /**
     * Creates an instance of a <>DepthFirstPath</> object and computes paths from the source to each vertex
     * connected to source.
     *
     * @param graph is the given graph which contains the source vertex.
     * @param source is the vertex the search algorithm starts from.
     */
    private DepthFirstPath(Graph<Key> graph, Key source) {

        this.marked = new boolean[graph.vertices()];
        this.edgeTo = (Key[]) new Comparable[graph.vertices()];
        this.source = source;
        DFS(graph, source);
    }

    /**
     * Computes the paths from a given vertex in a graph of choice using a recursive algorithm
     * called "Depth First Search".
     *
     * @param graph is the graph being searched through.
     * @param source is the vertex from which the search algorithm searches for
     *               unvisited adjacent vertices.
     */
    private void DFS(Graph<Key> graph, Key source) {

        int index = graph.getIndex(source);

        this.marked[index] = true;

        for (Object w : graph.adjacencies(source))
            if (!this.marked[graph.getIndex((Key) w)]) {

                this.edgeTo[graph.getIndex((Key) w)] = source;
                DFS(graph, (Key) w);
            }
    }

    /**
     * States if there is a path from the source to the given vertex.
     *
     * @param index is the index of the vertex of interest.
     * @return is the booleans statement for if there exists a path from
     *         the given vertex to the source or not.
     */
    private boolean hasPathTo(int index){
        return this.marked[index];
    }

    /**
     * Shows the path between the source and a given vertex.
     *
     * @param vertex is teh given vertex of interest.
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
