@.str = private unnamed_addr constant [7 x i8] c"f = %f\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %f = alloca float, align 4
  store i32 0, i32* %1, align 4
  %2 = sitofp i32 18 to float
  store float %2, float* %f, align 4
  %3 = getelementptr inbounds [7 x i8], [7 x i8]* @.str, i32 0, i32 0
  %4 = load float, float* %f, align 4
  %5 = fpext float %4 to double
  %6 = call i32 (i8*, ...)* @printf(i8* %3, double %5)
  %7 = load i32, i32* %1, align 4
  ret i32 %7
}
