/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190913
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code sorts the elements of a given array in ascending order using the algorithm "Insert Sort", and counts the number of
 *  times an exchange or "swap" is being made.
 *  The algorithm works by iterating through the array. At each iteration "Insertion Sort" compares the element at the current index
 *  with the elements before it and finds the location it belongs to within the previous part of the list which has been sorted
 *  and inserts it there. It repeats this process until all elements ha been iterated through and pout on its correct place.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Sorting Lab PM and the course literature "Algorithms" where
 *  the Algorithm 2.2 where used as a base for the "Insert Sort" implementation used in this code.
 *
 */


/**
 * Sorts the elements of a given array in ascending order using the algorithm "Insert Sort".
 *
 */

import java.util.Scanner;

/**
 * Sorts the elements of a given array in ascending order using the algorithm "Insert Sort".
 *
 */
public class Assignment1 {

    /**
     * Contains unit testing in form of different calls to the implemented methods, trying out all from
     * likely input to very unlikely.
     *
     * @param args contains a set of arguments gotten from the command line in the form of a <>String</> array.
     */
    public static void main(String[] args) {

        System.out.println("Start of process");

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter size of input: ");
        int size = scanner.nextInt();

        System.out.println("Enter " + size + " integer elements: ");

        Integer[] input = new Integer[size];

        for (int i = 0; i < size; i++ )
            input[i] = scanner.nextInt();


        System.out.println("Entered elements sorted in ascending order: ");
        insertSort(input);
        System.out.println();



        Integer[] array1 = new Integer[]{4,3,2,1,-11,1000,333,23,0,-32354,98,3,980};
        Integer[] array2 = new Integer[]{10,9,8,7,6, 5, 4, 3, 2, 1,0};
        Integer[] array3 = new Integer[]{1,2,3,4,9,6,7,8,10};
        Integer[] array4 = new Integer[]{1,2,4,3,5,0};


        show(array1);
        System.out.println();
        insertSort(array1);
        System.out.println();

        show(array2);
        System.out.println();
        insertSort(array2);
        System.out.println();

        show(array3);
        System.out.println();
        insertSort(array3);
        System.out.println();

        show(array4);
        System.out.println();
        insertSort(array4);

        System.out.println("End of process");
    }

    /**
     * Sorts a given array of type <>int</> in ascending order using the sorting
     * algorithm "Insert Sort".
     *
     * @param elements are the elements being sorted in ascending order.
     */
    private static void insertSort(Comparable[] elements) {

        for (int i = 1; i < elements.length; i++) {
            for (int j = i; (j > 0 && less(elements[j], elements[j - 1])); j--) {
                exchange(elements, j, j-1);
                show(elements);
            }
        }
    }

    /**
     * Compares two elements and returns true if the parameter a has a smaller key value
     * than the parameter b.
     *
     * @param a is the first given element.
     * @param b is the second given element.
     * @return true if the element x is smaller than the element y. If not, it is false.
     */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Exchanges two elements at the given indexes i and j of a given array of elements.
     *
     * @param elements is the array where the elements are being exchanged.
     * @param i is the first index of the exchange.
     * @param j is the second index of the exchange.
     */
    private static void exchange(Comparable[] elements, int i, int j) {

        Comparable temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Prints out the elements of a given array on a single line.
     *
     * @param elements are the array of elements being printed.
     */
    private static void show(Comparable[] elements) {

        for (Comparable e: elements)
            System.out.print(e + " ");

        System.out.println();
    }

    /**
     * Test whether the elements of a given array is in an ascending order.
     *
     * @param elements is the array being tested.
     * @return id true if the array is sorted in ascending order, false if not.
     */
    public static boolean isSorted(Comparable[] elements) {

        for (int i = 1; i < elements.length; i++)
            if (less(elements[i], elements[i -1]))
                return false;

        return true;
    }
}
