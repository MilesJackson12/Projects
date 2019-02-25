#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "a4.h"

double comp_distance(const PIXEL *A, const PIXEL *B, int image_size){
    double d=0;
    int i;
    for (i=0; i<image_size; i++){
       d += pow((A[i].r - B[i].r),2) + pow((A[i].g - B[i].g),2) + pow((A[i].r - B[i].r),2);
    }
    double fitness = sqrt(d);

    return fitness;
}

void comp_fitness_population(const PIXEL *image, Individual *individual, int population_size){
    int image_size = individual[0].image.width * individual[0].image.height;
    int i;
    for (i=0; i<population_size; i++){
        individual[i].fitness = comp_distance(image, individual[i].image.data, image_size);
    }
}

a = pow((A[i].r - B[i].r),2); 
b = pow((A[i].g - B[i].g),2);
c = pow((A[i].r - B[i].r),2);
d += a + b + c;