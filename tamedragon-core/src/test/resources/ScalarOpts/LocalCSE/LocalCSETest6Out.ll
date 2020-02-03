define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  call void @boo(i32* %1)
  %2 = load i32, i32* %1, align 4
  %3 = add i32 %2, 5
  ret i32 %3
}

declare void @boo(i32*)