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

    /* Fungsi Buat Ngambil Elemen Yang Barisnya Bukan i dan Kolomnya Bukan j */
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

    /* Fungsi Buat Buang Baris Terakhir */
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

    /* Fungsi Buat Buang Kolom Terakhir */
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

    /* Fungsi Buat Ambil Baris Terakhir */
    public static matrix takeLastRow(matrix mIn){
        matrix mOut = new matrix();
        mOut.nRow = mIn.nRow;
        mOut.nCol = 1;
        for (int i = 0; i < mOut.nCol; i++){
            mOut.Matrix[i][0] = mIn.Matrix[mIn.nRow - 1][i];
        }
        return mOut;
    }

    /* Fungsi Buat Ambil Kolom Terakhir */
    public static matrix takeLastCol(matrix mIn){
        matrix mOut = new matrix();
        mOut.nRow = 1;
        mOut.nCol = mIn.nCol;
        for (int i = 0; i < mOut.nRow; i++){
            mOut.Matrix[0][i] = mIn.Matrix[mIn.nRow - 1][i];
        }
        return mOut;
    }

    public static double detExCofRow0 (matrix mIn) {
        /* kenapa row 0 doang? jadi buat nyari determinan pake ekspansi kofaktor itu kan rekursif ya
       (coba aja sendiri kalo matriksnya 4 x 4), sedangkan di sini untuk setiap determinan harus pake ekspansi kofaktor.
       sedangkan si fungsi slice() itu bakal ngebuang bbrp baris dan kolom. satu2nya yang bisa dipastiin dari setiap matriks
       yang diinput ke fungsi ini ya yang pasti punya baris 0 (gabisa milih baris mana karna takutnya dia ngakses baris di luar 
       indeks yang valid) */
        // PREKONDISI: mIn matriks persegi
        double det;
        if (mIn.nRow == 1) {
            det = mIn.Matrix[0][0];
        } else {
            det = 0;
            for (int j = 0; j < mIn.nRow; j++) {
                if (j % 2 == 0) {
                    det += mIn.Matrix[0][j] * detExCofRow0(slice(mIn, 0, j));
                } else {
                    det += (-1) * mIn.Matrix[0][j] * detExCofRow0(slice(mIn, 0, j));
                }
            }
        }
        return det;
    }
    
    static void tidyUp(matrix mIn){
        for(int i = 0; i < mIn.nRow; i++){
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
                        colSearch += 1;
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

        tidyUp(mOut);
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

    public static matrix gaussJordan(matrix mIn){
        matrix mOut = new matrix();
        int kolom = 0;
        int baris = 0;
        int i;

        mOut = gauss(mIn);
        tidyUp(mOut);
        while (kolom < mOut.nCol-1){
            if (mOut.Matrix[baris][kolom] == 0){
                kolom += 1;
            }
            else{
                for (i = 0; i < baris; i++){
                    if (i != baris){
                        mOut = minKaliBaris(mOut, i, baris, mOut.Matrix[i][kolom]/mOut.Matrix[baris][kolom]);
                    }
                }
                kolom += 1;
                baris += 1;
            }
        }
        return mOut;
    }

     
    public void OBEMatriksEselon(matrix matriks){
        int kolom_utama = 0; //kolom yang sedang dicek 
        int jumlah_baris = matriks.nRow; //jumlah baris
        int jumlah_kolom = matriks.nCol; //jumlah kolom
    
        for (int r = 0; r < jumlah_baris; r++) {
            if (kolom_utama >= jumlah_kolom)
                break;
            
            //mencari kolom utama pertama = kolom yg tidak memiliki 0 pada baris manapun
            int i = r; //r adalah indeks baris yang sedang diproses
            while (matriks.Matrix[i][kolom_utama] == 0) { //mencari baris di bawah baris saat ini (baris r) yang memiliki elemen utama (leading entry) yang tidak nol pada kolom utama
                i++;
                if (i == jumlah_baris) {
                    i = r;
                    kolom_utama++;
                    if (jumlah_kolom == kolom_utama)
                        break; 
                }
            }
    
            //OBE pertukaran baris 
            //Tujuan dari pertukaran baris ini adalah untuk memindahkan baris dengan elemen non-nol di kolom utama (kolom yang sedang diproses) ke baris di atas, sehingga elemen non-nol tersebut menjadi elemen utama di baris baru.
            double[] temp = matriks.Matrix[i];
            matriks.Matrix[i] = matriks.Matrix[r];
            matriks.Matrix[r] = temp;
    
            // Perhitungan 1 utama
            double elemenutama = matriks.Matrix[r][kolom_utama];
            for (int j = 0; j < jumlah_kolom; j++) {
                matriks.Matrix[r][j] /= elemenutama;
            }
    
            //membuat kolom di bawah 1 utama jadi 0
            for (i = 0; i < jumlah_baris; i++) {
                if (i != r) {
                    double elemenutama2 = matriks.Matrix[i][kolom_utama];
                    for (int j = 0; j < jumlah_kolom; j++) {
                        matriks.Matrix[i][j] -= elemenutama2 * matriks.Matrix[r][j];
                    }
                }
            }
            
            kolom_utama++;
        }
        }
    
    public void OBEMatriksEselonTereduksi(matrix matriks){
        int kolom_utama = 0; //kolom utama  
        int jumlah_baris = matriks.nRow; //jumlah baris
        int jumlah_kolom = matriks.nCol; //jumlah kolom

        for (int r = 0; r < jumlah_baris; r++) {
            if (kolom_utama >= jumlah_kolom)
                break;
            
            //mencari kolom utama pertama = kolom yg tidak memiliki 0 pada baris manapun
            int i = r; //r adalah indeks baris yang sedang diproses
            while (matriks.Matrix[i][kolom_utama] == 0) { //mencari baris di bawah baris saat ini (baris r) yang memiliki elemen utama yang tidak nol pada kolom utama.
                i++;
                if (i == jumlah_baris) {
                    i = r;
                    kolom_utama++;
                    if (jumlah_kolom == kolom_utama)
                        break; 
                }
            }

            //OBE pertukaran baris 
            //Tujuan dari pertukaran baris ini adalah untuk memindahkan baris dengan elemen non-nol di kolom utama (kolom yang sedang diproses) ke baris di atas, sehingga elemen non-nol tersebut menjadi elemen utama di baris baru.
            double[] temp = matriks.Matrix[i];
            matriks.Matrix[i] = matriks.Matrix[r];
            matriks.Matrix[r] = temp;

            // Perhitungan 1 utama
            double elemenutama = matriks.Matrix[r][kolom_utama];
            for (int j = 0; j < jumlah_kolom; j++) {
                matriks.Matrix[r][j] /= elemenutama;
            }

            //membuat kolom di bawah 1 utama jadi 0
            for (i = 0; i < jumlah_baris; i++) {
                if (i != r) {
                    double elemenutama2 = matriks.Matrix[i][kolom_utama];
                    for (int j = 0; j < jumlah_kolom; j++) {
                        matriks.Matrix[i][j] -= elemenutama2 * matriks.Matrix[r][j];
                    }
                }
            }
            
            kolom_utama++;
        }
    
        //buat angka !0 di atas 1 utama jadi 0
        for (int r = jumlah_baris - 1; r >= 0; r--) {
            int kolom_utamaIndex = -1;
            for (int j = 0; j < jumlah_kolom; j++) {
                if (matriks.Matrix[r][j] != 0) {
                    kolom_utamaIndex = j;
                    break;
                }
            }
    
            if (kolom_utamaIndex != -1) {
                for (int i = 0; i < r; i++) {
                    double factor = matriks.Matrix[i][kolom_utamaIndex];
                    for (int j = 0; j < jumlah_kolom; j++) {
                        matriks.Matrix[i][j] -= factor * matriks.Matrix[r][j];
                    }
                }
            }
        }
    }

    public boolean isEselonBarisTereduksi(matrix matriks) {
        for (int i = 0; i < matriks.nRow; i++) {
            if (matriks.Matrix[i][i] != 1) {
                return false; 
            }
            //memeriksa elemen di atas 1 utama
            for (int j = 0; j < i; j++) {
                if (matriks.Matrix[i][j] != 0) {
                    return false; 
                }
            }
    
            // Memeriksa elemen di bawah 1 utama
            for (int j = i + 1; j < matriks.nCol; j++) {
                if (matriks.Matrix[i][j] != 0) {
                    return false; 
                }
            }
        }
    
        return true; 
    }

    public void EliminasiGaussJordan(matrix Matriks) {
        if (!isEselonBarisTereduksi(Matriks)) {
            OBEMatriksEselonTereduksi(Matriks);
        }
    
        // Menginisialisasi solusi dengan nol
        double[] solusi = new double[Matriks.nCol - 1];
    
        // Mencari tipe baris/tipe solusi
        for (int i = Matriks.nRow - 1; i >= 0; i--) {
            boolean hasNonZeroCoefficient = false; 
    
            for (int j = 0; j < Matriks.nCol - 1; j++) {
                if (Matriks.Matrix[i][j] != 0) {
                    hasNonZeroCoefficient = true; // Jika ada koefisien non-nol, tandai sebagai benar
                    break;
                }
            }
    
            if (!hasNonZeroCoefficient) {
                if (Matriks.Matrix[i][Matriks.nCol - 1] != 0) {
                    // Jika baris semua nol dengan b(last column) != 0 -> tidak ada solusi
                    System.out.println("Tidak ada solusi.");
                    break; 
                } else {
                    // Jika baris semua nol dengan b(last column) == 0 -> solusi banyak atau tak hingga
                    System.out.println("Solusi parametrik :");
                    for (int j = 0; j < Matriks.nCol - 1; j++) {
                        if (solusi[j] != 0) {
                            System.out.println("x" + (j + 1) + " = " + solusi[j]);
                        }
                    }
                    break; 
                }
            } else {
                // Solusi unik
                solusi[i] = Matriks.Matrix[i][Matriks.nCol - 1];
                for (int j = i + 1; j < Matriks.nCol - 1; j++) {
                    solusi[i] -= Matriks.Matrix[i][j] * solusi[j];
                }
            }
        }
    
        // display solusi unik
        System.out.println("Solusi unik :");
        for (int i = 0; i < Matriks.nCol - 1; i++) {
            System.out.println("x" + (i + 1) + " = " + solusi[i]);
        }
    }

    /* Submatriks untuk ekspansi kofaktor */
    public static matrix createSubMatrix(matrix m, int rowToRemove, int colToRemove) {
        matrix subMatrix = new matrix();
        int sub_i = 0;
        for (int i = 0; i < m.nRow; i++) {
            if (i == rowToRemove) {
                continue;
            }
            int sub_j = 0;
            for (int j = 0; j < m.nCol; j++) {
                if (j == colToRemove) {
                    continue;
                }
                subMatrix.Matrix[sub_i][sub_j] = m.Matrix[i][j];
                sub_j++;
            }
            sub_i++;
        }
        return subMatrix;
    }


    public matrix setIdentity(matrix matriks) {
        for (int i = 0; i < matriks.nRow; i++) {
            for (int j = 0; j < matriks.nCol; j++) {
                if (i == j) {
                    matriks.Matrix[i][j]= 1.0f;
                } else {
                    matriks.Matrix[i][j]= 0.0f;
                }
            }
        }
        return matriks;
    }
    
    public matrix inverseWithGaussJordan(matrix matriks) {
        int n = matriks.nRow; // Ukuran matriks A (n x n)
        matrix identity = new matrix();
        identity = setIdentity(identity); // Matriks identitas I
    
        // Buat matriks gabungan [A | I]
        matrix combinedMatrix = concatenateHorizontal(matriks, identity);
    
        // Eliminasi Gauss-Jordan pada matriks gabungan
        EliminasiGaussJordan(combinedMatrix);
    
        // Ekstrak matriks invers dari matriks hasil
        matrix inverseMatrix = getSubmatrix(combinedMatrix, 0, n, n, 2 * n);
        return inverseMatrix;
    }
    
    // Menggabungkan dua matriks secara horizontal
    public matrix concatenateHorizontal(matrix A, matrix B) {
        if (A.nRow != B.nRow) {
            System.out.println("Matriks harus memiliki jumlah baris yang sama.");
        }
    
        matrix combined = new matrix();
    
        for (int i = 0; i < A.nRow; i++) {
            for (int j = 0; j < A.nCol; j++) {
                setElement(combined, i, j, getElement(A,i, j));
            }
            for (int j = 0; j < B.nCol; j++) {
                setElement(combined, i, A.nCol + j, getElement(B,i, j));
            }
        }
    
        return combined;
    }

    public double getElement(matrix matriks, int row, int col) {
        if (row < 0 || row >= matriks.nRow || col < 0 || col >= matriks.nCol) {
            System.out.println("Indeks baris atau kolom tidak valid.");
        }

        return matriks.Matrix[row][col];
    }
    
    public void setElement(matrix matriks, int row, int col, double value) {
        if (row < 0 || row >= matriks.nRow || col < 0 || col >= matriks.nCol) {
            System.out.println("Indeks baris atau kolom tidak valid.");
        }
    
        matriks.Matrix[row][col] = value;
    }
    
    // Fungsi untuk mendapatkan submatriks dari matriks
    public matrix getSubmatrix(matrix matriks, int rowStart, int rowEnd, int colStart, int colEnd) {
        int subRows = rowEnd - rowStart;
        int subCols = colEnd - colStart;
    
        matrix submatrix = new matrix();
    
        for (int i = 0; i < subRows; i++) {
            for (int j = 0; j < subCols; j++) {
                setElement(submatrix,i, j, matriks.Matrix[rowStart + i][colStart + j]);
            }
        }
    
        return submatrix;
    }
    

    /* Menghitung determinan matriks dengan menggunakan ekspansi kofaktor */
    public static double determinantKofaktor(matrix m){
        double det = 0.0;
        if(m.nRow == 1){
            det = m.Matrix[0][0];
        } else if (m.nRow == 4){
            det = (m.Matrix[0][0]*m.Matrix[1][1]) - (m.Matrix[0][1]*m.Matrix[1][0]);
        } else{
            int sign = 1;
            for(int j = 0; j < m.nCol; j++){
                det += sign * m.Matrix[0][j] * determinantKofaktor(createSubMatrix(m, 0, j));
                sign = -sign;
            }
        }
        return det;
    }

    
    public static double detOBE(matrix MIn){
        /* Mengembalikan determinan matriks */
        //prekondisi berukuran aXa
        //determinan dicari menggunakan metode segitiga atas
        
        // Kamus Lokal
        double det = 1;
        int kolom = 0;
        int lenNon0 = 0;
        int kolomSearch;
        boolean adaNon0;
        matrix MTemp = new matrix();

        // Algoritma


        MTemp = cloneMatrix(MIn);
        
        //Padetin 0 dulu
        while ((lenNon0 < MTemp.nRow) && (kolom < MTemp.nCol)) {
            adaNon0 = false;

            if (MTemp.Matrix[lenNon0][kolom] == 0) {
                
                kolomSearch = lenNon0 + 1;
                while ((kolomSearch < MTemp.nRow) && (!adaNon0)) {
                    if (MTemp.Matrix[kolomSearch][kolom] != 0) {
                        adaNon0 = true;
                        MTemp = rowSwap(MTemp, kolomSearch, lenNon0);
                        det *= -1;
                        lenNon0 += 1;
                    }
                    else{
                        kolomSearch += 1;
                    }
                }
                if (!adaNon0) {
                    kolom += 1;
                }
            }
            else{
                lenNon0 += 1;
            }
        }


        //Kalo awal 0 ya udah 0
        if (MTemp.Matrix[0][0] == 0){
            det = 0;
        }

        //Kalo engga diubah jadi matriks segitiga
        else{
            for (int i = 0; i < MTemp.nCol; i++){
                for (int j = i+1; j < MTemp.nRow; j++){                    
                    MTemp = minKaliBaris(MTemp, j, i, MTemp.Matrix[j][i]/MTemp.Matrix[i][i]);
                }

                //Padetin 0 lagi
                kolom = 0;
                lenNon0 = 0;
                while ((lenNon0 < MTemp.nRow) && (kolom < MTemp.nCol)) {
                    adaNon0 = false;
                    
                    if (MTemp.Matrix[lenNon0][kolom] == 0) {
                        kolomSearch = lenNon0 + 1;
                        while ((kolomSearch < MTemp.nRow) && (!adaNon0)) {
                            if (MTemp.Matrix[kolomSearch][kolom] != 0) {
                                adaNon0 = true;
                                MTemp = rowSwap(MTemp, kolomSearch, lenNon0);
                                det *= -1;
                                lenNon0 += 1;
                            }
                            else{
                                kolomSearch += 1;
                            }
                        }
                        if (!adaNon0) {
                            kolom += 1;
                        }
                    }
                    else{
                        lenNon0 += 1;
                    }
                }

                //diagonal dikaliin
                det *= MTemp.Matrix[i][i];
            }
        }

        //dibuletin 5 angka dibelakang koma
        det = Math.round(det *10000) / 10000;
        return det;
    }

    /* Menghitung Kofaktor */
    public static matrix kofaktor(matrix m) {
        int sign;
        matrix cofactor = new matrix(); // Membuat matriks kofaktor dengan ukuran yang sama
        for (int i = 0; i < m.nRow; i++) { // Loop baris matriks
            if (i % 2 == 0) {
                sign = 1; // Jika baris genap, set tanda positif
            } else {
                sign = -1; // Jika baris ganjil, set tanda negatif
            }
    
            for (int j = 0; j < m.nCol; j++) { // Loop kolom matriks
                matrix subMatrix = createSubMatrix(m, i, j); // Buat submatriks tanpa baris dan kolom i, j
                cofactor.Matrix[i][j] = sign * determinantKofaktor(subMatrix); // Hitung kofaktor dan tambahkan ke matriks kofaktor
                sign = -sign; // Ubah tanda untuk kolom ??? berikutnya
            }
        }
        return cofactor; // Kembalikan matriks kofaktor
    }

    public static double cofactor(matrix mIn, int i, int j){
        double cof;
        cof = detOBE(slice(mIn, i, j));
        if ((i + j) % 2 != 0){
            cof += (-1);
        }
        return cof;
    }

    /* Menghitung Invers Menggunakan Adjoin */
    public static matrix invAdjoint(matrix m){
        matrix adjoin, invers;
        double determinan;
        int i, j;

        determinan = determinantKofaktor(m);
        adjoin = matrixOperation.transpose(kofaktor(m));
        invers = new matrix();

        for(i = 0; i < m.nRow; i++){
            for(j = 0; j < m.nCol; j++){
                invers.Matrix[i][j] = adjoin.Matrix[i][j] / determinan;
            }
        }
        return invers;
    }

    public static matrix inverseAdjoint(matrix mIn){
        // PREKONDISI: MIn matriks persegi, DET mIn != 0
        matrix mOut = new matrix();
        mOut = transpose(matrixCof(mIn));
        for (int i = 0; i < mIn.nCol; i++){
            for (int j = 0; j < mIn.nRow; j++){
                mOut.Matrix[i][j] /= detOBE(mIn);
            }
        }
        return mOut;
    }     
    
    /* Fungsi Untuk Mendapat Matriks Kofaktor Tiap Elemen i, j */
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

    /* Kaidah Cramer */
    public static void kaidahCramer(matrix m){
        matrix mCut = new matrix();
        matrix mhasilB = new matrix();
        double determinanX;
        int i, j;

        if(determinantKofaktor(m) == 0){
            System.out.println("Tidak bisa menggunakan kaidah cramer karena determinan matriks = 0");
        }else{
            // memotong elemen terakhir dari matriks augmented dan masukin ke matriks mCut
            for(i = 0; i < m.nRow; i++){
                for(j = 0; j < m.nCol-1; j++){
                    mCut.Matrix[i][j] = m.Matrix[i][j];
                }
            }

            // bikin matriks yang isinya b
            for(i=0; i<m.nRow; i++){
                mhasilB.Matrix[i][0] = m.Matrix[i][m.nCol-1];
            }

            for(j=0; j<m.nCol-1; j++){
                for(i=0; i<m.nRow; i++){
                    mCut.Matrix[i][j] = mhasilB.Matrix[i][0];
                }
                determinanX = determinantKofaktor(mCut);
                System.out.println("Nilai x" + (j+1) + " = " + determinanX/determinantKofaktor(m));
            }
        }
    }
}