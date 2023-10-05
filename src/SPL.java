import java.util.*;
import java.io.*;

public class SPL{
    public int nEff;
    public double[] x;
    public String[] answer;
    /* Konstruktor SPL */
    public SPL(){
        this.nEff = 0; // Jumlah elemen valid
        this.x = new double[100000]; // Inisialisasi x
        this.answer = new String[100000];
    }

    static Scanner in = new Scanner(System.in);

    // Prosedur untuk mendapat solusi SPL
    public static void solveSPL(matrix mIn){
        // Menerima matriks gauss/gauss jordan SPL
        int condition;
        matrixOperation.tidyUp(mIn);
        condition = checkSPL(mIn);

        System.out.print("\n");
        switch (condition){
            case 0:
            solusiKosong(mIn);
            break;

            case 1:
            solusiUnik(mIn);
            break;

            case 2:
            solusiBanyak(mIn);
            break;

            case 3:
            solusiNone();
            break;
        }
    }

    // Prosedur untuk membuat file dari solusi SPL
    public static void solveSPLFile(matrix mIn){
        int condition;
        String filename;

        matrixOperation.tidyUp(mIn);
        condition = checkSPL(mIn);
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";

        switch (condition){
            case 0:
            solusiKosongFile(mIn, filename);
            break;

            case 1:
            solusiUnikFile(mIn, filename);
            break;

            case 2:
            solusiBanyakFile(mIn, filename);
            break;

            case 3:
            solusiNoneFile(mIn, filename);
            break;
        }
    }

    // Prosedur untuk implementasi solusi inverse
    public static void solveWithInverse(matrix mIn){
        matrix m1 = new matrix();
        matrix m2 = new matrix();

        m1 = matrixOperation.sliceLastCol(mIn);
        m2 = matrixOperation.takeLastCol(mIn);

        System.out.print("\n");
        if (m1.nRow != m1.nCol){
            System.out.println("Matriks memerlukan " + m1.nCol + " persamaan untuk dapat diselesaikan");
        }
        else if (determinan.determinanKofaktor(m1) == 0){
            System.out.println("Matriks tidak memiliki inverse.");
        }
        else{
            m1 = balikan.inverseAdjoint(m1);
            m2 = matrixOperation.multiplyMatrix(m1, m2);

            System.out.println("Solusi: ");
            for (int i = 0; i < m2.nRow; i++){
                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
                System.out.print(m2.Matrix[i][0]);
                System.out.print(", \n");
            }
        }   
    }
    
    // Prosedur untuk membuat file dari solusi inverse
    public static void solveWithInverseFile(matrix mIn){
        matrix m1 = new matrix();
        matrix m2 = new matrix();

        int i, j;

        String filename;

        m1 = matrixOperation.takeLastCol(mIn);
        m2 = matrixOperation.sliceLastCol(mIn);

        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

            bw.write("Matriks: ");
            bw.newLine();
            for (i = 0; i < mIn.nRow; i++){
                for (j = 0; j < mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol - 1) ? "" : " "));
                }
            bw.newLine();
            }

            bw.newLine();
            if (m2.nRow != m2.nCol){
                bw.write("Matriks memerlukan " + m2.nCol + " persamaan untuk dapat diselesaikan");
            }
            else if (determinan.determinanKofaktor(m2) == 0){
                bw.write("Tidak dapat menggunakan metode matriks balikan!");
                bw.newLine();
            }
            else{
                m2 = balikan.inverseAdjoint(mIn);
                m1 = matrixOperation.multiplyMatrix(m2, m1);
                bw.write("Solusi: ");
                for (i = 0; i < m1.nRow; i++){
                    bw.write("x" + (i+1) + " = " + m1.Matrix[i][0]);
                    if (i == m1.nRow - 1){
                        bw.newLine();
                    }
                    else{
                        bw.write(", ");
                        bw.newLine();
                    }
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Prosedur untuk implementasi solusi kaidah cramer
    public static void solveCramer(matrix mIn){
        matrix m1 = new matrix();
        matrix m2 = new matrix();
        matrix temp = new matrix();

        m1 = matrixOperation.takeLastCol(mIn);
        m2 = matrixOperation.sliceLastCol(mIn);
        temp = matrixOperation.cloneMatrix(m2);

        System.out.print("\n");
        if (m2.nRow != m2.nCol){
            System.out.println("Matriks memerlukan " + m2.nCol + " persamaan untuk dapat diselesaikan.");
        }
        else if (determinan.determinanKofaktor(m2) == 0){
            System.out.println("Tidak dapat menggunakan kaidah Cramer!");
        }

        else{
            System.out.println("Solusi: ");
            for (int i = 0; i < m1.nRow; i++){
                temp = matrixOperation.cramerSwap(m2, m1, i);

                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
                System.out.print(determinan.determinanKofaktor(temp)/determinan.determinanKofaktor(m2));
                System.out.print(", \n");
            }
        }
    }

    // Prosedur untuk membuat file dari solusi kaidah crame
    public static void solveCramerFile(matrix mIn){
        matrix m1 = new matrix();
        matrix m2 = new matrix();
        matrix temp = new matrix();

        int i, j;
        String filename;

        m1 = matrixOperation.takeLastCol(mIn);
        m2 = matrixOperation.sliceLastCol(mIn);
        temp = matrixOperation.cloneMatrix(m2);

        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

            bw.write("Matriks:");
            bw.newLine();
            for (i = 0; i < mIn.nRow; i++){
                for (j = 0; j < mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }

            bw.newLine();
            if (m2.nRow != m2.nCol){
                bw.write("Matriks memerlukan " + m2.nCol + " persamaan untuk dapat diselesaikan.");
                bw.newLine();
            }
            else if (determinan.determinanKofaktor(m2) == 0){
                bw.write("Matriks tidak memiliki inverse sehingga tidak bisa menggunakan kaidah Cramer.");
                bw.newLine();
            }
            else{
                System.out.println("Solusi: ");
                for (i = 0; i < m1.nRow; i++){
                    temp = matrixOperation.cramerSwap(m2, m1, i);
                    bw.write("x" + (i+1) + " = " + (determinan.determinanKofaktor(temp)/determinan.determinanKofaktor(m2)));
                    if (i == m1.nRow-1) {
                        bw.newLine();
                    }
                    else{
                        bw.write(", ");
                        bw.newLine();
                    }
                }
            }

            bw.flush();
            bw.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Fungsi untuk mengecek jenis solusi matriks
    public static int checkSPL(matrix mIn) {
        // PREKONDISI: matriks mIn adalah matriks gauss/gauss-jordan SPL
        // 0 = Matriks kosong (semua 0)
        // 1 = Solusi unik
        // 2 = Solusi banyak
        // 3 = Solusi tidak ada
        int x = -999;
        boolean unique;

        if (mIn.isAllZero()) {
            x = 0;
        }
        else if (!solvable(mIn)){
            x = 3;
        }
        else if (mIn.nRow == mIn.nCol-1){
            unique = true;
            for (int i = 0; i < mIn.nRow; i++){
                if (mIn.Matrix[i][i] != 1){
                    unique = false;
                }
            }
            if (unique){
                x = 1;
            }
            else x = 2;
        }
        else{
            x = 2;
        }

        return x;
    }

    // Fungsi untuk implementasi metode eliminasi Gauss
    public static matrix gauss(matrix mIn){
        matrix mOut = new matrix();
        int column = 0;
        int row = 0;
        int i;
        
        matrixOperation.tidyUp(mOut);
        mOut = matrixOperation.compactzero(mIn);
        while (column < mOut.nCol-1) {
            if (mOut.Matrix[row][column] == 0) {
                column += 1;
            }
            else{
                for(i = row + 1; i < mOut.nRow; i++){
                    mOut = matrixOperation.minKaliBaris(mOut, i, row, mOut.Matrix[i][column]/mOut.Matrix[row][column]);
                }
                mOut = matrixOperation.rowXConst(mOut, row, 1/mOut.Matrix[row][column]);

                mOut = matrixOperation.compactzero(mOut);

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
        matrixOperation.tidyUp(mOut);
        while (column < mOut.nCol-1){
            if (mOut.Matrix[row][column] == 0){
                column += 1;
            }
            else{
                for (i = 0; i < row; i++){
                    if (i != row){
                        mOut = matrixOperation.minKaliBaris(mOut, i, row, mOut.Matrix[i][column]/mOut.Matrix[row][column]);
                    }
                }
                column += 1;
                row += 1;
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
        temp = matrixOperation.cloneMatrix(m2);
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
        matrixOperation.tidyUp(mOut);

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
                    mOut = matrixOperation.rowSwap(mOut, row, swapRow);
                } else {
                    // Tidak ada baris yang bisa ditukar, lanjut ke kolom berikutnya
                    column += 1;
                    continue;
                }
            }

            // Buat elemen utama menjadi 1
            double pivot = mOut.Matrix[row][column];
            mOut = matrixOperation.rowXConst(mOut, row, 1.0 / pivot);

            // Nolkan elemen-elemen di atas dan di bawah elemen utama
            for (int i = 0; i < mOut.nRow; i++) {
                if (i != row) {
                    double factor = -mOut.Matrix[i][column];
                    mOut = matrixOperation.minKaliBaris(mOut, i, row, factor);
                }
            }

            // Pindah ke kolom berikutnya
            column += 1;
            row += 1;
        }

        return mOut;
    }

    /* FUNGSI ANTARA SPL */
    // Fungsi untuk menghapus baris yang elemennya 0 semua
    static matrix removerow0 (matrix mIn){
        // Dipakai di solusi banyak & solvable
        matrix temp = new matrix();
        boolean adabaris0 = true;
        int i, j;

        temp = matrixOperation.cloneMatrix(mIn);

        i = mIn.nRow-1;
        while (adabaris0 && i > -1){
            while (adabaris0 && i > -1) {

                j = 0;
                while (adabaris0 && j < mIn.nCol){
                    if(mIn.Matrix[i][j] != 0){
                        adabaris0 = false;
                    }
                    j += 1;
                }

                if (adabaris0){
                    temp = matrixOperation.sliceLastRow(temp);
                    i = temp.nRow-1;
                }
            }
        }

        return temp;
    }

    // Fungsi untuk mengecek apakah matriks punya solusi atau tidak
    static boolean solvable(matrix mIn){
        // Dipakai di check
        matrix temp = new matrix();
        boolean solvable = false;
        int j;

        temp = removerow0(mIn);

        j = 0;
        while (!solvable && j < temp.nCol-1){
            if (temp.Matrix[temp.nRow-1][j] != 0){
                solvable = true;
            }
            else{
                j += 1;
            }
        }

        return solvable;
    }

    // Fungsi untuk mencari 1 pertama di suatu baris
    public static int cariSatu(matrix mIn, int row){
        // Dipakai di solusi banyak
        int column;
        boolean adasatu = false;

        column = 0;
        while ((!adasatu) && (column < mIn.nCol)){
            if (mIn.Matrix[row][column] == 1){
                adasatu = true;
            }
            else{
                column += 1;
            }
        }

        return column;
    }

    // Prosedur untuk mencetak solusi kosong
    public static void solusiKosong(matrix mIn){
        char var = 'S';
        char arrayChar[] = new char[mIn.nCol-1];
        int i;
        for(i = mIn.nCol-2; i > -1; i--){
            arrayChar[i] = var;
            if (var == 'Z'){
                var = 'A';
            }
            else if (var == 'R'){
                var = 'a';
            }
            else var += 1;
        }

        System.out.println("Persamaan linear kosong, semua variabel nilai memenuhi.");
        for(i = 0; i < mIn.nCol-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            System.out.print(arrayChar[i]);
            System.out.print(", \n");
        }
    }

    // Prosedur untuk menulis solusi kosong di file
    public static void solusiKosongFile(matrix mIn, String filename){
        char var = 'S';
        char arrayChar[] = new char[mIn.nCol-1];
        int i, j;
        for (i = mIn.nCol-2; i > -1; i--){
            arrayChar[i] = var;
            if (var == 'Z'){
                var = 'A';
            }
            else if (var == 'R'){
                var = 'a';
            }
            else var += 1;
        }

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

            bw.write("Hasil pengolahan matriks: ");
            bw.newLine();
            for (i = 0; i < mIn.nRow; i++){
                for (j = 0; j < mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();

            // Write perline
            bw.write("Persamaan linear kosong, semua variabel nilai memenuhi.");
            bw.newLine();
            for (i = 0; i < mIn.nCol-1; i++){
                bw.write("x" + (i+1) + " = " + arrayChar[i]);
                if (i == mIn.nCol-2){
                    bw.newLine();
                }
                else{
                    bw.write(", ");
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Prosedur untuk mencari solusi unik dari persamaan gauss
    public static void solusiUnik(matrix mIn){
        int i, j;
        double cache;
        double arrayRes[] = new double[mIn.nCol-1];
        
        for(i = 0; i < mIn.nCol-1; i++){
            arrayRes[i] = 0;
        }

        for(i = mIn.nRow-1; i > -1; i--){

            cache = mIn.Matrix[i][mIn.nCol-1];
            for(j = i; j < mIn.nCol-1; j++){
                cache -= arrayRes[j] * mIn.Matrix[i][j];
            }

            arrayRes[i] = cache;
        }

                
        for(i = 0; i < mIn.nCol-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            System.out.print(arrayRes[i]);
            System.out.print(", \n");
        }
    }

    // Prosedur untuk menulis file solusi unik dari persamaan gauss
    public static void solusiUnikFile(matrix mIn, String filename){
        int i, j;
        double cache;
        double arrayRes[] = new double[mIn.nCol-1];

        try {
            for(i = 0; i < mIn.nCol-1; i++){
                arrayRes[i] = 0;
        }

        for(i = mIn.nRow-1; i > -1; i--){

            cache = mIn.Matrix[i][mIn.nCol-1];
            for(j = i; j < mIn.nCol-1; j++){
                cache -= arrayRes[j] * mIn.Matrix[i][j];
            }
            arrayRes[i] = cache;
        }
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<mIn.nRow; i++){
                for (j=0; j<mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();

            bw.write("Solusi: ");
            for (i = 0; i < mIn.nCol-1; i++){
                bw.write("x" + (i+1) + " = " + arrayRes[i]);
                if (i == mIn.nCol-2) {
                    bw.newLine();
                }
                else{
                    bw.write(", ");
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Prosedur untuk mencari solusi banyak dari persamaan gauss
    public static void solusiBanyak(matrix mIn){
        int i, j, k;
        boolean trivial;
        boolean realzerp;
        double cache;
        double arrayRes[] = new double[mIn.nCol-1];
        char arrayChar[] = new char[mIn.nCol-1];
        String arrayString[] = new String[mIn.nCol-1];
        char var;
        // Algoritma
        for(i = 0; i < mIn.nCol-1; i++){
            arrayRes[i] = 0;
        }for(i = 0; i < mIn.nCol-1; i++){
            arrayChar[i] = '/';
        } for(i = 0; i < mIn.nCol-1; i++){
            arrayString[i] = "";
        }

        var = 'S';
        mIn = removerow0(mIn);

        for(i = mIn.nRow-1; i > -1; i--){

            cache = mIn.Matrix[i][mIn.nCol-1];

            for(j = cariSatu(mIn, i) + 1; j < mIn.nCol-1; j++){
                if (arrayRes[j] == 0) {
                    realzerp = true;
                    for(k = j; k < mIn.nCol-1; k++){
                        if (arrayChar[k] != '/'){
                            realzerp = false;
                        }
                    }

                    if (j > 0){
                        for(k = j-1; k > -1; k--){
                            if (mIn.Matrix[i][k] != 0){
                                realzerp = false;
                            }
                        }
                    }

                    if (arrayString[j] != ""){
                        realzerp = true;
                    }
                    if (j == mIn.nCol-2){
                        realzerp = false;
                    }

                    if (!realzerp){
                        if  (arrayChar[j] == '/'){
                            arrayChar[j] = var;
                            if (var == 'Z'){
                                var = 'A';
                            }
                            else if (var == 'R'){
                                var = 'a';
                            }
                            else var += 1;
                        }
                        double cacheConst = (-1)*mIn.Matrix[i][j];
                        cacheConst += recursion(mIn.nCol-2, cariSatu(mIn, i), i, j, arrayRes, arrayString, mIn);
                        
                        if (cacheConst > 0){
                            arrayString[cariSatu(mIn, i)] += String.format("+%.2f%c", cacheConst, arrayChar[j]);
                        }
                        else if (cacheConst < 0) {
                            arrayString[cariSatu(mIn, i)] += String.format("%.2f%c", cacheConst, arrayChar[j]);
                        }
                    }
                }
                else{
                    cache -= arrayRes[j] * mIn.Matrix[i][j];
                }
            }
            try {
                arrayRes[cariSatu(mIn, i)] = cache;
            } catch (Exception e) {

            }
        }
        trivial = true;
        for(i = 0; i < mIn.nCol-1; i++){
            if (arrayRes[i] != 0){
                trivial = false;
            }
        }
        if (trivial) {
            for(i = 0; i < mIn.nCol-1; i++){
                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
            }
            System.out.print(0);
            System.out.println("atau");
        }
        for(i = 0; i < mIn.nCol-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            if (arrayRes[i] == 0){
                realzerp = true;
                for(j = i; j < mIn.nCol-1; j++){
                    if (arrayChar[j] != '/'){
                        realzerp = false;
                    }
                }
                if (arrayString[i] != ""){
                    realzerp = true;
                }

                if (!realzerp){
                    if (arrayChar[i] == '/'){
                        arrayChar[i] = var;
                        if (var == 'Z'){
                            var = 'A';
                        }
                        else if (var == 'R'){
                            var = 'a';
                        }
                        else var += 1;
                    }
                    System.out.print(arrayChar[i] + "");
                }
                if (realzerp && (arrayString[i] == "")){
                    System.out.print(arrayRes[i]);
                }
            }
            else {
                System.out.print(arrayRes[i]);
            }
            System.out.print(arrayString[i]);
            System.out.print(", \n");
        }
    }

    // Prosedur untuk menulis file solusi banyak dari persamaan gauss
    public static void solusiBanyakFile(matrix mIn, String filename){
        int i, j, k;
        boolean trivial;
        boolean realzerp;
        double cache;
        double arrayRes[] = new double[mIn.nCol-1];
        char arrayChar[] = new char[mIn.nCol-1];
        String arrayString[] = new String[mIn.nCol-1];
        char var;

        for(i = 0; i < mIn.nCol-1; i++){
            arrayRes[i] = 0;
        }for(i = 0; i < mIn.nCol-1; i++){
            arrayChar[i] = '/';
        } for(i = 0; i < mIn.nCol-1; i++){
            arrayString[i] = "";
        }

        var = 'S';
        mIn = removerow0(mIn);

        for(i = mIn.nRow-1; i > -1; i--){

            cache = mIn.Matrix[i][mIn.nCol-1];

            for(j = cariSatu(mIn, i) + 1; j < mIn.nCol-1; j++){
                if (arrayRes[j] == 0) {
                    realzerp = true;
                    for(k = j; k < mIn.nCol-1; k++){
                        if (arrayChar[k] != '/'){
                            realzerp = false;
                        }
                    }

                    if (j > 0){
                        for(k = j-1; k < -1; k--){
                            if (mIn.Matrix[i][k] != 0){
                                realzerp = false;
                            }
                        }
                    }

                    if (arrayString[j] != ""){
                        realzerp = true;
                    }
                    if (j == mIn.nCol-2){
                        realzerp = false;
                    }

                    if (!realzerp){
                        if  (arrayChar[j] == '/'){
                            arrayChar[j] = var;
                            if (var == 'Z'){
                                var = 'A';
                            }
                            else if (var == 'R'){
                                var = 'a';
                            }
                            else var += 1;
                        }
                        double cacheConst = (-1)*mIn.Matrix[i][j];
                        cacheConst += recursion(mIn.nCol-2, cariSatu(mIn, i), i, j, arrayRes, arrayString, mIn);
                        
                        if (cacheConst > 0){
                            arrayString[cariSatu(mIn, i)] += String.format("+%.2f%c", cacheConst, arrayChar[j]);
                        }
                        else if (cacheConst < 0) {
                            arrayString[cariSatu(mIn, i)] += String.format("%.2f%c", cacheConst, arrayChar[j]);
                        }
                    }
                }
                else{
                    cache -= arrayRes[j] * mIn.Matrix[i][j];
                }
            }
            try {
                arrayRes[cariSatu(mIn, i)] = cache;
            } catch (Exception e) {

            }
        }
        
        trivial = true;
        for(i = 0; i < mIn.nCol-1; i++){
            if (arrayRes[i] != 0){
                trivial = false;
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<mIn.nRow; i++){
                for (j=0; j<mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();

            if (trivial) {
                for(i = 0; i < mIn.nCol-1; i++){
                    bw.write("x" + (i+1) + " = " + 0);
                }
                bw.newLine();
                bw.write("atau");
                bw.newLine();
            }

            for(i = 0; i < mIn.nCol-1; i++){
                bw.write("x" + (i+1) + " = ");
                if (arrayRes[i] == 0){
                    realzerp = true;
                    for(j = i; j < mIn.nCol-1; j++){
                        if (arrayChar[j] != '/'){
                            realzerp = false;
                        }
                    }
                    if (arrayString[i] != ""){
                        realzerp = true;
                    }

                    if (i > 0){
                        for(j = i-1; j > -1; j--){
                            if (mIn.Matrix[i][j] != 0){
                                realzerp = false;
                            }
                        }
                    }
    
                    if (!realzerp){
                        if (arrayChar[i] == '/'){
                            arrayChar[i] = var;
                            if (var == 'Z'){
                                var = 'A';
                            }
                            else if (var == 'R'){
                                var = 'a';
                            }
                            else var += 1;
                        }
                        bw.write(arrayChar[i]);
                    }

                    if (realzerp && arrayString[i] == ""){
                        bw.write(arrayRes[i] + "");
                    }
                }

                
                else {
                    bw.write(arrayRes[i] + "");
                }
                bw.write(arrayString[i]);
                bw.write((i == mIn.nCol-2) ? "\n" : ", \n");
            }
            bw.flush();
            bw.close();
        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Prosedur untuk menampilkan tidak ada solusi
    public static void solusiNone(){
        System.out.println("Solusi tidak ada.");
    }

    // Prosedur untuk menulis file tidak ada solusi
    public static void solusiNoneFile(matrix mIn, String filename){
        int i, j;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<mIn.nRow; i++){
                for (j=0; j<mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();
            bw.write("Solusi tidak ada.");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Fungsi untuk melakukan perhitungan rekursif yang terkait dengan manipulasi matriks dan sistem persamaan linear
    public static double recursion(int toplimit, int bottomlimit, int row, int varCol, double arrayHasil[], String arrayString[], matrix mIn){
        double cacheConst = 0;
        for (int k = toplimit; k > bottomlimit; k--){
            if ((arrayHasil[k] != 0 || arrayString[k] != "") && mIn.Matrix[row][k] != 0){
                int row1 = mIn.nRow-1;
                while (mIn.Matrix[row1][k] != 1) {
                    row1 -=1;
                }
                cacheConst = cacheConst + mIn.Matrix[row][k]*(mIn.Matrix[row1][varCol]) - mIn.Matrix[row][k] * recursion(toplimit, cariSatu(mIn, row1), row1, varCol, arrayHasil, arrayString, mIn);
            }
        }
        return cacheConst;
    }
}