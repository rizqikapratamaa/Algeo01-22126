import java.util.*;
import java.io.*;

public class SPL{
    static Scanner in = new Scanner(System.in);

    public static void solveSPL(matrix m){
        /* Menerima matrix gauss/gauss jordan spl */
        int condition;
        matrixOperation.tidyUp(m);
        condition = checkSPL(m);

        System.out.print("\n");
        switch (condition){
            case 0:
            solusiKosong(m);
            break;

            case 1:
            solusiUnik(m);
            break;

            case 2:
            solusiBanyak(m);
            break;

            case 3:
            solusiNone();
            break;
        }
    }

    public static void solveSPLFile(matrix m){
        // Buat output file
        int condition;
        String filename;

        matrixOperation.tidyUp(m);
        condition = checkSPL(m);
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine();

        switch (condition){
            case 0:
            solusiKosongFile(m, filename);
            break;

            case 1:
            solusiUnikFile(m, filename);
            break;

            case 2:
            solusiBanyakFile(m, filename);
            break;

            case 3:
            solusiNoneFile(m, filename);
            break;
        }
    }

    /* Persamaan SPL dari Gauss atau Gauss Jordan */
    public static int checkSPL(matrix m){
        // I.S matriks m adalah matriks gauss/gauss jordan spl
        // 0 = Matriks kosong (semua 0)
        // 1 = Solusi unik
        // 2 = Solusi banyak
        // 3 = Solusi tidak ada
        int x = -999;
        boolean unique;

        if (m.isAllZero()){
            x = 0;
        }
        else if(!solvable(m)){
            x = 3;
        }
        else if (m.nRow == m.nCol-1){
            unique = true;
            for (int i = 0; i < m.nRow; i++){
                if (m.Matrix[i][i] != 1){
                    unique = false;
                }
            }
            if (unique) x = 1;
            else x = 2;
        }
        else{
            x = 2;
        }

        return x;
    }

    // FUNGSI ANTARA SPL
    static matrix removerow0(matrix mIn){
        // Buat ilangin baris 0 semua kalo ada
        // di pake di solusi banyak sama solvable
        matrix temp = new matrix();
        boolean adabaris0 = true;
        int i, j;

        temp = matrixOperation.cloneMatrix(mIn);
        i = mIn.nRow - 1;
        while (adabaris0 && i > -1){
            while (adabaris0 && i > -1){
                j = 0;
                while (adabaris0 && j < mIn.nCol){
                    if (mIn.Matrix[i][j] != 0){   
                    }
                    j += 1;
                }
            }
            if (adabaris0){
                temp = matrixOperation.sliceLastRow(temp);
                i = temp.nRow - 1;
            }
        }
        return temp;
    }

    static boolean solvable(matrix mIn){
        /* Untuk cek apakah matrix punya solusi atau tidak */
        // Kepake di check

        matrix temp = new matrix();
        boolean solvable = false;

        int j = 0;

        temp = removerow0(mIn);

        while (!solvable && j < temp.nCol-1){
            if (temp.Matrix[temp.nCol-1][j] != 0){
                solvable = true;
            }
            else{
                j += 1;
            }
        }

        return solvable;
    }

    public static int cariSatu(matrix mIn, int baris){
        // Buat nyari 1 pertama suatu baris
        // Dipake di solusi banyak
        
        // Kamus Lokal
        int kolom = 0;
        boolean ada = false;

        while ((!ada) && (kolom < mIn.nCol)){
            if (mIn.Matrix[baris][kolom] == 1){
                ada = true;
            }
            else{
                kolom += 1;
            }
        }
        return kolom;
    }

    public static void solusiKosong(matrix mIn){
        char var = 'S';
        char arrayChar[] = new char[mIn.nCol-1];
        int i;

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

        System.out.println("Persamaan linear kosong, semua variabel nilai memenuhi.");
        for (i = 0; i < mIn.nCol-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            System.out.print(", \n");
        }
    }

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
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            // Nulis hasil matrix
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

    public static void solusiUnik(matrix mIn){
        /* Mencari solusi unik dari persamaan gauss */
        // I.S Matrix yang masuk adalah matrix gauss atau gauss-jordan

        // kamus lokal
        int i, j;
        double cache;
        double arrayHasil[] = new double[mIn.nCol - 1];

        // Algortima
        for (i = 0; i < mIn.nCol; i++){
            arrayHasil[i] = 0;
        }

        for (i = mIn.nRow-1; i > -1; i--){
            cache = mIn.Matrix[i][mIn.nCol-1];
            for (j = 0; j < mIn.nCol-1; j++){
                cache -= arrayHasil[j] * mIn.Matrix[i][j];
            }
            arrayHasil[i] = cache;
        }

        for (i = 0; i < mIn.nCol-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            System.out.print(arrayHasil[i]);
            System.out.print(", \n");
        }
    }

    public static void solusiUnikFile(matrix mIn, String filename){
        /* Mencari hasil solusi unik dari persamaan Gauss */
        //prekondisi matriks yang masuk adalah matriks gauss/gauss jordan
            // Kamus Lokal
            int i, j;
            double cache;
            double arrayHasil[] = new double[mIn.nCol-1];
    
            try {
                        // Algoritma
            for(i = 0; i < mIn.nCol-1; i++){
                arrayHasil[i] = 0;
            }
    
            for(i = mIn.nRow-1; i > -1; i--){
    
                cache = mIn.Matrix[i][mIn.nCol-1];
                for(j = i; j < mIn.nCol-1; j++){
                    cache -= arrayHasil[j] * mIn.Matrix[i][j];
                }
                arrayHasil[i] = cache;
            }
                // Buat file
                BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));
    
    
                //nulis hasil matriks
                bw.write("Hasil pengolahan matriks:");
                bw.newLine();
                for (i= 0; i<mIn.nRow; i++){
                    for (j=0; j<mIn.nCol; j++){
                        bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                    }
                bw.newLine();
                }
                bw.newLine();
    
                // Write Perline
                for (i = 0; i < mIn.nCol-1; i++){
                    bw.write("x" + (i+1) + " = " + arrayHasil[i]);
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

    public static void solusiBanyak(matrix mIn){
        /* Mencari hasil solusi unik dari persamaan Gauss */
        // I.S Matriks yang masuk adalah matriks gauss/gauss-jordan

        // Kamus Lokal
        int i, j, k;
        boolean trivial;
        boolean realzero;
        double cache;
        double arrayHasil[] = new double[mIn.nCol-1];
        char arrayChar[] = new char[mIn.nCol-1];
        String arrayString[] = new String[mIn.nCol-1];
        char var;

        // Algoritma
        for(i = 0; i < mIn.nCol-1; i++){
            arrayHasil[i] = 0;
        }
        for(i = 0; i < mIn.nCol-1; i++){
            arrayChar[i] = '/';
        }
        for(i = 0; i < mIn.nCol-1; i++){
            arrayString[i] = "";
        }

        var = 'S';
        mIn = removerow0(mIn);

        for (i = mIn.nRow-1; i > -1; i--){
            cache = mIn.Matrix[i][mIn.nCol-1];
            for (j = cariSatu(mIn, i) + 1; j < mIn.nCol-1; j++){
                if (arrayHasil[j] == 0){
                    realzero = true;
                    for(k = j; k < mIn.nCol-1; k++){
                        if (arrayChar[k] != '/'){
                            realzero = false;
                        }
                    }
                    if (j > 0){
                        for (k = j - 1; k > -1; k--){
                            if (mIn.Matrix[i][k] != 0){
                                realzero = false;
                            }
                        }
                    }
                    if (arrayString[j] != ""){
                        realzero = true;
                    }
                    if (j == mIn.nCol-2){
                        realzero = false;
                    }

                    if (!realzero){
                        if (arrayChar[j] == '/'){
                            arrayChar[j] = var;
                            if (var == 'z'){
                                var = 'A';
                            }
                            else if (var == 'R'){
                                var = 'a';
                            }
                            else var += 1;
                        }
                        double cacheConst = (-1)*mIn.Matrix[i][j];
                        cacheConst += recursion(mIn.nCol-2, cariSatu(mIn, i), i, j, arrayHasil, arrayString,mIn);

                        if (cacheConst > 0){
                            arrayString[cariSatu(mIn, i)] += String.format("+%.2f%c", cacheConst, arrayChar[j]);
                        }
                        else if (cacheConst < 0){
                            arrayString[cariSatu(mIn, i)] += String.format("%.2f%c", cacheConst, arrayChar[j]);
                        }

                    }
                }
                else{
                    cache -= arrayHasil[j] * mIn.Matrix[i][j];
                }
            }
            try{
                arrayHasil[cariSatu(mIn, i)] = cache;
            }catch (Exception e){

            }
        }
        // Cek kalo ada jawaban trivial
        trivial = true;
        for (i = 0; i < mIn.nCol-1; i++){
            if (arrayHasil[i] != 0){
                trivial = false;
            }
        }

        // Print trivial
        if (trivial){
            for (i = 0; i < mIn.nCol-1; i++){
                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
            }
            System.out.print(0);
            System.out.println("atau");
        }

        // Print utama
        for (i = 0; i < mIn.nCol-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            if (arrayHasil[i] == 0){
                realzero = true;
                for (j = i; j < mIn.nRow-1; j++){
                    if (arrayChar[j] != '/'){
                        realzero = true;
                    }
                }
                if (arrayString[i] != ""){
                    realzero = true;
                }
                if(!realzero){
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
                if (realzero && (arrayString[i] == "")){
                    System.out.print(arrayHasil[i]);
                }
            }
            else {
                System.out.print(arrayHasil[i]);
            }
            System.out.print(arrayString[i]);
            System.out.print(", \n");
        }
    }

    public static void solusiBanyakFile(matrix mIn, String filename){
        /* Mencari hasil solusi unik dari persamaan Gauss */
        //prekondisi matriks yang masuk adalah matriks gauss/gauss jordan
            // Kamus Lokal
            int i, j, k;
            boolean trivial;
            boolean nolbeneran;
            double cache;
            double arrayHasil[] = new double[mIn.nCol-1];
            char arrayChar[] = new char[mIn.nCol-1];
            String arrayString[] = new String[mIn.nCol-1];
            char var;
            // Algoritma
            for(i = 0; i < mIn.nCol-1; i++){
                arrayHasil[i] = 0;
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
                    if (arrayHasil[j] == 0) {
                        nolbeneran = true;
                        for(k = j; k < mIn.nCol-1; k++){
                            if (arrayChar[k] != '/'){
                                nolbeneran = false;
                            }
                        }
    
                        if (j > 0){
                            for(k = j-1; k < -1; k--){
                                if (mIn.Matrix[i][k] != 0){
                                    nolbeneran = false;
                                }
                            }
                        }
    
                        if (arrayString[j] != ""){
                            nolbeneran = true;
                        }
                        if (j == mIn.nCol-2){
                            nolbeneran = false;
                        }
    
                        if (!nolbeneran){
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
                            cacheConst += recursion(mIn.nCol-2, cariSatu(mIn, i), i, j, arrayHasil, arrayString, mIn);
                            
                            if (cacheConst > 0){
                                arrayString[cariSatu(mIn, i)] += String.format("+%.2f%c", cacheConst, arrayChar[j]);
                            }
                            else if (cacheConst < 0) {
                                arrayString[cariSatu(mIn, i)] += String.format("%.2f%c", cacheConst, arrayChar[j]);
                            }
                        }
                    }
                    else{
                        cache -= arrayHasil[j] * mIn.Matrix[i][j];
                    }
                }
                try {
                    arrayHasil[cariSatu(mIn, i)] = cache;
                } catch (Exception e) {
    
                }
            }
            
            //cek kalo ada jawaban trivial
            trivial = true;
            for(i = 0; i < mIn.nCol-1; i++){
                if (arrayHasil[i] != 0){
                    trivial = false;
                }
            }
    
            try {
                // Buat file
                BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));
    
                //nulis hasil matriks
                bw.write("Hasil pengolahan matriks:");
                bw.newLine();
                for (i= 0; i<mIn.nRow; i++){
                    for (j=0; j<mIn.nCol; j++){
                        bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                    }
                bw.newLine();
                }
                bw.newLine();
    
                // Write Perline
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
                    if (arrayHasil[i] == 0){
                        nolbeneran = true;
                        for(j = i; j < mIn.nCol-1; j++){
                            if (arrayChar[j] != '/'){
                                nolbeneran = false;
                            }
                        }
                        if (arrayString[i] != ""){
                            nolbeneran = true;
                        }
    
                        if (i > 0){
                            for(j = i-1; j > -1; j--){
                                if (mIn.Matrix[i][j] != 0){
                                    nolbeneran = false;
                                }
                            }
                        }
        
                        if (!nolbeneran){
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
    
                        if (nolbeneran && arrayString[i] == ""){
                            bw.write(arrayHasil[i] + "");
                        }
                    }
    
                    
                    else {
                        bw.write(arrayHasil[i] + "");
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

    public static void solusiNone(){
        System.out.println("Persamaan linear tidak memiliki solusi");
    }

    public static void solusiNoneFile(matrix mIn, String filename){
        int i, j;

        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            //nulis hasil matriks
            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<mIn.nRow; i++){
                for (j=0; j<mIn.nCol; j++){
                    bw.write(mIn.Matrix[i][j] + ((j == mIn.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();


            // Write
            bw.write("Persamaan linear tidak memiliki solusi.");
            bw.newLine();
            bw.flush();
            bw.close();
        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static double recursion(int toplimit, int bottomlimit, int row, int varCol, double arrayHasil[], String arrayString[], matrix mIn){
        double cacheConst = 0;
        for (int k = toplimit; k > bottomlimit; k--){
            if ((arrayHasil[k] != 0 || arrayString[k] != "") && mIn.Matrix[row][k] != 0){
                int baris1 = mIn.nRow-1;
                while (mIn.Matrix[baris1][k] != 1){
                    baris1 -= 1;
                }

                cacheConst = cacheConst + mIn.Matrix[row][k]*(mIn.Matrix[baris1][varCol]) - mIn.Matrix[row][k]*recursion(toplimit, cariSatu(mIn, baris1), baris1, varCol, arrayHasil, arrayString, mIn);
            }
        }
        return cacheConst;
    }
}