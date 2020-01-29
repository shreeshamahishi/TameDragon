@.str = private unnamed_addr constant [12 x i8] c"Hello World\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 0, i32* %i, align 4
  br label %2

; <label>:2  		; preds = %0, %8
  %3 = load i32, i32* %i, align 4
  %4 = icmp slt i32 %3, 10
  br i1 %4, label %5, label %11

; <label>:5                                            		; preds = %2
  %6 = getelementptr inbounds [12 x i8], [12 x i8]* @.str, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  br label %8

; <label>:8      		; preds = %5
  %9 = load i32, i32* %i, align 4
  %10 = add i32 %9, 1
  store i32 %10, i32* %i, align 4
  br label %2

; <label>:11		; preds = %2
  ret i32 0
}
