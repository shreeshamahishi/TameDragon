void fibonacci(int first, int second){
	if(second > 100)
		return;
	else{
		int add = first + second;
		first = second;
		second = add;
		fibonacci(first,second);
	}
}
