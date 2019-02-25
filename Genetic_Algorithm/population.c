#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "a4.h"

PIXEL *generate_random_image (int width, int height, int max_color){
    
    int numOfPixels = width * height;
    PIXEL *pixelArray = malloc(numOfPixels * sizeof(PIXEL));

    int i;
    for (i=0; i<numOfPixels; i++){
        unsigned char a = rand()%max_color;
        unsigned char b = rand()%max_color;
        unsigned char c = rand()%max_color;

        pixelArray[i].r = a; 
        pixelArray[i].g = b; 
        pixelArray[i].b = c; 

    }

    PIXEL *ptr = pixelArray;
    return ptr;
}

Individual * generate_population (int population_size, int width, int height, int max_color){

    Individual *indivPtr = malloc(population_size * (sizeof(Individual) + sizeof(PPM_IMAGE)));

    int i;
    for (i=0; i<population_size; i++){
        indivPtr[i].image.width = width;
        indivPtr[i].image.height = height;
        indivPtr[i].image.max_color = max_color;
        indivPtr[i].image.data = generate_random_image(width, height, max_color);
    }

    Individual *ptr = indivPtr;
    return ptr;

}

