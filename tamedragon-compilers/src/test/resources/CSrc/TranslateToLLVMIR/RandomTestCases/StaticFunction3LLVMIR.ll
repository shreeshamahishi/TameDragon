@i = common global i32 0, align 4
@a = common global [10 x i32*] zeroinitializer, align 16

define internal void @foo() nounwind {
  %k = alloca i32, align 4
  %q = alloca i32, align 4
  store i32 30, i32* %k, align 4
  store i32 40, i32* %q, align 4
  store i32 100, i32* @i, align 4
  %1 = load i32, i32* @i, align 4
  %2 = load i32, i32* %q, align 4
  %3 = mul i32 %1, %2
  store i32 %3, i32* %k, align 4
  %4 = load i32, i32* @i, align 4
  %5 = load i32, i32* %k, align 4
  %6 = sdiv i32 %4, %5
  store i32 %6, i32* %q, align 4
  ret void
}

define void @moo() nounwind {
  call void @foo()
  ret void
}
