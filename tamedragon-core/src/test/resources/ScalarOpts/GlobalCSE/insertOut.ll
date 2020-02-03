@heap = common global [1000000 x i32] zeroinitializer, align 16
@heapSize = common global i32 0, align 4

define void @Insert(i32 %element) nounwind {
  %1 = load i32, i32* @heapSize, align 4
  %2 = add i32 %1, 1
  store i32 %2, i32* @heapSize, align 4
  %3 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %2
  store i32 %element, i32* %3, align 4
  %4 = load i32, i32* @heapSize, align 4
  br label %5

; <label>:5                                                    		; preds = %0, %10
  %now.0 = phi i32 [ %6, %10 ], [ %4, %0 ]
  %6 = sdiv i32 %now.0, 2
  %7 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %6
  %8 = load i32, i32* %7, align 4
  %9 = icmp sgt i32 %8, %element
  br i1 %9, label %10, label %12

; <label>:10                                                             		; preds = %5
  %11 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %now.0
  store i32 %8, i32* %11, align 4
  br label %5

; <label>:12                                                             		; preds = %5
  %13 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %now.0
  store i32 %element, i32* %13, align 4
  ret void
}
