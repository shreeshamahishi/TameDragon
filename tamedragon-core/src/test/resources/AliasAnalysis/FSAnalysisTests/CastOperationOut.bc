; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Core\TestData\AliasAnalysis\FSAnalysisTests\CastOperationSrc.bc'
target datalayout = "e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

define void @foo() nounwind {
  %C = alloca [2 x i8], align 1
  %D = alloca [2 x i8], align 1
  %A = alloca [10 x i8], align 1
  br label %1

; <label>:1                                       ; preds = %11, %0
  %i.0 = phi i32 [ %12, %11 ], [ 0, %0 ]
  %2 = icmp ne i32 %i.0, 10
  br i1 %2, label %3, label %13

; <label>:3                                       ; preds = %1
  %4 = getelementptr inbounds [10 x i8]* %A, i32 0, i32 %i.0
  %5 = getelementptr inbounds [2 x i8]* %C, i32 0, i32 0
  %6 = load i8* %4, align 1
  store i8 %6, i8* %5, align 1
  %7 = sub i32 9, %i.0
  %8 = getelementptr inbounds [10 x i8]* %A, i32 0, i32 %7
  %9 = getelementptr inbounds [2 x i8]* %C, i32 0, i32 1
  %10 = load i8* %8, align 1
  store i8 %10, i8* %9, align 1
  br label %11

; <label>:11                                      ; preds = %3
  %12 = add i32 %i.0, 1
  br label %1

; <label>:13                                      ; preds = %1
  br label %14

; <label>:14                                      ; preds = %27, %13
  %i.1 = phi i32 [ %28, %27 ], [ 0, %13 ]
  %15 = icmp ne i32 %i.1, 10
  br i1 %15, label %16, label %29

; <label>:16                                      ; preds = %14
  %17 = getelementptr inbounds [10 x i8]* %A, i32 0, i32 %i.1
  %18 = getelementptr inbounds [2 x i8]* %D, i32 0, i32 0
  %19 = bitcast i8* %18 to i16*
  %20 = getelementptr inbounds i16* %19, i32 0
  %21 = load i8* %17, align 1
  %22 = sext i8 %21 to i16
  store i16 %22, i16* %20, align 2
  %23 = sub i32 9, %i.1
  %24 = getelementptr inbounds [10 x i8]* %A, i32 0, i32 %23
  %25 = getelementptr inbounds [2 x i8]* %D, i32 0, i32 1
  %26 = load i8* %24, align 1
  store i8 %26, i8* %25, align 1
  br label %27

; <label>:27                                      ; preds = %16
  %28 = add i32 %i.1, 1
  br label %14

; <label>:29                                      ; preds = %14
  ret void
}
