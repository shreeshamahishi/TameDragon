define i32 @foo(i32 %a, i32 %b, i32* %ptr) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32*, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32* %1, i32** %3, align 4
  %4 = load i32, i32* %1, align 4
  %5 = add i32 %4, 4
  store i32 %5, i32* %2, align 4
  %6 = load i32*, i32** %3, align 4
  %7 = load i32, i32* %6
  %8 = add i32 %7, 4
  store i32 %8, i32* %6
  %9 = load i32, i32* %1, align 4
  %10 = add i32 %9, 4
  store i32 %10, i32* %2, align 4
  ret i32 %10
}