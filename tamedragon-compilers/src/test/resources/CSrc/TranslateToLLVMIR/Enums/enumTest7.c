#include <stdio.h>

enum BOOLEAN { false, true } end_flag;
enum BOOLEAN match_flag;

int main(){
   if ( match_flag == false )
    {
      printf("hello\n");
    }
    end_flag = true;
}