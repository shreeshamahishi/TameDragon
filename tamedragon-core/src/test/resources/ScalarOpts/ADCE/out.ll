; ModuleID = 'D:\Shreesha\work\TameDragon\TameDragon\tamedragon-core\src\test\resources\ScalarOpts\ADCE\ADCESrc1.bc'
source_filename = "D:\\Shreesha\\work\\TameDragon\\TameDragon\\tamedragon-core\\src\\test\\resources\\ScalarOpts\\ADCE\\ADCESrc1.bc"
; Function Attrs: nounwind uwtable
define i32 @foo(i32 %m, i32 %n) #0 {
  %1 = add i32 %m, %n
  %2 = icmp sgt i32 %1, 20
  br i1 %2, label %3, label %5

3:                                                ; preds = %0
  %4 = add i32 34, 10
  br label %13

5:                                                ; preds = %0
  br label %6

6:                                                ; preds = %10, %5
  %x.0 = phi i32 [ 21, %5 ], [ %9, %10 ]
  %j.0 = phi i32 [ 0, %5 ], [ %11, %10 ]
  %7 = icmp slt i32 %j.0, 30
  br i1 %7, label %8, label %12

8:                                                ; preds = %6
  %9 = add i32 %x.0, 2
  br label %10

10:                                               ; preds = %8
  %11 = add i32 %j.0, 1
  br label %6

12:                                               ; preds = %6
  br label %13

13:                                               ; preds = %12, %3
  %x.1 = phi i32 [ 21, %3 ], [ %x.0, %12 ]
  ret i32 %x.1
}

attributes #0 = { nounwind uwtable }
