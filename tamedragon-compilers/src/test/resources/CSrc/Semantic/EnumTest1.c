
void main()
{
	enum escapes{BELL = '\a', BACKSPACE = '\b', TAB = '\t'};
	enum boolean{NO, YES};
	enum test{t1, t2};
	enum some_other{x=1, y=2, z=3, a};
	enum invalid{ix=2.3, iy=4.0, iz};
	enum zy {z, r};
	enum mm{A=x*x, B};
	enum rr{R, S, R};
	double test;

	enum xyz;
		enum test;

	int m1, m2;

	enum some_other;

	enum boolean yes_or_no1, yes_or_no2;
	enum zzz{s1, s2} w1;
	int x;

	enum boolean {NO1, YES1};


	m1 = x;
	m2 = boolean;
	m1 = boolean.YES;
	yes_or_no1 = NO;
	yes_or_no1 = 20;
	yes_or_no2 = 23.4;
	yes_or_no1 = YES1;
}
