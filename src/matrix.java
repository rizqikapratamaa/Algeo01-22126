/* ADT Matriks dan IO Matriks*/

import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class matrix {
    /* Membuat matriks dengan kapasitas maksimal 100 */
    Scanner in = new Scanner(System.in);
    int CAPACITY = 100;
    double[][] Matrix = new double[CAPACITY][CAPACITY];

    public int nCol = 0;
    public int nRow = 0;

    /* METHOD */

    // Prosedur untuk mengisi elemen matriks
    public void readMatrix(int m, int n){
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

    // Prosedur untuk membaca/mengisi elemen matriks dari file
    public void readFileMatrix(String filename){
        File file = new File(filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");

                if (tokens.length > 0) {
                    if (this.nCol == 0) {
                        this.nCol = tokens.length;
                    } else if (tokens.length != this.nCol) {
                        System.err.println("Invalid matrix format: Rows have different column counts.");
                        return;
                    }

                    for (int j = 0; j < this.nCol; j++) {
                        this.Matrix[this.nRow][j] = Double.parseDouble(tokens[j]);
                    }

                    this.nRow++;
                }
            }
            for (int i = 0; i < this.nRow; i++) {
                for (int j = 0; j < this.nCol; j++) {
                    System.out.print(this.Matrix[i][j] + " ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the file: " + e.getMessage());
        }
    }

    // Prosedur untuk membaca/mengisi elemen matriks dari file yang baris paling bawahnya tidak lengkap
    public void readFileMatrixBolong(String filename, int nKosong) {

        /* DEFAULT nKosong
         * Interpolasi Polinom: 1
         * Bicubic Interpolation: 2
         * Regresi Linear Berganda: 1
         */

        // PREKONDISI: nbBolong < nCol

        File file = new File(filename);
        int i,j;
        int countElmt;

        
        try{ // Untuk validasi dan dapat error message
            Scanner bacafile = new Scanner (file);
            countElmt = 0;
    
            // Menghitung banyaknya kolom dan baris
            while(bacafile.hasNextLine()){
                this.nRow++;
                
                // Membaca banyak double
                Scanner bacakolom = new Scanner(bacafile.nextLine());
                    while(bacakolom.hasNextDouble()){
                        countElmt++;
                        bacakolom.nextDouble();
                    }
                }
    
            // Testing
            this.nCol = (countElmt + nKosong) / this.nRow ;
    
            // Close scanner
            bacafile.close();
    
            // Membaca integer dari file
            bacafile = new Scanner (file);
            for(i=0; i<this.nRow; i++){
                for(j=0; j<this.nCol; j++){
                    if(bacafile.hasNextDouble()){
                        this.Matrix[i][j] = bacafile.nextDouble();
                    }
                }
            }

            // Mengisi bagian yang kosong dengan -999.0
            for (int k = this.nCol - 1; k >= this.nCol - nKosong; k--) {
                this.Matrix[this.nRow - 1][k] = -999.0;
            }

        } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());}
    }
    
    // Prosedur untuk menampilkan matriks dari file pada layar
    public void writeMatrixFile(matrix m){
        int i, j;
        String filename;

        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            for (i= 0; i < m.nRow; i++){
                for (j=0; j < m.nCol; j++){
                    bw.write(m.Matrix[i][j] + ((j == m.nCol-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Fungsi untuk mengecek apakah semua elemen di dalam matriks bernilai nol */
    public boolean isAllZero() {
        int i, j;
        boolean foundNonZero;
        foundNonZero = false;
        for (i = 0; i < this.nRow && !foundNonZero; i++) {
            for (j = 0; j < this.nCol && !foundNonZero; j++) {
                foundNonZero = (this.Matrix[i][j] != 0);
            }
        }
        return !foundNonZero;
    }

    // Prosedur untuk menampilkan matriks pada layar
    public void writeMatrix(){
        int i, j;
        for(i = 0; i < this.nRow; i++) {
            System.out.print("| ");
            for (j = 0; j < this.nCol; j++) {
                System.out.print(this.Matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }  
    }
}