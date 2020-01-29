@stack = common global [10 x i8] zeroinitializer, align 1
@tos = global i32 0, align 4
@ele = common global i32 0, align 4
@infix = common global [30 x i8] zeroinitializer, align 16
@output = common global [30 x i8] zeroinitializer, align 16
@.str = private unnamed_addr constant [28 x i8] c"\0AEnter an infix expression:\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str2 = private unnamed_addr constant [27 x i8] c"\0AThe infix expresson is %s\00", align 16
@.str3 = private unnamed_addr constant [30 x i8] c"\0AThe element added to Q is:%c\00", align 16
@.str4 = private unnamed_addr constant [26 x i8] c"\0AThe pushed element is:%c\00", align 16
@.str5 = private unnamed_addr constant [35 x i8] c"\0A Now the current character is :%c\00", align 16
@.str6 = private unnamed_addr constant [21 x i8] c"\0Aprec(infix[i]) = %d\00", align 16
@.str7 = private unnamed_addr constant [25 x i8] c"\0Aprec(stack[tos-1]) = %d\00", align 16
@.str8 = private unnamed_addr constant [27 x i8] c"\0A the poped element is :%c\00", align 16
@.str9 = private unnamed_addr constant [28 x i8] c"\0A The pushed element is :%c\00", align 16
@.str10 = private unnamed_addr constant [8 x i8] c"\0AVikash\00", align 1
@.str11 = private unnamed_addr constant [7 x i8] c"\0AJoshi\00", align 1
@.str12 = private unnamed_addr constant [28 x i8] c"\0AThe pushed-- element is:%c\00", align 16
@.str13 = private unnamed_addr constant [29 x i8] c"\0Athe infix expression is: %s\00", align 16
@.str14 = private unnamed_addr constant [29 x i8] c"the infix expression is: %s\0A\00", align 16
@.str15 = private unnamed_addr constant [15 x i8] c"\0Ainside push()\00", align 1
@.str16 = private unnamed_addr constant [28 x i8] c"\0Aelement to be pushed is %c\00", align 16
@.str17 = private unnamed_addr constant [10 x i8] c"\0Atos = %d\00", align 1
@.str18 = private unnamed_addr constant [23 x i8] c"\0Acomming out of push()\00", align 16
@.str19 = private unnamed_addr constant [30 x i8] c"--The Stack elements are.....\00", align 16
@.str20 = private unnamed_addr constant [5 x i8] c"%c, \00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

declare i32 @strlen(i8*) 

define void @push(i32 %ele) nounwind {
  %1 = alloca i32, align 4
  %ch = alloca i8, align 1
  store i32 %ele, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = trunc i32 %2 to i8
  store i8 %3, i8* %ch, align 1
  %4 = getelementptr inbounds [15 x i8], [15 x i8]* @.str15, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @printf(i8* %4)
  %6 = getelementptr inbounds [28 x i8], [28 x i8]* @.str16, i32 0, i32 0
  %7 = load i8, i8* %ch, align 1
  %8 = call i32 (i8*, ...)* @printf(i8* %6, i8 %7)
  %9 = getelementptr inbounds [10 x i8], [10 x i8]* @.str17, i32 0, i32 0
  %10 = load i32, i32* @tos, align 4
  %11 = call i32 (i8*, ...)* @printf(i8* %9, i32 %10)
  %12 = load i32, i32* @tos, align 4
  %13 = getelementptr inbounds [10 x i8], [10 x i8]* @stack, i32 0, i32 %12
  %14 = load i32, i32* %1, align 4
  %15 = trunc i32 %14 to i8
  store i8 %15, i8* %13, align 1
  %16 = load i32, i32* @tos, align 4
  %17 = add i32 %16, 1
  store i32 %17, i32* @tos, align 4
  %18 = getelementptr inbounds [23 x i8], [23 x i8]* @.str18, i32 0, i32 0
  %19 = call i32 (i8*, ...)* @printf(i8* %18)
  ret void
}

define i8 @pop() nounwind {
  %1 = load i32, i32* @tos, align 4
  %2 = sub i32 %1, 1
  store i32 %2, i32* @tos, align 4
  %3 = load i32, i32* @tos, align 4
  %4 = getelementptr inbounds [10 x i8], [10 x i8]* @stack, i32 0, i32 %3
  %5 = load i8, i8* %4, align 1
  ret i8 %5
}

define void @show() nounwind {
  %x = alloca i32, align 4
  %1 = load i32, i32* @tos, align 4
  store i32 %1, i32* %x, align 4
  %2 = getelementptr inbounds [30 x i8], [30 x i8]* @.str19, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  br label %4

; <label>:4  		; preds = %0, %7
  %5 = load i32, i32* %x, align 4
  %6 = icmp ne i32 %5, 0
  br i1 %6, label %7, label %14

; <label>:7                          		; preds = %4
  %8 = load i32, i32* %x, align 4
  %9 = sub i32 %8, 1
  store i32 %9, i32* %x, align 4
  %10 = getelementptr inbounds [10 x i8], [10 x i8]* @stack, i32 0, i32 %9
  %11 = getelementptr inbounds [5 x i8], [5 x i8]* @.str20, i32 0, i32 0
  %12 = load i8, i8* %10, align 1
  %13 = call i32 (i8*, ...)* @printf(i8* %11, i8 %12)
  br label %4

; <label>:14		; preds = %4
  ret void
}

define i32 @prec(i8 %symbol) nounwind {
  %1 = alloca i8, align 1
  %2 = alloca i32, align 4
  store i8 %symbol, i8* %1, align 1
  %3 = load i8, i8* %1, align 1
  %4 = icmp eq i8 %3, 40
  br i1 %4, label %5, label %6

; <label>:5    		; preds = %0
  store i32 0, i32* %2, align 4
  br label %33

; <label>:6    		; preds = %0
  %7 = load i8, i8* %1, align 1
  %8 = icmp eq i8 %7, 41
  br i1 %8, label %9, label %10

; <label>:9    		; preds = %6
  store i32 0, i32* %2, align 4
  br label %33

; <label>:10     		; preds = %6
  %11 = load i8, i8* %1, align 1
  %12 = icmp eq i8 %11, 43
  br i1 %12, label %16, label %13

; <label>:13   		; preds = %10
  %14 = load i8, i8* %1, align 1
  %15 = icmp eq i8 %14, 45
  br label %16

; <label>:16        		; preds = %10, %13
  %17 = phi i1 [ true, %10 ], [ %15, %13 ]
  br i1 %17, label %18, label %19

; <label>:18  		; preds = %16
  store i32 1, i32* %2, align 4
  br label %33

; <label>:19    		; preds = %16
  %20 = load i8, i8* %1, align 1
  %21 = icmp eq i8 %20, 42
  br i1 %21, label %25, label %22

; <label>:22   		; preds = %19
  %23 = load i8, i8* %1, align 1
  %24 = icmp eq i8 %23, 47
  br label %25

; <label>:25        		; preds = %19, %22
  %26 = phi i1 [ true, %19 ], [ %24, %22 ]
  br i1 %26, label %27, label %28

; <label>:27  		; preds = %25
  store i32 2, i32* %2, align 4
  br label %33

; <label>:28    		; preds = %25
  %29 = load i8, i8* %1, align 1
  %30 = icmp eq i8 %29, 94
  br i1 %30, label %31, label %32

; <label>:31  		; preds = %28
  store i32 3, i32* %2, align 4
  br label %33

; <label>:32  		; preds = %28
  store i32 0, i32* %2, align 4
  br label %33

; <label>:33		; preds = %5, %9, %18, %27, %31, %32
  %34 = load i32, i32* %2, align 4
  ret i32 %34
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %k = alloca i32, align 4
  %length = alloca i32, align 4
  %temp = alloca i8, align 1
  store i32 0, i32* %1, align 4
  store i32 0, i32* %i, align 4
  store i32 0, i32* %j, align 4
  store i32 0, i32* %k, align 4
  %2 = getelementptr inbounds [28 x i8], [28 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @scanf(i8* %4, i8* %5)
  %7 = getelementptr inbounds [27 x i8], [27 x i8]* @.str2, i32 0, i32 0
  %8 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @printf(i8* %7, i8* %8)
  %10 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 0
  %11 = call i32 @strlen(i8* %10)
  store i32 %11, i32* %length, align 4
  store i32 0, i32* %i, align 4
  br label %12

; <label>:12     		; preds = %0, %222
  %13 = load i32, i32* %i, align 4
  %14 = load i32, i32* %length, align 4
  %15 = icmp slt i32 %13, %14
  br i1 %15, label %16, label %225

; <label>:16                                              		; preds = %12
  %17 = load i32, i32* %i, align 4
  %18 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %17
  %19 = load i8, i8* %18, align 1
  %20 = icmp ne i8 %19, 43
  br i1 %20, label %21, label %26

; <label>:21                                              		; preds = %16
  %22 = load i32, i32* %i, align 4
  %23 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %22
  %24 = load i8, i8* %23, align 1
  %25 = icmp ne i8 %24, 45
  br label %26

; <label>:26         		; preds = %16, %21
  %27 = phi i1 [ false, %16 ], [ %25, %21 ]
  %28 = zext i1 %27 to i32
  %29 = icmp ne i32 %28, 0
  br i1 %29, label %30, label %35

; <label>:30                                              		; preds = %26
  %31 = load i32, i32* %i, align 4
  %32 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %31
  %33 = load i8, i8* %32, align 1
  %34 = icmp ne i8 %33, 42
  br label %35

; <label>:35         		; preds = %26, %30
  %36 = phi i1 [ false, %26 ], [ %34, %30 ]
  %37 = zext i1 %36 to i32
  %38 = icmp ne i32 %37, 0
  br i1 %38, label %39, label %44

; <label>:39                                              		; preds = %35
  %40 = load i32, i32* %i, align 4
  %41 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %40
  %42 = load i8, i8* %41, align 1
  %43 = icmp ne i8 %42, 47
  br label %44

; <label>:44         		; preds = %35, %39
  %45 = phi i1 [ false, %35 ], [ %43, %39 ]
  %46 = zext i1 %45 to i32
  %47 = icmp ne i32 %46, 0
  br i1 %47, label %48, label %53

; <label>:48                                              		; preds = %44
  %49 = load i32, i32* %i, align 4
  %50 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %49
  %51 = load i8, i8* %50, align 1
  %52 = icmp ne i8 %51, 94
  br label %53

; <label>:53         		; preds = %44, %48
  %54 = phi i1 [ false, %44 ], [ %52, %48 ]
  %55 = zext i1 %54 to i32
  %56 = icmp ne i32 %55, 0
  br i1 %56, label %57, label %62

; <label>:57                                              		; preds = %53
  %58 = load i32, i32* %i, align 4
  %59 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %58
  %60 = load i8, i8* %59, align 1
  %61 = icmp ne i8 %60, 41
  br label %62

; <label>:62         		; preds = %53, %57
  %63 = phi i1 [ false, %53 ], [ %61, %57 ]
  %64 = zext i1 %63 to i32
  %65 = icmp ne i32 %64, 0
  br i1 %65, label %66, label %71

; <label>:66                                              		; preds = %62
  %67 = load i32, i32* %i, align 4
  %68 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %67
  %69 = load i8, i8* %68, align 1
  %70 = icmp ne i8 %69, 40
  br label %71

; <label>:71         		; preds = %62, %66
  %72 = phi i1 [ false, %62 ], [ %70, %66 ]
  br i1 %72, label %73, label %85

; <label>:73                        		; preds = %71
  %74 = load i32, i32* %j, align 4
  %75 = add i32 %74, 1
  store i32 %75, i32* %j, align 4
  %76 = load i32, i32* %i, align 4
  %77 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %76
  %78 = getelementptr inbounds [30 x i8], [30 x i8]* @output, i32 0, i32 %74
  %79 = load i8, i8* %77, align 1
  store i8 %79, i8* %78, align 1
  %80 = load i32, i32* %i, align 4
  %81 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %80
  %82 = getelementptr inbounds [30 x i8], [30 x i8]* @.str3, i32 0, i32 0
  %83 = load i8, i8* %81, align 1
  %84 = call i32 (i8*, ...)* @printf(i8* %82, i8 %83)
  br label %218

; <label>:85       		; preds = %71
  %86 = load i32, i32* @tos, align 4
  %87 = icmp eq i32 %86, 0
  br i1 %87, label %88, label %98

; <label>:88                        		; preds = %85
  %89 = load i32, i32* %i, align 4
  %90 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %89
  %91 = load i8, i8* %90, align 1
  %92 = sext i8 %91 to i32
  call void @push(i32 %92)
  %93 = load i32, i32* %i, align 4
  %94 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %93
  %95 = getelementptr inbounds [26 x i8], [26 x i8]* @.str4, i32 0, i32 0
  %96 = load i8, i8* %94, align 1
  %97 = call i32 (i8*, ...)* @printf(i8* %95, i8 %96)
  br label %217

; <label>:98       		; preds = %85
  %99 = load i32, i32* %i, align 4
  %100 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %99
  %101 = getelementptr inbounds [35 x i8], [35 x i8]* @.str5, i32 0, i32 0
  %102 = load i8, i8* %100, align 1
  %103 = call i32 (i8*, ...)* @printf(i8* %101, i8 %102)
  %104 = load i32, i32* %i, align 4
  %105 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %104
  %106 = load i8, i8* %105, align 1
  %107 = icmp ne i8 %106, 41
  br i1 %107, label %108, label %113

; <label>:108                                               		; preds = %98
  %109 = load i32, i32* %i, align 4
  %110 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %109
  %111 = load i8, i8* %110, align 1
  %112 = icmp ne i8 %111, 40
  br label %113

; <label>:113          		; preds = %98, %108
  %114 = phi i1 [ false, %98 ], [ %112, %108 ]
  br i1 %114, label %115, label %177

; <label>:115     		; preds = %113
  %116 = load i32, i32* %i, align 4
  %117 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %116
  %118 = load i8, i8* %117, align 1
  %119 = call i32 @prec(i8 %118)
  %120 = getelementptr inbounds [21 x i8], [21 x i8]* @.str6, i32 0, i32 0
  %121 = call i32 (i8*, ...)* @printf(i8* %120, i32 %119)
  %122 = load i32, i32* @tos, align 4
  %123 = sub i32 %122, 1
  %124 = getelementptr inbounds [10 x i8], [10 x i8]* @stack, i32 0, i32 %123
  %125 = load i8, i8* %124, align 1
  %126 = call i32 @prec(i8 %125)
  %127 = getelementptr inbounds [25 x i8], [25 x i8]* @.str7, i32 0, i32 0
  %128 = call i32 (i8*, ...)* @printf(i8* %127, i32 %126)
  %129 = load i32, i32* %i, align 4
  %130 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %129
  %131 = load i8, i8* %130, align 1
  %132 = call i32 @prec(i8 %131)
  %133 = load i32, i32* @tos, align 4
  %134 = sub i32 %133, 1
  %135 = getelementptr inbounds [10 x i8], [10 x i8]* @stack, i32 0, i32 %134
  %136 = load i8, i8* %135, align 1
  %137 = call i32 @prec(i8 %136)
  %138 = icmp sle i32 %132, %137
  br i1 %138, label %139, label %157

; <label>:139                         		; preds = %115
  %140 = call i8 @pop()
  store i8 %140, i8* %temp, align 1
  %141 = getelementptr inbounds [27 x i8], [27 x i8]* @.str8, i32 0, i32 0
  %142 = load i8, i8* %temp, align 1
  %143 = call i32 (i8*, ...)* @printf(i8* %141, i8 %142)
  %144 = load i32, i32* %j, align 4
  %145 = add i32 %144, 1
  store i32 %145, i32* %j, align 4
  %146 = getelementptr inbounds [30 x i8], [30 x i8]* @output, i32 0, i32 %144
  %147 = load i8, i8* %temp, align 1
  store i8 %147, i8* %146, align 1
  %148 = load i32, i32* %i, align 4
  %149 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %148
  %150 = load i8, i8* %149, align 1
  %151 = sext i8 %150 to i32
  call void @push(i32 %151)
  %152 = load i32, i32* %i, align 4
  %153 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %152
  %154 = getelementptr inbounds [28 x i8], [28 x i8]* @.str9, i32 0, i32 0
  %155 = load i8, i8* %153, align 1
  %156 = call i32 (i8*, ...)* @printf(i8* %154, i8 %155)
  call void @show()
  br label %176

; <label>:157                		; preds = %115
  %158 = load i32, i32* %i, align 4
  %159 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %158
  %160 = getelementptr inbounds [26 x i8], [26 x i8]* @.str4, i32 0, i32 0
  %161 = load i8, i8* %159, align 1
  %162 = call i32 (i8*, ...)* @printf(i8* %160, i8 %161)
  %163 = getelementptr inbounds [8 x i8], [8 x i8]* @.str10, i32 0, i32 0
  %164 = call i32 (i8*, ...)* @printf(i8* %163)
  %165 = load i32, i32* %i, align 4
  %166 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %165
  %167 = load i8, i8* %166, align 1
  %168 = sext i8 %167 to i32
  call void @push(i32 %168)
  %169 = load i32, i32* %i, align 4
  %170 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %169
  %171 = getelementptr inbounds [26 x i8], [26 x i8]* @.str4, i32 0, i32 0
  %172 = load i8, i8* %170, align 1
  %173 = call i32 (i8*, ...)* @printf(i8* %171, i8 %172)
  %174 = getelementptr inbounds [7 x i8], [7 x i8]* @.str11, i32 0, i32 0
  %175 = call i32 (i8*, ...)* @printf(i8* %174)
  call void @show()
  br label %176

; <label>:176		; preds = %139, %157
  br label %216

; <label>:177     		; preds = %113
  %178 = load i32, i32* %i, align 4
  %179 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %178
  %180 = load i8, i8* %179, align 1
  %181 = icmp eq i8 %180, 40
  br i1 %181, label %182, label %192

; <label>:182                         		; preds = %177
  %183 = load i32, i32* %i, align 4
  %184 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %183
  %185 = load i8, i8* %184, align 1
  %186 = sext i8 %185 to i32
  call void @push(i32 %186)
  %187 = load i32, i32* %i, align 4
  %188 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %187
  %189 = getelementptr inbounds [28 x i8], [28 x i8]* @.str12, i32 0, i32 0
  %190 = load i8, i8* %188, align 1
  %191 = call i32 (i8*, ...)* @printf(i8* %189, i8 %190)
  br label %192

; <label>:192		; preds = %177, %182
  %193 = load i32, i32* %i, align 4
  %194 = getelementptr inbounds [30 x i8], [30 x i8]* @infix, i32 0, i32 %193
  %195 = load i8, i8* %194, align 1
  %196 = icmp eq i8 %195, 41
  br i1 %196, label %197, label %215

; <label>:197    		; preds = %192
  %198 = call i8 @pop()
  store i8 %198, i8* %temp, align 1
  br label %199

; <label>:199		; preds = %197, %202
  %200 = load i8, i8* %temp, align 1
  %201 = icmp ne i8 %200, 40
  br i1 %201, label %202, label %214

; <label>:202                         		; preds = %199
  %203 = load i32, i32* %j, align 4
  %204 = add i32 %203, 1
  store i32 %204, i32* %j, align 4
  %205 = getelementptr inbounds [30 x i8], [30 x i8]* @output, i32 0, i32 %203
  %206 = load i8, i8* %temp, align 1
  store i8 %206, i8* %205, align 1
  %207 = getelementptr inbounds [30 x i8], [30 x i8]* @.str3, i32 0, i32 0
  %208 = load i8, i8* %temp, align 1
  %209 = call i32 (i8*, ...)* @printf(i8* %207, i8 %208)
  %210 = getelementptr inbounds [27 x i8], [27 x i8]* @.str8, i32 0, i32 0
  %211 = load i8, i8* %temp, align 1
  %212 = call i32 (i8*, ...)* @printf(i8* %210, i8 %211)
  %213 = call i8 @pop()
  store i8 %213, i8* %temp, align 1
  br label %199

; <label>:214		; preds = %199
  br label %215

; <label>:215		; preds = %192, %214
  br label %216

; <label>:216		; preds = %176, %215
  br label %217

; <label>:217		; preds = %88, %216
  br label %218

; <label>:218                                       		; preds = %73, %217
  %219 = getelementptr inbounds [29 x i8], [29 x i8]* @.str13, i32 0, i32 0
  %220 = getelementptr inbounds [30 x i8], [30 x i8]* @output, i32 0, i32 0
  %221 = call i32 (i8*, ...)* @printf(i8* %219, i8* %220)
  br label %222

; <label>:222    		; preds = %218
  %223 = load i32, i32* %i, align 4
  %224 = add i32 %223, 1
  store i32 %224, i32* %i, align 4
  br label %12

; <label>:225		; preds = %12
  br label %226

; <label>:226		; preds = %225, %229
  %227 = load i32, i32* @tos, align 4
  %228 = icmp ne i32 %227, 0
  br i1 %228, label %229, label %234

; <label>:229                                               		; preds = %226
  %230 = load i32, i32* %j, align 4
  %231 = add i32 %230, 1
  store i32 %231, i32* %j, align 4
  %232 = call i8 @pop()
  %233 = getelementptr inbounds [30 x i8], [30 x i8]* @output, i32 0, i32 %230
  store i8 %232, i8* %233, align 1
  br label %226

; <label>:234                                            		; preds = %226
  %235 = getelementptr inbounds [29 x i8], [29 x i8]* @.str14, i32 0, i32 0
  %236 = getelementptr inbounds [30 x i8], [30 x i8]* @output, i32 0, i32 0
  %237 = call i32 (i8*, ...)* @printf(i8* %235, i8* %236)
  %238 = load i32, i32* %1, align 4
  ret i32 %238
}
