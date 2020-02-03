@.str = private unnamed_addr constant [2 x i8] c"1\00", align 1
@.str1 = private unnamed_addr constant [2 x i8] c"2\00", align 1
@.str2 = private unnamed_addr constant [2 x i8] c"3\00", align 1
@.str3 = private unnamed_addr constant [2 x i8] c"4\00", align 1
@str_array_all = global [2 x [2 x i8*]] [[2 x i8*] [i8* getelementptr inbounds [2 x i8], ([2 x i8]* @.str, i32 0, i32 0), i8* getelementptr inbounds [2 x i8], ([2 x i8]* @.str1, i32 0, i32 0)], [2 x i8*] [i8* getelementptr inbounds [2 x i8], ([2 x i8]* @.str2, i32 0, i32 0), i8* getelementptr inbounds [2 x i8], ([2 x i8]* @.str3, i32 0, i32 0)]], align 16
