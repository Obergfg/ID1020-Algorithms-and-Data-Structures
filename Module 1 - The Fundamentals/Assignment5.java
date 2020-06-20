/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190902
 *
 *  Date of update: --
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code creates a generalized linked queue which allows the user to insert an element at the first index of
 *  the queue and remove elements of any position of choice from the queue(k'th element of the queue). This is done by calling a
 *  number of methods which performs the previous mentioned procedures. Since the list is generic the input
 *  can be anything specified when instantiating an object of type <>Assignment5</>. The program lastly
 *  outputs the content of the list whenever an element is added or removed from it.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Fundamentals Lab PM and the course literature "Algorithms".
 */

import java.util.Iterator;

/**
 * A generalized linked queue with the ability of adding elements at the front of the queue(index 1) and to remove elements from
 * any position of the queue.
 */
public class Assignment5<Item> implements Iterable<Item>{

    private Node first;
    private int queueSize;

    /**
     * Handles all the method calls which symbolizes unit tests which ensures that the code
     * acts as intended.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        Assignment5<Integer> testRun = new Assignment5<>();

        testRun.insert(1);
        testRun.insert(2);
        testRun.insert(3);
        testRun.insert(1);
        testRun.insert(-45);
        testRun.insert(11);
        testRun.delete(6);
        testRun.delete(2);
        testRun.delete(4);
        testRun.delete(3);

        try{
            testRun.delete(4);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        try{
            testRun.delete(-10);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        try{
            testRun.delete(0);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
    }

    /**
     * Constructor which creates an empty queue.
     */
    Assignment5(){
        this.queueSize = 0;
    }

    /**
     * Adds an element to the front of the queue.
     *
     * @param element is the item being added to the queue.
     */
    void insert(Item element) {

        Node n = new Node(element);

        if(isEmpty())
            this.first = n;
        else {
            n.next = this.first;
            this.first = n;
        }

        this.queueSize++;

        System.out.println("Insert item: " + this);
    }

    /**
     * Deletes and returns the element at a given position of the queue.
     *
     * @param position is the position of the element being removed.
     * @return is the removed element.
     */
      private Item delete(int position) throws java.lang.IndexOutOfBoundsException {

          Node n;

          if(position > getQueueSize() || position < 1)
              throw new IndexOutOfBoundsException("Index " + position + " does not exist");
          else if (1 == position){

               n = this.first;
              this.first = this.first.next;

              this.queueSize--;

              System.out.println("Delete item: " + this);
          }
            else{
                Node current = this.first.next;
                Node previous = this.first;

              for (int i = 2; i < position; i++) {
                  current = current.next;
                  previous = previous.next;
              }

              n = current;

              previous.next = current.next;
              n.next = null;

              this.queueSize--;

              System.out.println("Delete item: " + this);
          }

          return n.item;
      }

    /**
     * Checks to see if the queue has any elements in it. Returns true if it is empty. False
     * if it is not.
     *
     * @return is the <>boolean</> value being returned.
     */
    boolean isEmpty(){
        return this.first == null;
    }

    /**
     * Returns the getQueueSize of the current queue.
     *
     * @return is the getQueueSize of the queue.
     */
    int getQueueSize(){
        return this.queueSize;
    }

    /**
     * Returns a string containing all the elements of the list.
     *
     * @return is the <>String</> containing all the item of the list.
     */
    public String toString(){

        StringBuilder sb = new StringBuilder();

        for(Item item: this)
            sb.append(item + " ");

        return sb.toString();
    }

    /**
     * Represents a Node consisting an <>Item</> in the queue.
     *
     */
    private class Node{

        private Item item;
        private Node next;

        /**
         * Class constructor.
         *
         * @param item is the <>Item</> each new node is given.
         */
        private Node (Item item){

            this.item = item;
            this.next = null;
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
     * An iterator class with which an instantiation makes the queue iterable.
     */
    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        /**
         * Returns true if the first node of the queue is not null.
         *
         * @return is true if the queue has one or more nodes. False if it is empty.
         */
        public boolean hasNext(){
            return current != null;
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



