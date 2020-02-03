declare noalias i8* @malloc(i64) nounwind

declare void @free(i8*) nounwind

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %S = alloca i32**, align 8
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 0, i32* %i, align 4
  store i32 0, i32* %j, align 4
  %2 = call i8* @malloc(i64 40) nounwind
  %3 = bitcast i8* %2 to i32**
  store i32** %3, i32*** %S, align 8
  br label %4

; <label>:4 		; preds = %0, %25
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 10
  br i1 %6, label %7, label %28

; <label>:7		; preds = %4
  br label %8

; <label>:8 		; preds = %7, %21
  %9 = load i32, i32* %j, align 4
  %10 = icmp slt i32 %9, 10
  br i1 %10, label %11, label %24

; <label>:11                         		; preds = %8
  %12 = load i32, i32* %i, align 4
  %13 = load i32, i32* %j, align 4
  %14 = add i32 %12, %13
  %15 = load i32**, i32*** %S, align 8
  %16 = load i32, i32* %i, align 4
  %17 = getelementptr inbounds i32*, i32** %15, i32 %16
  %18 = load i32*, i32** %17, align 8
  %19 = load i32, i32* %j, align 4
  %20 = getelementptr inbounds i32, i32* %18, i32 %19
  store i32 %14, i32* %20, align 4
  br label %21

; <label>:21     		; preds = %11
  %22 = load i32, i32* %j, align 4
  %23 = add i32 %22, 1
  store i32 %23, i32* %j, align 4
  br label %8

; <label>:24		; preds = %8
  br label %25

; <label>:25     		; preds = %24
  %26 = load i32, i32* %i, align 4
  %27 = add i32 %26, 1
  store i32 %27, i32* %i, align 4
  br label %4

; <label>:28          		; preds = %4
  %29 = load i32**, i32*** %S, align 8
  %30 = bitcast i32** %29 to i8*
  call void @free(i8* %30) nounwind
  %31 = load i32, i32* %1, align 4
  ret i32 %31
}
