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
  %1 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %allPersons, i32 0
  %2 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %1, i32 0, i32 3
  %3 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %2, i32 0, i32 0
  %4 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %3, i32 0, i32 0
  %5 = load i32, i32* %4, align 4
  %6 = getelementptr inbounds [10 x i8], [10 x i8]* @.str, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6, i32 %5)
  %8 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %allPersons, i32 0
  %9 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %8, i32 0, i32 3
  %10 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %9, i32 0, i32 0
  %11 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %10, i32 0, i32 0
  %12 = load i32, i32* %11, align 4
  %13 = getelementptr inbounds [10 x i8], [10 x i8]* @.str1, i32 0, i32 0
  %14 = call i32 (i8*, ...)* @printf(i8* %13, i32 %12)
  %15 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %allPersons, i32 1
  %16 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %15, i32 0, i32 3
  %17 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %16, i32 0, i32 0
  %18 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %17, i32 0, i32 0
  %19 = load i32, i32* %18, align 4
  %20 = getelementptr inbounds [10 x i8], [10 x i8]* @.str2, i32 0, i32 0
  %21 = call i32 (i8*, ...)* @printf(i8* %20, i32 %19)
  %22 = icmp eq i32 %5, %19
  br i1 %22, label %23, label %29

; <label>:23                                         		; preds = %0
  %24 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %allPersons, i32 0
  %25 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %24, i32 0, i32 0
  %26 = load i8*, i8** %25, align 8
  %27 = getelementptr inbounds [12 x i8], [12 x i8]* @.str3, i32 0, i32 0
  %28 = call i32 (i8*, ...)* @printf(i8* %27, i8* %26)
  br label %29

; <label>:29		; preds = %23, %0
  %30 = icmp eq i32 %5, %12
  br i1 %30, label %31, label %37

; <label>:31                                        		; preds = %29
  %32 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %allPersons, i32 0
  %33 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %32, i32 0, i32 0
  %34 = load i8*, i8** %33, align 8
  %35 = getelementptr inbounds [12 x i8], [12 x i8]* @.str4, i32 0, i32 0
  %36 = call i32 (i8*, ...)* @printf(i8* %35, i8* %34)
  br label %37

; <label>:37		; preds = %31, %29
  ret i32 0
}