void foo(){
  int a = 10;
  for(a = 0; a < 10; a++){
     if(a < 10)
       break;
     else if(a > 10)
       continue;
     else if(a == 10)
       return;
     else
       a++;
  }
}
