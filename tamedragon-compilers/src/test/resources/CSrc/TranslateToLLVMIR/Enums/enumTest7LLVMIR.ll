%enum.BOOLEAN = type i32

@end_flag = common global i32 0, align 4
@match_flag = common global i32 0, align 4
@.str = private unnamed_addr constant [7 x i8] c"hello\0A\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = load i32, i32* @match_flag, align 4
  %3 = icmp eq i32 %2, 0
  br i1 %3, label %4, label %7

; <label>:4                                          		; preds = %0
  %5 = getelementptr inbounds [7 x i8], [7 x i8]* @.str, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @printf(i8* %5)
  br label %7

; <label>:7       		; preds = %0, %4
  store i32 1, i32* @end_flag, align 4
  %8 = load i32, i32* %1, align 4
  ret i32 %8
}
