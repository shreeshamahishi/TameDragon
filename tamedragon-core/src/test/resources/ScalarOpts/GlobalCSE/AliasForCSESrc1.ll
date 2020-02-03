declare void @foo(i32*) 

define i32 @bar(i32 %max) nounwind {
  %a = alloca i32, align 4
  store i32 %max, i32* %a, align 4
  %1 = load i32* %a, align 4
  %2 = add i32 %1, 5
  call void @foo(i32* %a)
  %3 = load i32* %a, align 4
  %4 = add i32 %3, 5
  ret i32 %4
}