define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = add i32 %2, 5
  call void @boo(i32* %1)
  %4 = load i32, i32* %1, align 4
  %5 = add i32 %4, 5
  ret i32 %5
}

declare void @boo(i32*)