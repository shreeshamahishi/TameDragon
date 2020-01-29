@ttime = common global i32 0, align 4
@i = common global i32 0, align 4
@j = common global i32 0, align 4
@temp = common global i32 0, align 4
@.str = private unnamed_addr constant [29 x i8] c"\0A\09 ROUND ROBIN SCHEDULING \0A\0A\00", align 16
@.str1 = private unnamed_addr constant [23 x i8] c"Enter the time slice:\0A\00", align 16
@.str2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str3 = private unnamed_addr constant [42 x i8] c"PROCESS NAME \09 REMAINING TIME\09 TOTAL TIME\00", align 16
@.str4 = private unnamed_addr constant [15 x i8] c"\0A%d\09\09 %d \09\09 %d\00", align 1
@.str5 = private unnamed_addr constant [26 x i8] c"Enter the no. of process:\00", align 16
@.str6 = private unnamed_addr constant [55 x i8] c"Enter the process name and burst time for the process\0A\00", align 16
@.str7 = private unnamed_addr constant [24 x i8] c"Enter the process name:\00", align 16
@.str8 = private unnamed_addr constant [37 x i8] c"Enter burst time for the process %d:\00", align 16
@.str9 = private unnamed_addr constant [28 x i8] c"PROCESS NAME \09\09 BURST TIME\0A\00", align 16
@.str10 = private unnamed_addr constant [10 x i8] c"%d\09\09\09 %d\0A\00", align 1
@.str11 = private unnamed_addr constant [28 x i8] c"PRESS 1.ROUND ROBIN 2.EXIT\0A\00", align 16
@.str12 = private unnamed_addr constant [15 x i8] c"Invalid option\00", align 1
@.str13 = private unnamed_addr constant [36 x i8] c"\0A\0A If you want to continue press 1:\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

declare void @exit(i32) 

define i32 @rrobin(i32* %pname, i32* %btime, i32 %n) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %tslice = alloca i32, align 4
  store i32* %pname, i32** %1, align 8
  store i32* %btime, i32** %2, align 8
  store i32 %n, i32* %3, align 4
  store i32 0, i32* @j, align 4
  %5 = getelementptr inbounds [29 x i8], [29 x i8]* @.str, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @printf(i8* %5)
  %7 = getelementptr inbounds [23 x i8], [23 x i8]* @.str1, i32 0, i32 0
  %8 = call i32 (i8*, ...)* @printf(i8* %7)
  %9 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %10 = call i32 (i8*, ...)* @scanf(i8* %9, i32* %tslice)
  %11 = getelementptr inbounds [42 x i8], [42 x i8]* @.str3, i32 0, i32 0
  %12 = call i32 (i8*, ...)* @printf(i8* %11)
  br label %13

; <label>:13 		; preds = %0, %94
  %14 = load i32, i32* @j, align 4
  %15 = load i32, i32* %3, align 4
  %16 = icmp slt i32 %14, %15
  br i1 %16, label %17, label %95

; <label>:17  		; preds = %13
  store i32 0, i32* @i, align 4
  br label %18

; <label>:18		; preds = %17, %91
  %19 = load i32, i32* @i, align 4
  %20 = load i32, i32* %3, align 4
  %21 = icmp slt i32 %19, %20
  br i1 %21, label %22, label %94

; <label>:22                        		; preds = %18
  %23 = load i32*, i32** %2, align 8
  %24 = load i32, i32* @i, align 4
  %25 = getelementptr inbounds i32, i32* %23, i32 %24
  %26 = load i32, i32* %25, align 4
  %27 = icmp sgt i32 %26, 0
  br i1 %27, label %28, label %90

; <label>:28          		; preds = %22
  %29 = load i32*, i32** %2, align 8
  %30 = load i32, i32* @i, align 4
  %31 = getelementptr inbounds i32, i32* %29, i32 %30
  %32 = load i32, i32* %tslice, align 4
  %33 = load i32, i32* %31, align 4
  %34 = icmp sge i32 %33, %32
  br i1 %34, label %35, label %68

; <label>:35                        		; preds = %28
  %36 = load i32, i32* @ttime, align 4
  %37 = load i32, i32* %tslice, align 4
  %38 = add i32 %36, %37
  store i32 %38, i32* @ttime, align 4
  %39 = load i32*, i32** %2, align 8
  %40 = load i32, i32* @i, align 4
  %41 = getelementptr inbounds i32, i32* %39, i32 %40
  %42 = load i32, i32* %tslice, align 4
  %43 = load i32, i32* %41, align 4
  %44 = sub i32 %43, %42
  %45 = load i32*, i32** %2, align 8
  %46 = load i32, i32* @i, align 4
  %47 = getelementptr inbounds i32, i32* %45, i32 %46
  store i32 %44, i32* %47, align 4
  %48 = load i32*, i32** %2, align 8
  %49 = load i32, i32* @i, align 4
  %50 = getelementptr inbounds i32, i32* %48, i32 %49
  %51 = load i32*, i32** %1, align 8
  %52 = load i32, i32* @i, align 4
  %53 = getelementptr inbounds i32, i32* %51, i32 %52
  %54 = getelementptr inbounds [15 x i8], [15 x i8]* @.str4, i32 0, i32 0
  %55 = load i32, i32* %53, align 4
  %56 = load i32, i32* %50, align 4
  %57 = load i32, i32* @ttime, align 4
  %58 = call i32 (i8*, ...)* @printf(i8* %54, i32 %55, i32 %56, i32 %57)
  %59 = load i32*, i32** %2, align 8
  %60 = load i32, i32* @i, align 4
  %61 = getelementptr inbounds i32, i32* %59, i32 %60
  %62 = load i32, i32* %61, align 4
  %63 = icmp eq i32 %62, 0
  br i1 %63, label %64, label %67

; <label>:64     		; preds = %35
  %65 = load i32, i32* @j, align 4
  %66 = add i32 %65, 1
  store i32 %66, i32* @j, align 4
  br label %67

; <label>:67		; preds = %35, %64
  br label %89

; <label>:68                                           		; preds = %28
  %69 = load i32*, i32** %2, align 8
  %70 = load i32, i32* @i, align 4
  %71 = getelementptr inbounds i32, i32* %69, i32 %70
  %72 = load i32, i32* @ttime, align 4
  %73 = load i32, i32* %71, align 4
  %74 = add i32 %72, %73
  store i32 %74, i32* @ttime, align 4
  %75 = load i32*, i32** %2, align 8
  %76 = load i32, i32* @i, align 4
  %77 = getelementptr inbounds i32, i32* %75, i32 %76
  store i32 0, i32* %77, align 4
  %78 = load i32*, i32** %2, align 8
  %79 = load i32, i32* @i, align 4
  %80 = getelementptr inbounds i32, i32* %78, i32 %79
  %81 = load i32*, i32** %1, align 8
  %82 = load i32, i32* @i, align 4
  %83 = getelementptr inbounds i32, i32* %81, i32 %82
  %84 = getelementptr inbounds [15 x i8], [15 x i8]* @.str4, i32 0, i32 0
  %85 = load i32, i32* %83, align 4
  %86 = load i32, i32* %80, align 4
  %87 = load i32, i32* @ttime, align 4
  %88 = call i32 (i8*, ...)* @printf(i8* %84, i32 %85, i32 %86, i32 %87)
  br label %89

; <label>:89		; preds = %67, %68
  br label %90

; <label>:90		; preds = %22, %89
  br label %91

; <label>:91     		; preds = %90
  %92 = load i32, i32* @i, align 4
  %93 = add i32 %92, 1
  store i32 %93, i32* @i, align 4
  br label %18

; <label>:94		; preds = %18
  br label %13

; <label>:95     		; preds = %13
  %96 = load i32, i32* %4, align 4
  ret i32 %96
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %pname = alloca [10 x i32], align 16
  %btime = alloca [10 x i32], align 16
  %pname2 = alloca [10 x i32], align 16
  %btime2 = alloca [10 x i32], align 16
  %n = alloca i32, align 4
  %x = alloca i32, align 4
  %z = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [26 x i8], [26 x i8]* @.str5, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  %6 = getelementptr inbounds [55 x i8], [55 x i8]* @.str6, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  store i32 0, i32* @i, align 4
  br label %8

; <label>:8  		; preds = %0, %28
  %9 = load i32, i32* @i, align 4
  %10 = load i32, i32* %n, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %31

; <label>:12                                                  		; preds = %8
  %13 = getelementptr inbounds [24 x i8], [24 x i8]* @.str7, i32 0, i32 0
  %14 = call i32 (i8*, ...)* @printf(i8* %13)
  %15 = load i32, i32* @i, align 4
  %16 = getelementptr inbounds [10 x i32], [10 x i32]* %pname2, i32 0, i32 %15
  %17 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %18 = call i32 (i8*, ...)* @scanf(i8* %17, i32* %16)
  %19 = load i32, i32* @i, align 4
  %20 = getelementptr inbounds [10 x i32], [10 x i32]* %pname2, i32 0, i32 %19
  %21 = getelementptr inbounds [37 x i8], [37 x i8]* @.str8, i32 0, i32 0
  %22 = load i32, i32* %20, align 4
  %23 = call i32 (i8*, ...)* @printf(i8* %21, i32 %22)
  %24 = load i32, i32* @i, align 4
  %25 = getelementptr inbounds [10 x i32], [10 x i32]* %btime2, i32 0, i32 %24
  %26 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %27 = call i32 (i8*, ...)* @scanf(i8* %26, i32* %25)
  br label %28

; <label>:28     		; preds = %12
  %29 = load i32, i32* @i, align 4
  %30 = add i32 %29, 1
  store i32 %30, i32* @i, align 4
  br label %8

; <label>:31                                             		; preds = %8
  %32 = getelementptr inbounds [28 x i8], [28 x i8]* @.str9, i32 0, i32 0
  %33 = call i32 (i8*, ...)* @printf(i8* %32)
  store i32 0, i32* @i, align 4
  br label %34

; <label>:34		; preds = %31, %47
  %35 = load i32, i32* @i, align 4
  %36 = load i32, i32* %n, align 4
  %37 = icmp slt i32 %35, %36
  br i1 %37, label %38, label %50

; <label>:38                                  		; preds = %34
  %39 = load i32, i32* @i, align 4
  %40 = getelementptr inbounds [10 x i32], [10 x i32]* %btime2, i32 0, i32 %39
  %41 = load i32, i32* @i, align 4
  %42 = getelementptr inbounds [10 x i32], [10 x i32]* %pname2, i32 0, i32 %41
  %43 = getelementptr inbounds [10 x i8], [10 x i8]* @.str10, i32 0, i32 0
  %44 = load i32, i32* %42, align 4
  %45 = load i32, i32* %40, align 4
  %46 = call i32 (i8*, ...)* @printf(i8* %43, i32 %44, i32 %45)
  br label %47

; <label>:47     		; preds = %38
  %48 = load i32, i32* @i, align 4
  %49 = add i32 %48, 1
  store i32 %49, i32* @i, align 4
  br label %34

; <label>:50  		; preds = %34
  store i32 1, i32* %z, align 4
  br label %51

; <label>:51		; preds = %50, %88
  %52 = load i32, i32* %z, align 4
  %53 = icmp eq i32 %52, 1
  br i1 %53, label %54, label %93

; <label>:54      		; preds = %51
  store i32 0, i32* @ttime, align 4
  store i32 0, i32* @i, align 4
  br label %55

; <label>:55		; preds = %54, %70
  %56 = load i32, i32* @i, align 4
  %57 = load i32, i32* %n, align 4
  %58 = icmp slt i32 %56, %57
  br i1 %58, label %59, label %73

; <label>:59      		; preds = %55
  %60 = load i32, i32* @i, align 4
  %61 = getelementptr inbounds [10 x i32], [10 x i32]* %pname2, i32 0, i32 %60
  %62 = load i32, i32* @i, align 4
  %63 = getelementptr inbounds [10 x i32], [10 x i32]* %pname, i32 0, i32 %62
  %64 = load i32, i32* %61, align 4
  store i32 %64, i32* %63, align 4
  %65 = load i32, i32* @i, align 4
  %66 = getelementptr inbounds [10 x i32], [10 x i32]* %btime2, i32 0, i32 %65
  %67 = load i32, i32* @i, align 4
  %68 = getelementptr inbounds [10 x i32], [10 x i32]* %btime, i32 0, i32 %67
  %69 = load i32, i32* %66, align 4
  store i32 %69, i32* %68, align 4
  br label %70

; <label>:70     		; preds = %59
  %71 = load i32, i32* @i, align 4
  %72 = add i32 %71, 1
  store i32 %72, i32* @i, align 4
  br label %55

; <label>:73                                               		; preds = %55
  %74 = getelementptr inbounds [28 x i8], [28 x i8]* @.str11, i32 0, i32 0
  %75 = call i32 (i8*, ...)* @printf(i8* %74)
  %76 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %77 = call i32 (i8*, ...)* @scanf(i8* %76, i32* %x)
  %78 = load i32, i32* %x, align 4
  switch i32 %78, label %85 [
    i32 1, label %79
    i32 2, label %84 
  ]

; <label>:79                                              		; preds = %73
  %80 = getelementptr inbounds [10 x i32], [10 x i32]* %pname, i32 0, i32 0
  %81 = getelementptr inbounds [10 x i32], [10 x i32]* %btime, i32 0, i32 0
  %82 = load i32, i32* %n, align 4
  %83 = call i32 @rrobin(i32* %80, i32* %81, i32 %82)
  br label %88

; <label>:84    		; preds = %73
  call void @exit(i32 0) noreturn
  unreachable

; <label>:85                                             		; preds = %73
  %86 = getelementptr inbounds [15 x i8], [15 x i8]* @.str12, i32 0, i32 0
  %87 = call i32 (i8*, ...)* @printf(i8* %86)
  br label %88

; <label>:88                                        		; preds = %79, %85
  %89 = getelementptr inbounds [36 x i8], [36 x i8]* @.str13, i32 0, i32 0
  %90 = call i32 (i8*, ...)* @printf(i8* %89)
  %91 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %92 = call i32 (i8*, ...)* @scanf(i8* %91, i32* %z)
  br label %51

; <label>:93     		; preds = %51
  %94 = load i32, i32* %1, align 4
  ret i32 %94
}
