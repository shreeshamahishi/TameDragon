define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %p4, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  br i1 %1, label %split1, label %split2

; <label>:split1        		; preds = %0
  %3 = icmp slt i32 %flag, 6
  br i1 %3, label %split3, label %split4

; <label>:split3		; preds = %split1
  br label %10

; <label>:split4		; preds = %split1
  br label %25

; <label>:split2        		; preds = %0
  %7 = icmp slt i32 %flag, 6
  br i1 %7, label %split5, label %split6

; <label>:split5		; preds = %split2
  br label %48

; <label>:split6		; preds = %split2
  br label %57

; <label>:10   		; preds = %split3, %23
  %i.0 = phi i32 [ 0, %0 ], [ %24, %23 ]
  %.03 = phi i32 [ %p4, %0 ], [ %.2, %23 ]
  %.02 = phi i32 [ %p3, %0 ], [ %22, %23 ]
  %.01 = phi i32 [ %p2, %0 ], [ %.1, %23 ]
  %.0 = phi i32 [ %p1, %0 ], [ %13, %23 ]
  %11 = icmp slt i32 %i.0, 10
  br i1 %11, label %12, label %94

; <label>:12     		; preds = %10
  %13 = add i32 %.0, 1
  br i1 true, label %14, label %21

; <label>:14     		; preds = %12
  %15 = add i32 %.01, 1
  br i1 true, label %16, label %18

; <label>:16		; preds = %14
  %17 = add i32 %.03, 1
  br label %20

; <label>:18		; preds = %14
  %19 = sub i32 %.03, 1
  br label %20

; <label>:20         		; preds = %18, %16
  %.14 = phi i32 [ %17, %16 ], [ %19, %18 ]
  br label %21

; <label>:21          		; preds = %20, %12
  %.2 = phi i32 [ %.14, %20 ], [ %.03, %12 ]
  %.1 = phi i32 [ %15, %20 ], [ %.01, %12 ]
  %22 = add i32 %.02, 1
  br label %23

; <label>:23		; preds = %21
  %24 = add i32 %i.0, 1
  br label %10

; <label>:25   		; preds = %32, %split4
  %26 = phi i32 [ 0, %0 ], [ %33, %32 ]
  %27 = phi i32 [ %p4, %0 ], [ %35, %32 ]
  %28 = phi i32 [ %p3, %0 ], [ %37, %32 ]
  %29 = phi i32 [ %p2, %0 ], [ %36, %32 ]
  %30 = phi i32 [ %p1, %0 ], [ %41, %32 ]
  %31 = icmp slt i32 %26, 10
  br i1 %31, label %40, label %94

; <label>:32		; preds = %34
  %33 = add i32 %26, 1
  br label %25

; <label>:34        		; preds = %38, %40
  %35 = phi i32 [ %39, %38 ], [ %27, %40 ]
  %36 = phi i32 [ %47, %38 ], [ %29, %40 ]
  %37 = add i32 %28, 1
  br label %32

; <label>:38        		; preds = %44, %42
  %39 = phi i32 [ %45, %44 ], [ %43, %42 ]
  br label %34

; <label>:40     		; preds = %25
  %41 = add i32 %30, 1
  br i1 true, label %46, label %34

; <label>:42		; preds = %46
  %43 = sub i32 %27, 1
  br label %38

; <label>:44		; preds = %46
  %45 = add i32 %27, 1
  br label %38

; <label>:46      		; preds = %40
  %47 = add i32 %29, 1
  br i1 false, label %44, label %42

; <label>:48   		; preds = %split5, %55
  %49 = phi i32 [ 0, %0 ], [ %56, %55 ]
  %50 = phi i32 [ %p4, %0 ], [ %81, %55 ]
  %51 = phi i32 [ %p3, %0 ], [ %83, %55 ]
  %52 = phi i32 [ %p2, %0 ], [ %82, %55 ]
  %53 = phi i32 [ %p1, %0 ], [ %87, %55 ]
  %54 = icmp slt i32 %49, 10
  br i1 %54, label %86, label %94

; <label>:55		; preds = %80
  %56 = add i32 %49, 1
  br label %48

; <label>:57   		; preds = %64, %split6
  %58 = phi i32 [ 0, %0 ], [ %65, %64 ]
  %59 = phi i32 [ %p4, %0 ], [ %67, %64 ]
  %60 = phi i32 [ %p3, %0 ], [ %69, %64 ]
  %61 = phi i32 [ %p2, %0 ], [ %68, %64 ]
  %62 = phi i32 [ %p1, %0 ], [ %73, %64 ]
  %63 = icmp slt i32 %58, 10
  br i1 %63, label %72, label %94

; <label>:64		; preds = %66
  %65 = add i32 %58, 1
  br label %57

; <label>:66        		; preds = %72, %70
  %67 = phi i32 [ %71, %70 ], [ %59, %72 ]
  %68 = phi i32 [ %79, %70 ], [ %61, %72 ]
  %69 = add i32 %60, 1
  br label %64

; <label>:70        		; preds = %74, %76
  %71 = phi i32 [ %75, %74 ], [ %77, %76 ]
  br label %66

; <label>:72      		; preds = %57
  %73 = add i32 %62, 1
  br i1 false, label %78, label %66

; <label>:74		; preds = %78
  %75 = add i32 %59, 1
  br label %70

; <label>:76		; preds = %78
  %77 = sub i32 %59, 1
  br label %70

; <label>:78      		; preds = %72
  %79 = add i32 %61, 1
  br i1 false, label %74, label %76

; <label>:80        		; preds = %84, %86
  %81 = phi i32 [ %85, %84 ], [ %50, %86 ]
  %82 = phi i32 [ %93, %84 ], [ %52, %86 ]
  %83 = add i32 %51, 1
  br label %55

; <label>:84        		; preds = %90, %88
  %85 = phi i32 [ %91, %90 ], [ %89, %88 ]
  br label %80

; <label>:86      		; preds = %48
  %87 = add i32 %53, 1
  br i1 false, label %92, label %80

; <label>:88		; preds = %92
  %89 = sub i32 %50, 1
  br label %84

; <label>:90		; preds = %92
  %91 = add i32 %50, 1
  br label %84

; <label>:92     		; preds = %86
  %93 = add i32 %52, 1
  br i1 true, label %90, label %88

; <label>:94		; preds = %57, %25, %48, %10
  ret void
}