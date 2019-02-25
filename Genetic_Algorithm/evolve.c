#include <stdio.h>
#include <stdlib.h>
#include <time.h>       
#include "a4.h"

int compare(const void *C, const void *D){
        Individual *A = (Individual *)C;
        Individual *B = (Individual *)D;
        if ((A->fitness - B->fitness) >= 0){
                return 1;
        }
        return -1;
}


PPM_IMAGE *evolve_image(const PPM_IMAGE *image, int num_generations, 
int population_size, double rate){
    srand(time(NULL));
         //generate rand population
    Individual *population = NULL; // = malloc(population_size * sizeof(PPM_IMAGE));
    population = generate_population(population_size, image->width, image->height, image->max_color);
            //compute fitness for each individual
    comp_fitness_population(image->data, population, population_size);

         //sort in non-decreasing order of fitness
    qsort(population, population_size, sizeof(Individual), compare);

    //       //for every Generation to follow:
    int gen;
    for (gen=1; gen<=num_generations; gen++){
    //           //do a crossover on population
        crossover(population, population_size);

    //           //mutate individuals from popSize/4 to popSize-1
        mutate_population(population, population_size, rate);

    //           //compute fitness for each individual
        comp_fitness_population(image->data, population, population_size);

        qsort(population, population_size, sizeof(Individual), compare);
        printf("Gen: %d   Best Fitness: %f\n", gen, population[0].fitness);
    }
             //once done all generations return a pointer to PPM_IMAGE with the best fitness
    
    PPM_IMAGE *imPtr = malloc(sizeof(PPM_IMAGE));
    imPtr->width = population[0].image.width;
    imPtr->height = population[0].image.height;
    imPtr->max_color = population[0].image.max_color;
    imPtr->data = malloc(imPtr->height * imPtr->width * sizeof(PIXEL));
    int i;
    for (i=0; i<(imPtr->height * imPtr->width); i++){
        imPtr->data[i] = population[0].image.data[i];
    }


    for (i=population_size-1; i>=0; i--){
        free(population[i].image.data);
    }
    free(population);

    return imPtr;

}


void free_image(PPM_IMAGE *p){
    free(p->data);
    free(p);
}
