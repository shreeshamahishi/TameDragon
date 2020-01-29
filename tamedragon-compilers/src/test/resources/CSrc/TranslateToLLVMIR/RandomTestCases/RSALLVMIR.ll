@phi = common global i32 0, align 4
@M = common global i32 0, align 4
@n = common global i32 0, align 4
@e = common global i32 0, align 4
@d = common global i32 0, align 4
@C = common global i32 0, align 4
@FLAG = common global i32 0, align 4
@.str = private unnamed_addr constant [25 x i8] c"\0A\09Encrypted keyword : %d\00", align 16
@.str1 = private unnamed_addr constant [25 x i8] c"\0A\09Decrypted keyword : %d\00", align 16
@.str2 = private unnamed_addr constant [38 x i8] c"Enter Two Relatively Prime Numbers\09: \00", align 16
@.str3 = private unnamed_addr constant [5 x i8] c"%d%d\00", align 1
@.str4 = private unnamed_addr constant [12 x i8] c"\0A\09F(n)\09= %d\00", align 1
@.str5 = private unnamed_addr constant [13 x i8] c"\0A\0AEnter e\09: \00", align 1
@.str6 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str7 = private unnamed_addr constant [23 x i8] c"\0A\09Public Key\09: {%d,%d}\00", align 16
@.str8 = private unnamed_addr constant [24 x i8] c"\0A\09Private Key\09: {%d,%d}\00", align 16
@.str9 = private unnamed_addr constant [26 x i8] c"\0A\0AEnter The Plain Text\09: \00", align 16
@.str10 = private unnamed_addr constant [27 x i8] c"\0A\0AEnter the Cipher text\09: \00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @check() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 3, i32* %i, align 4
  br label %2

; <label>:2 		; preds = %0, %15
  %3 = load i32, i32* @e, align 4
  %4 = load i32, i32* %i, align 4
  %5 = srem i32 %3, %4
  %6 = icmp eq i32 %5, 0
  br i1 %6, label %7, label %12

; <label>:7        		; preds = %2
  %8 = load i32, i32* @phi, align 4
  %9 = load i32, i32* %i, align 4
  %10 = srem i32 %8, %9
  %11 = icmp eq i32 %10, 0
  br label %12

; <label>:12         		; preds = %2, %7
  %13 = phi i1 [ false, %2 ], [ %11, %7 ]
  br i1 %13, label %14, label %18

; <label>:14     		; preds = %12
  store i32 1, i32* @FLAG, align 4
  br label %19

; <label>:15        		; preds = 
  %16 = load i32, i32* %i, align 4
  %17 = add i32 %16, 2
  br label %2

; <label>:18     		; preds = %12
  store i32 0, i32* @FLAG, align 4
  br label %19

; <label>:19		; preds = %14, %18
  %20 = load i32, i32* %1, align 4
  ret i32 %20
}

define void @encrypt() nounwind {
  %i = alloca i32, align 4
  store i32 1, i32* @C, align 4
  store i32 0, i32* %i, align 4
  br label %1

; <label>:1 		; preds = %0, %11
  %2 = load i32, i32* %i, align 4
  %3 = load i32, i32* @e, align 4
  %4 = icmp slt i32 %2, %3
  br i1 %4, label %5, label %14

; <label>:5      		; preds = %1
  %6 = load i32, i32* @C, align 4
  %7 = load i32, i32* @M, align 4
  %8 = mul i32 %6, %7
  %9 = load i32, i32* @n, align 4
  %10 = srem i32 %8, %9
  store i32 %10, i32* @C, align 4
  br label %11

; <label>:11      		; preds = %5
  %12 = load i32, i32* %i, align 4
  %13 = add i32 %12, 1
  store i32 %13, i32* %i, align 4
  br label %1

; <label>:14                          		; preds = %1
  %15 = load i32, i32* @C, align 4
  %16 = load i32, i32* @n, align 4
  %17 = srem i32 %15, %16
  store i32 %17, i32* @C, align 4
  %18 = getelementptr inbounds [25 x i8], [25 x i8]* @.str, i32 0, i32 0
  %19 = load i32, i32* @C, align 4
  %20 = call i32 (i8*, ...)* @printf(i8* %18, i32 %19)
  ret void
}

define void @decrypt() nounwind {
  %i = alloca i32, align 4
  store i32 1, i32* @M, align 4
  store i32 0, i32* %i, align 4
  br label %1

; <label>:1 		; preds = %0, %11
  %2 = load i32, i32* %i, align 4
  %3 = load i32, i32* @d, align 4
  %4 = icmp slt i32 %2, %3
  br i1 %4, label %5, label %14

; <label>:5      		; preds = %1
  %6 = load i32, i32* @M, align 4
  %7 = load i32, i32* @C, align 4
  %8 = mul i32 %6, %7
  %9 = load i32, i32* @n, align 4
  %10 = srem i32 %8, %9
  store i32 %10, i32* @M, align 4
  br label %11

; <label>:11      		; preds = %5
  %12 = load i32, i32* %i, align 4
  %13 = add i32 %12, 1
  store i32 %13, i32* %i, align 4
  br label %1

; <label>:14                          		; preds = %1
  %15 = load i32, i32* @M, align 4
  %16 = load i32, i32* @n, align 4
  %17 = srem i32 %15, %16
  store i32 %17, i32* @M, align 4
  %18 = getelementptr inbounds [25 x i8], [25 x i8]* @.str1, i32 0, i32 0
  %19 = load i32, i32* @M, align 4
  %20 = call i32 (i8*, ...)* @printf(i8* %18, i32 %19)
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %p = alloca i32, align 4
  %q = alloca i32, align 4
  %s = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [38 x i8], [38 x i8]* @.str2, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [5 x i8], [5 x i8]* @.str3, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %p, i32* %q)
  %6 = load i32, i32* %p, align 4
  %7 = load i32, i32* %q, align 4
  %8 = mul i32 %6, %7
  store i32 %8, i32* @n, align 4
  %9 = load i32, i32* %p, align 4
  %10 = sub i32 %9, 1
  %11 = load i32, i32* %q, align 4
  %12 = sub i32 %11, 1
  %13 = mul i32 %10, %12
  store i32 %13, i32* @phi, align 4
  %14 = getelementptr inbounds [12 x i8], [12 x i8]* @.str4, i32 0, i32 0
  %15 = load i32, i32* @phi, align 4
  %16 = call i32 (i8*, ...)* @printf(i8* %14, i32 %15)
  br label %17

; <label>:17                                        		; preds = %0, %23
  %18 = getelementptr inbounds [13 x i8], [13 x i8]* @.str5, i32 0, i32 0
  %19 = call i32 (i8*, ...)* @printf(i8* %18)
  %20 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %21 = call i32 (i8*, ...)* @scanf(i8* %20, i32* @e)
  %22 = call i32 @check()
  br label %23

; <label>:23        		; preds = %17
  %24 = load i32, i32* @FLAG, align 4
  %25 = icmp eq i32 %24, 1
  br i1 %25, label %17, label %26

; <label>:26  		; preds = %23
  store i32 1, i32* @d, align 4
  br label %27

; <label>:27  		; preds = %26, %35
  %28 = load i32, i32* @d, align 4
  %29 = load i32, i32* @e, align 4
  %30 = mul i32 %28, %29
  %31 = load i32, i32* @phi, align 4
  %32 = srem i32 %30, %31
  store i32 %32, i32* %s, align 4
  %33 = load i32, i32* @d, align 4
  %34 = add i32 %33, 1
  store i32 %34, i32* @d, align 4
  br label %35

; <label>:35     		; preds = %27
  %36 = load i32, i32* %s, align 4
  %37 = icmp ne i32 %36, 1
  br i1 %37, label %27, label %38

; <label>:38                        		; preds = %35
  %39 = load i32, i32* @d, align 4
  %40 = sub i32 %39, 1
  store i32 %40, i32* @d, align 4
  %41 = getelementptr inbounds [23 x i8], [23 x i8]* @.str7, i32 0, i32 0
  %42 = load i32, i32* @e, align 4
  %43 = load i32, i32* @n, align 4
  %44 = call i32 (i8*, ...)* @printf(i8* %41, i32 %42, i32 %43)
  %45 = getelementptr inbounds [24 x i8], [24 x i8]* @.str8, i32 0, i32 0
  %46 = load i32, i32* @d, align 4
  %47 = load i32, i32* @n, align 4
  %48 = call i32 (i8*, ...)* @printf(i8* %45, i32 %46, i32 %47)
  %49 = getelementptr inbounds [26 x i8], [26 x i8]* @.str9, i32 0, i32 0
  %50 = call i32 (i8*, ...)* @printf(i8* %49)
  %51 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %52 = call i32 (i8*, ...)* @scanf(i8* %51, i32* @M)
  call void @encrypt()
  %53 = getelementptr inbounds [27 x i8], [27 x i8]* @.str10, i32 0, i32 0
  %54 = call i32 (i8*, ...)* @printf(i8* %53)
  %55 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %56 = call i32 (i8*, ...)* @scanf(i8* %55, i32* @C)
  call void @decrypt()
  %57 = load i32, i32* %1, align 4
  ret i32 %57
}
