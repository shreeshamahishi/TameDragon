define i32 @foo(i32* %ptrX, i32* %ptrY) nounwind {
  %1 = load i32, i32* %ptrX, align 4
  %2 = add i32 40, %1
  %3 = add i32 %2, %2
  ret i32 %3
}
