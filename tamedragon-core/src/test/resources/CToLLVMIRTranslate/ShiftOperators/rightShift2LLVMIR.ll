define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 0, i32* %a, align 4
  ret i32 0
}
