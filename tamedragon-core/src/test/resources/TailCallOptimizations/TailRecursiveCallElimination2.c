void doll ( int size )
{
  if ( size == 0 )   
    return;          
                     
  doll ( size - 1 ); 
}
