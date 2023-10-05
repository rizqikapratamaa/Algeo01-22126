import java.util.Scanner;

public class rlb3 {

    static Scanner in = new Scanner (System.in);

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

        matrix varbebas = (matrixOperation.concatCol(allisone,matrixOperation.sliceLastCol(mtxinput)));
        
        return varbebas;
    }

    public static matrix variabelterikat (matrix mtxinput) {
        return matrixOperation.takeLastCol(matrixOperation.sliceLastRow(mtxinput));
    }
    
    public static matrix koefreg (matrix mtxinput) {
        return matrixOperation.takeLastRow(matrixOperation.sliceLastCol(mtxinput));
    }


    public static double sigmaRow(matrix m, int s, int rowReg, int colReg) {
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

    public static matrix Fungsi(matrix m) {
        int i, j;
        matrix m1 = matrixOperation.transpose(m);
        matrix mfuc = new matrix();
        mfuc.nRow = m.nRow;
        mfuc.nCol = m.nCol+1;
        for (i = 0; i < mfuc.nRow; i++) {
            for (j = 0; j < mfuc.nCol; j++) {
                if (i == 0) {
                    if (j == 0) {
                        mfuc.Matrix[i][j] = m.nRow - 1;
                    } else {
                        mfuc.Matrix[i][j] = sigmaRow(m1, m.nRow, i, j);
                    }

                } else {
                    if (j == 0) {
                        mfuc.Matrix[i][j] = mfuc.Matrix[j][i];
                    } else {
                        mfuc.Matrix[i][j] = sigmaRow(m1, m.nRow, i, j);
                    }
                }
            }
        }
        
        mfuc.writeMatrix();;
        return mfuc;
    }

    public static void Solusi(matrix m) {
        matrix mb = Fungsi(m);
        matrixOperation.gauss(matrixOperation.sliceLastRow(mb));
        
        for (int i=m.nRow-1;i>=0;i--){
            mb.Matrix[0][i]= m.Matrix[i][m.nCol-1];
            for (int j = m.nRow-1;j>1;j--){
                mb.Matrix[0][i]-= (mb.Matrix[0][j])*m.Matrix[i][j];
            }
        }
       
      
        String output = "f(x) = ";
        System.out.print("f(x) = ");
        for (int i = 0; i <= mb.nCol - 1; i++) {
            if (i == mb.nCol- 1) {
                output += String.format("%.4f", mb.Matrix[0][i]) + "x" + i + ", ";
                System.out.print(String.format("%.4f", mb.Matrix[0][i]) + "x" + i + ", ");
            } else if (i != 0) {
                output += String.format("%.4f", mb.Matrix[0][i]) + "x" + i + " + ";
                System.out.print(String.format("%.4f", mb.Matrix[0][i]) + "x" + i + " + ");
            } else {
                output += String.format("%.4f", mb.Matrix[0][i]) + " + ";
                System.out.print(String.format("%.4f", mb.Matrix[0][i]) + " + ");
            }
        }
        double sum = 0;
        int j;
        for (j = 0; j < m.nCol - 1; j++) {
            if (j == 0) {
                sum += mb.Matrix[0][j];
            } else {
                sum += mb.Matrix[0][j] * m.Matrix[m.nRow - 1][j];
            }
        }
        int l;
        output += " f(";
        System.out.print(" f(");
        for (l = 0; l < m.nCol - 1; l++) {
            if (l != m.nCol - 2) {
                output += m.Matrix[m.nRow - 1][l] + ",";
                System.out.print(m.Matrix[m.nRow -1][l] + ",");
            } else {
                output += m.Matrix[m.nRow - 1][l];
                System.out.print(m.Matrix[m.nRow -1][l]);
            }
        }
        output += ") = " + String.format("%.4f", sum);
        System.out.print(") = ");
        System.out.print(String.format("%.4f", sum));
        System.out.println();

    }

}

