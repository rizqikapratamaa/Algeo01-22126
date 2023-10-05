
import java.lang.Math;
import java.util.*;
import java.io.*; 

public class InterpolasiPolinom {
    static Scanner in = new Scanner (System.in);

    /* IO */
    // Fungsi untuk menerima input dari keyboard
    public static matrix inputKeyboard() {
        int n;
        System.out.print("Masukkan derajat polinom (n): ");
        n = Integer.parseInt(in.nextLine());

        matrix inputMatrix = new matrix();
        inputMatrix.nRow = n + 2;
        inputMatrix.nCol = 2;

        for (int i = 0; i < inputMatrix.nRow; i++) {
            for (int j = 0; j < inputMatrix.nCol; j++) {
                if (i != inputMatrix.nRow - 1) {
                    if (j == 0) {
                        System.out.print("Masukkan nilai x titik ke-" + i + ": ");
                    } else {
                        System.out.print("Masukkan nilai y titik ke-" + i + ": ");
                    }
                    inputMatrix.Matrix[i][j] = Double.parseDouble(in.nextLine());
                } else {
                    if (j != inputMatrix.nCol - 1) {
                        System.out.print("Masukkan nilai x yang akan diinterpolasi: ");
                        inputMatrix.Matrix[i][j] = Double.parseDouble(in.nextLine());
                    }
                }
            }
        }
        inputMatrix.Matrix[inputMatrix.nRow - 1][inputMatrix.nCol - 1] = -999.0;
        
        return inputMatrix;
    }

    // Fungsi untuk membuat matriks tanpa kolom pertama
    public static matrix x (matrix inputMatrix) {
        matrix x = new matrix();
        x.nRow = inputMatrix.nRow - 1;
        x.nCol = 1;

        for (int i = 0; i < x.nRow; i++) {
            x.Matrix[i][0] = inputMatrix.Matrix[i][0];
        }

        return x;
    }

    // Fungsi untuk membuat matrix hasil perhitungan
    public static matrix fx (matrix inputMatrix) {
        matrix fx = new matrix();
        fx.nRow = inputMatrix.nRow - 1;
        fx.nCol = 1;

        for (int i = 0; i < fx.nRow; i++) {
            fx.Matrix[i][0] = inputMatrix.Matrix[i][1];
        }
        return fx;
    }

    // Fungsi untuk mengekstrak nilai a dari matriks inputMatrix
    public static double a (matrix inputMatrix) {
        return inputMatrix.Matrix[inputMatrix.nRow - 1][0];
    }


    /* PERHITUNGAN */
    // Fungsi untuk membuat matriks xi dari matriks x
    public static matrix xi (matrix x) {
        /* Membuat matrix xi dari matrix x */
        matrix xi = new matrix();
        xi.nRow = x.nRow;
        xi.nCol = x.nRow;

        for (int i = 0; i < xi.nRow; i++) {
            for (int j = 0; j < xi.nCol; j++) {
                xi.Matrix[i][j] = Math.pow(x.Matrix[i][0], j);
            }
        }

        return xi;
    }

    // Fungsi untuk membuat matriks ai dengan metode gauss dari matriks augmented xi|fx
    public static matrix ai (matrix xi, matrix fx) {
        matrix ai = new matrix();
        ai.nRow = fx.nRow;
        ai.nCol = 1;
        
        matrix augmented = matrixOperation.concatCol(xi, fx);
        matrix gaussed = SPL.gauss(augmented);
        
        double cache;
        
        for (int i = 0; i < gaussed.nCol - 1; i++){
            ai.Matrix[i][0] = 0;
        }

        for (int i = gaussed.nRow - 1; i >= 0; i--){
            cache = gaussed.Matrix[i][gaussed.nCol-1];
            for (int j = i; j < gaussed.nCol-1; j++){
                cache -= ai.Matrix[j][0] * gaussed.Matrix[i][j];
            }
            ai.Matrix[i][0] = cache;
        }

        return ai;
    }

    // Fungsi untuk menghasilkan fa
    public static double fa (matrix ai, double a) {
        double fa;
        int baris;
        fa = 0;
        baris = 0;
        for (int i = 0; i < ai.nRow ; i++) {
            fa += ai.Matrix[baris][0] * Math.pow(a, i);
            baris++;
        }

        return fa;
    }


    /* OUTPUT */
    // Fungsi untuk menghasilkan string hasil
    public static String fxString(matrix ai) {
        String fx = "f(x) =";

        int lastNonZeroIdx;
        boolean found;

        if (ai.isAllZero()) {
            fx += (" 0");
        } else {
            lastNonZeroIdx = ai.nRow - 1;
            found = false;
            for (int a = lastNonZeroIdx; a >= 0 && !found; a--) {
                if (ai.Matrix[a][0] != 0) {
                    found = true;
                    lastNonZeroIdx = a;
                }
            }

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
            
            if (lastNonZeroIdx == 1) {
                fx += ("x");
            } else if (lastNonZeroIdx != 0) {
                fx += ("x^" + (lastNonZeroIdx));
            }

            for (int i = lastNonZeroIdx - 1; i >= 0; i--) {
                if (ai.Matrix[i][0] != 0) {
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

                    if (i == 1) {
                        fx += ("x");
                    } else if (i != 0) {
                        fx += ("x^" + (i));
                    }
                }
            }
        }
        return fx;
    }
    
    // Prosedur untuk menulis hasil interpolasi ke file
    public static void IPFile(matrix ai, double a) {
        String filename;
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("../test/" + filename));

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