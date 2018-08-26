define i32 @bar(i32 %max_val, i32 %j, i32 %addr) nounwind uwtable {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %index = alloca i32, align 4
  %result = alloca i32, align 4
  %var1 = alloca i32, align 4
  %var2 = alloca i32, align 4
  %var3 = alloca i32, align 4
  %var4 = alloca i32, align 4
  %var5 = alloca i32, align 4
  store i32 %max_val, i32* %1, align 4
  store i32 %j, i32* %2, align 4
  store i32 %addr, i32* %3, align 4
  store i32 0, i32* %result, align 4
  store i32 0, i32* %index, align 4
  br label %4

; <label>:4		; preds = %32, %0
  %5 = load i32* %index, align 4
  %6 = load i32* %1, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %35

; <label>:8           		; preds = %4
  %9 = load i32* %index, align 4
  %10 = mul i32 100, %9
  store i32 %10, i32* %var1, align 4
  %11 = load i32* %var1, align 4
  %12 = load i32* %2, align 4
  %13 = add i32 %11, %12
  store i32 %13, i32* %var2, align 4
  %14 = load i32* %var2, align 4
  %15 = sub i32 %14, 101
  store i32 %15, i32* %var3, align 4
  %16 = load i32* %var3, align 4
  %17 = mul i32 4, %16
  store i32 %17, i32* %var4, align 4
  %18 = load i32* %var4, align 4
  %19 = load i32* %3, align 4
  %20 = add i32 %18, %19
  store i32 %20, i32* %var5, align 4
  %21 = load i32* %result, align 4
  %22 = load i32* %var1, align 4
  %23 = load i32* %var2, align 4
  %24 = sub i32 %22, %23
  %25 = load i32* %var3, align 4
  %26 = add i32 %24, %25
  %27 = load i32* %var4, align 4
  %28 = load i32* %var5, align 4
  %29 = mul i32 %27, %28
  %30 = add i32 %26, %29
  %31 = add i32 %21, %30
  store i32 %31, i32* %result, align 4
  br label %32

; <label>:32         		; preds = %8
  %33 = load i32* %index, align 4
  %34 = add i32 %33, 1
  store i32 %34, i32* %index, align 4
  br label %4

; <label>:35      		; preds = %4
  %36 = load i32* %result, align 4
  ret i32 %36
}