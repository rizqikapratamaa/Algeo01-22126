/* Kelas ADT Matriks */
// Import library

import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class matrix {
    /* CreateMatrix dengan maximal CAPACITY 100 */
    Scanner in = new Scanner(System.in);
    int CAPACITY = 100;
    double[][] Matrix = new double[CAPACITY][CAPACITY];

    public int nCol = 0;
    public int nRow = 0;

    // Method:
    public void readMatrix(int m, int n){
        // Fungsi untuk mengisi elemen matriks
        // untuk baca matriks maksimal input 50 kolom, orang gila mana juga yang mau masukkin matrix 50 kolom lewat terminal

        // Kamus Lokal
        String line;
        String[] row = new String[100];
        double[] cache = new double[50];

        int i, j;
        boolean valid = false;

        this.nRow = m;
        this.nCol = n;

        for (i = 0; i < m; i++){
            do{
                valid = false;
                line = in.nextLine();
                row = line.split(" ");

                try{
                    for (j = 0; j < row.length; j++){
                        cache[j] = Double.parseDouble(row[j]);
                    }
                    if (row.length == nCol){
                        for (j = 0; j < row.length; j++){
                            this.Matrix[i][j] = cache[j];
                        }
                        valid = true;
                    }
                    else{
                        System.out.println("Jumlah input tidak valid, masukkan " + n + " buah bilangan.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Terdapat input yang tidak valid, mohon ulangi input.");
                    valid = false;
                }
            } while (!valid);
        }
    }

    /* Baca matrix dari file */
    public void readFileMatrix(String filename){
        // Kamus lokal
        File file = new File(filename);
        int i, j;
        int countElmt;

        // Algoritma
        try{ //Untuk validasi dan dapet error massage error
            Scanner bacafile = new Scanner (file);
            countElmt = 0;

            // Menghitung banyak baris dan kolom
            while (bacafile.hasNextLine()){
                this.nRow++;

                // Membaca banyak double
                Scanner bacakolom = new Scanner (bacafile.nextLine());
                while (bacakolom.hasNextDouble()){
                    countElmt++;
                    bacakolom.nextDouble();
                }
            }

            // Testing
            this.nCol = (countElmt + this.nRow - 1) / this.nRow;

            // Close scanner
            bacafile.close();

            // Membaca integer dari file
            bacafile = new Scanner (file);
            for (i = 0; i < this.nRow; i++){
                for (j = 0; j < this.nCol; i++){
                    if(bacafile.hasNextDouble()){
                        this.Matrix[i][j] = bacafile.nextDouble();
                    }
                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void readFileMatrixBolong(String filename, int nKosong){
        // Membuat matriks dengan bagian baris terbawah tidak lengkap sebanyak nbBolong element
        /* DEFAULT nKosong
         * Interpolasi Polinom: 1
         * Bicubic Interpolation: 2
         * Regresi Linear Berganda: 1
         */
        // PREKONDISI: nbBolong < nCol

        // Kamus lokal
        File file = new File(filename);
        int i, j;
        int countElmt;

        // Algoritma

        try{ // untuk validasi dan error message
            Scanner bacafile = new Scanner (file);
            countElmt = 0;
            
            // Menghitung banyaknya kolom dan baris
            while (bacafile.hasNextLine()){
                this.nCol++;

                // Membaca banyak double
                Scanner bacakolom = new Scanner (bacafile.nextLine());
                while (bacakolom.hasNextDouble()){
                    countElmt++;
                    bacakolom.nextDouble();
                }
            }

            // Testing
            this.nCol = (countElmt + nKosong) / this.nRow;

            // Close scanner
            bacafile.close();

            // Membaca integer dari file
            bacafile = new Scanner (file); // Refresh dari atas
            for (i = 0; i < this.nRow; i++){
                for (j = 0; j < this.nCol; j++){
                    if (bacafile.hasNextDouble()){
                        this.Matrix[i][j] = bacafile.nextDouble();
                    }
                }
            }

            // Mengisi bagian yang kosong dengan -999.0
            for (int k = this.nCol - 1; k >= this.nCol - nKosong; k--){
                this.Matrix[this.nRow - 1][k] = -999.0;
            }
        
        // Jika file tidak ditemukan, maka output error message
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /* Write File dari matriks */
    public void writeMatrixFile(matrix m){
        // Kamus Lokal
        int i, j;
        String filename;

        // Algoritma
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            // Write Perline
            for (i= 0; i < m.nRow; i++){
                for (j=0; j < m.nCol; j++){
                    bw.write(m.Matrix[i][j] + ((j == m.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /* Fungsi untuk mendapatkan komponen matrix (siapa tau perlu) */
    public double getComponent(int n, int m){
        return this.Matrix[n][m];
    }

    /* Apakah matrix penuh? */
    public boolean fullRow(){
        return (this.nRow == CAPACITY);
    }
    public boolean fullCol(){
        return (this.nCol == CAPACITY);
    }

    /* Mengecek apakah semua elemen di dalam matrix bernilai nol */
    public boolean isAllZero(){
        int i, j;
        boolean foundNonZero;
        foundNonZero = false;
        for (i = 0; i < this.nRow && !foundNonZero; i++){
            for (j = 0; j < this.nCol && !foundNonZero; j++){
                foundNonZero = (this.Matrix[i][j] != 0);
            }
        }
        return !foundNonZero;
    }

    public void writeMatrix(){
        /* I.S. Matriks terdefinisi */
        /* Menuliskan matriks pada layar */
        // Kamus Lokal
        int i, j;
        // Algoritma
        for(i = 0; i < this.nRow; i++) {
            System.out.print("| ");
            for (j = 0; j < this.nCol; j++) {
                System.out.print(this.Matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }  
    }

    /* Mengubah kapasitas matrix */
    public void resetCap(int newCap){
        this.CAPACITY = newCap;
        this.Matrix = new double[CAPACITY][CAPACITY];
        this.nRow = 0;
        this.nCol = 0;
    }

    public static double getElement(matrix matriks, int row, int col) {
        if (row < 0 || row >= matriks.nRow || col < 0 || col >= matriks.nCol) {
            System.out.println("Indeks baris atau kolom tidak valid.");
        }

        return matriks.Matrix[row][col];
    }
    
    public static void setElement(matrix matriks, int row, int col, double value) {
        if (row < 0 || row >= matriks.nRow || col < 0 || col >= matriks.nCol) {
            System.out.println("Indeks baris atau kolom tidak valid.");
        }
    
        matriks.Matrix[row][col] = value;
    }
    

}
