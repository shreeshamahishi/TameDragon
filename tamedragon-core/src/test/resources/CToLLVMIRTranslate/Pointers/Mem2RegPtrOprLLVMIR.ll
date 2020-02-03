define i32 @bar(i32 %a) nounwind {
  %1 = alloca i32, align 4
  %m = alloca i32, align 4
  %n = alloca i32, align 4
  %p = alloca i32, align 4
  %ptr = alloca i32*, align 8
  store i32 %a, i32* %1, align 4
  store i32 10, i32* %m, align 4
  store i32 30, i32* %n, align 4
  store i32* %m, i32** %ptr, align 8
  %2 = load i32, i32* %1, align 4
  %3 = load i32, i32* %m, align 4
  %4 = mul i32 %2, %3
  store i32 %4, i32* %p, align 4
  %5 = load i32, i32* %n, align 4
  %6 = load i32, i32* %p, align 4
  %7 = add i32 %5, %6
  store i32 %7, i32* %n, align 4
  %8 = load i32, i32* %1, align 4
  ret i32 %8
}
