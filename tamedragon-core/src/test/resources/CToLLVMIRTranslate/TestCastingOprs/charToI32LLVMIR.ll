define i32 @foo(i32 %a, double %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %ch = alloca i8, align 1
  store i32 %a, i32* %1, align 4
  store double %b, double* %2, align 8
  store i8 97, i8* %ch, align 1
  %3 = load i8, i8* %ch, align 1
  %4 = sext i8 %3 to i32
  store i32 %4, i32* %1, align 4
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
