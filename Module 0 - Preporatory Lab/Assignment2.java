/**o
 * 	Author: Fredrik Öberg
 *
 *  Date of generation: 190901
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code takes input from a text file (input.txt) in form of characters, and filters every 'a' character in the file
 *  into an 'x' character and outputs the filtered text to another text file (output.txt). This procedure continues
 *  until the program reaches an EOF character. Then the program is terminated.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Preporatory Lab PM.
 *
 */

import java.io.*;

/**
 * Creates an <>Assignment2</> object which then calls for the method fileEntering.
 */
public class Assignment2{

    public static void main(String Args[]) {

        Assignment2 testRun = new Assignment2();

        testRun.fileEntering();
    }

    /**
     * Takes input from "input.txt" and filters every 'a* character and
     * transforms it into an 'x* character. Then it outputs the filtered version of "input.txt"
     * to "output.txt" and stdout.S
     */
    void fileEntering() {

            try {
                FileReader fr = new FileReader("2input.txt");
                BufferedReader br = new BufferedReader(fr);

                StringBuilder sb = new StringBuilder();
                String text = "";

                while((text = br.readLine()) != null) {
                    sb.append(text);
                    sb.append(System.getProperty("line.separator"));
                }

                for (int i = 0; i < sb.length(); i++)
                    if ((sb.charAt(i) == 'a'))
                        sb.replace(i,i + 1, "x");

                FileWriter fw = new FileWriter("output.txt");
                PrintWriter pw = new PrintWriter(fw);

                pw.println(sb.toString());

                System.out.println(sb.toString());

                br.close();
                pw.close();
            }
            catch(IOException e){
                System.out.println("File not found");
            }
    }
}


