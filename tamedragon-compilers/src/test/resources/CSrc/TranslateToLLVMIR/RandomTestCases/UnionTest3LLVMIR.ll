%0 = type { double, [8 x i8] }
%union.def = type { [2 x [2 x i32]] }

@main.ud1 = internal constant %0 { double 1.000000e+00, [8 x i8] undef }, align 4

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %ud1 = alloca %union.def, align 4
  store i32 0, i32* %1
  %2 = bitcast %union.def* %ud1 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i32(i8* %2, i8* bitcast (%0* @main.ud1 to i8*), i32 16, i32 4, i1 false)
  ret i32 0
}

declare void @llvm.memcpy.p0i8.p0i8.i32(i8* nocapture, i8* nocapture, i32, i32, i1) nounwind