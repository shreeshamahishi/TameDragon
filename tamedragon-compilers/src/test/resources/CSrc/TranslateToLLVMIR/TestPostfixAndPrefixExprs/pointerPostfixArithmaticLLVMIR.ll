define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32*, align 8
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32* %1, i32** %c, align 8
  %3 = load i32*, i32** %c, align 8
  %4 = getelementptr inbounds i32, i32* %3, i32 1
  store i32* %4, i32** %c, align 8
  %5 = load i32, i32* %3, align 4
  store i32 %5, i32* %d, align 4
  %6 = load i32, i32* %d, align 4
  ret i32 %6
}
