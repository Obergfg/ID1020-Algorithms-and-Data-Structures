/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190914
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code creates an iterable linked list which sorts the element of a given int array in ascending order when
 *  the elements are inserted to the list. After each insertion the list gets printed to stdout so the insertion
 *  process can be observed by the user. Individual int elements can also be added by calling the method enqueue and
 *  elements of a given index can also be removed by calling the method delete and the list gets printed when an
 *  element gets removed as well.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Sorting Lab PM.
 */

import java.util.Iterator;

/**
 * A linked list with the ability of adding elements to the list in an ascending order and to remove elements from
 * any position of the list.
 */
public class Assignment7 implements Iterable{

    private Node first;
    private int listSize;

    /**
     * Handles all the method calls which symbolizes unit tests and ensures that the code
     * acts as intended.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,0,15,0,15,0,-1};

        Assignment7 testrun = new Assignment7(array);


        testrun.delete(5);

        int[] array2 = new int[]{};

        try {
            Assignment7 testrun2 = new Assignment7(array2);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * Constructor which creates an empty list.
     */
    Assignment7(){
        this.listSize = 0;
    }

    /**
     * A constructor which takes an array as argument and inserts the elements of the array
     * to the current linked list in ascending order.
     *
     * @param elements is the array of elements being inserted to the linked list.
     */
    Assignment7(int[] elements) throws java.lang.IllegalArgumentException {

        if (0 == elements.length)
            throw new IllegalArgumentException("Empty Array");

        this.listSize = 0;

        for(int element: elements)
            enqueue(element);
    }

    /**
     * Enqueues an element to the list in an ascending order.
     *
     * @param element is the <>int</> being added to the list.
     */
    void enqueue(int element) {

        Node node = new Node(element);

        if(!isEmpty() && this.first.element < element){

                Node previous = this.first;

                while (previous.next != null && previous.next.element < element)
                        previous = previous.next;

                        node.next = previous.next;
                        previous.next = node;

        }else if(!isEmpty() && this.first.element >= element) {
            node.next = this.first;
            this.first = node;
        } else
            this.first = node;

        this.listSize++;
        System.out.println(this.toString());
    }

    /**
     * Deletes and returns the element at a given position of the list.
     *
     * @param position is the position of the element being removed.
     * @return is the removed element.
     */
    private int delete(int position) throws java.lang.IndexOutOfBoundsException {

        Node node;

        if(position > getListSize() || position < 1)
            throw new IndexOutOfBoundsException("Index " + position + " does not exist");
        else if (1 == position){

            node = this.first;
            this.first = this.first.next;
        }
        else{
            Node current = this.first.next;
            Node previous = this.first;

            for (int i = 2; i < position; i++) {
                current = current.next;
                previous = previous.next;
            }

            node = current;

            previous.next = current.next;
            node.next = null;
        }

        this.listSize--;

        System.out.println("Deleted element: " + node.element + ", Complete list: " + this);

        return node.element;
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

        private int element;
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



