%struct.QuestionStruct = type { i32, i8*, [4 x i8*], i32 }
%struct.SectionStruct = type { i8*, [20 x %struct.QuestionStruct], i32 }
%struct.TestStruct = type { i8*, [3 x %struct.SectionStruct], i32 }

@Question = common global %struct.QuestionStruct zeroinitializer, align 8

define i32 @foo(%struct.TestStruct* %tests, i32 %m, i32 %n, i32 %x, i32 %y, i32 %opIndex) nounwind {
  %1 = alloca %struct.TestStruct*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %marks1 = alloca i32, align 4
  %marks2 = alloca i32, align 4
  %option = alloca i8*, align 8
  %result = alloca i32, align 4
  store %struct.TestStruct* %tests, %struct.TestStruct** %1, align 8
  store i32 %m, i32* %2, align 4
  store i32 %n, i32* %3, align 4
  store i32 %x, i32* %4, align 4
  store i32 %y, i32* %5, align 4
  store i32 %opIndex, i32* %6, align 4
  %7 = load %struct.TestStruct*, %struct.TestStruct** %1, align 8
  %8 = getelementptr inbounds %struct.TestStruct, %struct.TestStruct* %7, i32 0
  %9 = getelementptr inbounds %struct.TestStruct, %struct.TestStruct* %8, i32 0, i32 1
  %10 = load i32, i32* %2, align 4
  %11 = getelementptr inbounds [3 x %struct.SectionStruct], [3 x %struct.SectionStruct]* %9, i32 0, i32 %10
  %12 = getelementptr inbounds %struct.SectionStruct, %struct.SectionStruct* %11, i32 0, i32 1
  %13 = load i32, i32* %3, align 4
  %14 = getelementptr inbounds [20 x %struct.QuestionStruct], [20 x %struct.QuestionStruct]* %12, i32 0, i32 %13
  %15 = getelementptr inbounds %struct.QuestionStruct, %struct.QuestionStruct* %14, i32 0, i32 0
  %16 = load i32, i32* %15, align 4
  store i32 %16, i32* %marks1, align 4
  %17 = load %struct.TestStruct*, %struct.TestStruct** %1, align 8
  %18 = getelementptr inbounds %struct.TestStruct, %struct.TestStruct* %17, i32 1
  %19 = getelementptr inbounds %struct.TestStruct, %struct.TestStruct* %18, i32 0, i32 1
  %20 = load i32, i32* %4, align 4
  %21 = getelementptr inbounds [3 x %struct.SectionStruct], [3 x %struct.SectionStruct]* %19, i32 0, i32 %20
  %22 = getelementptr inbounds %struct.SectionStruct, %struct.SectionStruct* %21, i32 0, i32 1
  %23 = load i32, i32* %5, align 4
  %24 = getelementptr inbounds [20 x %struct.QuestionStruct], [20 x %struct.QuestionStruct]* %22, i32 0, i32 %23
  %25 = getelementptr inbounds %struct.QuestionStruct, %struct.QuestionStruct* %24, i32 0, i32 0
  %26 = load i32, i32* %25, align 4
  store i32 %26, i32* %marks2, align 4
  %27 = load %struct.TestStruct*, %struct.TestStruct** %1, align 8
  %28 = getelementptr inbounds %struct.TestStruct, %struct.TestStruct* %27, i32 0
  %29 = getelementptr inbounds %struct.TestStruct, %struct.TestStruct* %28, i32 0, i32 1
  %30 = getelementptr inbounds [3 x %struct.SectionStruct], [3 x %struct.SectionStruct]* %29, i32 0, i32 1
  %31 = getelementptr inbounds %struct.SectionStruct, %struct.SectionStruct* %30, i32 0, i32 1
  %32 = getelementptr inbounds [20 x %struct.QuestionStruct], [20 x %struct.QuestionStruct]* %31, i32 0, i32 2
  %33 = getelementptr inbounds %struct.QuestionStruct, %struct.QuestionStruct* %32, i32 0, i32 2
  %34 = load i32, i32* %6, align 4
  %35 = getelementptr inbounds [4 x i8*], [4 x i8*]* %33, i32 0, i32 %34
  %36 = load i8*, i8** %35, align 8
  store i8* %36, i8** %option, align 8
  %37 = load i8*, i8** %option, align 8
  %38 = load i32, i32* %marks1, align 4
  %39 = load i32, i32* %marks2, align 4
  %40 = add i32 %38, %39
  %41 = getelementptr inbounds i8, i8* %37, i32 %40
  %42 = ptrtoint i8* %41 to i32
  store i32 %42, i32* %result, align 4
  %43 = load i32, i32* %result, align 4
  ret i32 %43
}
