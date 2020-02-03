; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Core\TestData\AliasAnalysis\FSAnalysisTests\AliasWithLoadStoresSrc1.bc'
target datalayout = "e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

%struct.TestStruct = type { i8*, [3 x %struct.SectionStruct], i32 }
%struct.SectionStruct = type { i8*, [20 x %struct.QuestionStruct], i32 }
%struct.QuestionStruct = type { i32, i8*, [4 x i8*], i32 }

@glb1 = common global i32 0, align 4
@glb2 = common global i32 0, align 4
@ptrglb1 = common global i32* null, align 8
@ptrglb2 = common global i32* null, align 8
@glblTest1 = common global %struct.TestStruct zeroinitializer, align 8
@glblTest2 = common global %struct.TestStruct zeroinitializer, align 8
@.str = private unnamed_addr constant [29 x i8] c"What is the capital of India\00", align 16
@.str1 = private unnamed_addr constant [6 x i8] c"Delhi\00", align 1
@.str2 = private unnamed_addr constant [10 x i8] c"Bangalore\00", align 1
@.str3 = private unnamed_addr constant [36 x i8] c"Which of these rivers flows in USA?\00", align 16
@.str4 = private unnamed_addr constant [6 x i8] c"Ganga\00", align 1
@.str5 = private unnamed_addr constant [12 x i8] c"Mississippi\00", align 1

define i32 @foo(%struct.TestStruct* %argTest1) nounwind {
  %localTest1 = alloca %struct.TestStruct, align 8
  %localTest2 = alloca %struct.TestStruct, align 8
  %1 = getelementptr inbounds [29 x i8]* @.str, i32 0, i32 0
  %2 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %3 = getelementptr inbounds [3 x %struct.SectionStruct]* %2, i32 0, i32 0
  %4 = getelementptr inbounds %struct.SectionStruct* %3, i32 0, i32 1
  %5 = getelementptr inbounds [20 x %struct.QuestionStruct]* %4, i32 0, i32 0
  %6 = getelementptr inbounds %struct.QuestionStruct* %5, i32 0, i32 1
  store i8* %1, i8** %6, align 8
  %7 = getelementptr inbounds [6 x i8]* @.str1, i32 0, i32 0
  %8 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %9 = getelementptr inbounds [3 x %struct.SectionStruct]* %8, i32 0, i32 0
  %10 = getelementptr inbounds %struct.SectionStruct* %9, i32 0, i32 1
  %11 = getelementptr inbounds [20 x %struct.QuestionStruct]* %10, i32 0, i32 0
  %12 = getelementptr inbounds %struct.QuestionStruct* %11, i32 0, i32 2
  %13 = getelementptr inbounds [4 x i8*]* %12, i32 0, i32 0
  store i8* %7, i8** %13, align 8
  %14 = getelementptr inbounds [10 x i8]* @.str2, i32 0, i32 0
  %15 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %16 = getelementptr inbounds [3 x %struct.SectionStruct]* %15, i32 0, i32 0
  %17 = getelementptr inbounds %struct.SectionStruct* %16, i32 0, i32 1
  %18 = getelementptr inbounds [20 x %struct.QuestionStruct]* %17, i32 0, i32 0
  %19 = getelementptr inbounds %struct.QuestionStruct* %18, i32 0, i32 2
  %20 = getelementptr inbounds [4 x i8*]* %19, i32 0, i32 1
  store i8* %14, i8** %20, align 8
  %21 = getelementptr inbounds [36 x i8]* @.str3, i32 0, i32 0
  %22 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %23 = getelementptr inbounds [3 x %struct.SectionStruct]* %22, i32 0, i32 0
  %24 = getelementptr inbounds %struct.SectionStruct* %23, i32 0, i32 1
  %25 = getelementptr inbounds [20 x %struct.QuestionStruct]* %24, i32 0, i32 0
  %26 = getelementptr inbounds %struct.QuestionStruct* %25, i32 0, i32 1
  store i8* %21, i8** %26, align 8
  %27 = getelementptr inbounds [6 x i8]* @.str4, i32 0, i32 0
  %28 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %29 = getelementptr inbounds [3 x %struct.SectionStruct]* %28, i32 0, i32 0
  %30 = getelementptr inbounds %struct.SectionStruct* %29, i32 0, i32 1
  %31 = getelementptr inbounds [20 x %struct.QuestionStruct]* %30, i32 0, i32 0
  %32 = getelementptr inbounds %struct.QuestionStruct* %31, i32 0, i32 2
  %33 = getelementptr inbounds [4 x i8*]* %32, i32 0, i32 0
  store i8* %27, i8** %33, align 8
  %34 = getelementptr inbounds [12 x i8]* @.str5, i32 0, i32 0
  %35 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %36 = getelementptr inbounds [3 x %struct.SectionStruct]* %35, i32 0, i32 0
  %37 = getelementptr inbounds %struct.SectionStruct* %36, i32 0, i32 1
  %38 = getelementptr inbounds [20 x %struct.QuestionStruct]* %37, i32 0, i32 0
  %39 = getelementptr inbounds %struct.QuestionStruct* %38, i32 0, i32 2
  %40 = getelementptr inbounds [4 x i8*]* %39, i32 0, i32 1
  store i8* %34, i8** %40, align 8
  %41 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %42 = getelementptr inbounds [3 x %struct.SectionStruct]* %41, i32 0, i32 0
  %43 = getelementptr inbounds %struct.SectionStruct* %42, i32 0, i32 1
  %44 = getelementptr inbounds [20 x %struct.QuestionStruct]* %43, i32 0, i32 0
  %45 = getelementptr inbounds %struct.QuestionStruct* %44, i32 0, i32 2
  %46 = getelementptr inbounds [4 x i8*]* %45, i32 0, i32 1
  %47 = load i8** %46, align 8
  %48 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %49 = getelementptr inbounds [3 x %struct.SectionStruct]* %48, i32 0, i32 0
  %50 = getelementptr inbounds %struct.SectionStruct* %49, i32 0, i32 1
  %51 = getelementptr inbounds [20 x %struct.QuestionStruct]* %50, i32 0, i32 0
  %52 = getelementptr inbounds %struct.QuestionStruct* %51, i32 0, i32 2
  %53 = getelementptr inbounds [4 x i8*]* %52, i32 0, i32 1
  %54 = load i8** %53, align 8
  %55 = getelementptr inbounds %struct.TestStruct* %argTest1, i32 0
  %56 = getelementptr inbounds %struct.TestStruct* %55, i32 0, i32 1
  %57 = getelementptr inbounds [3 x %struct.SectionStruct]* %56, i32 0, i32 0
  %58 = getelementptr inbounds %struct.SectionStruct* %57, i32 0, i32 1
  %59 = getelementptr inbounds [20 x %struct.QuestionStruct]* %58, i32 0, i32 0
  %60 = getelementptr inbounds %struct.QuestionStruct* %59, i32 0, i32 2
  %61 = getelementptr inbounds [4 x i8*]* %60, i32 0, i32 1
  %62 = load i8** %61, align 8
  %63 = load i8* %47, align 1
  %64 = load i8* %54, align 1
  %65 = icmp eq i8 %63, %64
  br i1 %65, label %66, label %67

; <label>:66                                      ; preds = %0
  br label %67

; <label>:67                                      ; preds = %66, %0
  %result.0 = phi i32 [ 1, %66 ], [ 0, %0 ]
  %68 = load i8* %47, align 1
  %69 = load i8* %62, align 1
  %70 = icmp eq i8 %68, %69
  br i1 %70, label %71, label %72

; <label>:71                                      ; preds = %67
  br label %72

; <label>:72                                      ; preds = %71, %67
  %result.1 = phi i32 [ 1, %71 ], [ %result.0, %67 ]
  ret i32 %result.1
}
