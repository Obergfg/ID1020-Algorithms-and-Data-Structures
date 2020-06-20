/**
 * 
 *	Author: Fredrik Öberg
 *
 *  Date of generation: 190901
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *	The code lets the user input 10 characters if the function iterative is called, and it lets 
 *	the function input any amount of characters if the function recursive is called. The characters 
 * 	are then sent to stdout where they are presented in reverse order from which they were 
 *	entered
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions of the Preporatory Lab PM.
 * 
 */

# include <stdio.h>


/**
*	Lets the user input characters and presents them in reversed order. This is done 
*	by using a recursive implementaition where the function is called with putchar as 
*	argument. As long as the input character is not the next line/enter character, 
*	the function calls itself. When the next line character is reached the characters
*	are sent to stdout through putchar. Since they are printed lasly in the function 
*	the characters become printed in the reversed order from the one entered.
*
*	@param c is the character being checked if its end of line or not(Ascii value of 10).
*
*/
void recursive(char c){
	
	if('\n' != c)
		recursive(getchar());
	
	putchar(c);	
}

/**
*	Lets the user input characters and presents them in reversed order. This is done 
*	by using an iterative implementation where a maximum amount of characters is set
*	(in this case 10) and a while loop is letting the user input as many as the user wants
*	up until that number. If more than 10 characters is entered the stdin is also "flushed"
*	from the leftover characters with the intent of mitigating unintended behavior from the program. 
*	The correct input is then sent to stdout in reversed order via a for loop.
*
*/
void iterative(){
	
	char input[10], c;
	int i = 0;
	
	while((c = getchar()) != '\n' && i < sizeof(input)/sizeof(char)){
		input[i]  = c;
		i++;
	}
	
	if(c != '\n' && 10 == i){
		printf("\nMore than 10 characters were entered. The first 10 characters are presented in reversed order: ");
		while(getchar() != '\n'){}		//Flushing the stdin
	}
	else
	    printf("\nThe characters in reversed order: ");
	
	for(i--; i > -1; i--)	
		putchar(input[i]);
	
}

/**
* 	Called by the operating system when the user runs the program. Returns the value 
* 	0 when the program has run properly.
*
*	@return is the return value the main function returns when the program has ended.
*/
int main(){

	printf("\nEnter characters iterative version. They will be presented in reversed order: ");
	
	iterative();
	
	printf("\n\nEnter characters recursive version. They will be presented in reversed order: ");
	
	recursive(getchar());

	return 0;
}