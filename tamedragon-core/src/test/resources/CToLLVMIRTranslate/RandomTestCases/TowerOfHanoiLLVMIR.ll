@.str = private unnamed_addr constant [35 x i8] c"\0AMove disk 1 from peg %c to peg %c\00", align 16
@.str1 = private unnamed_addr constant [36 x i8] c"\0AMove disk %d from peg %c to peg %c\00", align 16
@.str2 = private unnamed_addr constant [29 x i8] c"Enter the number of disks : \00", align 16
@.str3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str4 = private unnamed_addr constant [42 x i8] c"The Tower of Hanoi involves the moves :\0A\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @towers(i32 %n, i8 %frompeg, i8 %topeg, i8 %auxpeg) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i8, align 1
  %3 = alloca i8, align 1
  %4 = alloca i8, align 1
  store i32 %n, i32* %1, align 4
  store i8 %frompeg, i8* %2, align 1
  store i8 %topeg, i8* %3, align 1
  store i8 %auxpeg, i8* %4, align 1
  %5 = load i32, i32* %1, align 4
  %6 = icmp eq i32 %5, 1
  br i1 %6, label %7, label %12

; <label>:7                                            		; preds = %0
  %8 = getelementptr inbounds [35 x i8], [35 x i8]* @.str, i32 0, i32 0
  %9 = load i8, i8* %2, align 1
  %10 = load i8, i8* %3, align 1
  %11 = call i32 (i8*, ...)* @printf(i8* %8, i8 %9, i8 %10)
  br label %28

; <label>:12                        		; preds = %0
  %13 = load i32, i32* %1, align 4
  %14 = sub i32 %13, 1
  %15 = load i8, i8* %2, align 1
  %16 = load i8, i8* %4, align 1
  %17 = load i8, i8* %3, align 1
  call void @towers(i32 %14, i8 %15, i8 %16, i8 %17)
  %18 = getelementptr inbounds [36 x i8], [36 x i8]* @.str1, i32 0, i32 0
  %19 = load i32, i32* %1, align 4
  %20 = load i8, i8* %2, align 1
  %21 = load i8, i8* %3, align 1
  %22 = call i32 (i8*, ...)* @printf(i8* %18, i32 %19, i8 %20, i8 %21)
  %23 = load i32, i32* %1, align 4
  %24 = sub i32 %23, 1
  %25 = load i8, i8* %4, align 1
  %26 = load i8, i8* %3, align 1
  %27 = load i8, i8* %2, align 1
  call void @towers(i32 %24, i8 %25, i8 %26, i8 %27)
  br label %28

; <label>:28		; preds = %7, %12
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %n = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [29 x i8], [29 x i8]* @.str2, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  %6 = getelementptr inbounds [42 x i8], [42 x i8]* @.str4, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  %8 = load i32, i32* %n, align 4
  call void @towers(i32 %8, i8 65, i8 67, i8 66)
  ret i32 0
}
