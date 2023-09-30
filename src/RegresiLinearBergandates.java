import java.util.Scanner;
import java.io.*;
public class RegresiLinearBergandates {
    static Scanner in = new Scanner(System.in);
    public static matrix inputKeyboard(){
        int n, m;
        System.out.print("Masukkan jumlah peubah x (n): ");
        n = Integer.parseInt(in.nextLine());
        System.out.print("Masukkan jumlah sample (m): ");
        m = Integer.parseInt(in.nextLine());

        matrix mtx = new matrix();
        mtx.nRow = m + 1;
        mtx.nCol = n + 1;

        for (int i = 0; i < mtx.nRow; i++){
            for (int j = 0; j < mtx.nCol; j++){
                if (i != mtx.nRow - 1){
                    if (j != mtx.nCol - 1){
                        System.out.print("Masukkan nilai x" + (j + 1) + " ke-" + (i + 1) + ": ");
                    }
                    else{
                        System.out.print("Masukkan nilai y sampe ke-" + (i + 1) + ": ");
                    }
                    mtx.Matrix[i][j] = Double.parseDouble(in.nextLine());
                }
                else{
                    if (j != mtx.nCol - 1){
                        System.out.print("Masukkan nilai x" + (j + 1) + "ingin dihampiri nilainya:");
                        mtx.Matrix[i][j] = Double.parseDouble(in.nextLine());
                    }
                }
            }
        }
        return mtx;
    }

    public static matrix xnm(matrix mtx){
        matrix temp = new matrix();
        temp.nRow = mtx.nRow - 1;
        temp.nCol = 1;

        for (int i = 0; i < temp.nRow; i++){
            temp.Matrix[i][0] = 1;
        }

        matrix xnm = matrixOperation.sliceLastCol(matrixOperation.concatCol(temp, matrixOperation.sliceLastRow(mtx)));
        return xnm;
    }
    
    public static matrix ym(matrix mtx){
        // Ekstrak matriks ym dari matriks mtx
        return matrixOperation.takeLastCol(matrixOperation.sliceLastRow(mtx));
    }

    public static matrix xk(matrix mtx){
        // EKstrak matriks xk darii mtx
        return matrixOperation.takeLastRow(matrixOperation.sliceLastCol(mtx));
    }

    // RUNGKAD
    public static matrix ai(matrix xnm, matrix ym){
        /* Membuat matriks ai dengan metode gauss dari matriks augmented (transpose(xnm)) x (xnm) | (transpose(xnm)) x (ym) */
        matrix ai = new matrix();
        ai.nRow = xnm.nCol;
        ai.nCol = 1;

        matrix augmented = matrixOperation.concatCol(matrixOperation.multiplyMatrix(matrixOperation.transpose(xnm), xnm), matrixOperation.multiplyMatrix(matrixOperation.transpose(xnm), ym));
        matrix gaussed = matrixOperation.gauss(augmented);

        double cache;

        for (int i = 0; i < gaussed.nCol - 1; i++){
            ai.Matrix[i][0] = 0;
        }

        for (int i = gaussed.nRow - 1; i >= 0; i--){
            cache = gaussed.Matrix[i][gaussed.nCol - 1];
            for (int j = i; j < gaussed.nCol - 1; j++){
                cache -= ai.Matrix[j][0] * gaussed.Matrix[i][j];
            }
        }
        return ai;
    }

    public static double fxk(matrix xk, matrix ai){
        // Menghasilkan f(xk)
        double res;
        res = ai.Matrix[0][0];
        
        for (int i = 0; i < xk.nCol; i++){
            res = ai.Matrix[0][i] * ai.Matrix[i + 1][0];
        }
        return res;
    }

    // otput
    public static String fxkString(matrix ai){
        /* Menghasilkan string penjabaran f(xk), contohnya "f(xk) = -0.0064 + 0.2266x1 - 0.6762x2", dengan beberapa aturan.
        Aturan untuk koefisien:
        - Jika semua koefisiennya bernilai nol, maka ditulis f(xk) = 0
        - Jika koefisien dari suatu suku bernilai 0, sukunya tidak ditulis
        - Jika koefisien dari suatu suku bernilai positif, digunakan tanda +, jika negatif, digunakan tanda -
        - Jika koefisien bernilai 1 atau -1, koefisien tidak ditulis, kecuali jika koefisien tersebut adalah konstanta dari f(xk) */
        boolean found;
        String fxk = "f(xk) =";
        int notZeroIDXFirst;

        if (ai.isAllZero()){
            fxk += " 0";
        }
        else{
            /* Mencari indeks pertama yang tidak nol */
            notZeroIDXFirst = 0;
            found = false;
            for (int i = notZeroIDXFirst; i <= ai.nRow - 1; i++){
                if(ai.Matrix[i][0] != 0){
                    found = true;
                    notZeroIDXFirst = i;
                }
            }
            if (ai.Matrix[notZeroIDXFirst][0] > 0){
                if (ai.Matrix[notZeroIDXFirst][0] != 1){
                    fxk += " " + (ai.Matrix[notZeroIDXFirst][0]);
                }
                else{
                    fxk += (" ");
                }
            }
            else{
                if (ai.Matrix[notZeroIDXFirst][0] != -1) {
                    fxk += (" - " + ((-1) * ai.Matrix[notZeroIDXFirst][0]));
                } else {
                    if (notZeroIDXFirst == 0) {
                        fxk += (" - " + ((-1) * ai.Matrix[notZeroIDXFirst][0]));
                    } else {
                        fxk += (" - ");
                    }
                }  
            }

            // Add xk dari suku pertama
            if(notZeroIDXFirst != 0){
                fxk += ("x" + (notZeroIDXFirst));
            }

            // Add suku selanjutnya
            for (int a = notZeroIDXFirst + 1; a <= ai.nRow - 1; a++){
                if (ai.Matrix[a][0] != 0){
                    // Print koef suku
                    if (ai.Matrix[a][0] > 0){
                        if (ai.Matrix[a][0] != 1){
                            fxk += (" + " + (ai.Matrix[a][0]));
                        }
                        else{
                            if (ai.Matrix[a][0] != -1){
                                fxk += (" - " + ((-1) * ai.Matrix[a][0]));
                            }
                            else{
                                fxk += " - ";
                            }
                        }
                    }
                }
            }
        }
        return fxk;
    }

    public static void RLBFile(matrix xk, matrix ai) {
        // Kamus Lokal
        String filename;

        // Algoritma
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            // Buat file
            BufferedWriter writer = new BufferedWriter(new FileWriter("./test/" + filename));

            // Write
            writer.write("Hasil Perhitungan Regresi Linear Berganda");
            writer.newLine();
            writer.write("Penjabaran f(xk):");
            writer.newLine();
            writer.write(fxkString(ai));
            writer.newLine();
            writer.write("Hasil substitusi dengan nilai xk dari masukan:");
            writer.newLine();
            writer.write(("f(xk) = " + fxk(xk, ai)));
            writer.flush();
            writer.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
