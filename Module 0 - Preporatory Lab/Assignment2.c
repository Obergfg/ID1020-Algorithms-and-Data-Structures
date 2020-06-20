/**
*Author: Fredrik Öberg
*
*Date of generation: 190901
*
*Date of update:
*
*Problem the code solves, how it is used,(executed, input, outputs etc.):
*
*The code lets the user input characters to stdin and the oputputs the characters to stdout. 
*It filters every 'a' character into an 'x' character untiol the user enters an EOF character. Then
*the program is terminated.
*
*
*Code based upon: 
*
*The code has been based upon the instructions of the Preporatory Lab PM.
*/

# include <stdio.h>


/**
*Let's the user input any character and outputs it to stdout. When a user
*inputs an 'a' character the function outputs an 'x' character instead. 
*The loop is terminated when the user inputs an "end of file" character(Ctrl-d or Ctrl-z depending on the OS).
*
*/
void manualEntering(){
	
	int c;
	
	printf("Enter  characters: ");
	
	while((c = getchar()) != EOF){
		
		if('a' == c)
			c = 'x';
		
	putchar(c);
	}
	
}


/**
*Calls for manualEntering and then signals that the program has ended
*through a printout.
*
*/
int main(){
	
	manualEntering();
	
	printf("\r\n\r\nThe program has ended!");
	
	return 0;
}