define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %f = alloca float, align 4
  store i32 0, i32* %1, align 4
  store float 0x3F926E9780000000, float* %f, align 4
  %2 = load i32, i32* %1, align 4
  ret i32 %2
}
