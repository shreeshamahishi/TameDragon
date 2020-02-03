@queue_arr = common global [50 x i32] zeroinitializer, align 16
@rear = global i32 -1, align 4
@front = global i32 -1, align 4
@.str = private unnamed_addr constant [16 x i8] c"Queue Overflow\0A\00", align 16
@.str1 = private unnamed_addr constant [41 x i8] c"Input the element for adding in queue : \00", align 16
@.str2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str3 = private unnamed_addr constant [17 x i8] c"Queue Underflow\0A\00", align 16
@.str4 = private unnamed_addr constant [36 x i8] c"Element deleted from queue is : %d\0A\00", align 16
@.str5 = private unnamed_addr constant [16 x i8] c"Queue is empty\0A\00", align 16
@.str6 = private unnamed_addr constant [12 x i8] c"Queue is :\0A\00", align 1
@.str7 = private unnamed_addr constant [4 x i8] c"%d \00", align 1
@.str8 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str9 = private unnamed_addr constant [10 x i8] c"1.Insert\0A\00", align 1
@.str10 = private unnamed_addr constant [10 x i8] c"2.Delete\0A\00", align 1
@.str11 = private unnamed_addr constant [11 x i8] c"3.Display\0A\00", align 1
@.str12 = private unnamed_addr constant [8 x i8] c"4.Quit\0A\00", align 1
@.str13 = private unnamed_addr constant [21 x i8] c"Enter your choice : \00", align 16
@.str14 = private unnamed_addr constant [14 x i8] c"Wrong choice\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

declare void @exit(i32) 

define i32 @insert() nounwind {
  %1 = alloca i32, align 4
  %added_item = alloca i32, align 4
  %2 = load i32, i32* @rear, align 4
  %3 = icmp eq i32 %2, 49
  br i1 %3, label %4, label %7

; <label>:4                                            		; preds = %0
  %5 = getelementptr inbounds [16 x i8], [16 x i8]* @.str, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @printf(i8* %5)
  br label %21

; <label>:7          		; preds = %0
  %8 = load i32, i32* @front, align 4
  %9 = icmp eq i32 %8, -1
  br i1 %9, label %10, label %11

; <label>:10       		; preds = %7
  store i32 0, i32* @front, align 4
  br label %11

; <label>:11                                                		; preds = %7, %10
  %12 = getelementptr inbounds [41 x i8], [41 x i8]* @.str1, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @printf(i8* %12)
  %14 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %15 = call i32 (i8*, ...)* @scanf(i8* %14, i32* %added_item)
  %16 = load i32, i32* @rear, align 4
  %17 = add i32 %16, 1
  store i32 %17, i32* @rear, align 4
  %18 = load i32, i32* @rear, align 4
  %19 = getelementptr inbounds [50 x i32], [50 x i32]* @queue_arr, i32 0, i32 %18
  %20 = load i32, i32* %added_item, align 4
  store i32 %20, i32* %19, align 4
  br label %21

; <label>:21 		; preds = %4, %11
  %22 = load i32, i32* %1, align 4
  ret i32 %22
}

define i32 @del() nounwind {
  %1 = alloca i32, align 4
  %2 = load i32, i32* @front, align 4
  %3 = icmp eq i32 %2, -1
  br i1 %3, label %8, label %4

; <label>:4          		; preds = %0
  %5 = load i32, i32* @front, align 4
  %6 = load i32, i32* @rear, align 4
  %7 = icmp sgt i32 %5, %6
  br label %8

; <label>:8       		; preds = %0, %4
  %9 = phi i1 [ true, %0 ], [ %7, %4 ]
  br i1 %9, label %10, label %13

; <label>:10                                             		; preds = %8
  %11 = getelementptr inbounds [17 x i8], [17 x i8]* @.str3, i32 0, i32 0
  %12 = call i32 (i8*, ...)* @printf(i8* %11)
  br label %21

; <label>:13                          		; preds = %8
  %14 = load i32, i32* @front, align 4
  %15 = getelementptr inbounds [50 x i32], [50 x i32]* @queue_arr, i32 0, i32 %14
  %16 = getelementptr inbounds [36 x i8], [36 x i8]* @.str4, i32 0, i32 0
  %17 = load i32, i32* %15, align 4
  %18 = call i32 (i8*, ...)* @printf(i8* %16, i32 %17)
  %19 = load i32, i32* @front, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* @front, align 4
  br label %21

; <label>:21		; preds = %10, %13
  %22 = load i32, i32* %1, align 4
  ret i32 %22
}

define i32 @display() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  %2 = load i32, i32* @front, align 4
  %3 = icmp eq i32 %2, -1
  br i1 %3, label %4, label %7

; <label>:4                                             		; preds = %0
  %5 = getelementptr inbounds [16 x i8], [16 x i8]* @.str5, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @printf(i8* %5)
  br label %27

; <label>:7                                             		; preds = %0
  %8 = getelementptr inbounds [12 x i8], [12 x i8]* @.str6, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @printf(i8* %8)
  %10 = load i32, i32* @front, align 4
  store i32 %10, i32* %i, align 4
  br label %11

; <label>:11    		; preds = %7, %21
  %12 = load i32, i32* %i, align 4
  %13 = load i32, i32* @rear, align 4
  %14 = icmp sle i32 %12, %13
  br i1 %14, label %15, label %24

; <label>:15                         		; preds = %11
  %16 = load i32, i32* %i, align 4
  %17 = getelementptr inbounds [50 x i32], [50 x i32]* @queue_arr, i32 0, i32 %16
  %18 = getelementptr inbounds [4 x i8], [4 x i8]* @.str7, i32 0, i32 0
  %19 = load i32, i32* %17, align 4
  %20 = call i32 (i8*, ...)* @printf(i8* %18, i32 %19)
  br label %21

; <label>:21     		; preds = %15
  %22 = load i32, i32* %i, align 4
  %23 = add i32 %22, 1
  store i32 %23, i32* %i, align 4
  br label %11

; <label>:24                                          		; preds = %11
  %25 = getelementptr inbounds [2 x i8], [2 x i8]* @.str8, i32 0, i32 0
  %26 = call i32 (i8*, ...)* @printf(i8* %25)
  br label %27

; <label>:27 		; preds = %4, %24
  %28 = load i32, i32* %1, align 4
  ret i32 %28
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %choice = alloca i32, align 4
  store i32 0, i32* %1, align 4
  br label %2

; <label>:2 		; preds = %0, %27
  br i1 true, label %3, label %28

; <label>:3                                                                                           		; preds = %2
  %4 = getelementptr inbounds [10 x i8], [10 x i8]* @.str9, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @printf(i8* %4)
  %6 = getelementptr inbounds [10 x i8], [10 x i8]* @.str10, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  %8 = getelementptr inbounds [11 x i8], [11 x i8]* @.str11, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @printf(i8* %8)
  %10 = getelementptr inbounds [8 x i8], [8 x i8]* @.str12, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @printf(i8* %10)
  %12 = getelementptr inbounds [21 x i8], [21 x i8]* @.str13, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @printf(i8* %12)
  %14 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %15 = call i32 (i8*, ...)* @scanf(i8* %14, i32* %choice)
  %16 = load i32, i32* %choice, align 4
  switch i32 %16, label %24 [
    i32 1, label %17
    i32 2, label %19
    i32 3, label %21
    i32 4, label %23 
  ]

; <label>:17		; preds = %3
  %18 = call i32 @insert()
  br label %27

; <label>:19		; preds = %3
  %20 = call i32 @del()
  br label %27

; <label>:21		; preds = %3
  %22 = call i32 @display()
  br label %27

; <label>:23     		; preds = %3
  call void @exit(i32 1) noreturn
  unreachable

; <label>:24                                              		; preds = %3
  %25 = getelementptr inbounds [14 x i8], [14 x i8]* @.str14, i32 0, i32 0
  %26 = call i32 (i8*, ...)* @printf(i8* %25)
  br label %27

; <label>:27		; preds = %17, %19, %21, %24
  br label %2

; <label>:28      		; preds = %2
  %29 = load i32, i32* %1, align 4
  ret i32 %29
}
