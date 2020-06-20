/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190904
 *
 *  Date of update: --
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code serves as a filter which determines if a file has balanced parenthesis, i.e. if the parentheses in a given
 *  file appears of the same amount and in correct order. This is accomplished by inserting the file into a <>StringBuilder</>
 *  object and then iterating through that object analyzing each and every one of the characters in the <>String</>. If the file
 *  is balanced the <>boolean</> variable "balanced" is set to true, if else it is set to false and the program notifies
 *  the given result to the user.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Fundamentals Lab PM and the course literature "Algorithms".
 */

import java.io.*;
import java.util.Stack;

/**
 * Filters a given file and determines if the parenthesis of the file is balanced or not.
 */
public class Assignment7 {

    /**
     * Contains a method call which initiates the filtering procedure.
     *
     * @param Args contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String Args[]) {

        Assignment7 testRun = new Assignment7();

        testRun.balancedParenthesisFilter();
    }

    /**
     * Takes input from a given file and pushes every {, [, and ( character onto a <>Stack</>
     * and compares if they are properly balanced with a corresponding }, ] or ) parenthesis.
     * The output declare if the file is balanced or not.
     */
 private void balancedParenthesisFilter() {

        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null) {
                sb.append(text);
                sb.append(System.getProperty("line.separator"));
            }

            boolean balanced = balanceChecker(sb.toString());

            System.out.println("File printout:\n");
            System.out.println(sb.toString());

            if(balanced)
                System.out.println("The file is balanced!");
            else
                System.out.println("The file is not balanced!");

            br.close();
        }
        catch(IOException e){
            System.out.println("File not found");
        }
    }

    /**
     * Iterates through a <>String</> to se if the file has balanced parenthesis. There are for different
     * cases the loop checks for and returns the correct value based on those.
     *
     * @param string is the <>String</> being balanced checked.
     * @return is true if the <>String</> is balanced, false if not.
     */
    private boolean balanceChecker(String string) {

        Stack<Character> stack = new Stack<>();
        char c, s;

        for (int i = 0; i < string.length(); i++) {

            c = string.charAt(i);
            s = ' ';

            if(!stack.isEmpty())
                s = stack.peek();

            if (c == '{' || c == '[' || c == '(')
                stack.push(c);
            else if (c == '}' && s == '{' || c == ']' && s == '[' || c == ')' && s == '(')
                stack.pop();
            else if ((c == '}' || c == ']'  || c == ')') && s == ' ')
                 return false;
            else if(c == '}'&& (s == '[' || s == '(' ) || c == ']'&& (s == '{' || s == '(') || c == ')' && (s == '{' || s == '['))
                 return false;
        }

        return stack.isEmpty();
    }
}
