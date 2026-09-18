#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 3

typedef struct node
{
    int value;
    struct node *next;
} Node;

int isEmpty(Node *n)
{
    return n == NULL;
}

int size(Node *n)
{
    Node *temp = n;
    int count = 0;
    while (temp != NULL)
    {
        count++;
        temp = temp->next;
    }
    return count;
}

void push(Node **head, int key)
{
    printf("inserting %d\n", key);

    Node *node = malloc(sizeof(Node));

    node->value = key;
    node->next = NULL;

    if (*head == NULL)
    {
        *head = node;
        return;
    }

    Node *temp = *head;
    while (temp->next != NULL)
    {
        temp = temp->next;
    }

    temp->next = node;
}

void print(Node *n)
{
    Node *temp = n;
    while (temp != NULL)
    {
        printf("%d->", temp->value);
        temp = temp->next;
    }
    printf("NULL\n");
}

Node *reverse(Node *n)
{
    if (n->next == NULL)
        return n;
    Node *h1 = reverse(n->next);
    n->next->next = n;
    n->next = NULL;
    return h1;
}

int main(void)
{
    Node *head = NULL;
    push(&head, 1);
    push(&head, 2);
    push(&head, 3);
    push(&head, 4);
    push(&head, 5);
    print(head);

    head = reverse(head);
    print(head);
    return 0;
}