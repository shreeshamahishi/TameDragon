define i32 @foo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  %b = alloca i32*, align 8
  %c = alloca i32**, align 8
  %d = alloca i32***, align 8
  %e = alloca i32*, align 8
  %f = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32* %1, i32** %b, align 8
  store i32** %b, i32*** %c, align 8
  store i32*** %c, i32**** %d, align 8
  %2 = load i32***, i32**** %d, align 8
  %3 = load i32**, i32*** %2, align 8
  %4 = load i32*, i32** %3, align 8
  store i32* %4, i32** %e, align 8
  %5 = load i32*, i32** %e, align 8
  %6 = load i32, i32* %5, align 4
  store i32 %6, i32* %f, align 4
  %7 = load i32, i32* %f, align 4
  ret i32 %7
}
