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
 *  The takes input in form of a string and based on the arguments sent to main via the command line, the code specifies which key was
 *  the most frequently used in the input string as well as the keys between the given indices indexHigh and indexLow. All of this
 *  information gets sent to stdout.
 *
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searchin Lab PM and the book "Algorithms" were the
 *  class FrequencyCounter were used as a base for this extended version..
 *
 *
 */

/**
 * Takes input from user and outputs the most frequent key and it lets the user query the system for
 * the n to n+kth most frequent word and outputs that to.
 */
public class ExtendedFrequencyCounterST {

    /**
     * Takes input and initiates the searching processes as well as the outputs.
     *
     * @param args are the set of commands entered in the command line.
     */
    public static void main(String[] args) {

        int minLength = Integer.parseInt(args[0]);
        int maxWords = Integer.parseInt(args[1]);
        int indexHigh = Integer.parseInt(args[2]);
        int indexLow = Integer.parseInt(args[3]);

        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<String, Integer>(maxWords);

        input(binarySearchST, minLength, maxWords);

        mostFrequent(binarySearchST);

        indexQuery(binarySearchST, indexHigh, indexLow);
    }

    /**
     * Takes input from stdin and puts it into the symbol table.
     *
     * @param binarySearchST is the symbol table of choice.
     * @param minLength is the word min length of interest.
     * @param maxWords is the maximum nr of different keys the Symbol table can take.
     */
    private static void input(BinarySearchST<String, Integer> binarySearchST, int minLength, int maxWords) {

        int wordCount = 0;

        while (!StdIn.isEmpty() && wordCount < maxWords - 1) {

            String word = StdIn.readString();

            if (word.length() < minLength) {
                wordCount++;
                continue;
            }
            if (!binarySearchST.contains(word))
                binarySearchST.put(word, 1);
            else
                binarySearchST.put(word, binarySearchST.get(word) + 1);

            wordCount++;
        }
    }

    /**
     * Outputs the most frequent key of a given symbol table.
     *
     * @param binarySearchST is the symbol table which is given.
     */
    private static void mostFrequent(BinarySearchST<String, Integer> binarySearchST) {

        String max = "";
        binarySearchST.put(max, 0);

        for (String word : binarySearchST.keys())
            if (binarySearchST.get(word) > binarySearchST.get(max))
                max = word;

        StdOut.println("Most frequent word was " + "'" + max + "'" + " with nr of counts: " + binarySearchST.get(max) + "\n");
    }

    /**
     * Outputs the n to n+kth most frequent keys of a symbol table.
     *
     * @param binarySearchST is the given symbol table.
     * @param indexHigh is the index of the most frequent key of interest.
     * @param indexLow is the index of the key which is least frequent.
     */
    private static void indexQuery(BinarySearchST<String, Integer> binarySearchST, int indexHigh, int indexLow) {

        InsertSort sorter = new InsertSort();
        StringBuilder sb = new StringBuilder();

        int keyCount = binarySearchST.size();

        Comparable<Integer>[] values = binarySearchST.getValues();
        Comparable<String>[] keys = binarySearchST.getKeys();
        sorter.insertSort(values,keys, keyCount - 1);

        StdOut.println("The " + indexHigh + " to " + indexLow + " most frequent words are:\n");

        for(int i = indexHigh - 1; i < indexLow; i++)
            sb.append(keys[i] + ", " + values[i] + " times\n");

        StdOut.println(sb);
    }
}
