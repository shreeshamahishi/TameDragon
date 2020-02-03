target datalayout="e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

%struct.addressStruct = type { i32, i8*, i8*, i8*, i8*, i8* }
%struct.contactDetails = type { %struct.addressStruct, i8* }
%struct.PersonAttr = type { i8*, i8*, double, %struct.contactDetails, %struct.contactDetails, i64 }

@refPerson = external global %struct.PersonAttr

; i32* %1, i32* %3 must alias
; i32* %1, i32* %5 do not alias
; i32* %1, i8** %9 do not alias
; i32* %1, i8** %14 do not alias
; i32* %1, i32* %19 may alias

; i32* %3, i32* %5 do not alias
; i32* %3, i8** %9 do not alias
; i32* %3, i8** %14 do not alias
; i32* %3, i32* %19 may alias

; i32* %5, i8** %9 do not alias
; i32* %5, i8** %14 do not alias
; i32* %5, i32* %19 may alias

; i8** %9, i8** %14 must alias
; i8** %9, i32* %19 may alias

; i8** %14, i32* %19 may alias

define i32 @foo(%struct.PersonAttr* %allPersons, i32 %m, i32 %n) nounwind {
  %1 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 0, i32 3, i32 0, i32 0
  %2 = load i32* %1, align 4
  %3 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 0, i32 3, i32 0, i32 0
  %4 = load i32* %3, align 4
  %5 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 1, i32 3, i32 0, i32 0
  %6 = load i32* %5, align 4
  %7 = icmp eq i32 %2, %6
  br i1 %7, label %8, label %11

; <label>:8                                        
  %9 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 0, i32 0
  %10 = load i8** %9, align 8
  br label %11

; <label>:11		
  %12 = icmp eq i32 %2, %4
  br i1 %12, label %13, label %17

; <label>:13                                        		; preds = %21
  %14 = getelementptr inbounds %struct.PersonAttr* %allPersons, i32 0, i32 0
  %15 = load i8** %14, align 8
  %16 = ptrtoint i8* %15 to i8
  br label %17

; <label>:17		
  %18 = add i32 %2, %4
  %19 = getelementptr inbounds %struct.PersonAttr* @refPerson, i32 0, i32 3, i32 0, i32 0
  store i32 %18, i32* %19, align 4
  ret i32 0
}