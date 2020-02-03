target datalayout="e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

%struct.QuestionStruct = type { i32, i8*, [4 x i8*], i32 }
%struct.SectionStruct = type { i8*, [20 x %struct.QuestionStruct], i32 }
%struct.TestStruct = type { i8*, [3 x %struct.SectionStruct], i32 }

define i32 @foo(%struct.TestStruct* %tests, i32 %m, i32 %n, i32 %x, i32 %y, i32 %opIndex) nounwind {
  %1 = getelementptr inbounds %struct.TestStruct* %tests, i32 0, i32 1, i32 %m, i32 1, i32 %n, i32 0
  %2 = load i32* %1, align 4
  %3 = getelementptr inbounds %struct.TestStruct* %tests, i32 1, i32 1, i32 %x, i32 1, i32 %y, i32 0
  %4 = load i32* %3, align 4
  %5 = getelementptr inbounds %struct.TestStruct* %tests, i32 0, i32 1, i32 1, i32 1, i32 2, i32 2, i32 %opIndex
  %6 = load i8** %5, align 8
  %7 = add i32 %2, %4
  %8 = getelementptr inbounds i8* %6, i32 %7
  %9 = load i8* %8, align 1
  %10 = sext i8 %9 to i32
  %11 = getelementptr inbounds %struct.TestStruct* %tests, i32 1, i32 1, i32 %x, i32 1, i32 %y, i32 0
  %12 = load i32* %11, align 4
  %13 = add i32 %10, %12
  ret i32 %13
}

