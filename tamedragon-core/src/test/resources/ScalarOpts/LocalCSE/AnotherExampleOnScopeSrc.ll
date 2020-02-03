@x = global i32 2, align 4
@y = global i32 3, align 4
@z = global i32 4, align 4

define void @moo(i32 %x, i32* %y) nounwind {
  %1 = add i32 %x, 3
  %2 = load i32, i32* %y, align 4
  %3 = add i32 %2, 3
  store i32 %3, i32* %y, align 4
  %4 = add i32 undef, 3
  ret void
}