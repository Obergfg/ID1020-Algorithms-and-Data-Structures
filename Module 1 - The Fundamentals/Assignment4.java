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
 *  The code creates a generic iterable circular linked list which is double linked. It allows the user
 *  to insert and remove elements to/from the front and back end of the list. This is done by calling a
 *  number of methods which performs the previous mentioned procedure. Since the list is generic the input
 *  can be anything specified when instantiating an object of typ <>Assignment4</>. The program lastly
 *  outputs the content of the list whenever an element is added or removed from it.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Fundamentals Lab PM and the course literature "Algorithms".
 */

import java.util.Iterator;

/**
 * A generic iterable circular linked list which is double linked.
 *
 */
public class Assignment4<Item> implements Iterable<Item> {

        private Node first;
        private Node last;
        private int listSize;

        /**
         * Handles all the method calls which symbolizes unit tests which ensures that the code
         * acts as intended.
         *
         * @param args contains the supplied command-line arguments as an array of String objects.
         */
        public static void main(String[] args) {

            Assignment4<String> testRun = new Assignment4<>();

            testRun.pushFront("Coming");

            testRun.pushFront("Is");

            testRun.pushBack("And");

            testRun.pushBack("Pays");

            testRun.pushBack("Its");

            testRun.pushBack("Debts");

            testRun.pushFront("Winter");

            testRun.frontPop();

            testRun.frontPop();

            testRun.backPop();

            testRun.backPop();

            testRun.frontPop();

            testRun.frontPop();

            testRun.backPop();

            try {
                testRun.backPop();
            } catch (IllegalStateException e) {
                System.out.println(e);
            }
        }

        /**
         * Constructor which creates an empty circular list.
         */
        Assignment4(){
            this.listSize = 0;
        }

        /**
         * Adds an element to the front of the circular list.
         *
         * @param element is the item being added to the list.
         */
        void pushFront(Item element) {

            Node n = new Node(element);

            if (isEmpty()) {
                pushEmpty(n);
            }else{

                n.next = this.first;
                n.previous = this.last;
                this.first.previous = n;
                this.last.next = n;
                this.first = n;
            }

            this.listSize++;
            System.out.println("pushFront printout: " + this);
        }

        /**
         * Adds an element to the back of the circular list.
         *
         * @param element is the element being added to the queue.
         */
         void pushBack(Item element) {

             Node n = new Node(element);

             if (isEmpty()) {
                 pushEmpty(n);
             }else{
                 n.next = this.first;
                 n.previous = this.last;
                 this.first.previous = n;
                 this.last.next = n;
                 this.last = n;
             }

             this.listSize++;
             System.out.println("pushBack printout: " + this);
         }

            /**
             * Adds a node to an empty list.
             *
             * @param n is the <>Node</> being added to the list
             */
             private void pushEmpty(Node n) {

                n.next = n;
                n.previous = n;
                this.first = n;
                this.last = n;
            }

        /**
         * Removes the first item from the list.
         *
         * @return is the item being removed.
         * @throws IllegalStateException when the queue is empty.
         */
        Node frontPop()throws java.lang.IllegalStateException{

            if (isEmpty())
                throw new IllegalStateException("Empty List");
            else if(1 == this.listSize)
                return popLast();
            else {
                Node n = this.first;
                this.first = this.first.next;
                this.first.previous = this.last;
                this.last.next = this.first;
                n.next = n;
                n.previous = n;

                this.listSize--;

                System.out.println("frontPop printout: " + this);

                return n;
            }
        }

    /**
     * Removes the last item from the list.
     *
     * @return is the item being removed.
     * @throws IllegalStateException when the queue is empty.
     */
     Node backPop()throws java.lang.IllegalStateException{

         if (isEmpty())
             throw new IllegalStateException("Empty List");
         else if(1 == this.listSize)
             return popLast();
         else {
             Node n = this.last;
             this.last = this.last.previous;
             this.first.previous = this.last;
             this.last.next = this.first;
             n.next = n;
             n.previous = n;

             this.listSize--;

             System.out.println("backPop printout: " + this);

             return n;
         }
     }

        /**
         * Removes the last Node of the list.
         *
         * @return is the last being removed.
         */
        private Node popLast() {

             Node n = this.first;
             this.first = null;
             this.last = null;

            this.listSize--;

            System.out.println("popLast printout: " + this);


             return n;
         }

        /**
         * Checks to see if the list ha any elements in it. Returns true if it is empty. False
         * if it is not.
         *
         * @return is the <>boolean</> value being returned.
         */
        boolean isEmpty(){
            return this.first == null;
        }

        /**
         * Returns the listSize of the current list.
         *
         * @return is the listSize of the list.
         */
        int size(){
            return this.listSize;
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
         * Represents a Node consisting an <>Item</> in the circular list.
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
         * Returns a generic <>Iterator</> which iterates through the current list.
         *
         * @return is the <>Iterator</>
         */
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        /**
         * An iterator which makes the list iterable.
         */
        private class ListIterator implements Iterator<Item> {

            private Node current = first;
            private int listPos = 0;

            /**
             * Returns true if the first node of the list is not null.
             *
             * @return is true if the list has one or more nodes. False if it is empty.
             */
            public boolean hasNext(){
                return this.listPos < listSize;
            }

            /**
             * Iterates through the list and makes the <>Node</> of the queue the current one.
             *
             * @return is the item of the current <>Node</>.
             */
            public Item next() {

                Item item = current.item;
                current = current.next;
                listPos++;
                return item;

            }
        }
}



