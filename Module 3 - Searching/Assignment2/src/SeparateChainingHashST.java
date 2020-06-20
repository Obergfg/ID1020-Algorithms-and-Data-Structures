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
 *  The code represents a hash table. It stores input in form of keys with its associated
 *  value in array of unordered linked lists.. It can output the value of the keys to stdout.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the algorithm
 *  3.5 were used as a basis.
 *
 */

/**
 * Contains an array of linked lists, using a hash function to choose a list for each key.
 *
 * @param <Key> is the key being stored.
 * @param <Value> is the value of the keys.
 */
public class SeparateChainingHashST<Key, Value> {

    private int N;
    private int tableSize;
    private SequentialSearchST<Key, Value>[] st;

    /**
     * Contains unit tests to see if the code works properly.
     *
     * @param args is the supplied commands entered from the command line as an array of type <>String</>.
     */
    public static void main(String[] args) {

        SeparateChainingHashST<String, Integer> tree = new SeparateChainingHashST<>();

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
        System.out.println(tree.hash("11"));
        System.out.println(tree.hash("3"));
        System.out.println(tree.keys());
    }

    /**
     * Constructs a SeparateChainingHashST object.
     */
    public SeparateChainingHashST() {

        this(997);
    }

    /**
     * Constructs a SeparateChainingHashST object.
     *
     * @param tableSize is the size of the hash table.
     */
     SeparateChainingHashST(int tableSize) {

        this.tableSize = tableSize;

        this.st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[tableSize];

        for (int i = 0; i < tableSize; i++)
           this.st[i] = new SequentialSearchST();

    }

    /**
     * Returns the hash value of a given key.
     *
     * @param key is the give of interest.
     * @return is the hash value of the key.
     */
    private int hash(Key key) {

        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    /**
     * Returns the value of a given key.
     *
     * @param key is the key of interest.
     * @return is the value of the given key. Null if not present.
     */
    public Value get(Key key) {

        return (Value) this.st[hash(key)].get(key);
    }

    /**
     * Insets keys to the hash table.
     *
     * @param key is the key being inserted.
     * @param val is the value of the key.
     */
    public void put(Key key, Value val) {

        this.st[hash(key)].put(key, val);
    }

    /**
     * Returns the keys of the hash table in form of a queue.
     *
     * @return is a queue containing all the keys of the table.
     */
    public Iterable<Key> keys() {

        Queue<Key> keys = new Queue<>();

        for(SequentialSearchST sequentialSearchST: this.st)
            for(Object key : sequentialSearchST.keys())
                keys.enqueue((Key) key);

        return keys;
    }
}
