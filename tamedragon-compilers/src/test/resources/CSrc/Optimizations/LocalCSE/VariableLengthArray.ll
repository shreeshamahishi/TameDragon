define void @foo(i32 %size) nounwind {
  %1 = call i8* @llvm.stacksave()
  %2 = mul i32 4, %size
  %3 = alloca i8, i32 %2, align 1
  %4 = call i8* @llvm.stacksave()
  %5 = alloca i8, i32 %2, align 1
  call void @llvm.stackrestore(i8* %4)
  %6 = alloca i8, i32 %2, align 1
  call void @llvm.stackrestore(i8* %1)
  ret void
}
