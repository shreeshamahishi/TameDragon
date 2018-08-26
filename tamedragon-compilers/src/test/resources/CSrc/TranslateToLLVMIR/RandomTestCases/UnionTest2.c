union def
{
	int x[2][2];
	double d;
};

int main(){
	union def ud1 = {{{1, 2}, {3, 4}}}; 
	return 0;
}
