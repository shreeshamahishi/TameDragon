%struct.inner = type { double, double }
%struct.test4 = type { i32, %struct.inner, i8* }

@.str = private unnamed_addr constant [14 x i8] c"hello complex\00", align 1
@foo.inner_inst = internal constant %struct.test4 { i32 0, %struct.inner { double 4.100000e+00, double 3.200000e+00 }, i8* getelementptr inbounds [14 x i8], ([14 x i8]* @.str, i32 0, i32 0) }, align 8

define void @foo() nounwind {
  %inner_inst = alloca %struct.test4, align 8
  %1 = bitcast %struct.test4* %inner_inst to i8*
  %2 = bitcast %struct.test4* @foo.inner_inst to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %1, i8* %2, i64 24, i32 8, i1 0)
  ret void
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
