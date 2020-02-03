@foo.a = internal constant [3 x i32] [i32 1, i32 2, i32 3], align 4

define i32 @foo(i32 %m) nounwind {
  %a = alloca [3 x i32], align 4
  %1 = bitcast [3 x i32]* %a to i8*
  %2 = bitcast [3 x i32]* @foo.a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %1, i8* %2, i64 12, i32 4, i1 false)
  %3 = getelementptr inbounds [3 x i32], [3 x i32]* %a, i32 0, i32 0
  %4 = load i32, i32* %3, align 4
  %5 = getelementptr inbounds [3 x i32], [3 x i32]* %a, i32 0, i32 0
  %6 = load i32, i32* %5, align 4
  %7 = icmp eq i32 %m, 1
  br i1 %7, label %8, label %9

; <label>:8		; preds = %0
  br label %10

; <label>:9		; preds = %0
  br label %10

; <label>:10       		; preds = %9, %8
  %k.0 = phi i32 [ %6, %9 ], [ %4, %8 ]
  ret i32 %k.0
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
