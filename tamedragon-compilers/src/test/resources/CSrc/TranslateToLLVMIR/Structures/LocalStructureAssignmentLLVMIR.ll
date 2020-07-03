%struct.Student = type { i32, double }

define i32 @fun(i32 %x) nounwind {
  %1 = alloca i32, align 4
  %a = alloca %struct.Student, align 8
  %b = alloca %struct.Student, align 8
  %c = alloca %struct.Student, align 8
  store i32 %x, i32* %1, align 4
  %2 = getelementptr inbounds %struct.Student, %struct.Student* %a, i32 0, i32 0
  store i32 20, i32* %2, align 4
  %3 = getelementptr inbounds %struct.Student, %struct.Student* %a, i32 0, i32 1
  store double 5.000000e+01, double* %3, align 8
  %4 = getelementptr inbounds %struct.Student, %struct.Student* %b, i32 0, i32 0
  store i32 20, i32* %4, align 4
  %5 = getelementptr inbounds %struct.Student, %struct.Student* %b, i32 0, i32 1
  store double 5.000000e+01, double* %5, align 8
  %6 = load i32, i32* %1, align 4
  %7 = icmp eq i32 %6, 10
  br i1 %7, label %8, label %11

; <label>:8                                                     		; preds = %0
  %9 = bitcast %struct.Student* %c to i8*
  %10 = bitcast %struct.Student* %a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %9, i8* %10, i64 12, i32 8, i1 0)
  br label %14

; <label>:11                                                     		; preds = %0
  %12 = bitcast %struct.Student* %c to i8*
  %13 = bitcast %struct.Student* %b to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %12, i8* %13, i64 12, i32 8, i1 0)
  br label %14

; <label>:14                                                		; preds = %8, %11
  %15 = getelementptr inbounds %struct.Student, %struct.Student* %c, i32 0, i32 0
  %16 = load i32, i32* %15, align 4
  ret i32 %16
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
