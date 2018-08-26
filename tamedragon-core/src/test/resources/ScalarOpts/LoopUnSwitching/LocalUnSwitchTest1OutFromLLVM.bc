; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Core\TestData\ScalarOpts\LoopUnSwitching\LocalUnSwitchTest1Src.bc'

define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  br i1 %1, label %.split.us, label %..split_crit_edge

..split_crit_edge:                                ; preds = %0
  br label %.split

.split.us:                                        ; preds = %0
  br label %2

; <label>:2                                       ; preds = %4, %.split.us
  %i.0.us = phi i32 [ %5, %4 ], [ 0, %.split.us ]
  %.02.us = phi i32 [ %7, %4 ], [ %p3, %.split.us ]
  %.01.us = phi i32 [ %.1.us, %4 ], [ %p2, %.split.us ]
  %.0.us = phi i32 [ %9, %4 ], [ %p1, %.split.us ]
  %3 = icmp slt i32 %i.0.us, 10
  br i1 %3, label %8, label %.us-lcssa.us

; <label>:4                                       ; preds = %6
  %5 = add i32 %i.0.us, 1
  br label %2

; <label>:6                                       ; preds = %10, %8
  %.1.us = phi i32 [ %11, %10 ], [ %.01.us, %8 ]
  %7 = add i32 %.02.us, 1
  br label %4

; <label>:8                                       ; preds = %2
  %9 = add i32 %.0.us, 1
  br i1 true, label %10, label %6

; <label>:10                                      ; preds = %8
  %11 = add i32 %.01.us, 1
  br label %6

.us-lcssa.us:                                     ; preds = %2
  br label %22

.split:                                           ; preds = %..split_crit_edge
  br label %12

; <label>:12                                      ; preds = %20, %.split
  %i.0 = phi i32 [ %21, %20 ], [ 0, %.split ]
  %.02 = phi i32 [ %19, %20 ], [ %p3, %.split ]
  %.01 = phi i32 [ %.1, %20 ], [ %p2, %.split ]
  %.0 = phi i32 [ %15, %20 ], [ %p1, %.split ]
  %13 = icmp slt i32 %i.0, 10
  br i1 %13, label %14, label %.us-lcssa

; <label>:14                                      ; preds = %12
  %15 = add i32 %.0, 1
  br i1 false, label %16, label %18

; <label>:16                                      ; preds = %14
  %17 = add i32 %.01, 1
  br label %18

; <label>:18                                      ; preds = %16, %14
  %.1 = phi i32 [ %17, %16 ], [ %.01, %14 ]
  %19 = add i32 %.02, 1
  br label %20

; <label>:20                                      ; preds = %18
  %21 = add i32 %i.0, 1
  br label %12

.us-lcssa:                                        ; preds = %12
  br label %22

; <label>:22                                      ; preds = %.us-lcssa.us, %.us-lcssa
  ret void
}
