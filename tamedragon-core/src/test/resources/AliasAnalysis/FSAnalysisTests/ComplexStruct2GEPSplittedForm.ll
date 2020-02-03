%struct.PersonAttr = type { i8*, i8*, double, %struct.contactDetails, %struct.contactDetails, i32 }
%struct.addressStruct = type { i32, i8*, i8*, i8*, i8*, i8* }
%struct.contactDetails = type { %struct.addressStruct, i8* }

@m = common global i32 0, align 4
@n = common global i32 0, align 4

define i32 @foo(%struct.PersonAttr* %allPersons) nounwind {
  %1 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 0
  %2 = getelementptr inbounds %struct.PersonAttr* %1, i32 0, i32 3
  %3 = getelementptr inbounds %struct.contactDetails* %2, i32 0, i32 0
  %4 = getelementptr inbounds %struct.addressStruct* %3, i32 0, i32 0
  %5 = load i32* %4, align 4
  %6 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 1
  %7 = getelementptr inbounds %struct.PersonAttr* %6, i32 0, i32 3
  %8 = getelementptr inbounds %struct.contactDetails* %7, i32 0, i32 0
  %9 = getelementptr inbounds %struct.addressStruct* %8, i32 0, i32 0
  %10 = load i32* %9, align 4
  %11 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 2
  %12 = getelementptr inbounds %struct.PersonAttr* %11, i32 0, i32 3
  %13 = getelementptr inbounds %struct.contactDetails* %12, i32 0, i32 0
  %14 = getelementptr inbounds %struct.addressStruct* %13, i32 0, i32 0
  %15 = load i32* %14, align 4
  %16 = icmp eq i32 %5, %15
  br i1 %16, label %17, label %21

; <label>:17                                      ; preds = %0
  %18 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 0
  %19 = getelementptr inbounds %struct.PersonAttr* %18, i32 0, i32 0
  %20 = load i8** %19, align 4
  br label %21

; <label>:21                                      ; preds = %17, %0
  %22 = icmp eq i32 %5, %10
  br i1 %22, label %23, label %28

; <label>:23                                      ; preds = %21
  %24 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 0
  %25 = getelementptr inbounds %struct.PersonAttr* %24, i32 0, i32 0
  %26 = load i8** %25, align 4
  %27 = ptrtoint i8* %26 to i8
  br label %28

; <label>:28                                      ; preds = %23, %21
  %29 = load i32* @m, align 4
  %30 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 %29
  %31 = getelementptr inbounds %struct.PersonAttr* %30, i32 0, i32 3
  %32 = getelementptr inbounds %struct.contactDetails* %31, i32 0, i32 0
  %33 = getelementptr inbounds %struct.addressStruct* %32, i32 0, i32 0
  %34 = load i32* %33, align 4
  %35 = load i32* @m, align 4
  %36 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 %35
  %37 = getelementptr inbounds %struct.PersonAttr* %36, i32 0, i32 3
  %38 = getelementptr inbounds %struct.contactDetails* %37, i32 0, i32 0
  %39 = getelementptr inbounds %struct.addressStruct* %38, i32 0, i32 0
  %40 = load i32* %39, align 4
  %41 = load i32* @n, align 4
  %42 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 %41
  %43 = getelementptr inbounds %struct.PersonAttr* %42, i32 0, i32 3
  %44 = getelementptr inbounds %struct.contactDetails* %43, i32 0, i32 0
  %45 = getelementptr inbounds %struct.addressStruct* %44, i32 0, i32 0
  %46 = load i32* %45, align 4
  %47 = icmp eq i32 %34, %40
  br i1 %47, label %48, label %53

; <label>:48                                      ; preds = %28
  %49 = load i32* @m, align 4
  %50 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 %49
  %51 = getelementptr inbounds %struct.PersonAttr* %50, i32 0, i32 1
  %52 = load i8** %51, align 4
  br label %53

; <label>:53                                      ; preds = %48, %28
  %54 = icmp eq i32 %34, %46
  br i1 %54, label %55, label %61

; <label>:55                                      ; preds = %53
  %56 = load i32* @n, align 4
  %57 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 %56
  %58 = getelementptr inbounds %struct.PersonAttr* %57, i32 0, i32 1
  %59 = load i8** %58, align 4
  %60 = ptrtoint i8* %59 to i8
  br label %61

; <label>:61                                      ; preds = %55, %53
  ret i32 0
}
