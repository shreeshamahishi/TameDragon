union def
{
	double d;
	int x[2][2];
};

int main(){
	union def ud1 = {{{1, 2}, {3, 4}}}; 
	return 0;
}