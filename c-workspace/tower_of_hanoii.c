#include<stdio.h>

int toh(int n, char F, char T, char H){

	if(n==0) return 0;

	toh(n-1, F, H, T);
	printf("%c-%c\n", F, T);
	toh(n-1,H, T, F);
    return 0;

}



int main(){
	toh(4, 'A', 'C', 'B');
}
