#include <stdio.h>
#include <stdlib.h>
#include "a4.h"

PPM_IMAGE *read_ppm(const char *file_name){
    FILE *fp;
    PPM_IMAGE *img = NULL;
    fp = fopen(file_name, "r");

    if (fp == NULL){
        printf("Error Loading Image");
        return img;
    }

    img = malloc(sizeof(PPM_IMAGE));
    char buff[255];

    fgets(buff, 255, fp); //Image type
    printf("File Type: %s", buff );

    fscanf(fp, "%d %d", &img->width, &img->height);//width and height
    printf("Width: %d, Height: %d\n", img->width, img->height);

    fscanf(fp, "%d", &img->max_color); //max_color
    printf("Max Color: %d\n", img->max_color);

    int numOfPixels = img->width * img->height;
    PIXEL pixelArray[numOfPixels];

    for (int i=0; i<numOfPixels; i++){
        fscanf(fp, "%hhu %hhu %hhu", &pixelArray[i].r, &pixelArray[i].g, &pixelArray[i].b);
    }
    
    img->data = pixelArray;

    fclose(fp);

    return img;
}
void write_ppm(const char *file_name , const PPM_IMAGE *image){

    FILE *fp;

    fp = fopen(file_name, "w");
    fprintf(fp, "P3\n");
    fprintf(fp, "%d %d\n", image->width, image->height);
    fprintf(fp, "%d\n", image->max_color);

    int numOfPixels = (image->width * image->height);
    for (int i=0; i<numOfPixels; i++){
        fprintf(fp, "%hhu %hhu %hhu  ", image->data[i].r, image->data[i].g, image->data[i].b);
    }

    fclose(fp);
}