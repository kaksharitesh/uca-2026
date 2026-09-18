#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>

void generateRandom(int *a, int s)
{
    int i;
    for (i = 0; i < s; i++)
    {
        *(a + i) = rand() % s + 1;
    }
}

void swap(void *a, int i, int j, int sz)
{
    char *temp = malloc(sizeof(char *));
    char *t1 = (char *)(a + i * sz);
    char *t2 = (char *)(a + j * sz);
    memcpy(temp, t1, sz);
    memcpy(t1, t2, sz);
    memcpy(t2, temp, sz);
}

void quick_sort(void *a, int L, int R, int (*cmp)(void *, void *), int sz)
{
    if (L >= R)
        return;
    int p = L;
    int x = L;
    int i = L + 1;
    for (; i <= R; i++)
    {
        if (cmp(a + i * sz, a + p * sz) < 0)
        {
            swap(a, i, ++x, sz);
        }
    }
    swap(a, x, p, sz);
    quick_sort(a, L, x - 1, cmp, sz);
    quick_sort(a, x + 1, R, cmp, sz);
}

void print(int *a, int s)
{
    int i;
    for (i = 0; i < s; i++)
    {
        printf("%d ", *(a + i));
    }
    printf("\n");
}

struct student
{
    int id;
    float cgpa;
    char *name;
};

int intCmparator(void *i, void *j)
{
    int x = *(int *)i;
    int y = *(int *)j;
    if (x == y)
        return 0;
    if (x < y)
        return -1;
    return +1;
}
//   float * = 120, 130    void *i = 120  void *j = 130
int floatCmparator(void *i, void *j)
{
    float x = *(float *)i;
    float y = *(float *)j;
    if (x == y)
        return 0;
    if (x < y)
        return -1;
    return +1;
}

int studentIdCmp(void *i, void *j)
{
    struct student *x = (struct student *)(i);
    struct student *y = (struct student *)(j);
    return x->id - y->id;
}

int cgpaCmp(void *i, void *j)
{
    struct student *s1 = (struct student *)(i);
    struct student *s2 = (struct student *)(j);
    float x = s1->cgpa;
    float y = s2->cgpa;
    if (x == y)
        return 0;
    if (x < y)
        return -1;
    return +1;
}

int main()
{
    int size = 8;
    int a[size];

    generateRandom(a, size);
    print(a, size);

    quick_sort(a, 0, size - 1, intCmparator, sizeof(a[0]));

    print(a, size);

    float f[6] = {1.2f, 3.4f, .7f, .8f, .4f, .3f};
     int i;
    for (i = 0; i < 6; i++)
    {
        printf("%.2f ", f[i]);
    }
    printf("\n");

    quick_sort(f, 0, 5, floatCmparator, sizeof(f[0]));
    for (i = 0; i < 6; i++)
    {
        printf("%.2f ", f[i]);
    }
    printf("\n");

    int n = 7;
    struct student *d = (struct student *)(malloc(sizeof(struct student *) * n));

    float cgpa[] = {1.2f, 2.2f, 1.3f, .7f, 5.4f, 2.3f, .9f};
    char *names[] = {"ram", "tina", "tom", "sam", "tom", "david", "harry"};
    for (i = 0; i < n; i++)
    {
        struct student *s = (struct student *)(malloc(sizeof(struct student *)));
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
    quick_sort(d, 0, 6, cgpaCmp, sizeof(d[0]));

    for (int i = 0; i < n; i++)
    {
        printf("%d:%s:%.2f, ", d[i].id, d[i].name, d[i].cgpa);
    }
    printf("\n");

    return 0;
}