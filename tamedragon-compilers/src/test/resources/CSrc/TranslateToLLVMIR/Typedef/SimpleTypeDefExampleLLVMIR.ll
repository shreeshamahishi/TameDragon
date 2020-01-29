define void @foo() nounwind {
  %i = alloca i32, align 4
  store i32 10, i32* %i, align 4
  ret void
}
