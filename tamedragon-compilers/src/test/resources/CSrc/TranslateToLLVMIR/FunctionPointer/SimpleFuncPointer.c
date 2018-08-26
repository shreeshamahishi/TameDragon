void foo(int (*bar)(int a));

int factorial(int X);

int main(void) {
  foo(factorial);
}
