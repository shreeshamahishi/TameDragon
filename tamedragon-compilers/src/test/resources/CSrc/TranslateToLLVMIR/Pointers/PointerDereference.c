int foo(int *ptrX, int *ptrY)
{
 int a = 40, *p1, *p2, *p3, *p4, result;

 p1 = ptrX;
 p2 = ptrX;

 p3 = ptrY;
 p4 = &a;

 result =  *p1 + *p2;
 return result;
}
