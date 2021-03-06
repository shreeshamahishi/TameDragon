%struct.addressStruct = type { i32, i8*, i8*, i8*, i8*, i8* }
%struct.contactDetails = type { %struct.addressStruct, i8* }
%struct.PersonAttr = type { i8*, i8*, double, %struct.contactDetails, %struct.contactDetails, i64 }

@.str = private unnamed_addr constant [10 x i8] c"hn1 = %d\0A\00", align 1
@.str1 = private unnamed_addr constant [10 x i8] c"hn2 = %d\0A\00", align 1
@.str2 = private unnamed_addr constant [10 x i8] c"hn3 = %d\0A\00", align 1
@.str3 = private unnamed_addr constant [12 x i8] c"name1 = %s\0A\00", align 1
@.str4 = private unnamed_addr constant [12 x i8] c"name2 = %s\0A\00", align 1
@.str5 = private unnamed_addr constant [7 x i8] c"Vikash\00", align 1
@.str6 = private unnamed_addr constant [6 x i8] c"Joshi\00", align 1
@.str7 = private unnamed_addr constant [9 x i8] c"T.T Road\00", align 1
@.str8 = private unnamed_addr constant [10 x i8] c"Bangalore\00", align 1
@.str9 = private unnamed_addr constant [10 x i8] c"Karnataka\00", align 1
@.str10 = private unnamed_addr constant [7 x i8] c"560070\00", align 1
@.str11 = private unnamed_addr constant [6 x i8] c"India\00", align 1
@.str12 = private unnamed_addr constant [7 x i8] c"Anurup\00", align 1
@.str13 = private unnamed_addr constant [8 x i8] c"Iyenger\00", align 1
@.str14 = private unnamed_addr constant [13 x i8] c"Basanvangudi\00", align 1
@.str15 = private unnamed_addr constant [7 x i8] c"560069\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @foo(%struct.PersonAttr* %allPersons, i32 %m, i32 %n) nounwind {
  %1 = alloca %struct.PersonAttr*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %name1 = alloca i8*, align 8
  %name2 = alloca i8*, align 8
  %hn1 = alloca i32, align 4
  %hn2 = alloca i32, align 4
  %hn3 = alloca i32, align 4
  store %struct.PersonAttr* %allPersons, %struct.PersonAttr** %1, align 8
  store i32 %m, i32* %2, align 4
  store i32 %n, i32* %3, align 4
  %4 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %5 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %4, i32 0
  %6 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %5, i32 0, i32 3
  %7 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %6, i32 0, i32 0
  %8 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %7, i32 0, i32 0
  %9 = load i32, i32* %8, align 4
  store i32 %9, i32* %hn1, align 4
  %10 = getelementptr inbounds [10 x i8], [10 x i8]* @.str, i32 0, i32 0
  %11 = load i32, i32* %hn1, align 4
  %12 = call i32 (i8*, ...)* @printf(i8* %10, i32 %11)
  %13 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %14 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %13, i32 0
  %15 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %14, i32 0, i32 3
  %16 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %15, i32 0, i32 0
  %17 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %16, i32 0, i32 0
  %18 = load i32, i32* %17, align 4
  store i32 %18, i32* %hn2, align 4
  %19 = getelementptr inbounds [10 x i8], [10 x i8]* @.str1, i32 0, i32 0
  %20 = load i32, i32* %hn2, align 4
  %21 = call i32 (i8*, ...)* @printf(i8* %19, i32 %20)
  %22 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %23 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %22, i32 1
  %24 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %23, i32 0, i32 3
  %25 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %24, i32 0, i32 0
  %26 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %25, i32 0, i32 0
  %27 = load i32, i32* %26, align 4
  store i32 %27, i32* %hn3, align 4
  %28 = getelementptr inbounds [10 x i8], [10 x i8]* @.str2, i32 0, i32 0
  %29 = load i32, i32* %hn3, align 4
  %30 = call i32 (i8*, ...)* @printf(i8* %28, i32 %29)
  %31 = load i32, i32* %hn1, align 4
  %32 = load i32, i32* %hn3, align 4
  %33 = icmp eq i32 %31, %32
  br i1 %33, label %34, label %42

; <label>:34                          		; preds = %0
  %35 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %36 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %35, i32 0
  %37 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %36, i32 0, i32 0
  %38 = load i8*, i8** %37, align 8
  store i8* %38, i8** %name1, align 8
  %39 = getelementptr inbounds [12 x i8], [12 x i8]* @.str3, i32 0, i32 0
  %40 = load i8*, i8** %name1, align 8
  %41 = call i32 (i8*, ...)* @printf(i8* %39, i8* %40)
  br label %42

; <label>:42		; preds = %34, %0
  %43 = load i32, i32* %hn1, align 4
  %44 = load i32, i32* %hn2, align 4
  %45 = icmp eq i32 %43, %44
  br i1 %45, label %46, label %54

; <label>:46                         		; preds = %42
  %47 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %48 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %47, i32 0
  %49 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %48, i32 0, i32 0
  %50 = load i8*, i8** %49, align 8
  store i8* %50, i8** %name2, align 8
  %51 = getelementptr inbounds [12 x i8], [12 x i8]* @.str4, i32 0, i32 0
  %52 = load i8*, i8** %name2, align 8
  %53 = call i32 (i8*, ...)* @printf(i8* %51, i8* %52)
  br label %54

; <label>:54		; preds = %46, %42
  ret i32 0
}