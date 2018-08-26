int Fibonacci(int n);
int main(){
	int num = 10, i, ans;
	for(i = 0; i < num; i++){
		ans = Fibonacci(i);
		return ans;
	}
	return 0;
}

int Fibonacci(int n)
{
   if ( n == 0 )
      return 0;
   if ( n == 1 )
      return 1;
   else
      return ( Fibonacci(n-1) + Fibonacci(n-2));
}
