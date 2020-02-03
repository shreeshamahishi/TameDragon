%union.un = type { [10 x i32] }
%struct.s = type { i32, float, double }

@uno = common global %union.un zeroinitializer, align 16

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast %union.un* @uno to %struct.s*
  %3 = getelementptr inbounds %struct.s, %struct.s* %2, i32 0, i32 0
  store i32 10, i32* %3, align 4
  %4 = load i32, i32* %1, align 4
  ret i32 %4
}
