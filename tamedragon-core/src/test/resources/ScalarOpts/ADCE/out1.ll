; ModuleID = 'ADCESrc1.bc'
source_filename = "ADCESrc1.bc"
; Function Attrs: norecurse nounwind readnone uwtable
define i32 @foo(i32 %m, i32 %n) local_unnamed_addr #0 {
.loopexit:
  %0 = add i32 %n, %m
  %.inv = icmp slt i32 %0, 21
  %x.1 = select i1 %.inv, i32 81, i32 21
  ret i32 %x.1
}

attributes #0 = { norecurse nounwind readnone uwtable }
