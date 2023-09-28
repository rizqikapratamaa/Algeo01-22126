public class bicubicInterpolation {
    // jangan lupa tambahin static scanner nanti

    public static double getA(matrix stdInput){
        return stdInput.Matrix[4][0];
    }

    public static double getB(matrix stdInput){
        return stdInput.Matrix[4][1];
    }

    public static matrix m4x4(matrix stdInput){
        return matrixOperation.sliceLastRow(stdInput);
    }

    public static matrix m16x1(matrix m4x4){
        matrix m16x1 = new matrix();
        m16x1.nRow = 16;
        m16x1.nCol = 1;

        int i, j, row=0; 
        for(i=0; i<4; i++){
            for(j=0; j<4; j++){
                m16x1.Matrix[row][0] = m4x4.Matrix[i][j];
                row++;
            }
        }
        return m16x1;
    }

    public static matrix m16x16(){
        matrix m16x16 = new matrix();
        m16x16.nRow = 16;
        m16x16.nCol = 16;

        int i, j, x, y, row, col;
        row=0;
        for(y=0; y<2; y++){
            for(x=0; x<2; x++){
                col=0;
                for(j=0; j<4; j++){
                    for(i=0; i<4; i++){
                        if(row>=0 && row<=3){
                            m16x16.Matrix[row][col] = Math.pow(x, i) * Math.pow(y, j);
                        } else if(row>=4 && row<=7){
                            m16x16.Matrix[row][col] = i * Math.pow(x, i-1) * Math.pow(y, j);
                        } else if(row>=8 && row<=11){
                            m16x16.Matrix[row][col] = j * Math.pow(x, i) * Math.pow(y, j-1);
                        } else{
                            m16x16.Matrix[row][col] = i * j * Math.pow(x, i-1) * Math.pow(y, j-1); 
                        }
                        col++;
                    }
                }
                row++;
            }
        }
        return m16x16;
    }

    public static matrix mAij(matrix m16x1){
        matrix mInversA = new matrix();
        mInversA.nRow = 16;
        mInversA.nCol = 16;
        mInversA = matrixOperation.inverseWithGaussJordan(m16x16());

        return matrixOperation.multiplyMatrix(mInversA, m16x1);
    }

    public static double getFab(matrix mAij, double a, double b){
        int i, j, row=0;
        double hslfab = 0;

        for(j=0; j<4; j++){
            for(i=0; i<4; i++){
                hslfab += mAij.Matrix[row][0] * Math.pow(a, i) * Math.pow(b, j);
                row++;
            }
        }
        return hslfab;
    }


    

}