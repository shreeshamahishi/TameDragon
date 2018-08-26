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
  %localTest1 = alloca %struct.TestStruct, align 8
  %localTest2 = alloca %struct.TestStruct, align 8
  %localTest3 = alloca %struct.TestStruct, align 8
  %localTestMarks1 = alloca i32, align 4
  %localTestMarks2 = alloca i32, align 4
  %localTest1Opt1 = alloca i8*, align 8
  %localTest1Opt2 = alloca i8*, align 8
  %localTest2Opt1 = alloca i8*, align 8
  %localTest2Opt2 = alloca i8*, align 8
  %localTest3Opt1 = alloca i8*, align 8
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
  %7 = getelementptr inbounds [3 x i8]* @.str, i32 0, i32 0
  %8 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 0
  store i8* %7, i8** %8, align 8
  %9 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 2
  store i32 60, i32* %9, align 4
  %10 = getelementptr inbounds [10 x i8]* @.str1, i32 0, i32 0
  %11 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %12 = getelementptr inbounds [3 x %struct.SectionStruct]* %11, i32 0, i32 0
  %13 = getelementptr inbounds %struct.SectionStruct* %12, i32 0, i32 0
  store i8* %10, i8** %13, align 8
  %14 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %15 = getelementptr inbounds [3 x %struct.SectionStruct]* %14, i32 0, i32 0
  %16 = getelementptr inbounds %struct.SectionStruct* %15, i32 0, i32 2
  store i32 10, i32* %16, align 4
  %17 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %18 = getelementptr inbounds [3 x %struct.SectionStruct]* %17, i32 0, i32 0
  %19 = getelementptr inbounds %struct.SectionStruct* %18, i32 0, i32 1
  %20 = getelementptr inbounds [20 x %struct.QuestionStruct]* %19, i32 0, i32 0
  %21 = getelementptr inbounds %struct.QuestionStruct* %20, i32 0, i32 0
  store i32 1, i32* %21, align 4
  %22 = getelementptr inbounds [29 x i8]* @.str2, i32 0, i32 0
  %23 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %24 = getelementptr inbounds [3 x %struct.SectionStruct]* %23, i32 0, i32 0
  %25 = getelementptr inbounds %struct.SectionStruct* %24, i32 0, i32 1
  %26 = getelementptr inbounds [20 x %struct.QuestionStruct]* %25, i32 0, i32 0
  %27 = getelementptr inbounds %struct.QuestionStruct* %26, i32 0, i32 1
  store i8* %22, i8** %27, align 8
  %28 = getelementptr inbounds [6 x i8]* @.str3, i32 0, i32 0
  %29 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %30 = getelementptr inbounds [3 x %struct.SectionStruct]* %29, i32 0, i32 0
  %31 = getelementptr inbounds %struct.SectionStruct* %30, i32 0, i32 1
  %32 = getelementptr inbounds [20 x %struct.QuestionStruct]* %31, i32 0, i32 0
  %33 = getelementptr inbounds %struct.QuestionStruct* %32, i32 0, i32 2
  %34 = getelementptr inbounds [4 x i8*]* %33, i32 0, i32 0
  store i8* %28, i8** %34, align 8
  %35 = getelementptr inbounds [10 x i8]* @.str4, i32 0, i32 0
  %36 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %37 = getelementptr inbounds [3 x %struct.SectionStruct]* %36, i32 0, i32 0
  %38 = getelementptr inbounds %struct.SectionStruct* %37, i32 0, i32 1
  %39 = getelementptr inbounds [20 x %struct.QuestionStruct]* %38, i32 0, i32 0
  %40 = getelementptr inbounds %struct.QuestionStruct* %39, i32 0, i32 2
  %41 = getelementptr inbounds [4 x i8*]* %40, i32 0, i32 1
  store i8* %35, i8** %41, align 8
  %42 = getelementptr inbounds [10 x i8]* @.str4, i32 0, i32 0
  %43 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %44 = getelementptr inbounds [3 x %struct.SectionStruct]* %43, i32 0, i32 0
  %45 = getelementptr inbounds %struct.SectionStruct* %44, i32 0, i32 1
  %46 = getelementptr inbounds [20 x %struct.QuestionStruct]* %45, i32 0, i32 0
  %47 = getelementptr inbounds %struct.QuestionStruct* %46, i32 0, i32 2
  %48 = getelementptr inbounds [4 x i8*]* %47, i32 0, i32 2
  store i8* %42, i8** %48, align 8
  %49 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %50 = load i32* %3, align 4
  %51 = getelementptr inbounds [3 x %struct.SectionStruct]* %49, i32 0, i32 %50
  %52 = getelementptr inbounds %struct.SectionStruct* %51, i32 0, i32 1
  %53 = load i32* %4, align 4
  %54 = getelementptr inbounds [20 x %struct.QuestionStruct]* %52, i32 0, i32 %53
  %55 = getelementptr inbounds %struct.QuestionStruct* %54, i32 0, i32 0
  %56 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %57 = load i32* %3, align 4
  %58 = getelementptr inbounds [3 x %struct.SectionStruct]* %56, i32 0, i32 %57
  %59 = getelementptr inbounds %struct.SectionStruct* %58, i32 0, i32 1
  %60 = load i32* %4, align 4
  %61 = getelementptr inbounds [20 x %struct.QuestionStruct]* %59, i32 0, i32 %60
  %62 = getelementptr inbounds %struct.QuestionStruct* %61, i32 0, i32 0
  %63 = load i32* %55, align 4
  %64 = load i32* %62, align 4
  %65 = add i32 %63, %64
  %66 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %67 = load i32* %3, align 4
  %68 = getelementptr inbounds [3 x %struct.SectionStruct]* %66, i32 0, i32 %67
  %69 = getelementptr inbounds %struct.SectionStruct* %68, i32 0, i32 1
  %70 = load i32* %4, align 4
  %71 = getelementptr inbounds [20 x %struct.QuestionStruct]* %69, i32 0, i32 %70
  %72 = getelementptr inbounds %struct.QuestionStruct* %71, i32 0, i32 0
  %73 = load i32* %72, align 4
  %74 = add i32 %65, %73
  store i32 %74, i32* %localTestMarks1, align 4
  %75 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %76 = load i32* %5, align 4
  %77 = getelementptr inbounds [3 x %struct.SectionStruct]* %75, i32 0, i32 %76
  %78 = getelementptr inbounds %struct.SectionStruct* %77, i32 0, i32 1
  %79 = load i32* %6, align 4
  %80 = getelementptr inbounds [20 x %struct.QuestionStruct]* %78, i32 0, i32 %79
  %81 = getelementptr inbounds %struct.QuestionStruct* %80, i32 0, i32 0
  %82 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %83 = load i32* %5, align 4
  %84 = getelementptr inbounds [3 x %struct.SectionStruct]* %82, i32 0, i32 %83
  %85 = getelementptr inbounds %struct.SectionStruct* %84, i32 0, i32 1
  %86 = load i32* %6, align 4
  %87 = getelementptr inbounds [20 x %struct.QuestionStruct]* %85, i32 0, i32 %86
  %88 = getelementptr inbounds %struct.QuestionStruct* %87, i32 0, i32 0
  %89 = load i32* %81, align 4
  %90 = load i32* %88, align 4
  %91 = add i32 %89, %90
  %92 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %93 = load i32* %5, align 4
  %94 = getelementptr inbounds [3 x %struct.SectionStruct]* %92, i32 0, i32 %93
  %95 = getelementptr inbounds %struct.SectionStruct* %94, i32 0, i32 1
  %96 = load i32* %6, align 4
  %97 = getelementptr inbounds [20 x %struct.QuestionStruct]* %95, i32 0, i32 %96
  %98 = getelementptr inbounds %struct.QuestionStruct* %97, i32 0, i32 0
  %99 = load i32* %98, align 4
  %100 = add i32 %91, %99
  store i32 %100, i32* %localTestMarks2, align 4
  %101 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %102 = getelementptr inbounds [3 x %struct.SectionStruct]* %101, i32 0, i32 0
  %103 = getelementptr inbounds %struct.SectionStruct* %102, i32 0, i32 1
  %104 = getelementptr inbounds [20 x %struct.QuestionStruct]* %103, i32 0, i32 0
  %105 = getelementptr inbounds %struct.QuestionStruct* %104, i32 0, i32 2
  %106 = getelementptr inbounds [4 x i8*]* %105, i32 0, i32 1
  %107 = load i8** %106, align 8
  store i8* %107, i8** %localTest1Opt1, align 8
  %108 = getelementptr inbounds %struct.TestStruct* %localTest1, i32 0, i32 1
  %109 = getelementptr inbounds [3 x %struct.SectionStruct]* %108, i32 0, i32 0
  %110 = getelementptr inbounds %struct.SectionStruct* %109, i32 0, i32 1
  %111 = getelementptr inbounds [20 x %struct.QuestionStruct]* %110, i32 0, i32 0
  %112 = getelementptr inbounds %struct.QuestionStruct* %111, i32 0, i32 2
  %113 = getelementptr inbounds [4 x i8*]* %112, i32 0, i32 2
  %114 = load i8** %113, align 8
  store i8* %114, i8** %localTest1Opt2, align 8
  %115 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %116 = getelementptr inbounds [3 x %struct.SectionStruct]* %115, i32 0, i32 0
  %117 = getelementptr inbounds %struct.SectionStruct* %116, i32 0, i32 1
  %118 = getelementptr inbounds [20 x %struct.QuestionStruct]* %117, i32 0, i32 0
  %119 = getelementptr inbounds %struct.QuestionStruct* %118, i32 0, i32 2
  %120 = getelementptr inbounds [4 x i8*]* %119, i32 0, i32 1
  %121 = load i8** %120, align 8
  store i8* %121, i8** %localTest2Opt1, align 8
  %122 = getelementptr inbounds %struct.TestStruct* %localTest2, i32 0, i32 1
  %123 = getelementptr inbounds [3 x %struct.SectionStruct]* %122, i32 0, i32 0
  %124 = getelementptr inbounds %struct.SectionStruct* %123, i32 0, i32 1
  %125 = getelementptr inbounds [20 x %struct.QuestionStruct]* %124, i32 0, i32 0
  %126 = getelementptr inbounds %struct.QuestionStruct* %125, i32 0, i32 2
  %127 = getelementptr inbounds [4 x i8*]* %126, i32 0, i32 2
  %128 = load i8** %127, align 8
  store i8* %128, i8** %localTest2Opt2, align 8
  %129 = getelementptr inbounds %struct.TestStruct* %localTest3, i32 0, i32 1
  %130 = getelementptr inbounds [3 x %struct.SectionStruct]* %129, i32 0, i32 0
  %131 = getelementptr inbounds %struct.SectionStruct* %130, i32 0, i32 1
  %132 = getelementptr inbounds [20 x %struct.QuestionStruct]* %131, i32 0, i32 0
  %133 = getelementptr inbounds %struct.QuestionStruct* %132, i32 0, i32 2
  %134 = getelementptr inbounds [4 x i8*]* %133, i32 0, i32 1
  %135 = load i8** %134, align 8
  store i8* %135, i8** %localTest3Opt1, align 8
  %136 = load %struct.TestStruct** %1, align 8
  %137 = getelementptr inbounds %struct.TestStruct* %136, i32 0
  %138 = getelementptr inbounds %struct.TestStruct* %137, i32 0, i32 1
  %139 = getelementptr inbounds [3 x %struct.SectionStruct]* %138, i32 0, i32 0
  %140 = getelementptr inbounds %struct.SectionStruct* %139, i32 0, i32 1
  %141 = getelementptr inbounds [20 x %struct.QuestionStruct]* %140, i32 0, i32 0
  %142 = getelementptr inbounds %struct.QuestionStruct* %141, i32 0, i32 2
  %143 = getelementptr inbounds [4 x i8*]* %142, i32 0, i32 1
  %144 = load i8** %143, align 8
  store i8* %144, i8** %arg1Opt1, align 8
  %145 = load %struct.TestStruct** %1, align 8
  %146 = getelementptr inbounds %struct.TestStruct* %145, i32 0
  %147 = getelementptr inbounds %struct.TestStruct* %146, i32 0, i32 1
  %148 = getelementptr inbounds [3 x %struct.SectionStruct]* %147, i32 0, i32 0
  %149 = getelementptr inbounds %struct.SectionStruct* %148, i32 0, i32 1
  %150 = getelementptr inbounds [20 x %struct.QuestionStruct]* %149, i32 0, i32 0
  %151 = getelementptr inbounds %struct.QuestionStruct* %150, i32 0, i32 2
  %152 = getelementptr inbounds [4 x i8*]* %151, i32 0, i32 2
  %153 = load i8** %152, align 8
  store i8* %153, i8** %arg1Opt2, align 8
  %154 = load %struct.TestStruct** %2, align 8
  %155 = getelementptr inbounds %struct.TestStruct* %154, i32 0
  %156 = getelementptr inbounds %struct.TestStruct* %155, i32 0, i32 1
  %157 = getelementptr inbounds [3 x %struct.SectionStruct]* %156, i32 0, i32 0
  %158 = getelementptr inbounds %struct.SectionStruct* %157, i32 0, i32 1
  %159 = getelementptr inbounds [20 x %struct.QuestionStruct]* %158, i32 0, i32 0
  %160 = getelementptr inbounds %struct.QuestionStruct* %159, i32 0, i32 2
  %161 = getelementptr inbounds [4 x i8*]* %160, i32 0, i32 1
  %162 = load i8** %161, align 8
  store i8* %162, i8** %arg2Opt1, align 8
  %163 = load %struct.TestStruct** %2, align 8
  %164 = getelementptr inbounds %struct.TestStruct* %163, i32 0
  %165 = getelementptr inbounds %struct.TestStruct* %164, i32 0, i32 1
  %166 = getelementptr inbounds [3 x %struct.SectionStruct]* %165, i32 0, i32 0
  %167 = getelementptr inbounds %struct.SectionStruct* %166, i32 0, i32 1
  %168 = getelementptr inbounds [20 x %struct.QuestionStruct]* %167, i32 0, i32 0
  %169 = getelementptr inbounds %struct.QuestionStruct* %168, i32 0, i32 2
  %170 = getelementptr inbounds [4 x i8*]* %169, i32 0, i32 2
  %171 = load i8** %170, align 8
  store i8* %171, i8** %arg2Opt2, align 8
  %172 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %173 = getelementptr inbounds [3 x %struct.SectionStruct]* %172, i32 0, i32 0
  %174 = getelementptr inbounds %struct.SectionStruct* %173, i32 0, i32 1
  %175 = getelementptr inbounds [20 x %struct.QuestionStruct]* %174, i32 0, i32 0
  %176 = getelementptr inbounds %struct.QuestionStruct* %175, i32 0, i32 2
  %177 = getelementptr inbounds [4 x i8*]* %176, i32 0, i32 1
  %178 = load i8** %177, align 8
  store i8* %178, i8** %glblTest1Opt1, align 8
  %179 = getelementptr inbounds %struct.TestStruct* @glblTest1, i32 0, i32 1
  %180 = getelementptr inbounds [3 x %struct.SectionStruct]* %179, i32 0, i32 0
  %181 = getelementptr inbounds %struct.SectionStruct* %180, i32 0, i32 1
  %182 = getelementptr inbounds [20 x %struct.QuestionStruct]* %181, i32 0, i32 0
  %183 = getelementptr inbounds %struct.QuestionStruct* %182, i32 0, i32 2
  %184 = getelementptr inbounds [4 x i8*]* %183, i32 0, i32 2
  %185 = load i8** %184, align 8
  store i8* %185, i8** %glblTest1Opt2, align 8
  %186 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %187 = getelementptr inbounds [3 x %struct.SectionStruct]* %186, i32 0, i32 0
  %188 = getelementptr inbounds %struct.SectionStruct* %187, i32 0, i32 1
  %189 = getelementptr inbounds [20 x %struct.QuestionStruct]* %188, i32 0, i32 0
  %190 = getelementptr inbounds %struct.QuestionStruct* %189, i32 0, i32 2
  %191 = getelementptr inbounds [4 x i8*]* %190, i32 0, i32 1
  %192 = load i8** %191, align 8
  store i8* %192, i8** %glblTest2Opt1, align 8
  %193 = getelementptr inbounds %struct.TestStruct* @glblTest2, i32 0, i32 1
  %194 = getelementptr inbounds [3 x %struct.SectionStruct]* %193, i32 0, i32 0
  %195 = getelementptr inbounds %struct.SectionStruct* %194, i32 0, i32 1
  %196 = getelementptr inbounds [20 x %struct.QuestionStruct]* %195, i32 0, i32 0
  %197 = getelementptr inbounds %struct.QuestionStruct* %196, i32 0, i32 2
  %198 = getelementptr inbounds [4 x i8*]* %197, i32 0, i32 2
  %199 = load i8** %198, align 8
  store i8* %199, i8** %glblTest2Opt2, align 8
  store i32 0, i32* %result, align 4
  %200 = load i8** %localTest1Opt1, align 8
  %201 = load i8* %200, align 1
  %202 = sext i8 %201 to i32
  %203 = icmp eq i32 %202, 0
  br i1 %203, label %204, label %214

; <label>:204          		; preds = %0
  %205 = load i32** @ptrglb2, align 8
  %206 = load i32** @ptrglb1, align 8
  %207 = load i32* @glb1, align 4
  %208 = load i32* @glb2, align 4
  %209 = add i32 %207, %208
  %210 = load i32* %206, align 4
  %211 = add i32 %209, %210
  %212 = load i32* %205, align 4
  %213 = add i32 %211, %212
  store i32 %213, i32* %result, align 4
  br label %214

; <label>:214        		; preds = %204, %0
  %215 = load i8** %localTest1Opt2, align 8
  %216 = load i8* %215, align 1
  %217 = sext i8 %216 to i32
  %218 = icmp eq i32 %217, 0
  br i1 %218, label %219, label %220

; <label>:219      		; preds = %214
  store i32 20, i32* %result, align 4
  br label %220

; <label>:220      		; preds = %219, %214
  %221 = load i8** %localTest2Opt1, align 8
  %222 = load i8* %221, align 1
  %223 = sext i8 %222 to i32
  %224 = icmp eq i32 %223, 0
  br i1 %224, label %225, label %226

; <label>:225      		; preds = %220
  store i32 30, i32* %result, align 4
  br label %226

; <label>:226      		; preds = %225, %220
  %227 = load i8** %localTest2Opt2, align 8
  %228 = load i8* %227, align 1
  %229 = sext i8 %228 to i32
  %230 = icmp eq i32 %229, 0
  br i1 %230, label %231, label %232

; <label>:231      		; preds = %226
  store i32 40, i32* %result, align 4
  br label %232

; <label>:232		; preds = %231, %226
  %233 = load i8** %arg1Opt1, align 8
  %234 = load i8* %233, align 1
  %235 = sext i8 %234 to i32
  %236 = icmp eq i32 %235, 0
  br i1 %236, label %237, label %238

; <label>:237      		; preds = %232
  store i32 50, i32* %result, align 4
  br label %238

; <label>:238		; preds = %237, %232
  %239 = load i8** %arg1Opt2, align 8
  %240 = load i8* %239, align 1
  %241 = sext i8 %240 to i32
  %242 = icmp eq i32 %241, 0
  br i1 %242, label %243, label %244

; <label>:243      		; preds = %238
  store i32 60, i32* %result, align 4
  br label %244

; <label>:244		; preds = %243, %238
  %245 = load i8** %arg2Opt1, align 8
  %246 = load i8* %245, align 1
  %247 = sext i8 %246 to i32
  %248 = icmp eq i32 %247, 0
  br i1 %248, label %249, label %250

; <label>:249      		; preds = %244
  store i32 70, i32* %result, align 4
  br label %250

; <label>:250		; preds = %249, %244
  %251 = load i8** %arg2Opt2, align 8
  %252 = load i8* %251, align 1
  %253 = sext i8 %252 to i32
  %254 = icmp eq i32 %253, 0
  br i1 %254, label %255, label %256

; <label>:255      		; preds = %250
  store i32 80, i32* %result, align 4
  br label %256

; <label>:256     		; preds = %255, %250
  %257 = load i8** %glblTest1Opt1, align 8
  %258 = load i8* %257, align 1
  %259 = sext i8 %258 to i32
  %260 = icmp eq i32 %259, 0
  br i1 %260, label %261, label %262

; <label>:261      		; preds = %256
  store i32 90, i32* %result, align 4
  br label %262

; <label>:262     		; preds = %261, %256
  %263 = load i8** %glblTest1Opt2, align 8
  %264 = load i8* %263, align 1
  %265 = sext i8 %264 to i32
  %266 = icmp eq i32 %265, 0
  br i1 %266, label %267, label %268

; <label>:267       		; preds = %262
  store i32 100, i32* %result, align 4
  br label %268

; <label>:268     		; preds = %267, %262
  %269 = load i8** %glblTest2Opt1, align 8
  %270 = load i8* %269, align 1
  %271 = sext i8 %270 to i32
  %272 = icmp eq i32 %271, 0
  br i1 %272, label %273, label %274

; <label>:273       		; preds = %268
  store i32 110, i32* %result, align 4
  br label %274

; <label>:274     		; preds = %273, %268
  %275 = load i8** %glblTest2Opt2, align 8
  %276 = load i8* %275, align 1
  %277 = sext i8 %276 to i32
  %278 = icmp eq i32 %277, 0
  br i1 %278, label %279, label %280

; <label>:279       		; preds = %274
  store i32 120, i32* %result, align 4
  br label %280

; <label>:280      		; preds = %279, %274
  %281 = load i8** %localTest3Opt1, align 8
  %282 = load i8* %281, align 1
  %283 = sext i8 %282 to i32
  %284 = icmp eq i32 %283, 0
  br i1 %284, label %285, label %286

; <label>:285       		; preds = %280
  store i32 320, i32* %result, align 4
  br label %286

; <label>:286       		; preds = %285, %280
  %287 = load i32* %result, align 4
  %288 = load i32* %localTestMarks1, align 4
  %289 = add i32 %287, %288
  %290 = load i32* %localTestMarks2, align 4
  %291 = add i32 %289, %290
  ret i32 %291
}