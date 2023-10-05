import java.io.*;
import java.util.*;

public class matrixOperation {
    static Scanner in = new Scanner(System.in);
    
    // Fungsi untuk mengecek apakah dimensi matriks m1 dan m2 sama
    public static boolean isEqual(matrix m1, matrix m2){
        return ((m1.nRow == m2.nRow) && (m1.nCol == m2.nCol));
    }

    // Fungsi untuk menduplikasi matriks
    public static matrix cloneMatrix(matrix mIn){
            matrix mOut = new matrix();
            mOut.nCol = mIn.nCol;
            mOut.nRow = mIn.nRow;
            for(int i=0; i<mIn.nRow; i++){
                for(int j=0; j<mIn.nCol; j++) {
                    mOut.Matrix[i][j] = mIn.Matrix[i][j];
                }
            }
            return mOut;
        }

    // Fungsi untuk mengalikan dua buah matriks
    public static matrix multiplyMatrix(matrix m1, matrix m2){
        // I.S Ukuran m1 = m2
        // Kolom m1 = Baris m2

        matrix mOut = new matrix();

        int i, j, k;
        double sum;

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

    // Fungsi untuk mengalikan dua buah matriks untuk interpolasi bikubik
    public static matrix multiplyMatrixBik(matrix m1, matrix m2){
        // Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2
        // Mengirim hasil perkalian matriks: salinan m1 * m2
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
    
    // Fungsi untuk melakukan transpose matriks
    public static matrix transpose(matrix mIn){
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

    // Fungsi untuk menukar baris matriks
    public static matrix rowSwap(matrix mIn, int b1, int b2){
        matrix mOut = new matrix();

        mOut = cloneMatrix(mIn);

        mOut.Matrix[b1] = mIn.Matrix[b2];
        mOut.Matrix[b2] = mIn.Matrix[b1];

        return mOut;
    }

    // Fungsi untuk mengambil elemen yang barisnya bukan i dan kolomnya bukan j
    public static matrix slice(matrix mIn, int i, int j){
        matrix mOut = new matrix();
        mOut.nRow = mIn.nRow - 1;
        mOut.nCol = mIn.nCol - 1;
        int count = 0;
        for (int k = 0; k < mIn.nRow; k++){
            for (int l = 0; l < mIn.nCol; l++){
                if (!(k == i || l == j)){
                    count++;
                    mOut.Matrix[(count - 1) / mOut.nCol][(count - 1) % mOut.nCol] = mIn.Matrix[k][l];
                }
            }
        }
        return mOut;
    }

    // Fungsi untuk membuang baris terakhir
    public static matrix sliceLastRow(matrix mIn){
        matrix mOut = new matrix();
        mOut.nRow = mIn.nRow - 1;
        mOut.nCol = mIn.nCol;
        for (int i = 0; i < mOut.nRow; i++){
            for (int j = 0; j < mOut.nCol; j++){
                mOut.Matrix[i][j] = mIn.Matrix[i][j];
            }
        }
        return mOut;
    }

    // Fungsi untuk membuang kolom terakhir
    public static matrix sliceLastCol(matrix mIn){
        matrix mOut = new matrix();
        mOut.nRow = mIn.nRow;
        mOut.nCol = mIn.nCol - 1;
        for (int i = 0; i < mOut.nRow; i++){
            for (int j = 0; j < mOut.nCol; j++){
                mOut.Matrix[i][j] = mIn.Matrix[i][j];
            }
        }
        return mOut;
    }

    // Fungsi untuk mengambil baris terakhir
    public static matrix takeLastRow(matrix mIn){
        matrix mOut = new matrix();
        mOut.nRow = 1;
        mOut.nCol = mIn.nCol;
        for (int i = 0; i < mOut.nRow; i++){
            mOut.Matrix[0][i] = mIn.Matrix[mIn.nRow - 1][i];
        }
        return mOut;
    }

    // Fungsi untuk mengambil kolom terakhir
    public static matrix takeLastCol(matrix mIn){
        matrix mOut = new matrix();
        mOut.nRow = mIn.nRow;
        mOut.nCol = 1;
        for (int i = 0; i < mOut.nRow; i++){
            mOut.Matrix[i][0] = mIn.Matrix[i][mIn.nCol - 1];
        }
        return mOut;
    }
    
    // Prosedur untuk mengatur nilai-nilai dalam matriks sehingga nilai-nilainya mendekati 0 atau 1 dalam batasan toleransi yang sangat kecil
    static void tidyUp(matrix mIn){
        for(int i =0; i < mIn.nRow; i++){
            for(int j = 0; j < mIn.nCol; j++){
                if (mIn.Matrix[i][j] < 0.00000000001 && mIn.Matrix[i][j] > -0.00000000001){
                    mIn.Matrix[i][j] = 0;
                }
                else if (mIn.Matrix[i][j] < 1.00000000001 && mIn.Matrix[i][j] > 0.99999999999){
                    mIn.Matrix[i][j] = 1;
                }
            }
        }
    }

    // Fungsi untuk mengkompakkan matriks dengan menggeser semua elemen nol ke bagian bawah matriks
    public static matrix compactzero(matrix mIn){
        matrix mOut = new matrix();
        int kolom = 0;
        int lenNonZero = 0;
        int colSearch;
        boolean adaNonZero;

        mOut = cloneMatrix(mIn);

        while ((lenNonZero < mOut.nRow) && (kolom < mOut.nCol)) {
            adaNonZero = false;

            if (mOut.Matrix[lenNonZero][kolom] == 0) {
                
                colSearch = lenNonZero + 1;
                while ((colSearch < mOut.nRow) && (!adaNonZero)) {
                    if (mOut.Matrix[colSearch][kolom] != 0) {
                        adaNonZero = true;
                        mOut = rowSwap(mOut, colSearch, lenNonZero);
                        lenNonZero += 1;
                    }
                    else{
                        colSearch += 1;
                    }
                }


                if (!adaNonZero) {
                    kolom += 1;
                }
            }

            else{
                lenNonZero += 1;
            }
        }
        return mOut;
    }

    // Fungsi untuk mengalikan semua elemen dalam satu baris tertentu dari matriks dengan sebuah konstanta
    public static matrix rowXConst(matrix mIn, int baris, double konstanta){
        matrix mOut = new matrix();


        mOut = cloneMatrix(mIn);

        for (int i = 0; i < mOut.nCol; i++){
            mOut.Matrix[baris][i] *= konstanta;
        }

        return mOut;
    }

    // Fungsi untuk mengurangkan dan mengalikan satu baris tertentu dari matriks
    public static matrix minKaliBaris(matrix mIn, int barisTujuan, int barisPengurang, double konstanta){
        matrix mOut = new matrix();

        mOut = cloneMatrix(mIn);

        for (int i = 0; i < mOut.nCol; i++){
            mOut.Matrix[barisTujuan][i] -= konstanta * mOut.Matrix[barisPengurang][i];
        }

        return mOut;
    }     
    
    // Fungsi untuk mendapat matriks kofaktor tiap elemen i, j
    public static matrix matrixCof(matrix mIn){
        matrix mOut = new matrix();
        mOut.nRow = mIn.nRow;
        mOut.nCol = mIn.nCol;
        for ( int i = 0; i < mIn.nRow; i++){
            for (int j = 0; j < mIn.nCol; j++){
                mOut.Matrix[i][j] = determinan.cofactor(mIn, i, j);
            }
        }
        return mOut;
    }

    // Prosedur untuk implementasi kaidah cramer
    public static void kaidahCramer(matrix m){
        matrix mCut = new matrix();
        matrix mhasilB = new matrix();
        double determinanX, determinant;
        int i, j;

        // memotong elemen terakhir dari matriks augmented dan masukin ke matriks mCut
        for(i = 0; i < m.nRow; i++){
            for(j = 0; j < m.nCol-1; j++){
                mCut.Matrix[i][j] = m.Matrix[i][j];
            }
        }

        determinant = determinan.determinanKofaktor(mCut);

        if(determinant == 0){
            System.out.println("Tidak bisa menggunakan kaidah cramer karena determinan matriks = 0");
        }else{

            // bikin matriks yang isinya b
            for(i=0; i<m.nRow; i++){
                mhasilB.Matrix[i][0] = m.Matrix[i][m.nCol-1];
            }

            for(j=0; j<m.nCol-1; j++){
                for(i=0; i<m.nRow; i++){
                    mCut.Matrix[i][j] = mhasilB.Matrix[i][0];
                }
                determinanX = determinan.determinanKofaktor(mCut);
                System.out.println("Nilai x" + (j+1) + " = " + determinanX/determinant);
            }
        }
    }

    // Fungsi untuk untuk menyelesaikan sistem persamaan linear dengan kaidar cramer
    public static matrix cramerSwap(matrix m2, matrix m1, int col){
        matrix temp = new matrix();
        temp = cloneMatrix(m2);
        for (int i = 0; i < m2.nRow; i++){
            temp.Matrix[i][col] = m1.Matrix[i][0];
        }
        return temp;
    }

    // Fungsi untuk menyatukan m1 dan m2 secara kolom
    public static matrix concatCol(matrix m1, matrix m2) {
        // PREKONDISI: m1.nRow = m2.nRow
        matrix m3 = new matrix();
        m3.nRow = m1.nRow;
        m3.nCol = m1.nCol + m2.nCol;
        int i, j;
        for (i = 0; i <= m3.nRow; i++) {
            for (j = 0; j <= m3.nCol; j++){
                if (j < m1.nCol) {
                    m3.Matrix[i][j] = m1.Matrix[i][j];
                } else {
                    m3.Matrix[i][j] = m2.Matrix[i][j - m1.nCol];
                }
            }
        }
        return m3;
    }

    public static matrix gaussJordanReg(matrix mIn){   
        matrix mOut = new matrix();
        int column = 0;
        int row = 0;

        mOut = SPL.gauss(mIn);
        tidyUp(mOut);

        while (column < mOut.nCol - 1 && row < mOut.nRow) {
            if (mOut.Matrix[row][column] == 0) {
                // Jika elemen utama di bawah nol, coba cari baris di bawahnya yang tidak nol
                int swapRow = -1;
                for (int i = row + 1; i < mOut.nRow; i++) {
                    if (mOut.Matrix[i][column] != 0) {
                        swapRow = i;
                        break;
                    }
                }

                if (swapRow != -1) {
                    // Tukar baris
                    mOut = rowSwap(mOut, row, swapRow);
                } else {
                    // Tidak ada baris yang bisa ditukar, lanjut ke kolom berikutnya
                    column += 1;
                    continue;
                }
            }

            // Buat elemen utama menjadi 1
            double pivot = mOut.Matrix[row][column];
            mOut = rowXConst(mOut, row, 1.0 / pivot);

            // Nolkan elemen-elemen di atas dan di bawah elemen utama
            for (int i = 0; i < mOut.nRow; i++) {
                if (i != row) {
                    double factor = -mOut.Matrix[i][column];
                    mOut = minKaliBaris(mOut, i, row, factor);
                }
            }

            // Pindah ke kolom berikutnya
            column += 1;
            row += 1;
        }

        return mOut;
    }
}