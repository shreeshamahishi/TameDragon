%struct.PersonStruct = type { i8*, i8*, i32, double }
%struct.CompanyStruct = type { i8*, i8*, %struct.PersonStruct* }

define i32 @foo(i32 %compIndex1, i32 %personIndex1, i32 %compIndex2, i32 %personIndex2, %struct.CompanyStruct* %companies) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca %struct.CompanyStruct*, align 8
  %elder = alloca i32, align 4
  store i32 %compIndex1, i32* %1, align 4
  store i32 %personIndex1, i32* %2, align 4
  store i32 %compIndex2, i32* %3, align 4
  store i32 %personIndex2, i32* %4, align 4
  store %struct.CompanyStruct* %companies, %struct.CompanyStruct** %5, align 8
  store i32 0, i32* %elder, align 4
  %6 = load %struct.CompanyStruct*, %struct.CompanyStruct** %5, align 8
  %7 = load i32, i32* %1, align 4
  %8 = getelementptr inbounds %struct.CompanyStruct, %struct.CompanyStruct* %6, i32 %7
  %9 = getelementptr inbounds %struct.CompanyStruct, %struct.CompanyStruct* %8, i32 0, i32 2
  %10 = load %struct.PersonStruct*, %struct.PersonStruct** %9, align 8
  %11 = load i32, i32* %2, align 4
  %12 = getelementptr inbounds %struct.PersonStruct, %struct.PersonStruct* %10, i32 %11
  %13 = getelementptr inbounds %struct.PersonStruct, %struct.PersonStruct* %12, i32 0, i32 2
  %14 = load %struct.CompanyStruct*, %struct.CompanyStruct** %5, align 8
  %15 = load i32, i32* %3, align 4
  %16 = getelementptr inbounds %struct.CompanyStruct, %struct.CompanyStruct* %14, i32 %15
  %17 = getelementptr inbounds %struct.CompanyStruct, %struct.CompanyStruct* %16, i32 0, i32 2
  %18 = load %struct.PersonStruct*, %struct.PersonStruct** %17, align 8
  %19 = load i32, i32* %4, align 4
  %20 = getelementptr inbounds %struct.PersonStruct, %struct.PersonStruct* %18, i32 %19
  %21 = getelementptr inbounds %struct.PersonStruct, %struct.PersonStruct* %20, i32 0, i32 2
  %22 = load i32, i32* %13, align 4
  %23 = load i32, i32* %21, align 4
  %24 = icmp sgt i32 %22, %23
  br i1 %24, label %25, label %26

; <label>:25       		; preds = %0
  store i32 1, i32* %elder, align 4
  br label %27

; <label>:26       		; preds = %0
  store i32 0, i32* %elder, align 4
  br label %27

; <label>:27    		; preds = %25, %26
  %28 = load i32, i32* %elder, align 4
  ret i32 %28
}
