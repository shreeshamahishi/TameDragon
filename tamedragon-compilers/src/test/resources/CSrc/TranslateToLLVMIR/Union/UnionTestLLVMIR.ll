%union.techno = type { i32 }

@tch = common global %union.techno zeroinitializer, align 4
@.str = private unnamed_addr constant [13 x i8] c"Vikash Joshi\00", align 1

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds %union.techno, %union.techno* @tch, i32 0, i32 0
  store i32 1, i32* %2, align 4
  %3 = getelementptr inbounds [13 x i8], [13 x i8]* @.str, i32 0, i32 0
  %4 = bitcast %union.techno* @tch to i8**
  store i8* %3, i8** %4, align 8
  ret i32 0
}
