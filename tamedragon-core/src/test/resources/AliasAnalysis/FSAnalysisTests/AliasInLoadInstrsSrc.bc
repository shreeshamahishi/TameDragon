target datalayout = "e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

%struct.QuestionStruct = type { i32, i8*, [4 x i8*], i32 }
%struct.SectionStruct = type { i8*, [20 x %struct.QuestionStruct], i32 }
%struct.TestStruct = type { i8*, [3 x %struct.SectionStruct], i32 }

@glb1 = common global i32 0, align 4
@glb2 = common global i32 0, align 4
@ptrglb1 = common global i32* null, align 8
@ptrglb2 = common global i32* null, align 8
@glblTest1 = common global %struct.TestStruct zeroinitializer, align 8
@glblTest2 = common global %struct.TestStruct zeroinitializer, align 8
@.str = private unnamed_addr constant [3 x i8] c"GK\00", align 1
@.str1 = private unnamed_addr constant [10 x i8] c"Geography\00", align 1
@.str2 = private unnamed_addr constant [29 x i8] c"What is the capital of India\00", align 16
@.str3 = private unnamed_addr constant [6 x i8] c"Delhi\00", align 1
@.str4 = private unnamed_addr constant [10 x i8] c"Bangalore\00", align 1

define i32 @foo(%struct.TestStruct* %argTest1, %struct.TestStruct* %argTest2, i32 %m1, i32 %n1, i32 %x1, i32 %y1) nounwind {
  %localTest1 = alloca %struct.TestStruct, align 8
  %localTest2 = alloca %struct.TestStruct, align 8
  %localTest3 = alloca %struct.TestStruct, align 8
  %1 = getelementptr inbounds [3 x i8]* @.str, i32 0, i32 0
  %2 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 0
  store i8* %1, i8** %2, align 8
  %3 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 2
  store i32 60, i32* %3, align 4
  %4 = getelementptr inbounds [10 x i8]* @.str1, i32 0, i32 0
  %5 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %6 = getelementptr inbounds [3 x %struct.SectionStruct]* %5, i32 0, i32 0
  %7 = getelementptr inbounds %struct.SectionStruct* %6, i32 0, i32 0
  store i8* %4, i8** %7, align 8
  %8 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %9 = getelementptr inbounds [3 x %struct.SectionStruct]* %8, i32 0, i32 0
  %10 = getelementptr inbounds %struct.SectionStruct* %9, i32 0, i32 2
  store i32 10, i32* %10, align 4
  %11 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %12 = getelementptr inbounds [3 x %struct.SectionStruct]* %11, i32 0, i32 0
  %13 = getelementptr inbounds %struct.SectionStruct* %12, i32 0, i32 1
  %14 = getelementptr inbounds [20 x %struct.QuestionStruct]* %13, i32 0, i32 0
  %15 = getelementptr inbounds %struct.QuestionStruct* %14, i32 0, i32 0
  store i32 1, i32* %15, align 4
  %16 = getelementptr inbounds [29 x i8]* @.str2, i32 0, i32 0
  %17 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %18 = getelementptr inbounds [3 x %struct.SectionStruct]* %17, i32 0, i32 0
  %19 = getelementptr inbounds %struct.SectionStruct* %18, i32 0, i32 1
  %20 = getelementptr inbounds [20 x %struct.QuestionStruct]* %19, i32 0, i32 0
  %21 = getelementptr inbounds %struct.QuestionStruct* %20, i32 0, i32 1
  store i8* %16, i8** %21, align 8
  %22 = getelementptr inbounds [6 x i8]* @.str3, i32 0, i32 0
  %23 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %24 = getelementptr inbounds [3 x %struct.SectionStruct]* %23, i32 0, i32 0
  %25 = getelementptr inbounds %struct.SectionStruct* %24, i32 0, i32 1
  %26 = getelementptr inbounds [20 x %struct.QuestionStruct]* %25, i32 0, i32 0
  %27 = getelementptr inbounds %struct.QuestionStruct* %26, i32 0, i32 2
  %28 = getelementptr inbounds [4 x i8*]* %27, i32 0, i32 0
  store i8* %22, i8** %28, align 8
  %29 = getelementptr inbounds [10 x i8]* @.str4, i32 0, i32 0
  %30 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %31 = getelementptr inbounds [3 x %struct.SectionStruct]* %30, i32 0, i32 0
  %32 = getelementptr inbounds %struct.SectionStruct* %31, i32 0, i32 1
  %33 = getelementptr inbounds [20 x %struct.QuestionStruct]* %32, i32 0, i32 0
  %34 = getelementptr inbounds %struct.QuestionStruct* %33, i32 0, i32 2
  %35 = getelementptr inbounds [4 x i8*]* %34, i32 0, i32 1
  store i8* %29, i8** %35, align 8
  %36 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %37 = getelementptr inbounds [3 x %struct.SectionStruct]* %36, i32 0, i32 %m1
  %38 = getelementptr inbounds %struct.SectionStruct* %37, i32 0, i32 1
  %39 = getelementptr inbounds [20 x %struct.QuestionStruct]* %38, i32 0, i32 %n1
  %40 = getelementptr inbounds %struct.QuestionStruct* %39, i32 0, i32 0
  %41 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %42 = getelementptr inbounds [3 x %struct.SectionStruct]* %41, i32 0, i32 %m1
  %43 = getelementptr inbounds %struct.SectionStruct* %42, i32 0, i32 1
  %44 = getelementptr inbounds [20 x %struct.QuestionStruct]* %43, i32 0, i32 %n1
  %45 = getelementptr inbounds %struct.QuestionStruct* %44, i32 0, i32 0
  %46 = load i32* %40, align 4
  %47 = load i32* %45, align 4
  %48 = add i32 %46, %47
  %49 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %50 = getelementptr inbounds [3 x %struct.SectionStruct]* %49, i32 0, i32 %m1
  %51 = getelementptr inbounds %struct.SectionStruct* %50, i32 0, i32 1
  %52 = getelementptr inbounds [20 x %struct.QuestionStruct]* %51, i32 0, i32 %n1
  %53 = getelementptr inbounds %struct.QuestionStruct* %52, i32 0, i32 0
  %54 = load i32* %53, align 4
  %55 = add i32 %48, %54
  %56 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %57 = getelementptr inbounds [3 x %struct.SectionStruct]* %56, i32 0, i32 0
  %58 = getelementptr inbounds %struct.SectionStruct* %57, i32 0, i32 1
  %59 = getelementptr inbounds [20 x %struct.QuestionStruct]* %58, i32 0, i32 0
  %60 = getelementptr inbounds %struct.QuestionStruct* %59, i32 0, i32 2
  %61 = getelementptr inbounds [4 x i8*]* %60, i32 0, i32 0
  %62 = load i8** %61, align 8
  %63 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %64 = getelementptr inbounds [3 x %struct.SectionStruct]* %63, i32 0, i32 0
  %65 = getelementptr inbounds %struct.SectionStruct* %64, i32 0, i32 1
  %66 = getelementptr inbounds [20 x %struct.QuestionStruct]* %65, i32 0, i32 0
  %67 = getelementptr inbounds %struct.QuestionStruct* %66, i32 0, i32 2
  %68 = getelementptr inbounds [4 x i8*]* %67, i32 0, i32 1
  %69 = load i8** %68, align 8
  %70 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %71 = getelementptr inbounds [3 x %struct.SectionStruct]* %70, i32 0, i32 0
  %72 = getelementptr inbounds %struct.SectionStruct* %71, i32 0, i32 1
  %73 = getelementptr inbounds [20 x %struct.QuestionStruct]* %72, i32 0, i32 0
  %74 = getelementptr inbounds %struct.QuestionStruct* %73, i32 0, i32 2
  %75 = getelementptr inbounds [4 x i8*]* %74, i32 0, i32 1
  %76 = load i8** %75, align 8
  %77 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %78 = getelementptr inbounds [3 x %struct.SectionStruct]* %77, i32 0, i32 0
  %79 = getelementptr inbounds %struct.SectionStruct* %78, i32 0, i32 1
  %80 = getelementptr inbounds [20 x %struct.QuestionStruct]* %79, i32 0, i32 0
  %81 = getelementptr inbounds %struct.QuestionStruct* %80, i32 0, i32 2
  %82 = getelementptr inbounds [4 x i8*]* %81, i32 0, i32 1
  %83 = load i8** %82, align 8
  %84 = getelementptr inbounds %struct.TestStruct* %argTest1, i32 0
  %85 = getelementptr inbounds %struct.TestStruct* %84, i32 0, i32 1
  %86 = getelementptr inbounds [3 x %struct.SectionStruct]* %85, i32 0, i32 0
  %87 = getelementptr inbounds %struct.SectionStruct* %86, i32 0, i32 1
  %88 = getelementptr inbounds [20 x %struct.QuestionStruct]* %87, i32 0, i32 0
  %89 = getelementptr inbounds %struct.QuestionStruct* %88, i32 0, i32 2
  %90 = getelementptr inbounds [4 x i8*]* %89, i32 0, i32 1
  %91 = load i8** %90, align 8
  %92 = getelementptr inbounds %struct.TestStruct* %argTest1, i32 0
  %93 = getelementptr inbounds %struct.TestStruct* %92, i32 0, i32 1
  %94 = getelementptr inbounds [3 x %struct.SectionStruct]* %93, i32 0, i32 0
  %95 = getelementptr inbounds %struct.SectionStruct* %94, i32 0, i32 1
  %96 = getelementptr inbounds [20 x %struct.QuestionStruct]* %95, i32 0, i32 0
  %97 = getelementptr inbounds %struct.QuestionStruct* %96, i32 0, i32 2
  %98 = getelementptr inbounds [4 x i8*]* %97, i32 0, i32 2
  %99 = load i8** %98, align 8
  %100 = getelementptr inbounds %struct.TestStruct* %argTest2, i32 0
  %101 = getelementptr inbounds %struct.TestStruct* %100, i32 0, i32 1
  %102 = getelementptr inbounds [3 x %struct.SectionStruct]* %101, i32 0, i32 0
  %103 = getelementptr inbounds %struct.SectionStruct* %102, i32 0, i32 1
  %104 = getelementptr inbounds [20 x %struct.QuestionStruct]* %103, i32 0, i32 0
  %105 = getelementptr inbounds %struct.QuestionStruct* %104, i32 0, i32 2
  %106 = getelementptr inbounds [4 x i8*]* %105, i32 0, i32 1
  %107 = load i8** %106, align 8
  %108 = getelementptr inbounds %struct.TestStruct* %argTest2, i32 0
  %109 = getelementptr inbounds %struct.TestStruct* %108, i32 0, i32 1
  %110 = getelementptr inbounds [3 x %struct.SectionStruct]* %109, i32 0, i32 0
  %111 = getelementptr inbounds %struct.SectionStruct* %110, i32 0, i32 1
  %112 = getelementptr inbounds [20 x %struct.QuestionStruct]* %111, i32 0, i32 0
  %113 = getelementptr inbounds %struct.QuestionStruct* %112, i32 0, i32 2
  %114 = getelementptr inbounds [4 x i8*]* %113, i32 0, i32 2
  %115 = load i8** %114, align 8
  %116 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %117 = getelementptr inbounds [3 x %struct.SectionStruct]* %116, i32 0, i32 0
  %118 = getelementptr inbounds %struct.SectionStruct* %117, i32 0, i32 1
  %119 = getelementptr inbounds [20 x %struct.QuestionStruct]* %118, i32 0, i32 0
  %120 = getelementptr inbounds %struct.QuestionStruct* %119, i32 0, i32 2
  %121 = getelementptr inbounds [4 x i8*]* %120, i32 0, i32 1
  %122 = load i8** %121, align 8
  %123 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %124 = getelementptr inbounds [3 x %struct.SectionStruct]* %123, i32 0, i32 0
  %125 = getelementptr inbounds %struct.SectionStruct* %124, i32 0, i32 1
  %126 = getelementptr inbounds [20 x %struct.QuestionStruct]* %125, i32 0, i32 0
  %127 = getelementptr inbounds %struct.QuestionStruct* %126, i32 0, i32 2
  %128 = getelementptr inbounds [4 x i8*]* %127, i32 0, i32 2
  %129 = load i8** %128, align 8
  %130 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %131 = getelementptr inbounds [3 x %struct.SectionStruct]* %130, i32 0, i32 0
  %132 = getelementptr inbounds %struct.SectionStruct* %131, i32 0, i32 1
  %133 = getelementptr inbounds [20 x %struct.QuestionStruct]* %132, i32 0, i32 0
  %134 = getelementptr inbounds %struct.QuestionStruct* %133, i32 0, i32 2
  %135 = getelementptr inbounds [4 x i8*]* %134, i32 0, i32 1
  %136 = load i8** %135, align 8
  %137 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %138 = getelementptr inbounds [3 x %struct.SectionStruct]* %137, i32 0, i32 0
  %139 = getelementptr inbounds %struct.SectionStruct* %138, i32 0, i32 1
  %140 = getelementptr inbounds [20 x %struct.QuestionStruct]* %139, i32 0, i32 0
  %141 = getelementptr inbounds %struct.QuestionStruct* %140, i32 0, i32 2
  %142 = getelementptr inbounds [4 x i8*]* %141, i32 0, i32 2
  %143 = load i8** %142, align 8
  %144 = load i8* %62, align 1
  %145 = sext i8 %144 to i32
  %146 = icmp eq i32 %145, 0
  br i1 %146, label %147, label %157

; <label>:147        		; preds = %0
  %148 = load i32** @ptrglb2, align 8
  %149 = load i32** @ptrglb1, align 8
  %150 = load i32* @glb1, align 4
  %151 = load i32* @glb2, align 4
  %152 = add i32 %150, %151
  %153 = load i32* %149, align 4
  %154 = add i32 %152, %153
  %155 = load i32* %148, align 4
  %156 = add i32 %154, %155
  br label %157

; <label>:157 		; preds = %147, %0
  %158 = load i8* %69, align 1
  %159 = sext i8 %158 to i32
  %160 = icmp eq i32 %159, 0
  br i1 %160, label %161, label %162

; <label>:161		; preds = %157
  br label %162

; <label>:162		; preds = %161, %157
  %163 = load i8* %76, align 1
  %164 = sext i8 %163 to i32
  %165 = icmp eq i32 %164, 0
  br i1 %165, label %166, label %167

; <label>:166		; preds = %162
  br label %167

; <label>:167		; preds = %166, %162
  %168 = load i8* %83, align 1
  %169 = sext i8 %168 to i32
  %170 = icmp eq i32 %169, 0
  br i1 %170, label %171, label %172

; <label>:171		; preds = %167
  br label %172

; <label>:172		; preds = %171, %167
  %173 = load i8* %91, align 1
  %174 = sext i8 %173 to i32
  %175 = icmp eq i32 %174, 0
  br i1 %175, label %176, label %177

; <label>:176		; preds = %172
  br label %177

; <label>:177		; preds = %176, %172
  %178 = load i8* %99, align 1
  %179 = sext i8 %178 to i32
  %180 = icmp eq i32 %179, 0
  br i1 %180, label %181, label %182

; <label>:181		; preds = %177
  br label %182

; <label>:182		; preds = %181, %177
  %183 = load i8* %107, align 1
  %184 = sext i8 %183 to i32
  %185 = icmp eq i32 %184, 0
  br i1 %185, label %186, label %187

; <label>:186		; preds = %182
  br label %187

; <label>:187		; preds = %186, %182
  %188 = load i8* %115, align 1
  %189 = sext i8 %188 to i32
  %190 = icmp eq i32 %189, 0
  br i1 %190, label %191, label %192

; <label>:191		; preds = %187
  br label %192

; <label>:192		; preds = %191, %187
  %193 = load i8* %122, align 1
  %194 = sext i8 %193 to i32
  %195 = icmp eq i32 %194, 0
  br i1 %195, label %196, label %197

; <label>:196		; preds = %192
  br label %197

; <label>:197		; preds = %196, %192
  %198 = load i8* %129, align 1
  %199 = sext i8 %198 to i32
  %200 = icmp eq i32 %199, 0
  br i1 %200, label %201, label %202

; <label>:201		; preds = %197
  br label %202

; <label>:202		; preds = %201, %197
  %203 = load i8* %136, align 1
  %204 = sext i8 %203 to i32
  %205 = icmp eq i32 %204, 0
  br i1 %205, label %206, label %207

; <label>:206		; preds = %202
  br label %207

; <label>:207		; preds = %206, %202
  %208 = load i8* %143, align 1
  %209 = sext i8 %208 to i32
  %210 = icmp eq i32 %209, 0
  br i1 %210, label %211, label %212

; <label>:211		; preds = %207
  br label %212

; <label>:212		; preds = %211, %207
  ret i32 undef
}

