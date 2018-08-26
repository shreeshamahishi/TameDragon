/*
	Try various assignments including compatible ones as well as narrowing ones.
*/

//#include <stdafx.h>

void main()
{
	 char c;
	 short s;
	 int m;
	 long ln;
	 
	 char c1 = 2;
	 char c2 = 345;
	 short s1 = 4567;
	 short s2 = 4567890;
	 int m1 = 123456789012367;
	 
	 double d = 43.23;
	 float f = 34.2;
	 
	 // Assign double to other types
	 c = d;
	 s = d;
	 m = d;
	 ln = d;
	 f = d;
	 
	 // Assign float to other types
	 c = f;
	 s = f;
	 m = f;
	 ln = f;
	 d = f;
	 
	 // Assign int to other types
	 c = m;
	 s = m;
	 ln = m;
	 d = m;
	 f = m;
	 
	 // Assign short to other types
	 c = s;
	 m = s;
	 ln = s;
	 d = s;
	 f = s;
	 
	 // Assign char to other types
	 s = c;
	 m = c;
	 ln = c;
	 d = c;
	 f = c;
	 
	 // Assign long to other types
	 c = ln;
	 s = ln;
	 m = ln;
	 d = ln;
	 f = ln;
}