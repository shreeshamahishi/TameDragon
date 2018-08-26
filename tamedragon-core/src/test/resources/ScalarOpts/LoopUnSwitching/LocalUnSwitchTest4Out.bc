define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %p4, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  br i1 %1, label %split1, label %split2

; <label>:split1        		; preds = %0
  %3 = icmp slt i32 %flag, 6
  br i1 %3, label %split3, label %split4

; <label>:split3   		; preds = %split1
  %5 = icmp sgt i32 %flag, 7
  br i1 %5, label %split5, label %split6

; <label>:split5		; preds = %split3
  br label %22

; <label>:split6		; preds = %split3
  br label %41

; <label>:split4     		; preds = %split1
  %9 = icmp sgt i32 %flag, 7
  br i1 %9, label %split13, label %split14

; <label>:split13		; preds = %split4
  br label %69

; <label>:split14		; preds = %split4
  br label %78

; <label>:split2         		; preds = %0
  %13 = icmp slt i32 %flag, 6
  br i1 %13, label %split7, label %split8

; <label>:split7      		; preds = %split2
  %15 = icmp sgt i32 %flag, 7
  br i1 %15, label %split11, label %split12

; <label>:split11		; preds = %split7
  br label %125

; <label>:split12		; preds = %split7
  br label %134

; <label>:split8     		; preds = %split2
  %19 = icmp sgt i32 %flag, 7
  br i1 %19, label %split9, label %split10

; <label>:split9		; preds = %split8
  br label %162

; <label>:split10		; preds = %split8
  br label %171

; <label>:22   		; preds = %split5, %39
  %i.0 = phi i32 [ 0, %0 ], [ %40, %39 ]
  %.03 = phi i32 [ %p4, %0 ], [ %.3, %39 ]
  %.02 = phi i32 [ %p3, %0 ], [ %38, %39 ]
  %.01 = phi i32 [ %p2, %0 ], [ %.1, %39 ]
  %.0 = phi i32 [ %p1, %0 ], [ %25, %39 ]
  %23 = icmp slt i32 %i.0, 10
  br i1 %23, label %24, label %237

; <label>:24     		; preds = %22
  %25 = add i32 %.0, 1
  br i1 true, label %26, label %37

; <label>:26     		; preds = %24
  %27 = add i32 %.01, 1
  br i1 true, label %28, label %30

; <label>:28		; preds = %26
  %29 = add i32 %.03, 1
  br label %36

; <label>:30     		; preds = %26
  br i1 true, label %31, label %33

; <label>:31		; preds = %30
  %32 = sub i32 %.03, 1
  br label %35

; <label>:33		; preds = %30
  %34 = add i32 %.03, 1
  br label %35

; <label>:35         		; preds = %33, %31
  %.14 = phi i32 [ %32, %31 ], [ %34, %33 ]
  br label %36

; <label>:36         		; preds = %35, %28
  %.2 = phi i32 [ %29, %28 ], [ %.14, %35 ]
  br label %37

; <label>:37         		; preds = %36, %24
  %.3 = phi i32 [ %.2, %36 ], [ %.03, %24 ]
  %.1 = phi i32 [ %27, %36 ], [ %.01, %24 ]
  %38 = add i32 %.02, 1
  br label %39

; <label>:39		; preds = %37
  %40 = add i32 %i.0, 1
  br label %22

; <label>:41   		; preds = %48, %split6
  %42 = phi i32 [ 0, %0 ], [ %49, %48 ]
  %43 = phi i32 [ %p4, %0 ], [ %51, %48 ]
  %44 = phi i32 [ %p3, %0 ], [ %53, %48 ]
  %45 = phi i32 [ %p2, %0 ], [ %52, %48 ]
  %46 = phi i32 [ %p1, %0 ], [ %57, %48 ]
  %47 = icmp slt i32 %42, 10
  br i1 %47, label %56, label %237

; <label>:48		; preds = %50
  %49 = add i32 %42, 1
  br label %41

; <label>:50        		; preds = %54, %56
  %51 = phi i32 [ %55, %54 ], [ %43, %56 ]
  %52 = phi i32 [ %63, %54 ], [ %45, %56 ]
  %53 = add i32 %44, 1
  br label %48

; <label>:54        		; preds = %58, %60
  %55 = phi i32 [ %61, %60 ], [ %59, %58 ]
  br label %50

; <label>:56     		; preds = %41
  %57 = add i32 %46, 1
  br i1 true, label %62, label %50

; <label>:58        		; preds = %66, %64
  %59 = phi i32 [ %67, %66 ], [ %65, %64 ]
  br label %54

; <label>:60		; preds = %62
  %61 = add i32 %43, 1
  br label %54

; <label>:62     		; preds = %56
  %63 = add i32 %45, 1
  br i1 true, label %60, label %68

; <label>:64		; preds = %68
  %65 = add i32 %43, 1
  br label %58

; <label>:66		; preds = %68
  %67 = sub i32 %43, 1
  br label %58

; <label>:68      		; preds = %62
  br i1 false, label %66, label %64

; <label>:69   		; preds = %split13, %76
  %70 = phi i32 [ 0, %0 ], [ %77, %76 ]
  %71 = phi i32 [ %p4, %0 ], [ %107, %76 ]
  %72 = phi i32 [ %p3, %0 ], [ %109, %76 ]
  %73 = phi i32 [ %p2, %0 ], [ %108, %76 ]
  %74 = phi i32 [ %p1, %0 ], [ %113, %76 ]
  %75 = icmp slt i32 %70, 10
  br i1 %75, label %112, label %237

; <label>:76		; preds = %106
  %77 = add i32 %70, 1
  br label %69

; <label>:78  		; preds = %85, %split14
  %79 = phi i32 [ 0, %0 ], [ %86, %85 ]
  %80 = phi i32 [ %p4, %0 ], [ %88, %85 ]
  %81 = phi i32 [ %p3, %0 ], [ %90, %85 ]
  %82 = phi i32 [ %p2, %0 ], [ %89, %85 ]
  %83 = phi i32 [ %p1, %0 ], [ %94, %85 ]
  %84 = icmp slt i32 %79, 10
  br i1 %84, label %93, label %237

; <label>:85		; preds = %87
  %86 = add i32 %79, 1
  br label %78

; <label>:87         		; preds = %91, %93
  %88 = phi i32 [ %92, %91 ], [ %80, %93 ]
  %89 = phi i32 [ %100, %91 ], [ %82, %93 ]
  %90 = add i32 %81, 1
  br label %85

; <label>:91        		; preds = %95, %97
  %92 = phi i32 [ %98, %97 ], [ %96, %95 ]
  br label %87

; <label>:93     		; preds = %78
  %94 = add i32 %83, 1
  br i1 true, label %99, label %87

; <label>:95          		; preds = %103, %101
  %96 = phi i32 [ %104, %103 ], [ %102, %101 ]
  br label %91

; <label>:97		; preds = %99
  %98 = add i32 %80, 1
  br label %91

; <label>:99       		; preds = %93
  %100 = add i32 %82, 1
  br i1 false, label %97, label %105

; <label>:101		; preds = %105
  %102 = add i32 %80, 1
  br label %95

; <label>:103		; preds = %105
  %104 = sub i32 %80, 1
  br label %95

; <label>:105       		; preds = %99
  br i1 false, label %103, label %101

; <label>:106         		; preds = %110, %112
  %107 = phi i32 [ %111, %110 ], [ %71, %112 ]
  %108 = phi i32 [ %119, %110 ], [ %73, %112 ]
  %109 = add i32 %72, 1
  br label %76

; <label>:110          		; preds = %114, %116
  %111 = phi i32 [ %117, %116 ], [ %115, %114 ]
  br label %106

; <label>:112      		; preds = %69
  %113 = add i32 %74, 1
  br i1 true, label %118, label %106

; <label>:114          		; preds = %120, %122
  %115 = phi i32 [ %123, %122 ], [ %121, %120 ]
  br label %110

; <label>:116		; preds = %118
  %117 = add i32 %71, 1
  br label %110

; <label>:118      		; preds = %112
  %119 = add i32 %73, 1
  br i1 false, label %116, label %124

; <label>:120		; preds = %124
  %121 = add i32 %71, 1
  br label %114

; <label>:122		; preds = %124
  %123 = sub i32 %71, 1
  br label %114

; <label>:124     		; preds = %118
  br i1 true, label %122, label %120

; <label>:125   		; preds = %split11, %132
  %126 = phi i32 [ 0, %0 ], [ %133, %132 ]
  %127 = phi i32 [ %p4, %0 ], [ %219, %132 ]
  %128 = phi i32 [ %p3, %0 ], [ %221, %132 ]
  %129 = phi i32 [ %p2, %0 ], [ %220, %132 ]
  %130 = phi i32 [ %p1, %0 ], [ %225, %132 ]
  %131 = icmp slt i32 %126, 10
  br i1 %131, label %224, label %237

; <label>:132		; preds = %218
  %133 = add i32 %126, 1
  br label %125

; <label>:134   		; preds = %141, %split12
  %135 = phi i32 [ 0, %0 ], [ %142, %141 ]
  %136 = phi i32 [ %p4, %0 ], [ %144, %141 ]
  %137 = phi i32 [ %p3, %0 ], [ %146, %141 ]
  %138 = phi i32 [ %p2, %0 ], [ %145, %141 ]
  %139 = phi i32 [ %p1, %0 ], [ %150, %141 ]
  %140 = icmp slt i32 %135, 10
  br i1 %140, label %149, label %237

; <label>:141		; preds = %143
  %142 = add i32 %135, 1
  br label %134

; <label>:143          		; preds = %147, %149
  %144 = phi i32 [ %148, %147 ], [ %136, %149 ]
  %145 = phi i32 [ %161, %147 ], [ %138, %149 ]
  %146 = add i32 %137, 1
  br label %141

; <label>:147          		; preds = %153, %151
  %148 = phi i32 [ %152, %151 ], [ %154, %153 ]
  br label %143

; <label>:149      		; preds = %134
  %150 = add i32 %139, 1
  br i1 false, label %160, label %143

; <label>:151		; preds = %160
  %152 = add i32 %136, 1
  br label %147

; <label>:153          		; preds = %155, %157
  %154 = phi i32 [ %158, %157 ], [ %156, %155 ]
  br label %147

; <label>:155		; preds = %159
  %156 = add i32 %136, 1
  br label %153

; <label>:157		; preds = %159
  %158 = sub i32 %136, 1
  br label %153

; <label>:159      		; preds = %160
  br i1 false, label %157, label %155

; <label>:160     		; preds = %149
  %161 = add i32 %138, 1
  br i1 true, label %151, label %159

; <label>:162    		; preds = %split9, %169
  %163 = phi i32 [ 0, %0 ], [ %170, %169 ]
  %164 = phi i32 [ %p4, %0 ], [ %200, %169 ]
  %165 = phi i32 [ %p3, %0 ], [ %202, %169 ]
  %166 = phi i32 [ %p2, %0 ], [ %201, %169 ]
  %167 = phi i32 [ %p1, %0 ], [ %206, %169 ]
  %168 = icmp slt i32 %163, 10
  br i1 %168, label %205, label %237

; <label>:169		; preds = %199
  %170 = add i32 %163, 1
  br label %162

; <label>:171   		; preds = %178, %split10
  %172 = phi i32 [ 0, %0 ], [ %179, %178 ]
  %173 = phi i32 [ %p4, %0 ], [ %181, %178 ]
  %174 = phi i32 [ %p3, %0 ], [ %183, %178 ]
  %175 = phi i32 [ %p2, %0 ], [ %182, %178 ]
  %176 = phi i32 [ %p1, %0 ], [ %187, %178 ]
  %177 = icmp slt i32 %172, 10
  br i1 %177, label %186, label %237

; <label>:178		; preds = %180
  %179 = add i32 %172, 1
  br label %171

; <label>:180          		; preds = %184, %186
  %181 = phi i32 [ %185, %184 ], [ %173, %186 ]
  %182 = phi i32 [ %198, %184 ], [ %175, %186 ]
  %183 = add i32 %174, 1
  br label %178

; <label>:184          		; preds = %190, %188
  %185 = phi i32 [ %189, %188 ], [ %191, %190 ]
  br label %180

; <label>:186      		; preds = %171
  %187 = add i32 %176, 1
  br i1 false, label %197, label %180

; <label>:188		; preds = %197
  %189 = add i32 %173, 1
  br label %184

; <label>:190          		; preds = %192, %194
  %191 = phi i32 [ %195, %194 ], [ %193, %192 ]
  br label %184

; <label>:192		; preds = %196
  %193 = add i32 %173, 1
  br label %190

; <label>:194		; preds = %196
  %195 = sub i32 %173, 1
  br label %190

; <label>:196      		; preds = %197
  br i1 false, label %194, label %192

; <label>:197      		; preds = %186
  %198 = add i32 %175, 1
  br i1 false, label %188, label %196

; <label>:199          		; preds = %203, %205
  %200 = phi i32 [ %204, %203 ], [ %164, %205 ]
  %201 = phi i32 [ %217, %203 ], [ %166, %205 ]
  %202 = add i32 %165, 1
  br label %169

; <label>:203          		; preds = %207, %209
  %204 = phi i32 [ %208, %207 ], [ %210, %209 ]
  br label %199

; <label>:205      		; preds = %162
  %206 = add i32 %167, 1
  br i1 false, label %216, label %199

; <label>:207		; preds = %216
  %208 = add i32 %164, 1
  br label %203

; <label>:209          		; preds = %211, %213
  %210 = phi i32 [ %214, %213 ], [ %212, %211 ]
  br label %203

; <label>:211		; preds = %215
  %212 = add i32 %164, 1
  br label %209

; <label>:213		; preds = %215
  %214 = sub i32 %164, 1
  br label %209

; <label>:215     		; preds = %216
  br i1 true, label %213, label %211

; <label>:216      		; preds = %205
  %217 = add i32 %166, 1
  br i1 false, label %207, label %215

; <label>:218          		; preds = %222, %224
  %219 = phi i32 [ %223, %222 ], [ %127, %224 ]
  %220 = phi i32 [ %231, %222 ], [ %129, %224 ]
  %221 = add i32 %128, 1
  br label %132

; <label>:222          		; preds = %228, %226
  %223 = phi i32 [ %229, %228 ], [ %227, %226 ]
  br label %218

; <label>:224      		; preds = %125
  %225 = add i32 %130, 1
  br i1 false, label %230, label %218

; <label>:226          		; preds = %232, %234
  %227 = phi i32 [ %235, %234 ], [ %233, %232 ]
  br label %222

; <label>:228		; preds = %230
  %229 = add i32 %127, 1
  br label %222

; <label>:230     		; preds = %224
  %231 = add i32 %129, 1
  br i1 true, label %228, label %236

; <label>:232		; preds = %236
  %233 = add i32 %127, 1
  br label %226

; <label>:234		; preds = %236
  %235 = sub i32 %127, 1
  br label %226

; <label>:236     		; preds = %230
  br i1 true, label %234, label %232

; <label>:237		; preds = %78, %134, %171, %162, %41, %69, %125, %22
  ret void
}