; ModuleID = 'D:\Shreesha\work\TameDragon\TameDragon\tamedragon-core\src\test\resources\Analysis\TestDebug1.ll'
source_filename = "D:\\Shreesha\\work\\TameDragon\\TameDragon\\tamedragon-core\\src\\test\\resources\\Analysis\\TestDebug1.ll"
; Function Attrs: nounwind uwtable
define i32 @foo(i32 %val1, i32 %val2, i32 %val3) #0 {
  %1 = add i32 %val1, 14
  %2 = sub i32 %1, %val1
  %3 = sdiv i32 %2, 7
  ret i32 %3
}

attributes #0 = { nounwind uwtable }
