#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

struct student
{
    int id;
    float cgpa;
    char *name;
};

int main()
{
    int n = 7;
    struct student *d = (struct student *)(malloc(sizeof(int *) * n));
    int i;
    float cgpa[] = {1.2f, 2.2f, 1.3f, .7f, 5.4f, 2.3f, .9f};
    char *names[] = {"ram", "tina", "tom", "sam", "tom", "david", "harry"};
    for (i = 0; i < n; i++)
    {
        struct student *s = (struct student *)(malloc(sizeof(int *)));
        s->id = rand() % 100;
        s->cgpa = cgpa[i];
        s->name = names[i];
        d[i] = *s;
    }
    for (int i = 0; i < n; i++)
    {
        printf("%d:%s:%.2f, ", d[i].id, d[i].name, d[i].cgpa);
    }
    printf("\n");
}