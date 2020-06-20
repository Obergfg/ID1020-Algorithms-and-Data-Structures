/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190913
 *
 *  Date of update: 190919
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code sorts the elements of a given arrays in descending order using the algorithm "Insert Sort" and prints out the content
 *  of the array that is being sorted after each inner loop iteration of the method insertSort. It also sorts a Symbol Tree by its Values
 *  in descending order.
 *  The algorithm works by iterating through the array. At each iteration "Insertion Sort" compares the element at the current index
 *  with the elements before it and finds the location it belongs to within the previous part of the list which has been sorted
 *  and inserts it there. It repeats this process until all elements ha been iterated through and pout on its correct place.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Sorting Lab PM and the course literature "Algorithms" where
 *  the Algorithm 2.2 where used as a base for the "Insert Sort" implementation used in this code.
 */


/**
 * Sorts arrays in descending order using the algorithm Insert Sort.
 *
 */
public class InsertSort {


    /**
     * Contains unit testing in form of different calls to the implemented methods, trying out all from
     * likely input to very unlikely.
     *
     * @param args contains a set of arguments gotten from the command line in the form of a <>String</> array.
     */
    public static void main(String[] args) {

        System.out.println("Start of process");

        Integer[] array = new Integer[]{10,2,3,4,9,6,7,8,1};

        show(array);
        System.out.println();
        insertSort(array);
        System.out.println();

        Integer[] array2 = new Integer[]{1,2,4,3,5,0};

        show(array2);
        System.out.println();
        insertSort(array2);
        System.out.println();

        Integer[] array3 = new Integer[]{-10,-2,333,431231,-8789,6,7,0,11};

        show(array3);
        System.out.println();
        insertSort(array3);
        System.out.println();

        System.out.println("End of process");
    }


    /**
     * Sorts a given array of type <>Comparable</> in descending order using the sorting
     * algorithm "Insert Sort".
     *
     * @param elements are the elements being sorted in descending order.
     */
    private static void insertSort(Comparable[] elements) {

        for (int i = 1; i < elements.length; i++) {
            for (int j = i; (j > 0 && greater(elements[j], elements[j - 1])); j--) {
                exchange(elements, j, j-1);
                show(elements);
            }
        }
    }

    /**
     * Sorts two given arrays of type <>Comparable</> in descending order using the sorting
     * algorithm "Insert Sort".
     *
     * @param values are the elements being sorted in descending order.
     */
     void insertSort(Comparable[] values, Comparable[] keys, int keyCount) {

        for (int i = 1; i <= keyCount; i++) {
            for (int j = i; (j > 0 && greater(values[j], values[j - 1])); j--) {
                exchange(values, j, j-1);
                exchange(keys,j , j-1);
            }
        }
    }

    /**
     * Compares two elements and returns true if the parameter a has a greater key value
     * than the parameter b.
     *
     * @param a is the first given element.
     * @param b is the second given element.
     * @return true if the element a is greater than the element b. If not, it is false.
     */
    private static boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
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
    private static void show(Comparable[] elements, int keyCount) {

        for (int i = 0; i <= keyCount; i++)
            System.out.print(elements[i] + " ");

        System.out.println();
    }

    /**
     * Prints out the elements of a given array on a single line.
     *
     * @param elements are the array of elements being printed.
     */
    private static void show(Comparable[] elements) {

        for (int i = 0; i < elements.length; i++)
            System.out.print(elements[i] + " ");

        System.out.println();
    }


    /**
     * Test whether the elements of a given array is in an descending order.
     *
     * @param elements is the array being tested.
     * @return id true if the array is sorted in descending order, false if not.
     */
    public static boolean isSorted(Comparable[] elements) {

        for (int i = 1; i < elements.length; i++)
            if (greater(elements[i], elements[i -1]))
                return false;

        return true;
    }
}
