define void @coo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  store i32 %2, i32* %b, align 4
  ret void
}

define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  call void @coo(i32 20)
  ret void
}
