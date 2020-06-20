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
 *  Stores a collection of items in an abstract Bag. The client can add items to the bag, but not remove them. The content
 *  of the bag can be iterated through if a specific item is of interest.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as algorithm 1.4 in the course literature "Algorithms"
 *  by Sedgewick and Wayne
 *
 *
 */

import java.util.Iterator;

/**
 * A collection of <>Item</> where removing items is not supported. Its purpose is to
 * provide clients with the ability to collect items and then to iterate through the collected
 * items. The order of iteration is unspecified and should be immaterial to the client.
 *
 * @param <Key> is the data type being stored in the Bag.
 */
public class Bag<Key> implements Iterable<Key> {

    private Node first;

    /**
     * Contains unit tests validating the functionality of the class.
     *
     * @param args is a set of commands in the form a <code>String</code> array received from the command line.
     */
    public static void main(String[] args) {

        Bag<String> bag = new Bag();

        bag.add("1");
        bag.add("2");
        bag.add("3");
        bag.add("4");
        bag.add("5");
        bag.add("11");
        bag.add("3");


        System.out.println(bag);

    }

    /**
     * Contains the data of each individual item in the bag.
     */
    private class Node {

        Key key;
        Node next;
    }

    /**
     * States if the bag is empty or not.
     *
     * @return is true if the bag is empty. False if not.
     */
     boolean isEmpty() {
        return this.first == null;
    }

    /**
     * Adds a new node to the bag.
     *
     * @param key is the item stored in the node.
     */
     void add(Key key) {

        Node oldFirst = first;

        this.first = new Node();
        this.first.key = key;
        this.first.next = oldFirst;
    }

    /**
     * Returns the content of the bag in form of a <>String</>.
     *
     * @return is the content of the bag.
     */
    public String toString() {

         String content = "";

         for (Key key : this)
           content =  content + key + " ";

         return content;
    }

    /**
     * Returns a <>ListIterator</> which makes the  content of the bag iterable.
     *
     * @return is the <>ListIterator</> in question.
     */
    public Iterator<Key> iterator() {
        return new ListIterator();
    }

    /**
     * Iterates through the content of the bag.
     */
    private class ListIterator implements Iterator<Key> {

        private Node current = first;

        /**
         * States if the bag contains more items.
         *
         * @return is true if the bag has more items. False if not.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next item in the bag.
         *
         * @return is the next item in the bag.
         */
        public Key next() {

            Key key = current.key;
            current = current.next;
            return key;
        }
    }
}