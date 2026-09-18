#include <stdio.h>

int main()
{
    /*
     char d = 11;
     printf("address = %p\n", &d);
     printf("address = %ld\n", &d);

     printf("double %ld\n", (long)&d % 8); //0or4
      printf("double %ld\n", (long)&d % 4); //0
      */

    struct xyz
    {
        char c;
        double d;
        char e;
    };

    struct abc
    {
        struct xyz a[2];
        int i;
    };

    struct abc2
    {
        int a[3];
        char i;
    };

    printf("size of xyz = %ld\n", sizeof(struct xyz));
    printf("size of abc = %ld\n", sizeof(struct abc));
    printf("size of abc2 = %ld\n", sizeof(struct abc2));
}