%struct.techno = type { i32, [4 x i8] }

@tch = global %struct.techno { i32 1, [4 x i8] c"abc\00" }, align 4
