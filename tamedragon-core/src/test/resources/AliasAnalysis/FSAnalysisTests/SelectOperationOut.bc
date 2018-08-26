; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Core\TestData\AliasAnalysis\FSAnalysisTests\SelectOperationSrc.bc'
target datalayout = "e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

define i32 @foo(i1 %flag, i32* %m, i32* %n) nounwind {
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 21, i32* %a, align 4
  store i32 67, i32* %b, align 4
  %X = select i1 %flag, i32* %m, i32* %n
  %Y = select i1 %flag, i32* %m, i32* %a
  %Z = select i1 %flag, i32* %a, i32* %b
  %1 = load i32* %X, align 4
  %2 = load i32* %Y, align 4
  %3 = load i32* %Z, align 4
  %4 = add i32 %1, %2
  %5 = add i32 %4, %3
  ret i32 %3
}
