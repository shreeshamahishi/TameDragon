define i32 @main() nounwind {
  %1 = alloca i1, align 4
  %first = alloca i32, align 4
  %second = alloca i32, align 4
  store i1 0, i1* %1, align 4
  store i32 10, i32* %first, align 4
  store i32 20, i32* %second, align 4
  %2 = load i1* %1
  %3 = load i32* %first
  %4 = load i32* %second
  %5 = select i1 %2, i32 %3, i32 %4
  ret i32 0
}