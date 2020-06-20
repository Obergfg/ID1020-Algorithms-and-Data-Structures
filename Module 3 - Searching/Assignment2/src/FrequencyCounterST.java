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
 * Counts the frequency of keys in a symbol table.
 */
public class FrequencyCounterST {

    /**
     * Takes input and initiates the searching process.
     *
     * @param args are the set of commands entered in the command line.
     */
    public static void main(String[] args) {
        System.out.println("Binary Search Initiated");

            int minlen = Integer.parseInt(args[0]);
            int wordcount = 0;
            int maxwords = Integer.parseInt(args[1]);

            BinarySearchST<String, Integer> binarySearchSt = new BinarySearchST<String, Integer>(maxwords);

            while (!StdIn.isEmpty() && wordcount < maxwords) {

                String word = StdIn.readString();

                if (word.length() < minlen) {
                    wordcount++;
                    continue;
                }
                if (!binarySearchSt.contains(word))
                    binarySearchSt.put(word, 1);
                else
                    binarySearchSt.put(word, binarySearchSt.get(word) + 1);


                wordcount++;
            }

        System.out.println("Binary Search in Symbol Table Process Begins");

        long searchStartTime = System.nanoTime();

            String max = "";
            binarySearchSt.put(max, 0);
            for (String word : binarySearchSt.keys())
                if (binarySearchSt.get(word) > binarySearchSt.get(max))
                    max = word;

        long searchEndTime = System.nanoTime();
        long searchTimeElapsed = searchEndTime - searchStartTime;

        System.out.println("Binary Search in Symbol Table Process Ends");
        StdOut.println("Most frequent word was " + "'" + max + "'" + " with nr of counts: " + binarySearchSt.get(max));
        StdOut.println("Search time was " + searchTimeElapsed/1000 + " micro seconds");

    }
}

