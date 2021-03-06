@heap = common global [1000000 x i32] zeroinitializer, align 16
@heapSize = common global i32 0, align 4

define void @Insert(i32 %element) nounwind {
  %1 = load i32, i32* @heapSize, align 4
  %2 = add i32 %1, 1
  store i32 %2, i32* @heapSize, align 4
  %3 = load i32, i32* @heapSize, align 4
  %4 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %3
  store i32 %element, i32* %4, align 4
  %5 = load i32, i32* @heapSize, align 4
  br label %6

; <label>:6                                   		; preds = %11, %0
  %now.0 = phi i32 [ %16, %11 ], [ %5, %0 ]
  %7 = sdiv i32 %now.0, 2
  %8 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %7
  %9 = load i32, i32* %8, align 4
  %10 = icmp sgt i32 %9, %element
  br i1 %10, label %11, label %17

; <label>:11      		; preds = %6
  %12 = sdiv i32 %now.0, 2
  %13 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %12
  %14 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %now.0
  %15 = load i32, i32* %13, align 4
  store i32 %15, i32* %14, align 4
  %16 = sdiv i32 %now.0, 2
  br label %6

; <label>:17                                            		; preds = %6
  %18 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %now.0
  store i32 %element, i32* %18, align 4
  ret void
}