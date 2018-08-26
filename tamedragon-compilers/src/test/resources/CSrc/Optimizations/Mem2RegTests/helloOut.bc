; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Compilers\TestData\CSrc\Optimizations\Mem2RegTests\helloSrc.bc'

define void @foo(i32 %a, i32 %b) nounwind {
  %f = alloca [10 x i32], align 4
  %g = alloca [10 x i32], align 4
  %1 = add i32 %a, %b
  %2 = mul i32 %a, %1
  %3 = mul i32 %2, %2
  br label %4

; <label>:4                                       ; preds = %17, %0
  %c.0 = phi i32 [ %1, %0 ], [ %7, %17 ]
  %i.0 = phi i32 [ 1, %0 ], [ %16, %17 ]
  %5 = add i32 %a, %b
  %6 = getelementptr inbounds [10 x i32]* %f, i32 0, i32 %i.0
  store i32 %5, i32* %6
  %7 = mul i32 %c.0, 2
  %8 = icmp sgt i32 %7, %2
  br i1 %8, label %9, label %12

; <label>:9                                       ; preds = %4
  %10 = mul i32 %a, %7
  %11 = getelementptr inbounds [10 x i32]* %g, i32 0, i32 %i.0
  store i32 %10, i32* %11
  br label %15

; <label>:12                                      ; preds = %4
  %13 = mul i32 %2, %2
  %14 = getelementptr inbounds [10 x i32]* %g, i32 0, i32 %i.0
  store i32 %13, i32* %14
  br label %15

; <label>:15                                      ; preds = %12, %9
  %16 = add i32 %i.0, 1
  br label %17

; <label>:17                                      ; preds = %15
  %18 = icmp slt i32 %16, 10
  br i1 %18, label %4, label %19

; <label>:19                                      ; preds = %17
  ret void
}
