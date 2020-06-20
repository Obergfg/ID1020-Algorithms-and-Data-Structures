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
 *  The code creates a weighted edge which can be used in a graph. It has two vertices in form of a generic item type
 *  and a weight attribute which states the weight of the edge.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Graph Lab PM and the data type on page 610
 *  in the course literature "Algorithms" by Sedgewick and Wayne.
 *
 */

/**
 * Represents an undirected weighted edge in a graph.
 *
 * @param <Key>
 */
public class WeightedEdge<Key extends Comparable<Key>> implements Comparable<WeightedEdge>  {

    private final Key vertex1;
    private final Key vertex2;
    private final double weight;


    /**
     * Creates an instance.
     *
     * @param vertex1 is the first vertex of the edge.
     * @param vertex2 is the second vertex of the edge.
     * @param weight is the weight of the edge.
     */
     WeightedEdge(Key vertex1, Key vertex2, double weight) {

        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return is the weight of the edge.
     */
     double getWeight() {
        return this.weight;
    }

    /**
     * Returns the first of the graphs vertices.
     *
     * @return is the graphs first vertex.
     */
     Key either() {
        return vertex1;
    }

    /**
     * Returns the other of the graphs vertex.
     *
     * @param vertex is the given vertex.
     * @return is the other vertex of the graph.
     * @throws RuntimeException if the given vertex is inconsistent with this edges vertices.
     */
     Key other(Key vertex) throws java.lang.RuntimeException{

        if (0 == vertex.compareTo(this.vertex1))
            return vertex2;
        else if (0 == vertex.compareTo(this.vertex2))
            return vertex1;
        else
            throw new RuntimeException("'" + vertex + "' is not a vertex on this edge");
    }

    /**
     * Compares the weight between two edges.
     *
     * @param edge is the edge being compared to this.
     * @return is negative if edges weight is larger; zero if the weights are equal; positive if
     * this edges weight is larger.
     */
    public int compareTo(WeightedEdge edge) {
        return Double.compare(this.weight, edge.getWeight());
    }

    /**
     * Is this edges attributes in form of a string.
     *
     * @return is the attributes of this edge.
     */
    public String toString() {
        return this.vertex1 + " " + this.vertex2 + " " + this.weight;
    }

    /**
     * Contains unit testing validating the functionality of the class.
     *
     * @param args is a setco commands entered from the command line in the form of a set of strings.
     */
    public static void main(String[] args) {

        WeightedEdge<String> edge1 = new WeightedEdge<>("A", "B", 10);
        WeightedEdge<String> edge2 = new WeightedEdge<>("C", "D", 5);
        WeightedEdge<String> edge3 = new WeightedEdge<>("P", "Q", 1);

        System.out.println("Weight1: " + edge1.getWeight());
        System.out.println("Weight2: " + edge2.getWeight());
        System.out.println("Weight3: " + edge3.getWeight());

        System.out.println("Either1: " + edge1.either());
        System.out.println("Either2: " + edge2.either());
        System.out.println("Either3: " + edge3.either());

        System.out.println("Other1: " + edge1.other("A"));
        System.out.println("Other2: " + edge2.other("C"));
        System.out.println("Other3: " + edge3.other("P"));

        try {
            edge1.other("T");
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        System.out.println("Compare to 1 and 2 : " + edge1.compareTo(edge2));
        System.out.println("Compare to 2 and 3 : " + edge2.compareTo(edge3));
        System.out.println("Compare to 3 and 1 : " + edge3.compareTo(edge1));
        System.out.println("Compare to 3 and 3 : " + edge3.compareTo(edge3));

        System.out.println("String 1: " + edge1);
        System.out.println("String 2: " + edge2);
        System.out.println("String 3: " + edge3);
    }
}
