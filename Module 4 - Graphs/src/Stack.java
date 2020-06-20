/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 191002
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The represents a push-down stack implemented as a linked list. It serves as LIFO queue where the last item added is the first
 *  being removed if asked by the client.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Graphs Lab PM as well as algorithm 1.2 in the course literature "Algorithms"
 *  by Sedgewick and Wayne
 *
 */

import java.util.Iterator;

/**
 * A push down (LIFO) stack based on a linked-list data structure. It can be used to create
 * stacks containing any type of data.
 *
 * @param <Key> is the type of data being stored.
 */
public class Stack<Key> implements Iterable<Key>
{
    private Node first;
    private int size;

    /**
     * Contains unit tests validating the functionality of the class.
     *
     * @param args is a set of commands in the form a <code>String</code> array received from the command line.
     */
    public static void main(String[] args) {

        Stack<String> stack = new Stack();

        stack.push("77");
        stack.push("2");
        stack.push("3");
        stack.push("abc");
        stack.push("5");
        stack.push("11");
        stack.push("3");

        System.out.println(stack);

        stack.pop();
        stack.pop();

        System.out.println(stack);

        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println(stack);

        stack.pop();
        stack.pop();

        try {
            stack.pop();
        }catch (IllegalStateException e) {
            System.out.print(e);
        }
    }

    /**
     * Contains the data stored in each item of the stack.
     */
    private class Node {

        Key key;
        Node next;
    }

    /**
     * States if the stack is empty.
     *
     * @return is true if the stack is empty. False if not.
     */
     boolean isEmpty() {
        return this.first == null;
    }

    /**
     * States the number of elements in the stack.
     *
     * @return is the size of the stack i.e. the number of nodes in the linked list.
     */
    public int size() {
        return size;
    }

    /**
     * Adds an item to the first position of the stack.
     *
     * @param key is the item being added to the stack.
     */
     void push(Key key) {

        Node oldFirst = first;
        this.first = new Node();
        this.first.key = key;
        this.first.next = oldFirst;
        this.size++;
    }

    /**
     * Removes the last added item from the stack.
     *
     * @return is the removed item.
     */
     Key pop() throws java.lang.IllegalStateException {

        if (isEmpty())
            throw new IllegalStateException("Stack is empty");

        Key key = this.first.key;
        this.first = first.next;
        this.size--;

        return key;
    }

    /**
     * Returns the content of the stack in form of a <>String</>.
     *
     * @return is the content of the bag.
     */
    public String toString() {

        String content = "";

        for (Key key : this)
            content =  content + key + " ";

        return content;
    }

    /**
     * Returns a <>ListIterator</> which makes the stack iterable.
     *
     * @return is the <>ListIterator</> of the stack.
     */
    public Iterator<Key> iterator() {
        return new ListIterator();
    }

    /**
     * Makes the stack iterable i.e. so the content of the stack can be iterated through.
     */
    private class ListIterator implements Iterator<Key> {

        private Node current = first;

        /**
         * States if the stack has another item in the list.
         *
         * @return is true if there are more items in the stack. False if not.
         */
        public boolean hasNext() {
            return current != null;
        }


        /**
         * Returns the next item in the stack.
         *
         * @return is the next item of the stack.
         */
        public Key next() {

            Key key = current.key;
            current = current.next;
            return key;
        }
    }
}