#include<stdio.h>

int size = 0;

void swap(int a[], int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

int sizeOf(){
	return size;
}

void swim(int heap[], int i){
	if(i == 0) return;
	int p = i/2;
	if(heap[p] < heap[i]){
		swap(heap, p, i);
		swim(heap, p);
	}
}

void insert(int heap[], int key){
    printf("insering %d\n", key);
	heap[size] = key;
	swim(heap, size);
	size++;
}


int get_max(int heap[]){
	return heap[0];
}


void sink(int heap[], int i){
	if(i>= size) return;
	int L = 2*i;
	int R = 2*i+1;
	int max = i;
	if(L < size && heap[L] > heap[max]){
		max = L;
	}
	if(R < size && heap[R] > heap[max]){
		max = R;
	}
	if(max != i){
		swap(heap, i, max);
		sink(heap, max);
	}

}

int del_max(int heap[]){
	if (sizeOf() == 0) {
		printf("underflow - i dont have any elememnt");
		return -1;
	}
	int res = get_max(heap);  // heap[0]
	swap(heap, 0, size-1);
	size--;
	sink(heap, 0);

	return res;
}



void print(int a[]) {
	int i;
	for (i = 0; i < size; i++) {
		printf("%d ", a[i]);
	}
	printf("\n");
}


int main(){

	int heap[10];
	insert(heap, 5);
    print(heap);
	insert(heap, 4);
    print(heap);
	insert(heap, 8);
    print(heap);
	insert(heap, 9);
    print(heap);
	insert(heap, 1);
    print(heap);
	insert(heap, 2);
    print(heap);

	printf("size = %d\n", sizeOf()); // 6

	int max = get_max(heap); // 1
    printf("current max is %d\n", max);


	del_max(heap);

	max = get_max(heap); // 2

	printf("size = %d\n", sizeOf()); // 5

    printf("current max is %d\n", max);

    print(heap);

}
