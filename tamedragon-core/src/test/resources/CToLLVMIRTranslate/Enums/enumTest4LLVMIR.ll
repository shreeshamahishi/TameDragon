%enum.DAY = type i32

@weekdays = common global i32 0, align 4

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %today = alloca i32, align 4
  %day_value = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 3, i32* %today, align 4
  store i32 3, i32* %day_value, align 4
  %2 = load i32, i32* %day_value, align 4
  %3 = sub i32 %2, 1
  store i32 %3, i32* @weekdays, align 4
  %4 = load i32, i32* %1, align 4
  ret i32 %4
}
