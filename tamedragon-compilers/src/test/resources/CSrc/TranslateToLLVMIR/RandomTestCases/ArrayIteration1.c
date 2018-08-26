int MAX = 300;

int foo(int m, int arr[MAX])
{
	extern int split;
	
	int *p1, *p2, *ptr, count = 0, result = 65;
	
	p1 = arr; p2 = arr;
	
	while(count < m)
	{
		p1  = p1 + 1; p2 = p2 + 1;
		//p1++ ; p2++;
		if(count < split){
			ptr = p1 + 1;
		}
		else{
			ptr = p2;
		}
		
		result += *ptr;
		
		count++;  
	};
}