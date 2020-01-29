%struct.student = type { i32, float }

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
  ret i32 0
}
