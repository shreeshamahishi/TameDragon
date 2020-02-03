define void @foo(i32 %size) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  store i32 %size, i32* %1, align 4
  %4 = call i8* @llvm.stacksave()
  store i8* %4, i8** %2, align 8
  %5 = load i32, i32* %1, align 4
  %6 = mul i32 4, %5
  %7 = alloca i8, i32 %6, align 1
  %8 = bitcast i8* %7 to i32*
  %9 = call i8* @llvm.stacksave()
  store i8* %9, i8** %3, align 8
  %10 = load i32, i32* %1, align 4
  %11 = mul i32 1, %10
  %12 = alloca i8, i32 %11, align 1
  %13 = load i32, i32* %1, align 4
  %14 = mul i32 4, %13
  %15 = alloca i8, i32 %14, align 1
  %16 = bitcast i8* %15 to i32*
  %17 = load i8*, i8** %3, align 8
  call void @llvm.stackrestore(i8* %17)
  %18 = load i32, i32* %1, align 4
  %19 = mul i32 4, %18
  %20 = alloca i8, i32 %19, align 1
  %21 = bitcast i8* %20 to float*
  %22 = load i8*, i8** %2, align 8
  call void @llvm.stackrestore(i8* %22)
  ret void
}

declare i8* @llvm.stacksave() nounwind

declare void @llvm.stackrestore(i8*) nounwind
