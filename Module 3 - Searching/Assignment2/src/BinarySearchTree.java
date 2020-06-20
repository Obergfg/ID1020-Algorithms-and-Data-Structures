/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190919
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code represents a symbol table built as a Binary Search Tree. It stores input in form
 *  of keys with its associated value in nodes which are put into the tree and can output the content of the tree
 *  to stdout.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the algorithm
 *  3.3 were used as a basis.
 *
 *
 */

/**
 * A Binary Tree which stores a key and its value at a root, and has left Node which points to a smaller Key
 * as well as a right node which points to a larger key.
 *
 * @param <Key> is the identifier of each inserted element.
 * @param <Value> is the number of items of a specific key.
 */
public class BinarySearchTree <Key extends Comparable<Key>, Value>{

    /**
     * Contains unit tests for the class BinarySearchTree
     *
     * @param args is a set of commands given from the command line in an array of type <>String</>.
     */
    public static void main(String[] args) {

        BinarySearchTree<String, Integer> tree = new BinarySearchTree<String, Integer>();


        tree.put("1", 1);
        tree.put("2", 1);
        tree.put("3", 2);
        tree.put("4", 1);
        tree.put("5", 1);
        tree.put("11", 2);
        tree.put("3", 3);

        System.out.println(tree.get("a"));
        System.out.println(tree.get("d"));
        System.out.println(tree.get("11"));
        System.out.println(tree.get("3"));
        System.out.println("Rank size 5: " + tree.rank("5"));
        System.out.println("Rank size 1: " + tree.rank( "1"));
        System.out.println("Rank size 11: " + tree.rank( "11"));
        System.out.println("Rank size 2: " + tree.rank( "2"));
        System.out.println("Rank size 21: " + tree.rank( "21"));
        System.out.println( tree.keys().toString());

    }

    private Node root;

    /**
     * Stores the key and value of each element in the binary tree as well as pointing to nodes at lower
     * branches of the binary tree.
     */
    private class Node
    {
        private Key key;
        private Value val;
        private int N;
        private Node left;
        private Node right;

        /**
         * Creates a <>Node</>.
         *
         * @param key is the identifier of each <>Node</>.
         * @param val is the value of a specific key.
         * @param N keeps track of the number of keys in the binary tree.
         */
         Node(Key key, Value val, int N){

            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /**
     * Returns the size of the tree.
     *
     * @return is the size of the tree.
     */
    public int size(){
        return size(root);
    }

    /**
     * Recursive method called by size to count the number of nodes below a specific <>Node</>
     * of the tree (the subtree).
     *
     * @param x is the node from which to count the number of nodes.
     * @return is the number of NOdes below a specific node.
     */
    private int size(Node x) {

        if (x == null)
            return 0;
        else
            return x.N;
    }

    /**
     * Returns the value of a given key.
     *
     * @param key is the key which value is of interest.
     * @return is the value of the given key. Null if key not present in tree.
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * Recursive method for getting the value of a given key.
     *
     * @param x is the node from who's subtree you search for the given keys value.
     * @param key is teh key which value is of interest.
     * @return is the value of the given key. Null if key not present in subtree.
     */
    private Value get(Node x, Key key) {

        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    /**
     * Inserts a given key and its value to the binary tree.
     *
     * @param key is the key being inserted.
     * @param val is the value of the given key.
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * Updates a keys value if the key is rooted at the subtree of Node x. If key not present in subtree
     * it gets inserted to the left or right side of the subtree depending of the size of the given key.
     *
     * @param x is the node from which the current subtree is rooted from.
     * @param key is the key being inserted to the tree.
     * @param value is the value of the key of interest.
     * @return is the node of the given key.
     */
    private Node put(Node x, Key key, Value value)
    {
        if (x == null)
            return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.val = value;

        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    /**
     * States if a tree contains a given key.
     *
     * @param key is the key of interest.
     * @return true if the tree contains the given key. False if not.
     */
    boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Specifies how many keys less then the given key value is in the subtree of the node with the given key.
     *
     * @param key is the given key of interest.
     * @return is the number of keys less then then the subtree of the given key value.
     */
    public int rank(Key key) {
        return rank(key, root);
    }

    /**
     * Returns the number of keys that have a less key value in the subtree rooted at the node x.
     *
     * @param key is the given key of interest.
     * @param x is the node which is the root of the current subtree.
     * @return is the number of keys that are less  then the given keys subtree.
     */
    private int rank(Key key, Node x) {

        if (x == null)
            return 0;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    /**
     * Returns the smallest key of the tree.
     *
     * @return is the smallest key.
     */
    public Key min() {
        return min(root).key;
    }

    /**
     *  * Returns the smallest key of the current  subtree.
     *
     * @param x is the root of the current subtree.
     * @return is the value of the smallest key.
     */
    private Node min(Node x) {

        if (x.left == null)
            return x;

        return min(x.left);
    }

    /**
     * Returns the largest key of the tree.
     *
     * @return is the largest key.
     */
    public Key max() {
        return max(root).key;
    }

    /**
     *  * Returns the largest key of the current  subtree.
     *
     * @param x is the root of the current subtree.
     * @return is the value of the largest key.
     */
    private Node max(Node x) {

        if (x.right == null)
            return x;

        return max(x.right);
    }

    /**
     * Returns all the key of the tree.
     *
     * @return is all the keys of the tree.
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Makes a queue of all the keys in the tree.
     *
     * @param lo is the largest key of the tree.
     * @param hi is the lowest key of the tree.
     * @return is all the keys in a queue.
     */
    public Iterable<Key> keys(Key lo, Key hi) {

        Queue<Key> queue = new Queue<Key>();

        keys(root, queue, lo, hi);

        return queue;
    }

    /**
     * Puts all keys of the tree in a queue in the order of priority.
     *
     * @param x is the root of the current subtree.
     * @param queue is the queue with all the keys.
     * @param lo is the smallest key of the tree.
     * @param hi is the largest key of the tree.
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {

        if (x == null)
            return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0)
            keys(x.left, queue, lo, hi);

        if (cmplo <= 0 && cmphi >= 0)
            queue.enqueue(x.key);

        if (cmphi > 0)
            keys(x.right, queue, lo, hi);
    }
}
