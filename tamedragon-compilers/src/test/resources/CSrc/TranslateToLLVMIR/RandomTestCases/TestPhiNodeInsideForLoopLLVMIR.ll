@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %4 = load i32, i32* %1, align 4
  %5 = icmp ne i32 %4, 0
  br i1 %5, label %6, label %9

; <label>:6      		; preds = %0
  %7 = load i32, i32* %2, align 4
  %8 = icmp ne i32 %7, 0
  br label %9

; <label>:9         		; preds = %0, %6
  %10 = phi i1 [ false, %0 ], [ %8, %6 ]
  %11 = zext i1 %10 to i32
  %12 = icmp ne i32 %11, 0
  br i1 %12, label %16, label %13

; <label>:13      		; preds = %9
  %14 = load i32, i32* %1, align 4
  %15 = icmp ne i32 %14, 0
  br label %16

; <label>:16        		; preds = %9, %13
  %17 = phi i1 [ true, %9 ], [ %15, %13 ]
  %18 = zext i1 %17 to i32
  %19 = icmp ne i32 %18, 0
  br i1 %19, label %23, label %20

; <label>:20     		; preds = %16
  %21 = load i32, i32* %2, align 4
  %22 = icmp ne i32 %21, 0
  br label %23

; <label>:23        		; preds = %16, %20
  %24 = phi i1 [ true, %16 ], [ %22, %20 ]
  br i1 %24, label %25, label %26

; <label>:25  		; preds = %23
  store i32 0, i32* %3, align 4
  br label %27

; <label>:26  		; preds = %23
  store i32 1, i32* %3, align 4
  br label %27

; <label>:27		; preds = %25, %26
  %28 = load i32, i32* %3, align 4
  ret i32 %28
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %retVal = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = call i32 @foo(i32 1, i32 2)
  store i32 %2, i32* %retVal, align 4
  %3 = getelementptr inbounds [3 x i8], [3 x i8]* @.str, i32 0, i32 0
  %4 = load i32, i32* %retVal, align 4
  %5 = call i32 (i8*, ...)* @printf(i8* %3, i32 %4)
  %6 = load i32, i32* %retVal, align 4
  ret i32 %6
}
