@a = global i32 0, align 4

define void @foo() nounwind {
  %b = alloca i32, align 4
  store i32 4, i32* %b, align 4
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 2, i32* %a, align 4
  store i32 3, i32* %b, align 4
  call void @foo()
  %2 = load i32, i32* %1, align 4
  ret i32 %2
}
