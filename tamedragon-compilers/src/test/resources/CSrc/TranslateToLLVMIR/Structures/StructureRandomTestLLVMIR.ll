%struct.RT = type { i8, [10 x [20 x i32]], i8 }
%struct.ST = type { i32, double, %struct.RT }

define i32* @foo(%struct.ST* %s) nounwind {
  %1 = alloca %struct.ST*, align 8
  store %struct.ST* %s, %struct.ST** %1, align 8
  %2 = load %struct.ST*, %struct.ST** %1, align 8
  %3 = getelementptr inbounds %struct.ST, %struct.ST* %2, i32 1
  %4 = getelementptr inbounds %struct.ST, %struct.ST* %3, i32 0, i32 2
  %5 = getelementptr inbounds %struct.RT, %struct.RT* %4, i32 0, i32 1
  %6 = getelementptr inbounds [10 x [20 x i32]], [10 x [20 x i32]]* %5, i32 0, i32 5
  %7 = getelementptr inbounds [20 x i32], [20 x i32]* %6, i32 0, i32 13
  ret i32* %7
}
