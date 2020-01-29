@a = external global i32
@b = common global i32 0, align 4

define void @foo() nounwind {
  store i32 20, i32* @a, align 4
  ret void
}
