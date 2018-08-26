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
  %36 = getelementptr inbounds [10 x i8]* @.str4, i32 0, i32 0
  %37 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %38 = getelementptr inbounds [3 x %struct.SectionStruct]* %37, i32 0, i32 0
  %39 = getelementptr inbounds %struct.SectionStruct* %38, i32 0, i32 1
  %40 = getelementptr inbounds [20 x %struct.QuestionStruct]* %39, i32 0, i32 0
  %41 = getelementptr inbounds %struct.QuestionStruct* %40, i32 0, i32 2
  %42 = getelementptr inbounds [4 x i8*]* %41, i32 0, i32 2
  store i8* %36, i8** %42, align 8
  %43 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %44 = getelementptr inbounds [3 x %struct.SectionStruct]* %43, i32 0, i32 %m1
  %45 = getelementptr inbounds %struct.SectionStruct* %44, i32 0, i32 1
  %46 = getelementptr inbounds [20 x %struct.QuestionStruct]* %45, i32 0, i32 %n1
  %47 = getelementptr inbounds %struct.QuestionStruct* %46, i32 0, i32 0
  %48 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %49 = getelementptr inbounds [3 x %struct.SectionStruct]* %48, i32 0, i32 %m1
  %50 = getelementptr inbounds %struct.SectionStruct* %49, i32 0, i32 1
  %51 = getelementptr inbounds [20 x %struct.QuestionStruct]* %50, i32 0, i32 %n1
  %52 = getelementptr inbounds %struct.QuestionStruct* %51, i32 0, i32 0
  %53 = load i32* %47, align 4
  %54 = load i32* %52, align 4
  %55 = add i32 %53, %54
  %56 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %57 = getelementptr inbounds [3 x %struct.SectionStruct]* %56, i32 0, i32 %m1
  %58 = getelementptr inbounds %struct.SectionStruct* %57, i32 0, i32 1
  %59 = getelementptr inbounds [20 x %struct.QuestionStruct]* %58, i32 0, i32 %n1
  %60 = getelementptr inbounds %struct.QuestionStruct* %59, i32 0, i32 0
  %61 = load i32* %60, align 4
  %62 = add i32 %55, %61
  %63 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %64 = getelementptr inbounds [3 x %struct.SectionStruct]* %63, i32 0, i32 %x1
  %65 = getelementptr inbounds %struct.SectionStruct* %64, i32 0, i32 1
  %66 = getelementptr inbounds [20 x %struct.QuestionStruct]* %65, i32 0, i32 %y1
  %67 = getelementptr inbounds %struct.QuestionStruct* %66, i32 0, i32 0
  %68 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %69 = getelementptr inbounds [3 x %struct.SectionStruct]* %68, i32 0, i32 %x1
  %70 = getelementptr inbounds %struct.SectionStruct* %69, i32 0, i32 1
  %71 = getelementptr inbounds [20 x %struct.QuestionStruct]* %70, i32 0, i32 %y1
  %72 = getelementptr inbounds %struct.QuestionStruct* %71, i32 0, i32 0
  %73 = load i32* %67, align 4
  %74 = load i32* %72, align 4
  %75 = add i32 %73, %74
  %76 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %77 = getelementptr inbounds [3 x %struct.SectionStruct]* %76, i32 0, i32 %x1
  %78 = getelementptr inbounds %struct.SectionStruct* %77, i32 0, i32 1
  %79 = getelementptr inbounds [20 x %struct.QuestionStruct]* %78, i32 0, i32 %y1
  %80 = getelementptr inbounds %struct.QuestionStruct* %79, i32 0, i32 0
  %81 = load i32* %80, align 4
  %82 = add i32 %75, %81
  %83 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %84 = getelementptr inbounds [3 x %struct.SectionStruct]* %83, i32 0, i32 0
  %85 = getelementptr inbounds %struct.SectionStruct* %84, i32 0, i32 1
  %86 = getelementptr inbounds [20 x %struct.QuestionStruct]* %85, i32 0, i32 0
  %87 = getelementptr inbounds %struct.QuestionStruct* %86, i32 0, i32 2
  %88 = getelementptr inbounds [4 x i8*]* %87, i32 0, i32 1
  %89 = load i8** %88, align 8
  %90 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %91 = getelementptr inbounds [3 x %struct.SectionStruct]* %90, i32 0, i32 0
  %92 = getelementptr inbounds %struct.SectionStruct* %91, i32 0, i32 1
  %93 = getelementptr inbounds [20 x %struct.QuestionStruct]* %92, i32 0, i32 0
  %94 = getelementptr inbounds %struct.QuestionStruct* %93, i32 0, i32 2
  %95 = getelementptr inbounds [4 x i8*]* %94, i32 0, i32 2
  %96 = load i8** %95, align 8
  %97 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %98 = getelementptr inbounds [3 x %struct.SectionStruct]* %97, i32 0, i32 0
  %99 = getelementptr inbounds %struct.SectionStruct* %98, i32 0, i32 1
  %100 = getelementptr inbounds [20 x %struct.QuestionStruct]* %99, i32 0, i32 0
  %101 = getelementptr inbounds %struct.QuestionStruct* %100, i32 0, i32 2
  %102 = getelementptr inbounds [4 x i8*]* %101, i32 0, i32 1
  %103 = load i8** %102, align 8
  %104 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %105 = getelementptr inbounds [3 x %struct.SectionStruct]* %104, i32 0, i32 0
  %106 = getelementptr inbounds %struct.SectionStruct* %105, i32 0, i32 1
  %107 = getelementptr inbounds [20 x %struct.QuestionStruct]* %106, i32 0, i32 0
  %108 = getelementptr inbounds %struct.QuestionStruct* %107, i32 0, i32 2
  %109 = getelementptr inbounds [4 x i8*]* %108, i32 0, i32 2
  %110 = load i8** %109, align 8
  %111 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %112 = getelementptr inbounds [3 x %struct.SectionStruct]* %111, i32 0, i32 0
  %113 = getelementptr inbounds %struct.SectionStruct* %112, i32 0, i32 1
  %114 = getelementptr inbounds [20 x %struct.QuestionStruct]* %113, i32 0, i32 0
  %115 = getelementptr inbounds %struct.QuestionStruct* %114, i32 0, i32 2
  %116 = getelementptr inbounds [4 x i8*]* %115, i32 0, i32 1
  %117 = load i8** %116, align 8
  %118 = getelementptr inbounds %struct.TestStruct* %argTest1, i32 0
  %119 = getelementptr inbounds %struct.TestStruct* %118, i32 0, i32 1
  %120 = getelementptr inbounds [3 x %struct.SectionStruct]* %119, i32 0, i32 0
  %121 = getelementptr inbounds %struct.SectionStruct* %120, i32 0, i32 1
  %122 = getelementptr inbounds [20 x %struct.QuestionStruct]* %121, i32 0, i32 0
  %123 = getelementptr inbounds %struct.QuestionStruct* %122, i32 0, i32 2
  %124 = getelementptr inbounds [4 x i8*]* %123, i32 0, i32 1
  %125 = load i8** %124, align 8
  %126 = getelementptr inbounds %struct.TestStruct* %argTest1, i32 0
  %127 = getelementptr inbounds %struct.TestStruct* %126, i32 0, i32 1
  %128 = getelementptr inbounds [3 x %struct.SectionStruct]* %127, i32 0, i32 0
  %129 = getelementptr inbounds %struct.SectionStruct* %128, i32 0, i32 1
  %130 = getelementptr inbounds [20 x %struct.QuestionStruct]* %129, i32 0, i32 0
  %131 = getelementptr inbounds %struct.QuestionStruct* %130, i32 0, i32 2
  %132 = getelementptr inbounds [4 x i8*]* %131, i32 0, i32 2
  %133 = load i8** %132, align 8
  %134 = getelementptr inbounds %struct.TestStruct* %argTest2, i32 0
  %135 = getelementptr inbounds %struct.TestStruct* %134, i32 0, i32 1
  %136 = getelementptr inbounds [3 x %struct.SectionStruct]* %135, i32 0, i32 0
  %137 = getelementptr inbounds %struct.SectionStruct* %136, i32 0, i32 1
  %138 = getelementptr inbounds [20 x %struct.QuestionStruct]* %137, i32 0, i32 0
  %139 = getelementptr inbounds %struct.QuestionStruct* %138, i32 0, i32 2
  %140 = getelementptr inbounds [4 x i8*]* %139, i32 0, i32 1
  %141 = load i8** %140, align 8
  %142 = getelementptr inbounds %struct.TestStruct* %argTest2, i32 0
  %143 = getelementptr inbounds %struct.TestStruct* %142, i32 0, i32 1
  %144 = getelementptr inbounds [3 x %struct.SectionStruct]* %143, i32 0, i32 0
  %145 = getelementptr inbounds %struct.SectionStruct* %144, i32 0, i32 1
  %146 = getelementptr inbounds [20 x %struct.QuestionStruct]* %145, i32 0, i32 0
  %147 = getelementptr inbounds %struct.QuestionStruct* %146, i32 0, i32 2
  %148 = getelementptr inbounds [4 x i8*]* %147, i32 0, i32 2
  %149 = load i8** %148, align 8
  %150 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %151 = getelementptr inbounds [3 x %struct.SectionStruct]* %150, i32 0, i32 0
  %152 = getelementptr inbounds %struct.SectionStruct* %151, i32 0, i32 1
  %153 = getelementptr inbounds [20 x %struct.QuestionStruct]* %152, i32 0, i32 0
  %154 = getelementptr inbounds %struct.QuestionStruct* %153, i32 0, i32 2
  %155 = getelementptr inbounds [4 x i8*]* %154, i32 0, i32 1
  %156 = load i8** %155, align 8
  %157 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %158 = getelementptr inbounds [3 x %struct.SectionStruct]* %157, i32 0, i32 0
  %159 = getelementptr inbounds %struct.SectionStruct* %158, i32 0, i32 1
  %160 = getelementptr inbounds [20 x %struct.QuestionStruct]* %159, i32 0, i32 0
  %161 = getelementptr inbounds %struct.QuestionStruct* %160, i32 0, i32 2
  %162 = getelementptr inbounds [4 x i8*]* %161, i32 0, i32 2
  %163 = load i8** %162, align 8
  %164 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %165 = getelementptr inbounds [3 x %struct.SectionStruct]* %164, i32 0, i32 0
  %166 = getelementptr inbounds %struct.SectionStruct* %165, i32 0, i32 1
  %167 = getelementptr inbounds [20 x %struct.QuestionStruct]* %166, i32 0, i32 0
  %168 = getelementptr inbounds %struct.QuestionStruct* %167, i32 0, i32 2
  %169 = getelementptr inbounds [4 x i8*]* %168, i32 0, i32 1
  %170 = load i8** %169, align 8
  %171 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %172 = getelementptr inbounds [3 x %struct.SectionStruct]* %171, i32 0, i32 0
  %173 = getelementptr inbounds %struct.SectionStruct* %172, i32 0, i32 1
  %174 = getelementptr inbounds [20 x %struct.QuestionStruct]* %173, i32 0, i32 0
  %175 = getelementptr inbounds %struct.QuestionStruct* %174, i32 0, i32 2
  %176 = getelementptr inbounds [4 x i8*]* %175, i32 0, i32 2
  %177 = load i8** %176, align 8
  %178 = load i8* %89, align 1
  %179 = sext i8 %178 to i32
  %180 = icmp eq i32 %179, 0
  br i1 %180, label %181, label %191

; <label>:181        		; preds = %0
  %182 = load i32** @ptrglb2, align 8
  %183 = load i32** @ptrglb1, align 8
  %184 = load i32* @glb1, align 4
  %185 = load i32* @glb2, align 4
  %186 = add i32 %184, %185
  %187 = load i32* %183, align 4
  %188 = add i32 %186, %187
  %189 = load i32* %182, align 4
  %190 = add i32 %188, %189
  br label %191

; <label>:191            		; preds = %181, %0
  %result.0 = phi i32 [ %190, %181 ], [ 0, %0 ]
  %192 = load i8* %96, align 1
  %193 = sext i8 %192 to i32
  %194 = icmp eq i32 %193, 0
  br i1 %194, label %195, label %196

; <label>:195		; preds = %191
  br label %196

; <label>:196                  		; preds = %195, %191
  %result.1 = phi i32 [ 20, %195 ], [ %result.0, %191 ]
  %197 = load i8* %103, align 1
  %198 = sext i8 %197 to i32
  %199 = icmp eq i32 %198, 0
  br i1 %199, label %200, label %201

; <label>:200		; preds = %196
  br label %201

; <label>:201                  		; preds = %200, %196
  %result.2 = phi i32 [ 30, %200 ], [ %result.1, %196 ]
  %202 = load i8* %110, align 1
  %203 = sext i8 %202 to i32
  %204 = icmp eq i32 %203, 0
  br i1 %204, label %205, label %206

; <label>:205		; preds = %201
  br label %206

; <label>:206                  		; preds = %205, %201
  %result.3 = phi i32 [ 40, %205 ], [ %result.2, %201 ]
  %207 = load i8* %125, align 1
  %208 = sext i8 %207 to i32
  %209 = icmp eq i32 %208, 0
  br i1 %209, label %210, label %211

; <label>:210		; preds = %206
  br label %211

; <label>:211                  		; preds = %210, %206
  %result.4 = phi i32 [ 50, %210 ], [ %result.3, %206 ]
  %212 = load i8* %133, align 1
  %213 = sext i8 %212 to i32
  %214 = icmp eq i32 %213, 0
  br i1 %214, label %215, label %216

; <label>:215		; preds = %211
  br label %216

; <label>:216                  		; preds = %215, %211
  %result.5 = phi i32 [ 60, %215 ], [ %result.4, %211 ]
  %217 = load i8* %141, align 1
  %218 = sext i8 %217 to i32
  %219 = icmp eq i32 %218, 0
  br i1 %219, label %220, label %221

; <label>:220		; preds = %216
  br label %221

; <label>:221                  		; preds = %220, %216
  %result.6 = phi i32 [ 70, %220 ], [ %result.5, %216 ]
  %222 = load i8* %149, align 1
  %223 = sext i8 %222 to i32
  %224 = icmp eq i32 %223, 0
  br i1 %224, label %225, label %226

; <label>:225		; preds = %221
  br label %226

; <label>:226                  		; preds = %225, %221
  %result.7 = phi i32 [ 80, %225 ], [ %result.6, %221 ]
  %227 = load i8* %156, align 1
  %228 = sext i8 %227 to i32
  %229 = icmp eq i32 %228, 0
  br i1 %229, label %230, label %231

; <label>:230		; preds = %226
  br label %231

; <label>:231                  		; preds = %230, %226
  %result.8 = phi i32 [ 90, %230 ], [ %result.7, %226 ]
  %232 = load i8* %163, align 1
  %233 = sext i8 %232 to i32
  %234 = icmp eq i32 %233, 0
  br i1 %234, label %235, label %236

; <label>:235		; preds = %231
  br label %236

; <label>:236                   		; preds = %235, %231
  %result.9 = phi i32 [ 100, %235 ], [ %result.8, %231 ]
  %237 = load i8* %170, align 1
  %238 = sext i8 %237 to i32
  %239 = icmp eq i32 %238, 0
  br i1 %239, label %240, label %241

; <label>:240		; preds = %236
  br label %241

; <label>:241                    		; preds = %240, %236
  %result.10 = phi i32 [ 110, %240 ], [ %result.9, %236 ]
  %242 = load i8* %177, align 1
  %243 = sext i8 %242 to i32
  %244 = icmp eq i32 %243, 0
  br i1 %244, label %245, label %246

; <label>:245		; preds = %241
  br label %246

; <label>:246                     		; preds = %245, %241
  %result.11 = phi i32 [ 120, %245 ], [ %result.10, %241 ]
  %247 = load i8* %117, align 1
  %248 = sext i8 %247 to i32
  %249 = icmp eq i32 %248, 0
  br i1 %249, label %250, label %251

; <label>:250		; preds = %246
  br label %251

; <label>:251                     		; preds = %250, %246
  %result.12 = phi i32 [ 320, %250 ], [ %result.11, %246 ]
  %252 = add i32 %result.12, %62
  %253 = add i32 %252, %82
  ret i32 %253
}

