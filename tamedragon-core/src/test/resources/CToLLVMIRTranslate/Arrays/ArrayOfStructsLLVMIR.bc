%struct.key = type { i32, i32 }

@foo.keytab1 = internal constant [3 x %struct.key] [%struct.key { i32 1, i32 10 }, %struct.key { i32 11, i32 20 }, %struct.key { i32 12, i32 30 }], align 4

define void @foo() nounwind {
  %keytab1 = alloca [3 x %struct.key], align 4
  %1 = bitcast [3 x %struct.key]* %keytab1 to i8*
  %2 = bitcast [3 x %struct.key]* @foo.keytab1 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %1, i8* %2, i64 24, i32 4, i1 false)
  ret void
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind