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
 *  value in parallel arrays. If a new key hashes to an empty entry, it is stored there at that index.
 *  If not, the code scans sequentially to find an empty position. To search for a key, the list is scanned
 *  sequentially starting at its hash index until finding null (search miss) or the key (search hit).
 *  It can output the value of the keys to stdout.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the algorithm
 *  3.6 were used as a basis.
 *
 */

/**
 * A hash symbol-table implementation which keeps keys and values in parallel arrays.
 *
 * @param <Key> is the keys being stored.
 * @param <Value> is tha value of the keys.
 */
public class LinearProbingHashST<Key, Value> {

    private int N;
    private int tableSize = 16;
    private Key[] keys;
    private Value[] values;

    /**
     * Contains unit tests to see if the code works properly.
     *
     * @param args is the supplied commands entered from the command line as an array of type <>String</>.
     */
    public static void main(String[] args) {

        LinearProbingHashST<String, Integer> tree = new LinearProbingHashST<>();

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

    /**
     * Creates a hash table.
     *
     */
    public LinearProbingHashST() {

        keys = (Key[]) new Object[tableSize];
        values = (Value[]) new Object[tableSize];
    }


    /**
     * Creates a hash table of a given size.
     *
     * @param tableSize is th e size of the created hash table.
     */
    LinearProbingHashST(int tableSize) {

        this.tableSize = tableSize;

        keys = (Key[]) new Object[tableSize];
        values = (Value[]) new Object[tableSize];
    }

    /**
     * Returns the hash value of a given key.
     *
     * @param key is the key of interest,
     * @return is the hash value of the key of interest.
     */
    private int hash(Key key) {

        return (key.hashCode() & 0x7fffffff) % this.tableSize;
    }

    /**
     * Inserts a kay and its associated value into the hash table. Re-sizes the table if
     * the table becomes more than half full.
     *
     * @param key si the key being inserted.
     * @param value is the value of the key being inserted.
     */
    public void put(Key key, Value value) {

        if (N >= tableSize/2)
            resize(2*tableSize);

        int i;

        for (i = hash(key); keys[i] != null; i = (i + 1) % tableSize)
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    /**
     * Re-sizes the hash table to a specified size.
     *
     * @param cap is the new size of the hash table.
     */
    private void resize(int cap)
    {
        LinearProbingHashST<Key, Value> temp;

        temp = new LinearProbingHashST<>(cap);

        for (int i = 0; i < tableSize; i++)
            if (keys[i] != null)
                temp.put(keys[i], values[i]);

        keys = temp.keys;
        values = temp.values;
        tableSize = temp.tableSize;
    }

    /**
     * Returns the value of a given key.
     *
     * @param key is the key of interest.
     * @return is the value of the key of interest. Null if not present.
     */
    public Value get(Key key) {

        for (int i = hash(key); keys[i] != null; i = (i + 1) % tableSize)
            if (keys[i].equals(key))
                return values[i];

        return null;
    }

    /**
     * Returns the keys of the hash table in form of a queue.
     *
     * @return is a queue containing all the keys of the table.
     */
    public Iterable<Key> keys() {

        Queue<Key> keys = new Queue<>();

        for(Object key : this.keys)
            if (key != null)
                keys.enqueue((Key) key);

        return keys;
    }

}
