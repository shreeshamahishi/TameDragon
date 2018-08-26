;target datalayout="e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

@pglobal = common global i32* null, align 8
@FLAG = common global i32 0, align 4

define i32 @foo(i32 %max_iter, i32 %split, i32* %pArg) nounwind {
  %b = alloca i32, align 4
  store i32 34, i32* %b, align 4
  br label %1

; <label>:1             		; preds = %15, %0
  %p2.0 = phi i32* [ %p2.1, %15 ], [ %b, %0 ]
  %result.0 = phi i32 [ %19, %15 ], [ 0, %0 ]
  %2 = icmp slt i32 0, %max_iter
  br i1 %2, label %3, label %20

; <label>:3        		; preds = %1
  %4 = load i32** @pglobal, align 8
  %5 = load i32* %4, align 4
  %6 = sub i32 %result.0, %5
  %7 = icmp slt i32 0, %split
  br i1 %7, label %8, label %10

; <label>:8        		; preds = %3
  %9 = load i32* @FLAG, align 4
  store i32 %9, i32* %p2.0, align 4
  br label %15

; <label>:10    		; preds = %3
  %11 = load i32* %p2.0, align 4
  %12 = load i32* %p2.0, align 4
  %13 = add i32 %11, %12
  %14 = add i32 %6, %13
  br label %15

; <label>:15               		; preds = %10, %8
  %p2.1 = phi i32* [ %p2.0, %10 ], [ %p2.0, %8 ]
  %p1.0 = phi i32* [ %p2.0, %10 ], [ %4, %8 ]
  %result.1 = phi i32 [ %14, %10 ], [ %6, %8 ]
  %16 = load i32* %p1.0, align 4
  %17 = add i32 %result.1, %16
  %18 = load i32* %pArg, align 4
  %19 = sub i32 %17, %18
  br label %1

; <label>:20		; preds = %1
  ret i32 %result.0
}