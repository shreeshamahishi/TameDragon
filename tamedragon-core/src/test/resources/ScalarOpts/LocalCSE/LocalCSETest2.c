int foo(int a, int b, int sum, int sum2){
    sum = a+b;
    sum2 = a+b;
    a = a+b;
    sum++;
    sum2--;
    if(a>b){
         sum = a+b;
         sum2 = a+b;
         a = a+b;
         sum++;
         sum2--;
	return sum2;
    }
    else{
         sum = a+b;
         sum2 = a+b;
         a = a+b;
         sum++;
         sum2--;
         return sum;
    }
}