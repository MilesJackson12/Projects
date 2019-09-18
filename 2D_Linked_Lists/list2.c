#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "assignment3.h"


int compare (const void * a, const void * b) { 
   return ( *(int*)a - *(int*)b );
}
//compare function taken from:
// https://www.tutorialspoint.com/c_standard_library/c_function_qsort.htm

void randomNumbers(int * a, int m){
	
	int i;
	for (i=0; i<m; i++){
		a[i] = rand()%200;
	}
	
	qsort(a, m, sizeof(int), compare); 

}

void printSLlist(SLnodePtr ptr){
	if (ptr != NULL){
		int a = ptr->key;
		printf("%d\n", a);
		
		printSLlist(ptr->next);
	}
}

void printSLlistForHB(SLnodePtr ptr){
	if (ptr != NULL){
		int a = ptr->key;
		printf("%d ", a);
		
		printSLlist(ptr->next);
	}
}

SLnodePtr createSLlist(SLnodePtr ptr, int m){
	ptr = NULL;
	int numbers[m];
	randomNumbers(numbers, m);

	for (int i = m-1; i>=0; i--){
		SLnodePtr new;
		new = malloc(sizeof(struct SLnode));
		new->key = numbers[i];
		new->next = ptr;
		ptr = new;
	}
	return ptr;
}

SLnodePtr mergeSLlist(SLnodePtr ptr1, SLnodePtr ptr2, SLnodePtr ptr, int first){ //merge ptr2 into ptr
	if (first == 1){
	
		if (first == 1 && ptr1 != NULL && ptr2 != NULL && (ptr1->key > ptr2->key)){
			SLnodePtr temp = ptr2->next;
			ptr2->next = ptr1;
			ptr1 = ptr2;
			ptr = ptr1;
			ptr2 = temp;
			ptr = mergeSLlist(ptr1, ptr2, ptr, 0);
		}
		
		ptr = ptr1;
		ptr = mergeSLlist(ptr1, ptr2, ptr, 0);
	}
	else if (ptr1->next != NULL && ptr2 != NULL && (ptr1->next->key >= ptr2->key)){
		if (ptr2->next != NULL){
			SLnodePtr temp = ptr2->next;
			ptr2->next = ptr1->next;
			ptr1->next = ptr2;
			ptr2 = temp;
			ptr = mergeSLlist(ptr1, ptr2, ptr, 0);
		}
		else{
			ptr2->next = ptr1->next;
			ptr1->next = ptr2;
		}
	}
	
	else if (ptr1->next != NULL && ptr2 != NULL && (ptr1->next->key < ptr2->key)){
		ptr = mergeSLlist(ptr1->next, ptr2, ptr, 0);
	}
	
	else if (ptr1->next == NULL && ptr2 != NULL){
		ptr1->next = ptr2;
	}
	return ptr;

}

HBnodePtr createHBlist(int m, int n){

	srand(time(NULL));
	
	HBnodePtr ptr = NULL;
	SLnodePtr a[m];

	for (int i = 0; i<m; i++){
		int numSL = rand()%(n-1) + 1;
		a[i] = createSLlist(a[i], numSL);
	}
	
	for (int i = 0; i<m; i++){
		for (int j = i+1; j<m; j++){
			if (a[i]->key > a[j]->key){
				SLnodePtr temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
	
	for (int i = m-1; i>=0; i--){
		HBnodePtr new;
		new = malloc(sizeof(struct HBnode));
		new->key = a[i]->key;
		new->bottom = a[i]->next;
		new->next = ptr;
		ptr = new;
	}
	
	
	return ptr;
}

SLnodePtr takeCopySL(SLnodePtr ptr1, SLnodePtr ptr2, int first){
	
	
	if (first == 1 && ptr2 != NULL){
		SLnodePtr new;
		new = malloc(sizeof(struct SLnode));
		new->key = ptr2->key;
		new->next = NULL;
		ptr1 = new;
		
		ptr1 = takeCopySL(ptr1, ptr2->next, 0);
	}
	
	else if (ptr2 != NULL){
		
		SLnodePtr temp = ptr1;
			
		while(temp->next != NULL){
			temp = temp->next;
		}
		
		SLnodePtr new;
		new = malloc(sizeof(struct SLnode));
		new->key = ptr2->key;
		new->next = NULL;
		temp->next = new;
		
		ptr1 = takeCopySL(ptr1, ptr2->next, 0);
	}
	else if (first == 1 && ptr2 == NULL){
		return NULL;
	}
	return ptr1;
}

SLnodePtr initialHBintoSL(const HBnodePtr HBptr){
	SLnodePtr SLptr = NULL;
	
	if(HBptr != NULL){
		SLptr = malloc(sizeof(struct SLnode));
		SLptr->key = HBptr->key;
		SLnodePtr ptr2;
		SLptr->next = takeCopySL(ptr2, HBptr->bottom, 1);
	}
	
	return SLptr;
}

SLnodePtr makeHBintoSL(SLnodePtr SLptr, const HBnodePtr HBptr){

	SLnodePtr SLptr2 = NULL;

	if(HBptr != NULL){
		SLptr2 = malloc(sizeof(struct SLnode));
		SLptr2->key = HBptr->key;
		SLnodePtr ptr2;
		SLptr2->next = takeCopySL(ptr2, HBptr->bottom, 1);
		
		SLnodePtr SLptr1 = SLptr;
		SLptr = mergeSLlist(SLptr1, SLptr2, SLptr, 1);
		SLptr = makeHBintoSL(SLptr, HBptr->next);
	}
	
	return SLptr;
}


void printHBlist(HBnodePtr ptr){
	if (ptr != NULL){
		int a = ptr->key;
		printf("%d ->\n", a);	
		if (ptr->bottom != NULL){	
			printSLlist(ptr->bottom);
			printf("\n");
		}
		else{
			printf("\n");
		}
		printHBlist(ptr->next);
	}
}

SLnodePtr flattenList(const HBnodePtr L){

	SLnodePtr SLptr = initialHBintoSL(L);
	
	SLnodePtr SLptrfinal;
	SLptrfinal = makeHBintoSL(SLptr, L->next);
	
	return SLptrfinal;
	
}
void freeSLlist(SLnodePtr ptr){
	if (ptr != NULL){
		if (ptr->next != NULL){
			SLnodePtr temp = ptr->next;
			freeSLlist(temp);
		}
		free(ptr);
	}
}

void freeHBlist(HBnodePtr ptr){
	if (ptr != NULL){
		if (ptr->bottom != NULL){
			freeSLlist(ptr->bottom);
		}
		HBnodePtr temp = ptr->next;
		free(ptr);
		freeHBlist(temp);
	}
}
int main ()
{
int n = 5 , m = 5;
HBnodePtr L = createHBlist(n , m ) ;
printf ( "HB list \n" ) ;
printHBlist( L ) ;
printf ( "\n" ) ;
printf ( "SL list \n" ) ;
SLnodePtr P = flattenList(L) ;
printSLlist(P) ;
printf( "\n " ) ;
freeSLlist(P) ;
freeHBlist(L);
return 0;
}