/* Rabin-Karp String Matching Algorithm
   RabinKarpMatch(T,P,d,q)
   - Assume T and P consist only a..z and A..Z
   - radix d, prime q
   - match whether P is a substring of T
   - return the starting index of the first occurence of P in T
   - n = length(T)
   - m = length(P)

    Worst Case : O((n-m+1)*m)
    Best Case : O(n+m)
*/
#include <stdio.h>
#include <string.h>
/* return a^p mod m */
int mod(int a,int p,int m)
{
	 int sqr;
     if (p == 0) return 1;
      sqr = mod(a,p/2,m) % m;
     sqr = (sqr * sqr) % m;
     if (p & 1)
    	 return ((a % m) * sqr) % m;
     else
    	 return sqr;
}
int tonum(char c) {
	if(c >= 'A' && c <= 'Z')
		return c - 'A' ;
	else
		return c - 'a' + 26;
}
int RabinKarpMatch(char *T,char *P,int d,int q)
{
     int i,j,p,t,n,m,h,found;
     n = strlen(T);
     m = strlen(P);
     h = mod(d,m-1,q);
     p = t = 0;
     for (i=0; i<m; i++)
     {
          p = (d*p + tonum(P[i])) % q;
          t = (d*t + tonum(T[i])) % q;
     }
     for (i=0; i<=n-m; i++)
     {
          if (p == t)
          {
               found = 1;
               for (j=0; j<m; j++)
                   if (P[j] != T[i+j])
                   {
                       found = 0;
                       break;
                   }
               if (found) return i;
          } else {
               t = (d*(t - ((tonum(T[i])*h) % q)) + tonum(T[i+m])) % q;
          }
     }
     return -1;
}

int main(){
	char str[100], pattern[100];
	int index;
	printf("Enter the String ");
	scanf("%s", str);
	printf("Enter pattern to search in the String ");
	scanf("%s", pattern);
	index = RabinKarpMatch(str, pattern, 26, 1);
	if(index == -1)
		printf("Pattern doesn't exist\n");
	else
		printf("Pattern exist from index %d\n", index);
	return 0;
}
