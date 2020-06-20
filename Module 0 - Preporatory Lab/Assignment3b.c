/**
 * 
 * 	Author: Fredrik Öberg
 *
 *  Date of generation: 190901
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *	
 *	The code lets the user input an integer which becomes the size of an array. This array is 
 *	filled with the same number of integers which also comes from user input.The elements of 
 *	the array is lastly printed to stdout in the reversed order they were inserted.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Preporatory Lab PM.
 */

# include <stdio.h>
# include <assert.h>
# include <stdlib.h>
# include <ctype.h>
# include <string.h>

/**
* Asks the user for input and the input becomes the number of elements in the <>Array<> dynArr - the return value.
* It wont allow for any other input than integers above 999999999.
*
*/
int getElements(){
	
	char input[64];			
	
	int size = 0, i, letter = 0;
	
	while(size == 0){
		
		fgets(input, 63, stdin);
		
		if(strlen(input) > 9){
			printf("To many characters\n\n");
	
			if(input[strlen(input -1) != '\n']){
				for(int c; (c = getchar()) != EOF && c != '\n';)
					;	
			}
			
			continue;
		}
		
		for(i = 0; i < strlen(input) - 1; i++)		
			if(!isdigit(input[i])){
			letter = 1;
			break;
			}	
				
		if(letter == 1){
			printf("Invalid input: Enter a number\n\n");
			letter = 0;
			continue;
		}
		
		sscanf(input, "%d", &size);
		
		if(size < 1){
			size = 0;
			printf("Enter a number larger than zero\n\n");
			continue;
		}	
	}
	
	return size;	
}

/**
* Asks the user for input and the input becomes the elements in the <>Array<> dynArr - the return value.
* It wont allow for any other input than integers above 999999999.
*
*/
int getIntegers(){
	
	char input[64];		
	
	int integer, letter = 0, validation = 0, i;
	
	while(validation == 0){
		
		fgets(input, 63, stdin);
		
		if(strlen(input) > 9){
			printf("To many characters\n\n");
			
			if(input[strlen(input -1) != '\n']){
				for(int c; (c = getchar()) != EOF && c != '\n';)
					;	
			}
			
			continue;
		}
		
		for(i = 0; i < strlen(input) - 1; i++)		
			if(!isdigit(input[i])){
			letter = 1;
			break;
			}	
				
		if(letter == 1){
			printf("Invalid input: Enter a number\n\n");
			letter = 0;
			continue;
		}
		
		sscanf(input, "%d", &integer);
		validation = 1;		
	}
	
	return integer;	
}


/**
*	Creates an array (dynArr) whos size the user decides. The array is
* 	then filled with a that amount of elements whos value the user decides.
*
*/
void userInput(){
	
	int size, *dynArr, i;
	
	printf("Enter number of elements: ");
	
	size = getElements();
	
	dynArr = (int*)malloc(size*sizeof(int));	
		
	printf("Enter %d elements: ", size);	
	
	for(i = 0; i < size; i++)
		dynArr[i] = getIntegers();	
		
	printf("\nEntered elements in reversed order: \n");
		
	for(i = size - 1; i > -1; i--)
		printf("\n%d", dynArr[i]);
	
	free(dynArr);	
}

/**
*	Reads input from a textfile(input.txt) and based on that input 
*	creates an output file (output.txt) which the output is sent to. 
*/
void inputTextfile(){
	
	int i, *dynArr, nrElements;
	
	FILE *inpFile = fopen("3binput.txt","r");
	FILE *outpFile = fopen("output.txt", "w"); 
	
	fscanf(inpFile, "%d", &nrElements);
	
	if(nrElements < 1){
		printf("A positive integer was not entered");
		exit(0);
	}
		
	dynArr = (int*)malloc(nrElements*sizeof(int));
	
	for(i = 0; i < nrElements; i++)
		fscanf(inpFile, "%d", &dynArr[i]);
			
	for(i = nrElements - 1; i > -1; i--)
		fprintf(outpFile, "%d\r\n", dynArr[i]);
	
	fclose(inpFile);
	fclose(outpFile);
	free(dynArr);
}


/**
*	Initiates the program through different function calls.
*
*/
int main(){
	
	//inputTextfile();
	userInput();

	return 0;
}