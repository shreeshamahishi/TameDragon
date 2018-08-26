void foo(){
  int a = 10;
  for(a = 0; a < 10; a++){
     if(a < 10)
       a++;
     else if(a > 10)
       a--;
     else
       ++a;
  }
}
