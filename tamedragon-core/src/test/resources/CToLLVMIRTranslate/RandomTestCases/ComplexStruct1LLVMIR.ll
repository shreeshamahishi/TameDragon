%struct.addressStruct = type { i32, i8*, i8*, i8*, i8*, i8* }
%struct.contactDetails = type { %struct.addressStruct, i8* }
%struct.PersonAttr = type { i8*, i8*, double, %struct.contactDetails, %struct.contactDetails, i64 }

define i32 @foo(%struct.PersonAttr* %allPersons, i32 %m, i32 %n) nounwind {
  %1 = alloca %struct.PersonAttr*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %name1 = alloca i8*, align 8
  %name2 = alloca i8, align 1
  %hn1 = alloca i32, align 4
  %hn2 = alloca i32, align 4
  %hn3 = alloca i32, align 4
  store %struct.PersonAttr* %allPersons, %struct.PersonAttr** %1, align 8
  store i32 %m, i32* %2, align 4
  store i32 %n, i32* %3, align 4
  %4 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %5 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %4, i32 0
  %6 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %5, i32 0, i32 3
  %7 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %6, i32 0, i32 0
  %8 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %7, i32 0, i32 0
  %9 = load i32, i32* %8, align 4
  store i32 %9, i32* %hn1, align 4
  %10 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %11 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %10, i32 0
  %12 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %11, i32 0, i32 3
  %13 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %12, i32 0, i32 0
  %14 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %13, i32 0, i32 0
  %15 = load i32, i32* %14, align 4
  store i32 %15, i32* %hn2, align 4
  %16 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %17 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %16, i32 1
  %18 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %17, i32 0, i32 3
  %19 = getelementptr inbounds %struct.contactDetails, %struct.contactDetails* %18, i32 0, i32 0
  %20 = getelementptr inbounds %struct.addressStruct, %struct.addressStruct* %19, i32 0, i32 0
  %21 = load i32, i32* %20, align 4
  store i32 %21, i32* %hn3, align 4
  %22 = load i32, i32* %hn1, align 4
  %23 = load i32, i32* %hn3, align 4
  %24 = icmp eq i32 %22, %23
  br i1 %24, label %25, label %30

; <label>:25                                                            		; preds = %0
  %26 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %27 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %26, i32 0
  %28 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %27, i32 0, i32 0
  %29 = load i8*, i8** %28, align 8
  store i8* %29, i8** %name1, align 8
  br label %30

; <label>:30   		; preds = %0, %25
  %31 = load i32, i32* %hn1, align 4
  %32 = load i32, i32* %hn2, align 4
  %33 = icmp eq i32 %31, %32
  br i1 %33, label %34, label %40

; <label>:34                                                           		; preds = %30
  %35 = load %struct.PersonAttr*, %struct.PersonAttr** %1, align 8
  %36 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %35, i32 0
  %37 = getelementptr inbounds %struct.PersonAttr, %struct.PersonAttr* %36, i32 0, i32 0
  %38 = load i8*, i8** %37, align 8
  %39 = ptrtoint i8* %38 to i8
  store i8 %39, i8* %name2, align 1
  br label %40

; <label>:40		; preds = %30, %34
  ret i32 0
}
