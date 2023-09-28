import java.util.*;
import java.lang.Math;
import java.io.*;

public class InterpolasiPolinom {
    // in: Scanner untuk membaca mIn dari pengguna.
    static Scanner in = new Scanner (System.in);

    /* Fungsi untuk menerima mIn dari pengguna */
    public static matrix inputMatrix(){
        // Meminta pengguna untuk memasukkan jumlah baris dan kolom.
        int numRows, numCols;
        System.out.print("Masukkan jumlah baris: ");
        numRows = Integer.parseInt(in.nextLine());
        System.out.print("Masukkan jumlah kolom: ");
        numCols = Integer.parseInt(in.nextLine());

        // Membuat matriks untuk menyimpan data.
        matrix mIn = new matrix();
        mIn.nRow = numRows;
        mIn.nCol = numCols;

        // Meminta pengguna untuk memasukkan setiap elemen matriks.
        System.out.println("Masukkan elemen matriks:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print("Masukkan elemen (" + i + ", " + j + "): ");
                mIn.Matrix[i][j] = Double.parseDouble(in.nextLine());
            }
        }

        return mIn;
    }

    // Mengekstrak matriks x dari matriks
    public static matrix x(matrix mIn){
        // Buat matriks x untuk menyimpan nilai x dari matriks mIn
        matrix x = new matrix();
        x.nRow = mIn.nRow - 1; // Matriks x memiliki satu baris lebih sedikit daripada matriks mIn karena baris terakhir matriks mIn berisi nilai x yang akan diinterpolasi
        x.nCol = 1; // Matriks x memiliki satu kolom karena hanya berisi nilai x

        // Salin nilai x dari matriks mIn ke matriks x
        for (int i = 0; i < x.nRow; i++) {
            x.Matrix[i][0] = mIn.Matrix[i][0];
        }
        // Kembalikan matriks x
        return x;
    }

    // Mengekstrak matriks fx dari matriks mIn
    public static matrix fx(matrix mIn){
        // Buat matriks fx untuk menyimpan nilai y dari matriks mIn
        matrix fx = new matrix();
        fx.nRow = mIn.nRow - 1; // Matriks fx memiliki satu baris lebih sedikit daripada matriks mIn karena baris terakhir matriks mIn berisi nilai x yang akan diinterpolasi
        fx.nCol = 1; // Matriks fx memiliki satu kolom karena hanya berisi nilai y

        // Salin nilai y dari matriks mIn ke matriks fx
        for (int i = 0; i < fx.nRow; i++) {
            fx.Matrix[i][0] = mIn.Matrix[i][1];
        }
        // Kembalikan matriks fx
        return fx;
    }

    // Membuat matriks xi dari matriks mIn
    public static matrix xi (matrix mIn){
        // Buat matriks xi untuk menyimpan nilai x pangkat j dari matriks mIn
        matrix xi = new matrix();
        xi.nRow = mIn.nRow; // Matriks xi memiliki jumlah baris yang sama dengan matriks mIn
        xi.nCol = mIn.nCol; // Matriks xi memiliki jumlah kolom yang sama dengan matriks mIn

        // Isi matriks xi dengan nilai x pangkat j dari matriks mIn
        for (int i = 0; i < xi.nRow; i++) {
            for (int j = 0; j < xi.nCol; j++) {
                xi.Matrix[i][j] = Math.pow(mIn.Matrix[i][0], j);
            }
        }

        // Kembalikan matriks xi
        return xi;
    }

    // Mengekstrak nilai a dari matriks mIn
    public static double a(matrix mIn){
        return mIn.Matrix[mIn.nRow - 1][0];
    }

    // Menghitung koefisien interpolasi polinom menggunakan eliminasi Gauss
    public static matrix ai(matrix xi, matrix fx){

        // Buat matriks ai untuk menyimpan koefisien interpolasi polinom
        matrix ai = new matrix();
        ai.nRow = fx.nRow; // Matriks ai memiliki jumlah baris yang sama dengan matriks fx
        ai.nCol = 1; // Matriks ai memiliki satu kolom karena hanya berisi koefisien interpolasi polinom

        // Gabungkan matriks xi dan matriks fx menjadi satu matriks
        matrix augmented = matrixOperation.concatCol(xi, fx);

        // Lakukan eliminasi Gauss pada matriks gabungan
        matrix gaussed = matrixOperation.gauss(augmented);

        // Hitung koefisien interpolasi polinom
        double cache;
        for (int i = gaussed.nCol - 1; i >= 0; i--) {
            cache = gaussed.Matrix[i][gaussed.nCol - 1];
            for (int j = i; j < gaussed.nCol - 1; j++) {
                cache -= ai.Matrix[j][0] * gaussed.Matrix[i][j];
            }
            ai.Matrix[i][0] = cache;
        }

        // Kembalikan matriks ai yang berisi koefisien interpolasi polinom
        return ai;
    }

    // Menghitung nilai fungsi interpolasi polinom
    public static double fa (matrix ai, double a){

        // Deklarasi variabel
        double fa; // Nilai fungsi interpolasi polinomial
        int baris; // Baris matriks ai yang sedang diproses

        // Inisialisasi variabel
        fa = 0;
        baris = 0;

        // Iterasi untuk menghitung nilai fungsi interpolasi polinomial
        for (int i = 0; i < ai.nRow; i++) {
            fa += ai.Matrix[baris][0] * Math.pow(a, i);
            baris++;
        }

        // Kembalikan nilai fungsi interpolasi polinomial
        return fa;
}

    // Mengubah matriks ke bentuk string
    public static String fxString(matrix ai){
        String fx = "f(x) =";
        int lastNonZeroIdx;
        boolean found;

        if (ai.isAllZero()) {
            /* Jika semua koefisiennya bernilai nol, maka ditulis f(x) = 0 */
            fx += (" 0");
        } else {
            // Mencari indeks baris terakhir di matriks ai yang tidak bernilai nol
            lastNonZeroIdx = ai.nRow - 1;
            found = false;
            for (int a = lastNonZeroIdx; a >= 0 && !found; a--) {
                if (ai.Matrix[a][0] != 0) {
                    found = true;
                    lastNonZeroIdx = a;
                }
            }

            // Menambahkan suku pertama polinom ke string
            if (ai.Matrix[lastNonZeroIdx][0] > 0) {
                if (ai.Matrix[lastNonZeroIdx][0] != 1) {
                    fx += (" " + (ai.Matrix[lastNonZeroIdx][0]));
                } else {
                    if (lastNonZeroIdx == 0) {
                        fx += (" " + (ai.Matrix[lastNonZeroIdx][0]));
                    } else {
                        fx += (" ");
                    }
                }
            } else {
                if (ai.Matrix[lastNonZeroIdx][0] != -1) {
                    fx += (" - " + ((-1) * ai.Matrix[lastNonZeroIdx][0]));
                } else {
                    if (lastNonZeroIdx == 0) {
                        fx += (" - " + ((-1) * ai.Matrix[lastNonZeroIdx][0]));
                    } else {
                        fx += (" - ");
                    }
                }
            }

            // Menambahkan pangkat x dari suku pertama polinom ke string
            if (lastNonZeroIdx == 1) {
                fx += ("x");
            } else if (lastNonZeroIdx != 0) {
                fx += ("x^" + (lastNonZeroIdx));
            }

            // Menambahkan suku-suku selanjutnya polinom ke string
            for (int i = lastNonZeroIdx - 1; i >= 0; i--) {
                if (ai.Matrix[i][0] != 0) {
                    // Menambahkan koefisien dari suku ke string
                    if (ai.Matrix[i][0] > 0) {
                        if (ai.Matrix[i][0] != 1) {
                            fx += (" + " + (ai.Matrix[i][0]));
                        } else {
                            if (i == 0) {
                                fx += (" + " + (ai.Matrix[i][0]));
                            } else {
                                fx += (" + ");
                            }
                        }
                    } else {
                        if (ai.Matrix[i][0] != -1) {
                            fx += (" - " + ((-1) * ai.Matrix[i][0]));
                        } else {
                            if (i == 0) {
                                fx += (" - " + ((-1) * ai.Matrix[i][0]));
                            } else {
                                fx += (" - ");
                            }
                        }
                    }

                    // Menambahkan pangkat x dari suku ke string
                    if (i == 1) {
                        fx += ("x");
                    } else if (i != 0) {
                        fx += ("x^" + (i));
                    }
                }
            }
        }
        // Mengembalikan string yang berisi polinom
        return fx;
    }

    // Menyimpan hasil perhitungan interpolasi polinom ke file
    public static void IPFile(matrix ai, double a) {

        // Deklarasi variabel
        String filename; // Nama file tempat menyimpan hasil perhitungan interpolasi polinom
    
        // Meminta nama file dari pengguna
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
    
        // Menulis hasil perhitungan interpolasi polinom ke file
        try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));
    
        bw.write("Hasil Perhitungan Interpolasi Polinom");
        bw.newLine();
        bw.write("Penjabaran f(x):");
        bw.newLine();
        bw.write(fxString(ai));
        bw.newLine();
        bw.write(("Hasil substitusi dengan nilai x = " + a + ":"));
        bw.newLine();
        bw.write(("f("+ a + ") = " + fa(ai, a)));
        bw.flush();
        bw.close();
    
        } catch(IOException e){
        System.out.println(e.getMessage());
        }
    }
}