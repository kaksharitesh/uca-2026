#include <stdio.h>
#include <stdlib.h>
#define MAX_SIZE 3

typedef struct
{
    int a[MAX_SIZE];
    int top;
} Stack;

void initialize(Stack *s)
{
    s->top = -1;
}

int is_empty(Stack *s)
{
    return s->top == -1;
}

int size(Stack *s)
{
    return s->top + 1;
}

void push(Stack *s, int key)
{
    printf("inserting %d\n", key);
    if (size(s) == MAX_SIZE)
    {
        printf("Overflow\n");
        return;
    }
    s->a[++s->top] = key;
}

int pop(Stack *s)
{
    if (is_empty(s))
    {
        printf("Underflow\n");
        return -1;
    }
    return s->a[s->top--];
}

int main()
{
    Stack stack;
    initialize(&stack);
    printf("size = %d\n", size(&stack));
    printf("is empty = %d\n", is_empty(&stack));
    push(&stack, 1);
    push(&stack, 2);
    push(&stack, 3);
    push(&stack, 4);
    printf("size = %d\n", size(&stack));
    printf("is empty = %d\n", is_empty(&stack));
    printf("element popped %d\n", pop(&stack));
    printf("element popped %d\n", pop(&stack));
    printf("element popped %d\n", pop(&stack));
    printf("element popped %d\n", pop(&stack));
    printf("size = %d\n", size(&stack));
    printf("is empty = %d\n", is_empty(&stack));
}
