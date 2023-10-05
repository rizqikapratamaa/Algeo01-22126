import java.util.Scanner;
import java.io.*; 

public class RegresiLinearBerganda {

    static Scanner in = new Scanner (System.in);
    //Gauss jordan khusus untuk regresi
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

    // Fungsi untuk menerima masukan dari keyboard
    public static matrix mtxfromkeyboard() {
        int n, m;
        System.out.println("Notes : Untuk jumlah variabel peubah, variabel y tidak diperhitungkan\nMasukkan jumlah variabel : ");
        n = Integer.parseInt(in.nextLine());
        System.out.println("Masukkan jumlah data yang akan dihitung : ");
        m = Integer.parseInt(in.nextLine());

        matrix mtxinput = new matrix();
        mtxinput.nRow = m + 1;
        mtxinput.nCol = n + 1;

        for (int i = 0; i < mtxinput.nRow; i++) {
            for (int j = 0; j < mtxinput.nCol; j++) {
                if (i != mtxinput.nRow - 1) {
                    if (j != mtxinput.nCol - 1) {
                        System.out.println("Masukkan nilai x" + (j + 1) + " data ke-" + (i + 1) + ": ");
                    } else {
                        System.out.println("Masukkan nilai y data ke-" + (i + 1) + ": ");
                    }
                    mtxinput.Matrix[i][j] = Double.parseDouble(in.nextLine());
                } else {
                    if (j != mtxinput.nCol - 1) {
                        System.out.println("Masukkan nilai x" + (j + 1) + " yang akan diregresi: ");
                        mtxinput.Matrix[i][j] = Double.parseDouble(in.nextLine());
                    }
                }
            }
        }
        mtxinput.Matrix[mtxinput.nRow - 1][mtxinput.nCol - 1] = 0.0;
        
        return mtxinput;
    }

    // Fungsi untuk mengubah matriks yang vertikal menjadi horizontal
    public static double ROW(matrix m, int s, int rowReg, int colReg) {
        double countRow; 
        countRow = 0;
        int i;
        
        for (i = 0; i < s - 1; i++) {
            if (rowReg == 0) {
                countRow += m.Matrix[colReg-1][i]; 
            } else {
                countRow += m.Matrix[rowReg-1][i]*m.Matrix[colReg-1][i];
            }
            
        }
        return countRow;
    }

    // Fungsi untuk mendapatkan persamaan normal equation
    public static matrix FUNCTION(matrix m) {
        int i, j;
        matrix m1 = matrixOperation.transpose(m);
        matrix mfuc = new matrix();
        mfuc.nRow = m.nCol;
        mfuc.nCol = m.nCol+1;
        for (i = 0; i < mfuc.nRow; i++) {
            for (j = 0; j < mfuc.nCol; j++) {
                if (i == 0) {
                    if (j == 0) {
                        mfuc.Matrix[i][j] = m.nRow - 1;}
                    // } else if(j==mfuc.nRow){
                    //     mfuc.Matrix[i][j] = 0.0;
                    // }
                    else {
                        mfuc.Matrix[i][j] = ROW(m1, m.nRow, i, j);
                    }

                } else {
                    if (j == 0) {
                        mfuc.Matrix[i][j] = mfuc.Matrix[j][i];
                    } else {
                        mfuc.Matrix[i][j] = ROW(m1, m.nRow, i, j);
                    }
                }
            }
        }
        
        return mfuc;
    }

    // Prosedur untuk mendapatkan persamaan regresi dan hampirannya
    public static void SOLUTION(matrix m) {
        matrix mb = FUNCTION(m);
        matrix a = gaussJordanReg(mb);

        String output = "f(x) = ";
        System.out.print("f(x) = ");
        double fx = a.Matrix[0][a.nCol - 1] + a.Matrix[1][a.nCol - 1] * m.Matrix[m.nRow - 1][0] + a.Matrix[2][a.nCol - 1] * m.Matrix[m.nRow - 1][1] + a.Matrix[3][a.nCol - 1] * m.Matrix[m.nRow - 1][2];

        for (int i = 0; i < a.nRow; i++) {
            double coefficient = a.Matrix[i][a.nCol - 1]; // Mengambil koefisien (hasil) di kolom terakhir
            if (i == 0) {
                output += String.format("%.10f", coefficient);
                System.out.print(String.format("%.10f", coefficient));
            } else {
                if (coefficient > 0) {
                    output += " + ";
                    System.out.print(" + ");
                } else if (coefficient < 0) {
                    output += " - ";
                    System.out.print(" - ");
                }
                output += String.format("%.10f", Math.abs(coefficient)) + "x" + i;
                System.out.print(String.format("%.10f", Math.abs(coefficient)) + "x" + i);
            }
        }
        
        System.out.println();
        System.out.println("Hampiran (taksiran) nilai f(x): " + fx);

        
        
        
    }

    // Prosedur untuk menyimpan hasil regresi dalam bentuk file
    public static void RLBFile(matrix m) {
        String namafile;

        System.out.print("\nMasukkan nama file: ");
        namafile = in.nextLine() + ".txt";
        try {
            BufferedWriter buatfile = new BufferedWriter(new FileWriter("../test/" + namafile));

            // Write
            matrix mb = FUNCTION(m);
            matrix a = SPL.gaussJordan(mb);

            String output = "f(x) = ";
            buatfile.write("f(x) = ");
            double fx = a.Matrix[0][a.nCol - 1] + a.Matrix[1][a.nCol - 1] * m.Matrix[m.nRow - 1][0] + a.Matrix[2][a.nCol - 1] * m.Matrix[m.nRow - 1][1] + a.Matrix[3][a.nCol - 1] * m.Matrix[m.nRow - 1][2];

            for (int i = 0; i < a.nRow; i++) {
                double coefficient = a.Matrix[i][a.nCol - 1]; // Mengambil koefisien (hasil) di kolom terakhir
                if (i == 0) {
                    output += String.format("%.10f", coefficient);
                    buatfile.write(String.format("%.10f", coefficient));
                } else {
                    if (coefficient > 0) {
                        output += " + ";
                        buatfile.write(" + ");
                    } else if (coefficient < 0) {
                        output += " - ";
                        buatfile.write(" - ");
                    }
                    output += String.format("%.10f", Math.abs(coefficient)) + "x" + i;
                    buatfile.write(String.format("%.10f", Math.abs(coefficient)) + "x" + i);
                }
            }
            
            buatfile.newLine();;
            buatfile.write("Hampiran (taksiran) nilai f(x): " + fx);
            buatfile.flush();
            buatfile.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}


