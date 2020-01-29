define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %f = alloca i32, align 4
  %next = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 0, i32* %f, align 4
  store i32 3, i32* %f, align 4
  store i32 3, i32* %next, align 4
  %2 = load i32, i32* %next, align 4
  ret i32 %2
}
