#include<stdio.h>

int glb_val = 5;

int factorial(int i);

int main(int argc, char** argv){
    for(int n = 0 ; n < glb_val; n++){
       for(int j = 0; j <= (glb_val - (n + 2)); j++)
          printf(" ");
       for(int r = 0; r <= n; r++){
           int n_fact = factorial(n);
           int n_minus_r_fact = factorial(n-r);
           int r_fact = factorial(r);
           int result = n_fact/(n_minus_r_fact * r_fact);
           printf("%d ", result);
       }
       printf("\n");
    }
}

int factorial(int i){
   if(i == 0)
       return 1;
   else
       return i * factorial(i - 1);
}
