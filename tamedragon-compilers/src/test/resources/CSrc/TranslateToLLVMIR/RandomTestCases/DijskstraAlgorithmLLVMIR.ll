@main.cost = internal constant [7 x [7 x i32]] [[7 x i32] [i32 998, i32 2, i32 4, i32 7, i32 998, i32 5, i32 998], [7 x i32] [i32 2, i32 998, i32 998, i32 6, i32 3, i32 998, i32 8], [7 x i32] [i32 4, i32 998, i32 998, i32 998, i32 998, i32 6, i32 998], [7 x i32] [i32 7, i32 6, i32 998, i32 998, i32 998, i32 1, i32 6], [7 x i32] [i32 998, i32 3, i32 998, i32 998, i32 998, i32 998, i32 7], [7 x i32] [i32 5, i32 998, i32 6, i32 1, i32 998, i32 998, i32 6], [7 x i32] [i32 998, i32 8, i32 998, i32 6, i32 7, i32 6, i32 998]], align 16
@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @allselected(i32* %selected) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32* %selected, i32** %1, align 8
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %14
  %4 = load i32, i32* %i, align 4
  %5 = icmp slt i32 %4, 7
  br i1 %5, label %6, label %17

; <label>:6                       		; preds = %3
  %7 = load i32*, i32** %1, align 8
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds i32, i32* %7, i32 %8
  %10 = load i32, i32* %9, align 4
  %11 = icmp eq i32 %10, 0
  br i1 %11, label %12, label %13

; <label>:12   		; preds = %6
  store i32 0, i32* %2, align 4
  br label %18

; <label>:13		; preds = %6
  br label %14

; <label>:14     		; preds = %13
  %15 = load i32, i32* %i, align 4
  %16 = add i32 %15, 1
  store i32 %16, i32* %i, align 4
  br label %3

; <label>:17   		; preds = %3
  store i32 1, i32* %2, align 4
  br label %18

; <label>:18		; preds = %12, %17
  %19 = load i32, i32* %2, align 4
  ret i32 %19
}

define void @shortpath([7 x i32]* %cost, i32* %preced, i32* %distance) nounwind {
  %1 = alloca [7 x i32]*, align 8
  %2 = alloca i32*, align 8
  %3 = alloca i32*, align 8
  %selected = alloca [7 x i32], align 16
  %current = alloca i32, align 4
  %i = alloca i32, align 4
  %k = alloca i32, align 4
  %dc = alloca i32, align 4
  %smalldist = alloca i32, align 4
  %newdist = alloca i32, align 4
  store [7 x i32]* %cost, [7 x i32]** %1, align 8
  store i32* %preced, i32** %2, align 8
  store i32* %distance, i32** %3, align 8
  %4 = bitcast [7 x i32]* %selected to i8*
  call void @llvm.memset.p0i8.i64(i8* %4, i8 0, i64 28, i32 16, i1 false)
  %5 = getelementptr inbounds [7 x i32], [7 x i32]* %selected, i32 0, i32 0
  store i32 0, i32* %5, align 4
  store i32 0, i32* %current, align 4
  store i32 0, i32* %i, align 4
  br label %6

; <label>:6 		; preds = %0, %13
  %7 = load i32, i32* %i, align 4
  %8 = icmp slt i32 %7, 7
  br i1 %8, label %9, label %16

; <label>:9                          		; preds = %6
  %10 = load i32*, i32** %3, align 8
  %11 = load i32, i32* %i, align 4
  %12 = getelementptr inbounds i32, i32* %10, i32 %11
  store i32 998, i32* %12, align 4
  br label %13

; <label>:13      		; preds = %9
  %14 = load i32, i32* %i, align 4
  %15 = add i32 %14, 1
  store i32 %15, i32* %i, align 4
  br label %6

; <label>:16                       		; preds = %6
  %17 = load i32, i32* %current, align 4
  %18 = getelementptr inbounds [7 x i32], [7 x i32]* %selected, i32 0, i32 %17
  store i32 1, i32* %18, align 4
  %19 = load i32*, i32** %3, align 8
  %20 = getelementptr inbounds i32, i32* %19, i32 0
  store i32 0, i32* %20, align 4
  store i32 0, i32* %current, align 4
  br label %21

; <label>:21                                          		; preds = %16, %81
  %22 = getelementptr inbounds [7 x i32], [7 x i32]* %selected, i32 0, i32 0
  %23 = call i32 @allselected(i32* %22)
  %24 = icmp ne i32 %23, 0
  %25 = xor i1 %24, true
  br i1 %25, label %26, label %85

; <label>:26                        		; preds = %21
  store i32 998, i32* %smalldist, align 4
  %27 = load i32*, i32** %3, align 8
  %28 = load i32, i32* %current, align 4
  %29 = getelementptr inbounds i32, i32* %27, i32 %28
  %30 = load i32, i32* %29, align 4
  store i32 %30, i32* %dc, align 4
  store i32 0, i32* %i, align 4
  br label %31

; <label>:31		; preds = %26, %78
  %32 = load i32, i32* %i, align 4
  %33 = icmp slt i32 %32, 7
  br i1 %33, label %34, label %81

; <label>:34      		; preds = %31
  %35 = load i32, i32* %i, align 4
  %36 = getelementptr inbounds [7 x i32], [7 x i32]* %selected, i32 0, i32 %35
  %37 = load i32, i32* %36, align 4
  %38 = icmp eq i32 %37, 0
  br i1 %38, label %39, label %77

; <label>:39                        		; preds = %34
  %40 = load i32, i32* %dc, align 4
  %41 = load [7 x i32]*, [7 x i32]** %1, align 8
  %42 = load i32, i32* %current, align 4
  %43 = getelementptr inbounds [7 x i32], [7 x i32]* %41, i32 %42
  %44 = load i32, i32* %i, align 4
  %45 = getelementptr inbounds [7 x i32], [7 x i32]* %43, i32 0, i32 %44
  %46 = load i32, i32* %45, align 4
  %47 = add i32 %40, %46
  store i32 %47, i32* %newdist, align 4
  %48 = load i32, i32* %newdist, align 4
  %49 = load i32*, i32** %3, align 8
  %50 = load i32, i32* %i, align 4
  %51 = getelementptr inbounds i32, i32* %49, i32 %50
  %52 = load i32, i32* %51, align 4
  %53 = icmp slt i32 %48, %52
  br i1 %53, label %54, label %63

; <label>:54           		; preds = %39
  %55 = load i32*, i32** %3, align 8
  %56 = load i32, i32* %i, align 4
  %57 = getelementptr inbounds i32, i32* %55, i32 %56
  %58 = load i32, i32* %newdist, align 4
  store i32 %58, i32* %57, align 4
  %59 = load i32*, i32** %2, align 8
  %60 = load i32, i32* %i, align 4
  %61 = getelementptr inbounds i32, i32* %59, i32 %60
  %62 = load i32, i32* %current, align 4
  store i32 %62, i32* %61, align 4
  br label %63

; <label>:63        		; preds = %39, %54
  %64 = load i32*, i32** %3, align 8
  %65 = load i32, i32* %i, align 4
  %66 = getelementptr inbounds i32, i32* %64, i32 %65
  %67 = load i32, i32* %smalldist, align 4
  %68 = load i32, i32* %66, align 4
  %69 = icmp slt i32 %68, %67
  br i1 %69, label %70, label %76

; <label>:70            		; preds = %63
  %71 = load i32*, i32** %3, align 8
  %72 = load i32, i32* %i, align 4
  %73 = getelementptr inbounds i32, i32* %71, i32 %72
  %74 = load i32, i32* %73, align 4
  store i32 %74, i32* %smalldist, align 4
  %75 = load i32, i32* %i, align 4
  store i32 %75, i32* %k, align 4
  br label %76

; <label>:76		; preds = %63, %70
  br label %77

; <label>:77		; preds = %34, %76
  br label %78

; <label>:78     		; preds = %77
  %79 = load i32, i32* %i, align 4
  %80 = add i32 %79, 1
  store i32 %80, i32* %i, align 4
  br label %31

; <label>:81                                                 		; preds = %31
  %82 = load i32, i32* %k, align 4
  store i32 %82, i32* %current, align 4
  %83 = load i32, i32* %current, align 4
  %84 = getelementptr inbounds [7 x i32], [7 x i32]* %selected, i32 0, i32 %83
  store i32 1, i32* %84, align 4
  br label %21

; <label>:85		; preds = %21
  ret void
}

declare void @llvm.memset.p0i8.i64(i8* nocapture, i8, i64, i32, i1) nounwind

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %cost = alloca [7 x [7 x i32]], align 16
  %i = alloca i32, align 4
  %preced = alloca [7 x i32], align 16
  %distance = alloca [7 x i32], align 16
  store i32 0, i32* %1, align 4
  %2 = bitcast [7 x [7 x i32]]* %cost to i8*
  %3 = bitcast [7 x [7 x i32]]* @main.cost to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 196, i32 16, i1 false)
  %4 = bitcast [7 x i32]* %preced to i8*
  call void @llvm.memset.p0i8.i64(i8* %4, i8 0, i64 28, i32 16, i1 false)
  %5 = getelementptr inbounds [7 x i32], [7 x i32]* %preced, i32 0, i32 0
  store i32 0, i32* %5, align 4
  %6 = getelementptr inbounds [7 x [7 x i32]], [7 x [7 x i32]]* %cost, i32 0, i32 0
  %7 = getelementptr inbounds [7 x i32], [7 x i32]* %preced, i32 0, i32 0
  %8 = getelementptr inbounds [7 x i32], [7 x i32]* %distance, i32 0, i32 0
  call void @shortpath([7 x i32]* %6, i32* %7, i32* %8)
  store i32 0, i32* %i, align 4
  br label %9

; <label>:9  		; preds = %0, %18
  %10 = load i32, i32* %i, align 4
  %11 = icmp slt i32 %10, 7
  br i1 %11, label %12, label %21

; <label>:12                          		; preds = %9
  %13 = load i32, i32* %i, align 4
  %14 = getelementptr inbounds [7 x i32], [7 x i32]* %distance, i32 0, i32 %13
  %15 = getelementptr inbounds [4 x i8], [4 x i8]* @.str, i32 0, i32 0
  %16 = load i32, i32* %14, align 4
  %17 = call i32 (i8*, ...)* @printf(i8* %15, i32 %16)
  br label %18

; <label>:18     		; preds = %12
  %19 = load i32, i32* %i, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %i, align 4
  br label %9

; <label>:21		; preds = %9
  ret i32 0
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
