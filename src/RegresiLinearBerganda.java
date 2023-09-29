import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.*; 

public class RegresiLinearBerganda {

    static Scanner in = new Scanner (System.in);

    public static matrix mtxfromkeyboard() {
        int n, m;
        System.out.println("Masukkan jumlah data yang akan dihitung : ");
        m = Integer.parseInt(in.nextLine());
        System.out.println("Notes : Untuk jumlah variabel peubah, variabel y tidak diperhitungkan\nMasukkan jumlah variabel : ");
        n = Integer.parseInt(in.nextLine());

        matrix mtxinput = new matrix();
        mtxinput.nRow = m + 1;
        mtxinput.nCol = n + 1;

        for (int i = 0; i < mtxinput.nRow; i++) {
            for (int j = 0; j < mtxinput.nCol; j++) {
                if (i != mtxinput.nRow - 1) {
                    if (j != mtxinput.nCol - 1) {
                        System.out.println("Masukkan nilai x" + (j + 1) + " sampel ke-" + (i + 1) + ": ");
                    } else {
                        System.out.println("Masukkan nilai y sampel ke-" + (i + 1) + ": ");
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
        mtxinput.Matrix[mtxinput.nRow - 1][mtxinput.nCol - 1] = -999.0;
        
        return mtxinput;
    }

    
    public static matrix varbebas(matrix mtxinput) {
        matrix allisone = new matrix();
        allisone.nRow = mtxinput.nRow - 1;
        allisone.nCol = 1;

        for (int i = 0; i < allisone.nRow; i++) {
            allisone.Matrix[i][0] = 1;
        }

        matrix varbebas = matrixOperation.sliceLastCol(matrixOperation.concatCol(allisone,matrixOperation.sliceLastRow(mtxinput)));
        
        return varbebas;
    }

    public static matrix variabelterikat (matrix mtxinput) {
        return matrixOperation.takeLastCol(matrixOperation.sliceLastRow(mtxinput));
    }
    
    public static matrix koefreg (matrix mtxinput) {
        return matrixOperation.takeLastRow(matrixOperation.sliceLastCol(mtxinput));
    }

    
    public static matrix AI (matrix varbebas, matrix variabelterikat) {
        matrix AI = new matrix();
        AI.nRow = varbebas.nCol;
        AI.nCol = 1;

        matrix augmented = matrixOperation.concatCol(matrixOperation.multiplyMatrix(matrixOperation.transpose(varbebas), varbebas),matrixOperation.multiplyMatrix(matrixOperation.transpose(varbebas), variabelterikat));
        matrix operasigauss =  matrixOperation.gauss(augmented);
        
        
        for(int i = 0; i < operasigauss.nCol - 1; i++){
            AI.Matrix[i][0] = 0;
        }
        double cac;
        for(int i = operasigauss.nRow - 1; i >= 0; i--){
            cac = operasigauss.Matrix[i][operasigauss.nCol-1];
            for(int j = i; j < operasigauss.nCol-1; j++){
                cac -= AI.Matrix[j][0] * operasigauss.Matrix[i][j];
            }
            AI.Matrix[i][0] = cac;
        }

        return AI;
    }

    public static double hasilfx (matrix koefreg, matrix AI) {
        double hasilf;
        hasilf = AI.Matrix[0][0];

        for (int i = 0; i < koefreg.nCol; i++) {
            hasilf += koefreg.Matrix[0][i] * AI.Matrix[i + 1][0];
        }

        return hasilf;
    }

  
    public static String hasilfxStringvers (matrix AI) {
        String fx = "f(x) =";
        int nonzeropertama;
        boolean found;

        if (AI.isAllZero()) {
            fx += " 0";
        } else {
            nonzeropertama = 0;
            found = false;
            for (int p = nonzeropertama; p <= AI.nRow - 1 && !found; p++) {
                if (AI.Matrix[p][0] != 0) {
                    found = true;
                    nonzeropertama = p;
                }
            }

            /* Add koefisien dari suku pertama */
            if (AI.Matrix[nonzeropertama][0] > 0) {
                if (AI.Matrix[nonzeropertama][0] != 1) {
                    fx += (" " + (AI.Matrix[nonzeropertama][0]));
                } else {
                    if (nonzeropertama == 0) {
                        fx += (" " + (AI.Matrix[nonzeropertama][0]));
                    } else {
                        fx += (" ");
                    }
                }
            } else {
                if (AI.Matrix[nonzeropertama][0] != -1) {
                    fx += (" - " + ((-1) * AI.Matrix[nonzeropertama][0]));
                } else {
                    if (nonzeropertama == 0) {
                        fx += (" - " + ((-1) * AI.Matrix[nonzeropertama][0]));
                    } else {
                        fx += (" - ");
                    }
                }    
            }
            
            /* Add xk dari suku pertama */
            if (nonzeropertama != 0) {
                fx += ("x" + (nonzeropertama));
            }

            /* Add suku-suku selanjutnya */
            for (int i = nonzeropertama + 1; i <= AI.nRow - 1; i++) {
                if (AI.Matrix[i][0] != 0) {
                    /* Print koefisien dari suku */
                    if (AI.Matrix[i][0] > 0) {
                        if (AI.Matrix[i][0] != 1) {
                            fx += (" + " + (AI.Matrix[i][0]));
                        } else {
                            if (i == 0) {
                                fx += (" + " + (AI.Matrix[i][0]));
                            } else {
                                fx += (" + ");
                            }
                        }
                    } else {
                        if (AI.Matrix[i][0] != -1) {
                            fx += (" - " + ((-1) * AI.Matrix[i][0]));
                        } else {
                            if (i == 0) {
                                fx += (" - " + ((-1) * AI.Matrix[i][0]));
                            } else {
                                fx += (" - ");
                            }
                        }
                    }

                    /* Add xk dari suku-suku selanjutnya*/
                    if (i != 0) {
                        fx += ("x" + (i));
                    }
                }
            }
        }
        return fx;
    }
    
    public static void RLBFile(matrix koefreg, matrix AI) {
        // Kamus Lokal
        String namafile;

        System.out.print("\nMasukkan nama file: ");
        namafile = in.nextLine() + ".txt";
        try {
            BufferedWriter buatfile = new BufferedWriter(new FileWriter("./test/" + namafile));

            // Write
            buatfile.write("Hasil Perhitungan Regresi Linear Berganda");
            buatfile.newLine();
            buatfile.write("Persamaan regresi linear berganda f(x):");
            buatfile.newLine();
            buatfile.write(hasilfxStringvers (AI));
            buatfile.newLine();
            buatfile.write("Hampiran (taksiran) nilai f(x):");
            buatfile.newLine();
            buatfile.write(("f(x) = " + hasilfx(koefreg, AI)));
            buatfile.flush();
            buatfile.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
