%struct.subject = type { i32, i32 }
%struct.student = type { i32, float, %struct.subject }

@phy = common global %struct.subject zeroinitializer, align 4
@chem = common global %struct.subject zeroinitializer, align 4
@student1 = common global %struct.student zeroinitializer, align 4
@student2 = common global %struct.student zeroinitializer, align 4

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %st = alloca %struct.student, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds %struct.student, %struct.student* @student1, i32 0, i32 0
  store i32 1, i32* %3, align 4
  %4 = getelementptr inbounds %struct.student, %struct.student* @student2, i32 0, i32 1
  store float 0x4056A00000000000, float* %4, align 4
  %5 = getelementptr inbounds %struct.student, %struct.student* @student2, i32 0, i32 2
  %6 = getelementptr inbounds %struct.subject, %struct.subject* %5, i32 0, i32 0
  store i32 100, i32* %6, align 4
  %7 = getelementptr inbounds %struct.student, %struct.student* @student2, i32 0, i32 2
  %8 = getelementptr inbounds %struct.subject, %struct.subject* %7, i32 0, i32 1
  store i32 99, i32* %8, align 4
  %9 = getelementptr inbounds %struct.subject, %struct.subject* @phy, i32 0, i32 0
  store i32 80, i32* %9, align 4
  %10 = getelementptr inbounds %struct.subject, %struct.subject* @chem, i32 0, i32 1
  store i32 98, i32* %10, align 4
  %11 = getelementptr inbounds %struct.student, %struct.student* @student1, i32 0, i32 0
  %12 = load i32, i32* %11, align 4
  store i32 %12, i32* %1, align 4
  ret i32 0
}
