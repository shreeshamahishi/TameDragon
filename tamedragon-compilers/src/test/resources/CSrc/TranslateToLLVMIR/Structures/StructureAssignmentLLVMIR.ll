%struct.inner = type { double, double }

@foo.x = internal constant %struct.inner { double 1.000000e+00, double 2.000000e+00 }, align 8

define void @foo() nounwind {
  %x = alloca %struct.inner, align 8
  %1 = bitcast %struct.inner* %x to i8*
  %2 = bitcast %struct.inner* @foo.x to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %1, i8* %2, i64 16, i32 8, i1 false)
  ret void
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
