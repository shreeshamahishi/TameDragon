struct test4
{
	int m;
	struct inner {
		double m;
		double n;
	} rms;
	char * pmsg;
};

void foo(){
  struct test4 inner_inst = {0, {4.1, 3.2}, "hello complex"};
} 