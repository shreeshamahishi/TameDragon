@x = global i32 2, align 4
@y = global i32 3, align 4
@z = global i32 4, align 4

define void @moo(i32 %x, i32* %y) nounwind {
  %1 = load i32, i32* %y, align 4
  %2 = add i32 %1, 3
  store i32 %2, i32* %y, align 4
  ret void
}