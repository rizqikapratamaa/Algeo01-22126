import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class determinan {
    static Scanner in = new Scanner(System.in);

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
                    det += mIn.Matrix[0][j] * determinanKofaktor(matrixOperation.slice(mIn, 0, j));
                } else {
                    det += (-1) * mIn.Matrix[0][j] * determinanKofaktor(matrixOperation.slice(mIn, 0, j));
                }
            }
        }
        return det;
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

        temp = matrixOperation.cloneMatrix(mIn);
    
        while ((lenNonZero < temp.nRow) && (kolom < temp.nCol)) {
            adaNonZero = false;

            if (temp.Matrix[lenNonZero][kolom] == 0) {
                
                colSearch = lenNonZero + 1;
                while ((colSearch < temp.nRow) && (!adaNonZero)) {
                    if (temp.Matrix[colSearch][kolom] != 0) {
                        adaNonZero = true;
                        temp = matrixOperation.rowSwap(temp, colSearch, lenNonZero);
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
                    temp = matrixOperation.minKaliBaris(temp, j, i, temp.Matrix[j][i]/temp.Matrix[i][i]);
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
                                temp = matrixOperation.rowSwap(temp, colSearch, lenNonZero);
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
        cof = determinan.detOBE(matrixOperation.slice(mIn, i, j));
        if ((i + j) % 2 != 0){
            cof += (-1);
        }
        return cof;
    }
}