define i32 @boo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  %x = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  store i32 %2, i32* %x, align 4
  %3 = load i32, i32* %x, align 4
  ret i32 %3
}

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = call i32 @boo(i32 %3)
  %5 = load i32, i32* %2, align 4
  ret i32 %5
}
