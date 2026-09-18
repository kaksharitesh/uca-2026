#include<stdio.h>
#include<stdlib.h>
#include<sys/time.h>

int heap_size = 0;

void generateRandom(int a[], int s) {
	int i;
	for (i = 0; i < s; i++) {
		a[i] = rand() %s + 1;
	}
}

void generateAscending(int a[], int s) {
	int i;
	for (i = 0; i < s; i++) {
		a[i] = i;
	}
}

void generateDescending(int a[], int s) {
	int i;
	for (i = 0; i < s; i++) {
		a[i] = s - i;
	}
}

void swap(int a[], int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

int merge(int a[], int L, int mid, int R, int aux[]){
	int k = L;
	int i = L;
	int j = mid+1;
	int c = 0;
	while(k<=R){
		if(i>mid) aux[k++] = a[j++];
		else if(j>R) aux[k++] = a[i++];
		else if(a[i] <= a[j]) {
			aux[k++] = a[i++];
			
		}
		else { 
			aux[k++] = a[j++];
			c += mid-i+1;
		}

	}
	k = L;
	while(k <= R){
		a[k] = aux[k];
		k++;
	}
	return c;

}

int merge_sort(int a[], int L, int R, int aux[]){
	if(L >= R) return 0;
	int mid = (L+R)/2;
	int c = 0;
	c += merge_sort(a, L, mid, aux);
	c += merge_sort(a, mid+1, R, aux);
	c += merge(a, L, mid, R, aux);
	return c;

}

void  quick_sort(int a[], int L, int R) {
	if(L>=R) return;
	int p = L;
	int x = L;
	int i = L+1;
	for(;i<=R;i++){
		if(a[i] < a[p]){
			swap(a, i, ++x);
		}
	}
	swap(a, x, p);
	quick_sort(a, L, x-1);
	quick_sort(a, x+1, R);
}

void print(int a[], int s) {
	int i;
	for (i = 0; i < s; i++) {
		printf("%d ", a[i]);
	}
	printf("\n");
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
    struct timeval te;
    int size = 8000;
    int step = 4000;
    int i;
    for (i = 0; i < 10; i++) {
        //int a[] = {5,4,1,2,3};
		int a[size]; 

        //generateRandom(a, size);
		//generateAscending(a,size);
		generateDescending(a,size);
		//a = {3,2,1,4};
    	//print(a, size);
        
		gettimeofday(&te, NULL);
        long long start = te.tv_sec * 1000LL + te.tv_usec / 1000;


        heap_sort(a, size-1);
		//printf("inversion count = %d \n", 99999999);

        gettimeofday(&te, NULL);
		long long end = te.tv_sec * 1000LL + te.tv_usec / 1000;

		printf("%lld\n", end - start);
        
		//print(a, size);
        size += step;
    }
	printf("\n");
	return 0;
}
