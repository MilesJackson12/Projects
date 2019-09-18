//#ifndef ASSIGNMENT3_H
#define ASSIGNMENT3_H

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

