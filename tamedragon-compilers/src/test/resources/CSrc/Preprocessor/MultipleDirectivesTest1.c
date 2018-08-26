
#include <stdio.h>
#include <cmath.h>
#include <ctype.h>

#define MAX_COUNT 1000

#include<conio.h>

#define SYSTEM MSDOS

# define MIN_COUNT 300

#include     "sample1.h"

#ifdef MAX_COUNT
	#include <floh.h>
#elif SYSTEM == SYSV 
	#include   <half.h>
# elif SYSTEM == MSDOS
	#include   <half1.h>
#else
	# define FACTORIAL f *f * f
#endif
	
#if !defined SYSTEM
	#undef FACTORIAL
# endif

#if defined MAX_COUNT
	# error Why is max_count defined here
#endif

#include "tyranny.h"		

#include <turn.h>
#ifndef HDR
	#include <hdr.h>
#endif

#error There is a problem here
# line 34 "heavy.c"
#line 23

#

int main(){
	int i = 10;
	int j = 20, k;	
	k = i + j;	
	int defined = 30;
	
	printf("The result is%d\n", k);
	
}