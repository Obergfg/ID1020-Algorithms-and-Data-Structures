/**
 * Author: Fredrik Öberg
 
 * Date of code generation: 190901
 
 * Date of update:
 *
 * Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 * The code constructs a data structure in form of a binary tree. It contains of of nodes
 * each of which contains a key in form of a integer and to nodes(left and right) which is used
 * to connect each node to two other nodes down the binary tree. If a given node has a
 * key which is equal or higher than the current node (the root) them that node is refrenced via
 * the right node. If the key is lower than the roots key, the it is referenced via the left
 * node of the root.
 * To execute the code an array of type <>int</> is sent through a <>Assignment5</> object which
 * instantiates the object. The tree can then be printed out in three different ways by calling
 * the methods printPrefixTraversal, printInfixTraversal or printPostFixTraversal. A specific key
 * ina a <>Assignment5</> can also be searched for via the method hasElement by sending a root <>Node</>
 * and the key being searched for in form of a <>int</>.
 *
 *
 * Code based upon:
 * The code has been based upon the instructions of the Preporatory Lab PM.
 *
 *
 * Assignment 5.1:
 *
 * Prefix output - 12,8,5,2,10,22,37
 * Infix output - 2,5,8,10,12,22,37
 * Postfix output - 2,5,10,8,37,22,12
 *
 *
 * Assignment 5.2:
 *
 * Unordered - {3,5,1,2,4} ->   3
 *                            1   5
 *                           n 2 4 n
 *
 * Ordered Ascending - {1,2,3,4,5} ->   1
 *                                     n  2
 *                                       n  3
 *                                         n  4
 *                                           n  5
 *                                             n  n
 *
 * Ordered Descending - {5,4,3,2,1} ->   5
 *                                     4   n
 *                                   3  n
 *                                 2  n
 *                               1  n
 *                             n  n
 *
 */




/**
 * Represents a binary search tree where the keys are integer values and each element is connected
 * to other elements of the tree via two nodes - left and right - and  a "descendant" nod which leads back
 * to the first node of the tree - the "root".  The ordering of the
 * nodes in <>Assignment5</> is that all nodes in the left sub-tree of the root has keys which are smaller
 * than the key in the root, while all elements in the right sub-tree of the root has elements
 * with keys larger or equal to the key in the root.
 *
 */
class Assignment5 {

    /**
     * Handles all the inputs and outputs which in this case is tests for each method in the class.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        int[] keys = new int[]{12,22,37,8,10,5,2};
        int[] keys2 = new int[]{12, 8, 10, 5, 2, 22, 37};

        Assignment5 tree = new Assignment5(keys);
        Assignment5 tree2 = new Assignment5(keys2);

        testPrefixTraversal(tree);
        testInfixTraversal(tree);
        testPostfixTraversal(tree);

        System.out.println();

        testPrefixTraversal(tree2);
        testInfixTraversal(tree2);
        testPostfixTraversal(tree2);

        System.out.println();

        testHasElement(tree, 8);
        testHasElement(tree, 37);
        testHasElement(tree, -2);

        System.out.println();

        int[] keys3 = new int[]{-11, -100, 323542, 32, 1, 0};

        Assignment5 tree3 = new Assignment5(keys3);

        testPrefixTraversal(tree3);
        testInfixTraversal(tree3);
        testPostfixTraversal(tree3);

        System.out.println();

        testHasElement(tree3, 8);
        testHasElement(tree3, 0);
        testHasElement(tree3, -100);
    }

    /**
     * Contains a key and a right and a left node connecting it to other nodes of
     * <>Assignment5</>
     */
    private class Node {

        private int key;
        private Node left;
        private Node right;

        /**
         * Instantiates an object where the left and right nodes
         * are set to null.
         *
         * @param key is the integer value each node of <>Assignment5</> is given.
         */
      private Node(int key) {

            this.key = key;
            this.left = null;
            this.right = null;

        }
    }

    private Node root;      //The first node of <>Assignment5</>

    /**
     * Instantiates an object where each new <>Node</> is created and given a value from
     * the parameter.
     *
     * @param keys contains all values given to each new elements. The amount of new nodes
     *             created is the same as the length of the array.
     */
   private Assignment5(int[] keys) throws java.lang.IllegalArgumentException{

        if (keys.length == 0)
            throw new java.lang.IllegalArgumentException("Empty Array");

        this.root = new Node(keys[0]);

        for (int i = 1; i < keys.length; i++ )
            insertNode(this.root, new Node(keys[i]));
    }

    /**
     * Inserts new nodes to <>Assignment5</> according to a recursive function. If
     * the new nodes key value is less than the root node and roots left node eaquals to null
     * then the new node is placed in the lefte node of the root. Else the function i called upon again with the
     * left node of the root as argument together with the new node.
     * If the key value of the new node is bigger or equal than the root node and the right node of
     * the root is null, then the new node is put in to the right. If else the this function is called upon
     * and right node of the root is sent as argument together with the new node.
     *
     * @param root is the descendant <>Node</> of the new node.
     * @param newNode is placed in either the left or right node of the
     *                "root" node according to its key value
     */
    private void insertNode(Node root, Node newNode) {

        if (newNode.key < root.key) {
            if (root.left == null)
                root.left = newNode;
            else
                insertNode(root.left, newNode);
        }
        else {
            if (root.right == null)
                root.right = newNode;
            else
                insertNode(root.right, newNode);
        }
    }

    /**
     * Iterates through <>Assignment5</> in search of a specific key. If the root parameter
     * has the same integer value as the parameter key, the key is found and the method returns true.
     * If the key parameter is lower than the roots key value, and the left node exists(it is not null)
     * the the method calls for itself and keep searching down the binary tree.
     * If the key parameter is higher than the roots key value and the right root nod is not null, then
     * the method calls for itself and iterates down the right side of the binary tree.
     *
     * @param root is the preceding element of the binary tree.
     * @param key is the key that is being searched for.
     * @return is <>true</> when the searched for key is found.
     */
   private boolean hasElement(Node root, int key) {

        if (root.key == key)
            return true;
        else if (key < root.key) {

            if(root.left == null)
                return false;
            else
               return hasElement(root.left, key);
        }else{
            if (root.right == null)
                return false;
            else
               return hasElement(root.right, key);
        }
    }

    /**
     * Prints all the elements of <>Assignment5</> in a prefix traversal way, which means that
     * the keys of the current node(the parameter root) are appended to the <>StringBuilder</>
     * object before the algorithm move down the binary tree.
     *
     * @param root is the preceding <>Node</> of the binary tree.
     * @return contains all elements of <>Assignment5</> in form of a <>String</>.
     */
   private String printPrefixTraversal(Node root) {

        StringBuilder sb = new StringBuilder();

        if (root != null){

            sb.append(root.key + " ");
            sb.append(printPrefixTraversal(root.left));
            sb.append(printPrefixTraversal(root.right));
        }

        return sb.toString();
    }

    /**
     * Prints all the elements of <>Assignment5</> in a infix traversal way, which means that
     * the keys of the current node(the parameter root) are appended to the <>StringBuilder</>
     * object after the algorithm move down the left node of the tree, but before traversing
     * down the right side of the tree.
     *
     * @param root is the preceding <>Node</> of the binary tree.
     * @return contains all elements of <>Assignment5</> in form of a <>String</>.
     */

  private String printInfixTraversal(Node root) {

        StringBuilder sb = new StringBuilder();

        if (root != null){

            sb.append(printInfixTraversal(root.left));
            sb.append(root.key + " ");
            sb.append(printInfixTraversal(root.right));
        }

        return sb.toString();
    }

    /**
     * Prints all the elements of <>Assignment5</> in a postfix traversal way, which means that
     * the keys of the current node(the parameter root) are appended to the <>StringBuilder</>
     * object after the algorithm has move down the left and right binary trees of the root node.
     *
     * @param root is the preceding <>Node</> of the binary tree.
     * @return contains all elements of <>Assignment5</> in form of a <>String</>.
     */
   private String printPostfixTraversal(Node root) {

        StringBuilder sb = new StringBuilder();

        if (root != null){

            sb.append(printPostfixTraversal(root.left));
            sb.append(printPostfixTraversal(root.right));
            sb.append(root.key + " ");
        }

        return sb.toString();
    }


    /**
     * Return the root attribute of <>Assignment5</> when called upon.
     *
     * @return is the attribute root of <>Assignment5</> which is
     * in the form of a <>Node</> object.
     */
   private Node getRoot() {
        return root;
    }





    //The following methods are test methods implemented with the intention of reusing
    // code when testing. Normally unit test classes would be made for each class in a
    // program but this method was used in this assignment since proper unit tests were not being asked for.

    /**
     * Tests the prefixTraversal method in <>Assignment5</>.
     *
     * @param tree is the <>Assignment5</> that is being tested
     */
  private static void testPrefixTraversal(Assignment5 tree) {

        System.out.println("PrefixTraversal printout: " + tree.printPrefixTraversal(tree.getRoot()));
    }

    /**
     * Tests the infixTraversal method in <>Assignment5</>.
     *
     * @param tree is the <>Assignment5</> that is being tested
     */
   private static void testInfixTraversal(Assignment5 tree) {

        System.out.println("InfixTraversal printout: " + tree.printInfixTraversal(tree.getRoot()));
    }

    /**
     * Tests the postTraversal method in <>Assignment5</>.
     *
     * @param tree is the <>Assignment5</> that is being tested
     */
   private static void testPostfixTraversal(Assignment5 tree) {

        System.out.println("PostfixTraversal printout: " + tree.printPostfixTraversal(tree.getRoot()));
    }

    /**
     * Test the method hasElement which searches for a given key in integer
     * form and returns true if the <>Assignment5</> has that
     * specific key. If not, it returns false.
     *
     * @param tree is the <>Assignment5</> being searched
     * @param key is the integer value being searched for.
     */
   private static void testHasElement(Assignment5 tree, int key) {

        System.out.println("HasElement search for key nr " + key + " in binary tree "
                + tree.printPrefixTraversal(tree.getRoot()) + " is: " + tree.hasElement(tree.getRoot(), key));

    }
}

