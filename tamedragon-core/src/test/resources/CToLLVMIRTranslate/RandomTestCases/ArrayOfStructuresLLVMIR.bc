%struct.xyz = type { i32, double, i8* }

@.str = private unnamed_addr constant [6 x i8] c"hello\00"
@.str1 = private unnamed_addr constant [6 x i8] c"there\00"
@.str2 = private unnamed_addr constant [10 x i8] c"earthling\00"
@.str3 = private unnamed_addr constant [5 x i8] c"take\00"
@struct_array_all = global [2 x [2 x %struct.xyz]] [[2 x %struct.xyz] [%struct.xyz { i32 1, double 2.000000e+00, i8* getelementptr inbounds ([6 x i8]* @.str, i32 0, i32 0) }, %struct.xyz { i32 3, double 4.000000e+00, i8* getelementptr inbounds ([6 x i8]* @.str1, i32 0, i32 0) }], [2 x %struct.xyz] [%struct.xyz { i32 5, double 6.000000e+00, i8* getelementptr inbounds ([10 x i8]* @.str2, i32 0, i32 0) }, %struct.xyz { i32 7, double 8.000000e+00, i8* getelementptr inbounds ([5 x i8]* @.str3, i32 0, i32 0) }]], align 16
