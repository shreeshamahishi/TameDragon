define i32 @foo(i32 %a, double %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %c = alloca i32*, align 8
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store double %b, double* %2, align 8
  store i32* %1, i32** %c, align 8
  %3 = load i32*, i32** %c, align 8
  %4 = ptrtoint i32* %3 to i32
  store i32 %4, i32* %d, align 4
  %5 = load i32, i32* %d, align 4
  ret i32 %5
}
