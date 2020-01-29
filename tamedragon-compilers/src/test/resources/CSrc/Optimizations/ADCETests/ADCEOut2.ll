define i32 @bar(i32* %a, i32 %b) nounwind {
  store i32 10, i32* %a, align 4
  ret i32 %b
}
