define i32 @bar(i32 %max_val, i32 %j, i32 %addr) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %var = alloca i32, align 4
  %foo = alloca i32, align 4
  %ptr = alloca i32*, align 8
  store i32 %max_val, i32* %1, align 4
  store i32 %j, i32* %2, align 4
  store i32 %addr, i32* %3, align 4
  store i32 10, i32* %var, align 4
  store i32 20, i32* %foo, align 4
  store i32* %var, i32** %ptr, align 8
  %4 = load i32*, i32** %ptr, align 8
  %5 = load i32, i32* %4, align 4
  ret i32 %5
}
