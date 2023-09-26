import java.io.*;
import java.util.*;

public class matrixOperation {
    static Scanner in = new Scanner(System.in);
    /* Validasi Matriks */
    public static boolean isEqual(matrix m1, matrix m2){
        /* Mengembalikan true jika dimensi matriks 1 sama dengan matriks 2 */
        return ((m1.nRow == m2.nRow) && (m1.nCol == m2.nCol));
    }

    public static matrix cloneMatrix(matrix mIn){
        /* Menduplikasi matriks */
        // Kamus Lokal
        matrix mOut = new matrix();
        mOut.nRow = mIn.nRow;
        mOut.nCol = mIn.nCol;

        // Algoritma
        for (int i = 0; i < mIn.nRow; i++){
            for (int j = 0; j < mIn.nCol; j++){
                mOut.Matrix[i][j] = mIn.Matrix[i][j];
            }
        }
        return mOut;
    }

    public static matrix matrixAddition(matrix m1, matrix m2){
        /* Menambahkan matriks */
        // I.S Dimensi m1 = dimensi m2

        // Kamus lokal
        matrix mOut = new matrix();
        int i, j;

        // algoritma
        mOut = cloneMatrix(m1);

        for (i = 0; i < m1.nRow; i++){
            for (j = 0; j < m1.nCol; j++){
                mOut.Matrix[i][j] = m1.Matrix[i][j] + m2.Matrix[i][j];
            }
        }
        return mOut;
    }

    public static matrix matrixReduction(matrix m1, matrix m2){
        /* Menambahkan matriks */
        // I.S Dimensi m1 = dimensi m2

        // Kamus lokal
        matrix mOut = new matrix();
        int i, j;

        // algoritma
        mOut = cloneMatrix(m1);

        for (i = 0; i < m1.nRow; i++){
            for (j = 0; j < m1.nCol; j++){
                mOut.Matrix[i][j] = m1.Matrix[i][j] - m2.Matrix[i][j];
            }
        }
        return mOut;
    }

    public static matrix multiplyMatrix(matrix m1, matrix m2){
        /* Perkalian dua matriks */
        // I.S Ukuran m1 = m2
        // Kolom m1 = Baris m2
        // Menghasilkan matrix baru

        // kamus lokal
        matrix mOut = new matrix();

        int i, j, k;
        double sum;

        // algoritma
        mOut.nCol = m2.nCol;
        mOut.nRow = m1.nRow;
        for (i = 0; i < m1.nRow; i++){
            for (j = 0; j < m1.nCol; j++){
                sum = 0;
                for (k = 0; k < m2.nCol; k++){
                    sum += m1.Matrix[i][j] * m2.Matrix[j][k];
                }
                mOut.Matrix[i][j] = sum;
            }
        }

        return mOut;
    }

    public static matrix transpose(matrix mIn){
        /* Mengembalikan matriks transpose */

        // kamus lokal
        matrix mOut = new matrix();
        mOut.nCol = mIn.nRow;
        mOut.nRow = mIn.nCol;

        // algoritma
        for (int i = 0; i < mIn.nRow; i++){
            for (int j = 0; j < mIn.nCol; j++){
                mOut.Matrix[j][i] = mIn.Matrix[i][j];
            }
        }
        return mOut;
    }

    public static matrix rowSwap(matrix mIn, int x, int y){
        matrix mOut = new matrix();

        mOut = cloneMatrix(mIn);

        mOut.Matrix[x] = mIn.Matrix[y];
        mOut.Matrix[y] = mIn.Matrix[x];

        return mOut;
    }

    static void tidyUp(matrix mIn){
        for (int i = 0; i < mIn.nRow; i++){
            for (int j = 0; j < mIn.nCol; i++){
                if (mIn.Matrix[i][j] < 0.00000000001 && mIn.Matrix[i][j] > -0.00000000001){
                    mIn.Matrix[i][j] = 0;
                }
                else if (mIn.Matrix[i][j] < 1.00000000001 && mIn.Matrix[i][j] > 0.99999999999){
                    mIn.Matrix[i][j] = 1;
                }
            }
        }
    }

    public static matrix compactzero(matrix mIn){
        // Madetin 0 ke bawah
        matrix mOut = new matrix();
        int kolom = 0;
        int lenNon0 = 0;
        int colSearch;
        boolean adaNon0;

        mOut = cloneMatrix(mIn);

        while((lenNon0 < mOut.nRow) && (kolom < mOut.nCol)){
            adaNon0 = false;

            if (mOut.Matrix[lenNon0][kolom] == 0){
                colSearch = lenNon0 + 1;
                while ((colSearch < mOut.nRow) && (!adaNon0)){
                    if (mOut.Matrix[colSearch][kolom] != 0){
                        adaNon0 = true;
                        mOut = rowSwap(mOut, colSearch, lenNon0);
                        lenNon0 += 1;
                    }
                    else{
                        kolom += 1;
                    }
                }
                if (!adaNon0){
                    kolom += 1;
                }
            }
            else{
                lenNon0 += 1;
            }
        }
        return mOut;
    }

    public static matrix rowXConst(matrix mIn, int baris, double constant){
        matrix mOut = new matrix();
        mOut = cloneMatrix(mIn);
        for (int i = 0; i < mOut.nCol; i++){
            mOut.Matrix[baris][i] *= constant;
        }
        return mOut;
    }

    public static matrix minKaliBaris(matrix mIn, int barisTujuan, int barisPengurang, double konstanta){
        matrix mOut = new matrix();
        mOut = cloneMatrix(mIn);

        for (int i = 0; i < mOut.nCol; i++){
            mOut.Matrix[barisTujuan][i] -= konstanta*mOut.Matrix[barisPengurang][i];
        }
        return mOut;
    }

    public static matrix gauss(matrix mIn){
        matrix mOut = new matrix();
        int kolom = 0;
        int baris = 0;
        int i;

        tidyUp(mIn);
        mOut = compactzero(mIn);
        while (kolom < mOut.nCol-1){
            if (mOut.Matrix[baris][kolom] == 0){
                kolom += 1;
            }
            else{
                for (i = baris + 1; i < mOut.nRow; i++){
                    mOut = minKaliBaris(mOut,i , baris, mOut.Matrix[i][kolom]/mOut.Matrix[baris][kolom]);
                }
                mOut = rowXConst(mOut, baris, 1/mOut.Matrix[baris][kolom]);
                mOut = compactzero(mOut);

                kolom += 1;
                baris += 1;
            }
        }
        return mOut;
    }
}