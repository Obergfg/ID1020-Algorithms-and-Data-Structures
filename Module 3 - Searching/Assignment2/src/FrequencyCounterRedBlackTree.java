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
 * Counts the frequency of keys in a given string input. Outputs the key with largest value.
 *
 */
public class FrequencyCounterRedBlackTree {

    /**
     * Takes input and initiates the searching process.
     *
     * @param args are the set of commands entered in the command line.
     */
    public static void main(String[] args) {

        System.out.println("Red Black Binary Search Tree Initiated");

        int minlen = Integer.parseInt(args[0]);
        int maxwords = Integer.parseInt(args[1]);
        int wordcount = 0;

        RedBlackBST<String, Integer> tree = new RedBlackBST<>();

        while (!StdIn.isEmpty() && wordcount <  maxwords) {
            String word = StdIn.readString();
            if (word.length() < minlen) {
                wordcount++;
                continue;
            }
            if (!tree.contains(word))
                tree.put(word, 1);
            else
                tree.put(word, tree.get(word) + 1);

            wordcount++;
        }

        System.out.println("Search in Red Black Tree Process Begins");

        long searchStartTime = System.nanoTime();

        String max = "";
        tree.put(max, 0);
        for (String word : tree.keys())
            if (tree.get(word) > tree.get(max))
                max = word;

        long searchEndTime = System.nanoTime();
        long searchTimeElapsed = searchEndTime - searchStartTime;

        System.out.println("Search in Red Black Tree Process Ends");
        StdOut.println("Most frequent word was " + "'" + max + "'" + " with nr of counts: " + tree.get(max));
        StdOut.println("Search time was " + searchTimeElapsed/1000 + " micro seconds");

    }
}
