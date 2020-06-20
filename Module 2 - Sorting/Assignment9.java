/**
 *
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190915
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  The code randomly generates an array of N number of integers and calls for the method  quickSort which uses that
 *  sorting algorithm to sort the array in an ascending order. The code also calculates the execution
 *  time of the sorting process and prints it to stdout when the array has been sorted. The quick sort algorithms
 *  performance is improved by calling insertionSort when it wants to sort tiny sub arrays. The reason is that
 *  Insertion Sort is a faster algorithm than Quick Sort for those type of arrays. The cut off for how large sub
 *  arrays to sort can be manipulated by changing the value of the conditional in quickSort which calls for insertionSort.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Sorting Lab PM. The implementation to the algorithms of
 *  Quick sort and Insertion Srt has been taken from the book "Algorithms", where algorithms 2.2 and 2.5 has been used.
 */

import java.util.Random;

/**
 * Creates randomly generated arrays and sorts them with either of the algorithms Merge Sort or Quick Sort.
 *
 */
public class Assignment9 {

    /**
     * Creates arrays with randomly generated integers as elements. Calls for either Quick Sort or
     * Merge Sort methods to sort them in ascending order.
     *
     * @param args contains command-line arguments as an array of type <>String</>
     */
    public static void main(String[] args) {

        Random random = new Random();
        int[] quickArray = new int[100000000];

        for (int i = 0; i < quickArray.length; i++)
            quickArray[i] = random.nextInt();

        System.out.println("Quick Sorting Process Begins");

        long quickStartTime = System.nanoTime();

        quickSort(quickArray,0,quickArray.length -1);

        long quickEndTime = System.nanoTime();

        long quickTimeElapsed = quickEndTime - quickStartTime;

        System.out.println("Quick Sorting Process Ends");
        System.out.println("Quick array is sorted: " + isSorted(quickArray));
        System.out.println("Execution time in milliseconds for Quick Sorting " + quickArray.length + " elements: " + quickTimeElapsed/1000000 + " millie seconds");

        quickArray = null;




    }

    /**
     * Sorts an array of integer using the algorithm Quick Sort and is assisted by the method insertSort
     * with a cut of value given in the conditional.
     *
     * @param array is the array being sorted.
     * @param low is the lowest index of the array.
     * @param high is the highest index of the array.
     */
    private static void quickSort(int[] array, int low, int high) {


        if ( high <= low + 30) {
            insertionSort(array, low , high);
            return;
        }

        int j = partition(array, low, high);
        quickSort(array, low, j-1);
        quickSort(array, j + 1, high);
    }

    /**
     * Partitions on the item v in array. The main loop exits when the scan indices i and j cross. After that an
     * exchange of elements at indices low and j are made.
     *
     * @param array is the array being sorted.
     * @param low is the low end of the part of the array being sorted.
     * @param high is the high end of the part of the array being sorted
     * @return is the index value where the array has been partitioned.
     */
    private static int partition(int[] array, int low, int high) {

        int i = low;
        int j = high + 1;
        int v = array[low];

        while (true) {

            while (less(array[++i], v))
                if (i == high)
                    break;

            while (less(v,array[--j]))
                if (j == low)
                    break;

            if (i >= j)
                break;

            exchange(array, i , j);
        }
        exchange(array, low , j);

        return j;
    }

    /**
     * Sorts a part of a given array of type <>int</> in ascending order using the sorting
     * algorithm "Insert Sort". Is called by quickSort.
     *
     * @param array are the elements being sorted in descending order.
     * @param high is the high end of the part being sorted.
     * @param low is the low end of the part being sorted.
     */
    private static void insertionSort(int[] array, int low, int high) {

        for (int i = low; i <= high; i++) {
            for (int j = i; (j > 0 && less(array[j], array[j - 1])); j--)
                exchange(array, j, j-1);

        }
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
     * @param array is the array where the elements are being exchanged.
     * @param i is the first index of the exchange.
     * @param j is the second index of the exchange.
     */
    private static void exchange(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Prints out the elements of a given array on a single line.
     *
     * @param array are the array of elements being printed.
     */
    private static void show(int[] array) {

        int i = 0;

        for (int e: array) {
            System.out.print(e + " ");
            i++;

            if (10 == i){
                System.out.print("\n");
                i = 0;
            }
        }
        System.out.println();
    }

    /**
     * Test whether the elements of a given array is in an ascending order.
     *
     * @param array is the array being tested.
     * @return id true if the array is sorted in ascending order, false if not.
     */
    private static boolean isSorted(int[] array) {

        for (int i = 1; i < array.length; i++)
            if (less(array[i], array[i -1]))
                return false;

        return true;
    }
}
