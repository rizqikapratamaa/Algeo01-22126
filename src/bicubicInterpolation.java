import java.lang.Math;
import java.util.*;
import java.io.*;

public class bicubicInterpolation {
    static Scanner input = new Scanner(System.in);

    /* Mengambil nilai a */
    public static double getA(matrix mInput){
        return mInput.Matrix[4][0];
    }

    /* Mengambil nilai b */
    public static double getB(matrix mInput){
        return mInput.Matrix[4][1];
    }

    /* Membuat matriks 4x4 dengan memotong baris terakhir */
    public static matrix m4x4(matrix mInput){
        return matrixOperation.transpose(matrixOperation.sliceLastRow(mInput));
    }

    /* Membuat matriks f(x,y) */
    public static matrix m16x1(matrix m4x4){
        matrix m16x1 = new matrix();
        m16x1.nRow = 16;
        m16x1.nCol = 1;

        int row = 0;

        int i, j; 
        for(i=0; i<4; i++){
            for(j=0; j<4; j++){
                m16x1.Matrix[row][0] = m4x4.Matrix[i][j];
                row++;
            }
        }
        return m16x1;
    }

    /* Membuat matriks X */
    public static matrix m16x16(){
        matrix m16x16 = new matrix();
        m16x16.nRow = 16;
        m16x16.nCol = 16;

        int i, j, x, y, row, col;
        row=0;
        for (int z = 0; z< 4; z++){
            for(y=0; y<2; y++){
                for(x=0; x<2; x++){
                    col=0;
                    for(j=0; j<4; j++){
                        for(i=0; i<4; i++){
                            if(row>=0 && row<=3){
                                m16x16.Matrix[row][col] = Math.pow(x, i) * Math.pow(y, j);
                            } else if(row>=4 && row<=7){
                                m16x16.Matrix[row][col] = i * Math.pow(x, i-1) * Math.pow(y, j);
                            } else if(row>=8 && row<=11){
                                m16x16.Matrix[row][col] = j * Math.pow(x, i) * Math.pow(y, j-1);
                            } else{
                                m16x16.Matrix[row][col] = i * j * Math.pow(x, i-1) * Math.pow(y, j-1); 
                            }
                            col++;
                        }
                    }
                    row++;
                }
            }
        }
        return m16x16;
    }

    /* Menghitung matriks Aij */
    public static matrix mAij(matrix m16x1){
        return matrixOperation.multiplyMatrix(matrixOperation.inverseIdentitas(m16x16()), m16x1);
    }

    /* Menghitung nilai f(a,b) */
    public static double getFab(matrix mAij, double a, double b){
        int i, j, row;
        double hslfab;

        row = 0;
        hslfab = 0;

        for(j=0; j<=3; j++){
            for(i=0; i<=3; i++){
                hslfab += mAij.Matrix[row][0] * Math.pow(a, i) * Math.pow(b, j);
                row++;
            }
        }
        return hslfab;
    }


    /* Memasukkan hasil bicubic ke File */
    public static void bicubicInterFile(matrix mAij, double a, double b){
        System.out.println("\nMasukkan nama file: ");
        String fileName = input.nextLine() + ".txt";

        try{
            // Membuat file
            BufferedWriter buff = new BufferedWriter(new FileWriter("../test/" + fileName));

            // Write Perline
            buff.write("\nHasil Perhitungan Bicubic Interpolation\n");
            buff.write("f(" + a + ", " + b + ") = " + getFab(mAij, a, b));

            buff.flush();
            buff.close();

        // Handler error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
