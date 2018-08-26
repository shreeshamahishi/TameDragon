@MAX = global i32 300, align 4
@split1 = external global i32
@split2 = external global i32

define i32 @foo(i32 %m, i32* %arr) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %p1 = alloca i32*, align 8
  %p2 = alloca i32*, align 8
  %p3 = alloca i32*, align 8
  %ptr = alloca i32*, align 8
  %count = alloca i32, align 4
  %result = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32* %arr, i32** %2, align 8
  store i32 0, i32* %count, align 4
  store i32 65, i32* %result, align 4
  %4 = load i32** %2, align 8
  store i32* %4, i32** %p1, align 8
  %5 = load i32** %2, align 8
  store i32* %5, i32** %p2, align 8
  %6 = load i32** %2, align 8
  store i32* %6, i32** %p3, align 8
  br label %7

; <label>:7 		; preds = %37, %0
  %8 = load i32* %count, align 4
  %9 = load i32* %1, align 4
  %10 = icmp slt i32 %8, %9
  br i1 %10, label %11, label %53

; <label>:11     		; preds = %7
  %12 = load i32** %p1, align 8
  %13 = getelementptr inbounds i32* %12, i32 2
  store i32* %13, i32** %p1, align 8
  %14 = load i32** %p2, align 8
  %15 = getelementptr inbounds i32* %14, i32 1
  store i32* %15, i32** %p2, align 8
  %16 = load i32** %p3, align 8
  %17 = getelementptr inbounds i32* %16, i32 1
  store i32* %17, i32** %p3, align 8
  %18 = load i32* %count, align 4
  %19 = load i32* @split1, align 4
  %20 = icmp slt i32 %18, %19
  br i1 %20, label %21, label %23

; <label>:21        		; preds = %11
  %22 = load i32** %p1, align 8
  store i32* %22, i32** %ptr, align 8
  br label %23

; <label>:23		; preds = %21, %11
  %24 = load i32* %count, align 4
  %25 = load i32* @split1, align 4
  %26 = icmp sge i32 %24, %25
  br i1 %26, label %27, label %31

; <label>:27     		; preds = %23
  %28 = load i32* %count, align 4
  %29 = load i32* @split2, align 4
  %30 = icmp slt i32 %28, %29
  br label %31

; <label>:31         		; preds = %27, %23
  %32 = phi i1 [ false, %23 ], [ %30, %27 ]
  br i1 %32, label %33, label %35

; <label>:33        		; preds = %31
  %34 = load i32** %p2, align 8
  store i32* %34, i32** %ptr, align 8
  br label %37

; <label>:35        		; preds = %31
  %36 = load i32** %p3, align 8
  store i32* %36, i32** %ptr, align 8
  br label %37

; <label>:37   		; preds = %35, %33
  %38 = load i32** %p3, align 8
  %39 = load i32** %p2, align 8
  %40 = load i32** %ptr, align 8
  %41 = load i32** %p1, align 8
  %42 = load i32* %40, align 4
  %43 = load i32* %41, align 4
  %44 = add i32 %42, %43
  %45 = load i32* %39, align 4
  %46 = add i32 %44, %45
  %47 = load i32* %38, align 4
  %48 = add i32 %46, %47
  %49 = load i32* %result, align 4
  %50 = add i32 %49, %48
  store i32 %50, i32* %result, align 4
  %51 = load i32* %count, align 4
  %52 = add i32 %51, 1
  store i32 %52, i32* %count, align 4
  br label %7

; <label>:53 		; preds = %7
  %54 = load i32* %3, align 4
  ret i32 %54
}