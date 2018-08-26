int fun(int x)
{
 struct Student
 {
 int mark;
 double percentage;
 }a,b,c;

 a.mark = 20;
 a.percentage = 50;
 b.mark = 20;
 b.percentage = 50;
 
 if(x== 10)
  c= a;
 else
  c = b;
 return c.mark;

}