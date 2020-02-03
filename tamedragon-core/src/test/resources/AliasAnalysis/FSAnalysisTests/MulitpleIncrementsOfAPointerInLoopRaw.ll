define i32 @foo(i32* %arr, i32 %max, i32 %m, i32 %n) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %count = alloca i32, align 4
  %ptr = alloca i32*, align 8
  %result = alloca i32, align 4
  store i32* %arr, i32** %1, align 8
  store i32 %max, i32* %2, align 4
  store i32 %m, i32* %3, align 4
  store i32 %n, i32* %4, align 4
  store i32 10, i32* %a, align 4
  store i32 20, i32* %b, align 4
  store i32 40, i32* %c, align 4
  store i32 0, i32* %count, align 4
  %5 = load i32** %1, align 8
  store i32* %5, i32** %ptr, align 8
  store i32 0, i32* %result, align 4
  br label %6

; <label>:6		; preds = %10, %0
  %7 = load i32* %count, align 4
  %8 = load i32* %2, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %46

; <label>:10          		; preds = %6
  %11 = load i32** %ptr, align 8
  %12 = getelementptr inbounds i32* %11, i32 1
  store i32* %12, i32** %ptr, align 8
  %13 = load i32** %ptr, align 8
  %14 = load i32* %b, align 4
  %15 = load i32* %c, align 4
  %16 = add i32 %14, %15
  %17 = load i32* %13, align 4
  %18 = add i32 %16, %17
  store i32 %18, i32* %a, align 4
  
  %19 = load i32** %ptr, align 8
  %20 = load i32* %3, align 4
;  %21 = load i32* %19, align 4
;  %22 = add i32 %21, %20
;  %23 = inttoptr i32 %22 to i32*
  %21 = getelementptr inbounds i32* %19, i32 %20
  %22 = load i32* %3, align 4    ; dummy instruction, ignore
  %23 = load i32* %3, align 4    ; dummy instruction, ignore
  store i32* %21, i32** %ptr, align 8
  
  %24 = load i32** %ptr, align 8
  %25 = load i32* %24, align 4
  %26 = add i32 2, %25
  %27 = load i32* %b, align 4
  %28 = add i32 %27, %26
  store i32 %28, i32* %b, align 4
  
  %29 = load i32** %ptr, align 8
  %30 = load i32* %4, align 4
;  %31 = load i32* %29, align 4
;  %32 = add i32 %31, %30
;  %33 = inttoptr i32 %32 to i32*
  %31 = getelementptr inbounds i32* %29, i32 %30
  %32 = load i32* %3, align 4    ; dummy instruction, ignore
  %33 = load i32* %3, align 4    ; dummy instruction, ignore
  store i32* %31, i32** %ptr, align 8
  
  
  %34 = load i32* %c, align 4
  %35 = add i32 %34, 3
  store i32 %35, i32* %c, align 4
  %36 = load i32** %ptr, align 8
  %37 = load i32* %a, align 4
  %38 = load i32* %36, align 4
  %39 = add i32 %38, %37
  %40 = load i32* %b, align 4
  %41 = add i32 %39, %40
  %42 = load i32* %c, align 4
  %43 = add i32 %41, %42
  %44 = load i32* %result, align 4
  %45 = add i32 %44, %43
  store i32 %45, i32* %result, align 4
  br label %6

; <label>:46      		; preds = %6
  %47 = load i32* %result, align 4
  ret i32 %47
}