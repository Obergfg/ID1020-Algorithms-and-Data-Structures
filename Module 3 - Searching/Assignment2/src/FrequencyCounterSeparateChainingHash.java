/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190921
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
 *  The code has been based upon the instructions in the Searching Lab PM and the book "Algorithms" were the symbol
 *  table client FrequencyCounter were used as a basis for this code.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Counts the frequency of keys in a hash table and outputs the key with highest value i.e. the most
 * frequent key.
 */
public class FrequencyCounterSeparateChainingHash {

    /**
     * Takes input and initiates the searching process.
     *
     * @param args are the set of commands entered in the command line.
     */
    public static void main(String[] args) {

        int maxWords = 200000;

        SeparateChainingHashST<String, Integer> hashTable = new SeparateChainingHashST<>();

        String text = input();

        String[] words = text.trim().split(" +");



        for (int i = 0; i < maxWords && i < words.length; i++)
            if (null == hashTable.get(words[i]))
                hashTable.put(words[i], 1);
            else
                hashTable.put(words[i], (hashTable.get(words[i]) + 1));




        System.out.println("Search in Hash Table Process Begins");

        long searchStartTime = System.nanoTime();

        String max = "";
        hashTable.put(max, 0);
        for (String word : hashTable.keys())
            if (hashTable.get(word) > hashTable.get(max))
                max = word;

        long searchEndTime = System.nanoTime();
        long searchTimeElapsed = searchEndTime - searchStartTime;

        System.out.println("Search in Hash Table Process Ends");
        StdOut.println("Most frequent word was " + "'" + max + "'" + " with nr of counts: " + hashTable.get(max));
        StdOut.println("Search time was " + searchTimeElapsed/1000 + " micro seconds");

    }

    /**
     * Takes input from stdin and converts it to a <>String</>.
     *
     * @return is the string taken from stdin.
     */
    private static String input() {

        try {

            FileReader reader = new FileReader("the text.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null)
                builder.append(line);

            return builder.toString();

        } catch (IOException e) {

            System.out.println("Input Failure");
            return null;
        }
    }

}
