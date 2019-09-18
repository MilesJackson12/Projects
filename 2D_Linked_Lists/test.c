#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct SLnode{
int key ;
struct SLnode * next ; /* pointer to the next item in a list */
};

typedef struct SLnode * SLnodePtr ;

/* Node in the " horizontal " list */
struct HBnode {
int key;
struct HBnode * next; /* pointer to the next item in the
horizontal list */
SLnodePtr bottom; /* pointer to the bottom list */
};

typedef struct HBnode * HBnodePtr ;

int compare (const void * a, const void * b) { //taken from *insert source
   return ( *(int*)a - *(int*)b );
}

void randomNumbers(int * a, int m){
	
	int i;
	for (i=0; i<m; i++){
		a[i] = rand()%200; // store m amount of random integers in a
	}
	
	qsort(a, m, sizeof(int), compare); // sort a

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
int main(){
	
	int m = 5;
	int n = 5;
	
	// SLnodePtr ptr = createSLlist(ptr, 5);
// 	printSLlist(ptr);
// 	
// 	printf("\n");
// 	
// 	SLnodePtr ptr2 = takeCopySL(ptr2, ptr, 1);
// 	printSLlist(ptr2);
// 		
	HBnodePtr ptr;
// //  	
	ptr = createHBlist(m, n);
// // 	
	printHBlist(ptr);
// // 	
	SLnodePtr abc;
	SLnodePtr abcd;
// 	abcd = makeHBintoSL(abc, ptr);
// // // 	
// // // 	abcd = takeCopySL(abcd, abc, ptr->bottom, 1);
// // // 	
// 	printSLlist(abcd);
// 
	SLnodePtr SLptr = flattenList(ptr);
//  	SLnodePtr SLptr = initialHBintoSL(ptr);
//  	SLnodePtr SLptrfinal;
// 	SLptrfinal= makeHBintoSL(SLptr, ptr->next);
// //  	
	printSLlist(SLptr);
// // 	
// // 	
	printf("\n");
// 		printHBlist(ptr);
	freeHBlist(ptr);
	freeSLlist(SLptr);
	
}











































