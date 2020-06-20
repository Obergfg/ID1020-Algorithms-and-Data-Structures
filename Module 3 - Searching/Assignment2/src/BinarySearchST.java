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
 *  The code represents a ordered symbol table. It stores input in form of keys with its associated
 *  value in two different arrays of those types. It can output the content of those arrays to stdout.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the algorithm
 *  3.2 were used as a basis.
 *
 */

/**
 * Serves as an ordered symbol table where the keys are stored in an array.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value>{

    private Key[] keys;
    private Value[] values;
    private int N;

    /**
     * Contains unit tests to see if the code works properly.
     *
     * @param args is the supplied commands entered from the command line as an array of type <>String</>.
     */
    public static void main(String[] args) {

        BinarySearchST<String, Integer> tree = new BinarySearchST<>(100);

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
        System.out.println( tree.keys());
    }

    /**
     * Creates an ordered symbol table.
     */
    BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Comparable[capacity];
        N = 0;
    }

    /**
     * Puts a key-value pair into the table and removes key from the table if value is null.
     *
     * @param key is the key data being put to the table.
     * @param value is the value of the given key.
     */
    void put(Key key, Value value) {

        int i = rank(key);

        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        for (int j = N; j > i; j--) {

            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;

        N++;
    }

    /**
     * Returns the value paired with a given key. Null if key is absent.
     *
     * @param key is the key of interest.
     * @return is the value of the given key.
     */
    Value get(Key key) {

        if (isEmpty())
            return null;

        int i = rank(key);

        if (i < N && keys[i].compareTo(key) == 0)
            return values[i];
        else
            return null;
    }

    /**
     * States if there is a value paired with a given key.
     *
     * @param key is the key of interest.
     * @return is true if the key exists in the table, if not false.
     */
    boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * States if the table is empty or not.
     *
     * @return is true if empty, false if not.
     */
    boolean isEmpty() {
        return size() == 0;

    }

    /**
     * Number of key-value pairs in the table.
     *
     * @return is the size of the symbol table
     */
    int size(){
        return this.N;
    }

    /**
     * Computes the number of keys in the table that are smaller than key. Uses binary search non-recursively.
     *
     * @param key is the key that is tha maxim key value wanted.
     * @return is the number of keys smaller than parameter key.
     */
    int rank(Key key) {

        int low = 0, high = N-1;

        while (low <= high) {

            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0)
                high = mid - 1;
            else if (cmp > 0)
                low = mid + 1;
            else
                return mid;
        }
        return low;
    }

    /**
     * Does the same sequence of compares as a call to the non-recursive
     * implementation of rank(), but this alternate version better exposes the structure of the algorithm better.
     *
     * @param key is the key that is the max key value wanted.
     * @param lo is the low boundary of the searched array.
     * @param hi is the high boundary of the search array.
     * @return is the number of keys below the param key.
     */
    public int rank(Key key, int lo, int hi)
    {
        if (hi < lo)
            return lo;

        int mid = lo + (hi - lo) / 2;

        int cmp = key.compareTo(keys[mid]);

        if (cmp < 0)
            return rank(key, lo, mid-1);
        else if (cmp > 0)
            return rank(key, mid+1, hi);
        else
            return mid;
    }

    /**
     * Returns the smallest key.
     *
     * @return is the smallest key.
     */
    Key min(){
        return keys[0];
    }

    /**
     * Returns the largest key.
     *
     * @return is the largest key.
     */
    Key max(){
        return keys[N-1];
    }

    /**
     * Returns all the keys in the table.
     *
     * @return is all the keys of the table.
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Keys of the table from low to high in sorted order.
     *
     * @param low is the lower boundary of interest.
     * @param high is the higher boundary of interest.
     * @return is the total number of keys in the given set of keys.
     */
    Iterable<Key> keys(Key low, Key high){

        Queue<Key> q = new Queue<Key>();

        for (int i = rank(low); i < rank(high); i++)
            q.enqueue(keys[i]);
        if (contains(high))
            q.enqueue(keys[rank(high)]);

        return q;
    }

    /**
     * Returns the keys of the symbol table.
     *
     * @return is the keys of the symbol table.
     */
    Key[] getKeys() {
        return this.keys;
    }

    /**
     * Returns the values of the symbol table.
     *
     * @return is the values of the symbol table.
     */
    Value[] getValues() {
        return this.values;
    }

}
