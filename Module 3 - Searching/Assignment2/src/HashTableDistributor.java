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
 * The code takes input from a text file and stores that input into a Hash table. To clarify why the hash table puts the
 * keys in the order it does, two loops iterates through the inserted keys and applies a hash function on each key
 * according to the size of the hash array(11 and 23 in this case). That procedure gives each key its hash value which is
 * the basis for the index which the table puts the key in. The user can by that compare the hash value with the order of
 * the keys in the hash table to get an understanding of why the keys are stored in the order they are.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM.
 *
 *
 */

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Takes input from stdin and puts that input into a <>Hashable</>, prints the content off that table
 * as well as the has value of teh contents so the user can see why they keys are in the order they are.
 *
 */
public class HashTableDistributor {

    /**
     * Inserts input into a hashtable.
     *
     * @param args contains input gotten from the command line in form of an array of type <>String</>.
     */
    public static void main(String[] args) {

        String text = input();
        String[] words = text.trim().split(" +");
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        System.out.println();

        int capacity = addKeys(hashtable, words, 0, 5, 11);
        hashValues(hashtable, capacity);

        capacity = addKeys(hashtable, words, 6, 11, capacity);
        hashValues(hashtable, capacity);

        capacity = addKeys(hashtable, words, 12, 23, capacity);
        hashValues(hashtable, capacity);

        capacity = addKeys(hashtable, words, 24, words.length, capacity);
        hashValues(hashtable, capacity);
    }

    /**
     * Adds keys to the hash table.
     *
     * @param hashtable is the hash table being added keys to.
     * @param words     are the keys of the hash table.
     * @param low       is the low index from which to add keys
     * @param high      is the high index to which from add keys.
     */
    private static int addKeys(Hashtable<String, Integer> hashtable, String[] words, int low, int high, int capacity) {

        for (int i = low; i < high; i++) {
            if (!hashtable.containsKey(words[i]))
                hashtable.put(words[i], 1);
            else
                hashtable.put(words[i], (hashtable.get(words[i]) + 1));

            if (hashtable.size() >= capacity * 0.75)
                capacity = (capacity << 1) + 1;

        }

        System.out.println("Hash Table Capacity: " + capacity);
        System.out.println("Hash Table Size: " + hashtable.size() + "\n" + hashtable );

        return capacity;
    }

    /**
     * Prints out keys and their hash values as well as the values sorted in ascending order.
     *
     * @param hashtable    is the hashtable of choice.
     * @param hashFunction are the modulus operator used on the hashtable.
     */
    private static void hashValues(Hashtable<String, Integer> hashtable, int hashFunction) {

        int[] hashValues = new int[hashtable.size()];
        Enumeration<String> enumeration = hashtable.keys();
        String key;
        int index = 0;

        while (enumeration.hasMoreElements()) {
            key = enumeration.nextElement();
            hashValues[index] = (key.hashCode() & 0x7FFFFFFF) % hashFunction;
            System.out.print(" '" + key + "' Hashcode: " + key.hashCode() + " Hash Value: " + (key.hashCode() & 0x7FFFFFFF) % hashFunction + " ");
            index++;

            if (0 == index % 10)
                System.out.println();

        }

        output(hashValues);
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

    /**
     * Outputs the hash values either to a file or stdout.
     *
     * @param hashValues are the values being sent to stdout.
     */
    private static void output(int[] hashValues) {

        StringBuilder sb = new StringBuilder();
        sb.append('{');

        sb.append(hashValues[0]);

        for (int i = 1; i < hashValues.length; i++) {
            sb.append(", " + hashValues[i]);

            if (0 == i % 100)
                sb.append("\n");
        }
        sb.append('}');

        System.out.println("\n" + sb.toString() + "\n");
    }
}