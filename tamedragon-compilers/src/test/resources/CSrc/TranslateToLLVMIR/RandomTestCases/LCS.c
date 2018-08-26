#include <stdio.h>
#include <string.h>
#include <malloc.h>

/**
  * This is an implementation, in C, of the Longest Common Subsequence algorithm.
  * That is, given two strings A and B, this program will find the longest sequence
  * of letters that are common and ordered in A and B.
  *
  * There are only two reasons you are reading this:
  *   - you don't care what the algorithm is but you need a piece of code to do it
  *   - you're trying to understand the algorithm, and a piece of code might help
  * In either case, you should either read an entire chapter of an algorithms textbook
  * on the subject of dynamic programming, or you should consult a webpage that describes
  * this particular algorithm.   It is important, for example, that we use arrays of size
  * |A|+1 x |B|+1.
  *
  * This code is provided AS-IS.
  * You may use this code in any way you see fit, EXCEPT as the answer to a homework
  * problem or as part of a term project in which you were expected to arrive at this
  * code yourself.
  *
  * Copyright (C) 2005 Neil Jones.
  *
  */

/**
  * Takes two NULL-terminated strings.  Returns the longest common subsequence to both.
  */
char* LCS(char* a, char* b);

#define NEITHER       0
#define UP            1
#define LEFT          2
#define UP_AND_LEFT   3

int main(int argc, char* argv[]) {
	printf("%s\n", LCS(argv[1],argv[2]));
}

/*
 * Note that this will allocate space for the result, and you will need
 * to free it yourself to avoid a memory leak.
 */
char* LCS(char* a, char* b) {
	int n = strlen(a);
	int m = strlen(b);

	int** S;
	int** R;

	int ii;
	int jj;

	int pos;
	char* lcs;

	/*
	 * Memory allocation.  Sigh.  What a quaint little idea. Nobody does this any more...
	 * A much better way to do this is to allocate one big array for S (and one for R)
	 * and then do your own pointer indexing.  However, that makes the card just slightly
	 * harder to read, so I avoid it.
	 */
	S = (int **)malloc( (n+1) * sizeof(int) );
	R = (int **)malloc( (n+1) * sizeof(int) );
	for(ii = 0; ii <= n; ++ii) {
		S[ii] = (int*) malloc( (m+1) * sizeof(int) );
		R[ii] = (int*) malloc( (m+1) * sizeof(int) );
	}

	/* It is important to use <=, not <.  The next two for-loops are initialization */
	for(ii = 0; ii <= n; ++ii) {
		S[ii][0] = 0;
		R[ii][0] = UP;
	}
	for(jj = 0; jj <= m; ++jj) {
		S[0][jj] = 0;
		R[0][jj] = LEFT;
	}

	/* This is the main dynamic programming loop that computes the score and */
	/* backtracking arrays. */
	for(ii = 1; ii <= n; ++ii) {
		for(jj = 1; jj <= m; ++jj) {

			if( a[ii-1] == b[jj-1] ) {
				S[ii][jj] = S[ii-1][jj-1] + 1;
				R[ii][jj] = UP_AND_LEFT;
			}

			else {
				S[ii][jj] = S[ii-1][jj-1] + 0;
				R[ii][jj] = NEITHER;
			}

			if( S[ii-1][jj] >= S[ii][jj] ) {
				S[ii][jj] = S[ii-1][jj];
				R[ii][jj] = UP;
			}

			if( S[ii][jj-1] >= S[ii][jj] ) {
				S[ii][jj] = S[ii][jj-1];
				R[ii][jj] = LEFT;
			}
		}
	}

	/* The length of the longest substring is S[n][m] */
	ii = n;
	jj = m;
	pos = S[ii][jj];
	lcs = (char *) malloc( (pos+1) * sizeof(char) );

	lcs[pos--] = (char)NULL;

	/* Trace the backtracking matrix. */
	while( ii > 0 || jj > 0 ) {
		if( R[ii][jj] == UP_AND_LEFT ) {
			ii--;
			jj--;
			lcs[pos--] = a[ii];
		}

		else if( R[ii][jj] == UP ) {
			ii--;
		}

		else if( R[ii][jj] == LEFT ) {
			jj--;
		}
	}

	for(ii = 0; ii <= n; ++ii ) {
		free(S[ii]);
		free(R[ii]);
	}
	free(S);
	free(R);

	return lcs;
}
