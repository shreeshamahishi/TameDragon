%union.anon = type { i32, [96 x i8] }

@tch = common global %union.anon zeroinitializer, align 16

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds %union.anon, %union.anon* @tch, i32 0, i32 0
  store i32 1, i32* %2, align 4
  %3 = bitcast %union.anon* @tch to [10 x [10 x i8]]*
  %4 = getelementptr inbounds [10 x [10 x i8]], [10 x [10 x i8]]* %3, i32 0, i32 0, i32 0
  store i8 86, i8* %4, align 1
  ret i32 0
}
