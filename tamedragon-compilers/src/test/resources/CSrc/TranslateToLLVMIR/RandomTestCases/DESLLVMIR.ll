@SB = common global [32 x i8] zeroinitializer, align 16
@.str = private unnamed_addr constant [5 x i8] c"0000\00", align 1
@.str1 = private unnamed_addr constant [5 x i8] c"0001\00", align 1
@.str2 = private unnamed_addr constant [5 x i8] c"0010\00", align 1
@.str3 = private unnamed_addr constant [5 x i8] c"0011\00", align 1
@.str4 = private unnamed_addr constant [5 x i8] c"0100\00", align 1
@.str5 = private unnamed_addr constant [5 x i8] c"0101\00", align 1
@.str6 = private unnamed_addr constant [5 x i8] c"0110\00", align 1
@.str7 = private unnamed_addr constant [5 x i8] c"0111\00", align 1
@.str8 = private unnamed_addr constant [5 x i8] c"1000\00", align 1
@.str9 = private unnamed_addr constant [5 x i8] c"1001\00", align 1
@.str10 = private unnamed_addr constant [5 x i8] c"1010\00", align 1
@.str11 = private unnamed_addr constant [5 x i8] c"1011\00", align 1
@.str12 = private unnamed_addr constant [5 x i8] c"1100\00", align 1
@.str13 = private unnamed_addr constant [5 x i8] c"1101\00", align 1
@.str14 = private unnamed_addr constant [5 x i8] c"1110\00", align 1
@.str15 = private unnamed_addr constant [5 x i8] c"1111\00", align 1
@bin = global [16 x i8*] [i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str1, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str2, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str3, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str4, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str5, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str6, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str7, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str8, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str9, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str10, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str11, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str12, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str13, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str14, i32 0, i32 0), i8* getelementptr inbounds [5 x i8], ([5 x i8]* @.str15, i32 0, i32 0)], align 16
@hex = global [16 x i8] c"0123456789ABCDEF", align 16
@PC1 = global [8 x [7 x i32]] [[7 x i32] [i32 57, i32 49, i32 41, i32 33, i32 25, i32 17, i32 9], [7 x i32] [i32 1, i32 58, i32 50, i32 42, i32 34, i32 26, i32 18], [7 x i32] [i32 10, i32 2, i32 59, i32 51, i32 43, i32 35, i32 27], [7 x i32] [i32 19, i32 11, i32 3, i32 60, i32 52, i32 44, i32 36], [7 x i32] [i32 63, i32 55, i32 47, i32 39, i32 31, i32 23, i32 15], [7 x i32] [i32 7, i32 62, i32 54, i32 46, i32 38, i32 30, i32 22], [7 x i32] [i32 14, i32 6, i32 61, i32 53, i32 45, i32 37, i32 29], [7 x i32] [i32 21, i32 13, i32 5, i32 28, i32 20, i32 12, i32 4]], align 16
@PC2 = global [8 x [6 x i32]] [[6 x i32] [i32 14, i32 17, i32 11, i32 24, i32 1, i32 5], [6 x i32] [i32 3, i32 28, i32 15, i32 6, i32 21, i32 10], [6 x i32] [i32 23, i32 19, i32 12, i32 4, i32 26, i32 8], [6 x i32] [i32 16, i32 7, i32 27, i32 20, i32 13, i32 2], [6 x i32] [i32 41, i32 52, i32 31, i32 37, i32 47, i32 55], [6 x i32] [i32 30, i32 40, i32 51, i32 45, i32 33, i32 48], [6 x i32] [i32 44, i32 49, i32 39, i32 56, i32 34, i32 53], [6 x i32] [i32 46, i32 42, i32 50, i32 36, i32 29, i32 32]], align 16
@IP = global [8 x [8 x i32]] [[8 x i32] [i32 58, i32 50, i32 42, i32 34, i32 26, i32 18, i32 10, i32 2], [8 x i32] [i32 60, i32 52, i32 44, i32 36, i32 28, i32 20, i32 12, i32 4], [8 x i32] [i32 62, i32 54, i32 46, i32 38, i32 30, i32 22, i32 14, i32 6], [8 x i32] [i32 64, i32 56, i32 48, i32 40, i32 32, i32 24, i32 16, i32 8], [8 x i32] [i32 57, i32 49, i32 41, i32 33, i32 25, i32 17, i32 9, i32 1], [8 x i32] [i32 59, i32 51, i32 43, i32 35, i32 27, i32 19, i32 11, i32 3], [8 x i32] [i32 61, i32 53, i32 45, i32 37, i32 29, i32 21, i32 13, i32 5], [8 x i32] [i32 63, i32 55, i32 47, i32 39, i32 31, i32 23, i32 15, i32 7]], align 16
@E_bit = global [8 x [6 x i32]] [[6 x i32] [i32 32, i32 1, i32 2, i32 3, i32 4, i32 5], [6 x i32] [i32 4, i32 5, i32 6, i32 7, i32 8, i32 9], [6 x i32] [i32 8, i32 9, i32 10, i32 11, i32 12, i32 13], [6 x i32] [i32 12, i32 13, i32 14, i32 15, i32 16, i32 17], [6 x i32] [i32 16, i32 17, i32 18, i32 19, i32 20, i32 21], [6 x i32] [i32 20, i32 21, i32 22, i32 23, i32 24, i32 25], [6 x i32] [i32 24, i32 25, i32 26, i32 27, i32 28, i32 29], [6 x i32] [i32 28, i32 29, i32 30, i32 31, i32 32, i32 1]], align 16
@.str16 = private unnamed_addr constant [3 x i8] c"00\00", align 1
@.str17 = private unnamed_addr constant [3 x i8] c"01\00", align 1
@.str18 = private unnamed_addr constant [3 x i8] c"10\00", align 1
@.str19 = private unnamed_addr constant [3 x i8] c"11\00", align 1
@look_up = global [4 x i8*] [i8* getelementptr inbounds [3 x i8], ([3 x i8]* @.str16, i32 0, i32 0), i8* getelementptr inbounds [3 x i8], ([3 x i8]* @.str17, i32 0, i32 0), i8* getelementptr inbounds [3 x i8], ([3 x i8]* @.str18, i32 0, i32 0), i8* getelementptr inbounds [3 x i8], ([3 x i8]* @.str19, i32 0, i32 0)], align 16
@sb_permutation = global [8 x [4 x i32]] [[4 x i32] [i32 16, i32 7, i32 20, i32 21], [4 x i32] [i32 29, i32 12, i32 28, i32 17], [4 x i32] [i32 1, i32 15, i32 23, i32 26], [4 x i32] [i32 5, i32 18, i32 31, i32 10], [4 x i32] [i32 2, i32 8, i32 24, i32 14], [4 x i32] [i32 32, i32 27, i32 3, i32 9], [4 x i32] [i32 19, i32 13, i32 30, i32 6], [4 x i32] [i32 22, i32 11, i32 4, i32 25]], align 16
@s1 = global [4 x [16 x i32]] [[16 x i32] [i32 14, i32 4, i32 13, i32 1, i32 2, i32 15, i32 11, i32 8, i32 3, i32 10, i32 6, i32 12, i32 5, i32 9, i32 0, i32 7], [16 x i32] [i32 0, i32 15, i32 7, i32 4, i32 14, i32 2, i32 13, i32 1, i32 10, i32 6, i32 12, i32 11, i32 9, i32 5, i32 3, i32 8], [16 x i32] [i32 4, i32 1, i32 14, i32 8, i32 13, i32 6, i32 2, i32 11, i32 15, i32 12, i32 9, i32 7, i32 3, i32 10, i32 5, i32 0], [16 x i32] [i32 15, i32 12, i32 8, i32 2, i32 4, i32 9, i32 1, i32 7, i32 5, i32 11, i32 3, i32 14, i32 10, i32 0, i32 6, i32 13]], align 16
@s2 = global [4 x [16 x i32]] [[16 x i32] [i32 15, i32 1, i32 8, i32 14, i32 6, i32 11, i32 3, i32 4, i32 9, i32 7, i32 2, i32 13, i32 12, i32 0, i32 5, i32 10], [16 x i32] [i32 3, i32 13, i32 4, i32 7, i32 15, i32 2, i32 8, i32 14, i32 12, i32 0, i32 1, i32 10, i32 6, i32 9, i32 11, i32 5], [16 x i32] [i32 0, i32 14, i32 7, i32 11, i32 10, i32 4, i32 13, i32 1, i32 5, i32 8, i32 12, i32 6, i32 9, i32 3, i32 2, i32 15], [16 x i32] [i32 13, i32 8, i32 10, i32 1, i32 3, i32 15, i32 4, i32 2, i32 11, i32 6, i32 7, i32 12, i32 0, i32 5, i32 14, i32 9]], align 16
@s3 = global [4 x [16 x i32]] [[16 x i32] [i32 10, i32 0, i32 9, i32 14, i32 6, i32 3, i32 15, i32 5, i32 1, i32 13, i32 12, i32 7, i32 11, i32 4, i32 2, i32 8], [16 x i32] [i32 13, i32 7, i32 0, i32 9, i32 3, i32 4, i32 6, i32 10, i32 2, i32 8, i32 5, i32 14, i32 12, i32 11, i32 15, i32 1], [16 x i32] [i32 13, i32 6, i32 4, i32 9, i32 8, i32 15, i32 3, i32 0, i32 11, i32 1, i32 2, i32 12, i32 5, i32 10, i32 14, i32 7], [16 x i32] [i32 1, i32 10, i32 13, i32 0, i32 6, i32 9, i32 8, i32 7, i32 4, i32 15, i32 14, i32 3, i32 11, i32 5, i32 2, i32 12]], align 16
@s4 = global [4 x [16 x i32]] [[16 x i32] [i32 7, i32 13, i32 14, i32 3, i32 0, i32 6, i32 9, i32 10, i32 1, i32 2, i32 8, i32 5, i32 11, i32 12, i32 4, i32 15], [16 x i32] [i32 13, i32 8, i32 11, i32 5, i32 6, i32 15, i32 0, i32 3, i32 4, i32 7, i32 2, i32 12, i32 1, i32 10, i32 14, i32 9], [16 x i32] [i32 10, i32 6, i32 9, i32 0, i32 12, i32 11, i32 7, i32 13, i32 15, i32 1, i32 3, i32 14, i32 5, i32 2, i32 8, i32 4], [16 x i32] [i32 3, i32 15, i32 0, i32 6, i32 10, i32 1, i32 13, i32 8, i32 9, i32 4, i32 5, i32 11, i32 12, i32 7, i32 2, i32 14]], align 16
@s5 = global [4 x [16 x i32]] [[16 x i32] [i32 2, i32 12, i32 4, i32 1, i32 7, i32 10, i32 11, i32 6, i32 8, i32 5, i32 3, i32 15, i32 13, i32 0, i32 14, i32 9], [16 x i32] [i32 14, i32 11, i32 2, i32 12, i32 4, i32 7, i32 13, i32 1, i32 5, i32 0, i32 15, i32 10, i32 3, i32 9, i32 8, i32 6], [16 x i32] [i32 4, i32 2, i32 1, i32 11, i32 10, i32 13, i32 7, i32 8, i32 15, i32 9, i32 12, i32 5, i32 6, i32 3, i32 0, i32 14], [16 x i32] [i32 11, i32 8, i32 12, i32 7, i32 1, i32 14, i32 2, i32 13, i32 6, i32 15, i32 0, i32 9, i32 10, i32 4, i32 5, i32 3]], align 16
@s6 = global [4 x [16 x i32]] [[16 x i32] [i32 12, i32 1, i32 10, i32 15, i32 9, i32 2, i32 6, i32 8, i32 0, i32 13, i32 3, i32 4, i32 14, i32 7, i32 5, i32 11], [16 x i32] [i32 10, i32 15, i32 4, i32 2, i32 7, i32 12, i32 9, i32 5, i32 6, i32 1, i32 12, i32 14, i32 0, i32 11, i32 3, i32 8], [16 x i32] [i32 9, i32 14, i32 15, i32 5, i32 2, i32 8, i32 12, i32 3, i32 7, i32 0, i32 4, i32 10, i32 1, i32 13, i32 11, i32 6], [16 x i32] [i32 4, i32 3, i32 2, i32 12, i32 9, i32 5, i32 15, i32 10, i32 11, i32 14, i32 1, i32 7, i32 6, i32 0, i32 8, i32 13]], align 16
@s7 = global [4 x [16 x i32]] [[16 x i32] [i32 4, i32 11, i32 2, i32 14, i32 15, i32 0, i32 8, i32 13, i32 3, i32 12, i32 9, i32 7, i32 5, i32 10, i32 6, i32 1], [16 x i32] [i32 13, i32 0, i32 11, i32 7, i32 4, i32 9, i32 1, i32 10, i32 14, i32 3, i32 5, i32 12, i32 2, i32 15, i32 8, i32 6], [16 x i32] [i32 1, i32 4, i32 11, i32 13, i32 12, i32 3, i32 7, i32 14, i32 10, i32 15, i32 6, i32 8, i32 0, i32 5, i32 9, i32 2], [16 x i32] [i32 6, i32 11, i32 13, i32 8, i32 1, i32 4, i32 10, i32 7, i32 9, i32 5, i32 0, i32 15, i32 14, i32 2, i32 3, i32 12]], align 16
@s8 = global [4 x [16 x i32]] [[16 x i32] [i32 13, i32 2, i32 8, i32 4, i32 6, i32 15, i32 11, i32 1, i32 10, i32 9, i32 3, i32 14, i32 5, i32 0, i32 12, i32 7], [16 x i32] [i32 1, i32 15, i32 13, i32 8, i32 10, i32 3, i32 7, i32 4, i32 12, i32 5, i32 6, i32 11, i32 0, i32 14, i32 9, i32 2], [16 x i32] [i32 7, i32 11, i32 4, i32 1, i32 9, i32 12, i32 14, i32 2, i32 0, i32 6, i32 10, i32 13, i32 15, i32 3, i32 5, i32 8], [16 x i32] [i32 2, i32 1, i32 14, i32 7, i32 4, i32 10, i32 8, i32 13, i32 15, i32 12, i32 9, i32 0, i32 3, i32 5, i32 6, i32 11]], align 16
@ip_inverse = global [8 x [8 x i32]] [[8 x i32] [i32 40, i32 8, i32 48, i32 16, i32 56, i32 24, i32 64, i32 32], [8 x i32] [i32 39, i32 7, i32 47, i32 15, i32 55, i32 23, i32 63, i32 31], [8 x i32] [i32 38, i32 6, i32 46, i32 14, i32 54, i32 22, i32 62, i32 30], [8 x i32] [i32 37, i32 5, i32 45, i32 13, i32 53, i32 21, i32 61, i32 29], [8 x i32] [i32 36, i32 4, i32 44, i32 12, i32 52, i32 20, i32 60, i32 28], [8 x i32] [i32 35, i32 3, i32 43, i32 11, i32 51, i32 19, i32 59, i32 27], [8 x i32] [i32 34, i32 2, i32 42, i32 10, i32 50, i32 18, i32 58, i32 26], [8 x i32] [i32 33, i32 1, i32 41, i32 9, i32 49, i32 17, i32 57, i32 25]], align 16
@main.key_hex = internal constant [16 x i8] c"133457799BBCDFF1", align 16
@.str20 = private unnamed_addr constant [21 x i8] c">Enter plain text : \00", align 16
@.str21 = private unnamed_addr constant [44 x i8] c"\0A>Key in Hexadecimal used for encryption : \00", align 16
@.str22 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str23 = private unnamed_addr constant [23 x i8] c"\0A\0A>Encrypted Output : \00", align 16
@.str24 = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str25 = private unnamed_addr constant [37 x i8] c"\0A\0A>Decrypted Output in Hexadecimal: \00", align 16
@.str26 = private unnamed_addr constant [35 x i8] c"\0A>Decrypted Output in Plain Text: \00", align 16
@.str27 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1

declare i8* @gets(i8*) 

declare i32 @printf(i8*, ...) 

declare i32 @strlen(i8*) 

declare i32 @strcmp(i8*, i8*) 

declare noalias i8* @malloc(i64) nounwind

define void @hex_to_bin(i8* %input, i8* %in) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i16, align 2
  %j = alloca i16, align 2
  %k = alloca i16, align 2
  %lim = alloca i16, align 2
  store i8* %input, i8** %1, align 8
  store i8* %in, i8** %2, align 8
  store i16 0, i16* %lim, align 2
  store i16 0, i16* %i, align 2
  br label %3

; <label>:3 		; preds = %0, %52
  %4 = load i16, i16* %i, align 2
  %5 = sext i16 %4 to i32
  %6 = icmp slt i32 %5, 16
  br i1 %6, label %7, label %55

; <label>:7    		; preds = %3
  store i16 0, i16* %j, align 2
  br label %8

; <label>:8 		; preds = %7, %48
  %9 = load i16, i16* %j, align 2
  %10 = sext i16 %9 to i32
  %11 = icmp slt i32 %10, 16
  br i1 %11, label %12, label %51

; <label>:12                                             		; preds = %8
  %13 = load i8*, i8** %1, align 8
  %14 = load i16, i16* %i, align 2
  %15 = sext i16 %14 to i32
  %16 = getelementptr inbounds i8, i8* %13, i32 %15
  %17 = load i16, i16* %j, align 2
  %18 = sext i16 %17 to i32
  %19 = getelementptr inbounds [16 x i8], [16 x i8]* @hex, i32 0, i32 %18
  %20 = load i8, i8* %16, align 1
  %21 = load i8, i8* %19, align 1
  %22 = icmp eq i8 %20, %21
  br i1 %22, label %23, label %47

; <label>:23  		; preds = %12
  store i16 0, i16* %k, align 2
  br label %24

; <label>:24		; preds = %23, %43
  %25 = load i16, i16* %k, align 2
  %26 = sext i16 %25 to i32
  %27 = icmp slt i32 %26, 4
  br i1 %27, label %28, label %46

; <label>:28      		; preds = %24
  %29 = load i16, i16* %j, align 2
  %30 = sext i16 %29 to i32
  %31 = getelementptr inbounds [16 x i8*], [16 x i8*]* @bin, i32 0, i32 %30
  %32 = load i8*, i8** %31, align 8
  %33 = load i16, i16* %k, align 2
  %34 = sext i16 %33 to i32
  %35 = getelementptr inbounds i8, i8* %32, i32 %34
  %36 = load i8*, i8** %2, align 8
  %37 = load i16, i16* %lim, align 2
  %38 = sext i16 %37 to i32
  %39 = getelementptr inbounds i8, i8* %36, i32 %38
  %40 = load i8, i8* %35, align 1
  store i8 %40, i8* %39, align 1
  %41 = load i16, i16* %lim, align 2
  %42 = add i16 %41, 1
  store i16 %42, i16* %lim, align 2
  br label %43

; <label>:43     		; preds = %28
  %44 = load i16, i16* %k, align 2
  %45 = add i16 %44, 1
  store i16 %45, i16* %k, align 2
  br label %24

; <label>:46		; preds = %24
  br label %47

; <label>:47		; preds = %12, %46
  br label %48

; <label>:48     		; preds = %47
  %49 = load i16, i16* %j, align 2
  %50 = add i16 %49, 1
  store i16 %50, i16* %j, align 2
  br label %8

; <label>:51		; preds = %8
  br label %52

; <label>:52     		; preds = %51
  %53 = load i16, i16* %i, align 2
  %54 = add i16 %53, 1
  store i16 %54, i16* %i, align 2
  br label %3

; <label>:55		; preds = %3
  ret void
}

define i8* @bin_to_hex(i8* %bit) nounwind {
  %1 = alloca i8*, align 8
  %tmp = alloca [5 x i8], align 1
  %out = alloca i8*, align 8
  %lim = alloca i16, align 2
  %i = alloca i16, align 2
  %j = alloca i16, align 2
  store i8* %bit, i8** %1, align 8
  store i16 0, i16* %lim, align 2
  %2 = call i8* @malloc(i64 16) nounwind
  store i8* %2, i8** %out, align 8
  store i16 0, i16* %i, align 2
  br label %3

; <label>:3 		; preds = %0, %63
  %4 = load i16, i16* %i, align 2
  %5 = sext i16 %4 to i32
  %6 = icmp slt i32 %5, 64
  br i1 %6, label %7, label %68

; <label>:7                                          		; preds = %3
  %8 = load i8*, i8** %1, align 8
  %9 = load i16, i16* %i, align 2
  %10 = sext i16 %9 to i32
  %11 = getelementptr inbounds i8, i8* %8, i32 %10
  %12 = getelementptr inbounds [5 x i8], [5 x i8]* %tmp, i32 0, i32 0
  %13 = load i8, i8* %11, align 1
  store i8 %13, i8* %12, align 1
  %14 = load i8*, i8** %1, align 8
  %15 = load i16, i16* %i, align 2
  %16 = sext i16 %15 to i32
  %17 = add i32 %16, 1
  %18 = getelementptr inbounds i8, i8* %14, i32 %17
  %19 = getelementptr inbounds [5 x i8], [5 x i8]* %tmp, i32 0, i32 1
  %20 = load i8, i8* %18, align 1
  store i8 %20, i8* %19, align 1
  %21 = load i8*, i8** %1, align 8
  %22 = load i16, i16* %i, align 2
  %23 = sext i16 %22 to i32
  %24 = add i32 %23, 2
  %25 = getelementptr inbounds i8, i8* %21, i32 %24
  %26 = getelementptr inbounds [5 x i8], [5 x i8]* %tmp, i32 0, i32 2
  %27 = load i8, i8* %25, align 1
  store i8 %27, i8* %26, align 1
  %28 = load i8*, i8** %1, align 8
  %29 = load i16, i16* %i, align 2
  %30 = sext i16 %29 to i32
  %31 = add i32 %30, 3
  %32 = getelementptr inbounds i8, i8* %28, i32 %31
  %33 = getelementptr inbounds [5 x i8], [5 x i8]* %tmp, i32 0, i32 3
  %34 = load i8, i8* %32, align 1
  store i8 %34, i8* %33, align 1
  %35 = getelementptr inbounds [5 x i8], [5 x i8]* %tmp, i32 0, i32 4
  store i8 0, i8* %35, align 1
  store i16 0, i16* %j, align 2
  br label %36

; <label>:36 		; preds = %7, %59
  %37 = load i16, i16* %j, align 2
  %38 = sext i16 %37 to i32
  %39 = icmp slt i32 %38, 16
  br i1 %39, label %40, label %62

; <label>:40                                              		; preds = %36
  %41 = getelementptr inbounds [5 x i8], [5 x i8]* %tmp, i32 0, i32 0
  %42 = load i16, i16* %j, align 2
  %43 = sext i16 %42 to i32
  %44 = getelementptr inbounds [16 x i8*], [16 x i8*]* @bin, i32 0, i32 %43
  %45 = load i8*, i8** %44, align 8
  %46 = call i32 @strcmp(i8* %41, i8* %45)
  %47 = icmp eq i32 %46, 0
  br i1 %47, label %48, label %58

; <label>:48                      		; preds = %40
  %49 = load i16, i16* %lim, align 2
  %50 = add i16 %49, 1
  store i16 %50, i16* %lim, align 2
  %51 = load i16, i16* %j, align 2
  %52 = sext i16 %51 to i32
  %53 = getelementptr inbounds [16 x i8], [16 x i8]* @hex, i32 0, i32 %52
  %54 = load i8*, i8** %out, align 8
  %55 = sext i16 %49 to i32
  %56 = getelementptr inbounds i8, i8* %54, i32 %55
  %57 = load i8, i8* %53, align 1
  store i8 %57, i8* %56, align 1
  br label %62

; <label>:58		; preds = %40
  br label %59

; <label>:59     		; preds = %58
  %60 = load i16, i16* %j, align 2
  %61 = add i16 %60, 1
  store i16 %61, i16* %j, align 2
  br label %36

; <label>:62		; preds = %36, %48
  br label %63

; <label>:63     		; preds = %62
  %64 = load i16, i16* %i, align 2
  %65 = sext i16 %64 to i32
  %66 = add i32 %65, 4
  %67 = trunc i32 %66 to i16
  store i16 %67, i16* %i, align 2
  br label %3

; <label>:68                       		; preds = %3
  %69 = load i8*, i8** %out, align 8
  %70 = load i16, i16* %lim, align 2
  %71 = sext i16 %70 to i32
  %72 = getelementptr inbounds i8, i8* %69, i32 %71
  store i8 0, i8* %72, align 1
  %73 = load i8*, i8** %out, align 8
  ret i8* %73
}

define void @permutation(i8* %key_bin, i8* %key_PC1) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i16, align 2
  %j = alloca i16, align 2
  %k = alloca i16, align 2
  %temp = alloca i16, align 2
  store i8* %key_bin, i8** %1, align 8
  store i8* %key_PC1, i8** %2, align 8
  store i16 0, i16* %k, align 2
  store i16 0, i16* %i, align 2
  br label %3

; <label>:3 		; preds = %0, %37
  %4 = load i16, i16* %i, align 2
  %5 = sext i16 %4 to i32
  %6 = icmp slt i32 %5, 8
  br i1 %6, label %7, label %40

; <label>:7    		; preds = %3
  store i16 0, i16* %j, align 2
  br label %8

; <label>:8 		; preds = %7, %33
  %9 = load i16, i16* %j, align 2
  %10 = sext i16 %9 to i32
  %11 = icmp slt i32 %10, 7
  br i1 %11, label %12, label %36

; <label>:12                       		; preds = %8
  %13 = load i16, i16* %i, align 2
  %14 = sext i16 %13 to i32
  %15 = getelementptr inbounds [8 x [7 x i32]], [8 x [7 x i32]]* @PC1, i32 0, i32 %14
  %16 = load i16, i16* %j, align 2
  %17 = sext i16 %16 to i32
  %18 = getelementptr inbounds [7 x i32], [7 x i32]* %15, i32 0, i32 %17
  %19 = load i32, i32* %18, align 4
  %20 = sub i32 %19, 1
  %21 = trunc i32 %20 to i16
  store i16 %21, i16* %temp, align 2
  %22 = load i8*, i8** %1, align 8
  %23 = load i16, i16* %temp, align 2
  %24 = sext i16 %23 to i32
  %25 = getelementptr inbounds i8, i8* %22, i32 %24
  %26 = load i8*, i8** %2, align 8
  %27 = load i16, i16* %k, align 2
  %28 = sext i16 %27 to i32
  %29 = getelementptr inbounds i8, i8* %26, i32 %28
  %30 = load i8, i8* %25, align 1
  store i8 %30, i8* %29, align 1
  %31 = load i16, i16* %k, align 2
  %32 = add i16 %31, 1
  store i16 %32, i16* %k, align 2
  br label %33

; <label>:33     		; preds = %12
  %34 = load i16, i16* %j, align 2
  %35 = add i16 %34, 1
  store i16 %35, i16* %j, align 2
  br label %8

; <label>:36		; preds = %8
  br label %37

; <label>:37     		; preds = %36
  %38 = load i16, i16* %i, align 2
  %39 = add i16 %38, 1
  store i16 %39, i16* %i, align 2
  br label %3

; <label>:40		; preds = %3
  ret void
}

define void @make_half(i8* %key_PC1, i8* %a, i8* %b) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store i8* %key_PC1, i8** %1, align 8
  store i8* %a, i8** %2, align 8
  store i8* %b, i8** %3, align 8
  store i32 0, i32* %j, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %29
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 56
  br i1 %6, label %7, label %32

; <label>:7      		; preds = %4
  %8 = load i32, i32* %i, align 4
  %9 = icmp slt i32 %8, 28
  br i1 %9, label %10, label %18

; <label>:10                       		; preds = %7
  %11 = load i8*, i8** %1, align 8
  %12 = load i32, i32* %i, align 4
  %13 = getelementptr inbounds i8, i8* %11, i32 %12
  %14 = load i8*, i8** %2, align 8
  %15 = load i32, i32* %i, align 4
  %16 = getelementptr inbounds i8, i8* %14, i32 %15
  %17 = load i8, i8* %13, align 1
  store i8 %17, i8* %16, align 1
  br label %28

; <label>:18                       		; preds = %7
  %19 = load i8*, i8** %1, align 8
  %20 = load i32, i32* %i, align 4
  %21 = getelementptr inbounds i8, i8* %19, i32 %20
  %22 = load i8*, i8** %3, align 8
  %23 = load i32, i32* %j, align 4
  %24 = getelementptr inbounds i8, i8* %22, i32 %23
  %25 = load i8, i8* %21, align 1
  store i8 %25, i8* %24, align 1
  %26 = load i32, i32* %j, align 4
  %27 = add i32 %26, 1
  store i32 %27, i32* %j, align 4
  br label %28

; <label>:28		; preds = %10, %18
  br label %29

; <label>:29     		; preds = %28
  %30 = load i32, i32* %i, align 4
  %31 = add i32 %30, 1
  store i32 %31, i32* %i, align 4
  br label %4

; <label>:32		; preds = %4
  ret void
}

define void @single_shift(i8* %p, i8* %q) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i32, align 4
  store i8* %p, i8** %1, align 8
  store i8* %q, i8** %2, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = getelementptr inbounds i8, i8* %3, i32 0
  %5 = load i8*, i8** %2, align 8
  %6 = getelementptr inbounds i8, i8* %5, i32 27
  %7 = load i8, i8* %4, align 1
  store i8 %7, i8* %6, align 1
  store i32 0, i32* %i, align 4
  br label %8

; <label>:8 		; preds = %0, %20
  %9 = load i32, i32* %i, align 4
  %10 = icmp slt i32 %9, 27
  br i1 %10, label %11, label %23

; <label>:11                       		; preds = %8
  %12 = load i8*, i8** %1, align 8
  %13 = load i32, i32* %i, align 4
  %14 = add i32 %13, 1
  %15 = getelementptr inbounds i8, i8* %12, i32 %14
  %16 = load i8*, i8** %2, align 8
  %17 = load i32, i32* %i, align 4
  %18 = getelementptr inbounds i8, i8* %16, i32 %17
  %19 = load i8, i8* %15, align 1
  store i8 %19, i8* %18, align 1
  br label %20

; <label>:20     		; preds = %11
  %21 = load i32, i32* %i, align 4
  %22 = add i32 %21, 1
  store i32 %22, i32* %i, align 4
  br label %8

; <label>:23		; preds = %8
  ret void
}

define void @double_shift(i8* %p, i8* %q) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i32, align 4
  store i8* %p, i8** %1, align 8
  store i8* %q, i8** %2, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = getelementptr inbounds i8, i8* %3, i32 0
  %5 = load i8*, i8** %2, align 8
  %6 = getelementptr inbounds i8, i8* %5, i32 26
  %7 = load i8, i8* %4, align 1
  store i8 %7, i8* %6, align 1
  %8 = load i8*, i8** %1, align 8
  %9 = getelementptr inbounds i8, i8* %8, i32 1
  %10 = load i8*, i8** %2, align 8
  %11 = getelementptr inbounds i8, i8* %10, i32 27
  %12 = load i8, i8* %9, align 1
  store i8 %12, i8* %11, align 1
  store i32 0, i32* %i, align 4
  br label %13

; <label>:13 		; preds = %0, %25
  %14 = load i32, i32* %i, align 4
  %15 = icmp slt i32 %14, 26
  br i1 %15, label %16, label %28

; <label>:16                      		; preds = %13
  %17 = load i8*, i8** %1, align 8
  %18 = load i32, i32* %i, align 4
  %19 = add i32 %18, 2
  %20 = getelementptr inbounds i8, i8* %17, i32 %19
  %21 = load i8*, i8** %2, align 8
  %22 = load i32, i32* %i, align 4
  %23 = getelementptr inbounds i8, i8* %21, i32 %22
  %24 = load i8, i8* %20, align 1
  store i8 %24, i8* %23, align 1
  br label %25

; <label>:25     		; preds = %16
  %26 = load i32, i32* %i, align 4
  %27 = add i32 %26, 1
  store i32 %27, i32* %i, align 4
  br label %13

; <label>:28		; preds = %13
  ret void
}

define void @make_key(i8* %a, i8* %b, i8* %c) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %i = alloca i32, align 4
  store i8* %a, i8** %1, align 8
  store i8* %b, i8** %2, align 8
  store i8* %c, i8** %3, align 8
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %15
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 28
  br i1 %6, label %7, label %18

; <label>:7                        		; preds = %4
  %8 = load i8*, i8** %1, align 8
  %9 = load i32, i32* %i, align 4
  %10 = getelementptr inbounds i8, i8* %8, i32 %9
  %11 = load i8*, i8** %3, align 8
  %12 = load i32, i32* %i, align 4
  %13 = getelementptr inbounds i8, i8* %11, i32 %12
  %14 = load i8, i8* %10, align 1
  store i8 %14, i8* %13, align 1
  br label %15

; <label>:15      		; preds = %7
  %16 = load i32, i32* %i, align 4
  %17 = add i32 %16, 1
  store i32 %17, i32* %i, align 4
  br label %4

; <label>:18    		; preds = %4
  store i32 28, i32* %i, align 4
  br label %19

; <label>:19		; preds = %18, %31
  %20 = load i32, i32* %i, align 4
  %21 = icmp slt i32 %20, 56
  br i1 %21, label %22, label %34

; <label>:22                      		; preds = %19
  %23 = load i8*, i8** %2, align 8
  %24 = load i32, i32* %i, align 4
  %25 = sub i32 %24, 28
  %26 = getelementptr inbounds i8, i8* %23, i32 %25
  %27 = load i8*, i8** %3, align 8
  %28 = load i32, i32* %i, align 4
  %29 = getelementptr inbounds i8, i8* %27, i32 %28
  %30 = load i8, i8* %26, align 1
  store i8 %30, i8* %29, align 1
  br label %31

; <label>:31     		; preds = %22
  %32 = load i32, i32* %i, align 4
  %33 = add i32 %32, 1
  store i32 %33, i32* %i, align 4
  br label %19

; <label>:34		; preds = %19
  ret void
}

define void @permutation_32(i8* %SB1, i8* %f) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i16, align 2
  %j = alloca i16, align 2
  %m = alloca i16, align 2
  %temp = alloca i16, align 2
  store i8* %SB1, i8** %1, align 8
  store i8* %f, i8** %2, align 8
  store i16 0, i16* %m, align 2
  store i16 0, i16* %i, align 2
  br label %3

; <label>:3 		; preds = %0, %37
  %4 = load i16, i16* %i, align 2
  %5 = sext i16 %4 to i32
  %6 = icmp slt i32 %5, 8
  br i1 %6, label %7, label %40

; <label>:7    		; preds = %3
  store i16 0, i16* %j, align 2
  br label %8

; <label>:8 		; preds = %7, %33
  %9 = load i16, i16* %j, align 2
  %10 = sext i16 %9 to i32
  %11 = icmp slt i32 %10, 4
  br i1 %11, label %12, label %36

; <label>:12                       		; preds = %8
  %13 = load i16, i16* %i, align 2
  %14 = sext i16 %13 to i32
  %15 = getelementptr inbounds [8 x [4 x i32]], [8 x [4 x i32]]* @sb_permutation, i32 0, i32 %14
  %16 = load i16, i16* %j, align 2
  %17 = sext i16 %16 to i32
  %18 = getelementptr inbounds [4 x i32], [4 x i32]* %15, i32 0, i32 %17
  %19 = load i32, i32* %18, align 4
  %20 = sub i32 %19, 1
  %21 = trunc i32 %20 to i16
  store i16 %21, i16* %temp, align 2
  %22 = load i8*, i8** %1, align 8
  %23 = load i16, i16* %temp, align 2
  %24 = sext i16 %23 to i32
  %25 = getelementptr inbounds i8, i8* %22, i32 %24
  %26 = load i8*, i8** %2, align 8
  %27 = load i16, i16* %m, align 2
  %28 = sext i16 %27 to i32
  %29 = getelementptr inbounds i8, i8* %26, i32 %28
  %30 = load i8, i8* %25, align 1
  store i8 %30, i8* %29, align 1
  %31 = load i16, i16* %m, align 2
  %32 = add i16 %31, 1
  store i16 %32, i16* %m, align 2
  br label %33

; <label>:33     		; preds = %12
  %34 = load i16, i16* %j, align 2
  %35 = add i16 %34, 1
  store i16 %35, i16* %j, align 2
  br label %8

; <label>:36		; preds = %8
  br label %37

; <label>:37     		; preds = %36
  %38 = load i16, i16* %i, align 2
  %39 = add i16 %38, 1
  store i16 %39, i16* %i, align 2
  br label %3

; <label>:40		; preds = %3
  ret void
}

define void @permutation_48(i8* %CD, i8* %K) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i16, align 2
  %j = alloca i16, align 2
  %m = alloca i16, align 2
  %temp = alloca i16, align 2
  store i8* %CD, i8** %1, align 8
  store i8* %K, i8** %2, align 8
  store i16 0, i16* %m, align 2
  store i16 0, i16* %i, align 2
  br label %3

; <label>:3 		; preds = %0, %37
  %4 = load i16, i16* %i, align 2
  %5 = sext i16 %4 to i32
  %6 = icmp slt i32 %5, 8
  br i1 %6, label %7, label %40

; <label>:7    		; preds = %3
  store i16 0, i16* %j, align 2
  br label %8

; <label>:8 		; preds = %7, %33
  %9 = load i16, i16* %j, align 2
  %10 = sext i16 %9 to i32
  %11 = icmp slt i32 %10, 6
  br i1 %11, label %12, label %36

; <label>:12                       		; preds = %8
  %13 = load i16, i16* %i, align 2
  %14 = sext i16 %13 to i32
  %15 = getelementptr inbounds [8 x [6 x i32]], [8 x [6 x i32]]* @PC2, i32 0, i32 %14
  %16 = load i16, i16* %j, align 2
  %17 = sext i16 %16 to i32
  %18 = getelementptr inbounds [6 x i32], [6 x i32]* %15, i32 0, i32 %17
  %19 = load i32, i32* %18, align 4
  %20 = sub i32 %19, 1
  %21 = trunc i32 %20 to i16
  store i16 %21, i16* %temp, align 2
  %22 = load i8*, i8** %1, align 8
  %23 = load i16, i16* %temp, align 2
  %24 = sext i16 %23 to i32
  %25 = getelementptr inbounds i8, i8* %22, i32 %24
  %26 = load i8*, i8** %2, align 8
  %27 = load i16, i16* %m, align 2
  %28 = sext i16 %27 to i32
  %29 = getelementptr inbounds i8, i8* %26, i32 %28
  %30 = load i8, i8* %25, align 1
  store i8 %30, i8* %29, align 1
  %31 = load i16, i16* %m, align 2
  %32 = add i16 %31, 1
  store i16 %32, i16* %m, align 2
  br label %33

; <label>:33     		; preds = %12
  %34 = load i16, i16* %j, align 2
  %35 = add i16 %34, 1
  store i16 %35, i16* %j, align 2
  br label %8

; <label>:36		; preds = %8
  br label %37

; <label>:37     		; preds = %36
  %38 = load i16, i16* %i, align 2
  %39 = add i16 %38, 1
  store i16 %39, i16* %i, align 2
  br label %3

; <label>:40		; preds = %3
  ret void
}

define void @permutation_64(i8* %in, i8* %L, i8* %R) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %m = alloca i32, align 4
  %temp = alloca i32, align 4
  store i8* %in, i8** %1, align 8
  store i8* %L, i8** %2, align 8
  store i8* %R, i8** %3, align 8
  store i32 0, i32* %m, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %31
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 4
  br i1 %6, label %7, label %34

; <label>:7    		; preds = %4
  store i32 0, i32* %j, align 4
  br label %8

; <label>:8 		; preds = %7, %27
  %9 = load i32, i32* %j, align 4
  %10 = icmp slt i32 %9, 8
  br i1 %10, label %11, label %30

; <label>:11                       		; preds = %8
  %12 = load i32, i32* %i, align 4
  %13 = getelementptr inbounds [8 x [8 x i32]], [8 x [8 x i32]]* @IP, i32 0, i32 %12
  %14 = load i32, i32* %j, align 4
  %15 = getelementptr inbounds [8 x i32], [8 x i32]* %13, i32 0, i32 %14
  %16 = load i32, i32* %15, align 4
  %17 = sub i32 %16, 1
  store i32 %17, i32* %temp, align 4
  %18 = load i8*, i8** %1, align 8
  %19 = load i32, i32* %temp, align 4
  %20 = getelementptr inbounds i8, i8* %18, i32 %19
  %21 = load i8*, i8** %2, align 8
  %22 = load i32, i32* %m, align 4
  %23 = getelementptr inbounds i8, i8* %21, i32 %22
  %24 = load i8, i8* %20, align 1
  store i8 %24, i8* %23, align 1
  %25 = load i32, i32* %m, align 4
  %26 = add i32 %25, 1
  store i32 %26, i32* %m, align 4
  br label %27

; <label>:27     		; preds = %11
  %28 = load i32, i32* %j, align 4
  %29 = add i32 %28, 1
  store i32 %29, i32* %j, align 4
  br label %8

; <label>:30		; preds = %8
  br label %31

; <label>:31     		; preds = %30
  %32 = load i32, i32* %i, align 4
  %33 = add i32 %32, 1
  store i32 %33, i32* %i, align 4
  br label %4

; <label>:34   		; preds = %4
  store i32 0, i32* %m, align 4
  store i32 4, i32* %i, align 4
  br label %35

; <label>:35		; preds = %34, %62
  %36 = load i32, i32* %i, align 4
  %37 = icmp slt i32 %36, 8
  br i1 %37, label %38, label %65

; <label>:38  		; preds = %35
  store i32 0, i32* %j, align 4
  br label %39

; <label>:39		; preds = %38, %58
  %40 = load i32, i32* %j, align 4
  %41 = icmp slt i32 %40, 8
  br i1 %41, label %42, label %61

; <label>:42                      		; preds = %39
  %43 = load i32, i32* %i, align 4
  %44 = getelementptr inbounds [8 x [8 x i32]], [8 x [8 x i32]]* @IP, i32 0, i32 %43
  %45 = load i32, i32* %j, align 4
  %46 = getelementptr inbounds [8 x i32], [8 x i32]* %44, i32 0, i32 %45
  %47 = load i32, i32* %46, align 4
  %48 = sub i32 %47, 1
  store i32 %48, i32* %temp, align 4
  %49 = load i8*, i8** %1, align 8
  %50 = load i32, i32* %temp, align 4
  %51 = getelementptr inbounds i8, i8* %49, i32 %50
  %52 = load i8*, i8** %3, align 8
  %53 = load i32, i32* %m, align 4
  %54 = getelementptr inbounds i8, i8* %52, i32 %53
  %55 = load i8, i8* %51, align 1
  store i8 %55, i8* %54, align 1
  %56 = load i32, i32* %m, align 4
  %57 = add i32 %56, 1
  store i32 %57, i32* %m, align 4
  br label %58

; <label>:58     		; preds = %42
  %59 = load i32, i32* %j, align 4
  %60 = add i32 %59, 1
  store i32 %60, i32* %j, align 4
  br label %39

; <label>:61		; preds = %39
  br label %62

; <label>:62     		; preds = %61
  %63 = load i32, i32* %i, align 4
  %64 = add i32 %63, 1
  store i32 %64, i32* %i, align 4
  br label %35

; <label>:65		; preds = %35
  ret void
}

define void @des_round(i8* %L1, i8* %R1, i8* %L0, i8* %R0, i8* %ER0, i8* %K1, i8* %F1) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  %6 = alloca i8*, align 8
  %7 = alloca i8*, align 8
  %t = alloca [3 x i8], align 1
  %tp = alloca [5 x i8], align 1
  %f = alloca [32 x i8], align 16
  %temp = alloca i32, align 4
  %i = alloca i32, align 4
  %row = alloca i32, align 4
  %column = alloca i32, align 4
  %j = alloca i32, align 4
  %limit = alloca i32, align 4
  store i8* %L1, i8** %1, align 8
  store i8* %R1, i8** %2, align 8
  store i8* %L0, i8** %3, align 8
  store i8* %R0, i8** %4, align 8
  store i8* %ER0, i8** %5, align 8
  store i8* %K1, i8** %6, align 8
  store i8* %F1, i8** %7, align 8
  store i32 0, i32* %limit, align 4
  %8 = load i8*, i8** %1, align 8
  %9 = load i8*, i8** %4, align 8
  call void @copy(i8* %8, i8* %9)
  %10 = load i8*, i8** %4, align 8
  %11 = load i8*, i8** %5, align 8
  call void @permut_48(i8* %10, i8* %11)
  %12 = load i8*, i8** %6, align 8
  %13 = load i8*, i8** %5, align 8
  %14 = load i8*, i8** %7, align 8
  call void @xor(i8* %12, i8* %13, i8* %14)
  store i32 0, i32* %i, align 4
  br label %15

; <label>:15		; preds = %0, %158
  %16 = load i32, i32* %i, align 4
  %17 = icmp slt i32 %16, 48
  br i1 %17, label %18, label %161

; <label>:18                                      		; preds = %15
  %19 = load i8*, i8** %7, align 8
  %20 = load i32, i32* %i, align 4
  %21 = getelementptr inbounds i8, i8* %19, i32 %20
  %22 = getelementptr inbounds [3 x i8], [3 x i8]* %t, i32 0, i32 0
  %23 = load i8, i8* %21, align 1
  store i8 %23, i8* %22, align 1
  %24 = load i8*, i8** %7, align 8
  %25 = load i32, i32* %i, align 4
  %26 = add i32 %25, 5
  %27 = getelementptr inbounds i8, i8* %24, i32 %26
  %28 = getelementptr inbounds [3 x i8], [3 x i8]* %t, i32 0, i32 1
  %29 = load i8, i8* %27, align 1
  store i8 %29, i8* %28, align 1
  %30 = getelementptr inbounds [3 x i8], [3 x i8]* %t, i32 0, i32 2
  store i8 0, i8* %30, align 1
  store i32 0, i32* %j, align 4
  br label %31

; <label>:31		; preds = %18, %44
  %32 = load i32, i32* %j, align 4
  %33 = icmp slt i32 %32, 4
  br i1 %33, label %34, label %47

; <label>:34                                                		; preds = %31
  %35 = getelementptr inbounds [3 x i8], [3 x i8]* %t, i32 0, i32 0
  %36 = load i32, i32* %j, align 4
  %37 = getelementptr inbounds [4 x i8*], [4 x i8*]* @look_up, i32 0, i32 %36
  %38 = load i8*, i8** %37, align 8
  %39 = call i32 @strcmp(i8* %35, i8* %38)
  %40 = icmp eq i32 %39, 0
  br i1 %40, label %41, label %43

; <label>:41      		; preds = %34
  %42 = load i32, i32* %j, align 4
  store i32 %42, i32* %row, align 4
  br label %47

; <label>:43		; preds = %34
  br label %44

; <label>:44     		; preds = %43
  %45 = load i32, i32* %j, align 4
  %46 = add i32 %45, 1
  store i32 %46, i32* %j, align 4
  br label %31

; <label>:47                                  		; preds = %31, %41
  %48 = load i8*, i8** %7, align 8
  %49 = load i32, i32* %i, align 4
  %50 = add i32 %49, 1
  %51 = getelementptr inbounds i8, i8* %48, i32 %50
  %52 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 0
  %53 = load i8, i8* %51, align 1
  store i8 %53, i8* %52, align 1
  %54 = load i8*, i8** %7, align 8
  %55 = load i32, i32* %i, align 4
  %56 = add i32 %55, 2
  %57 = getelementptr inbounds i8, i8* %54, i32 %56
  %58 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 1
  %59 = load i8, i8* %57, align 1
  store i8 %59, i8* %58, align 1
  %60 = load i8*, i8** %7, align 8
  %61 = load i32, i32* %i, align 4
  %62 = add i32 %61, 3
  %63 = getelementptr inbounds i8, i8* %60, i32 %62
  %64 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 2
  %65 = load i8, i8* %63, align 1
  store i8 %65, i8* %64, align 1
  %66 = load i8*, i8** %7, align 8
  %67 = load i32, i32* %i, align 4
  %68 = add i32 %67, 4
  %69 = getelementptr inbounds i8, i8* %66, i32 %68
  %70 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 3
  %71 = load i8, i8* %69, align 1
  store i8 %71, i8* %70, align 1
  %72 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 4
  store i8 0, i8* %72, align 1
  store i32 0, i32* %j, align 4
  br label %73

; <label>:73		; preds = %47, %86
  %74 = load i32, i32* %j, align 4
  %75 = icmp slt i32 %74, 16
  br i1 %75, label %76, label %89

; <label>:76                                              		; preds = %73
  %77 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 0
  %78 = load i32, i32* %j, align 4
  %79 = getelementptr inbounds [16 x i8*], [16 x i8*]* @bin, i32 0, i32 %78
  %80 = load i8*, i8** %79, align 8
  %81 = call i32 @strcmp(i8* %77, i8* %80)
  %82 = icmp eq i32 %81, 0
  br i1 %82, label %83, label %85

; <label>:83         		; preds = %76
  %84 = load i32, i32* %j, align 4
  store i32 %84, i32* %column, align 4
  br label %89

; <label>:85		; preds = %76
  br label %86

; <label>:86     		; preds = %85
  %87 = load i32, i32* %j, align 4
  %88 = add i32 %87, 1
  store i32 %88, i32* %j, align 4
  br label %73

; <label>:89                                                                                                                                                                                     		; preds = %73, %83
  %90 = load i32, i32* %i, align 4
  switch i32 %90, label %139 [
    i32 0, label %91
    i32 6, label %97
    i32 12, label %103
    i32 18, label %109
    i32 24, label %115
    i32 30, label %121
    i32 36, label %127
    i32 42, label %133 
  ]

; <label>:91                                             		; preds = %89
  %92 = load i32, i32* %row, align 4
  %93 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s1, i32 0, i32 %92
  %94 = load i32, i32* %column, align 4
  %95 = getelementptr inbounds [16 x i32], [16 x i32]* %93, i32 0, i32 %94
  %96 = load i32, i32* %95, align 4
  store i32 %96, i32* %temp, align 4
  br label %139

; <label>:97        		; preds = %89
  %98 = load i32, i32* %row, align 4
  %99 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s2, i32 0, i32 %98
  %100 = load i32, i32* %column, align 4
  %101 = getelementptr inbounds [16 x i32], [16 x i32]* %99, i32 0, i32 %100
  %102 = load i32, i32* %101, align 4
  store i32 %102, i32* %temp, align 4
  br label %139

; <label>:103                                               		; preds = %89
  %104 = load i32, i32* %row, align 4
  %105 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s3, i32 0, i32 %104
  %106 = load i32, i32* %column, align 4
  %107 = getelementptr inbounds [16 x i32], [16 x i32]* %105, i32 0, i32 %106
  %108 = load i32, i32* %107, align 4
  store i32 %108, i32* %temp, align 4
  br label %139

; <label>:109                                               		; preds = %89
  %110 = load i32, i32* %row, align 4
  %111 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s4, i32 0, i32 %110
  %112 = load i32, i32* %column, align 4
  %113 = getelementptr inbounds [16 x i32], [16 x i32]* %111, i32 0, i32 %112
  %114 = load i32, i32* %113, align 4
  store i32 %114, i32* %temp, align 4
  br label %139

; <label>:115                                               		; preds = %89
  %116 = load i32, i32* %row, align 4
  %117 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s5, i32 0, i32 %116
  %118 = load i32, i32* %column, align 4
  %119 = getelementptr inbounds [16 x i32], [16 x i32]* %117, i32 0, i32 %118
  %120 = load i32, i32* %119, align 4
  store i32 %120, i32* %temp, align 4
  br label %139

; <label>:121                                               		; preds = %89
  %122 = load i32, i32* %row, align 4
  %123 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s6, i32 0, i32 %122
  %124 = load i32, i32* %column, align 4
  %125 = getelementptr inbounds [16 x i32], [16 x i32]* %123, i32 0, i32 %124
  %126 = load i32, i32* %125, align 4
  store i32 %126, i32* %temp, align 4
  br label %139

; <label>:127                                               		; preds = %89
  %128 = load i32, i32* %row, align 4
  %129 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s7, i32 0, i32 %128
  %130 = load i32, i32* %column, align 4
  %131 = getelementptr inbounds [16 x i32], [16 x i32]* %129, i32 0, i32 %130
  %132 = load i32, i32* %131, align 4
  store i32 %132, i32* %temp, align 4
  br label %139

; <label>:133                                               		; preds = %89
  %134 = load i32, i32* %row, align 4
  %135 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s8, i32 0, i32 %134
  %136 = load i32, i32* %column, align 4
  %137 = getelementptr inbounds [16 x i32], [16 x i32]* %135, i32 0, i32 %136
  %138 = load i32, i32* %137, align 4
  store i32 %138, i32* %temp, align 4
  br label %139

; <label>:139		; preds = %89, %91, %97, %103, %109, %115, %121, %127, %133
  store i32 0, i32* %j, align 4
  br label %140

; <label>:140		; preds = %139, %154
  %141 = load i32, i32* %j, align 4
  %142 = icmp slt i32 %141, 4
  br i1 %142, label %143, label %157

; <label>:143        		; preds = %140
  %144 = load i32, i32* %temp, align 4
  %145 = getelementptr inbounds [16 x i8*], [16 x i8*]* @bin, i32 0, i32 %144
  %146 = load i8*, i8** %145, align 8
  %147 = load i32, i32* %j, align 4
  %148 = getelementptr inbounds i8, i8* %146, i32 %147
  %149 = load i32, i32* %limit, align 4
  %150 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 %149
  %151 = load i8, i8* %148, align 1
  store i8 %151, i8* %150, align 1
  %152 = load i32, i32* %limit, align 4
  %153 = add i32 %152, 1
  store i32 %153, i32* %limit, align 4
  br label %154

; <label>:154    		; preds = %143
  %155 = load i32, i32* %j, align 4
  %156 = add i32 %155, 1
  store i32 %156, i32* %j, align 4
  br label %140

; <label>:157		; preds = %140
  br label %158

; <label>:158    		; preds = %157
  %159 = load i32, i32* %i, align 4
  %160 = add i32 %159, 6
  store i32 %160, i32* %i, align 4
  br label %15

; <label>:161                   		; preds = %15
  %162 = load i32, i32* %limit, align 4
  %163 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 %162
  store i8 0, i8* %163, align 1
  %164 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 0
  %165 = getelementptr inbounds [32 x i8], [32 x i8]* %f, i32 0, i32 0
  call void @permutation_32(i8* %164, i8* %165)
  %166 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 0
  store i8 0, i8* %166, align 1
  %167 = load i8*, i8** %3, align 8
  %168 = getelementptr inbounds [32 x i8], [32 x i8]* %f, i32 0, i32 0
  %169 = load i8*, i8** %2, align 8
  call void @xor_32(i8* %167, i8* %168, i8* %169)
  ret void
}

define void @des_round_decry(i8* %L1, i8* %R1, i8* %L0, i8* %R0, i8* %ER0, i8* %K1, i8* %F1) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  %6 = alloca i8*, align 8
  %7 = alloca i8*, align 8
  %tp = alloca [5 x i8], align 1
  %f = alloca [32 x i8], align 16
  %temp = alloca i16, align 2
  %i = alloca i16, align 2
  %row = alloca i16, align 2
  %column = alloca i16, align 2
  %j = alloca i16, align 2
  %limit = alloca i16, align 2
  store i8* %L1, i8** %1, align 8
  store i8* %R1, i8** %2, align 8
  store i8* %L0, i8** %3, align 8
  store i8* %R0, i8** %4, align 8
  store i8* %ER0, i8** %5, align 8
  store i8* %K1, i8** %6, align 8
  store i8* %F1, i8** %7, align 8
  store i16 0, i16* %limit, align 2
  %8 = load i8*, i8** %1, align 8
  %9 = load i8*, i8** %4, align 8
  call void @copy(i8* %8, i8* %9)
  %10 = load i8*, i8** %4, align 8
  %11 = load i8*, i8** %5, align 8
  call void @permut_48(i8* %10, i8* %11)
  %12 = load i8*, i8** %6, align 8
  %13 = load i8*, i8** %5, align 8
  %14 = load i8*, i8** %7, align 8
  call void @xor(i8* %12, i8* %13, i8* %14)
  store i16 0, i16* %i, align 2
  br label %15

; <label>:15		; preds = %0, %198
  %16 = load i16, i16* %i, align 2
  %17 = sext i16 %16 to i32
  %18 = icmp slt i32 %17, 48
  br i1 %18, label %19, label %203

; <label>:19                                       		; preds = %15
  %20 = load i8*, i8** %7, align 8
  %21 = load i16, i16* %i, align 2
  %22 = sext i16 %21 to i32
  %23 = getelementptr inbounds i8, i8* %20, i32 %22
  %24 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 0
  %25 = load i8, i8* %23, align 1
  store i8 %25, i8* %24, align 1
  %26 = load i8*, i8** %7, align 8
  %27 = load i16, i16* %i, align 2
  %28 = sext i16 %27 to i32
  %29 = add i32 %28, 5
  %30 = getelementptr inbounds i8, i8* %26, i32 %29
  %31 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 1
  %32 = load i8, i8* %30, align 1
  store i8 %32, i8* %31, align 1
  %33 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 2
  store i8 0, i8* %33, align 1
  store i16 0, i16* %j, align 2
  br label %34

; <label>:34		; preds = %19, %49
  %35 = load i16, i16* %j, align 2
  %36 = sext i16 %35 to i32
  %37 = icmp slt i32 %36, 4
  br i1 %37, label %38, label %52

; <label>:38                                                		; preds = %34
  %39 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 0
  %40 = load i16, i16* %j, align 2
  %41 = sext i16 %40 to i32
  %42 = getelementptr inbounds [4 x i8*], [4 x i8*]* @look_up, i32 0, i32 %41
  %43 = load i8*, i8** %42, align 8
  %44 = call i32 @strcmp(i8* %39, i8* %43)
  %45 = icmp eq i32 %44, 0
  br i1 %45, label %46, label %48

; <label>:46      		; preds = %38
  %47 = load i16, i16* %j, align 2
  store i16 %47, i16* %row, align 2
  br label %52

; <label>:48		; preds = %38
  br label %49

; <label>:49     		; preds = %48
  %50 = load i16, i16* %j, align 2
  %51 = add i16 %50, 1
  store i16 %51, i16* %j, align 2
  br label %34

; <label>:52                                  		; preds = %34, %46
  %53 = load i8*, i8** %7, align 8
  %54 = load i16, i16* %i, align 2
  %55 = sext i16 %54 to i32
  %56 = add i32 %55, 1
  %57 = getelementptr inbounds i8, i8* %53, i32 %56
  %58 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 0
  %59 = load i8, i8* %57, align 1
  store i8 %59, i8* %58, align 1
  %60 = load i8*, i8** %7, align 8
  %61 = load i16, i16* %i, align 2
  %62 = sext i16 %61 to i32
  %63 = add i32 %62, 2
  %64 = getelementptr inbounds i8, i8* %60, i32 %63
  %65 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 1
  %66 = load i8, i8* %64, align 1
  store i8 %66, i8* %65, align 1
  %67 = load i8*, i8** %7, align 8
  %68 = load i16, i16* %i, align 2
  %69 = sext i16 %68 to i32
  %70 = add i32 %69, 3
  %71 = getelementptr inbounds i8, i8* %67, i32 %70
  %72 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 2
  %73 = load i8, i8* %71, align 1
  store i8 %73, i8* %72, align 1
  %74 = load i8*, i8** %7, align 8
  %75 = load i16, i16* %i, align 2
  %76 = sext i16 %75 to i32
  %77 = add i32 %76, 4
  %78 = getelementptr inbounds i8, i8* %74, i32 %77
  %79 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 3
  %80 = load i8, i8* %78, align 1
  store i8 %80, i8* %79, align 1
  %81 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 4
  store i8 0, i8* %81, align 1
  store i16 0, i16* %j, align 2
  br label %82

; <label>:82		; preds = %52, %97
  %83 = load i16, i16* %j, align 2
  %84 = sext i16 %83 to i32
  %85 = icmp slt i32 %84, 16
  br i1 %85, label %86, label %100

; <label>:86                                              		; preds = %82
  %87 = getelementptr inbounds [5 x i8], [5 x i8]* %tp, i32 0, i32 0
  %88 = load i16, i16* %j, align 2
  %89 = sext i16 %88 to i32
  %90 = getelementptr inbounds [16 x i8*], [16 x i8*]* @bin, i32 0, i32 %89
  %91 = load i8*, i8** %90, align 8
  %92 = call i32 @strcmp(i8* %87, i8* %91)
  %93 = icmp eq i32 %92, 0
  br i1 %93, label %94, label %96

; <label>:94         		; preds = %86
  %95 = load i16, i16* %j, align 2
  store i16 %95, i16* %column, align 2
  br label %100

; <label>:96		; preds = %86
  br label %97

; <label>:97     		; preds = %96
  %98 = load i16, i16* %j, align 2
  %99 = add i16 %98, 1
  store i16 %99, i16* %j, align 2
  br label %82

; <label>:100                                                                                                                                                                                       		; preds = %82, %94
  %101 = load i16, i16* %i, align 2
  %102 = sext i16 %101 to i32
  switch i32 %102, label %175 [
    i32 0, label %103
    i32 6, label %112
    i32 12, label %121
    i32 18, label %130
    i32 24, label %139
    i32 30, label %148
    i32 36, label %157
    i32 42, label %166 
  ]

; <label>:103                                              		; preds = %100
  %104 = load i16, i16* %row, align 2
  %105 = sext i16 %104 to i32
  %106 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s1, i32 0, i32 %105
  %107 = load i16, i16* %column, align 2
  %108 = sext i16 %107 to i32
  %109 = getelementptr inbounds [16 x i32], [16 x i32]* %106, i32 0, i32 %108
  %110 = load i32, i32* %109, align 4
  %111 = trunc i32 %110 to i16
  store i16 %111, i16* %temp, align 2
  br label %175

; <label>:112                                              		; preds = %100
  %113 = load i16, i16* %row, align 2
  %114 = sext i16 %113 to i32
  %115 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s2, i32 0, i32 %114
  %116 = load i16, i16* %column, align 2
  %117 = sext i16 %116 to i32
  %118 = getelementptr inbounds [16 x i32], [16 x i32]* %115, i32 0, i32 %117
  %119 = load i32, i32* %118, align 4
  %120 = trunc i32 %119 to i16
  store i16 %120, i16* %temp, align 2
  br label %175

; <label>:121                                              		; preds = %100
  %122 = load i16, i16* %row, align 2
  %123 = sext i16 %122 to i32
  %124 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s3, i32 0, i32 %123
  %125 = load i16, i16* %column, align 2
  %126 = sext i16 %125 to i32
  %127 = getelementptr inbounds [16 x i32], [16 x i32]* %124, i32 0, i32 %126
  %128 = load i32, i32* %127, align 4
  %129 = trunc i32 %128 to i16
  store i16 %129, i16* %temp, align 2
  br label %175

; <label>:130                                              		; preds = %100
  %131 = load i16, i16* %row, align 2
  %132 = sext i16 %131 to i32
  %133 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s4, i32 0, i32 %132
  %134 = load i16, i16* %column, align 2
  %135 = sext i16 %134 to i32
  %136 = getelementptr inbounds [16 x i32], [16 x i32]* %133, i32 0, i32 %135
  %137 = load i32, i32* %136, align 4
  %138 = trunc i32 %137 to i16
  store i16 %138, i16* %temp, align 2
  br label %175

; <label>:139                                              		; preds = %100
  %140 = load i16, i16* %row, align 2
  %141 = sext i16 %140 to i32
  %142 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s5, i32 0, i32 %141
  %143 = load i16, i16* %column, align 2
  %144 = sext i16 %143 to i32
  %145 = getelementptr inbounds [16 x i32], [16 x i32]* %142, i32 0, i32 %144
  %146 = load i32, i32* %145, align 4
  %147 = trunc i32 %146 to i16
  store i16 %147, i16* %temp, align 2
  br label %175

; <label>:148                                              		; preds = %100
  %149 = load i16, i16* %row, align 2
  %150 = sext i16 %149 to i32
  %151 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s6, i32 0, i32 %150
  %152 = load i16, i16* %column, align 2
  %153 = sext i16 %152 to i32
  %154 = getelementptr inbounds [16 x i32], [16 x i32]* %151, i32 0, i32 %153
  %155 = load i32, i32* %154, align 4
  %156 = trunc i32 %155 to i16
  store i16 %156, i16* %temp, align 2
  br label %175

; <label>:157                                              		; preds = %100
  %158 = load i16, i16* %row, align 2
  %159 = sext i16 %158 to i32
  %160 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s7, i32 0, i32 %159
  %161 = load i16, i16* %column, align 2
  %162 = sext i16 %161 to i32
  %163 = getelementptr inbounds [16 x i32], [16 x i32]* %160, i32 0, i32 %162
  %164 = load i32, i32* %163, align 4
  %165 = trunc i32 %164 to i16
  store i16 %165, i16* %temp, align 2
  br label %175

; <label>:166                                              		; preds = %100
  %167 = load i16, i16* %row, align 2
  %168 = sext i16 %167 to i32
  %169 = getelementptr inbounds [4 x [16 x i32]], [4 x [16 x i32]]* @s8, i32 0, i32 %168
  %170 = load i16, i16* %column, align 2
  %171 = sext i16 %170 to i32
  %172 = getelementptr inbounds [16 x i32], [16 x i32]* %169, i32 0, i32 %171
  %173 = load i32, i32* %172, align 4
  %174 = trunc i32 %173 to i16
  store i16 %174, i16* %temp, align 2
  br label %175

; <label>:175		; preds = %100, %103, %112, %121, %130, %139, %148, %157, %166
  store i16 0, i16* %j, align 2
  br label %176

; <label>:176		; preds = %175, %194
  %177 = load i16, i16* %j, align 2
  %178 = sext i16 %177 to i32
  %179 = icmp slt i32 %178, 4
  br i1 %179, label %180, label %197

; <label>:180        		; preds = %176
  %181 = load i16, i16* %temp, align 2
  %182 = sext i16 %181 to i32
  %183 = getelementptr inbounds [16 x i8*], [16 x i8*]* @bin, i32 0, i32 %182
  %184 = load i8*, i8** %183, align 8
  %185 = load i16, i16* %j, align 2
  %186 = sext i16 %185 to i32
  %187 = getelementptr inbounds i8, i8* %184, i32 %186
  %188 = load i16, i16* %limit, align 2
  %189 = sext i16 %188 to i32
  %190 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 %189
  %191 = load i8, i8* %187, align 1
  store i8 %191, i8* %190, align 1
  %192 = load i16, i16* %limit, align 2
  %193 = add i16 %192, 1
  store i16 %193, i16* %limit, align 2
  br label %194

; <label>:194    		; preds = %180
  %195 = load i16, i16* %j, align 2
  %196 = add i16 %195, 1
  store i16 %196, i16* %j, align 2
  br label %176

; <label>:197		; preds = %176
  br label %198

; <label>:198    		; preds = %197
  %199 = load i16, i16* %i, align 2
  %200 = sext i16 %199 to i32
  %201 = add i32 %200, 6
  %202 = trunc i32 %201 to i16
  store i16 %202, i16* %i, align 2
  br label %15

; <label>:203                   		; preds = %15
  %204 = load i16, i16* %limit, align 2
  %205 = sext i16 %204 to i32
  %206 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 %205
  store i8 0, i8* %206, align 1
  %207 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 0
  %208 = getelementptr inbounds [32 x i8], [32 x i8]* %f, i32 0, i32 0
  call void @permutation_32(i8* %207, i8* %208)
  %209 = getelementptr inbounds [32 x i8], [32 x i8]* @SB, i32 0, i32 0
  store i8 0, i8* %209, align 1
  %210 = load i8*, i8** %3, align 8
  %211 = getelementptr inbounds [32 x i8], [32 x i8]* %f, i32 0, i32 0
  %212 = load i8*, i8** %2, align 8
  call void @xor_32(i8* %210, i8* %211, i8* %212)
  ret void
}

define void @copy(i8* %L, i8* %R) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i32, align 4
  store i8* %L, i8** %1, align 8
  store i8* %R, i8** %2, align 8
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %14
  %4 = load i32, i32* %i, align 4
  %5 = icmp slt i32 %4, 32
  br i1 %5, label %6, label %17

; <label>:6                        		; preds = %3
  %7 = load i8*, i8** %2, align 8
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds i8, i8* %7, i32 %8
  %10 = load i8*, i8** %1, align 8
  %11 = load i32, i32* %i, align 4
  %12 = getelementptr inbounds i8, i8* %10, i32 %11
  %13 = load i8, i8* %9, align 1
  store i8 %13, i8* %12, align 1
  br label %14

; <label>:14      		; preds = %6
  %15 = load i32, i32* %i, align 4
  %16 = add i32 %15, 1
  store i32 %16, i32* %i, align 4
  br label %3

; <label>:17		; preds = %3
  ret void
}

define void @permut_48(i8* %R, i8* %ER) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i16, align 2
  %j = alloca i16, align 2
  %m = alloca i16, align 2
  %temp = alloca i16, align 2
  store i8* %R, i8** %1, align 8
  store i8* %ER, i8** %2, align 8
  store i16 0, i16* %m, align 2
  store i16 0, i16* %i, align 2
  br label %3

; <label>:3 		; preds = %0, %37
  %4 = load i16, i16* %i, align 2
  %5 = sext i16 %4 to i32
  %6 = icmp slt i32 %5, 8
  br i1 %6, label %7, label %40

; <label>:7    		; preds = %3
  store i16 0, i16* %j, align 2
  br label %8

; <label>:8 		; preds = %7, %33
  %9 = load i16, i16* %j, align 2
  %10 = sext i16 %9 to i32
  %11 = icmp slt i32 %10, 6
  br i1 %11, label %12, label %36

; <label>:12                       		; preds = %8
  %13 = load i16, i16* %i, align 2
  %14 = sext i16 %13 to i32
  %15 = getelementptr inbounds [8 x [6 x i32]], [8 x [6 x i32]]* @E_bit, i32 0, i32 %14
  %16 = load i16, i16* %j, align 2
  %17 = sext i16 %16 to i32
  %18 = getelementptr inbounds [6 x i32], [6 x i32]* %15, i32 0, i32 %17
  %19 = load i32, i32* %18, align 4
  %20 = sub i32 %19, 1
  %21 = trunc i32 %20 to i16
  store i16 %21, i16* %temp, align 2
  %22 = load i8*, i8** %1, align 8
  %23 = load i16, i16* %temp, align 2
  %24 = sext i16 %23 to i32
  %25 = getelementptr inbounds i8, i8* %22, i32 %24
  %26 = load i8*, i8** %2, align 8
  %27 = load i16, i16* %m, align 2
  %28 = sext i16 %27 to i32
  %29 = getelementptr inbounds i8, i8* %26, i32 %28
  %30 = load i8, i8* %25, align 1
  store i8 %30, i8* %29, align 1
  %31 = load i16, i16* %m, align 2
  %32 = add i16 %31, 1
  store i16 %32, i16* %m, align 2
  br label %33

; <label>:33     		; preds = %12
  %34 = load i16, i16* %j, align 2
  %35 = add i16 %34, 1
  store i16 %35, i16* %j, align 2
  br label %8

; <label>:36		; preds = %8
  br label %37

; <label>:37     		; preds = %36
  %38 = load i16, i16* %i, align 2
  %39 = add i16 %38, 1
  store i16 %39, i16* %i, align 2
  br label %3

; <label>:40		; preds = %3
  ret void
}

define void @xor(i8* %K, i8* %ER, i8* %F) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %i = alloca i32, align 4
  %m = alloca i32, align 4
  store i8* %K, i8** %1, align 8
  store i8* %ER, i8** %2, align 8
  store i8* %F, i8** %3, align 8
  store i32 0, i32* %m, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %54
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 48
  br i1 %6, label %7, label %57

; <label>:7                      		; preds = %4
  %8 = load i8*, i8** %1, align 8
  %9 = load i32, i32* %i, align 4
  %10 = getelementptr inbounds i8, i8* %8, i32 %9
  %11 = load i8, i8* %10, align 1
  %12 = icmp eq i8 %11, 49
  br i1 %12, label %13, label %19

; <label>:13                       		; preds = %7
  %14 = load i8*, i8** %2, align 8
  %15 = load i32, i32* %i, align 4
  %16 = getelementptr inbounds i8, i8* %14, i32 %15
  %17 = load i8, i8* %16, align 1
  %18 = icmp eq i8 %17, 49
  br label %19

; <label>:19         		; preds = %7, %13
  %20 = phi i1 [ false, %7 ], [ %18, %13 ]
  %21 = zext i1 %20 to i32
  %22 = icmp ne i32 %21, 0
  br i1 %22, label %39, label %23

; <label>:23                      		; preds = %19
  %24 = load i8*, i8** %1, align 8
  %25 = load i32, i32* %i, align 4
  %26 = getelementptr inbounds i8, i8* %24, i32 %25
  %27 = load i8, i8* %26, align 1
  %28 = icmp eq i8 %27, 48
  br i1 %28, label %29, label %35

; <label>:29                      		; preds = %23
  %30 = load i8*, i8** %2, align 8
  %31 = load i32, i32* %i, align 4
  %32 = getelementptr inbounds i8, i8* %30, i32 %31
  %33 = load i8, i8* %32, align 1
  %34 = icmp eq i8 %33, 48
  br label %35

; <label>:35         		; preds = %23, %29
  %36 = phi i1 [ false, %23 ], [ %34, %29 ]
  %37 = zext i1 %36 to i32
  %38 = icmp ne i32 %37, 0
  br label %39

; <label>:39        		; preds = %19, %35
  %40 = phi i1 [ true, %19 ], [ %38, %35 ]
  br i1 %40, label %41, label %47

; <label>:41                      		; preds = %39
  %42 = load i8*, i8** %3, align 8
  %43 = load i32, i32* %m, align 4
  %44 = getelementptr inbounds i8, i8* %42, i32 %43
  store i8 48, i8* %44, align 1
  %45 = load i32, i32* %m, align 4
  %46 = add i32 %45, 1
  store i32 %46, i32* %m, align 4
  br label %53

; <label>:47                      		; preds = %39
  %48 = load i8*, i8** %3, align 8
  %49 = load i32, i32* %m, align 4
  %50 = getelementptr inbounds i8, i8* %48, i32 %49
  store i8 49, i8* %50, align 1
  %51 = load i32, i32* %m, align 4
  %52 = add i32 %51, 1
  store i32 %52, i32* %m, align 4
  br label %53

; <label>:53		; preds = %41, %47
  br label %54

; <label>:54     		; preds = %53
  %55 = load i32, i32* %i, align 4
  %56 = add i32 %55, 1
  store i32 %56, i32* %i, align 4
  br label %4

; <label>:57		; preds = %4
  ret void
}

define void @xor_32(i8* %L0, i8* %f, i8* %R1) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %i = alloca i16, align 2
  %m = alloca i16, align 2
  store i8* %L0, i8** %1, align 8
  store i8* %f, i8** %2, align 8
  store i8* %R1, i8** %3, align 8
  store i16 0, i16* %m, align 2
  store i16 0, i16* %i, align 2
  br label %4

; <label>:4 		; preds = %0, %61
  %5 = load i16, i16* %i, align 2
  %6 = sext i16 %5 to i32
  %7 = icmp slt i32 %6, 32
  br i1 %7, label %8, label %64

; <label>:8                       		; preds = %4
  %9 = load i8*, i8** %1, align 8
  %10 = load i16, i16* %i, align 2
  %11 = sext i16 %10 to i32
  %12 = getelementptr inbounds i8, i8* %9, i32 %11
  %13 = load i8, i8* %12, align 1
  %14 = icmp eq i8 %13, 49
  br i1 %14, label %15, label %22

; <label>:15                       		; preds = %8
  %16 = load i8*, i8** %2, align 8
  %17 = load i16, i16* %i, align 2
  %18 = sext i16 %17 to i32
  %19 = getelementptr inbounds i8, i8* %16, i32 %18
  %20 = load i8, i8* %19, align 1
  %21 = icmp eq i8 %20, 49
  br label %22

; <label>:22         		; preds = %8, %15
  %23 = phi i1 [ false, %8 ], [ %21, %15 ]
  %24 = zext i1 %23 to i32
  %25 = icmp ne i32 %24, 0
  br i1 %25, label %44, label %26

; <label>:26                      		; preds = %22
  %27 = load i8*, i8** %1, align 8
  %28 = load i16, i16* %i, align 2
  %29 = sext i16 %28 to i32
  %30 = getelementptr inbounds i8, i8* %27, i32 %29
  %31 = load i8, i8* %30, align 1
  %32 = icmp eq i8 %31, 48
  br i1 %32, label %33, label %40

; <label>:33                      		; preds = %26
  %34 = load i8*, i8** %2, align 8
  %35 = load i16, i16* %i, align 2
  %36 = sext i16 %35 to i32
  %37 = getelementptr inbounds i8, i8* %34, i32 %36
  %38 = load i8, i8* %37, align 1
  %39 = icmp eq i8 %38, 48
  br label %40

; <label>:40         		; preds = %26, %33
  %41 = phi i1 [ false, %26 ], [ %39, %33 ]
  %42 = zext i1 %41 to i32
  %43 = icmp ne i32 %42, 0
  br label %44

; <label>:44        		; preds = %22, %40
  %45 = phi i1 [ true, %22 ], [ %43, %40 ]
  br i1 %45, label %46, label %53

; <label>:46                      		; preds = %44
  %47 = load i8*, i8** %3, align 8
  %48 = load i16, i16* %m, align 2
  %49 = sext i16 %48 to i32
  %50 = getelementptr inbounds i8, i8* %47, i32 %49
  store i8 48, i8* %50, align 1
  %51 = load i16, i16* %m, align 2
  %52 = add i16 %51, 1
  store i16 %52, i16* %m, align 2
  br label %60

; <label>:53                      		; preds = %44
  %54 = load i8*, i8** %3, align 8
  %55 = load i16, i16* %m, align 2
  %56 = sext i16 %55 to i32
  %57 = getelementptr inbounds i8, i8* %54, i32 %56
  store i8 49, i8* %57, align 1
  %58 = load i16, i16* %m, align 2
  %59 = add i16 %58, 1
  store i16 %59, i16* %m, align 2
  br label %60

; <label>:60		; preds = %46, %53
  br label %61

; <label>:61     		; preds = %60
  %62 = load i16, i16* %i, align 2
  %63 = add i16 %62, 1
  store i16 %63, i16* %i, align 2
  br label %4

; <label>:64		; preds = %4
  ret void
}

define void @common_permutation(i8* %in, i8* %out) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %i = alloca i16, align 2
  %j = alloca i16, align 2
  %temp = alloca i16, align 2
  %m = alloca i16, align 2
  store i8* %in, i8** %1, align 8
  store i8* %out, i8** %2, align 8
  store i16 0, i16* %m, align 2
  store i16 0, i16* %i, align 2
  br label %3

; <label>:3 		; preds = %0, %37
  %4 = load i16, i16* %i, align 2
  %5 = sext i16 %4 to i32
  %6 = icmp slt i32 %5, 8
  br i1 %6, label %7, label %40

; <label>:7    		; preds = %3
  store i16 0, i16* %j, align 2
  br label %8

; <label>:8 		; preds = %7, %33
  %9 = load i16, i16* %j, align 2
  %10 = sext i16 %9 to i32
  %11 = icmp slt i32 %10, 8
  br i1 %11, label %12, label %36

; <label>:12                       		; preds = %8
  %13 = load i16, i16* %i, align 2
  %14 = sext i16 %13 to i32
  %15 = getelementptr inbounds [8 x [8 x i32]], [8 x [8 x i32]]* @ip_inverse, i32 0, i32 %14
  %16 = load i16, i16* %j, align 2
  %17 = sext i16 %16 to i32
  %18 = getelementptr inbounds [8 x i32], [8 x i32]* %15, i32 0, i32 %17
  %19 = load i32, i32* %18, align 4
  %20 = sub i32 %19, 1
  %21 = trunc i32 %20 to i16
  store i16 %21, i16* %temp, align 2
  %22 = load i8*, i8** %1, align 8
  %23 = load i16, i16* %temp, align 2
  %24 = sext i16 %23 to i32
  %25 = getelementptr inbounds i8, i8* %22, i32 %24
  %26 = load i8*, i8** %2, align 8
  %27 = load i16, i16* %m, align 2
  %28 = sext i16 %27 to i32
  %29 = getelementptr inbounds i8, i8* %26, i32 %28
  %30 = load i8, i8* %25, align 1
  store i8 %30, i8* %29, align 1
  %31 = load i16, i16* %m, align 2
  %32 = add i16 %31, 1
  store i16 %32, i16* %m, align 2
  br label %33

; <label>:33     		; preds = %12
  %34 = load i16, i16* %j, align 2
  %35 = add i16 %34, 1
  store i16 %35, i16* %j, align 2
  br label %8

; <label>:36		; preds = %8
  br label %37

; <label>:37     		; preds = %36
  %38 = load i16, i16* %i, align 2
  %39 = add i16 %38, 1
  store i16 %39, i16* %i, align 2
  br label %3

; <label>:40		; preds = %3
  ret void
}

define void @hex_to_plain(i8* %in, i8* %out, i32 %t) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %z = alloca i32, align 4
  %sum = alloca i32, align 4
  %temp = alloca [3 x i8], align 1
  store i8* %in, i8** %1, align 8
  store i8* %out, i8** %2, align 8
  store i32 %t, i32* %3, align 4
  store i32 0, i32* %j, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %78
  %5 = load i32, i32* %i, align 4
  %6 = load i32, i32* %3, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %81

; <label>:8                                           		; preds = %4
  store i32 0, i32* %sum, align 4
  %9 = load i8*, i8** %1, align 8
  %10 = load i32, i32* %i, align 4
  %11 = getelementptr inbounds i8, i8* %9, i32 %10
  %12 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 0
  %13 = load i8, i8* %11, align 1
  store i8 %13, i8* %12, align 1
  %14 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 0
  %15 = load i8, i8* %14, align 1
  %16 = sext i8 %15 to i32
  %17 = icmp sge i32 %16, 65
  br i1 %17, label %18, label %23

; <label>:18                                          		; preds = %8
  %19 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 0
  %20 = load i8, i8* %19, align 1
  %21 = sext i8 %20 to i32
  %22 = icmp sle i32 %21, 71
  br label %23

; <label>:23         		; preds = %8, %18
  %24 = phi i1 [ false, %8 ], [ %22, %18 ]
  br i1 %24, label %25, label %29

; <label>:25                                         		; preds = %23
  %26 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 0
  %27 = load i8, i8* %26, align 1
  %28 = call i32 @switch_case(i8 %27)
  store i32 %28, i32* %z, align 4
  br label %34

; <label>:29                                         		; preds = %23
  %30 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 0
  %31 = load i8, i8* %30, align 1
  %32 = sext i8 %31 to i32
  %33 = sub i32 %32, 48
  store i32 %33, i32* %z, align 4
  br label %34

; <label>:34                                    		; preds = %25, %29
  %35 = load i32, i32* %sum, align 4
  %36 = load i32, i32* %z, align 4
  %37 = mul i32 %36, 16
  %38 = add i32 %35, %37
  store i32 %38, i32* %sum, align 4
  %39 = load i8*, i8** %1, align 8
  %40 = load i32, i32* %i, align 4
  %41 = add i32 %40, 1
  %42 = getelementptr inbounds i8, i8* %39, i32 %41
  %43 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 1
  %44 = load i8, i8* %42, align 1
  store i8 %44, i8* %43, align 1
  %45 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 1
  %46 = load i8, i8* %45, align 1
  %47 = sext i8 %46 to i32
  %48 = icmp sge i32 %47, 65
  br i1 %48, label %49, label %54

; <label>:49                                         		; preds = %34
  %50 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 1
  %51 = load i8, i8* %50, align 1
  %52 = sext i8 %51 to i32
  %53 = icmp sle i32 %52, 71
  br label %54

; <label>:54         		; preds = %34, %49
  %55 = phi i1 [ false, %34 ], [ %53, %49 ]
  br i1 %55, label %56, label %60

; <label>:56                                         		; preds = %54
  %57 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 1
  %58 = load i8, i8* %57, align 1
  %59 = call i32 @switch_case(i8 %58)
  store i32 %59, i32* %z, align 4
  br label %65

; <label>:60                                         		; preds = %54
  %61 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 1
  %62 = load i8, i8* %61, align 1
  %63 = sext i8 %62 to i32
  %64 = sub i32 %63, 48
  store i32 %64, i32* %z, align 4
  br label %65

; <label>:65                 		; preds = %56, %60
  %66 = load i32, i32* %sum, align 4
  %67 = load i32, i32* %z, align 4
  %68 = mul i32 %67, 1
  %69 = add i32 %66, %68
  store i32 %69, i32* %sum, align 4
  %70 = getelementptr inbounds [3 x i8], [3 x i8]* %temp, i32 0, i32 2
  store i8 0, i8* %70, align 1
  %71 = load i8*, i8** %2, align 8
  %72 = load i32, i32* %j, align 4
  %73 = getelementptr inbounds i8, i8* %71, i32 %72
  %74 = load i32, i32* %sum, align 4
  %75 = trunc i32 %74 to i8
  store i8 %75, i8* %73, align 1
  %76 = load i32, i32* %j, align 4
  %77 = add i32 %76, 1
  store i32 %77, i32* %j, align 4
  br label %78

; <label>:78     		; preds = %65
  %79 = load i32, i32* %i, align 4
  %80 = add i32 %79, 2
  store i32 %80, i32* %i, align 4
  br label %4

; <label>:81                       		; preds = %4
  %82 = load i8*, i8** %2, align 8
  %83 = load i32, i32* %j, align 4
  %84 = getelementptr inbounds i8, i8* %82, i32 %83
  store i8 0, i8* %84, align 1
  ret void
}

define i32 @switch_case(i8 %a) nounwind {
  %1 = alloca i8, align 1
  %2 = alloca i32, align 4
  store i8 %a, i8* %1, align 1
  %3 = load i8, i8* %1, align 1
  %4 = sext i8 %3 to i32
  switch i32 %4, label %11 [
    i32 65, label %5
    i32 66, label %6
    i32 67, label %7
    i32 68, label %8
    i32 69, label %9
    i32 70, label %10 
  ]

; <label>:5		; preds = %0
  ret i32 10

; <label>:6		; preds = %0
  ret i32 11

; <label>:7		; preds = %0
  ret i32 12

; <label>:8		; preds = %0
  ret i32 13

; <label>:9		; preds = %0
  ret i32 14

; <label>:10		; preds = %0
  ret i32 15

; <label>:11      		; preds = %0
  %12 = load i32, i32* %2, align 4
  ret i32 %12
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %input = alloca [200 x i8], align 16
  %initial_hex = alloca [400 x i8], align 16
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %k = alloca i32, align 4
  %len = alloca i32, align 4
  %r = alloca i32, align 4
  %x = alloca i32, align 4
  %m = alloca i32, align 4
  %temp = alloca i32, align 4
  %d = alloca i32, align 4
  %e = alloca i32, align 4
  %f = alloca i32, align 4
  %hex_arr = alloca [25 x [16 x i8]], align 16
  %input_hex = alloca [16 x i8], align 16
  %input_bin = alloca [64 x i8], align 16
  %key_hex = alloca [16 x i8], align 16
  %key_bin = alloca [64 x i8], align 16
  %key_PC1 = alloca [56 x i8], align 16
  %ch = alloca i8, align 1
  %decryption = alloca i8*, align 8
  %encryption = alloca i8*, align 8
  %encryption_final = alloca [400 x i8], align 16
  %decryption_final_hex = alloca [400 x i8], align 16
  %decryption_final_plain = alloca [200 x i8], align 16
  %encrypted = alloca [64 x i8], align 16
  %decrypted = alloca [64 x i8], align 16
  %encry_permut = alloca [64 x i8], align 16
  %decry_permut = alloca [64 x i8], align 16
  %length = alloca i32, align 4
  %p = alloca i32, align 4
  %q = alloca i32, align 4
  %C0 = alloca [28 x i8], align 16
  %D0 = alloca [28 x i8], align 16
  %C1 = alloca [28 x i8], align 16
  %D1 = alloca [28 x i8], align 16
  %CD1 = alloca [56 x i8], align 16
  %C2 = alloca [28 x i8], align 16
  %D2 = alloca [28 x i8], align 16
  %CD2 = alloca [56 x i8], align 16
  %C3 = alloca [28 x i8], align 16
  %D3 = alloca [28 x i8], align 16
  %CD3 = alloca [56 x i8], align 16
  %C4 = alloca [28 x i8], align 16
  %D4 = alloca [28 x i8], align 16
  %CD4 = alloca [56 x i8], align 16
  %C5 = alloca [28 x i8], align 16
  %D5 = alloca [28 x i8], align 16
  %CD5 = alloca [56 x i8], align 16
  %C6 = alloca [28 x i8], align 16
  %D6 = alloca [28 x i8], align 16
  %CD6 = alloca [56 x i8], align 16
  %C7 = alloca [28 x i8], align 16
  %D7 = alloca [28 x i8], align 16
  %CD7 = alloca [56 x i8], align 16
  %C8 = alloca [28 x i8], align 16
  %D8 = alloca [28 x i8], align 16
  %CD8 = alloca [56 x i8], align 16
  %C9 = alloca [28 x i8], align 16
  %D9 = alloca [28 x i8], align 16
  %CD9 = alloca [56 x i8], align 16
  %C10 = alloca [28 x i8], align 16
  %D10 = alloca [28 x i8], align 16
  %CD10 = alloca [56 x i8], align 16
  %C11 = alloca [28 x i8], align 16
  %D11 = alloca [28 x i8], align 16
  %CD11 = alloca [56 x i8], align 16
  %C12 = alloca [28 x i8], align 16
  %D12 = alloca [28 x i8], align 16
  %CD12 = alloca [56 x i8], align 16
  %C13 = alloca [28 x i8], align 16
  %D13 = alloca [28 x i8], align 16
  %CD13 = alloca [56 x i8], align 16
  %C14 = alloca [28 x i8], align 16
  %D14 = alloca [28 x i8], align 16
  %CD14 = alloca [56 x i8], align 16
  %C15 = alloca [28 x i8], align 16
  %D15 = alloca [28 x i8], align 16
  %CD15 = alloca [56 x i8], align 16
  %C16 = alloca [28 x i8], align 16
  %D16 = alloca [28 x i8], align 16
  %CD16 = alloca [56 x i8], align 16
  %L0 = alloca [32 x i8], align 16
  %R0 = alloca [32 x i8], align 16
  %ER0 = alloca [48 x i8], align 16
  %K1 = alloca [48 x i8], align 16
  %L1 = alloca [32 x i8], align 16
  %R1 = alloca [32 x i8], align 16
  %ER1 = alloca [48 x i8], align 16
  %F1 = alloca [48 x i8], align 16
  %K2 = alloca [48 x i8], align 16
  %L2 = alloca [32 x i8], align 16
  %R2 = alloca [32 x i8], align 16
  %ER2 = alloca [48 x i8], align 16
  %F2 = alloca [48 x i8], align 16
  %K3 = alloca [48 x i8], align 16
  %L3 = alloca [32 x i8], align 16
  %R3 = alloca [32 x i8], align 16
  %ER3 = alloca [48 x i8], align 16
  %F3 = alloca [48 x i8], align 16
  %K4 = alloca [48 x i8], align 16
  %L4 = alloca [32 x i8], align 16
  %R4 = alloca [32 x i8], align 16
  %ER4 = alloca [48 x i8], align 16
  %F4 = alloca [48 x i8], align 16
  %K5 = alloca [48 x i8], align 16
  %L5 = alloca [32 x i8], align 16
  %R5 = alloca [32 x i8], align 16
  %ER5 = alloca [48 x i8], align 16
  %F5 = alloca [48 x i8], align 16
  %K6 = alloca [48 x i8], align 16
  %L6 = alloca [32 x i8], align 16
  %R6 = alloca [32 x i8], align 16
  %ER6 = alloca [48 x i8], align 16
  %F6 = alloca [48 x i8], align 16
  %K7 = alloca [48 x i8], align 16
  %L7 = alloca [32 x i8], align 16
  %R7 = alloca [32 x i8], align 16
  %ER7 = alloca [48 x i8], align 16
  %F7 = alloca [48 x i8], align 16
  %K8 = alloca [48 x i8], align 16
  %L8 = alloca [32 x i8], align 16
  %R8 = alloca [32 x i8], align 16
  %ER8 = alloca [48 x i8], align 16
  %F8 = alloca [48 x i8], align 16
  %K9 = alloca [48 x i8], align 16
  %L9 = alloca [32 x i8], align 16
  %R9 = alloca [32 x i8], align 16
  %ER9 = alloca [48 x i8], align 16
  %F9 = alloca [48 x i8], align 16
  %K10 = alloca [48 x i8], align 16
  %L10 = alloca [32 x i8], align 16
  %R10 = alloca [32 x i8], align 16
  %ER10 = alloca [48 x i8], align 16
  %F10 = alloca [48 x i8], align 16
  %K11 = alloca [48 x i8], align 16
  %L11 = alloca [32 x i8], align 16
  %R11 = alloca [32 x i8], align 16
  %ER11 = alloca [48 x i8], align 16
  %F11 = alloca [48 x i8], align 16
  %K12 = alloca [48 x i8], align 16
  %L12 = alloca [32 x i8], align 16
  %R12 = alloca [32 x i8], align 16
  %ER12 = alloca [48 x i8], align 16
  %F12 = alloca [48 x i8], align 16
  %K13 = alloca [48 x i8], align 16
  %L13 = alloca [32 x i8], align 16
  %R13 = alloca [32 x i8], align 16
  %ER13 = alloca [48 x i8], align 16
  %F13 = alloca [48 x i8], align 16
  %K14 = alloca [48 x i8], align 16
  %L14 = alloca [32 x i8], align 16
  %R14 = alloca [32 x i8], align 16
  %ER14 = alloca [48 x i8], align 16
  %F14 = alloca [48 x i8], align 16
  %K15 = alloca [48 x i8], align 16
  %L15 = alloca [32 x i8], align 16
  %R15 = alloca [32 x i8], align 16
  %ER15 = alloca [48 x i8], align 16
  %F15 = alloca [48 x i8], align 16
  %K16 = alloca [48 x i8], align 16
  %L16 = alloca [32 x i8], align 16
  %R16 = alloca [32 x i8], align 16
  %ER16 = alloca [48 x i8], align 16
  %F16 = alloca [48 x i8], align 16
  store i32 0, i32* %1, align 4
  store i32 0, i32* %k, align 4
  %2 = bitcast [16 x i8]* %key_hex to i8*
  %3 = getelementptr inbounds [16 x i8], [16 x i8]* @main.key_hex, i32 0, i32 0
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 16, i32 16, i1 false)
  store i32 -1, i32* %p, align 4
  store i32 -1, i32* %q, align 4
  %4 = getelementptr inbounds [21 x i8], [21 x i8]* @.str20, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @printf(i8* %4)
  %6 = getelementptr inbounds [200 x i8], [200 x i8]* %input, i32 0, i32 0
  %7 = call i8* @gets(i8* %6)
  %8 = getelementptr inbounds [200 x i8], [200 x i8]* %input, i32 0, i32 0
  %9 = call i32 @strlen(i8* %8)
  store i32 %9, i32* %len, align 4
  store i32 0, i32* %i, align 4
  br label %10

; <label>:10   		; preds = %0, %56
  %11 = load i32, i32* %i, align 4
  %12 = load i32, i32* %len, align 4
  %13 = icmp slt i32 %11, %12
  br i1 %13, label %14, label %59

; <label>:14		; preds = %10
  br label %15

; <label>:15                                           		; preds = %14, %52
  %16 = load i32, i32* %i, align 4
  %17 = getelementptr inbounds [200 x i8], [200 x i8]* %input, i32 0, i32 %16
  %18 = load i8, i8* %17, align 1
  %19 = sext i8 %18 to i32
  %20 = icmp ne i32 %19, 0
  br i1 %20, label %21, label %55

; <label>:21                                                		; preds = %15
  %22 = load i32, i32* %i, align 4
  %23 = getelementptr inbounds [200 x i8], [200 x i8]* %input, i32 0, i32 %22
  %24 = load i8, i8* %23, align 1
  %25 = sext i8 %24 to i32
  %26 = srem i32 %25, 16
  store i32 %26, i32* %r, align 4
  %27 = load i32, i32* %i, align 4
  %28 = getelementptr inbounds [200 x i8], [200 x i8]* %input, i32 0, i32 %27
  %29 = load i8, i8* %28, align 1
  %30 = sext i8 %29 to i32
  %31 = sdiv i32 %30, 16
  %32 = load i32, i32* %i, align 4
  %33 = getelementptr inbounds [200 x i8], [200 x i8]* %input, i32 0, i32 %32
  %34 = trunc i32 %31 to i8
  store i8 %34, i8* %33, align 1
  %35 = load i32, i32* %r, align 4
  %36 = icmp sgt i32 %35, 9
  br i1 %36, label %37, label %46

; <label>:37                                                      		; preds = %21
  %38 = load i32, i32* %r, align 4
  %39 = sub i32 %38, 10
  store i32 %39, i32* %x, align 4
  %40 = load i32, i32* %x, align 4
  %41 = add i32 65, %40
  store i32 %41, i32* %r, align 4
  %42 = load i32, i32* %k, align 4
  %43 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %42
  %44 = load i32, i32* %r, align 4
  %45 = trunc i32 %44 to i8
  store i8 %45, i8* %43, align 1
  br label %52

; <label>:46                                                      		; preds = %21
  %47 = load i32, i32* %r, align 4
  %48 = add i32 %47, 48
  %49 = load i32, i32* %k, align 4
  %50 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %49
  %51 = trunc i32 %48 to i8
  store i8 %51, i8* %50, align 1
  br label %52

; <label>:52		; preds = %37, %46
  %53 = load i32, i32* %k, align 4
  %54 = add i32 %53, 1
  store i32 %54, i32* %k, align 4
  br label %15

; <label>:55		; preds = %15
  br label %56

; <label>:56     		; preds = %55
  %57 = load i32, i32* %i, align 4
  %58 = add i32 %57, 1
  store i32 %58, i32* %i, align 4
  br label %10

; <label>:59  		; preds = %10
  store i32 0, i32* %i, align 4
  br label %60

; <label>:60		; preds = %59, %80
  %61 = load i32, i32* %i, align 4
  %62 = load i32, i32* %k, align 4
  %63 = icmp slt i32 %61, %62
  br i1 %63, label %64, label %83

; <label>:64        		; preds = %60
  %65 = load i32, i32* %i, align 4
  %66 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %65
  %67 = load i8, i8* %66, align 1
  %68 = sext i8 %67 to i32
  store i32 %68, i32* %temp, align 4
  %69 = load i32, i32* %i, align 4
  %70 = add i32 %69, 1
  %71 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %70
  %72 = load i32, i32* %i, align 4
  %73 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %72
  %74 = load i8, i8* %71, align 1
  store i8 %74, i8* %73, align 1
  %75 = load i32, i32* %i, align 4
  %76 = add i32 %75, 1
  %77 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %76
  %78 = load i32, i32* %temp, align 4
  %79 = trunc i32 %78 to i8
  store i8 %79, i8* %77, align 1
  br label %80

; <label>:80     		; preds = %64
  %81 = load i32, i32* %i, align 4
  %82 = add i32 %81, 2
  store i32 %82, i32* %i, align 4
  br label %60

; <label>:83     		; preds = %60
  %84 = load i32, i32* %k, align 4
  %85 = sdiv i32 %84, 16
  store i32 %85, i32* %d, align 4
  %86 = load i32, i32* %k, align 4
  %87 = srem i32 %86, 16
  store i32 %87, i32* %e, align 4
  store i32 0, i32* %f, align 4
  store i32 0, i32* %i, align 4
  br label %88

; <label>:88		; preds = %83, %152
  %89 = load i32, i32* %i, align 4
  %90 = load i32, i32* %d, align 4
  %91 = icmp sle i32 %89, %90
  br i1 %91, label %92, label %155

; <label>:92     		; preds = %88
  %93 = load i32, i32* %i, align 4
  %94 = load i32, i32* %d, align 4
  %95 = icmp slt i32 %93, %94
  br i1 %95, label %96, label %113

; <label>:96  		; preds = %92
  store i32 0, i32* %j, align 4
  br label %97

; <label>:97		; preds = %96, %109
  %98 = load i32, i32* %j, align 4
  %99 = icmp sle i32 %98, 15
  br i1 %99, label %100, label %112

; <label>:100                                             		; preds = %97
  %101 = load i32, i32* %f, align 4
  %102 = add i32 %101, 1
  store i32 %102, i32* %f, align 4
  %103 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %101
  %104 = load i32, i32* %i, align 4
  %105 = getelementptr inbounds [25 x [16 x i8]], [25 x [16 x i8]]* %hex_arr, i32 0, i32 %104
  %106 = load i32, i32* %j, align 4
  %107 = getelementptr inbounds [16 x i8], [16 x i8]* %105, i32 0, i32 %106
  %108 = load i8, i8* %103, align 1
  store i8 %108, i8* %107, align 1
  br label %109

; <label>:109    		; preds = %100
  %110 = load i32, i32* %j, align 4
  %111 = add i32 %110, 1
  store i32 %111, i32* %j, align 4
  br label %97

; <label>:112		; preds = %97
  br label %151

; <label>:113      		; preds = %92
  %114 = load i32, i32* %k, align 4
  %115 = srem i32 %114, 16
  %116 = icmp eq i32 %115, 0
  br i1 %116, label %117, label %118

; <label>:117		; preds = %113
  br label %155

; <label>:118		; preds = %113
  store i32 0, i32* %j, align 4
  br label %119

; <label>:119		; preds = %118, %146
  %120 = load i32, i32* %j, align 4
  %121 = icmp sle i32 %120, 15
  br i1 %121, label %122, label %149

; <label>:122     		; preds = %119
  %123 = load i32, i32* %j, align 4
  %124 = load i32, i32* %e, align 4
  %125 = icmp slt i32 %123, %124
  br i1 %125, label %126, label %135

; <label>:126                                            		; preds = %122
  %127 = load i32, i32* %f, align 4
  %128 = add i32 %127, 1
  store i32 %128, i32* %f, align 4
  %129 = getelementptr inbounds [400 x i8], [400 x i8]* %initial_hex, i32 0, i32 %127
  %130 = load i32, i32* %i, align 4
  %131 = getelementptr inbounds [25 x [16 x i8]], [25 x [16 x i8]]* %hex_arr, i32 0, i32 %130
  %132 = load i32, i32* %j, align 4
  %133 = getelementptr inbounds [16 x i8], [16 x i8]* %131, i32 0, i32 %132
  %134 = load i8, i8* %129, align 1
  store i8 %134, i8* %133, align 1
  br label %145

; <label>:135                                            		; preds = %122
  %136 = load i32, i32* %i, align 4
  %137 = getelementptr inbounds [25 x [16 x i8]], [25 x [16 x i8]]* %hex_arr, i32 0, i32 %136
  %138 = load i32, i32* %j, align 4
  %139 = getelementptr inbounds [16 x i8], [16 x i8]* %137, i32 0, i32 %138
  store i8 50, i8* %139, align 1
  %140 = load i32, i32* %i, align 4
  %141 = getelementptr inbounds [25 x [16 x i8]], [25 x [16 x i8]]* %hex_arr, i32 0, i32 %140
  %142 = load i32, i32* %j, align 4
  %143 = add i32 %142, 1
  store i32 %143, i32* %j, align 4
  %144 = getelementptr inbounds [16 x i8], [16 x i8]* %141, i32 0, i32 %143
  store i8 48, i8* %144, align 1
  br label %145

; <label>:145		; preds = %126, %135
  br label %146

; <label>:146    		; preds = %145
  %147 = load i32, i32* %j, align 4
  %148 = add i32 %147, 1
  store i32 %148, i32* %j, align 4
  br label %119

; <label>:149		; preds = %119
  br label %150

; <label>:150		; preds = %149
  br label %151

; <label>:151		; preds = %112, %150
  br label %152

; <label>:152    		; preds = %151
  %153 = load i32, i32* %i, align 4
  %154 = add i32 %153, 1
  store i32 %154, i32* %i, align 4
  br label %88

; <label>:155		; preds = %88, %117
  %156 = load i32, i32* %k, align 4
  %157 = srem i32 %156, 16
  %158 = icmp ne i32 %157, 0
  br i1 %158, label %159, label %162

; <label>:159    		; preds = %155
  %160 = load i32, i32* %d, align 4
  %161 = add i32 %160, 1
  store i32 %161, i32* %d, align 4
  br label %162

; <label>:162                                       		; preds = %155, %159
  %163 = getelementptr inbounds [16 x i8], [16 x i8]* %key_hex, i32 0, i32 0
  %164 = getelementptr inbounds [64 x i8], [64 x i8]* %key_bin, i32 0, i32 0
  call void @hex_to_bin(i8* %163, i8* %164)
  %165 = getelementptr inbounds [44 x i8], [44 x i8]* @.str21, i32 0, i32 0
  %166 = call i32 (i8*, ...)* @printf(i8* %165)
  store i32 0, i32* %i, align 4
  br label %167

; <label>:167		; preds = %162, %176
  %168 = load i32, i32* %i, align 4
  %169 = icmp slt i32 %168, 16
  br i1 %169, label %170, label %179

; <label>:170                         		; preds = %167
  %171 = load i32, i32* %i, align 4
  %172 = getelementptr inbounds [16 x i8], [16 x i8]* %key_hex, i32 0, i32 %171
  %173 = getelementptr inbounds [3 x i8], [3 x i8]* @.str22, i32 0, i32 0
  %174 = load i8, i8* %172, align 1
  %175 = call i32 (i8*, ...)* @printf(i8* %173, i8 %174)
  br label %176

; <label>:176    		; preds = %170
  %177 = load i32, i32* %i, align 4
  %178 = add i32 %177, 1
  store i32 %178, i32* %i, align 4
  br label %167

; <label>:179		; preds = %167
  store i32 0, i32* %m, align 4
  br label %180

; <label>:180		; preds = %179, %654
  %181 = load i32, i32* %m, align 4
  %182 = load i32, i32* %d, align 4
  %183 = icmp slt i32 %181, %182
  br i1 %183, label %184, label %657

; <label>:184		; preds = %180
  store i32 0, i32* %i, align 4
  br label %185

; <label>:185		; preds = %184, %196
  %186 = load i32, i32* %i, align 4
  %187 = icmp slt i32 %186, 16
  br i1 %187, label %188, label %199

; <label>:188                                                  		; preds = %185
  %189 = load i32, i32* %m, align 4
  %190 = getelementptr inbounds [25 x [16 x i8]], [25 x [16 x i8]]* %hex_arr, i32 0, i32 %189
  %191 = load i32, i32* %i, align 4
  %192 = getelementptr inbounds [16 x i8], [16 x i8]* %190, i32 0, i32 %191
  %193 = load i32, i32* %i, align 4
  %194 = getelementptr inbounds [16 x i8], [16 x i8]* %input_hex, i32 0, i32 %193
  %195 = load i8, i8* %192, align 1
  store i8 %195, i8* %194, align 1
  br label %196

; <label>:196    		; preds = %188
  %197 = load i32, i32* %i, align 4
  %198 = add i32 %197, 1
  store i32 %198, i32* %i, align 4
  br label %185

; <label>:199                                                             		; preds = %185
  %200 = getelementptr inbounds [16 x i8], [16 x i8]* %input_hex, i32 0, i32 0
  %201 = getelementptr inbounds [64 x i8], [64 x i8]* %input_bin, i32 0, i32 0
  call void @hex_to_bin(i8* %200, i8* %201)
  %202 = getelementptr inbounds [64 x i8], [64 x i8]* %key_bin, i32 0, i32 0
  %203 = getelementptr inbounds [56 x i8], [56 x i8]* %key_PC1, i32 0, i32 0
  call void @permutation(i8* %202, i8* %203)
  %204 = getelementptr inbounds [56 x i8], [56 x i8]* %key_PC1, i32 0, i32 0
  %205 = getelementptr inbounds [28 x i8], [28 x i8]* %C0, i32 0, i32 0
  %206 = getelementptr inbounds [28 x i8], [28 x i8]* %D0, i32 0, i32 0
  call void @make_half(i8* %204, i8* %205, i8* %206)
  %207 = getelementptr inbounds [28 x i8], [28 x i8]* %C0, i32 0, i32 0
  %208 = getelementptr inbounds [28 x i8], [28 x i8]* %C1, i32 0, i32 0
  call void @single_shift(i8* %207, i8* %208)
  %209 = getelementptr inbounds [28 x i8], [28 x i8]* %D0, i32 0, i32 0
  %210 = getelementptr inbounds [28 x i8], [28 x i8]* %D1, i32 0, i32 0
  call void @single_shift(i8* %209, i8* %210)
  %211 = getelementptr inbounds [28 x i8], [28 x i8]* %C1, i32 0, i32 0
  %212 = getelementptr inbounds [28 x i8], [28 x i8]* %C2, i32 0, i32 0
  call void @single_shift(i8* %211, i8* %212)
  %213 = getelementptr inbounds [28 x i8], [28 x i8]* %D1, i32 0, i32 0
  %214 = getelementptr inbounds [28 x i8], [28 x i8]* %D2, i32 0, i32 0
  call void @single_shift(i8* %213, i8* %214)
  %215 = getelementptr inbounds [28 x i8], [28 x i8]* %C2, i32 0, i32 0
  %216 = getelementptr inbounds [28 x i8], [28 x i8]* %C3, i32 0, i32 0
  call void @double_shift(i8* %215, i8* %216)
  %217 = getelementptr inbounds [28 x i8], [28 x i8]* %D2, i32 0, i32 0
  %218 = getelementptr inbounds [28 x i8], [28 x i8]* %D3, i32 0, i32 0
  call void @double_shift(i8* %217, i8* %218)
  %219 = getelementptr inbounds [28 x i8], [28 x i8]* %C3, i32 0, i32 0
  %220 = getelementptr inbounds [28 x i8], [28 x i8]* %C4, i32 0, i32 0
  call void @double_shift(i8* %219, i8* %220)
  %221 = getelementptr inbounds [28 x i8], [28 x i8]* %D3, i32 0, i32 0
  %222 = getelementptr inbounds [28 x i8], [28 x i8]* %D4, i32 0, i32 0
  call void @double_shift(i8* %221, i8* %222)
  %223 = getelementptr inbounds [28 x i8], [28 x i8]* %C4, i32 0, i32 0
  %224 = getelementptr inbounds [28 x i8], [28 x i8]* %C5, i32 0, i32 0
  call void @double_shift(i8* %223, i8* %224)
  %225 = getelementptr inbounds [28 x i8], [28 x i8]* %D4, i32 0, i32 0
  %226 = getelementptr inbounds [28 x i8], [28 x i8]* %D5, i32 0, i32 0
  call void @double_shift(i8* %225, i8* %226)
  %227 = getelementptr inbounds [28 x i8], [28 x i8]* %C5, i32 0, i32 0
  %228 = getelementptr inbounds [28 x i8], [28 x i8]* %C6, i32 0, i32 0
  call void @double_shift(i8* %227, i8* %228)
  %229 = getelementptr inbounds [28 x i8], [28 x i8]* %D5, i32 0, i32 0
  %230 = getelementptr inbounds [28 x i8], [28 x i8]* %D6, i32 0, i32 0
  call void @double_shift(i8* %229, i8* %230)
  %231 = getelementptr inbounds [28 x i8], [28 x i8]* %C6, i32 0, i32 0
  %232 = getelementptr inbounds [28 x i8], [28 x i8]* %C7, i32 0, i32 0
  call void @double_shift(i8* %231, i8* %232)
  %233 = getelementptr inbounds [28 x i8], [28 x i8]* %D6, i32 0, i32 0
  %234 = getelementptr inbounds [28 x i8], [28 x i8]* %D7, i32 0, i32 0
  call void @double_shift(i8* %233, i8* %234)
  %235 = getelementptr inbounds [28 x i8], [28 x i8]* %C7, i32 0, i32 0
  %236 = getelementptr inbounds [28 x i8], [28 x i8]* %C8, i32 0, i32 0
  call void @double_shift(i8* %235, i8* %236)
  %237 = getelementptr inbounds [28 x i8], [28 x i8]* %D7, i32 0, i32 0
  %238 = getelementptr inbounds [28 x i8], [28 x i8]* %D8, i32 0, i32 0
  call void @double_shift(i8* %237, i8* %238)
  %239 = getelementptr inbounds [28 x i8], [28 x i8]* %C8, i32 0, i32 0
  %240 = getelementptr inbounds [28 x i8], [28 x i8]* %C9, i32 0, i32 0
  call void @single_shift(i8* %239, i8* %240)
  %241 = getelementptr inbounds [28 x i8], [28 x i8]* %D8, i32 0, i32 0
  %242 = getelementptr inbounds [28 x i8], [28 x i8]* %D9, i32 0, i32 0
  call void @single_shift(i8* %241, i8* %242)
  %243 = getelementptr inbounds [28 x i8], [28 x i8]* %C9, i32 0, i32 0
  %244 = getelementptr inbounds [28 x i8], [28 x i8]* %C10, i32 0, i32 0
  call void @double_shift(i8* %243, i8* %244)
  %245 = getelementptr inbounds [28 x i8], [28 x i8]* %D9, i32 0, i32 0
  %246 = getelementptr inbounds [28 x i8], [28 x i8]* %D10, i32 0, i32 0
  call void @double_shift(i8* %245, i8* %246)
  %247 = getelementptr inbounds [28 x i8], [28 x i8]* %C10, i32 0, i32 0
  %248 = getelementptr inbounds [28 x i8], [28 x i8]* %C11, i32 0, i32 0
  call void @double_shift(i8* %247, i8* %248)
  %249 = getelementptr inbounds [28 x i8], [28 x i8]* %D10, i32 0, i32 0
  %250 = getelementptr inbounds [28 x i8], [28 x i8]* %D11, i32 0, i32 0
  call void @double_shift(i8* %249, i8* %250)
  %251 = getelementptr inbounds [28 x i8], [28 x i8]* %C11, i32 0, i32 0
  %252 = getelementptr inbounds [28 x i8], [28 x i8]* %C12, i32 0, i32 0
  call void @double_shift(i8* %251, i8* %252)
  %253 = getelementptr inbounds [28 x i8], [28 x i8]* %D11, i32 0, i32 0
  %254 = getelementptr inbounds [28 x i8], [28 x i8]* %D12, i32 0, i32 0
  call void @double_shift(i8* %253, i8* %254)
  %255 = getelementptr inbounds [28 x i8], [28 x i8]* %C12, i32 0, i32 0
  %256 = getelementptr inbounds [28 x i8], [28 x i8]* %C13, i32 0, i32 0
  call void @double_shift(i8* %255, i8* %256)
  %257 = getelementptr inbounds [28 x i8], [28 x i8]* %D12, i32 0, i32 0
  %258 = getelementptr inbounds [28 x i8], [28 x i8]* %D13, i32 0, i32 0
  call void @double_shift(i8* %257, i8* %258)
  %259 = getelementptr inbounds [28 x i8], [28 x i8]* %C13, i32 0, i32 0
  %260 = getelementptr inbounds [28 x i8], [28 x i8]* %C14, i32 0, i32 0
  call void @double_shift(i8* %259, i8* %260)
  %261 = getelementptr inbounds [28 x i8], [28 x i8]* %D13, i32 0, i32 0
  %262 = getelementptr inbounds [28 x i8], [28 x i8]* %D14, i32 0, i32 0
  call void @double_shift(i8* %261, i8* %262)
  %263 = getelementptr inbounds [28 x i8], [28 x i8]* %C14, i32 0, i32 0
  %264 = getelementptr inbounds [28 x i8], [28 x i8]* %C15, i32 0, i32 0
  call void @double_shift(i8* %263, i8* %264)
  %265 = getelementptr inbounds [28 x i8], [28 x i8]* %D14, i32 0, i32 0
  %266 = getelementptr inbounds [28 x i8], [28 x i8]* %D15, i32 0, i32 0
  call void @double_shift(i8* %265, i8* %266)
  %267 = getelementptr inbounds [28 x i8], [28 x i8]* %C15, i32 0, i32 0
  %268 = getelementptr inbounds [28 x i8], [28 x i8]* %C16, i32 0, i32 0
  call void @single_shift(i8* %267, i8* %268)
  %269 = getelementptr inbounds [28 x i8], [28 x i8]* %D15, i32 0, i32 0
  %270 = getelementptr inbounds [28 x i8], [28 x i8]* %D16, i32 0, i32 0
  call void @single_shift(i8* %269, i8* %270)
  %271 = getelementptr inbounds [28 x i8], [28 x i8]* %C1, i32 0, i32 0
  %272 = getelementptr inbounds [28 x i8], [28 x i8]* %D1, i32 0, i32 0
  %273 = getelementptr inbounds [56 x i8], [56 x i8]* %CD1, i32 0, i32 0
  call void @make_key(i8* %271, i8* %272, i8* %273)
  %274 = getelementptr inbounds [56 x i8], [56 x i8]* %CD1, i32 0, i32 0
  %275 = getelementptr inbounds [48 x i8], [48 x i8]* %K1, i32 0, i32 0
  call void @permutation_48(i8* %274, i8* %275)
  %276 = getelementptr inbounds [28 x i8], [28 x i8]* %C2, i32 0, i32 0
  %277 = getelementptr inbounds [28 x i8], [28 x i8]* %D2, i32 0, i32 0
  %278 = getelementptr inbounds [56 x i8], [56 x i8]* %CD2, i32 0, i32 0
  call void @make_key(i8* %276, i8* %277, i8* %278)
  %279 = getelementptr inbounds [56 x i8], [56 x i8]* %CD2, i32 0, i32 0
  %280 = getelementptr inbounds [48 x i8], [48 x i8]* %K2, i32 0, i32 0
  call void @permutation_48(i8* %279, i8* %280)
  %281 = getelementptr inbounds [28 x i8], [28 x i8]* %C3, i32 0, i32 0
  %282 = getelementptr inbounds [28 x i8], [28 x i8]* %D3, i32 0, i32 0
  %283 = getelementptr inbounds [56 x i8], [56 x i8]* %CD3, i32 0, i32 0
  call void @make_key(i8* %281, i8* %282, i8* %283)
  %284 = getelementptr inbounds [56 x i8], [56 x i8]* %CD3, i32 0, i32 0
  %285 = getelementptr inbounds [48 x i8], [48 x i8]* %K3, i32 0, i32 0
  call void @permutation_48(i8* %284, i8* %285)
  %286 = getelementptr inbounds [28 x i8], [28 x i8]* %C4, i32 0, i32 0
  %287 = getelementptr inbounds [28 x i8], [28 x i8]* %D4, i32 0, i32 0
  %288 = getelementptr inbounds [56 x i8], [56 x i8]* %CD4, i32 0, i32 0
  call void @make_key(i8* %286, i8* %287, i8* %288)
  %289 = getelementptr inbounds [56 x i8], [56 x i8]* %CD4, i32 0, i32 0
  %290 = getelementptr inbounds [48 x i8], [48 x i8]* %K4, i32 0, i32 0
  call void @permutation_48(i8* %289, i8* %290)
  %291 = getelementptr inbounds [28 x i8], [28 x i8]* %C5, i32 0, i32 0
  %292 = getelementptr inbounds [28 x i8], [28 x i8]* %D5, i32 0, i32 0
  %293 = getelementptr inbounds [56 x i8], [56 x i8]* %CD5, i32 0, i32 0
  call void @make_key(i8* %291, i8* %292, i8* %293)
  %294 = getelementptr inbounds [56 x i8], [56 x i8]* %CD5, i32 0, i32 0
  %295 = getelementptr inbounds [48 x i8], [48 x i8]* %K5, i32 0, i32 0
  call void @permutation_48(i8* %294, i8* %295)
  %296 = getelementptr inbounds [28 x i8], [28 x i8]* %C6, i32 0, i32 0
  %297 = getelementptr inbounds [28 x i8], [28 x i8]* %D6, i32 0, i32 0
  %298 = getelementptr inbounds [56 x i8], [56 x i8]* %CD6, i32 0, i32 0
  call void @make_key(i8* %296, i8* %297, i8* %298)
  %299 = getelementptr inbounds [56 x i8], [56 x i8]* %CD6, i32 0, i32 0
  %300 = getelementptr inbounds [48 x i8], [48 x i8]* %K6, i32 0, i32 0
  call void @permutation_48(i8* %299, i8* %300)
  %301 = getelementptr inbounds [28 x i8], [28 x i8]* %C7, i32 0, i32 0
  %302 = getelementptr inbounds [28 x i8], [28 x i8]* %D7, i32 0, i32 0
  %303 = getelementptr inbounds [56 x i8], [56 x i8]* %CD7, i32 0, i32 0
  call void @make_key(i8* %301, i8* %302, i8* %303)
  %304 = getelementptr inbounds [56 x i8], [56 x i8]* %CD7, i32 0, i32 0
  %305 = getelementptr inbounds [48 x i8], [48 x i8]* %K7, i32 0, i32 0
  call void @permutation_48(i8* %304, i8* %305)
  %306 = getelementptr inbounds [28 x i8], [28 x i8]* %C8, i32 0, i32 0
  %307 = getelementptr inbounds [28 x i8], [28 x i8]* %D8, i32 0, i32 0
  %308 = getelementptr inbounds [56 x i8], [56 x i8]* %CD8, i32 0, i32 0
  call void @make_key(i8* %306, i8* %307, i8* %308)
  %309 = getelementptr inbounds [56 x i8], [56 x i8]* %CD8, i32 0, i32 0
  %310 = getelementptr inbounds [48 x i8], [48 x i8]* %K8, i32 0, i32 0
  call void @permutation_48(i8* %309, i8* %310)
  %311 = getelementptr inbounds [28 x i8], [28 x i8]* %C9, i32 0, i32 0
  %312 = getelementptr inbounds [28 x i8], [28 x i8]* %D9, i32 0, i32 0
  %313 = getelementptr inbounds [56 x i8], [56 x i8]* %CD9, i32 0, i32 0
  call void @make_key(i8* %311, i8* %312, i8* %313)
  %314 = getelementptr inbounds [56 x i8], [56 x i8]* %CD9, i32 0, i32 0
  %315 = getelementptr inbounds [48 x i8], [48 x i8]* %K9, i32 0, i32 0
  call void @permutation_48(i8* %314, i8* %315)
  %316 = getelementptr inbounds [28 x i8], [28 x i8]* %C10, i32 0, i32 0
  %317 = getelementptr inbounds [28 x i8], [28 x i8]* %D10, i32 0, i32 0
  %318 = getelementptr inbounds [56 x i8], [56 x i8]* %CD10, i32 0, i32 0
  call void @make_key(i8* %316, i8* %317, i8* %318)
  %319 = getelementptr inbounds [56 x i8], [56 x i8]* %CD10, i32 0, i32 0
  %320 = getelementptr inbounds [48 x i8], [48 x i8]* %K10, i32 0, i32 0
  call void @permutation_48(i8* %319, i8* %320)
  %321 = getelementptr inbounds [28 x i8], [28 x i8]* %C11, i32 0, i32 0
  %322 = getelementptr inbounds [28 x i8], [28 x i8]* %D11, i32 0, i32 0
  %323 = getelementptr inbounds [56 x i8], [56 x i8]* %CD11, i32 0, i32 0
  call void @make_key(i8* %321, i8* %322, i8* %323)
  %324 = getelementptr inbounds [56 x i8], [56 x i8]* %CD11, i32 0, i32 0
  %325 = getelementptr inbounds [48 x i8], [48 x i8]* %K11, i32 0, i32 0
  call void @permutation_48(i8* %324, i8* %325)
  %326 = getelementptr inbounds [28 x i8], [28 x i8]* %C12, i32 0, i32 0
  %327 = getelementptr inbounds [28 x i8], [28 x i8]* %D12, i32 0, i32 0
  %328 = getelementptr inbounds [56 x i8], [56 x i8]* %CD12, i32 0, i32 0
  call void @make_key(i8* %326, i8* %327, i8* %328)
  %329 = getelementptr inbounds [56 x i8], [56 x i8]* %CD12, i32 0, i32 0
  %330 = getelementptr inbounds [48 x i8], [48 x i8]* %K12, i32 0, i32 0
  call void @permutation_48(i8* %329, i8* %330)
  %331 = getelementptr inbounds [28 x i8], [28 x i8]* %C13, i32 0, i32 0
  %332 = getelementptr inbounds [28 x i8], [28 x i8]* %D13, i32 0, i32 0
  %333 = getelementptr inbounds [56 x i8], [56 x i8]* %CD13, i32 0, i32 0
  call void @make_key(i8* %331, i8* %332, i8* %333)
  %334 = getelementptr inbounds [56 x i8], [56 x i8]* %CD13, i32 0, i32 0
  %335 = getelementptr inbounds [48 x i8], [48 x i8]* %K13, i32 0, i32 0
  call void @permutation_48(i8* %334, i8* %335)
  %336 = getelementptr inbounds [28 x i8], [28 x i8]* %C14, i32 0, i32 0
  %337 = getelementptr inbounds [28 x i8], [28 x i8]* %D14, i32 0, i32 0
  %338 = getelementptr inbounds [56 x i8], [56 x i8]* %CD14, i32 0, i32 0
  call void @make_key(i8* %336, i8* %337, i8* %338)
  %339 = getelementptr inbounds [56 x i8], [56 x i8]* %CD14, i32 0, i32 0
  %340 = getelementptr inbounds [48 x i8], [48 x i8]* %K14, i32 0, i32 0
  call void @permutation_48(i8* %339, i8* %340)
  %341 = getelementptr inbounds [28 x i8], [28 x i8]* %C15, i32 0, i32 0
  %342 = getelementptr inbounds [28 x i8], [28 x i8]* %D15, i32 0, i32 0
  %343 = getelementptr inbounds [56 x i8], [56 x i8]* %CD15, i32 0, i32 0
  call void @make_key(i8* %341, i8* %342, i8* %343)
  %344 = getelementptr inbounds [56 x i8], [56 x i8]* %CD15, i32 0, i32 0
  %345 = getelementptr inbounds [48 x i8], [48 x i8]* %K15, i32 0, i32 0
  call void @permutation_48(i8* %344, i8* %345)
  %346 = getelementptr inbounds [28 x i8], [28 x i8]* %C16, i32 0, i32 0
  %347 = getelementptr inbounds [28 x i8], [28 x i8]* %D16, i32 0, i32 0
  %348 = getelementptr inbounds [56 x i8], [56 x i8]* %CD16, i32 0, i32 0
  call void @make_key(i8* %346, i8* %347, i8* %348)
  %349 = getelementptr inbounds [56 x i8], [56 x i8]* %CD16, i32 0, i32 0
  %350 = getelementptr inbounds [48 x i8], [48 x i8]* %K16, i32 0, i32 0
  call void @permutation_48(i8* %349, i8* %350)
  %351 = getelementptr inbounds [64 x i8], [64 x i8]* %input_bin, i32 0, i32 0
  %352 = getelementptr inbounds [32 x i8], [32 x i8]* %L0, i32 0, i32 0
  %353 = getelementptr inbounds [32 x i8], [32 x i8]* %R0, i32 0, i32 0
  call void @permutation_64(i8* %351, i8* %352, i8* %353)
  %354 = getelementptr inbounds [32 x i8], [32 x i8]* %L1, i32 0, i32 0
  %355 = getelementptr inbounds [32 x i8], [32 x i8]* %R1, i32 0, i32 0
  %356 = getelementptr inbounds [32 x i8], [32 x i8]* %L0, i32 0, i32 0
  %357 = getelementptr inbounds [32 x i8], [32 x i8]* %R0, i32 0, i32 0
  %358 = getelementptr inbounds [48 x i8], [48 x i8]* %ER0, i32 0, i32 0
  %359 = getelementptr inbounds [48 x i8], [48 x i8]* %K1, i32 0, i32 0
  %360 = getelementptr inbounds [48 x i8], [48 x i8]* %F1, i32 0, i32 0
  call void @des_round(i8* %354, i8* %355, i8* %356, i8* %357, i8* %358, i8* %359, i8* %360)
  %361 = getelementptr inbounds [32 x i8], [32 x i8]* %L2, i32 0, i32 0
  %362 = getelementptr inbounds [32 x i8], [32 x i8]* %R2, i32 0, i32 0
  %363 = getelementptr inbounds [32 x i8], [32 x i8]* %L1, i32 0, i32 0
  %364 = getelementptr inbounds [32 x i8], [32 x i8]* %R1, i32 0, i32 0
  %365 = getelementptr inbounds [48 x i8], [48 x i8]* %ER1, i32 0, i32 0
  %366 = getelementptr inbounds [48 x i8], [48 x i8]* %K2, i32 0, i32 0
  %367 = getelementptr inbounds [48 x i8], [48 x i8]* %F2, i32 0, i32 0
  call void @des_round(i8* %361, i8* %362, i8* %363, i8* %364, i8* %365, i8* %366, i8* %367)
  %368 = getelementptr inbounds [32 x i8], [32 x i8]* %L3, i32 0, i32 0
  %369 = getelementptr inbounds [32 x i8], [32 x i8]* %R3, i32 0, i32 0
  %370 = getelementptr inbounds [32 x i8], [32 x i8]* %L2, i32 0, i32 0
  %371 = getelementptr inbounds [32 x i8], [32 x i8]* %R2, i32 0, i32 0
  %372 = getelementptr inbounds [48 x i8], [48 x i8]* %ER2, i32 0, i32 0
  %373 = getelementptr inbounds [48 x i8], [48 x i8]* %K3, i32 0, i32 0
  %374 = getelementptr inbounds [48 x i8], [48 x i8]* %F3, i32 0, i32 0
  call void @des_round(i8* %368, i8* %369, i8* %370, i8* %371, i8* %372, i8* %373, i8* %374)
  %375 = getelementptr inbounds [32 x i8], [32 x i8]* %L4, i32 0, i32 0
  %376 = getelementptr inbounds [32 x i8], [32 x i8]* %R4, i32 0, i32 0
  %377 = getelementptr inbounds [32 x i8], [32 x i8]* %L3, i32 0, i32 0
  %378 = getelementptr inbounds [32 x i8], [32 x i8]* %R3, i32 0, i32 0
  %379 = getelementptr inbounds [48 x i8], [48 x i8]* %ER3, i32 0, i32 0
  %380 = getelementptr inbounds [48 x i8], [48 x i8]* %K4, i32 0, i32 0
  %381 = getelementptr inbounds [48 x i8], [48 x i8]* %F4, i32 0, i32 0
  call void @des_round(i8* %375, i8* %376, i8* %377, i8* %378, i8* %379, i8* %380, i8* %381)
  %382 = getelementptr inbounds [32 x i8], [32 x i8]* %L5, i32 0, i32 0
  %383 = getelementptr inbounds [32 x i8], [32 x i8]* %R5, i32 0, i32 0
  %384 = getelementptr inbounds [32 x i8], [32 x i8]* %L4, i32 0, i32 0
  %385 = getelementptr inbounds [32 x i8], [32 x i8]* %R4, i32 0, i32 0
  %386 = getelementptr inbounds [48 x i8], [48 x i8]* %ER4, i32 0, i32 0
  %387 = getelementptr inbounds [48 x i8], [48 x i8]* %K5, i32 0, i32 0
  %388 = getelementptr inbounds [48 x i8], [48 x i8]* %F5, i32 0, i32 0
  call void @des_round(i8* %382, i8* %383, i8* %384, i8* %385, i8* %386, i8* %387, i8* %388)
  %389 = getelementptr inbounds [32 x i8], [32 x i8]* %L6, i32 0, i32 0
  %390 = getelementptr inbounds [32 x i8], [32 x i8]* %R6, i32 0, i32 0
  %391 = getelementptr inbounds [32 x i8], [32 x i8]* %L5, i32 0, i32 0
  %392 = getelementptr inbounds [32 x i8], [32 x i8]* %R5, i32 0, i32 0
  %393 = getelementptr inbounds [48 x i8], [48 x i8]* %ER5, i32 0, i32 0
  %394 = getelementptr inbounds [48 x i8], [48 x i8]* %K6, i32 0, i32 0
  %395 = getelementptr inbounds [48 x i8], [48 x i8]* %F6, i32 0, i32 0
  call void @des_round(i8* %389, i8* %390, i8* %391, i8* %392, i8* %393, i8* %394, i8* %395)
  %396 = getelementptr inbounds [32 x i8], [32 x i8]* %L7, i32 0, i32 0
  %397 = getelementptr inbounds [32 x i8], [32 x i8]* %R7, i32 0, i32 0
  %398 = getelementptr inbounds [32 x i8], [32 x i8]* %L6, i32 0, i32 0
  %399 = getelementptr inbounds [32 x i8], [32 x i8]* %R6, i32 0, i32 0
  %400 = getelementptr inbounds [48 x i8], [48 x i8]* %ER6, i32 0, i32 0
  %401 = getelementptr inbounds [48 x i8], [48 x i8]* %K7, i32 0, i32 0
  %402 = getelementptr inbounds [48 x i8], [48 x i8]* %F7, i32 0, i32 0
  call void @des_round(i8* %396, i8* %397, i8* %398, i8* %399, i8* %400, i8* %401, i8* %402)
  %403 = getelementptr inbounds [32 x i8], [32 x i8]* %L8, i32 0, i32 0
  %404 = getelementptr inbounds [32 x i8], [32 x i8]* %R8, i32 0, i32 0
  %405 = getelementptr inbounds [32 x i8], [32 x i8]* %L7, i32 0, i32 0
  %406 = getelementptr inbounds [32 x i8], [32 x i8]* %R7, i32 0, i32 0
  %407 = getelementptr inbounds [48 x i8], [48 x i8]* %ER7, i32 0, i32 0
  %408 = getelementptr inbounds [48 x i8], [48 x i8]* %K8, i32 0, i32 0
  %409 = getelementptr inbounds [48 x i8], [48 x i8]* %F8, i32 0, i32 0
  call void @des_round(i8* %403, i8* %404, i8* %405, i8* %406, i8* %407, i8* %408, i8* %409)
  %410 = getelementptr inbounds [32 x i8], [32 x i8]* %L9, i32 0, i32 0
  %411 = getelementptr inbounds [32 x i8], [32 x i8]* %R9, i32 0, i32 0
  %412 = getelementptr inbounds [32 x i8], [32 x i8]* %L8, i32 0, i32 0
  %413 = getelementptr inbounds [32 x i8], [32 x i8]* %R8, i32 0, i32 0
  %414 = getelementptr inbounds [48 x i8], [48 x i8]* %ER8, i32 0, i32 0
  %415 = getelementptr inbounds [48 x i8], [48 x i8]* %K9, i32 0, i32 0
  %416 = getelementptr inbounds [48 x i8], [48 x i8]* %F9, i32 0, i32 0
  call void @des_round(i8* %410, i8* %411, i8* %412, i8* %413, i8* %414, i8* %415, i8* %416)
  %417 = getelementptr inbounds [32 x i8], [32 x i8]* %L10, i32 0, i32 0
  %418 = getelementptr inbounds [32 x i8], [32 x i8]* %R10, i32 0, i32 0
  %419 = getelementptr inbounds [32 x i8], [32 x i8]* %L9, i32 0, i32 0
  %420 = getelementptr inbounds [32 x i8], [32 x i8]* %R9, i32 0, i32 0
  %421 = getelementptr inbounds [48 x i8], [48 x i8]* %ER9, i32 0, i32 0
  %422 = getelementptr inbounds [48 x i8], [48 x i8]* %K10, i32 0, i32 0
  %423 = getelementptr inbounds [48 x i8], [48 x i8]* %F10, i32 0, i32 0
  call void @des_round(i8* %417, i8* %418, i8* %419, i8* %420, i8* %421, i8* %422, i8* %423)
  %424 = getelementptr inbounds [32 x i8], [32 x i8]* %L11, i32 0, i32 0
  %425 = getelementptr inbounds [32 x i8], [32 x i8]* %R11, i32 0, i32 0
  %426 = getelementptr inbounds [32 x i8], [32 x i8]* %L10, i32 0, i32 0
  %427 = getelementptr inbounds [32 x i8], [32 x i8]* %R10, i32 0, i32 0
  %428 = getelementptr inbounds [48 x i8], [48 x i8]* %ER10, i32 0, i32 0
  %429 = getelementptr inbounds [48 x i8], [48 x i8]* %K11, i32 0, i32 0
  %430 = getelementptr inbounds [48 x i8], [48 x i8]* %F11, i32 0, i32 0
  call void @des_round(i8* %424, i8* %425, i8* %426, i8* %427, i8* %428, i8* %429, i8* %430)
  %431 = getelementptr inbounds [32 x i8], [32 x i8]* %L12, i32 0, i32 0
  %432 = getelementptr inbounds [32 x i8], [32 x i8]* %R12, i32 0, i32 0
  %433 = getelementptr inbounds [32 x i8], [32 x i8]* %L11, i32 0, i32 0
  %434 = getelementptr inbounds [32 x i8], [32 x i8]* %R11, i32 0, i32 0
  %435 = getelementptr inbounds [48 x i8], [48 x i8]* %ER11, i32 0, i32 0
  %436 = getelementptr inbounds [48 x i8], [48 x i8]* %K12, i32 0, i32 0
  %437 = getelementptr inbounds [48 x i8], [48 x i8]* %F12, i32 0, i32 0
  call void @des_round(i8* %431, i8* %432, i8* %433, i8* %434, i8* %435, i8* %436, i8* %437)
  %438 = getelementptr inbounds [32 x i8], [32 x i8]* %L13, i32 0, i32 0
  %439 = getelementptr inbounds [32 x i8], [32 x i8]* %R13, i32 0, i32 0
  %440 = getelementptr inbounds [32 x i8], [32 x i8]* %L12, i32 0, i32 0
  %441 = getelementptr inbounds [32 x i8], [32 x i8]* %R12, i32 0, i32 0
  %442 = getelementptr inbounds [48 x i8], [48 x i8]* %ER12, i32 0, i32 0
  %443 = getelementptr inbounds [48 x i8], [48 x i8]* %K13, i32 0, i32 0
  %444 = getelementptr inbounds [48 x i8], [48 x i8]* %F13, i32 0, i32 0
  call void @des_round(i8* %438, i8* %439, i8* %440, i8* %441, i8* %442, i8* %443, i8* %444)
  %445 = getelementptr inbounds [32 x i8], [32 x i8]* %L14, i32 0, i32 0
  %446 = getelementptr inbounds [32 x i8], [32 x i8]* %R14, i32 0, i32 0
  %447 = getelementptr inbounds [32 x i8], [32 x i8]* %L13, i32 0, i32 0
  %448 = getelementptr inbounds [32 x i8], [32 x i8]* %R13, i32 0, i32 0
  %449 = getelementptr inbounds [48 x i8], [48 x i8]* %ER13, i32 0, i32 0
  %450 = getelementptr inbounds [48 x i8], [48 x i8]* %K14, i32 0, i32 0
  %451 = getelementptr inbounds [48 x i8], [48 x i8]* %F14, i32 0, i32 0
  call void @des_round(i8* %445, i8* %446, i8* %447, i8* %448, i8* %449, i8* %450, i8* %451)
  %452 = getelementptr inbounds [32 x i8], [32 x i8]* %L15, i32 0, i32 0
  %453 = getelementptr inbounds [32 x i8], [32 x i8]* %R15, i32 0, i32 0
  %454 = getelementptr inbounds [32 x i8], [32 x i8]* %L14, i32 0, i32 0
  %455 = getelementptr inbounds [32 x i8], [32 x i8]* %R14, i32 0, i32 0
  %456 = getelementptr inbounds [48 x i8], [48 x i8]* %ER14, i32 0, i32 0
  %457 = getelementptr inbounds [48 x i8], [48 x i8]* %K15, i32 0, i32 0
  %458 = getelementptr inbounds [48 x i8], [48 x i8]* %F15, i32 0, i32 0
  call void @des_round(i8* %452, i8* %453, i8* %454, i8* %455, i8* %456, i8* %457, i8* %458)
  %459 = getelementptr inbounds [32 x i8], [32 x i8]* %L16, i32 0, i32 0
  %460 = getelementptr inbounds [32 x i8], [32 x i8]* %R16, i32 0, i32 0
  %461 = getelementptr inbounds [32 x i8], [32 x i8]* %L15, i32 0, i32 0
  %462 = getelementptr inbounds [32 x i8], [32 x i8]* %R15, i32 0, i32 0
  %463 = getelementptr inbounds [48 x i8], [48 x i8]* %ER15, i32 0, i32 0
  %464 = getelementptr inbounds [48 x i8], [48 x i8]* %K16, i32 0, i32 0
  %465 = getelementptr inbounds [48 x i8], [48 x i8]* %F16, i32 0, i32 0
  call void @des_round(i8* %459, i8* %460, i8* %461, i8* %462, i8* %463, i8* %464, i8* %465)
  store i32 0, i32* %i, align 4
  br label %466

; <label>:466		; preds = %199, %481
  %467 = load i32, i32* %i, align 4
  %468 = icmp slt i32 %467, 32
  br i1 %468, label %469, label %484

; <label>:469                                                  		; preds = %466
  %470 = load i32, i32* %i, align 4
  %471 = getelementptr inbounds [32 x i8], [32 x i8]* %R16, i32 0, i32 %470
  %472 = load i32, i32* %i, align 4
  %473 = getelementptr inbounds [64 x i8], [64 x i8]* %encrypted, i32 0, i32 %472
  %474 = load i8, i8* %471, align 1
  store i8 %474, i8* %473, align 1
  %475 = load i32, i32* %i, align 4
  %476 = getelementptr inbounds [32 x i8], [32 x i8]* %L16, i32 0, i32 %475
  %477 = load i32, i32* %i, align 4
  %478 = add i32 %477, 32
  %479 = getelementptr inbounds [64 x i8], [64 x i8]* %encrypted, i32 0, i32 %478
  %480 = load i8, i8* %476, align 1
  store i8 %480, i8* %479, align 1
  br label %481

; <label>:481    		; preds = %469
  %482 = load i32, i32* %i, align 4
  %483 = add i32 %482, 1
  store i32 %483, i32* %i, align 4
  br label %466

; <label>:484                                                  		; preds = %466
  %485 = getelementptr inbounds [64 x i8], [64 x i8]* %encrypted, i32 0, i32 0
  %486 = getelementptr inbounds [64 x i8], [64 x i8]* %encry_permut, i32 0, i32 0
  call void @common_permutation(i8* %485, i8* %486)
  %487 = getelementptr inbounds [64 x i8], [64 x i8]* %encry_permut, i32 0, i32 0
  %488 = call i8* @bin_to_hex(i8* %487)
  store i8* %488, i8** %encryption, align 8
  store i32 0, i32* %i, align 4
  br label %489

; <label>:489		; preds = %484, %500
  %490 = load i32, i32* %i, align 4
  %491 = icmp slt i32 %490, 16
  br i1 %491, label %492, label %503

; <label>:492                                                           		; preds = %489
  %493 = load i8*, i8** %encryption, align 8
  %494 = load i32, i32* %i, align 4
  %495 = getelementptr inbounds i8, i8* %493, i32 %494
  %496 = load i32, i32* %p, align 4
  %497 = add i32 %496, 1
  store i32 %497, i32* %p, align 4
  %498 = getelementptr inbounds [400 x i8], [400 x i8]* %encryption_final, i32 0, i32 %497
  %499 = load i8, i8* %495, align 1
  store i8 %499, i8* %498, align 1
  br label %500

; <label>:500    		; preds = %492
  %501 = load i32, i32* %i, align 4
  %502 = add i32 %501, 1
  store i32 %502, i32* %i, align 4
  br label %489

; <label>:503                                                                   		; preds = %489
  %504 = getelementptr inbounds [32 x i8], [32 x i8]* %L16, i32 0, i32 0
  %505 = getelementptr inbounds [32 x i8], [32 x i8]* %R16, i32 0, i32 0
  %506 = getelementptr inbounds [32 x i8], [32 x i8]* %L15, i32 0, i32 0
  %507 = getelementptr inbounds [32 x i8], [32 x i8]* %R15, i32 0, i32 0
  %508 = getelementptr inbounds [48 x i8], [48 x i8]* %ER15, i32 0, i32 0
  %509 = getelementptr inbounds [48 x i8], [48 x i8]* %K16, i32 0, i32 0
  %510 = getelementptr inbounds [48 x i8], [48 x i8]* %F16, i32 0, i32 0
  call void @des_round_decry(i8* %504, i8* %505, i8* %506, i8* %507, i8* %508, i8* %509, i8* %510)
  %511 = getelementptr inbounds [32 x i8], [32 x i8]* %L15, i32 0, i32 0
  %512 = getelementptr inbounds [32 x i8], [32 x i8]* %R15, i32 0, i32 0
  %513 = getelementptr inbounds [32 x i8], [32 x i8]* %L14, i32 0, i32 0
  %514 = getelementptr inbounds [32 x i8], [32 x i8]* %R14, i32 0, i32 0
  %515 = getelementptr inbounds [48 x i8], [48 x i8]* %ER14, i32 0, i32 0
  %516 = getelementptr inbounds [48 x i8], [48 x i8]* %K15, i32 0, i32 0
  %517 = getelementptr inbounds [48 x i8], [48 x i8]* %F15, i32 0, i32 0
  call void @des_round_decry(i8* %511, i8* %512, i8* %513, i8* %514, i8* %515, i8* %516, i8* %517)
  %518 = getelementptr inbounds [32 x i8], [32 x i8]* %L14, i32 0, i32 0
  %519 = getelementptr inbounds [32 x i8], [32 x i8]* %R14, i32 0, i32 0
  %520 = getelementptr inbounds [32 x i8], [32 x i8]* %L13, i32 0, i32 0
  %521 = getelementptr inbounds [32 x i8], [32 x i8]* %R13, i32 0, i32 0
  %522 = getelementptr inbounds [48 x i8], [48 x i8]* %ER13, i32 0, i32 0
  %523 = getelementptr inbounds [48 x i8], [48 x i8]* %K14, i32 0, i32 0
  %524 = getelementptr inbounds [48 x i8], [48 x i8]* %F14, i32 0, i32 0
  call void @des_round_decry(i8* %518, i8* %519, i8* %520, i8* %521, i8* %522, i8* %523, i8* %524)
  %525 = getelementptr inbounds [32 x i8], [32 x i8]* %L13, i32 0, i32 0
  %526 = getelementptr inbounds [32 x i8], [32 x i8]* %R13, i32 0, i32 0
  %527 = getelementptr inbounds [32 x i8], [32 x i8]* %L12, i32 0, i32 0
  %528 = getelementptr inbounds [32 x i8], [32 x i8]* %R12, i32 0, i32 0
  %529 = getelementptr inbounds [48 x i8], [48 x i8]* %ER12, i32 0, i32 0
  %530 = getelementptr inbounds [48 x i8], [48 x i8]* %K13, i32 0, i32 0
  %531 = getelementptr inbounds [48 x i8], [48 x i8]* %F13, i32 0, i32 0
  call void @des_round_decry(i8* %525, i8* %526, i8* %527, i8* %528, i8* %529, i8* %530, i8* %531)
  %532 = getelementptr inbounds [32 x i8], [32 x i8]* %L12, i32 0, i32 0
  %533 = getelementptr inbounds [32 x i8], [32 x i8]* %R12, i32 0, i32 0
  %534 = getelementptr inbounds [32 x i8], [32 x i8]* %L11, i32 0, i32 0
  %535 = getelementptr inbounds [32 x i8], [32 x i8]* %R11, i32 0, i32 0
  %536 = getelementptr inbounds [48 x i8], [48 x i8]* %ER11, i32 0, i32 0
  %537 = getelementptr inbounds [48 x i8], [48 x i8]* %K12, i32 0, i32 0
  %538 = getelementptr inbounds [48 x i8], [48 x i8]* %F12, i32 0, i32 0
  call void @des_round_decry(i8* %532, i8* %533, i8* %534, i8* %535, i8* %536, i8* %537, i8* %538)
  %539 = getelementptr inbounds [32 x i8], [32 x i8]* %L11, i32 0, i32 0
  %540 = getelementptr inbounds [32 x i8], [32 x i8]* %R11, i32 0, i32 0
  %541 = getelementptr inbounds [32 x i8], [32 x i8]* %L10, i32 0, i32 0
  %542 = getelementptr inbounds [32 x i8], [32 x i8]* %R10, i32 0, i32 0
  %543 = getelementptr inbounds [48 x i8], [48 x i8]* %ER10, i32 0, i32 0
  %544 = getelementptr inbounds [48 x i8], [48 x i8]* %K11, i32 0, i32 0
  %545 = getelementptr inbounds [48 x i8], [48 x i8]* %F11, i32 0, i32 0
  call void @des_round_decry(i8* %539, i8* %540, i8* %541, i8* %542, i8* %543, i8* %544, i8* %545)
  %546 = getelementptr inbounds [32 x i8], [32 x i8]* %L10, i32 0, i32 0
  %547 = getelementptr inbounds [32 x i8], [32 x i8]* %R10, i32 0, i32 0
  %548 = getelementptr inbounds [32 x i8], [32 x i8]* %L9, i32 0, i32 0
  %549 = getelementptr inbounds [32 x i8], [32 x i8]* %R9, i32 0, i32 0
  %550 = getelementptr inbounds [48 x i8], [48 x i8]* %ER9, i32 0, i32 0
  %551 = getelementptr inbounds [48 x i8], [48 x i8]* %K10, i32 0, i32 0
  %552 = getelementptr inbounds [48 x i8], [48 x i8]* %F10, i32 0, i32 0
  call void @des_round_decry(i8* %546, i8* %547, i8* %548, i8* %549, i8* %550, i8* %551, i8* %552)
  %553 = getelementptr inbounds [32 x i8], [32 x i8]* %L9, i32 0, i32 0
  %554 = getelementptr inbounds [32 x i8], [32 x i8]* %R9, i32 0, i32 0
  %555 = getelementptr inbounds [32 x i8], [32 x i8]* %L8, i32 0, i32 0
  %556 = getelementptr inbounds [32 x i8], [32 x i8]* %R8, i32 0, i32 0
  %557 = getelementptr inbounds [48 x i8], [48 x i8]* %ER8, i32 0, i32 0
  %558 = getelementptr inbounds [48 x i8], [48 x i8]* %K9, i32 0, i32 0
  %559 = getelementptr inbounds [48 x i8], [48 x i8]* %F9, i32 0, i32 0
  call void @des_round_decry(i8* %553, i8* %554, i8* %555, i8* %556, i8* %557, i8* %558, i8* %559)
  %560 = getelementptr inbounds [32 x i8], [32 x i8]* %L8, i32 0, i32 0
  %561 = getelementptr inbounds [32 x i8], [32 x i8]* %R8, i32 0, i32 0
  %562 = getelementptr inbounds [32 x i8], [32 x i8]* %L7, i32 0, i32 0
  %563 = getelementptr inbounds [32 x i8], [32 x i8]* %R7, i32 0, i32 0
  %564 = getelementptr inbounds [48 x i8], [48 x i8]* %ER7, i32 0, i32 0
  %565 = getelementptr inbounds [48 x i8], [48 x i8]* %K8, i32 0, i32 0
  %566 = getelementptr inbounds [48 x i8], [48 x i8]* %F8, i32 0, i32 0
  call void @des_round_decry(i8* %560, i8* %561, i8* %562, i8* %563, i8* %564, i8* %565, i8* %566)
  %567 = getelementptr inbounds [32 x i8], [32 x i8]* %L7, i32 0, i32 0
  %568 = getelementptr inbounds [32 x i8], [32 x i8]* %R7, i32 0, i32 0
  %569 = getelementptr inbounds [32 x i8], [32 x i8]* %L6, i32 0, i32 0
  %570 = getelementptr inbounds [32 x i8], [32 x i8]* %R6, i32 0, i32 0
  %571 = getelementptr inbounds [48 x i8], [48 x i8]* %ER6, i32 0, i32 0
  %572 = getelementptr inbounds [48 x i8], [48 x i8]* %K7, i32 0, i32 0
  %573 = getelementptr inbounds [48 x i8], [48 x i8]* %F7, i32 0, i32 0
  call void @des_round_decry(i8* %567, i8* %568, i8* %569, i8* %570, i8* %571, i8* %572, i8* %573)
  %574 = getelementptr inbounds [32 x i8], [32 x i8]* %L6, i32 0, i32 0
  %575 = getelementptr inbounds [32 x i8], [32 x i8]* %R6, i32 0, i32 0
  %576 = getelementptr inbounds [32 x i8], [32 x i8]* %L5, i32 0, i32 0
  %577 = getelementptr inbounds [32 x i8], [32 x i8]* %R5, i32 0, i32 0
  %578 = getelementptr inbounds [48 x i8], [48 x i8]* %ER5, i32 0, i32 0
  %579 = getelementptr inbounds [48 x i8], [48 x i8]* %K6, i32 0, i32 0
  %580 = getelementptr inbounds [48 x i8], [48 x i8]* %F6, i32 0, i32 0
  call void @des_round_decry(i8* %574, i8* %575, i8* %576, i8* %577, i8* %578, i8* %579, i8* %580)
  %581 = getelementptr inbounds [32 x i8], [32 x i8]* %L5, i32 0, i32 0
  %582 = getelementptr inbounds [32 x i8], [32 x i8]* %R5, i32 0, i32 0
  %583 = getelementptr inbounds [32 x i8], [32 x i8]* %L4, i32 0, i32 0
  %584 = getelementptr inbounds [32 x i8], [32 x i8]* %R4, i32 0, i32 0
  %585 = getelementptr inbounds [48 x i8], [48 x i8]* %ER4, i32 0, i32 0
  %586 = getelementptr inbounds [48 x i8], [48 x i8]* %K5, i32 0, i32 0
  %587 = getelementptr inbounds [48 x i8], [48 x i8]* %F5, i32 0, i32 0
  call void @des_round_decry(i8* %581, i8* %582, i8* %583, i8* %584, i8* %585, i8* %586, i8* %587)
  %588 = getelementptr inbounds [32 x i8], [32 x i8]* %L4, i32 0, i32 0
  %589 = getelementptr inbounds [32 x i8], [32 x i8]* %R4, i32 0, i32 0
  %590 = getelementptr inbounds [32 x i8], [32 x i8]* %L3, i32 0, i32 0
  %591 = getelementptr inbounds [32 x i8], [32 x i8]* %R3, i32 0, i32 0
  %592 = getelementptr inbounds [48 x i8], [48 x i8]* %ER3, i32 0, i32 0
  %593 = getelementptr inbounds [48 x i8], [48 x i8]* %K4, i32 0, i32 0
  %594 = getelementptr inbounds [48 x i8], [48 x i8]* %F4, i32 0, i32 0
  call void @des_round_decry(i8* %588, i8* %589, i8* %590, i8* %591, i8* %592, i8* %593, i8* %594)
  %595 = getelementptr inbounds [32 x i8], [32 x i8]* %L3, i32 0, i32 0
  %596 = getelementptr inbounds [32 x i8], [32 x i8]* %R3, i32 0, i32 0
  %597 = getelementptr inbounds [32 x i8], [32 x i8]* %L2, i32 0, i32 0
  %598 = getelementptr inbounds [32 x i8], [32 x i8]* %R2, i32 0, i32 0
  %599 = getelementptr inbounds [48 x i8], [48 x i8]* %ER2, i32 0, i32 0
  %600 = getelementptr inbounds [48 x i8], [48 x i8]* %K3, i32 0, i32 0
  %601 = getelementptr inbounds [48 x i8], [48 x i8]* %F3, i32 0, i32 0
  call void @des_round_decry(i8* %595, i8* %596, i8* %597, i8* %598, i8* %599, i8* %600, i8* %601)
  %602 = getelementptr inbounds [32 x i8], [32 x i8]* %L2, i32 0, i32 0
  %603 = getelementptr inbounds [32 x i8], [32 x i8]* %R2, i32 0, i32 0
  %604 = getelementptr inbounds [32 x i8], [32 x i8]* %L1, i32 0, i32 0
  %605 = getelementptr inbounds [32 x i8], [32 x i8]* %R1, i32 0, i32 0
  %606 = getelementptr inbounds [48 x i8], [48 x i8]* %ER1, i32 0, i32 0
  %607 = getelementptr inbounds [48 x i8], [48 x i8]* %K2, i32 0, i32 0
  %608 = getelementptr inbounds [48 x i8], [48 x i8]* %F2, i32 0, i32 0
  call void @des_round_decry(i8* %602, i8* %603, i8* %604, i8* %605, i8* %606, i8* %607, i8* %608)
  %609 = getelementptr inbounds [32 x i8], [32 x i8]* %L1, i32 0, i32 0
  %610 = getelementptr inbounds [32 x i8], [32 x i8]* %R1, i32 0, i32 0
  %611 = getelementptr inbounds [32 x i8], [32 x i8]* %L0, i32 0, i32 0
  %612 = getelementptr inbounds [32 x i8], [32 x i8]* %R0, i32 0, i32 0
  %613 = getelementptr inbounds [48 x i8], [48 x i8]* %ER0, i32 0, i32 0
  %614 = getelementptr inbounds [48 x i8], [48 x i8]* %K1, i32 0, i32 0
  %615 = getelementptr inbounds [48 x i8], [48 x i8]* %F1, i32 0, i32 0
  call void @des_round_decry(i8* %609, i8* %610, i8* %611, i8* %612, i8* %613, i8* %614, i8* %615)
  store i32 0, i32* %i, align 4
  br label %616

; <label>:616		; preds = %503, %631
  %617 = load i32, i32* %i, align 4
  %618 = icmp slt i32 %617, 32
  br i1 %618, label %619, label %634

; <label>:619                                                  		; preds = %616
  %620 = load i32, i32* %i, align 4
  %621 = getelementptr inbounds [32 x i8], [32 x i8]* %L0, i32 0, i32 %620
  %622 = load i32, i32* %i, align 4
  %623 = getelementptr inbounds [64 x i8], [64 x i8]* %decrypted, i32 0, i32 %622
  %624 = load i8, i8* %621, align 1
  store i8 %624, i8* %623, align 1
  %625 = load i32, i32* %i, align 4
  %626 = getelementptr inbounds [32 x i8], [32 x i8]* %R0, i32 0, i32 %625
  %627 = load i32, i32* %i, align 4
  %628 = add i32 %627, 32
  %629 = getelementptr inbounds [64 x i8], [64 x i8]* %decrypted, i32 0, i32 %628
  %630 = load i8, i8* %626, align 1
  store i8 %630, i8* %629, align 1
  br label %631

; <label>:631    		; preds = %619
  %632 = load i32, i32* %i, align 4
  %633 = add i32 %632, 1
  store i32 %633, i32* %i, align 4
  br label %616

; <label>:634                                                  		; preds = %616
  %635 = getelementptr inbounds [64 x i8], [64 x i8]* %decrypted, i32 0, i32 0
  %636 = getelementptr inbounds [64 x i8], [64 x i8]* %decry_permut, i32 0, i32 0
  call void @common_permutation(i8* %635, i8* %636)
  %637 = getelementptr inbounds [64 x i8], [64 x i8]* %decry_permut, i32 0, i32 0
  %638 = call i8* @bin_to_hex(i8* %637)
  store i8* %638, i8** %decryption, align 8
  store i32 0, i32* %i, align 4
  br label %639

; <label>:639		; preds = %634, %650
  %640 = load i32, i32* %i, align 4
  %641 = icmp slt i32 %640, 16
  br i1 %641, label %642, label %653

; <label>:642                                                               		; preds = %639
  %643 = load i8*, i8** %decryption, align 8
  %644 = load i32, i32* %i, align 4
  %645 = getelementptr inbounds i8, i8* %643, i32 %644
  %646 = load i32, i32* %q, align 4
  %647 = add i32 %646, 1
  store i32 %647, i32* %q, align 4
  %648 = getelementptr inbounds [400 x i8], [400 x i8]* %decryption_final_hex, i32 0, i32 %647
  %649 = load i8, i8* %645, align 1
  store i8 %649, i8* %648, align 1
  br label %650

; <label>:650    		; preds = %642
  %651 = load i32, i32* %i, align 4
  %652 = add i32 %651, 1
  store i32 %652, i32* %i, align 4
  br label %639

; <label>:653		; preds = %639
  br label %654

; <label>:654    		; preds = %653
  %655 = load i32, i32* %m, align 4
  %656 = add i32 %655, 1
  store i32 %656, i32* %m, align 4
  br label %180

; <label>:657                          		; preds = %180
  %658 = load i32, i32* %p, align 4
  %659 = add i32 %658, 1
  %660 = getelementptr inbounds [400 x i8], [400 x i8]* %encryption_final, i32 0, i32 %659
  store i8 0, i8* %660, align 1
  %661 = getelementptr inbounds [23 x i8], [23 x i8]* @.str23, i32 0, i32 0
  %662 = call i32 (i8*, ...)* @printf(i8* %661)
  %663 = getelementptr inbounds [3 x i8], [3 x i8]* @.str24, i32 0, i32 0
  %664 = getelementptr inbounds [400 x i8], [400 x i8]* %encryption_final, i32 0, i32 0
  %665 = call i32 (i8*, ...)* @printf(i8* %663, i8* %664)
  %666 = load i32, i32* %q, align 4
  %667 = add i32 %666, 1
  %668 = getelementptr inbounds [400 x i8], [400 x i8]* %decryption_final_hex, i32 0, i32 %667
  store i8 0, i8* %668, align 1
  %669 = getelementptr inbounds [37 x i8], [37 x i8]* @.str25, i32 0, i32 0
  %670 = call i32 (i8*, ...)* @printf(i8* %669)
  %671 = getelementptr inbounds [3 x i8], [3 x i8]* @.str24, i32 0, i32 0
  %672 = getelementptr inbounds [400 x i8], [400 x i8]* %decryption_final_hex, i32 0, i32 0
  %673 = call i32 (i8*, ...)* @printf(i8* %671, i8* %672)
  %674 = getelementptr inbounds [400 x i8], [400 x i8]* %decryption_final_hex, i32 0, i32 0
  %675 = getelementptr inbounds [200 x i8], [200 x i8]* %decryption_final_plain, i32 0, i32 0
  %676 = load i32, i32* %q, align 4
  %677 = add i32 %676, 1
  call void @hex_to_plain(i8* %674, i8* %675, i32 %677)
  %678 = getelementptr inbounds [35 x i8], [35 x i8]* @.str26, i32 0, i32 0
  %679 = call i32 (i8*, ...)* @printf(i8* %678)
  %680 = getelementptr inbounds [4 x i8], [4 x i8]* @.str27, i32 0, i32 0
  %681 = getelementptr inbounds [200 x i8], [200 x i8]* %decryption_final_plain, i32 0, i32 0
  %682 = call i32 (i8*, ...)* @printf(i8* %680, i8* %681)
  %683 = load i32, i32* %1, align 4
  ret i32 %683
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
