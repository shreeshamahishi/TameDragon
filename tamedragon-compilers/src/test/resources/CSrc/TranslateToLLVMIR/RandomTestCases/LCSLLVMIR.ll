@.str = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @strlen(i8*) 

declare noalias i8* @malloc(i64) nounwind

declare void @free(i8*) nounwind

define i8* @LCS(i8* %a, i8* %b) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %n = alloca i32, align 4
  %m = alloca i32, align 4
  %S = alloca i32**, align 8
  %R = alloca i32**, align 8
  %ii = alloca i32, align 4
  %jj = alloca i32, align 4
  %pos = alloca i32, align 4
  %lcs = alloca i8*, align 8
  store i8* %a, i8** %1, align 8
  store i8* %b, i8** %2, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = call i32 @strlen(i8* %3)
  store i32 %4, i32* %n, align 4
  %5 = load i8*, i8** %2, align 8
  %6 = call i32 @strlen(i8* %5)
  store i32 %6, i32* %m, align 4
  %7 = load i32, i32* %n, align 4
  %8 = add i32 %7, 1
  %9 = mul i32 %8, 4
  %10 = sext i32 %9 to i64
  %11 = call i8* @malloc(i64 %10) nounwind
  %12 = bitcast i8* %11 to i32**
  store i32** %12, i32*** %S, align 8
  %13 = load i32, i32* %n, align 4
  %14 = add i32 %13, 1
  %15 = mul i32 %14, 4
  %16 = sext i32 %15 to i64
  %17 = call i8* @malloc(i64 %16) nounwind
  %18 = bitcast i8* %17 to i32**
  store i32** %18, i32*** %R, align 8
  store i32 0, i32* %ii, align 4
  br label %19

; <label>:19  		; preds = %0, %42
  %20 = load i32, i32* %ii, align 4
  %21 = load i32, i32* %n, align 4
  %22 = icmp sle i32 %20, %21
  br i1 %22, label %23, label %45

; <label>:23       		; preds = %19
  %24 = load i32, i32* %m, align 4
  %25 = add i32 %24, 1
  %26 = mul i32 %25, 4
  %27 = sext i32 %26 to i64
  %28 = call i8* @malloc(i64 %27) nounwind
  %29 = bitcast i8* %28 to i32*
  %30 = load i32**, i32*** %S, align 8
  %31 = load i32, i32* %ii, align 4
  %32 = getelementptr inbounds i32*, i32** %30, i32 %31
  store i32* %29, i32** %32, align 8
  %33 = load i32, i32* %m, align 4
  %34 = add i32 %33, 1
  %35 = mul i32 %34, 4
  %36 = sext i32 %35 to i64
  %37 = call i8* @malloc(i64 %36) nounwind
  %38 = bitcast i8* %37 to i32*
  %39 = load i32**, i32*** %R, align 8
  %40 = load i32, i32* %ii, align 4
  %41 = getelementptr inbounds i32*, i32** %39, i32 %40
  store i32* %38, i32** %41, align 8
  br label %42

; <label>:42      		; preds = %23
  %43 = load i32, i32* %ii, align 4
  %44 = add i32 %43, 1
  store i32 %44, i32* %ii, align 4
  br label %19

; <label>:45   		; preds = %19
  store i32 0, i32* %ii, align 4
  br label %46

; <label>:46 		; preds = %45, %61
  %47 = load i32, i32* %ii, align 4
  %48 = load i32, i32* %n, align 4
  %49 = icmp sle i32 %47, %48
  br i1 %49, label %50, label %64

; <label>:50                      		; preds = %46
  %51 = load i32**, i32*** %S, align 8
  %52 = load i32, i32* %ii, align 4
  %53 = getelementptr inbounds i32*, i32** %51, i32 %52
  %54 = load i32*, i32** %53, align 8
  %55 = getelementptr inbounds i32, i32* %54, i32 0
  store i32 0, i32* %55, align 4
  %56 = load i32**, i32*** %R, align 8
  %57 = load i32, i32* %ii, align 4
  %58 = getelementptr inbounds i32*, i32** %56, i32 %57
  %59 = load i32*, i32** %58, align 8
  %60 = getelementptr inbounds i32, i32* %59, i32 0
  store i32 1, i32* %60, align 4
  br label %61

; <label>:61      		; preds = %50
  %62 = load i32, i32* %ii, align 4
  %63 = add i32 %62, 1
  store i32 %63, i32* %ii, align 4
  br label %46

; <label>:64   		; preds = %46
  store i32 0, i32* %jj, align 4
  br label %65

; <label>:65 		; preds = %64, %80
  %66 = load i32, i32* %jj, align 4
  %67 = load i32, i32* %m, align 4
  %68 = icmp sle i32 %66, %67
  br i1 %68, label %69, label %83

; <label>:69                        		; preds = %65
  %70 = load i32**, i32*** %S, align 8
  %71 = getelementptr inbounds i32*, i32** %70, i32 0
  %72 = load i32*, i32** %71, align 8
  %73 = load i32, i32* %jj, align 4
  %74 = getelementptr inbounds i32, i32* %72, i32 %73
  store i32 0, i32* %74, align 4
  %75 = load i32**, i32*** %R, align 8
  %76 = getelementptr inbounds i32*, i32** %75, i32 0
  %77 = load i32*, i32** %76, align 8
  %78 = load i32, i32* %jj, align 4
  %79 = getelementptr inbounds i32, i32* %77, i32 %78
  store i32 2, i32* %79, align 4
  br label %80

; <label>:80      		; preds = %69
  %81 = load i32, i32* %jj, align 4
  %82 = add i32 %81, 1
  store i32 %82, i32* %jj, align 4
  br label %65

; <label>:83   		; preds = %65
  store i32 1, i32* %ii, align 4
  br label %84

; <label>:84		; preds = %83, %232
  %85 = load i32, i32* %ii, align 4
  %86 = load i32, i32* %n, align 4
  %87 = icmp sle i32 %85, %86
  br i1 %87, label %88, label %235

; <label>:88   		; preds = %84
  store i32 1, i32* %jj, align 4
  br label %89

; <label>:89		; preds = %88, %228
  %90 = load i32, i32* %jj, align 4
  %91 = load i32, i32* %m, align 4
  %92 = icmp sle i32 %90, %91
  br i1 %92, label %93, label %231

; <label>:93       		; preds = %89
  %94 = load i8*, i8** %1, align 8
  %95 = load i32, i32* %ii, align 4
  %96 = sub i32 %95, 1
  %97 = getelementptr inbounds i8, i8* %94, i32 %96
  %98 = load i8*, i8** %2, align 8
  %99 = load i32, i32* %jj, align 4
  %100 = sub i32 %99, 1
  %101 = getelementptr inbounds i8, i8* %98, i32 %100
  %102 = load i8, i8* %97, align 1
  %103 = load i8, i8* %101, align 1
  %104 = icmp eq i8 %102, %103
  br i1 %104, label %105, label %128

; <label>:105                          		; preds = %93
  %106 = load i32**, i32*** %S, align 8
  %107 = load i32, i32* %ii, align 4
  %108 = sub i32 %107, 1
  %109 = getelementptr inbounds i32*, i32** %106, i32 %108
  %110 = load i32*, i32** %109, align 8
  %111 = load i32, i32* %jj, align 4
  %112 = sub i32 %111, 1
  %113 = getelementptr inbounds i32, i32* %110, i32 %112
  %114 = load i32, i32* %113, align 4
  %115 = add i32 %114, 1
  %116 = load i32**, i32*** %S, align 8
  %117 = load i32, i32* %ii, align 4
  %118 = getelementptr inbounds i32*, i32** %116, i32 %117
  %119 = load i32*, i32** %118, align 8
  %120 = load i32, i32* %jj, align 4
  %121 = getelementptr inbounds i32, i32* %119, i32 %120
  store i32 %115, i32* %121, align 4
  %122 = load i32**, i32*** %R, align 8
  %123 = load i32, i32* %ii, align 4
  %124 = getelementptr inbounds i32*, i32** %122, i32 %123
  %125 = load i32*, i32** %124, align 8
  %126 = load i32, i32* %jj, align 4
  %127 = getelementptr inbounds i32, i32* %125, i32 %126
  store i32 3, i32* %127, align 4
  br label %151

; <label>:128                          		; preds = %93
  %129 = load i32**, i32*** %S, align 8
  %130 = load i32, i32* %ii, align 4
  %131 = sub i32 %130, 1
  %132 = getelementptr inbounds i32*, i32** %129, i32 %131
  %133 = load i32*, i32** %132, align 8
  %134 = load i32, i32* %jj, align 4
  %135 = sub i32 %134, 1
  %136 = getelementptr inbounds i32, i32* %133, i32 %135
  %137 = load i32, i32* %136, align 4
  %138 = add i32 %137, 0
  %139 = load i32**, i32*** %S, align 8
  %140 = load i32, i32* %ii, align 4
  %141 = getelementptr inbounds i32*, i32** %139, i32 %140
  %142 = load i32*, i32** %141, align 8
  %143 = load i32, i32* %jj, align 4
  %144 = getelementptr inbounds i32, i32* %142, i32 %143
  store i32 %138, i32* %144, align 4
  %145 = load i32**, i32*** %R, align 8
  %146 = load i32, i32* %ii, align 4
  %147 = getelementptr inbounds i32*, i32** %145, i32 %146
  %148 = load i32*, i32** %147, align 8
  %149 = load i32, i32* %jj, align 4
  %150 = getelementptr inbounds i32, i32* %148, i32 %149
  store i32 0, i32* %150, align 4
  br label %151

; <label>:151                   		; preds = %105, %128
  %152 = load i32**, i32*** %S, align 8
  %153 = load i32, i32* %ii, align 4
  %154 = sub i32 %153, 1
  %155 = getelementptr inbounds i32*, i32** %152, i32 %154
  %156 = load i32*, i32** %155, align 8
  %157 = load i32, i32* %jj, align 4
  %158 = getelementptr inbounds i32, i32* %156, i32 %157
  %159 = load i32**, i32*** %S, align 8
  %160 = load i32, i32* %ii, align 4
  %161 = getelementptr inbounds i32*, i32** %159, i32 %160
  %162 = load i32*, i32** %161, align 8
  %163 = load i32, i32* %jj, align 4
  %164 = getelementptr inbounds i32, i32* %162, i32 %163
  %165 = load i32, i32* %158, align 4
  %166 = load i32, i32* %164, align 4
  %167 = icmp sge i32 %165, %166
  br i1 %167, label %168, label %189

; <label>:168                         		; preds = %151
  %169 = load i32**, i32*** %S, align 8
  %170 = load i32, i32* %ii, align 4
  %171 = sub i32 %170, 1
  %172 = getelementptr inbounds i32*, i32** %169, i32 %171
  %173 = load i32*, i32** %172, align 8
  %174 = load i32, i32* %jj, align 4
  %175 = getelementptr inbounds i32, i32* %173, i32 %174
  %176 = load i32**, i32*** %S, align 8
  %177 = load i32, i32* %ii, align 4
  %178 = getelementptr inbounds i32*, i32** %176, i32 %177
  %179 = load i32*, i32** %178, align 8
  %180 = load i32, i32* %jj, align 4
  %181 = getelementptr inbounds i32, i32* %179, i32 %180
  %182 = load i32, i32* %175, align 4
  store i32 %182, i32* %181, align 4
  %183 = load i32**, i32*** %R, align 8
  %184 = load i32, i32* %ii, align 4
  %185 = getelementptr inbounds i32*, i32** %183, i32 %184
  %186 = load i32*, i32** %185, align 8
  %187 = load i32, i32* %jj, align 4
  %188 = getelementptr inbounds i32, i32* %186, i32 %187
  store i32 1, i32* %188, align 4
  br label %189

; <label>:189                   		; preds = %151, %168
  %190 = load i32**, i32*** %S, align 8
  %191 = load i32, i32* %ii, align 4
  %192 = getelementptr inbounds i32*, i32** %190, i32 %191
  %193 = load i32*, i32** %192, align 8
  %194 = load i32, i32* %jj, align 4
  %195 = sub i32 %194, 1
  %196 = getelementptr inbounds i32, i32* %193, i32 %195
  %197 = load i32**, i32*** %S, align 8
  %198 = load i32, i32* %ii, align 4
  %199 = getelementptr inbounds i32*, i32** %197, i32 %198
  %200 = load i32*, i32** %199, align 8
  %201 = load i32, i32* %jj, align 4
  %202 = getelementptr inbounds i32, i32* %200, i32 %201
  %203 = load i32, i32* %196, align 4
  %204 = load i32, i32* %202, align 4
  %205 = icmp sge i32 %203, %204
  br i1 %205, label %206, label %227

; <label>:206                         		; preds = %189
  %207 = load i32**, i32*** %S, align 8
  %208 = load i32, i32* %ii, align 4
  %209 = getelementptr inbounds i32*, i32** %207, i32 %208
  %210 = load i32*, i32** %209, align 8
  %211 = load i32, i32* %jj, align 4
  %212 = sub i32 %211, 1
  %213 = getelementptr inbounds i32, i32* %210, i32 %212
  %214 = load i32**, i32*** %S, align 8
  %215 = load i32, i32* %ii, align 4
  %216 = getelementptr inbounds i32*, i32** %214, i32 %215
  %217 = load i32*, i32** %216, align 8
  %218 = load i32, i32* %jj, align 4
  %219 = getelementptr inbounds i32, i32* %217, i32 %218
  %220 = load i32, i32* %213, align 4
  store i32 %220, i32* %219, align 4
  %221 = load i32**, i32*** %R, align 8
  %222 = load i32, i32* %ii, align 4
  %223 = getelementptr inbounds i32*, i32** %221, i32 %222
  %224 = load i32*, i32** %223, align 8
  %225 = load i32, i32* %jj, align 4
  %226 = getelementptr inbounds i32, i32* %224, i32 %225
  store i32 2, i32* %226, align 4
  br label %227

; <label>:227		; preds = %189, %206
  br label %228

; <label>:228     		; preds = %227
  %229 = load i32, i32* %jj, align 4
  %230 = add i32 %229, 1
  store i32 %230, i32* %jj, align 4
  br label %89

; <label>:231		; preds = %89
  br label %232

; <label>:232     		; preds = %231
  %233 = load i32, i32* %ii, align 4
  %234 = add i32 %233, 1
  store i32 %234, i32* %ii, align 4
  br label %84

; <label>:235                        		; preds = %84
  %236 = load i32, i32* %n, align 4
  store i32 %236, i32* %ii, align 4
  %237 = load i32, i32* %m, align 4
  store i32 %237, i32* %jj, align 4
  %238 = load i32**, i32*** %S, align 8
  %239 = load i32, i32* %ii, align 4
  %240 = getelementptr inbounds i32*, i32** %238, i32 %239
  %241 = load i32*, i32** %240, align 8
  %242 = load i32, i32* %jj, align 4
  %243 = getelementptr inbounds i32, i32* %241, i32 %242
  %244 = load i32, i32* %243, align 4
  store i32 %244, i32* %pos, align 4
  %245 = load i32, i32* %pos, align 4
  %246 = add i32 %245, 1
  %247 = mul i32 %246, 1
  %248 = sext i32 %247 to i64
  %249 = call i8* @malloc(i64 %248) nounwind
  store i8* %249, i8** %lcs, align 8
  %250 = load i32, i32* %pos, align 4
  %251 = sub i32 %250, 1
  store i32 %251, i32* %pos, align 4
  %252 = load i8*, i8** %lcs, align 8
  %253 = getelementptr inbounds i8, i8* %252, i32 %250
  store i8 0, i8* %253, align 1
  br label %254

; <label>:254		; preds = %235, %310
  %255 = load i32, i32* %ii, align 4
  %256 = icmp sgt i32 %255, 0
  br i1 %256, label %260, label %257

; <label>:257     		; preds = %254
  %258 = load i32, i32* %jj, align 4
  %259 = icmp sgt i32 %258, 0
  br label %260

; <label>:260         		; preds = %254, %257
  %261 = phi i1 [ true, %254 ], [ %259, %257 ]
  br i1 %261, label %262, label %311

; <label>:262                         		; preds = %260
  %263 = load i32**, i32*** %R, align 8
  %264 = load i32, i32* %ii, align 4
  %265 = getelementptr inbounds i32*, i32** %263, i32 %264
  %266 = load i32*, i32** %265, align 8
  %267 = load i32, i32* %jj, align 4
  %268 = getelementptr inbounds i32, i32* %266, i32 %267
  %269 = load i32, i32* %268, align 4
  %270 = icmp eq i32 %269, 3
  br i1 %270, label %271, label %284

; <label>:271                       		; preds = %262
  %272 = load i32, i32* %ii, align 4
  %273 = sub i32 %272, 1
  store i32 %273, i32* %ii, align 4
  %274 = load i32, i32* %jj, align 4
  %275 = sub i32 %274, 1
  store i32 %275, i32* %jj, align 4
  %276 = load i32, i32* %pos, align 4
  %277 = sub i32 %276, 1
  store i32 %277, i32* %pos, align 4
  %278 = load i8*, i8** %1, align 8
  %279 = load i32, i32* %ii, align 4
  %280 = getelementptr inbounds i8, i8* %278, i32 %279
  %281 = load i8*, i8** %lcs, align 8
  %282 = getelementptr inbounds i8, i8* %281, i32 %276
  %283 = load i8, i8* %280, align 1
  store i8 %283, i8* %282, align 1
  br label %310

; <label>:284                         		; preds = %262
  %285 = load i32**, i32*** %R, align 8
  %286 = load i32, i32* %ii, align 4
  %287 = getelementptr inbounds i32*, i32** %285, i32 %286
  %288 = load i32*, i32** %287, align 8
  %289 = load i32, i32* %jj, align 4
  %290 = getelementptr inbounds i32, i32* %288, i32 %289
  %291 = load i32, i32* %290, align 4
  %292 = icmp eq i32 %291, 1
  br i1 %292, label %293, label %296

; <label>:293     		; preds = %284
  %294 = load i32, i32* %ii, align 4
  %295 = sub i32 %294, 1
  store i32 %295, i32* %ii, align 4
  br label %309

; <label>:296                         		; preds = %284
  %297 = load i32**, i32*** %R, align 8
  %298 = load i32, i32* %ii, align 4
  %299 = getelementptr inbounds i32*, i32** %297, i32 %298
  %300 = load i32*, i32** %299, align 8
  %301 = load i32, i32* %jj, align 4
  %302 = getelementptr inbounds i32, i32* %300, i32 %301
  %303 = load i32, i32* %302, align 4
  %304 = icmp eq i32 %303, 2
  br i1 %304, label %305, label %308

; <label>:305     		; preds = %296
  %306 = load i32, i32* %jj, align 4
  %307 = sub i32 %306, 1
  store i32 %307, i32* %jj, align 4
  br label %308

; <label>:308		; preds = %296, %305
  br label %309

; <label>:309		; preds = %293, %308
  br label %310

; <label>:310		; preds = %271, %309
  br label %254

; <label>:311 		; preds = %260
  store i32 0, i32* %ii, align 4
  br label %312

; <label>:312		; preds = %311, %327
  %313 = load i32, i32* %ii, align 4
  %314 = load i32, i32* %n, align 4
  %315 = icmp sle i32 %313, %314
  br i1 %315, label %316, label %330

; <label>:316                           		; preds = %312
  %317 = load i32**, i32*** %S, align 8
  %318 = load i32, i32* %ii, align 4
  %319 = getelementptr inbounds i32*, i32** %317, i32 %318
  %320 = load i32*, i32** %319, align 8
  %321 = bitcast i32* %320 to i8*
  call void @free(i8* %321) nounwind
  %322 = load i32**, i32*** %R, align 8
  %323 = load i32, i32* %ii, align 4
  %324 = getelementptr inbounds i32*, i32** %322, i32 %323
  %325 = load i32*, i32** %324, align 8
  %326 = bitcast i32* %325 to i8*
  call void @free(i8* %326) nounwind
  br label %327

; <label>:327     		; preds = %316
  %328 = load i32, i32* %ii, align 4
  %329 = add i32 %328, 1
  store i32 %329, i32* %ii, align 4
  br label %312

; <label>:330        		; preds = %312
  %331 = load i32**, i32*** %S, align 8
  %332 = bitcast i32** %331 to i8*
  call void @free(i8* %332) nounwind
  %333 = load i32**, i32*** %R, align 8
  %334 = bitcast i32** %333 to i8*
  call void @free(i8* %334) nounwind
  %335 = load i8*, i8** %lcs, align 8
  ret i8* %335
}

define i32 @main(i32 %argc, i8** %argv) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i8**, align 8
  %3 = alloca i32, align 4
  store i32 %argc, i32* %1, align 4
  store i8** %argv, i8*** %2, align 8
  store i32 0, i32* %3, align 4
  %4 = load i8**, i8*** %2, align 8
  %5 = getelementptr inbounds i8*, i8** %4, i32 2
  %6 = load i8**, i8*** %2, align 8
  %7 = getelementptr inbounds i8*, i8** %6, i32 1
  %8 = load i8*, i8** %7, align 8
  %9 = load i8*, i8** %5, align 8
  %10 = call i8* @LCS(i8* %8, i8* %9)
  %11 = getelementptr inbounds [4 x i8], [4 x i8]* @.str, i32 0, i32 0
  %12 = call i32 (i8*, ...)* @printf(i8* %11, i8* %10)
  %13 = load i32, i32* %3, align 4
  ret i32 %13
}
