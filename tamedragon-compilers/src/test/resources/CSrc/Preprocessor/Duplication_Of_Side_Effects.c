#define min(X, Y)                \
     ({ typeof (X) x_ = (X);          \
        typeof (Y) y_ = (Y);          \
        (x_ < y_) ? x_ : y_; })

int foo(int a){
	return a;
}
int main(){
	int x = 10, y = 20, z = 30, next = min (x + y, foo (z));
	return next;
}
