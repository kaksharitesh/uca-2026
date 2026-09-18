#include<stdio.h>

int heap_size = 0;

void print(int a[], int n) {
	int i;
	for (i = 0; i < n; i++) {
		printf("%d ", a[i]);
	}
	printf("\n");
}

void swap(int a[], int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

void sink(int heap[], int i){
	if(i>= heap_size) return;
	int L = 2*i;
	int R = 2*i+1;
	int max = i;
	if(L < heap_size && heap[L] > heap[max]){
		max = L;
	}
	if(R < heap_size && heap[R] > heap[max]){
		max = R;
	}
	if(max != i){
		swap(heap, i, max);
		sink(heap, max);
	}

}

void del_max(int heap[]){
	swap(heap, 0, heap_size-1);
	heap_size--;
	sink(heap, 0);
}



void heap_sort(int a[], int n){
	int i;
	heap_size = n;
	for(i=n/2;i>=0;i--){
		sink(a, i);
	}
	for(i=0;i<n;i++){
		del_max(a);
	}
}

int main(){
	int a[] = {5,4,1,2,3,9,8,7,0};
	int n = 9;
	printf("Input       : ");
	print(a,n);
	heap_sort(a, n);
	printf("Sorted      : ");
	print(a,n);
}

