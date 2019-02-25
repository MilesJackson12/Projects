#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "a4.h"

void mutate(Individual *individual, double rate){
    
    int numOfPixels = individual->image.width * individual->image.height;

    int numOfMutations = (rate/100)*numOfPixels;
    int maxColor = individual->image.max_color;

    int i;
    for (i=0; i<numOfMutations; i++){
        int j = rand()%numOfPixels;
        individual->image.data[j].r = rand()%maxColor;
        individual->image.data[j].g = rand()%maxColor;
        individual->image.data[j].b = rand()%maxColor;
    }
}

void mutate_population(Individual *individual, int population_size, double rate){
    int starting_point = population_size/4;
    
    int i;
    for (i = starting_point; i<population_size; i++){
        mutate(&individual[i], rate);
    }
}

