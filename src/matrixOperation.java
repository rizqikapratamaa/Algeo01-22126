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

    // Fungsi untuk menghitung determinan kofaktor
    public static double determinanKofaktor(matrix mIn) {
        // PREKONDISI: mIn matriks persegi
        double det;
        if (mIn.nRow == 1) {
            det = mIn.Matrix[0][0];
        } else {
            det = 0;
            for (int j = 0; j < mIn.nRow; j++) {
                if (j % 2 == 0) {
                    det += mIn.Matrix[0][j] * determinanKofaktor(slice(mIn, 0, j));
                } else {
                    det += (-1) * mIn.Matrix[0][j] * determinanKofaktor(slice(mIn, 0, j));
                }
            }
        }
        return det;
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

    // Fungsi untuk implementasi metode eliminasi Gauss
    public static matrix gauss(matrix mIn){
        matrix mOut = new matrix();
        int column = 0;
        int row = 0;
        int i;
        
        tidyUp(mOut);
        mOut = compactzero(mIn);
        while (column < mOut.nCol-1) {
            if (mOut.Matrix[row][column] == 0) {
                column += 1;
            }
            else{
                for(i = row + 1; i < mOut.nRow; i++){
                    mOut = minKaliBaris(mOut, i, row, mOut.Matrix[i][column]/mOut.Matrix[row][column]);
                }
                mOut = rowXConst(mOut, row, 1/mOut.Matrix[row][column]);

                mOut = compactzero(mOut);

                column += 1;
                row += 1;
            }
        }
        return mOut;
    }

    // Fungsi untuk implementasi metode eliminasi Gauss-Jordan
    public static matrix gaussJordan(matrix mIn){
        matrix mOut = new matrix();
        int column = 0;
        int row = 0;
        int i;

        mOut = gauss(mIn);
        tidyUp(mOut);
        while (column < mOut.nCol-1){
            if (mOut.Matrix[row][column] == 0){
                column += 1;
            }
            else{
                for (i = 0; i < row; i++){
                    if (i != row){
                        mOut = minKaliBaris(mOut, i, row, mOut.Matrix[i][column]/mOut.Matrix[row][column]);
                    }
                }
                column += 1;
                row += 1;
            }
        }
        return mOut;
    }
    
    // Fungsi untuk menghitung determinan dengan metode OBE
    public static double detOBE(matrix mIn){
        //PREKONDISI: matriks berukuran m x m

        double det = 1;
        int kolom = 0;
        int lenNonZero = 0;
        int colSearch;
        boolean adaNonZero;
        matrix temp = new matrix();

        temp = cloneMatrix(mIn);
    
        while ((lenNonZero < temp.nRow) && (kolom < temp.nCol)) {
            adaNonZero = false;

            if (temp.Matrix[lenNonZero][kolom] == 0) {
                
                colSearch = lenNonZero + 1;
                while ((colSearch < temp.nRow) && (!adaNonZero)) {
                    if (temp.Matrix[colSearch][kolom] != 0) {
                        adaNonZero = true;
                        temp = rowSwap(temp, colSearch, lenNonZero);
                        det *= -1;
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

        if (temp.Matrix[0][0] == 0){
            det = 0;
        }

        else{
            for (int i = 0; i < temp.nCol; i++){
                for (int j = i+1; j < temp.nRow; j++){                    
                    temp = minKaliBaris(temp, j, i, temp.Matrix[j][i]/temp.Matrix[i][i]);
                }

                kolom = 0;
                lenNonZero = 0;
                while ((lenNonZero < temp.nRow) && (kolom < temp.nCol)) {
                    adaNonZero = false;
                    
                    if (temp.Matrix[lenNonZero][kolom] == 0) {
                        colSearch = lenNonZero + 1;
                        while ((colSearch < temp.nRow) && (!adaNonZero)) {
                            if (temp.Matrix[colSearch][kolom] != 0) {
                                adaNonZero = true;
                                temp = rowSwap(temp, colSearch, lenNonZero);
                                det *= -1;
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

                det *= temp.Matrix[i][i];
            }
        }

        // Permbulatan 5 angka di belakang koma
        det = Math.round(det *10000) / 10000;
        return det;
    }

    // Prosedur untuk menmbuat file determinan
    public static void detFile(matrix mIn, double det){
        int i, j;
        String filename;

        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

            bw.write("Matriks:");
            bw.newLine();
            for (i= 0; i<mIn.nRow; i++){
                for (j=0; j<mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }

            bw.newLine();
            bw.write("Determinannya adalah = " + det);
            bw.newLine();

            bw.flush();
            bw.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Fungsi untuk menghitung kofaktor
    public static double cofactor(matrix mIn, int i, int j){
        double cof;
        cof = detOBE(slice(mIn, i, j));
        if ((i + j) % 2 != 0){
            cof += (-1);
        }
        return cof;
    }

    // Fungsi untuk implementasi inverse identitas
    public static matrix inverseIdentitas(matrix mIn){
        matrix temp = new matrix();
        matrix mOut = new matrix();
        double cache = 0;
        int lenNonZero = 0;
        int kolom = 0;
        int kolom2 = 0;
        int colSearch = 0;
        int baris = 0;
        int i = 0;
        int j = 0;
        boolean adaNonZero;

        temp = cloneMatrix(mIn);

        // Buat matriks identitas
        mOut.nRow = mIn.nRow;
        mOut.nCol = mIn.nCol;
        for (i = 0; i <mOut.nRow; i++){
            for (j = 0; j < mOut.nCol; j++){
                if (i == j){
                    mOut.Matrix[i][j] = 1;
                }
                else {
                    mOut.Matrix[i][j] = 0;
                }
            }
        }

        // Gunakan metode eliminasi gauss
        while ((lenNonZero < temp.nRow) && (kolom < temp.nCol)) {
            adaNonZero = false;
            if (temp.Matrix[lenNonZero][kolom] == 0) {
                colSearch = lenNonZero + 1;
                while ((colSearch < temp.nRow) && (!adaNonZero)) {
                    if (temp.Matrix[colSearch][kolom] != 0) {
                        adaNonZero = true;
                        temp = rowSwap(temp, colSearch, lenNonZero);
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

        kolom = 0;
        baris = 0;
        while (kolom < temp.nCol) {
            if (temp.Matrix[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = baris + 1; i < temp.nRow; i++){
                    cache = temp.Matrix[i][kolom]/temp.Matrix[baris][kolom];
                    temp = minKaliBaris(temp, i, baris, cache);
                    mOut = minKaliBaris(mOut, i, baris, cache);
                }

                cache = 1/temp.Matrix[baris][kolom];

                temp = rowXConst(temp, baris, cache);
                mOut = rowXConst(mOut, baris, cache);

                lenNonZero = 0;
                kolom2 = 0;
                colSearch = 0;
                while ((lenNonZero < temp.nRow) && (kolom2 < temp.nCol)) {
                    adaNonZero = false;
                    if (temp.Matrix[lenNonZero][kolom2] == 0) {
                        colSearch = lenNonZero + 1;
                        while ((colSearch < temp.nRow) && (!adaNonZero)) {
                            if (temp.Matrix[colSearch][kolom2] != 0) {
                                adaNonZero = true;
                                temp = rowSwap(temp, colSearch, lenNonZero);
                                mOut = rowSwap(mOut, colSearch, lenNonZero);
                                lenNonZero += 1;
                            }
                            else{
                                colSearch += 1;
                            }
                        }
                        if (!adaNonZero) {
                            kolom2 += 1;
                        }
                    }
                    else{
                        lenNonZero += 1;
                    }
                }
                kolom += 1;
                baris += 1;
            }
        }

        // Jordan
        kolom = 0;
        baris = 0;
        while (kolom < temp.nCol) {
            if (temp.Matrix[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = 0; i < baris; i++){
                    if (i != baris){
                        cache = temp.Matrix[i][kolom]/temp.Matrix[baris][kolom];
                        temp = minKaliBaris(temp, i, baris, cache);
                        mOut = minKaliBaris(mOut, i, baris, cache);
                    }
                }
                kolom += 1;
                baris += 1;
            }
        }

        return mOut;
    }

    // Fungsi untuk implementasi inverse adjoint
    public static matrix inverseAdjoint(matrix mIn){
        // PREKONDISI: mIn matriks persegi, DET mIn != 0
        matrix mOut = new matrix();
        mOut = transpose(matrixCof(mIn));
        for (int i = 0; i < mIn.nCol; i++){
            for (int j = 0; j < mIn.nRow; j++){
                mOut.Matrix[i][j] /= detOBE(mIn);
            }
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
                mOut.Matrix[i][j] = cofactor(mIn, i, j);
            }
        }
        return mOut;
    }

    // Fungsi untuk implementasi kaidah cramer
    public static void kaidahCramer(matrix m){
        matrix mCut = new matrix();
        matrix mhasilB = new matrix();
        double determinanX, determinan;
        int i, j;

        // memotong elemen terakhir dari matriks augmented dan masukin ke matriks mCut
        for(i = 0; i < m.nRow; i++){
            for(j = 0; j < m.nCol-1; j++){
                mCut.Matrix[i][j] = m.Matrix[i][j];
            }
        }

        determinan = determinanKofaktor(mCut);

        if(determinan == 0){
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
                determinanX = determinanKofaktor(mCut);
                System.out.println("Nilai x" + (j+1) + " = " + determinanX/determinan);
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
}