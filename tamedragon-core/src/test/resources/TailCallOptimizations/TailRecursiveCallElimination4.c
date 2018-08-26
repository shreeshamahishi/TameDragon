int fact(int curNum, int sum) {
    if(curNum == 1) {
        return sum;
    } else {
        return fact(curNum - 1, sum*curNum);
    }
}