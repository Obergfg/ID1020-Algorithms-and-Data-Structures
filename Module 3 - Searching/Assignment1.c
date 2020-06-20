/**
 * 
 * Author: Fredrik Öberg
 *
 *  Date of generation: 190916
 *
 *  Date of update:
 *
 *  Problem the code solves, how it is used,(executed, input, outputs etc.):
 *
 *	The code serves as a filter which removes all characters that are not alphabetic, blank or newline and replaces 
 *	every such character by a blank to keep the number of characters constant to the original text.
 *
 *  Code based upon :
 *
 *  The code has been based upon the instructions in the Searching Lab PM.
 */
# include <stdio.h>
# include <stdlib.h>
# include <ctype.h>


/**
*	Reads input from a textfile (input.txt) and filters out all characters that are not alphabetic, blank or newline and replaces 
*	every such character by a blank
*/
void textFileFilter(){
	
	char c;
	
	FILE *inpFile = fopen("input.txt","r");
	FILE *outpFile = fopen("output.txt" ,"w");
	
	if(inpFile){
		while( (c = getc(inpFile)) != EOF){
			if(!(0 != isalpha(c) || ' ' == c || '\n' == c))
				c = ' ';
		
			fprintf(outpFile, "%c" ,c);
		}
			
	}
	
	fclose(inpFile);
	fclose(outpFile);
}

/**
*	Initiates the program through a function call.
*
*/
int main(){
	
	textFileFilter();
	
	return 0;
}