@i = global i32 0, align 4
@.str = private unnamed_addr constant [8 x i8] c"foo %d\0A\00", align 1

declare i32 @printf(i8*, ...) 

define void @foo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  %2 = getelementptr inbounds [8 x i8], [8 x i8]* @.str, i32 0, i32 0
  %3 = load i32, i32* %1, align 4
  %4 = call i32 (i8*, ...)* @printf(i8* %2, i32 %3)
  %5 = load i32, i32* @i, align 4
  %6 = icmp slt i32 %5, 10
  br i1 %6, label %7, label %10

; <label>:7      		; preds = %0
  %8 = load i32, i32* @i, align 4
  %9 = add i32 %8, 1
  store i32 %9, i32* @i, align 4
  call void @foo(i32 %8)
  br label %10

; <label>:10		; preds = %0, %7
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  call void @foo(i32 -1)
  %2 = load i32, i32* %1, align 4
  ret i32 %2
}
