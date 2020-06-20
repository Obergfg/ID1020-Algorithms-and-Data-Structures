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
 *  The code represents a symbol table built as a Red-Black Binary Search Tree. It stores input in form
 *  of keys with its associated value in nodes which are put into the tree.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the algorithm
 *  3.4 were used as a basis.
 *
 *
 */

/**
 * A Red-Black Binary Search Tree.
 *
 * @param <Key> is the key of the nodes of the tree.
 * @param <Value> is the value each key of the tree is given.
 */
public class RedBlackBST <Key extends Comparable<Key>, Value> {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * Contains unit testing for the codes different functions
     *
     * @param args is a set of commands in the form of a string array gotten from the command line.
     */
    public static void main(String[] args) {

        RedBlackBST<String, Integer> tree = new RedBlackBST<>();

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

    /**
     * A node of the BST.
     */
    private class Node {

        Key key;
        Value value;
        Node left;
        Node right;
        int N;
        boolean color;

        /**
         * Constructs node
         *
         * @param key is teh key of the node.
         * @param value is the value of the key of the node.
         * @param N is the number of nodes in the subtree of the node.
         * @param color is the color of the link:  red links, which bind together two 2-nodes to represent 3-nodes,
         *              and black links, which bind together the 2-3 tree.
         */
        Node(Key key, Value value, int N, boolean color) {

            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    /**
     * States how many keys there are in the tree.
     *
     * @return is the number of keys in the tree.
     */
    public int size() {

        return size(root);
    }

    /**
     * Recursive function for counting the keys in a given subtree.
     *
     * @param node is the node which is the root of the current subtree.
     * @return is the size of teh current subtree.
     */
    private int size(Node node) {

        if (node == null)
            return 0;
        else
            return node.N;
    }

    /**
     * Retunrs the value of a given key
     *
     * @param key is the given key of interest.
     * @returnis the value of the given key.
     */
    public Value get(Key key) {

        return get(root, key);
    }

    /**
     * Recursive method for getting the value of a specific key.
     *
     * @param node is the node which is the root of a subtree from which akey is searched for.
     * @param key is the key being searched for.
     * @return is the value of the key of interest.
     */
    private Value get(Node node, Key key) {

        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            return get(node.left, key);
        else if (cmp > 0)
            return get(node.right, key);
        else
            return node.value;
    }

    /**
     * Inserts a given key and its value to the tree in the form of a node.
     *
     * @param key is the key being inserted to the tree.
     * @param value is the value of the given key.
     */
    public void put(Key key, Value value) {

        root = put(root, key, value);

        root.color = BLACK;
    }

    /**
     * Recursive method for inserting a given key and its value to the tree.
     *
     * @param node is the root of the current subtree.
     * @param key is the key being inserted to the tre.
     * @param value is the value of the given key.
     * @return is the root of the current subtree.
     */
    private Node put(Node node, Key key, Value value) {

        if (node == null)
            return new Node(key, value, 1, RED);

        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        node.N = size(node.left) + size(node.right) + 1;

        return node;
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
     * @param node is the node which is the root of the current subtree.
     * @return is the number of keys that are less  then the given keys subtree.
     */
    private int rank(Key key, Node node) {

        if (node == null)
            return 0;

        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            return rank(key, node.left);
        else if (cmp > 0)
            return 1 + size(node.left) + rank(key, node.right);
        else
            return size(node.left);
    }


    /**
     *  Test the color of a node’s link to its parent.
     *
     * @param node is the node being tested.
     * @return is true if the value is red, else false.
     */
    private boolean isRed(Node node)
    {
        if (node == null)
            return false;

        return node.color == this.RED;
    }

    /**
     * Switches a right leaning red link to the left.
     *
     * @param node is the node being rotated.
     * @return is a link to a node that is the root of a red-black BST for the same set of keys as parameter node,
     *         but whose left link is red.
     */
    Node rotateLeft(Node node) {

        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = 1 + size(node.left) + size(node.right);

        return x;
    }

    /**
     * Converts a left-leaning red link to a right-leaning one.
     *
     * @param node is the link that is leaned to the right.
     * @return is a link to a node that is the root of a red-black BST for
     *         the same set of keys as parameter node, but whose right link is red
     */
    Node rotateRight(Node node) {

        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = 1 + size(node.left) + size(node.right);

        return x;
    }

    /**
     * Flips the colors of the two red children of a given node,
     *
     * @param node is the node who's children is being flipped to black.
     */
    void flipColors(Node node) {

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
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
     * @param node is the root of the current subtree.
     * @return is the value of the largest key.
     */
    private Node max(Node node) {

        if (node.right == null)
            return node;

        return max(node.right);
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
