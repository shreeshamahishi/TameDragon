declare void @boo(i32) 

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  call void @boo(i32 %3)
  %4 = load i32, i32* %2, align 4
  ret i32 %4
}
