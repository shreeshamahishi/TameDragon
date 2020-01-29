define i32 @foo() nounwind {
  %1 = load i32, i32* @glb1, align 4
  store i32 25, i32* @glb2, align 4
  %2 = load i32, i32* @glb1, align 4
  %3 = add i32 %1, %2
  ret i32 %3
}
