
define dso_local i32 @foo(i32 %val1, i32 %val2, i32 %val3) noinline nounwind uwtable {
entry:
  %val3.addr = alloca i32, align 4
  %val2.addr = alloca i32, align 4
  %val1.addr = alloca i32, align 4
  %q = alloca i32, align 4
  %r = alloca i32, align 4
  %s = alloca i32, align 4
  %t = alloca i32, align 4
  %v = alloca i32, align 4
  store i32 %val3, i32* %val3.addr, align 4
  store i32 %val2, i32* %val2.addr, align 4
  store i32 %val1, i32* %val1.addr, align 4
  %0 = load i32, i32* %val1.addr, align 4
  %1 = load i32, i32* %val2.addr, align 4
  %add = add i32 %0, %1
  store i32 %add, i32* %q, align 4
  %2 = load i32, i32* %q, align 4
  %3 = load i32, i32* %val3.addr, align 4
  %add1 = add i32 %2, %3
  store i32 %add1, i32* %r, align 4
  %4 = load i32, i32* %val2.addr, align 4
  %5 = load i32, i32* %val3.addr, align 4
  %add2 = add i32 %4, %5
  store i32 %add2, i32* %s, align 4
  %6 = load i32, i32* %val1.addr, align 4
  %7 = load i32, i32* %s, align 4
  %add3 = add i32 %6, %7
  store i32 %add3, i32* %t, align 4
  %8 = load i32, i32* %r, align 4
  %9 = load i32, i32* %t, align 4
  %sub = sub i32 %8, %9
  store i32 %sub, i32* %v, align 4
  %10 = load i32, i32* %v, align 4
  ret i32 %10
}