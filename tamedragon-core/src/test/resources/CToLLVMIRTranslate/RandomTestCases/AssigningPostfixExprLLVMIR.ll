define i32 @foo() nounwind {
  %p = alloca i32, align 4
  store i32 10, i32* %p, align 4
  %1 = load i32, i32* %p, align 4
  %2 = add i32 %1, 1
  store i32 %2, i32* %p, align 4
  store i32 %1, i32* %p, align 4
  ret i32 0
}
