%struct.s = type { i8*, i8*, double, double }
%union.techno = type { %struct.s }

@tch = common global %union.techno zeroinitializer, align 8
@.str = private unnamed_addr constant [13 x i8] c"Vikash Joshi\00", align 1
@.str1 = private unnamed_addr constant [11 x i8] c"0502270017\00", align 1

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast %union.techno* @tch to i32*
  store i32 1, i32* %2, align 4
  %3 = getelementptr inbounds [13 x i8], [13 x i8]* @.str, i32 0, i32 0
  %4 = getelementptr inbounds %union.techno, %union.techno* @tch, i32 0, i32 0, i32 0
  store i8* %3, i8** %4, align 8
  %5 = getelementptr inbounds [11 x i8], [11 x i8]* @.str1, i32 0, i32 0
  %6 = getelementptr inbounds %union.techno, %union.techno* @tch, i32 0, i32 0, i32 1
  store i8* %5, i8** %6, align 8
  %7 = getelementptr inbounds %union.techno, %union.techno* @tch, i32 0, i32 0, i32 2
  store double 5.620000e-04, double* %7, align 8
  %8 = getelementptr inbounds %union.techno, %union.techno* @tch, i32 0, i32 0, i32 3
  store double 7.620000e+01, double* %8, align 8
  ret i32 0
}
