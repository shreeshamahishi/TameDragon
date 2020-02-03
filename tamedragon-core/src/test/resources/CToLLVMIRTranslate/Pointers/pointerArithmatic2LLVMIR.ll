define i32 @foo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32*, align 8
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 10, i32* %b, align 4
  store i32* %1, i32** %c, align 8
  %2 = load i32*, i32** %c, align 8
  %3 = getelementptr inbounds i32, i32* %2, i32 2
  %4 = load i32, i32* %3, align 4
  store i32 %4, i32* %d, align 4
  %5 = load i32, i32* %d, align 4
  ret i32 %5
}
