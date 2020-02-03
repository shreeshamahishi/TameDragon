@i = common global i32 0, align 4
@a = common global [10 x i32*] zeroinitializer, align 16
@j = internal global i32 20, align 4

define void @foo() nounwind {
  %k = alloca i32, align 4
  %q = alloca i32, align 4
  store i32 30, i32* %k, align 4
  store i32 40, i32* %q, align 4
  store i32 100, i32* @i, align 4
  %1 = load i32, i32* %k, align 4
  %2 = load i32, i32* %q, align 4
  %3 = add i32 %1, %2
  store i32 %3, i32* @j, align 4
  %4 = load i32, i32* @j, align 4
  %5 = load i32, i32* %q, align 4
  %6 = mul i32 %4, %5
  store i32 %6, i32* %k, align 4
  %7 = load i32, i32* @i, align 4
  %8 = load i32, i32* @j, align 4
  %9 = sdiv i32 %7, %8
  store i32 %9, i32* %q, align 4
  ret void
}
