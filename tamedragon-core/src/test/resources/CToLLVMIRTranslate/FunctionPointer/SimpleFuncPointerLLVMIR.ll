declare void @foo(i32 (i32)*) 

declare i32 @factorial(i32) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  call void @foo(i32 (i32)* @factorial)
  %2 = load i32, i32, i32* %1, align 4
  ret i32 %2
}
