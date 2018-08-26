// #include <stdio.h>

struct xyz{
	int x;
	double y;
	char *msg;
};

int int_array_all[2][2][3] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
double dbl_array_all[2][2][3] = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0};
char* str_array_all[2][2][3] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
struct xyz struct_array_all[2][2] = {1, 2.0,"hello",  3, 4.0, "there",  5, 6.0, "earthling",  7, 8.0, "take" };
 struct xyz struct_array_all_mix[2][2] = {1, 2.0,"hello",  
					 3.56, 4.0, "me", 

					 5, "mine", "earthling", 
					 "words", 8.0, 98 }; 

int int_array_less[2][2] = {1, 2, 3};
double dbl_array_less[2][2] = {1.0, 2.0, 3.0};
char* str_array_less[2][2] = {"1", "2", "3"};
struct xyz struct_array_less[2][2] = {1, 2.0, "to",  3 };

int int_array_more[2][2] = {1, 2, 3, 4, 5, 6};
double dbl_array_more[2][2] = {1.0, 2.0, 3.0, 4.0, 5.0};
char* str_array_more[2][2] = {"1", "2", "3", "4", "5"};
struct xyz struct_array_more[2][2] = {1, 2.0, "your",  3 , 4.0,"leader",  5};

