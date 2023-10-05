#include <stdio.h>

int main(){
    int i, j, row=0; 
    for(i=0; i<4; i++){
        for(j=0; j<4; j++){
            ELMT(m, i, 0) = ELMT(m, i, j);
            row++;
        }
    }
}
