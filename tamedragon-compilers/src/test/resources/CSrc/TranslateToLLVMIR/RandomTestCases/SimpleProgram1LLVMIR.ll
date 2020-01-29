define void @square1() nounwind {
  %x = alloca i32, align 4
  %1 = load i32, i32* %x, align 4
  %2 = load i32, i32* %x, align 4
  %3 = mul i32 %1, %2
  store i32 %3, i32* %x, align 4
  ret void
}

define void @square2(i32 %i) nounwind {
  %1 = alloca i32, align 4
  store i32 %i, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = load i32, i32* %1, align 4
  %4 = mul i32 %2, %3
  store i32 %4, i32* %1, align 4
  ret void
}

define i32 @square3() nounwind {
  %x = alloca i32, align 4
  store i32 2, i32* %x, align 4
  %1 = load i32, i32* %x, align 4
  %2 = load i32, i32* %x, align 4
  %3 = mul i32 %1, %2
  ret i32 %3
}

define i32 @square4(i32 %i) nounwind {
  %1 = alloca i32, align 4
  store i32 %i, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = load i32, i32* %1, align 4
  %4 = mul i32 %2, %3
  ret i32 %4
}

define i32 @area(i32 %b, i32 %h) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %b, i32* %1, align 4
  store i32 %h, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = load i32, i32* %2, align 4
  %5 = mul i32 %3, %4
  ret i32 %5
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  call void @square1()
  call void @square2(i32 7)
  %2 = call i32 @square3()
  %3 = call i32 @square4(i32 5)
  %4 = call i32 @area(i32 3, i32 7)
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
