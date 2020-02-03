; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Core\TestData\TailCallOptimizations\TailRecursiveCallElimination3Src.bc'

define void @printnum(i32 %begin) nounwind {
  br label %tailrecurse

tailrecurse:                                      ; preds = %2, %0
  %begin.tr = phi i32 [ %begin, %0 ], [ %3, %2 ]
  %1 = icmp slt i32 %begin.tr, 9
  br i1 %1, label %2, label %4

; <label>:2                                       ; preds = %tailrecurse
  %3 = add i32 %begin.tr, 1
  br label %tailrecurse

; <label>:4                                       ; preds = %tailrecurse
  ret void
}
