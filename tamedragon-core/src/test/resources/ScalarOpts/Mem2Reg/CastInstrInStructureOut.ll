%struct.Student = type { i32, double }

define i32 @fun(i32 %x) nounwind {
  %a = alloca %struct.Student, align 8
  %b = alloca %struct.Student, align 8
  %c = alloca %struct.Student, align 8
  %1 = getelementptr inbounds %struct.Student, %struct.Student* %a, i32 0, i32 0
  store i32 20, i32* %1, align 4
  %2 = getelementptr inbounds %struct.Student, %struct.Student* %a, i32 0, i32 1
  store double 5.090000e+01, double* %2, align 8
  %3 = bitcast %struct.Student* %b to i8*
  %4 = bitcast %struct.Student* %a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %3, i8* %4, i64 16, i32 8, i1 false)
  %5 = icmp eq i32 %x, 10
  br i1 %5, label %6, label %9

; <label>:6                                       ; preds = %0
  %7 = bitcast %struct.Student* %c to i8*
  %8 = bitcast %struct.Student* %a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %7, i8* %8, i64 16, i32 8, i1 false)
  br label %12

; <label>:9                                       ; preds = %0
  %10 = bitcast %struct.Student* %c to i8*
  %11 = bitcast %struct.Student* %b to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %10, i8* %11, i64 16, i32 8, i1 false)
  br label %12

; <label>:12                                      ; preds = %9, %6
  %13 = getelementptr inbounds %struct.Student, %struct.Student* %c, i32 0, i32 0
  %14 = load i32, i32* %13, align 4
  ret i32 %14
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
