define i32 @foo(i32* %arr, i32 %max, i32 %m, i32 %split, i32 %x, i32 %y) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %count = alloca i32, align 4
  %inner_count = alloca i32, align 4
  %ptr = alloca i32*, align 8
  %result = alloca i32, align 4
  store i32* %arr, i32** %1, align 8
  store i32 %max, i32* %2, align 4
  store i32 %m, i32* %3, align 4
  store i32 %split, i32* %4, align 4
  store i32 %x, i32* %5, align 4
  store i32 %y, i32* %6, align 4
  store i32 10, i32* %a, align 4
  store i32 20, i32* %b, align 4
  store i32 40, i32* %c, align 4
  store i32 0, i32* %count, align 4
  store i32 0, i32* %inner_count, align 4
  %7 = load i32** %1, align 8
  store i32* %7, i32** %ptr, align 8
  store i32 0, i32* %result, align 4
  br label %8

; <label>:8 		; preds = %58, %0
  %9 = load i32* %count, align 4
  %10 = load i32* %2, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %71

; <label>:12         		; preds = %8
  %13 = load i32** %ptr, align 8
  %14 = getelementptr inbounds i32* %13, i32 1
  store i32* %14, i32** %ptr, align 8
  %15 = load i32** %ptr, align 8
  %16 = load i32* %b, align 4
  %17 = load i32* %c, align 4
  %18 = add i32 %16, %17
  %19 = load i32* %15, align 4
  %20 = add i32 %18, %19
  store i32 %20, i32* %a, align 4
  %21 = load i32** %ptr, align 8
  %22 = load i32* %3, align 4
  %23 = load i32* %21, align 4
  %24 = add i32 %23, %22
  %25 = inttoptr i32 %24 to i32*
  store i32* %25, i32** %ptr, align 8
  br label %26

; <label>:26     		; preds = %46, %12
  %27 = load i32* %inner_count, align 4
  %28 = load i32* %5, align 4
  %29 = icmp slt i32 %27, %28
  br i1 %29, label %30, label %49

; <label>:30    		; preds = %26
  %31 = load i32** %ptr, align 8
  %32 = load i32* %31, align 4
  %33 = add i32 %32, 3
  %34 = inttoptr i32 %33 to i32*
  store i32* %34, i32** %ptr, align 8
  %35 = load i32** %ptr, align 8
  %36 = load i32* %35, align 4
  %37 = add i32 %36, 3
  store i32 %37, i32* %a, align 4
  %38 = load i32* %inner_count, align 4
  %39 = load i32* %6, align 4
  %40 = icmp slt i32 %38, %39
  br i1 %40, label %41, label %46

; <label>:41        		; preds = %30
  %42 = load i32** %ptr, align 8
  %43 = load i32* %42, align 4
  %44 = add i32 %43, 2
  %45 = inttoptr i32 %44 to i32*
  store i32* %45, i32** %ptr, align 8
  br label %46

; <label>:46         		; preds = %41, %30
  %47 = load i32* %inner_count, align 4
  %48 = add i32 %47, 1
  store i32 %48, i32* %inner_count, align 4
  br label %26

; <label>:49    		; preds = %26
  %50 = load i32* %count, align 4
  %51 = load i32* %4, align 4
  %52 = icmp slt i32 %50, %51
  br i1 %52, label %53, label %58

; <label>:53        		; preds = %49
  %54 = load i32** %ptr, align 8
  %55 = load i32* %54, align 4
  %56 = add i32 %55, 2
  %57 = inttoptr i32 %56 to i32*
  store i32* %57, i32** %ptr, align 8
  br label %58

; <label>:58    		; preds = %53, %49
  %59 = load i32* %count, align 4
  %60 = add i32 %59, 1
  store i32 %60, i32* %count, align 4
  %61 = load i32** %ptr, align 8
  %62 = load i32* %a, align 4
  %63 = load i32* %61, align 4
  %64 = add i32 %63, %62
  %65 = load i32* %b, align 4
  %66 = add i32 %64, %65
  %67 = load i32* %c, align 4
  %68 = add i32 %66, %67
  %69 = load i32* %result, align 4
  %70 = add i32 %69, %68
  store i32 %70, i32* %result, align 4
  br label %8

; <label>:71      		; preds = %8
  %72 = load i32* %result, align 4
  ret i32 %72
}