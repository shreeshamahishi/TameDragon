define i32* @foo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  %b = alloca i32*, align 8
  store i32 %a, i32* %1, align 4
  store i32* %1, i32** %b, align 8
  %2 = load i32*, i32** %b, align 8
  ret i32* %2
}
