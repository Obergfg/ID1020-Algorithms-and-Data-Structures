/**
 * 
 *  Author: Fredrik Öberg
 *
 *  Date of generation: 190914
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *  This code takes an array of integers (both positive and negative) and orders the elements in the array so that all 
 *  negative elements come before the positive. This is accomplished by iterating through the given array via a for loop. 
 *  If the element at index i is negative it swaps itself with the element at index j. If element i is not negative 
 *  only i gets incremented. This process proceeds until all the elements of the array has been compared and all negative 
 *  elements are before the positive elements in the array.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Sorting Lab PM.
 * 
 *
 *
 *	Assignment 6) 
 *	
 *	The loop invariant is a property of a program loop that is true before and after each iteration of the loop as well as before and
 * 	after the loop initiates. 
 *
 * 	In this case the loop is supposed to put all negative numbers so the come before the positive. In this case the loop invariant is based upon 
 * 	the variable j. When the loop initiates no positive integer or only negative integers are supposed to be located at an index lower than 
 * 	the value of j, which is 0. That statement is true because there can be no elements at an index lower than 0 since it does not exist. This makes
 * 	it so the statement is true for when the first iteration of the loop starts. The loop then checks to see if the element at index 0 is negative. 
 * 	If it is j is incremented to 1, if not j stays at 0. I is iterated by one and the loop invariant is still true because the integer was either 
 * 	positive or negative and based on that the value of j makes the invariant true in either of the cases. If it was negative only negative integers 
 * 	are below index 1, if not there hasn’t been any negative integers found in the loop so the invariant is true. This process keeps on going until
 * 	all elements of the array has been iterated through and the algorithm of the loop keeps the invariant true through all of that process.
 */

#include <stdlib.h>
#include <stdio.h>

/**
*	Rearranges an array so that all negative elements comes before the positive ones. Zero in this case counts as positive.
*
*	@param elements is the array having its elements rearranged.
*/
void arrayRearranger(int *elements, int length){
	
	if(1 > length){
		printf("Empty Array");
		return;
	}
	
	int i,j = 0, temp;
	
	for(i = 0; i < length; i++)
		if(elements[i] < 0){
			if(i != j){
				temp = elements[j];
				elements[j] = elements[i];
				elements[i] = temp;
			}
			j++;
		}
}

/**
*	Initiates the process by function calls that gets arrays of integers as argument.
*
*/
int main(){
	
	int array[] = {5,4,3,-2,1,0,-1,-2,7,8};
	int i;
	
	
	arrayRearranger(array, sizeof(array)/sizeof(int));
	
	for(i = 0; i < sizeof(array)/sizeof(int); i++)
		printf("%d " , array[i]);
	
	printf("\n");
	
	int array2[] = {-5,-4,-3,-2,-1};
	
	
	arrayRearranger(array2, sizeof(array2)/sizeof(int));
	
	for(i = 0; i < sizeof(array2)/sizeof(int); i++)
		printf("%d " , array2[i]);
	
	printf("\n");
	
	int array3[] = {1,4,3,2,0,10};
	
	
	arrayRearranger(array3, sizeof(array3)/sizeof(int));
	
	for(i = 0; i < sizeof(array3)/sizeof(int); i++)
		printf("%d " , array3[i]);
	
	printf("\n");
	
	return 0;
}