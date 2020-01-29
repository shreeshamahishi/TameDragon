%struct.NODE = type { i32, %struct.NODE* }

@.str = private unnamed_addr constant [23 x i8] c"\0A-- Menu Selection --\0A\00", align 16
@.str1 = private unnamed_addr constant [9 x i8] c"0) Quit\0A\00", align 1
@.str2 = private unnamed_addr constant [11 x i8] c"1) Insert\0A\00", align 1
@.str3 = private unnamed_addr constant [11 x i8] c"2) Delete\0A\00", align 1
@.str4 = private unnamed_addr constant [11 x i8] c"3) Search\0A\00", align 1
@.str5 = private unnamed_addr constant [12 x i8] c"4) Display\0A\00", align 1
@.str6 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str7 = private unnamed_addr constant [13 x i8] c"Goodbye ...\0A\00", align 1
@.str8 = private unnamed_addr constant [26 x i8] c"Your choice: `Insertion'\0A\00", align 16
@.str9 = private unnamed_addr constant [43 x i8] c"Enter the value which should be inserted: \00", align 16
@.str10 = private unnamed_addr constant [25 x i8] c"Your choice: `Deletion'\0A\00", align 16
@.str11 = private unnamed_addr constant [42 x i8] c"Enter the value which should be deleted: \00", align 16
@.str12 = private unnamed_addr constant [23 x i8] c"Your choice: `Search'\0A\00", align 16
@.str13 = private unnamed_addr constant [35 x i8] c"Enter the value you want to find: \00", align 16
@.str14 = private unnamed_addr constant [22 x i8] c"Value `%d' not found\0A\00", align 16
@.str15 = private unnamed_addr constant [37 x i8] c"Value `%d' located at position `%d'\0A\00", align 16
@.str16 = private unnamed_addr constant [23 x i8] c"You choice: `Display'\0A\00", align 16
@.str17 = private unnamed_addr constant [4 x i8] c"%d \00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

declare noalias i8* @malloc(i64) nounwind

declare void @free(i8*) nounwind

define i32 @search_value(%struct.NODE* %llist, i32 %num) nounwind {
  %1 = alloca %struct.NODE*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %retval = alloca i32, align 4
  %i = alloca i32, align 4
  store %struct.NODE* %llist, %struct.NODE** %1, align 8
  store i32 %num, i32* %2, align 4
  store i32 -1, i32* %retval, align 4
  store i32 1, i32* %i, align 4
  br label %4

; <label>:4                                          		; preds = %0, %22
  %5 = load %struct.NODE*, %struct.NODE** %1, align 8
  %6 = getelementptr inbounds %struct.NODE, %struct.NODE* %5, i32 0, i32 1
  %7 = load %struct.NODE*, %struct.NODE** %6, align 8
  %8 = icmp ne %struct.NODE* %7, null
  br i1 %8, label %9, label %26

; <label>:9                                                 		; preds = %4
  %10 = load %struct.NODE*, %struct.NODE** %1, align 8
  %11 = getelementptr inbounds %struct.NODE, %struct.NODE* %10, i32 0, i32 1
  %12 = load %struct.NODE*, %struct.NODE** %11, align 8
  %13 = getelementptr inbounds %struct.NODE, %struct.NODE* %12, i32 0, i32 0
  %14 = load i32, i32* %2, align 4
  %15 = load i32, i32* %13, align 4
  %16 = icmp eq i32 %15, %14
  br i1 %16, label %17, label %19

; <label>:17      		; preds = %9
  %18 = load i32, i32* %i, align 4
  store i32 %18, i32* %3, align 4
  br label %28

; <label>:19      		; preds = %9
  %20 = load i32, i32* %i, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* %i, align 4
  br label %22

; <label>:22                          		; preds = %19
  %23 = load %struct.NODE*, %struct.NODE** %1, align 8
  %24 = getelementptr inbounds %struct.NODE, %struct.NODE* %23, i32 0, i32 1
  %25 = load %struct.NODE*, %struct.NODE** %24, align 8
  store %struct.NODE* %25, %struct.NODE** %1, align 8
  br label %4

; <label>:26           		; preds = %4
  %27 = load i32, i32* %retval, align 4
  store i32 %27, i32* %3, align 4
  br label %28

; <label>:28		; preds = %17, %26
  %29 = load i32, i32* %3, align 4
  ret i32 %29
}

define void @append_node(%struct.NODE* %llist, i32 %num) nounwind {
  %1 = alloca %struct.NODE*, align 8
  %2 = alloca i32, align 4
  store %struct.NODE* %llist, %struct.NODE** %1, align 8
  store i32 %num, i32* %2, align 4
  br label %3

; <label>:3                                           		; preds = %0, %8
  %4 = load %struct.NODE*, %struct.NODE** %1, align 8
  %5 = getelementptr inbounds %struct.NODE, %struct.NODE* %4, i32 0, i32 1
  %6 = load %struct.NODE*, %struct.NODE** %5, align 8
  %7 = icmp ne %struct.NODE* %6, null
  br i1 %7, label %8, label %12

; <label>:8                            		; preds = %3
  %9 = load %struct.NODE*, %struct.NODE** %1, align 8
  %10 = getelementptr inbounds %struct.NODE, %struct.NODE* %9, i32 0, i32 1
  %11 = load %struct.NODE*, %struct.NODE** %10, align 8
  store %struct.NODE* %11, %struct.NODE** %1, align 8
  br label %3

; <label>:12                           		; preds = %3
  %13 = call i8* @malloc(i64 8) nounwind
  %14 = bitcast i8* %13 to %struct.NODE*
  %15 = load %struct.NODE*, %struct.NODE** %1, align 8
  %16 = getelementptr inbounds %struct.NODE, %struct.NODE* %15, i32 0, i32 1
  store %struct.NODE* %14, %struct.NODE** %16, align 8
  %17 = load %struct.NODE*, %struct.NODE** %1, align 8
  %18 = getelementptr inbounds %struct.NODE, %struct.NODE* %17, i32 0, i32 1
  %19 = load %struct.NODE*, %struct.NODE** %18, align 8
  %20 = getelementptr inbounds %struct.NODE, %struct.NODE* %19, i32 0, i32 0
  %21 = load i32, i32* %2, align 4
  store i32 %21, i32* %20, align 4
  %22 = load %struct.NODE*, %struct.NODE** %1, align 8
  %23 = getelementptr inbounds %struct.NODE, %struct.NODE* %22, i32 0, i32 1
  %24 = load %struct.NODE*, %struct.NODE** %23, align 8
  %25 = getelementptr inbounds %struct.NODE, %struct.NODE* %24, i32 0, i32 1
  store %struct.NODE* null, %struct.NODE** %25, align 8
  ret void
}

define void @display_list(%struct.NODE* %llist) nounwind {
  %1 = alloca %struct.NODE*, align 8
  store %struct.NODE* %llist, %struct.NODE** %1, align 8
  br label %2

; <label>:2                                           		; preds = %0, %7
  %3 = load %struct.NODE*, %struct.NODE** %1, align 8
  %4 = getelementptr inbounds %struct.NODE, %struct.NODE* %3, i32 0, i32 1
  %5 = load %struct.NODE*, %struct.NODE** %4, align 8
  %6 = icmp ne %struct.NODE* %5, null
  br i1 %6, label %7, label %16

; <label>:7                                                 		; preds = %2
  %8 = getelementptr inbounds [4 x i8], [4 x i8]* @.str17, i32 0, i32 0
  %9 = load %struct.NODE*, %struct.NODE** %1, align 8
  %10 = getelementptr inbounds %struct.NODE, %struct.NODE* %9, i32 0, i32 0
  %11 = load i32, i32* %10, align 4
  %12 = call i32 (i8*, ...)* @printf(i8* %8, i32 %11)
  %13 = load %struct.NODE*, %struct.NODE** %1, align 8
  %14 = getelementptr inbounds %struct.NODE, %struct.NODE* %13, i32 0, i32 1
  %15 = load %struct.NODE*, %struct.NODE** %14, align 8
  store %struct.NODE* %15, %struct.NODE** %1, align 8
  br label %2

; <label>:16                                                		; preds = %2
  %17 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %18 = load %struct.NODE*, %struct.NODE** %1, align 8
  %19 = getelementptr inbounds %struct.NODE, %struct.NODE* %18, i32 0, i32 0
  %20 = load i32, i32* %19, align 4
  %21 = call i32 (i8*, ...)* @printf(i8* %17, i32 %20)
  ret void
}

define void @delete_node(%struct.NODE* %llist, i32 %num) nounwind {
  %1 = alloca %struct.NODE*, align 8
  %2 = alloca i32, align 4
  %temp = alloca %struct.NODE*, align 8
  store %struct.NODE* %llist, %struct.NODE** %1, align 8
  store i32 %num, i32* %2, align 4
  %3 = call i8* @malloc(i64 8) nounwind
  %4 = bitcast i8* %3 to %struct.NODE*
  store %struct.NODE* %4, %struct.NODE** %temp, align 8
  %5 = load %struct.NODE*, %struct.NODE** %1, align 8
  %6 = getelementptr inbounds %struct.NODE, %struct.NODE* %5, i32 0, i32 0
  %7 = load i32, i32* %2, align 4
  %8 = load i32, i32* %6, align 4
  %9 = icmp eq i32 %8, %7
  br i1 %9, label %10, label %17

; <label>:10                             		; preds = %0
  %11 = load %struct.NODE*, %struct.NODE** %1, align 8
  %12 = getelementptr inbounds %struct.NODE, %struct.NODE* %11, i32 0, i32 1
  %13 = load %struct.NODE*, %struct.NODE** %12, align 8
  store %struct.NODE* %13, %struct.NODE** %temp, align 8
  %14 = load %struct.NODE*, %struct.NODE** %1, align 8
  %15 = bitcast %struct.NODE* %14 to i8*
  call void @free(i8* %15) nounwind
  %16 = load %struct.NODE*, %struct.NODE** %temp, align 8
  store %struct.NODE* %16, %struct.NODE** %1, align 8
  br label %43

; <label>:17		; preds = %0
  br label %18

; <label>:18                                          		; preds = %17, %26
  %19 = load %struct.NODE*, %struct.NODE** %1, align 8
  %20 = getelementptr inbounds %struct.NODE, %struct.NODE* %19, i32 0, i32 1
  %21 = load %struct.NODE*, %struct.NODE** %20, align 8
  %22 = getelementptr inbounds %struct.NODE, %struct.NODE* %21, i32 0, i32 0
  %23 = load i32, i32* %2, align 4
  %24 = load i32, i32* %22, align 4
  %25 = icmp ne i32 %24, %23
  br i1 %25, label %26, label %30

; <label>:26                          		; preds = %18
  %27 = load %struct.NODE*, %struct.NODE** %1, align 8
  %28 = getelementptr inbounds %struct.NODE, %struct.NODE* %27, i32 0, i32 1
  %29 = load %struct.NODE*, %struct.NODE** %28, align 8
  store %struct.NODE* %29, %struct.NODE** %1, align 8
  br label %18

; <label>:30                            		; preds = %18
  %31 = load %struct.NODE*, %struct.NODE** %1, align 8
  %32 = getelementptr inbounds %struct.NODE, %struct.NODE* %31, i32 0, i32 1
  %33 = load %struct.NODE*, %struct.NODE** %32, align 8
  %34 = getelementptr inbounds %struct.NODE, %struct.NODE* %33, i32 0, i32 1
  %35 = load %struct.NODE*, %struct.NODE** %34, align 8
  store %struct.NODE* %35, %struct.NODE** %temp, align 8
  %36 = load %struct.NODE*, %struct.NODE** %1, align 8
  %37 = getelementptr inbounds %struct.NODE, %struct.NODE* %36, i32 0, i32 1
  %38 = load %struct.NODE*, %struct.NODE** %37, align 8
  %39 = bitcast %struct.NODE* %38 to i8*
  call void @free(i8* %39) nounwind
  %40 = load %struct.NODE*, %struct.NODE** %1, align 8
  %41 = getelementptr inbounds %struct.NODE, %struct.NODE* %40, i32 0, i32 1
  %42 = load %struct.NODE*, %struct.NODE** %temp, align 8
  store %struct.NODE* %42, %struct.NODE** %41, align 8
  br label %43

; <label>:43		; preds = %10, %30
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %num = alloca i32, align 4
  %input = alloca i32, align 4
  %retval = alloca i32, align 4
  %llist = alloca %struct.NODE*, align 8
  store i32 0, i32* %1, align 4
  store i32 0, i32* %num, align 4
  store i32 1, i32* %input, align 4
  store i32 0, i32* %retval, align 4
  %2 = call i8* @malloc(i64 8) nounwind
  %3 = bitcast i8* %2 to %struct.NODE*
  store %struct.NODE* %3, %struct.NODE** %llist, align 8
  %4 = load %struct.NODE*, %struct.NODE** %llist, align 8
  %5 = getelementptr inbounds %struct.NODE, %struct.NODE* %4, i32 0, i32 0
  store i32 0, i32* %5, align 4
  %6 = load %struct.NODE*, %struct.NODE** %llist, align 8
  %7 = getelementptr inbounds %struct.NODE, %struct.NODE* %6, i32 0, i32 1
  store %struct.NODE* null, %struct.NODE** %7, align 8
  br label %8

; <label>:8     		; preds = %0, %74
  %9 = load i32, i32* %input, align 4
  %10 = icmp ne i32 %9, 0
  br i1 %10, label %11, label %75

; <label>:11                                                                                                               		; preds = %8
  %12 = getelementptr inbounds [23 x i8], [23 x i8]* @.str, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @printf(i8* %12)
  %14 = getelementptr inbounds [9 x i8], [9 x i8]* @.str1, i32 0, i32 0
  %15 = call i32 (i8*, ...)* @printf(i8* %14)
  %16 = getelementptr inbounds [11 x i8], [11 x i8]* @.str2, i32 0, i32 0
  %17 = call i32 (i8*, ...)* @printf(i8* %16)
  %18 = getelementptr inbounds [11 x i8], [11 x i8]* @.str3, i32 0, i32 0
  %19 = call i32 (i8*, ...)* @printf(i8* %18)
  %20 = getelementptr inbounds [11 x i8], [11 x i8]* @.str4, i32 0, i32 0
  %21 = call i32 (i8*, ...)* @printf(i8* %20)
  %22 = getelementptr inbounds [12 x i8], [12 x i8]* @.str5, i32 0, i32 0
  %23 = call i32 (i8*, ...)* @printf(i8* %22)
  %24 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %25 = call i32 (i8*, ...)* @scanf(i8* %24, i32* %input)
  %26 = load i32, i32* %input, align 4
  switch i32 %26, label %28 [
    i32 0, label %27
    i32 1, label %31
    i32 2, label %40
    i32 3, label %49
    i32 4, label %70 
  ]

; <label>:27		; preds = %11
  br label %28

; <label>:28                                       		; preds = %11, %27
  %29 = getelementptr inbounds [13 x i8], [13 x i8]* @.str7, i32 0, i32 0
  %30 = call i32 (i8*, ...)* @printf(i8* %29)
  store i32 0, i32* %input, align 4
  br label %74

; <label>:31                                            		; preds = %11
  %32 = getelementptr inbounds [26 x i8], [26 x i8]* @.str8, i32 0, i32 0
  %33 = call i32 (i8*, ...)* @printf(i8* %32)
  %34 = getelementptr inbounds [43 x i8], [43 x i8]* @.str9, i32 0, i32 0
  %35 = call i32 (i8*, ...)* @printf(i8* %34)
  %36 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %37 = call i32 (i8*, ...)* @scanf(i8* %36, i32* %num)
  %38 = load %struct.NODE*, %struct.NODE** %llist, align 8
  %39 = load i32, i32* %num, align 4
  call void @append_node(%struct.NODE* %38, i32 %39)
  br label %74

; <label>:40                                             		; preds = %11
  %41 = getelementptr inbounds [25 x i8], [25 x i8]* @.str10, i32 0, i32 0
  %42 = call i32 (i8*, ...)* @printf(i8* %41)
  %43 = getelementptr inbounds [42 x i8], [42 x i8]* @.str11, i32 0, i32 0
  %44 = call i32 (i8*, ...)* @printf(i8* %43)
  %45 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %46 = call i32 (i8*, ...)* @scanf(i8* %45, i32* %num)
  %47 = load %struct.NODE*, %struct.NODE** %llist, align 8
  %48 = load i32, i32* %num, align 4
  call void @delete_node(%struct.NODE* %47, i32 %48)
  br label %74

; <label>:49                                             		; preds = %11
  %50 = getelementptr inbounds [23 x i8], [23 x i8]* @.str12, i32 0, i32 0
  %51 = call i32 (i8*, ...)* @printf(i8* %50)
  %52 = getelementptr inbounds [35 x i8], [35 x i8]* @.str13, i32 0, i32 0
  %53 = call i32 (i8*, ...)* @printf(i8* %52)
  %54 = getelementptr inbounds [3 x i8], [3 x i8]* @.str6, i32 0, i32 0
  %55 = call i32 (i8*, ...)* @scanf(i8* %54, i32* %num)
  %56 = load %struct.NODE*, %struct.NODE** %llist, align 8
  %57 = load i32, i32* %num, align 4
  %58 = call i32 @search_value(%struct.NODE* %56, i32 %57)
  store i32 %58, i32* %retval, align 4
  %59 = icmp eq i32 %58, -1
  br i1 %59, label %60, label %64

; <label>:60                                             		; preds = %49
  %61 = getelementptr inbounds [22 x i8], [22 x i8]* @.str14, i32 0, i32 0
  %62 = load i32, i32* %num, align 4
  %63 = call i32 (i8*, ...)* @printf(i8* %61, i32 %62)
  br label %69

; <label>:64                                             		; preds = %49
  %65 = getelementptr inbounds [37 x i8], [37 x i8]* @.str15, i32 0, i32 0
  %66 = load i32, i32* %num, align 4
  %67 = load i32, i32* %retval, align 4
  %68 = call i32 (i8*, ...)* @printf(i8* %65, i32 %66, i32 %67)
  br label %69

; <label>:69		; preds = %60, %64
  br label %74

; <label>:70                                             		; preds = %11
  %71 = getelementptr inbounds [23 x i8], [23 x i8]* @.str16, i32 0, i32 0
  %72 = call i32 (i8*, ...)* @printf(i8* %71)
  %73 = load %struct.NODE*, %struct.NODE** %llist, align 8
  call void @display_list(%struct.NODE* %73)
  br label %74

; <label>:74		; preds = %28, %31, %40, %69, %70
  br label %8

; <label>:75                              		; preds = %8
  %76 = load %struct.NODE*, %struct.NODE** %llist, align 8
  %77 = bitcast %struct.NODE* %76 to i8*
  call void @free(i8* %77) nounwind
  ret i32 0
}
