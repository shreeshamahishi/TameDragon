define i32 @foo(i32* %ptrX, i32* %ptrY) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32*, align 8
  %a = alloca i32, align 4
  %p1 = alloca i32*, align 8
  %p2 = alloca i32*, align 8
  %p3 = alloca i32*, align 8
  %p4 = alloca i32*, align 8
  %result = alloca i32, align 4
  store i32* %ptrX, i32** %1, align 8
  store i32* %ptrY, i32** %2, align 8
  store i32 40, i32* %a, align 4
  %3 = load i32*, i32** %1, align 8
  store i32* %3, i32** %p1, align 8
  %4 = load i32*, i32** %1, align 8
  store i32* %4, i32** %p2, align 8
  %5 = load i32*, i32** %2, align 8
  store i32* %5, i32** %p3, align 8
  store i32* %a, i32** %p4, align 8
  %6 = load i32*, i32** %p1, align 8
  %7 = load i32*, i32** %p2, align 8
  %8 = load i32, i32* %6, align 4
  %9 = load i32, i32* %7, align 4
  %10 = add i32 %8, %9
  store i32 %10, i32* %result, align 4
  %11 = load i32, i32* %result, align 4
  ret i32 %11
}
