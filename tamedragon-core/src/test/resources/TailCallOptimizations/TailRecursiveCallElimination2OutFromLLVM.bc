; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Core\TestData\TailCallOptimizations\TailRecursiveCallElimination2Src.bc'

define void @doll(i32 %size) nounwind {
  br label %tailrecurse

tailrecurse:                                      ; preds = %3, %0
  %size.tr = phi i32 [ %size, %0 ], [ %4, %3 ]
  %1 = icmp eq i32 %size.tr, 0
  br i1 %1, label %2, label %3

; <label>:2                                       ; preds = %tailrecurse
  br label %5

; <label>:3                                       ; preds = %tailrecurse
  %4 = sub i32 %size.tr, 1
  br label %tailrecurse

; <label>:5                                       ; preds = %2
  ret void
}
