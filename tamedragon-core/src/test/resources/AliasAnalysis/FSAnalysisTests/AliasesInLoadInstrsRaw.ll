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
  %1 = alloca %struct.TestStruct*, align 8
  %2 = alloca %struct.TestStruct*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %localTest1 = alloca %struct.TestStruct, align 8
  %localTest2 = alloca %struct.TestStruct, align 8
  %localTest3 = alloca %struct.TestStruct, align 8
  %localTestMarks = alloca i32, align 4
  %localTest1Opt1 = alloca i8*, align 8
  %localTest1Opt2 = alloca i8*, align 8
  %localTest2Opt1 = alloca i8*, align 8
  %localTest2Opt2 = alloca i8*, align 8
  %arg1Opt1 = alloca i8*, align 8
  %arg1Opt2 = alloca i8*, align 8
  %arg2Opt1 = alloca i8*, align 8
  %arg2Opt2 = alloca i8*, align 8
  %glblTest1Opt1 = alloca i8*, align 8
  %glblTest1Opt2 = alloca i8*, align 8
  %glblTest2Opt1 = alloca i8*, align 8
  %glblTest2Opt2 = alloca i8*, align 8
  %result = alloca i32, align 4
  store %struct.TestStruct* %argTest1, %struct.TestStruct** %1, align 8
  store %struct.TestStruct* %argTest2, %struct.TestStruct** %2, align 8
  store i32 %m1, i32* %3, align 4
  store i32 %n1, i32* %4, align 4
  store i32 %x1, i32* %5, align 4
  store i32 %y1, i32* %6, align 4
  %8 = getelementptr inbounds [3 x i8]* @.str, i32 0, i32 0
  %9 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 0
  store i8* %8, i8** %9, align 8
  %10 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 2
  store i32 60, i32* %10, align 4
  %11 = getelementptr inbounds [10 x i8]* @.str1, i32 0, i32 0
  %12 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %13 = getelementptr inbounds [3 x %struct.SectionStruct]* %12, i32 0, i32 0
  %14 = getelementptr inbounds %struct.SectionStruct* %13, i32 0, i32 0
  store i8* %11, i8** %14, align 8
  %15 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %16 = getelementptr inbounds [3 x %struct.SectionStruct]* %15, i32 0, i32 0
  %17 = getelementptr inbounds %struct.SectionStruct* %16, i32 0, i32 2
  store i32 10, i32* %17, align 4
  %18 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %19 = getelementptr inbounds [3 x %struct.SectionStruct]* %18, i32 0, i32 0
  %20 = getelementptr inbounds %struct.SectionStruct* %19, i32 0, i32 1
  %21 = getelementptr inbounds [20 x %struct.QuestionStruct]* %20, i32 0, i32 0
  %22 = getelementptr inbounds %struct.QuestionStruct* %21, i32 0, i32 0
  store i32 1, i32* %22, align 4
  %23 = getelementptr inbounds [29 x i8]* @.str2, i32 0, i32 0
  %24 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %25 = getelementptr inbounds [3 x %struct.SectionStruct]* %24, i32 0, i32 0
  %26 = getelementptr inbounds %struct.SectionStruct* %25, i32 0, i32 1
  %27 = getelementptr inbounds [20 x %struct.QuestionStruct]* %26, i32 0, i32 0
  %28 = getelementptr inbounds %struct.QuestionStruct* %27, i32 0, i32 1
  store i8* %23, i8** %28, align 8
  %29 = getelementptr inbounds [6 x i8]* @.str3, i32 0, i32 0
  %30 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %31 = getelementptr inbounds [3 x %struct.SectionStruct]* %30, i32 0, i32 0
  %32 = getelementptr inbounds %struct.SectionStruct* %31, i32 0, i32 1
  %33 = getelementptr inbounds [20 x %struct.QuestionStruct]* %32, i32 0, i32 0
  %34 = getelementptr inbounds %struct.QuestionStruct* %33, i32 0, i32 2
  %35 = getelementptr inbounds [4 x i8*]* %34, i32 0, i32 0
  store i8* %29, i8** %35, align 8
  %36 = getelementptr inbounds [10 x i8]* @.str4, i32 0, i32 0
  %37 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %38 = getelementptr inbounds [3 x %struct.SectionStruct]* %37, i32 0, i32 0
  %39 = getelementptr inbounds %struct.SectionStruct* %38, i32 0, i32 1
  %40 = getelementptr inbounds [20 x %struct.QuestionStruct]* %39, i32 0, i32 0
  %41 = getelementptr inbounds %struct.QuestionStruct* %40, i32 0, i32 2
  %42 = getelementptr inbounds [4 x i8*]* %41, i32 0, i32 1
  store i8* %36, i8** %42, align 8
  %43 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %44 = load i32* %3, align 4
  %45 = getelementptr inbounds [3 x %struct.SectionStruct]* %43, i32 0, i32 %44
  %46 = getelementptr inbounds %struct.SectionStruct* %45, i32 0, i32 1
  %47 = load i32* %4, align 4
  %48 = getelementptr inbounds [20 x %struct.QuestionStruct]* %46, i32 0, i32 %47
  %49 = getelementptr inbounds %struct.QuestionStruct* %48, i32 0, i32 0
  %50 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %51 = load i32* %3, align 4
  %52 = getelementptr inbounds [3 x %struct.SectionStruct]* %50, i32 0, i32 %51
  %53 = getelementptr inbounds %struct.SectionStruct* %52, i32 0, i32 1
  %54 = load i32* %4, align 4
  %55 = getelementptr inbounds [20 x %struct.QuestionStruct]* %53, i32 0, i32 %54
  %56 = getelementptr inbounds %struct.QuestionStruct* %55, i32 0, i32 0
  %57 = load i32* %49, align 4
  %58 = load i32* %56, align 4
  %59 = add i32 %57, %58
  %60 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %61 = load i32* %3, align 4
  %62 = getelementptr inbounds [3 x %struct.SectionStruct]* %60, i32 0, i32 %61
  %63 = getelementptr inbounds %struct.SectionStruct* %62, i32 0, i32 1
  %64 = load i32* %4, align 4
  %65 = getelementptr inbounds [20 x %struct.QuestionStruct]* %63, i32 0, i32 %64
  %66 = getelementptr inbounds %struct.QuestionStruct* %65, i32 0, i32 0
  %67 = load i32* %66, align 4
  %68 = add i32 %59, %67
  store i32 %68, i32* %localTestMarks, align 4
  %69 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %70 = getelementptr inbounds [3 x %struct.SectionStruct]* %69, i32 0, i32 0
  %71 = getelementptr inbounds %struct.SectionStruct* %70, i32 0, i32 1
  %72 = getelementptr inbounds [20 x %struct.QuestionStruct]* %71, i32 0, i32 0
  %73 = getelementptr inbounds %struct.QuestionStruct* %72, i32 0, i32 2
  %74 = getelementptr inbounds [4 x i8*]* %73, i32 0, i32 0
  %75 = load i8** %74, align 8
  store i8* %75, i8** %localTest1Opt1, align 8
  %76 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %77 = getelementptr inbounds [3 x %struct.SectionStruct]* %76, i32 0, i32 0
  %78 = getelementptr inbounds %struct.SectionStruct* %77, i32 0, i32 1
  %79 = getelementptr inbounds [20 x %struct.QuestionStruct]* %78, i32 0, i32 0
  %80 = getelementptr inbounds %struct.QuestionStruct* %79, i32 0, i32 2
  %81 = getelementptr inbounds [4 x i8*]* %80, i32 0, i32 1
  %82 = load i8** %81, align 8
  store i8* %82, i8** %localTest1Opt2, align 8
  %83 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %84 = getelementptr inbounds [3 x %struct.SectionStruct]* %83, i32 0, i32 0
  %85 = getelementptr inbounds %struct.SectionStruct* %84, i32 0, i32 1
  %86 = getelementptr inbounds [20 x %struct.QuestionStruct]* %85, i32 0, i32 0
  %87 = getelementptr inbounds %struct.QuestionStruct* %86, i32 0, i32 2
  %88 = getelementptr inbounds [4 x i8*]* %87, i32 0, i32 1
  %89 = load i8** %88, align 8
  store i8* %89, i8** %localTest2Opt1, align 8
  %90 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %91 = getelementptr inbounds [3 x %struct.SectionStruct]* %90, i32 0, i32 0
  %92 = getelementptr inbounds %struct.SectionStruct* %91, i32 0, i32 1
  %93 = getelementptr inbounds [20 x %struct.QuestionStruct]* %92, i32 0, i32 0
  %94 = getelementptr inbounds %struct.QuestionStruct* %93, i32 0, i32 2
  %95 = getelementptr inbounds [4 x i8*]* %94, i32 0, i32 1
  %96 = load i8** %95, align 8
  store i8* %96, i8** %localTest2Opt2, align 8
  %97 = load %struct.TestStruct** %1, align 8
  %98 = getelementptr inbounds %struct.TestStruct* %97, i32 0
  %99 = getelementptr inbounds %struct.TestStruct* %98, i32 0, i32 1
  %100 = getelementptr inbounds [3 x %struct.SectionStruct]* %99, i32 0, i32 0
  %101 = getelementptr inbounds %struct.SectionStruct* %100, i32 0, i32 1
  %102 = getelementptr inbounds [20 x %struct.QuestionStruct]* %101, i32 0, i32 0
  %103 = getelementptr inbounds %struct.QuestionStruct* %102, i32 0, i32 2
  %104 = getelementptr inbounds [4 x i8*]* %103, i32 0, i32 1
  %105 = load i8** %104, align 8
  store i8* %105, i8** %arg1Opt1, align 8
  %106 = load %struct.TestStruct** %1, align 8
  %107 = getelementptr inbounds %struct.TestStruct* %106, i32 0
  %108 = getelementptr inbounds %struct.TestStruct* %107, i32 0, i32 1
  %109 = getelementptr inbounds [3 x %struct.SectionStruct]* %108, i32 0, i32 0
  %110 = getelementptr inbounds %struct.SectionStruct* %109, i32 0, i32 1
  %111 = getelementptr inbounds [20 x %struct.QuestionStruct]* %110, i32 0, i32 0
  %112 = getelementptr inbounds %struct.QuestionStruct* %111, i32 0, i32 2
  %113 = getelementptr inbounds [4 x i8*]* %112, i32 0, i32 2
  %114 = load i8** %113, align 8
  store i8* %114, i8** %arg1Opt2, align 8
  %115 = load %struct.TestStruct** %2, align 8
  %116 = getelementptr inbounds %struct.TestStruct* %115, i32 0
  %117 = getelementptr inbounds %struct.TestStruct* %116, i32 0, i32 1
  %118 = getelementptr inbounds [3 x %struct.SectionStruct]* %117, i32 0, i32 0
  %119 = getelementptr inbounds %struct.SectionStruct* %118, i32 0, i32 1
  %120 = getelementptr inbounds [20 x %struct.QuestionStruct]* %119, i32 0, i32 0
  %121 = getelementptr inbounds %struct.QuestionStruct* %120, i32 0, i32 2
  %122 = getelementptr inbounds [4 x i8*]* %121, i32 0, i32 1
  %123 = load i8** %122, align 8
  store i8* %123, i8** %arg2Opt1, align 8
  %124 = load %struct.TestStruct** %2, align 8
  %125 = getelementptr inbounds %struct.TestStruct* %124, i32 0
  %126 = getelementptr inbounds %struct.TestStruct* %125, i32 0, i32 1
  %127 = getelementptr inbounds [3 x %struct.SectionStruct]* %126, i32 0, i32 0
  %128 = getelementptr inbounds %struct.SectionStruct* %127, i32 0, i32 1
  %129 = getelementptr inbounds [20 x %struct.QuestionStruct]* %128, i32 0, i32 0
  %130 = getelementptr inbounds %struct.QuestionStruct* %129, i32 0, i32 2
  %131 = getelementptr inbounds [4 x i8*]* %130, i32 0, i32 2
  %132 = load i8** %131, align 8
  store i8* %132, i8** %arg2Opt2, align 8
  %133 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %134 = getelementptr inbounds [3 x %struct.SectionStruct]* %133, i32 0, i32 0
  %135 = getelementptr inbounds %struct.SectionStruct* %134, i32 0, i32 1
  %136 = getelementptr inbounds [20 x %struct.QuestionStruct]* %135, i32 0, i32 0
  %137 = getelementptr inbounds %struct.QuestionStruct* %136, i32 0, i32 2
  %138 = getelementptr inbounds [4 x i8*]* %137, i32 0, i32 1
  %139 = load i8** %138, align 8
  store i8* %139, i8** %glblTest1Opt1, align 8
  %140 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %141 = getelementptr inbounds [3 x %struct.SectionStruct]* %140, i32 0, i32 0
  %142 = getelementptr inbounds %struct.SectionStruct* %141, i32 0, i32 1
  %143 = getelementptr inbounds [20 x %struct.QuestionStruct]* %142, i32 0, i32 0
  %144 = getelementptr inbounds %struct.QuestionStruct* %143, i32 0, i32 2
  %145 = getelementptr inbounds [4 x i8*]* %144, i32 0, i32 2
  %146 = load i8** %145, align 8
  store i8* %146, i8** %glblTest1Opt2, align 8
  %147 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %148 = getelementptr inbounds [3 x %struct.SectionStruct]* %147, i32 0, i32 0
  %149 = getelementptr inbounds %struct.SectionStruct* %148, i32 0, i32 1
  %150 = getelementptr inbounds [20 x %struct.QuestionStruct]* %149, i32 0, i32 0
  %151 = getelementptr inbounds %struct.QuestionStruct* %150, i32 0, i32 2
  %152 = getelementptr inbounds [4 x i8*]* %151, i32 0, i32 1
  %153 = load i8** %152, align 8
  store i8* %153, i8** %glblTest2Opt1, align 8
  %154 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %155 = getelementptr inbounds [3 x %struct.SectionStruct]* %154, i32 0, i32 0
  %156 = getelementptr inbounds %struct.SectionStruct* %155, i32 0, i32 1
  %157 = getelementptr inbounds [20 x %struct.QuestionStruct]* %156, i32 0, i32 0
  %158 = getelementptr inbounds %struct.QuestionStruct* %157, i32 0, i32 2
  %159 = getelementptr inbounds [4 x i8*]* %158, i32 0, i32 2
  %160 = load i8** %159, align 8
  store i8* %160, i8** %glblTest2Opt2, align 8
  store i32 0, i32* %result, align 4
  %161 = load i8** %localTest1Opt1, align 8
  %162 = load i8* %161, align 1
  %163 = sext i8 %162 to i32
  %164 = icmp eq i32 %163, 0
  br i1 %164, label %165, label %175

; <label>:165          		; preds = %0
  %166 = load i32** @ptrglb2, align 8
  %167 = load i32** @ptrglb1, align 8
  %168 = load i32* @glb1, align 4
  %169 = load i32* @glb2, align 4
  %170 = add i32 %168, %169
  %171 = load i32* %167, align 4
  %172 = add i32 %170, %171
  %173 = load i32* %166, align 4
  %174 = add i32 %172, %173
  store i32 %174, i32* %result, align 4
  br label %175

; <label>:175        		; preds = %165, %0
  %176 = load i8** %localTest1Opt2, align 8
  %177 = load i8* %176, align 1
  %178 = sext i8 %177 to i32
  %179 = icmp eq i32 %178, 0
  br i1 %179, label %180, label %181

; <label>:180      		; preds = %175
  store i32 20, i32* %result, align 4
  br label %181

; <label>:181      		; preds = %180, %175
  %182 = load i8** %localTest2Opt1, align 8
  %183 = load i8* %182, align 1
  %184 = sext i8 %183 to i32
  %185 = icmp eq i32 %184, 0
  br i1 %185, label %186, label %187

; <label>:186      		; preds = %181
  store i32 30, i32* %result, align 4
  br label %187

; <label>:187      		; preds = %186, %181
  %188 = load i8** %localTest2Opt2, align 8
  %189 = load i8* %188, align 1
  %190 = sext i8 %189 to i32
  %191 = icmp eq i32 %190, 0
  br i1 %191, label %192, label %193

; <label>:192      		; preds = %187
  store i32 40, i32* %result, align 4
  br label %193

; <label>:193		; preds = %192, %187
  %194 = load i8** %arg1Opt1, align 8
  %195 = load i8* %194, align 1
  %196 = sext i8 %195 to i32
  %197 = icmp eq i32 %196, 0
  br i1 %197, label %198, label %199

; <label>:198      		; preds = %193
  store i32 50, i32* %result, align 4
  br label %199

; <label>:199		; preds = %198, %193
  %200 = load i8** %arg1Opt2, align 8
  %201 = load i8* %200, align 1
  %202 = sext i8 %201 to i32
  %203 = icmp eq i32 %202, 0
  br i1 %203, label %204, label %205

; <label>:204      		; preds = %199
  store i32 60, i32* %result, align 4
  br label %205

; <label>:205		; preds = %204, %199
  %206 = load i8** %arg2Opt1, align 8
  %207 = load i8* %206, align 1
  %208 = sext i8 %207 to i32
  %209 = icmp eq i32 %208, 0
  br i1 %209, label %210, label %211

; <label>:210      		; preds = %205
  store i32 70, i32* %result, align 4
  br label %211

; <label>:211		; preds = %210, %205
  %212 = load i8** %arg2Opt2, align 8
  %213 = load i8* %212, align 1
  %214 = sext i8 %213 to i32
  %215 = icmp eq i32 %214, 0
  br i1 %215, label %216, label %217

; <label>:216      		; preds = %211
  store i32 80, i32* %result, align 4
  br label %217

; <label>:217     		; preds = %216, %211
  %218 = load i8** %glblTest1Opt1, align 8
  %219 = load i8* %218, align 1
  %220 = sext i8 %219 to i32
  %221 = icmp eq i32 %220, 0
  br i1 %221, label %222, label %223

; <label>:222      		; preds = %217
  store i32 90, i32* %result, align 4
  br label %223

; <label>:223     		; preds = %222, %217
  %224 = load i8** %glblTest1Opt2, align 8
  %225 = load i8* %224, align 1
  %226 = sext i8 %225 to i32
  %227 = icmp eq i32 %226, 0
  br i1 %227, label %228, label %229

; <label>:228       		; preds = %223
  store i32 100, i32* %result, align 4
  br label %229

; <label>:229     		; preds = %228, %223
  %230 = load i8** %glblTest2Opt1, align 8
  %231 = load i8* %230, align 1
  %232 = sext i8 %231 to i32
  %233 = icmp eq i32 %232, 0
  br i1 %233, label %234, label %235

; <label>:234       		; preds = %229
  store i32 110, i32* %result, align 4
  br label %235

; <label>:235     		; preds = %234, %229
  %236 = load i8** %glblTest2Opt2, align 8
  %237 = load i8* %236, align 1
  %238 = sext i8 %237 to i32
  %239 = icmp eq i32 %238, 0
  br i1 %239, label %240, label %241

; <label>:240       		; preds = %235
  store i32 120, i32* %result, align 4
  br label %241

; <label>:241		; preds = %240, %235
  %242 = load i32* %7, align 4
  ret i32 %242
}
