//#include<stdio.h>

struct abc{
	int x;
	double y;
	char *msg;
};

struct xyz {
	struct abc a1[2][2];
	double d;
	int m;
	
};

struct mst1
{
	double darray[2][];
	short s;
};

struct mst2
{
	double darray[][2];
	char *str;
};

struct mst3
{
	int mtr;
	double darray[][2];
};

void main()
{
	struct abc a1[][3] = {1, 2.0, "pascal", 3, 4.0, "wager", 
			       5, 6.0, "tabled", 7, 8.0, "water",
			       9, 10.0, "hung", 11, 12.0, "harry"};	

	struct xyz x1[][2] = {1, 2.0, "pascal", 3, 4.0, "wager", 5, 6.0, "tabled", 7, 8.0, "water",
			       10.0, 11,
			       32, 12.3, "potter", 43, 21.21, "todo", 8, 6.8, "storm", 7, 8.0, "well",
			       10.0, 11,
			        432, 12.9, "hangar", 43, 21.21, "ship",	7, 32.8, "signal", 7, 8.0, "morning",
			       10.0, 11,
			       32, 12.3, "show", 43, 21.21, "word", 8, 6.8, "pointer", 7, 8.0, "wellness",
			       10.0, 11};
	struct mst3 m1 = {1, 2.0, 3.0, 4.0, 5.0};	
	struct mst3 m2 = {1, 2.0, 3.0};	
	struct mst3 m3 = {1, 2.0};	
	struct mst3 m4 = {1};	
}

