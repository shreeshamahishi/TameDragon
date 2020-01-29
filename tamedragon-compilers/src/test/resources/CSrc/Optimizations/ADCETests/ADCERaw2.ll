define i32 @bar(i32* %a, i32 %b) nounwind uwtable {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %m = alloca i32, align 4
  %n = alloca i32, align 4
  %p = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 %b, i32* %2, align 4
  store i32 10, i32* %m, align 4
  store i32 30, i32* %n, align 4
  %3 = load i32* %m, align 4
  %4 = load i32** %1, align 8
  store i32 %3, i32* %4
  %5 = load i32** %1, align 8
  %6 = load i32* %5
  %7 = load i32* %m, align 4
  %8 = mul i32 %6, %7
  store i32 %8, i32* %p, align 4
  %9 = load i32* %p, align 4
  %10 = load i32* %n, align 4
  %11 = add i32 %10, %9
  store i32 %11, i32* %n, align 4
  %12 = load i32* %2, align 4
  ret i32 %12
}