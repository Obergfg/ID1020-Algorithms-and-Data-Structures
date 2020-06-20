/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190903
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code creates a linked list of integers which allows the user to insert elements in an ascending
 *  order, and remove elements of any position of choice from the queue(k'th element of the queue). This is done by in
 *  the main method calling a number of instance methods which performs the previous mentioned procedures. The program lastly
 *  outputs the content of the list to stdout whenever an element is added or removed from it.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Fundamentals Lab PM and the course literature "Algorithms".
 */

import java.util.Iterator;

/**
 * A linked list with the ability of adding elements to the list in an ascending order and to remove elements from
 * any position of the list.
 */
public class Assignment6 implements Iterable{

    private Node first;
    private int listSize;

    /**
     * Handles all the method calls which symbolizes unit tests and ensures that the code
     * acts as intended.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        Assignment6 testRun = new Assignment6();

        try {
            testRun.delete(3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        testRun.insertAscending(4);
        testRun.insertAscending(0);
        testRun.insertAscending(2);
        testRun.insertAscending(6);
        testRun.insertAscending(-1);
        testRun.insertAscending(1000);
        testRun.insertAscending(5);
        testRun.insertAscending(-10000);
        testRun.delete(5);
        testRun.delete(7);
        testRun.delete(1);

        try {
            testRun.insertElement(0,0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            testRun.insertElement(0,-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            testRun.delete(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }


    }

    /**
     * Constructor which creates an empty list.
     */
    Assignment6(){
        this.listSize = 0;
    }

    /**
     * Adds an element to the list in an ascending order.
     *
     * @param element is the <>int</> being added to the list.
     */
    void insertAscending(int element) {


        if(!isEmpty()){

            Node current = this.first;

            int position = 1;

            while (current != null && current.element < element){
                current = current.next;
                position++;
            }

            insertElement(element, position);

        }else
            insertElement(element,1);

        System.out.println("List size: " + getListSize());
     }


    /**
     System.out.println("Inserted integer " + element + ", complete list: " + this);
     * Inserts an element at a given position.
     *
     * @param element is the element being inserted.
     * @param position is the position the given element is inserted at.
     * @throws IndexOutOfBoundsException when a given index is either to large or below 1.
     */
   private void insertElement(int element, int position) throws java.lang.IndexOutOfBoundsException {


       if (1 > position || position > (this.listSize + 1))
           throw new java.lang.IndexOutOfBoundsException("Index " + position + " does not exist");
       else if (1 < position) {

           Node n = new Node(element);
           Node previous = this.first;

           for (int i = 1; i < position - 1; i++)
               previous = previous.next;

           n.next = previous.next;
           previous.next = n;
       } else {

           Node n = new Node(element);
           n.next = this.first;
           this.first = n;
       }

       this.listSize++;
    }

    /**
     * Deletes and returns the element at a given position of the list.
     *
     * @param position is the position of the element being removed.
     * @return is the removed element.
     */
    private int delete(int position) throws java.lang.IndexOutOfBoundsException {

        Node n;

        if(position > getListSize() || position < 1)
            throw new IndexOutOfBoundsException("Index " + position + " does not exist");
        else if (1 == position){

            n = this.first;
            this.first = this.first.next;
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
        }

        this.listSize--;


        System.out.println("List size: " + getListSize());
        System.out.println("Deleted element: " + n.element + ", Complete list: " + this);

        return n.element;
    }

    /**
     * Checks to see if the list has any elements in it. Returns true if it is empty. False
     * if it is not.
     *
     * @return is the <>boolean</> value being returned.
     */
    boolean isEmpty(){
        return this.first == null;
    }

    /**
     * Returns the list size of the current list.
     *
     * @return is the size of the list.
     */
    int getListSize(){
        return this.listSize;
    }

    /**
     * Returns a string containing all the elements of the list.
     *
     * @return is the <>String</> containing all the element of the list.
     */
    public String toString(){

        StringBuilder sb = new StringBuilder();

        for(Object element: this)
            sb.append(element + " ");

        return sb.toString();
    }

    /**
     * Represents a Node consisting an <>int</> in the list.
     *
     */
    private class Node{

        private int element;     //Class attributes
        private Node next;

        /**
         * Class constructor.
         *
         * @param element is the <>int</> each new node is given.
         */
        private Node (int element){

            this.element = element;
            this.next = null;
        }
    }

    /**
     * Returns an <>Iterator</> which iterates through the current list.
     *
     * @return is the <>Iterator</>
     */
    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    /**
     * An iterator which makes the list iterable.
     */
    private class ListIterator implements Iterator<Integer> {

        private Node current = first;

        /**
         * Returns true if the current node of the list is not null.
         *
         * @return is true if the list has one or more nodes. False if it is empty.
         */
        public boolean hasNext(){
            return current != null;
        }

        /**
         * Iterates through the list and makes the <>Node</> of the list the current one.
         *
         * @return is the element of the current <>Node</>.
         */
        public Integer next() {

            Integer element = current.element;
            current = current.next;

            return element;
        }
    }
}



