/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190921
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code represents a unordered symbol table. It stores input in form of keys with its associated
 *  value in a linked list. Keys can be searched for by a sequential searching algorithm.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the algorithm
 *  3.1 were used as a basis.
 *
 */

/**
 * Keeps keys and values in an unordered linked list and finds them doing sequential searches.
 *
 * @param <Key> are the keys being stored.
 * @param <Value> are the values of the keys.
 */
public class SequentialSearchST<Key, Value>{

    public static void main(String[] args) {

        SequentialSearchST<String, Integer> tree = new SequentialSearchST<>();

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
        System.out.println(tree.keys());
    }



    private Node first;

    /**
     * Contains the data of the linked list.
     */
    private class Node {

        Key key;
        Value value;
        Node next;

        /**
         * Constructs a <>Node</>
         *
         * @param key is the key of the <code>Node</code>
         * @param val is the value of the key.
         * @param next is the node next in the list.
         */
         Node(Key key, Value val, Node next) {

            this.key = key;
            this.value = val;
            this.next = next;
        }
    }

    /**
     * Searches for a given key.
     *
     * @param key is the key being searched for.
     * @return is the value of the searched key. Null if key not present.
     */
    public Value get(Key key) {

        for (Node node = first; node != null; node = node.next)
            if (key.equals(node.key))
                return node.value;

        return null;
    }

    /**
     * Searches for a key and updates the value if found. Else it puts the new key into the list.
     *
     * @param key is the key being inserted.
     * @param value is the value of the key.
     */
    public void put(Key key, Value value) {

        for (Node node = first; node != null; node = node.next)
            if (key.equals(node.key)) {
                node.value = value;
                return; }

        first = new Node(key, value, first);
    }

    /**
     * Returns teh keys of the linked list.
     *
     * @return is the keys of the linked list
     */
    public Iterable<Key> keys() {
        Queue<Key> keys = new Queue<>();

        for(Node node = first; node != null; node = node.next) {
            keys.enqueue(node.key);
        }

        return keys;
    }
}
