    public static matrix multiplyMatrixBik(matrix m1, matrix m2){
        /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
        /* Mengirim hasil perkalian matriks: salinan m1 * m2 */
        matrix mOut = new matrix();
    
        int i,j,k;
        for(i=0; i<m1.nRow; i++){
            for(j=0; j<m2.nCol; j++){
                for(k=0; k<m1.nCol; k++){
                    mOut.Matrix[i][j] += m1.Matrix[i][k] * m2.Matrix[k][j];
                }
            }
        }
        return mOut;
    }