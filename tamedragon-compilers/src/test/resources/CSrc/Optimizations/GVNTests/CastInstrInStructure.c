int fun(int x)
{
 struct Student
 {
 int mark;
 double percentage;
 }a,b,c;

 a.mark = 20;
 a.percentage = 50;
 b = a;
 
 if(x== 10)
  c= a;
 else
  c = b;
 return c.mark;

}