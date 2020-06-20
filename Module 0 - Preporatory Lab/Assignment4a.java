/**
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190901
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code creates a list of linked data structures (nodes) which contains an iniger element
 *  and a <>Node</> which points to the next node of the list. A <>Assignment4a</> is created by
 *  sending an integer array as argument to the constructor of <>Assignment4a</>. It then places
 *  the elements in ascending order by calling the method insertAscending in the <>Assignment4a</>
 *  class. The first element of the list can be removed and an element can be put in to any
 *  position of the list the is wanted. But the practice of being able to remove the first
 *  element makes this a flexible example of either a LIFO or a FIFO list since you can put in
 *  elements at both the last or first positions of the list.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Preporatory Lab PM.
 */


/**
 *Is a LIFO queue (last in first out) consisting of a number of nodes.
 *
 */
class Assignment4a {

    /**
     * Initializes calls to different functions in order to test them.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        int[] elements = new int[]{5,4,3,2,1};

        try {
            Assignment4a list = new Assignment4a(elements);
            System.out.println(list);

                list.push(33);
                System.out.println(list);

            try {
                list.insertElement(33, -1);
                System.out.println(list);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Position lower than one");
            }

            try {
                list.insertElement(100, 10);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Position to large");
            }

            System.out.println(list);

             System.out.println("Removed element: " + list.pop());
            System.out.println(list);

            System.out.println("Removed element: " + list.pop());
            System.out.println(list);

            System.out.println("Removed element: " + list.pop());
            System.out.println(list);

            int gottenElement = list.getFirstNodeData();
            System.out.println(gottenElement);
            System.out.println(list);

            list.insertAscending(-100);
            System.out.println(list);

            list.push(0);
            System.out.println(list);

            list.push(12343);
            System.out.println(list);

        }
        catch (IllegalArgumentException e){
            System.out.println("Empty Array");
        }

        try {
            elements = new int[0];
            Assignment4a list = new Assignment4a(elements);
            System.out.println(list);

        }
        catch (IllegalArgumentException e){
            System.out.println("Empty Array");
        }
    }

    /**
     * Consists of an integer element called data and a node next referencing
     * the node after the current in the LIFO queue.
     */
    private class Node {

         int data;
         Node next;

        /**
         * Class constructor. Node next is initiated to null since
         * its not certain that it will come a node after the current.
         *
         * @param data is stored in each nodes data attribute.
         */
         Node(int data) {

             this.data = data;
             this.next = null;
         }

        /**
         * Returns the data of a <>Node</> in the form of a <>String</>.
         *
         * @return is the <>String</> being returned.
         */
         public String toString() {
             return "" + this.data;
         }
     }

    private Node head;                  //<>Assignment4a</> attributes
    private int nrOfElements = 0;

    /**
     * Constructor which receives an array of typ <>int</> and calls for the method insertAscending. This repeats
     * until the array has been run through.
     *
     * @param numbers is the array from which the <>Assignment4a</> gets its data attributes.
     */
   private Assignment4a(int[] numbers) throws java.lang.IllegalArgumentException{

        if(numbers.length == 0)
                throw new java.lang.IllegalArgumentException();

          for (int i = 0; i < numbers.length; i++)
            insertAscending(numbers[i]);
       }

    /**
     * Adds an item on the first position of the <>LinkedList</>
     *
     * @param element is the element being inserted on the first position.
     */
    private void push(int element){

       insertElement(element, 1);
       }

    /**
     * Specifies if the current <>Assignment4a</> is empty or not.
     *
     * @return is true if the head attribute is null.
     */
   private boolean emptyList(){
        return this.head == null;
    }

    /**
     * Sends back the first node of a <>Assignment4a</>
     *
     * @return the head attribute of the <>Assignment4a</>.
     */
   private int getFirstNodeData() throws java.lang.IndexOutOfBoundsException {

        if(emptyList())
            throw new IndexOutOfBoundsException();

         return this.head.data;
     }

    /**
     * Removes the first node of a <>Assignment4a</> by setting the head to be
     * the node that follows the head and then returns the removed <>Node</>.
     */
    private Node pop()  throws java.lang.IndexOutOfBoundsException {

        if(emptyList())
            throw new IndexOutOfBoundsException();

        Node n = this.head;

        this.head = this.head.next;
        this.nrOfElements--;

        return n;
     }

    /**
     * Inserts an element at a given position. If the position is not 1 it calls for the method
     * insertPositionLargerThanOne due to simplifying the code. Throws an IndexOutOfBoundsException
     * if the given position is higher than the last position plus one of the list(you can insert
     * an element after the last one).
     *
     * @param element is the elements being inserted.
     * @param position is the position for the inserted element.
     */
    private void insertElement(int element, int position) throws java.lang.IndexOutOfBoundsException {

         Node newNode = new Node(element);

         if ((position > (this.nrOfElements + 1)) || position < 1)
             throw new java.lang.IndexOutOfBoundsException();
         else if(1 == position) {
             newNode.next = this.head;
             this.head = newNode;
             this.nrOfElements++;
         }
         else
            insertPositionLargerThanOne(newNode, position);
     }

    /**
     *  Inserts elements in positions which are larger than one.
     *
     * @param newNode is the <>Node</> being inserted
     * @param position is the position for the new <>Node</>.
     */
     private void insertPositionLargerThanOne(Node newNode, int position) {

         Node previous = this.head;
         int pos = 1;

         while ((previous.next != null) && (pos < position - 1)) {
             previous = previous.next;
             pos++;
         }

         newNode.next = previous.next;
         previous.next = newNode;
         this.nrOfElements++;
     }


    /**
     * Inserts elements in an ascending order. If the current element is
     * smaller than the given, the method iterates further down the list.
     * If the given element is the biggest, it gets inserted last.
     *
     * @param element is the element being inserted.
     */
  private void insertAscending(int element) {

         if (emptyList()) {
             this.head = new Node(element);
             this.nrOfElements++;
         }
         else {

             Node current = new Node(element);

             int pos = 1;

             while (current != null && current.data < element) {
                 current = current.next;
                 pos++;
             }

             this.insertElement(element, pos);
         }
     }

    /**
     * Returns the current <>Assignment4a</> in form of a <>String</>. All elements
     * the list is included.
     *
     * @return is a <>String</> containing all elements.
     */
    public String toString() {
         StringBuilder sb = new StringBuilder();
         Node n = this.head;

         while (n != null) {
             sb.append(n.data);

             if (n.next != null)
                 sb.append(" ");

             n = n.next;
         }

         return sb.toString();
     }
}
