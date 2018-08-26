

#define getc(__f)   ((--((__f)->level) >= 0) ? (unsigned char)(*(__f)->curp++) :     _fgetc (__f))

#define putc(__c,__f)   ((++((__f)->level) < 0) ? (unsigned char)(*(__f)->curp++=(__c)) :     _fputc (((char)(__c)),__f))

#define getchar()   getc(stdin)
