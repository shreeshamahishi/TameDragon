/* Examples of declarations of functions */

void square1(void);   /* Example of a function without input parameters
		 	 and without return value */

void square2(int i);  /* Example of a function with one input parameter
                         and without return value */

int square3(void);    /* Example of a function without input parameters
                         and with integer return value */

int square4(int i);   /* Example of a function with one input parameter
			 and with integer return value */

int area(int b, int h); /* Example of a function with two input parameters
			   and with integer return value */

/* Main program: Using the various functions */
int main (void) {
   square1();    /* Calling the square1 function */
   square2(7);   /* Calling the square2 function using 7 as actual
		    parameter corresponding to the formal parameter i */
   square3(); /* Ysing the square3
							   function */
  square4(5); /* Using the square4
		 function with 5 as actual parameter corresponding to i */
   area(3,7); /* Using the area
                 function with 3, 7 as actual parameters corresponding
		 to b, h respectively */
}

/* Definitions of the functions */

/* Function that reads from standard input an integer and prints
   it out together with its sum */
void square1(void){
  int x = x*x;
}

/* Function that prints i together with its sum */
void square2(int i){
  i = i*i;
}

/* Function that reads from standard input an integer and returns
   its square */
int square3(void){
  int x = 2;
  return (x*x);
}

/* Function that returns the square of i */
int square4(int i){
  return (i*i);
}

/* Function that returns the area of the rectangle with base b
   and hight h */
int area(int b, int h){
  return (b*h);
}
