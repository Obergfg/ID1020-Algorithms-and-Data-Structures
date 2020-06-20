/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190902
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code calls for the two methods: iterative and recursive, They both let the user
 *  input characters and presents them in reversed order. The iterative method accomplishes
 *  this by using an iterative method where a while loop runs through all the input characters
 *  until an end of line character has been reached. The recursive iterates through all input
 *  and calls for itself until an end of line character has been sent as an argument. Then the method
 *  iterates back through all the input characters and prints them.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Fundamentals Lab PM and the course literature
 *  "Algorithms".
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *  Contains all code of the program.
 *
 */
public class Assignment2 {

    /**
     * Contains all the method calls and the creation of objects being used in the program.
     *
     * @param args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {

        Assignment2 testRun = new Assignment2();
        InputStreamReader reader = new InputStreamReader(System.in);

        testRun.iterative();

        System.out.println("Enter characters: ");

        try {
            testRun.recursive(reader.read(), reader);
        } catch (IOException e) {
            System.out.println("\nInput error");
        }
    }

    /**
     * Class constructor.
     */
    Assignment2(){
    }

    /**
     * 	Lets the user input characters and presents them in reversed order. This is done
     * 	by using an iterative implementation where a while loop is letting the user input as many
     * 	characters as the user wants and pushes them onto a LIFO stack. When all the input has been added to
     * 	the stack the method iterates back on the stack and prints the elements one by one, which makes them
     * 	being printed in reversed order.
     *
     */
    private void iterative() {

        Stack input = new Stack();
        InputStreamReader reader = new InputStreamReader(System.in);

        int c;

        System.out.println("Enter characters: ");

        try {
           while((c = reader.read()) != '\n')
               input.push(c);

            System.out.println("\nThe entered characters in reversed order: ");

           while(!input.empty()) {
               c = (Integer)input.pop();
               System.out.print((char) c);
           }

            System.out.println('\n');

        }
        catch (IOException e){
            System.out.println("\nInput error");
        }
    }

    /**
     * Takes input from user and prints the input in reversed order. This is done by checking if the parameter
     * <>int</> c has an Ascii value of 10(an end of line character), which signals that all input has been
     * iterated through. If not, then the method calls itself with the parameter reader as an argument to the next
     * iteration, as well as the next in line of the user input.
     *
     * @param c is the the current character of the input in form of Ascii value.
     * @param reader is the object which handles the input from the user.
     */
   private void recursive(int c, InputStreamReader reader) {

       try {
           if(10 != c)
               this.recursive(reader.read(), reader);
            else
               System.out.print("\nThe entered characters in reversed order:");

               System.out.print((char) c);
       }
       catch (IOException e) {
           System.out.println("\nInput error");
       }
    }
}
