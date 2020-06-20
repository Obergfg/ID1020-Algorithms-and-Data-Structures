/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190919
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code counts the number of individual words from a given string input and outputs the word which is most
 *  frequent - key with the highest value.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the symbol table
 *  FrequencyCounter were used as a basis for this code.
 */


/**
 * Counts the frequency of keys in a Binary Tree.
 */
public class FrequencyCounterBinaryTree {


    /**
     * Takes input and initiates the searching process.
     *
     * @param args are the set of commands entered in the command line.
     */
    public static void main(String[] args) {
      System.out.println("Binary Search Tree Initiated");

        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        int wordcount = 0;
        int maxwords = Integer.parseInt(args[1]);

        BinarySearchTree<String, Integer> binarySearchTree = new BinarySearchTree<String, Integer>();


        while (!StdIn.isEmpty() && wordcount <  maxwords)
        { // Build symbol table and count frequencies.
            String word = StdIn.readString();
            if (word.length() < minlen) {
                wordcount++;
                continue; // Ignore short keys.
            }
            if (!binarySearchTree.contains(word))
                binarySearchTree.put(word, 1);
            else
                binarySearchTree.put(word, binarySearchTree.get(word) + 1);

            wordcount++;
        }

        System.out.println("Search in Binary Tree Process Begins");

        long searchStartTime = System.nanoTime();

        // Find a key with the highest frequency count.
        String max = "";
        binarySearchTree.put(max, 0);
        for (String word : binarySearchTree.keys())
            if (binarySearchTree.get(word) > binarySearchTree.get(max))
                max = word;

        long searchEndTime = System.nanoTime();
        long searchTimeElapsed = searchEndTime - searchStartTime;

        System.out.println("Search in Binary Tree Process  Ends");
        StdOut.println("Most frequent word was " + "'" + max + "'" + " with nr of counts: " + binarySearchTree.get(max));
        StdOut.println("Search time was " + searchTimeElapsed/1000 + " micro seconds");

    }
}
