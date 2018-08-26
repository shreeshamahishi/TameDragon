define void @foo(i32 %p1, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  br i1 %1, label %split1, label %split2

; <label>:split1        		; preds = %0
  %3 = icmp slt i32 %flag, 2
  br i1 %3, label %split3, label %split4

; <label>:split3		; preds = %split1
  br label %10

; <label>:split4		; preds = %split1
  br label %23

; <label>:split2        		; preds = %0
  %7 = icmp slt i32 %flag, 2
  br i1 %7, label %split5, label %split6

; <label>:split5		; preds = %split2
  br label %40

; <label>:split6		; preds = %split2
  br label %47

; <label>:10      		; preds = %split3, %21
  %i.0 = phi i32 [ %22, %21 ], [ undef, %0 ]
  %.0 = phi i32 [ %.2, %21 ], [ %p1, %0 ]
  %11 = icmp slt i32 %i.0, 10
  br i1 %11, label %12, label %74

; <label>:12     		; preds = %10
  br i1 true, label %13, label %15

; <label>:13		; preds = %12
  %14 = add i32 %.0, 1
  br label %21

; <label>:15     		; preds = %12
  br i1 true, label %16, label %18

; <label>:16		; preds = %15
  %17 = sub i32 %.0, 1
  br label %20

; <label>:18		; preds = %15
  %19 = add i32 %.0, 2
  br label %20

; <label>:20        		; preds = %18, %16
  %.1 = phi i32 [ %19, %18 ], [ %17, %16 ]
  br label %21

; <label>:21        		; preds = %20, %13
  %.2 = phi i32 [ %.1, %20 ], [ %14, %13 ]
  %22 = add i32 %i.0, 1
  br label %10

; <label>:23     		; preds = %27, %split4
  %24 = phi i32 [ %29, %27 ], [ undef, %0 ]
  %25 = phi i32 [ %28, %27 ], [ %p1, %0 ]
  %26 = icmp slt i32 %24, 10
  br i1 %26, label %34, label %74

; <label>:27        		; preds = %30, %32
  %28 = phi i32 [ %31, %30 ], [ %33, %32 ]
  %29 = add i32 %24, 1
  br label %23

; <label>:30        		; preds = %35, %37
  %31 = phi i32 [ %36, %35 ], [ %38, %37 ]
  br label %27

; <label>:32		; preds = %34
  %33 = add i32 %25, 1
  br label %27

; <label>:34     		; preds = %23
  br i1 true, label %32, label %39

; <label>:35		; preds = %39
  %36 = add i32 %25, 2
  br label %30

; <label>:37		; preds = %39
  %38 = sub i32 %25, 1
  br label %30

; <label>:39      		; preds = %34
  br i1 false, label %37, label %35

; <label>:40     		; preds = %split5, %44
  %41 = phi i32 [ %46, %44 ], [ undef, %0 ]
  %42 = phi i32 [ %45, %44 ], [ %p1, %0 ]
  %43 = icmp slt i32 %41, 10
  br i1 %43, label %68, label %74

; <label>:44        		; preds = %64, %66
  %45 = phi i32 [ %65, %64 ], [ %67, %66 ]
  %46 = add i32 %41, 1
  br label %40

; <label>:47     		; preds = %51, %split6
  %48 = phi i32 [ %53, %51 ], [ undef, %0 ]
  %49 = phi i32 [ %52, %51 ], [ %p1, %0 ]
  %50 = icmp slt i32 %48, 10
  br i1 %50, label %58, label %74

; <label>:51        		; preds = %54, %56
  %52 = phi i32 [ %55, %54 ], [ %57, %56 ]
  %53 = add i32 %48, 1
  br label %47

; <label>:54        		; preds = %59, %61
  %55 = phi i32 [ %62, %61 ], [ %60, %59 ]
  br label %51

; <label>:56		; preds = %58
  %57 = add i32 %49, 1
  br label %51

; <label>:58      		; preds = %47
  br i1 false, label %56, label %63

; <label>:59		; preds = %63
  %60 = sub i32 %49, 1
  br label %54

; <label>:61		; preds = %63
  %62 = add i32 %49, 2
  br label %54

; <label>:63      		; preds = %58
  br i1 false, label %59, label %61

; <label>:64        		; preds = %71, %69
  %65 = phi i32 [ %70, %69 ], [ %72, %71 ]
  br label %44

; <label>:66		; preds = %68
  %67 = add i32 %42, 1
  br label %44

; <label>:68      		; preds = %40
  br i1 false, label %66, label %73

; <label>:69		; preds = %73
  %70 = add i32 %42, 2
  br label %64

; <label>:71		; preds = %73
  %72 = sub i32 %42, 1
  br label %64

; <label>:73     		; preds = %68
  br i1 true, label %71, label %69

; <label>:74		; preds = %47, %23, %40, %10
  ret void
}