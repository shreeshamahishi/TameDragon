define void @moo(i32 %x, i32* %y) nounwind {
  %1 = load i32, i32* %y, align 4
  %2 = add i32 %1, 3
  store i32 %2, i32* %y, align 4
  ret void
}
