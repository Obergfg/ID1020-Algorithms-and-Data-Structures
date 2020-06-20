/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190902
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code creates an iterable FIFO-queue which is doubly linked. Each node points to the next
 *  and the previous item of the list. The code adds new elements to the last position of the queue
 *  and removes elements which are at the first position of the queue.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Fundamentals Lab PM
 *  and the course literature "Algorithms".
 *
 */

import java.util.Iterator;

/**
 * A generic iterable FIFO-queue which is double linked.
 *
 */
public class Queue<Item> implements Iterable<Item>  {

    private Node first;
    private Node last;
    private int size;

    /**
     * Handles all the method calls which symbolizes unit tests which ensures that the code
     * acts as intended.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        Queue<String> testRun = new Queue<>();

        System.out.println("The program has started\n");


        try{
            System.out.println("Dequeue item: " + testRun.dequeue().item);
        }catch (IllegalStateException e){
            System.out.println(e);
        }

        System.out.println("isEmpty test: " + testRun.isEmpty());
        System.out.println("Size test: " + testRun.size());
        System.out.println("Queue print: " + testRun);

        System.out.println();

        testRun.enqueue("First");
        System.out.println("isEmpty test: " + testRun.isEmpty());
        System.out.println("Size test: " + testRun.size());
        System.out.println("First item test: " + testRun.first.item);
        System.out.println("Last item test: " + testRun.last.item);

        System.out.println();

        testRun.enqueue("Second");
        System.out.println("isEmpty test: " + testRun.isEmpty());
        System.out.println("Size test: " + testRun.size());
        System.out.println("First item test: " + testRun.first.item);
        System.out.println("Last item test: " + testRun.last.item);
        System.out.println("First item next: " + testRun.first.next.item);
        System.out.println("Last item previous: " + testRun.last.previous.item);

        System.out.println();

        testRun.enqueue("Third");
        System.out.println("Size test: " + testRun.size());
        System.out.println("First item test: " + testRun.first.item);
        System.out.println("Last item test: " + testRun.last.item);
        System.out.println("First item next: " + testRun.first.next.item);
        System.out.println("Last item previous: " + testRun.last.previous.item);


        System.out.println();

        System.out.println("Dequeue item: " + testRun.dequeue().item);
        System.out.println("Size test: " + testRun.size());
        System.out.println("First item test: " + testRun.first.item);
        System.out.println("Last item test: " + testRun.last.item);
        System.out.println("First item next: " + testRun.first.next.item);
        System.out.println("Last item previous: " + testRun.last.previous.item);


        System.out.println();

        System.out.println("Dequeue item: " + testRun.dequeue().item);
        System.out.println("Size test: " + testRun.size());
        System.out.println("First item test: " + testRun.first.item);
        System.out.println("Last item test: " + testRun.last.item);

        System.out.println();

        System.out.println("Dequeue item: " + testRun.dequeue().item);
        System.out.println("Size test: " + testRun.size());
        System.out.println("isEmpty test: " + testRun.isEmpty());

        try{
            System.out.println("Dequeue item: " + testRun.dequeue());
        }catch (IllegalStateException e){
            System.out.println(e);
        }
    }

    /**
     * Constructor which creates an empty queue.
     */
    Queue(){
        this.size = 0;
    }

    /**
     * Adds an item to the queue and puts it at the last position.
     *
     * @param element is the item being added to the queue.
     */
    void enqueue(Item element) {

        Node n = new Node(element);

        if(isEmpty()) {
            this.first = n;
            this.last = n;
        }else{
            this.last.next = n;
            n.previous = this.last;
            this.last = n;
        }

        this.size++;
    }

    /**
     * Removes the first item being added from the queue.
     *
     * @return is the item being removed.
     * @throws IllegalStateException when the queue is empty.
     */
    Node dequeue()throws java.lang.IllegalStateException{

        if (isEmpty())
            throw new java.lang.IllegalStateException("The queue is empty");
        else if (1 == this.size){

            Node n = this.first;
            this.first = null;

            this.size--;
            System.out.println("dequeue print: " + this.toString());

            return n;
        }
        else {
            Node n = this.first;

            this.first = this.first.next;
            this.first.previous = null;

            n.next = null;
            n.previous = null;

            this.size--;
            System.out.println("dequeue print: " + this.toString());

            return n;
        }
    }

    /**
     * Checks to see if the queue ha any elements in it. Returns true if it is empty. False
     * if it is not.
     *
     * @return is the <>boolean</> value being returned.
     */
    boolean isEmpty(){
        return this.first == null;
    }

    /**
     * Returns the size of the current queue.
     *
     * @return is the size of the queue.
     */
    int size(){
        return this.size;
    }

    /**
     * Returns a string containing all the elements of the queue.
     *
     * @return is the <>String</> containing all the item of the queue.
     */
    public String toString(){

        StringBuilder sb = new StringBuilder();

            for(Item item: this)
                sb.append(item + " ");

            return sb.toString();
    }

    /**
     * Represents a Node consisting an <>Item</> of the FIFO-queue.
     *
     */
    private class Node{

        private Item item;
        private Node next;
        private Node previous;

        /**
         * Class constructor.
         *
         * @param item is the <>Item</> each new node is given.
         */
        private Node (Item item){

            this.item = item;
            this.next = null;
            this.previous = null;
        }
    }

    /**
     * Returns a generic <>Iterator</> which iterates through the current queue.
     *
     * @return is the <>Iterator</>
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * An iterator which makes the queue iterable.
     */
    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        /**
         * Returns true if the first node of the list is not null.
         *
         * @return is true if the list has one or more nodes. False if it is empty.
         */
        public boolean hasNext(){
            return this.current != null;
        }

        /**
         * Iterates through the queue and makes the <>Node</> of the queue the current one.
         *
         * @return is the item of the current <>Node</>.
         */
        public Item next() {

            Item item = current.item;
            current = current.next;

            return item;
        }
    }
}
