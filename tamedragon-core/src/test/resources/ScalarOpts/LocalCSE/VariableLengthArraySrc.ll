define void @foo(i32 %size) nounwind {
  %1 = call i8* @llvm.stacksave()
  %2 = mul i32 4, %size
  %3 = alloca i8, i32 %2, align 1
  %4 = bitcast i8* %3 to i32*
  %5 = call i8* @llvm.stacksave()
  %6 = mul i32 1, %size
  %7 = mul i32 4, %size
  %8 = alloca i8, i32 %7, align 1
  %9 = bitcast i8* %8 to i32*
  call void @llvm.stackrestore(i8* %5)
  %10 = mul i32 4, %size
  %11 = alloca i8, i32 %10, align 1
  %12 = bitcast i8* %11 to float*
  call void @llvm.stackrestore(i8* %1)
  ret void
}

declare i8* @llvm.stacksave() nounwind

declare void @llvm.stackrestore(i8*) nounwind
