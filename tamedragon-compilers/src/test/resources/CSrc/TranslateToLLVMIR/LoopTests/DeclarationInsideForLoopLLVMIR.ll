@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 0, i32* %i, align 4
  br label %2

; <label>:2  		; preds = %0, %9
  %3 = load i32, i32* %i, align 4
  %4 = icmp slt i32 %3, 10
  br i1 %4, label %5, label %12

; <label>:5                                          		; preds = %2
  %6 = getelementptr inbounds [4 x i8], [4 x i8]* @.str, i32 0, i32 0
  %7 = load i32, i32* %i, align 4
  %8 = call i32 (i8*, ...)* @printf(i8* %6, i32 %7)
  br label %9

; <label>:9       		; preds = %5
  %10 = load i32, i32* %i, align 4
  %11 = add i32 %10, 1
  store i32 %11, i32* %i, align 4
  br label %2

; <label>:12		; preds = %2
  ret i32 0
}
