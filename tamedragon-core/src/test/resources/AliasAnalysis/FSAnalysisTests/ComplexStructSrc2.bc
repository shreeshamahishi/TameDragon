; ModuleID = 'D:\shreesha\work\CompilerVision\trunk\Core\TestData\AliasAnalysis\FSAnalysisTests\ComplexStructSrc2.bc'
target datalayout = "e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

%struct.PersonAttr = type { i8*, i8*, double, %struct.contactDetails, %struct.contactDetails, i64 }
%struct.contactDetails = type { %struct.addressStruct, i8* }
%struct.addressStruct = type { i32, i8*, i8*, i8*, i8*, i8* }

define i32 @foo(%struct.PersonAttr* %allPersons, i32 %m, i32 %n) nounwind {
  %1 = alloca %struct.PersonAttr*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %name1 = alloca i8*, align 8
  %name2 = alloca i8*, align 8
  %hn1 = alloca i32, align 4
  %hn2 = alloca i32, align 4
  %hn3 = alloca i32, align 4
  store %struct.PersonAttr* %allPersons, %struct.PersonAttr** %1, align 8
  store i32 %m, i32* %2, align 4
  store i32 %n, i32* %3, align 4
  %4 = load %struct.PersonAttr** %1, align 8
  %5 = getelementptr inbounds %struct.PersonAttr* %4, i32 0, i32 3, i32 0, i32 0
  %6 = load i32* %5, align 4
  store i32 %6, i32* %hn1, align 4
  %7 = load %struct.PersonAttr** %1, align 8
  %8 = getelementptr inbounds %struct.PersonAttr* %7, i32 0, i32 3, i32 0, i32 0
  %9 = load i32* %8, align 4
  store i32 %9, i32* %hn2, align 4
  %10 = load %struct.PersonAttr** %1, align 8
  %11 = getelementptr inbounds %struct.PersonAttr* %10, i32 1, i32 3, i32 0, i32 0
  %12 = load i32* %11, align 4
  store i32 %12, i32* %hn3, align 4
  %13 = load i32* %hn1, align 4
  %14 = load i32* %hn3, align 4
  %15 = icmp eq i32 %13, %14
  br i1 %15, label %16, label %20

; <label>:16                                      ; preds = %0
  %17 = load %struct.PersonAttr** %1, align 8
  %18 = getelementptr inbounds %struct.PersonAttr* %17, i32 0, i32 0
  %19 = load i8** %18, align 8
  store i8* %19, i8** %name1, align 8
  br label %20

; <label>:20                                      ; preds = %16, %0
  %21 = load i32* %hn1, align 4
  %22 = load i32* %hn2, align 4
  %23 = icmp eq i32 %21, %22
  br i1 %23, label %24, label %28

; <label>:24                                      ; preds = %20
  %25 = load %struct.PersonAttr** %1, align 8
  %26 = getelementptr inbounds %struct.PersonAttr* %25, i32 0, i32 0
  %27 = load i8** %26, align 8
  store i8* %27, i8** %name2, align 8
  br label %28

; <label>:28                                      ; preds = %24, %20
  ret i32 0
}
