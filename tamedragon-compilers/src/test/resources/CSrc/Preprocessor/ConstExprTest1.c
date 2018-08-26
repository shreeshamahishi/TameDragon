# define VARTEST1 0
# define VARTEST2 1

#include <stdio.h>

int main() {

     #if VARTEST1 + VARTEST2
         printf("%s\n", "Inside addition");
     #endif

     #if VARTEST1 - VARTEST2
         z = 0;
         printf("%s\n", "Inside subtraction");
     #endif

     #if VARTEST1 * VARTEST2
         printf("%s\n", "Inside multiplication");
     #else
         printf("%s\n", "Inside ELSE of multiplication");
     #endif
     
     #if VARTEST1 / VARTEST2
         y = 20;
         printf("%s\n", "Inside division");
	 #else
         printf("%s\n", "Inside ELSE of division");
     #endif
        
	#if (VARTEST1 + VARTEST2)
	    printf("%s\n", "Inside addition with parentheses");
    #endif
    
    #if (VARTEST1 - VARTEST1)
        printf("%s\n", "Inside subtraction with parentheses");
    #else
        printf("%s\n", "Inside ELSE of subtraction with parentheses");
    #endif
        
    #if VARTEST2  >> VARTEST1
        printf("%s\n", "right shift operator");
    #endif
    
    #if VARTEST2  << VARTEST1
        printf("%s\n", "right shift operator");
    #endif
    
    #if ((VARTEST1 + VARTEST2)/(VARTEST1 * (VARTEST1 / VARTEST2))) * (VARTEST1 >> VARTEST2)
        printf("%s\n", "a relatively complex math");
    #endif
    
    #if VARTEST1 < VARTEST2
		printf("%s\n", "Inside LT comparison IF");
	#else
		printf("%s\n", "Inside LT comparison Else");
	#endif
	
	#if VARTEST1 != VARTEST2
		printf("%s\n", "Inside NE comparison IF");
	#else
		printf("%s\n", "Inside NE comparison Else");
	#endif
	
	#if VARTEST1 ^ VARTEST2
		printf("%s\n", "Inside NE comparison IF");
	#else
		printf("%s\n", "Inside NE comparison Else");
	#endif
	
	#if VARTEST1 & VARTEST2
		printf("%s\n", "Inside Amp comparison IF");
	#else
		printf("%s\n", "Inside Amp comparison Else");
	#endif
	
    
    /*#if VARTEST1 = VARTEST2
        abc = 45;
        printf("%s\n", "Inside simple assignment");
    #endif
    
    #if VARTEST1 += VARTEST2
        def = 45;
        printf("%s\n", "Inside simple assignment");
    #endif

	#if ++VARTEST2
	    printf("%s\n", "Inside prefix  ++");
	#endif
	
	#if VARTEST2--
	    printf("%s\n", "Inside postfix --");
	#endif
	
	#if VARTEST2 -> VARTEST1
	    printf("%s\n", "Inside dereference operator");
    #endif
	
	#if VARTEST1.VARTEST2
	    printf("%s\n", "Inside pointer dereference");
	#else
	    printf("%s\n", "Inside ELSE of  pointer dereference");
	#endif 
	
	#if VARTEST1^VARTEST2
	    printf("%s\n", "Inside ^ ");
	#endif */
	
	#if VARTEST1 == VARTEST1
	    printf("%s\n", "Inside simple comparison");
		x = 10;
    #endif

    return 1;
}
