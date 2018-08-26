
int *gptr;
int MAX = 100, INTM = 50;
int bar(int *a, int value){
	int *ptr;
	
	if(value < MAX){
		ptr = gptr;
	} 
	else{
		ptr = a;
	}
	
	int x = *ptr * value;
	return x;
}