
define dso_local i32 @foo(i32 %max, i32 %min, i32 %val) noinline nounwind uwtable {
entry:
  %val.addr = alloca i32, align 4
  %min.addr = alloca i32, align 4
  %max.addr = alloca i32, align 4
  %m = alloca i32, align 4
  %n = alloca i32, align 4
  %p = alloca i32, align 4
  %q = alloca i32, align 4
  %r = alloca i32, align 4
  store i32 %val, i32* %val.addr, align 4
  store i32 %min, i32* %min.addr, align 4
  store i32 %max, i32* %max.addr, align 4
  store i32 40, i32* %m, align 4
  store i32 45, i32* %n, align 4
  %0 = load i32, i32* %m, align 4
  %1 = load i32, i32* %n, align 4
  %add = add i32 %0, %1
  store i32 %add, i32* %p, align 4
  %2 = load i32, i32* %max.addr, align 4
  %3 = load i32, i32* %m, align 4
  %sub = sub i32 %2, %3
  store i32 %sub, i32* %q, align 4
  %4 = load i32, i32* %m, align 4
  %5 = load i32, i32* %q, align 4
  %add1 = add i32 %4, %5
  store i32 %add1, i32* %r, align 4
  %6 = load i32, i32* %r, align 4
  %7 = load i32, i32* %val.addr, align 4
  %add2 = add i32 %6, %7
  ret i32 %add2
}