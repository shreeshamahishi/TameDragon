define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 1, i32* %a, align 4
  store i32 2, i32* %b, align 4
  %2 = load i32, i32* %a, align 4
  %3 = load i32, i32* %b, align 4
  %4 = ashr i32 %2, %3
  store i32 %4, i32* %c, align 4
  ret i32 0
}
