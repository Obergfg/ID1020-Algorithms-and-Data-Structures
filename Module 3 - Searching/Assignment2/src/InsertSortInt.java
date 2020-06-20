/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190913
 *
 *  Date of update: 190922
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code sorts the elements of a given array in either ascending or descending order using the algorithm "Insert Sort" and prints out the content
 *  of the array that is being sorted after each inner loop iteration of the method insertSort.
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
 * Sorts the elements of a given array in either ascending or descending order using the algorithm "Insert Sort".
 *
 */

public class InsertSortInt {

    /**
     * Contains unit testing in form of different calls to the implemented methods, trying out all from
     * likely input to very unlikely.
     *
     * @param args contains a set of arguments gotten from the command line in the form of a <>String</> array.
     */
    public static void main(String[] args) {

        InsertSortInt sorter = new InsertSortInt();
        System.out.println("Start of process");

        int[] array = new int[]{10,2,3,4,9,6,7,8,1};

        show(array);
        System.out.println();
        sorter.insertSort(array, false);
        System.out.println();
        show(array);

        int[] array2 = new int[]{1,2,4,3,5,0};

        show(array2);
        System.out.println();
        sorter.insertSort(array2, true);
        System.out.println();
        show(array2);

        int[] array3 = new int[]{-10,-2,333,431231,-8789,6,7,0,11};

        show(array3);
        System.out.println();
       sorter.insertSort(array3, true);
        System.out.println();
        show(array3);


        System.out.println("End of process");
    }

    /**
     * Sorts a given array of type <>int</> in either ascending order using the sorting
     * algorithm "Insert Sort".
     *
     * @param elements are the elements being sorted in ascending order.
     */
      void insertSort(int[] elements, boolean descending) {

        if (descending)
            descending(elements);

        for (int i = 1; i < elements.length; i++)
            for (int j = i; (j > 0 && less(elements[j], elements[j - 1])); j--)
                exchange(elements, j, j-1);

        if (descending)
            descending(elements);

    }

    /**
     * Multiplies each element of a given array with -1 to prepare the array for being sorted in
     * descending order.
     *
     * @param elements is the array being manipulated.
     */
    private static void descending(int[] elements) {

        for (int i = 0; i < elements.length; i++)
            elements[i] = elements[i] * -1;

    }


    /**
     * Compares two elements and returns true if the parameter a has a smaller value
     * than the parameter b.
     *
     * @param a is the first given element.
     * @param b is the second given element.
     * @return true if the element a is smaller than the element b. If not, it is false.
     */
    private static boolean less(int a, int b) {
        return a < b;
    }

    /**
     * Exchanges two elements at the given indexes i and j of a given array of elements.
     *
     * @param elements is the array where the elements are being exchanged.
     * @param i is the first index of the exchange.
     * @param j is the second index of the exchange.
     */
    private static void exchange(int[] elements, int i, int j) {

        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Prints out the elements of a given array on a single line.
     *
     * @param elements are the array of elements being printed.
     */
    private static void show(int[] elements) {

        for (int e: elements)
            System.out.print(e + " ");

        System.out.println();
    }

    /**
     * Test whether the elements of a given array is in an ascending order.
     *
     * @param elements is the array being tested.
     * @return id true if the array is sorted in ascending order, false if not.
     */
    public static boolean isSorted(int[] elements) {

        for (int i = 1; i < elements.length; i++)
            if (less(elements[i], elements[i -1]))
                return false;

        return true;
    }
}
