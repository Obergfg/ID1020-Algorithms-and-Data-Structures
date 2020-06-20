/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190901
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 * The code reads a positive number from stdin into an int variable named nrElements. The number can be
 * read either as an input from the user or from a text file depending on which method is called upon
 * in the program. The value entered is the number of elements in a dynamically allocated array of integers.
 * With that number an array of integers  with nrElements elements is created. The program then reads nrElements
 * integers from stdin and stores them in the array. Lastly the elements of the array are printed to to
 * stdout in reverse order compared to how they were input.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Preporatory Lab PM.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contains all the code of the program.
 */
public class Assignment3a {

    /**
     * Creates an <>Assignment3a</> object which calls upon the methods userInput and textFileInput.
     */
    public static void main(String[] args) {

        Assignment3a testRun = new Assignment3a();

        testRun.textFileInput();

        testRun.userInput();
    }

    /**
     * Class constructor.
     */
    private Assignment3a(){
    }


    /**
     * Let's the user input elements of integers in an array. The user choose how many elements
     * and what value each element contain. This is done by creating a <>Scanner</> object which
     * reads a positive integer (nrElements) from stdin which then becomes the size of the array.
     * The scanner then reads nrElements integer elements from stdin an presents them in reversed
     * order from which they were entered.
     */
      private void userInput() {

        Assignment3a assignment3a = new Assignment3a();

        System.out.println("Enter a positive number: ");

        int nrElements = assignment3a.getNrOfElementsFromUser();

        int[] elements = new int[nrElements];

        System.out.println("Enter " + nrElements + " integers: ");

        for (int i = 0; i < elements.length; i++)
            elements[i] = getElementsFromUser();

        assignment3a.printElements(elements);
    }

    /**
     * Takes input from a text file(input.txt) and prints the elements entered to stdout.
     */
   private void textFileInput() {

        try {

            Assignment3a assignment3a = new Assignment3a();
            FileReader fr = new FileReader("3ainput.txt");
            BufferedReader br = new BufferedReader(fr);

            try {

              int nrElements = Integer.parseInt(br.readLine());

            int[] elements = new int[nrElements];

            for (int i = 0; i < elements.length; i++)
                elements[i] = Integer.parseInt(br.readLine());

            assignment3a.printElements(elements);

            }catch (NumberFormatException e){
                System.out.println("Input was not an int");
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Takes a user input in form of an <>int</> and returns that value. If a positive integer is not
     * entered, the program notifies the user and asks for a new input.
     *
     * @return is the <>int</> value that is read from stdin.
     */
   private int getNrOfElementsFromUser() {

        Scanner userInput = new Scanner(System.in);

        while (true) {

            String input = userInput.nextLine();
            try {

                int n = Integer.parseInt(input);

                if (n > 0)
                    return n;

            } catch (NumberFormatException e) {

            }
            System.out.println("Input mismatch: Must be a positive int");
        }
    }

    /**
     * Takes a user input in form of an <>int</> and returns that value. If a value which an
     * <>int</> can't hold, or an integer is not entered, the program asks for a new input.
     *
     * @return is the integer value the user has entered to stdin.
     */
    private int getElementsFromUser(){

        Scanner userInput = new Scanner(System.in);

        while (true) {

            String input = userInput.nextLine();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

            }
            System.out.println("Input mismatch: Must be an int");
        }
    }

    /**
     * Prints out an array of type <>int</> in reverse order compared to how they were input. That means that the
     * integer on the highest element position is printed first, the element on position zero is printed last.
     *
     * @param elements is the array that is going to be printed.
     */
   private void printElements(int[] elements) {

        System.out.println("\nThe entered elements:\n");

        for (int i = elements.length -1; i > -1; i--)
            System.out.println(elements[i]);
    }

}
