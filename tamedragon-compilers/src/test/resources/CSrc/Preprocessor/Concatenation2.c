#define hash_hash # ## #
#define mkstr(a) # a
#define in_between(a) mkstr(a)
#define join(c, d) in_between(c hash_hash d)

int main(){
	char p[] = join(x, y); // equivalent to char p[] = "x ## y";
}
