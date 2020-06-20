/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 191007
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code is an indexed minimal priority queue in form of a binary heap. That means that the key with smallest
 *  value is stored at index 1 with all the child nodes of the smallest key has ha larger value. The binary heap
 *  implementation means that the children of a given index i are stored at index  i*2 and i*2 +1. The code also
 *  outputs the content to stdout. The smallest key is stored at index 1 for intuitive reasons which means that index 0
 *  has a null value and is not used.
 *
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as the code on page 333
 *  in the course literature "Algorithms" by Sedgewick and Wayne.
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a indexed minimum priority queue. Each key in the queue is associated with a given index
 * which gives the client fast access to the smallest entry in the queue.
 *
 * @param <Key> is the keys of the items being queued.
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer>{

    private int maxN;
    private int N;
    private int[] binaryHeap;
    private int[] inverseHeap;
    private Key[] keys;

    /**
     * Creates an instance with a given maximum queue size.
     *
     * @param maxN is the queues maximum size.
     */
     IndexMinPQ(int maxN) {

         this.maxN = maxN;
         this.N = 0;
         this.keys = (Key[]) new Comparable[this.maxN + 1];
         this.binaryHeap = new int[this.maxN + 1];
         this.inverseHeap = new int[this.maxN + 1];

         for (int i = 0; i <= this.maxN; i++)
             this.inverseHeap[i] = -1;
    }

    /**
     * Returns the size of the queue.
     *
     * @return is the size of the queue.
     */
    int getMaxSize() {
        return this.maxN;
    }

    /**
     * Returns the number of keys in the priority queue.
     *
     * @return is the number of keys in the priority queue
     */
     int getSize() {
        return this.N;
    }

    /**
     * States if the queue is empty or not.
     *
     * @return is true if the queue is empty, False if not.
     */
    boolean isEmpty() {
        return this.N == 0;
    }

    /**
     * States if the queue contains a key and at what index.
     *
     * @param index is the given key index of interest
     * @return is true if the queue contains the key of the given index.
     */
    public boolean contains(int index) {

        return this.inverseHeap[index] != -1; }

    /**
     * Inserts a key and associates it with the given index and makes the newly added key
     * iterate up through the heap to find its correct place.
     *
     * @param key is the key which is to be inserted at the given index.
     */
    void insert(int index, Key key) {

        this.N++;
        this.inverseHeap[index] = this.N ;
        this.binaryHeap[this.N] = index;
        this.keys[index] = key;

        swim(this.N);
    }


    /**
     * Returns the instance variable "adjacencies" index of a given key.
     *
     * @param key is the given key.
     * @return is the index of the given key.
     */
    int getIndex(Key key) throws IllegalArgumentException{

        int index = 1;

        while(null != keys[index] && index <= this.N) {

            if (0 == this.keys[index].compareTo(key))
                return index;

            index++;
        }

        throw new IllegalArgumentException("Vertex " + key + " not found");
    }

    /**
     * Returns the index of the key with smallest value.
     *
     * @return is the key with smallest value..
     */
     int minIndex() {
        return this.inverseHeap[1];
    }

    /**
     * Returns the key with smallest associated value.
     *
     * @return is the minimal key.
     */
     Key getMinKey() {
        return this.keys[this.binaryHeap[1]];
    }

    /**
     * Exchanges the keys of two given indices.
     *
     * @param index1 is the first given index.
     * @param index2 is the second given index.
     */
    private void exchange(int index1, int index2) {

        int temp = this.binaryHeap[index1];

        this.binaryHeap[index1] = this.binaryHeap[index2];
        this.binaryHeap[index2] = temp;

        this.inverseHeap[binaryHeap[index1]] = index1;
        this.inverseHeap[binaryHeap[index2]] = index2;
    }

     void decreaseKey(int index, Key key) {

        this.keys[index] = key;
        swim(this.inverseHeap[index]);
    }

    /**
     * Removes the key with the smallest value and returns its index.
     *
     * @return is the index of the removed key.
     */
     int deleteMin() {

         int minKey = this.binaryHeap[1];

         exchange(1,  this.N--);
         sink(1);

         this.inverseHeap[minKey] = -1;
         this.keys[minKey] = null;
         this.binaryHeap[this.N + 1] = -1;

         return minKey;
    }

    /**
     * Sets a key to its correct place in the heap by letting it "swim" up the heap.
     *
     * @param index is the
     */
    private void swim(int index) {

        while(index > 1  && greater(index / 2, index)) {
            exchange(index, index / 2);
            index = index / 2;
        }
    }

    /**
     * Sets a key to its correct place in the heap by letting it "sink" to its proper place in
     * the heap.
     *
     * @param index is the index of the key being sunk in the binary heap.
     */
    private void sink(int index) {

        while (index*2 <= this.N) {

            int childIndex = index*2;

            if (childIndex < this.N && greater(childIndex, childIndex + 1))
                childIndex++;

            if (smaller(childIndex, index))
                exchange(index, childIndex);

            index = childIndex;
        }
    }

    /**
     * States if a given keys value is smaller than another given keys value.
     *
     * @param index1 is the first given key.
     * @param index2 is the second given key.
     * @return is true if the first given key is smaller. False if not.
     */
    private boolean smaller(int index1, int index2) {
        return this.keys[this.binaryHeap[index1]].compareTo(this.keys[this.binaryHeap[index2]]) < 0;
    }

    /**
     * States if a key has greater value or not than a key at another given index.
     *
     * @param keyIndex1 is the index of the first given key.
     * @param keyIndex2 is the index of the first given key.
     * @return is true if the first key i larger. False if not.
     */
    private boolean greater(int keyIndex1, int keyIndex2) {
        return this.keys[this.binaryHeap[keyIndex1]].compareTo(this.keys[this.binaryHeap[keyIndex2]]) > 0;
    }

    /**
     * Returns the content of the queue in form of a string.
     *
     * @return is the content of the queue in form of a string.
     */
    public String toString() {

        String string = "";

        for (int i = 1; i <= this.N; i++)
             string = string + this.keys[this.binaryHeap[i]] + " ";

        return string;
    }

    /**
     * Returns an <>Iterator</> object which makes the content of the priority queue iterable.
     *
     * @return is the iterator object.
     */
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    /**
     * Makes the priority queue iterable.
     */
    private class HeapIterator implements Iterator<Integer> {

        private IndexMinPQ<Key> copy;

        /**
         * Creates an instance.
         */
         HeapIterator() {

            this.copy = new IndexMinPQ<>(getMaxSize());

            for (int i = 1; i <= getSize(); i++)
                this.copy.insert(binaryHeap[i], keys[binaryHeap[i]]);

        }

        /**
         * States if the heap has another element.
         *
         * @return true if the queue is not empty. False if not.
         */
        public boolean hasNext()  {
             return !copy.isEmpty();
         }

        /**
         * Returns the current smallest key of the priority queue.
         *
         * @return is the current smallest key.
         * @throws java.util.NoSuchElementException is thrown when there are no more elements in the queue.
         */
        public Integer next() throws java.util.NoSuchElementException {

            if (!hasNext())
                throw new NoSuchElementException();

            return this.copy.deleteMin();
        }
    }

    /**
     * Contains unit tests to validate the functionality of the code.
     *
     * @param args is an array of type <>String</> with a set of commands entered through the command line.
     */
    public static void main(String[] args) {

        String[] keys ={"Z" ,"T" ,"Q" ,"D" ,"E" ,"F", "G"};
        IndexMinPQ<String> pq = new IndexMinPQ<>(keys.length );

        for (int i = 0; i <  keys.length; i++)
            pq.insert(i,keys[i]);


        System.out.println(pq.getMinKey());
        System.out.println(pq);

        while(!pq.isEmpty()) {
            System.out.println(pq.getMinKey() + " ");
            pq.deleteMin();
        }
    }
}
