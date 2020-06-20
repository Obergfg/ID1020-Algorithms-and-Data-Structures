/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190920
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  Lets the user input a text file as well as a string. The code matches the string with the text and outputs the positions
 *  of all occurrences and positions of the string in the text
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Finds the occurrences of a given string in a text.
 */
public class IndexProgram {

    /**
     * Called for when the program initiates. Contains unit testings which tests the functionality of the code.
     *
     * @param args is a set of commands supplied by the command line in form of an array of type <>String</>.
     */
    public static void main(String[] args) {

        IndexProgram indexProgram = new IndexProgram();
        String text = indexProgram.input();

        String word = "November";

        List<Integer> list = indexProgram.wordFinder(text, word);

        indexProgram.output(list, word);


        word = "Dickens";

        list = indexProgram.wordFinder(text, word);

        indexProgram.output(list, word);
    }


    /**
     * Takes input from stdin and converts it to a <>String</>.
     *
     * @return is the string taken from stdin.
     */
    String input() {

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
     * Iterates through a string and find the occurrences of a given word.
     *
     * @param text is teh text being searched
     * @param word is teh word being looked for
     * @return is the index values of the occurrences of the given word.
     */
    List<Integer> wordFinder(String text, String word) {

        List<Integer> indexes = new ArrayList<Integer>();
        String wordProper = " " + word + " ";

        int index = 0;

        while (index != -1) {

            index = text.indexOf(wordProper, index);

            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }

        return indexes;
    }


    /**
     * Outputs the occurrences of a given word in a text.
     *
     * @param list is the indexes of the occurrences of the given word.
     * @param word is the given word
     */
    void output(List<Integer> list, String word) {

        if(list.isEmpty()) {
            System.out.println("No occurrences of the word " + word);
            return;
        }

        int columns = 0;

        System.out.println("The word " + word + " is found " + list.size() + " times at the character indexes: ");

        for (Integer index: list) {

            System.out.print(index + " ");

            columns++;

            if (10 == columns){
                System.out.println();
                columns = 0;
            }
        }
        System.out.println("\n");
    }
}
