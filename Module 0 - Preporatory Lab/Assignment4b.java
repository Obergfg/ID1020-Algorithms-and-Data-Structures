/**
 *
 *  Author: Fredrik Öberg
 *
 *  Date of generation: 190901
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  This program takes an <code>int[]<code/> as input and creates a double linked list with the data from
 *  the array sorted ascending as a result, and then prints the content of the array to stdout. The doublyLinkedList
 *  has an integer attribute as well as two node attribute referencing two different nodes of the list - that is if
 *  the list does'nt contain one or two nodes, then it only references itself or the other node. The code can
 *  remove or insert new nodes based on what methods you call, and they can be inserted by position or by ascending values.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Preporatory Lab PM.
 */

import java.util.NoSuchElementException;

/**
 * This class represents a list which is linked by two nodes. One to
 * the next element (Node next) and one to the previous element(Node previous) of the list.
 *
 */
public class Assignment4b {

    /**
     * Creates a doubly linked list and uses mostly static method to test if all methods in <>Assignment4b</>
     * works as they should.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        int[] elements = new int[]{3,2,1,4,-1,-1};

        Assignment4b list = new Assignment4b(elements);

        System.out.println("Listan: " + list);
        System.out.println("Listlängd: " + list.getListLength());

        System.out.println("Head : " + list.getHead().getData());
        System.out.println("Head previous: " + list.getHead().getPrevious().getData());
        System.out.println("Head previous, previous: " + list.getHead().getPrevious().getPrevious().getData());
        System.out.println("Head previous, previous, previous: " + list.getHead().getPrevious().getPrevious().getPrevious().getData());

        System.out.println("Head next: " + list.getHead().getNext().getData());
        System.out.println("Head next, next: " + list.getHead().getNext().getNext().getData());
        System.out.println("Head next, next, next: " + list.getHead().getNext().getNext().getNext().getData());
        System.out.println();

        testInsertAscending(list,4);
        testInsertElement(list,5,3);
        testRemoveElement(list,3);
        testRemoveElement(list,-1);
        testRemoveElement(list,-1);
        testRemoveElement(list,5);
        testRemoveElement(list,1);
        testRemoveElement(list,2);
        testRemoveElement(list,4);

        try {
            Assignment4b.Node removed =  list.removeElement(4);
            System.out.println("Removed Node: " + removed.data);

            System.out.println("Listan efter removeElement: " + list);
            System.out.println("Listlängd: " + list.getListLength());
        } catch (NoSuchElementException e) {
            System.out.println("¤¤¤ No such element ¤¤¤");
        }

        try {
            list.removeElement(4);
        } catch (NoSuchElementException e) {
            System.out.println("¤¤¤ No such element ¤¤¤");
        }
    }

    /**
     * This class represents the nodes of the Assignment4b.
     */
          class Node {
            int data;
            Node next;
            Node previous;

        /**
         * Constructor of the Node class.
         *
         * @param data is the data stored in each node.
         */
       private Node(int data) {

                this.data = data;
                this.next = this;
                this.previous = this;
            }

        /**
         * A get method for the data attribute.
         *
         * @return is the data of the current <>Node</>
         */
        private int getData() {
            return data;
        }

        /**
         * A get method for the next attribute.
         *
         * @return is the next <>Node</> of the current <>Node</>
         */
       private Node getNext() {
            return next;
        }


        /**
         * A get method for the previous attribute.
         *
         * @return is the previous <>Node</> of the current <>Node</>
         */
        private Node getPrevious() {
            return previous;
        }

        /**
         * Returns the content of a <>Node</> in the form of a <>String</>.
         *
         * @return is the <>String</> containing each nodes attributes
         */
        public String toString(){
            return "Node data: " + this.data + ", Node next: " + this.next.data + ", Node previous: " + this.previous.data;
        }
    }

        private Node head;              //The first Node of the list
        private int listLength;         //Number of Nodes in                   the list.

    /**
     * A constructor for the Assignment4b class which recives an array of integers
     * and creates a list in ascending order.
     *
     * @param elements is the elements from which the list is created.
     */
    private Assignment4b(int[] elements)throws java.lang.IllegalArgumentException {

         if (elements.length == 0)
             throw new java.lang.IllegalArgumentException();

         this.head = new Node(elements[0]);
         this.listLength = 1;

         for (int i = 1; i < elements.length; i++)
                insertAscending(elements[i]);
    }

    /**
     * This method is used when the user wnats to add an element to a specific position of the list.
     *
     * @param element is the value of the added <>Node</>.
     * @param position is the position of the new <>Node</>.
     */
   private void insertElement(int element, int position) {

        Node newNode = new Node(element);

        if (1 == position) {
            newNode.next = this.head;
            newNode.previous = this.head.previous;
            this.head.previous.next = newNode;
            this.head.previous = newNode;
            this.head = newNode;
        }else
            insertPositionLargerThanOne(newNode, position);

        this.listLength++;
    }

    /**
     * This method is called upon when an element is added on a position larger than one.
     *
     * @param newNode is the node added to the list.
     * @param position is the position of the new <>Node</>.
     */
    private void insertPositionLargerThanOne(Node newNode, int position) {

        Node previous = this.head;
        int pos = 2;

        while ( pos < position ) {
            previous = previous.next;
            pos++;
        }

        Node next = previous.next;

        newNode.next = next;
        newNode.previous = previous;
        previous.next = newNode;
        next.previous = newNode;
    }

    /**
     * This method inserts a new element into a list in its correct ascending place. The list need to be
     * sorted in an ascending for the method to work correctly.
     *
     * @param element is the element that is to be inserted into the list.
     */
   private void insertAscending(int element) {

        if (element <= this.head.data)
            insertElement(element, 1);
        else {
            Node current = this.head.next;
            int pos = 2;

            while((pos <= this.listLength) && (current.data < element)) {
                current = current.next;
                pos++;
            }

            insertElement(element, pos);
        }
    }

    /**
     * This method removes a given element from a <>Assignment4b</> and returns a reference
     * to the removed element. The removed elements next and previous references are set to
     * point at the removed element.
     *
     * @param element is the data value of the element being removed.
     * @return is the removed <>Node</>.
     */
   private Node removeElement(int element) {

        Node removedElement;

         if (this.listLength == 0)
           throw new java.util.NoSuchElementException("The list is empty");
         else if (element == this.head.data && this.listLength == 1) {

            removedElement = this.head;
            removedElement.next = removedElement;
            removedElement.previous = removedElement;

            this.head = null;
            this.listLength--;

        } else if (element == this.head.data) {

            removedElement = this.head;

            this.head = this.head.next;
            this.head.previous = removedElement.previous;
            removedElement.previous.next = this.head;

            removedElement.next = removedElement;
            removedElement.previous = removedElement;

            this.listLength--;

        } else
            removedElement = removePositionLargerThanOne(element);

        return removedElement;
    }

    /**
     * A method called upon when an element on a position higher than one is
     * going to be removed. Implemented to make the method smaller and easier too understand.
     *
     * @return is the <>Node</> being removed.
     */
    private Node removePositionLargerThanOne(int element) throws java.util.NoSuchElementException {

        Node removedElement;
        Node current = this.head.next;
        int pos = 2;

        while (element != current.data) {
            current = current.next;
            pos++;

            if(pos > this.listLength)
                throw new java.util.NoSuchElementException();
        }

        removedElement = current;
        current.previous.next = current.next;
        current.next.previous = current.previous;

        removedElement.next = removedElement;
        removedElement.previous = removedElement;

        this.listLength--;

        return removedElement;
    }

    /**
     * A method which prints the elements of the current list in order from the head to
     * the last element.
     *
     * @return is the string which is created and whose content is the current lists element.
     */
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node n = this.head;

        for (int i = 0; i < this.listLength; i++) {

            sb.append(n.data + " ");
            n = n.next;
        }

        return sb.toString();
    }

    /**
     * A method used to get the head <>Node</> of the current list.
     *
     * @return is the head <>Node</> of the current list.
     */
  private Node getHead(){
        return this.head;
    }

    /**
     * A get method for the attribute listLength.
     *
     * @return is the length of the current <>Assignment4b</>.
     */
    private int getListLength() {
        return listLength;
    }




    //The following methods are test methods.


    /**
     * Tests the insertAscending method and was created with the intention of
     * reusing code and thereby making the program smaller. Normally this wouldn't be the procedure
     * but instead true unit test classes would be made.
     *
     * @param list is the list being tested.
     * @param element is the element being inserted to the parameter "list".
     */
   private static void testInsertAscending(Assignment4b list, int element) {

        list.insertAscending(element);

        System.out.println("Listlängd: " + list.getListLength());

        System.out.println("Listan efter insertAscending: " + list);

        System.out.println("Head : " + list.getHead().getData());
        System.out.println("Head previous: " + list.getHead().getPrevious().getData());
        System.out.println("Head previous, previous: " + list.getHead().getPrevious().getPrevious().getData());
        System.out.println("Head previous, previous, previous: " + list.getHead().getPrevious().getPrevious().getPrevious().getData());


        System.out.println("Head next: " + list.getHead().getNext().getData());
        System.out.println("Head next, next: " + list.getHead().getNext().getNext().getData());
        System.out.println("Head next, next, next: " + list.getHead().getNext().getNext().getNext().getData());
        System.out.println();
    }

    /**
     * Tests the insertElement method and was created with the intention of
     * reusing code, and thereby making the program smaller. Normally this wouldn't be the procedure
     * but instead true unit test classes would be made.
     *
     * @param list is the list being tested.
     * @param element is the element being inserted to the parameter "list".
     */
   private static void testInsertElement(Assignment4b list, int element, int pos){

        list.insertElement(element, pos);

        System.out.println("Listlängd: " + list.getListLength());

        System.out.println("Listan efter insert: " + list);

        System.out.println("Head : " + list.getHead().getData());
        System.out.println("Head previous: " + list.getHead().getPrevious().getData());
        System.out.println("Head previous, previous: " + list.getHead().getPrevious().getPrevious().getData());
        System.out.println("Head previous, previous, previous: " + list.getHead().getPrevious().getPrevious().getPrevious().getData());


        System.out.println("Head next: " + list.getHead().getNext().getData());
        System.out.println("Head next, next: " + list.getHead().getNext().getNext().getData());
        System.out.println("Head next, next, next: " + list.getHead().getNext().getNext().getNext().getData());
        System.out.println();
    }

    /**
     * Tests the removeElement method and was created with the intention of
     * reusing code, and thereby making the program smaller. Normally this wouldn't be the procedure
     * but instead true unit test classes would be made.
     *
     * @param list is the list being tested.
     * @param element is the element being removed from the parameter "list".
     */
   private static void testRemoveElement(Assignment4b list, int element){

        try {

            Assignment4b.Node removed =  list.removeElement(element);

            System.out.println("Listan efter removeElement: " + list);

            System.out.println("Listlängd: " + list.getListLength());

            System.out.println("Removed Node: " + removed);

            System.out.println("Head : " + list.getHead().getData());
            System.out.println("Head previous: " + list.getHead().getPrevious().getData());
            System.out.println("Head previous, previous: " + list.getHead().getPrevious().getPrevious().getData());
            System.out.println("Head previous, previous, previous: " + list.getHead().getPrevious().getPrevious().getPrevious().getData());


            System.out.println("Head next: " + list.getHead().getNext().getData());
            System.out.println("Head next, next: " + list.getHead().getNext().getNext().getData());
            System.out.println("Head next, next, next: " + list.getHead().getNext().getNext().getNext().getData());
            System.out.println();


        } catch (NoSuchElementException e) {
            System.out.println("¤¤¤ No such element ¤¤¤");
        }
    }
}


