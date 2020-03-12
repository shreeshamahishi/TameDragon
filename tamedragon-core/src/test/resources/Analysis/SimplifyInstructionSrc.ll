; ModuleID = 'SimplifyInstructionRaw.ll'
source_filename = "SimplifyInstructionRaw.ll"
; Function Attrs: noinline nounwind uwtable
define dso_local i32 @foo(i32 %max, i32 %min, i32 %val) #0 {
entry:
  %add = add i32 40, 45
  %sub = sub i32 %max, 40
  %add1 = add i32 40, %sub
  %add2 = add i32 %add1, %val
  ret i32 %add2
}

attributes #0 = { noinline nounwind uwtable }
