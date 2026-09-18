#include <stdio.h>
#include <stdlib.h>
#define MAX_SIZE 3

typedef struct
{
    int a[MAX_SIZE];
    int front;
    int rear;
    int size;
} Queue;

void initialize(Queue *s)
{
    s->front = 0;
    s->rear = 0;
    s->size = 0;
}

int is_empty(Queue *s)
{
    return s->size == 0;
}

int size(Queue *s)
{
    return s->size;
}

void push(Queue *s, int key)
{
    printf("inserting %d\n", key);
    if (size(s) == MAX_SIZE)
    {
        printf("Overflow\n");
        return;
    }
    s->a[s->front] = key;
    s->front = (s->front + 1) % MAX_SIZE;
    s->size++;
}

int pop(Queue *s)
{
    if (is_empty(s))
    {
        printf("Underflow\n");
        return -1;
    }
    int result = s->a[s->rear];
    s->rear = (s->rear + 1) % MAX_SIZE;
    s->size--;
    return result;
}

int main()
{
    Queue Queue;
    initialize(&Queue);
    printf("size = %d\n", size(&Queue));
    printf("is empty = %d\n", is_empty(&Queue));
    push(&Queue, 1);
    push(&Queue, 2);
    push(&Queue, 3);
    push(&Queue, 4);
    printf("size = %d\n", size(&Queue));
    printf("is empty = %d\n", is_empty(&Queue));
    printf("element popped %d\n", pop(&Queue));
    printf("element popped %d\n", pop(&Queue));
    printf("element popped %d\n", pop(&Queue));
    printf("element popped %d\n", pop(&Queue));
    printf("size = %d\n", size(&Queue));
    printf("is empty = %d\n", is_empty(&Queue));

    push(&Queue, 4);
    printf("element popped %d\n", pop(&Queue));
}
