@pglobal = common global i32* null, align 8
@FLAG = common global i32 0, align 4

define i32 @foo(i32 %max_iter, i32 %split, i32* %pArg) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32*, align 8
  %ptr1 = alloca i32**, align 8
  %ptr2 = alloca i32**, align 8
  %p1 = alloca i32*, align 8
  %p2 = alloca i32*, align 8
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %result = alloca i32, align 4
  %count = alloca i32, align 4
  %m0 = alloca i32, align 4
  %n0 = alloca i32, align 4
  %m1 = alloca i32, align 4
  %n1 = alloca i32, align 4
  %m2 = alloca i32, align 4
  %n2 = alloca i32, align 4
  %m3 = alloca i32, align 4
  %n3 = alloca i32, align 4
  %m4 = alloca i32, align 4
  %n4 = alloca i32, align 4
  store i32 %max_iter, i32* %1, align 4
  store i32 %split, i32* %2, align 4
  store i32* %pArg, i32** %3, align 8
  store i32** %p1, i32*** %ptr1, align 8
  store i32** %p2, i32*** %ptr2, align 8
  store i32* %a, i32** %p1, align 8
  store i32* %b, i32** %p2, align 8
  %4 = load i32** %p1, align 8
  store i32 42, i32* %4, align 4
  %5 = load i32** %p2, align 8
  store i32 34, i32* %5, align 4
  store i32 0, i32* %result, align 4
  store i32 0, i32* %count, align 4
  %6 = load i32*** %ptr1, align 8
  %7 = load i32** %6, align 8
  %8 = load i32* %7, align 4
  store i32 %8, i32* %m0, align 4
  %9 = load i32*** %ptr2, align 8
  %10 = load i32** %9, align 8
  %11 = load i32* %10, align 4
  store i32 %11, i32* %n0, align 4
  %12 = load i32* %m0, align 4
  %13 = load i32* %n0, align 4
  %14 = sub i32 %12, %13
  store i32 %14, i32* %result, align 4
  br label %15

; <label>:15		; preds = %57, %0
  %16 = load i32* %count, align 4
  %17 = load i32* %1, align 4
  %18 = icmp slt i32 %16, %17
  br i1 %18, label %19, label %82

; <label>:19         		; preds = %15
  %20 = load i32*** %ptr1, align 8
  %21 = load i32** @pglobal, align 8
  store i32* %21, i32** %20, align 8
  %22 = load i32*** %ptr1, align 8
  %23 = load i32** %22, align 8
  %24 = load i32* %23, align 4
  store i32 %24, i32* %m1, align 4
  %25 = load i32*** %ptr2, align 8
  %26 = load i32** %25, align 8
  %27 = load i32* %26, align 4
  store i32 %27, i32* %n1, align 4
  %28 = load i32* %m1, align 4
  %29 = load i32* %n1, align 4
  %30 = mul i32 %28, %29
  %31 = load i32* %result, align 4
  %32 = sub i32 %31, %30
  store i32 %32, i32* %result, align 4
  %33 = load i32* %count, align 4
  %34 = load i32* %2, align 4
  %35 = icmp slt i32 %33, %34
  br i1 %35, label %36, label %41

; <label>:36       		; preds = %19
  %37 = load i32*** %ptr1, align 8
  %38 = load i32** %p1, align 8
  store i32* %38, i32** %37, align 8
  %39 = load i32** %p2, align 8
  %40 = load i32* @FLAG, align 4
  store i32 %40, i32* %39, align 4
  br label %57

; <label>:41         		; preds = %19
  %42 = load i32*** %ptr1, align 8
  %43 = load i32** %p2, align 8
  store i32* %43, i32** %42, align 8
  %44 = load i32*** %ptr2, align 8
  %45 = load i32** %p2, align 8
  store i32* %45, i32** %44, align 8
  %46 = load i32*** %ptr1, align 8
  %47 = load i32** %46, align 8
  %48 = load i32* %47, align 4
  store i32 %48, i32* %m2, align 4
  %49 = load i32*** %ptr2, align 8
  %50 = load i32** %49, align 8
  %51 = load i32* %50, align 4
  store i32 %51, i32* %n2, align 4
  %52 = load i32* %m2, align 4
  %53 = load i32* %n2, align 4
  %54 = add i32 %52, %53
  %55 = load i32* %result, align 4
  %56 = add i32 %55, %54
  store i32 %56, i32* %result, align 4
  br label %57

; <label>:57    		; preds = %41, %36
  %58 = load i32*** %ptr1, align 8
  %59 = load i32** %58, align 8
  %60 = load i32* %59, align 4
  store i32 %60, i32* %m3, align 4
  %61 = load i32*** %ptr2, align 8
  %62 = load i32** %61, align 8
  %63 = load i32* %62, align 4
  store i32 %63, i32* %n3, align 4
  %64 = load i32* %m3, align 4
  %65 = load i32* %n3, align 4
  %66 = add i32 %64, %65
  %67 = load i32* %result, align 4
  %68 = add i32 %67, %66
  store i32 %68, i32* %result, align 4
  %69 = load i32*** %ptr1, align 8
  %70 = load i32** %3, align 8
  store i32* %70, i32** %69, align 8
  %71 = load i32*** %ptr1, align 8
  %72 = load i32** %71, align 8
  %73 = load i32* %72, align 4
  store i32 %73, i32* %m4, align 4
  %74 = load i32*** %ptr2, align 8
  %75 = load i32** %74, align 8
  %76 = load i32* %75, align 4
  store i32 %76, i32* %n4, align 4
  %77 = load i32* %m4, align 4
  %78 = load i32* %n4, align 4
  %79 = add i32 %77, %78
  %80 = load i32* %result, align 4
  %81 = sub i32 %80, %79
  store i32 %81, i32* %result, align 4
  br label %15

; <label>:82     		; preds = %15
  %83 = load i32* %result, align 4
  ret i32 %83
}
