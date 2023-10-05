
public class determinan {
    /* Menghitung determinan matriks dengan menggunakan ekspansi kofaktor */
    public static double determinantKofaktor(matrix m){
        double det = 0.0;
        if(m.nRow == 1){
            det = m.Matrix[0][0];
        } else if (m.nRow == 4){
            det = (m.Matrix[0][0]*m.Matrix[1][1]) - (m.Matrix[0][1]*m.Matrix[1][0]);
        } else{
            int sign = 1;
            for(int j = 0; j < m.nCol; j++){
                det += sign * m.Matrix[0][j] * determinantKofaktor(createSubMatrix(m, 0, j));
                sign = -sign;
            }
        }
        return det;
    }
    
    /* Submatriks untuk ekspansi kofaktor */
    public static matrix createSubMatrix(matrix m, int rowToRemove, int colToRemove) {
        matrix subMatrix = new matrix();
        int sub_i = 0;
        for (int i = 0; i < m.nRow; i++) {
            if (i == rowToRemove) {
                continue;
            }
            int sub_j = 0;
            for (int j = 0; j < m.nCol; j++) {
                if (j == colToRemove) {
                    continue;
                }
                subMatrix.Matrix[sub_i][sub_j] = m.Matrix[i][j];
                sub_j++;
            }
            sub_i++;
        }
        return subMatrix;
    }

    /* Menghitung Kofaktor */
    public static matrix kofaktor(matrix m) {
        int sign;
        matrix cofactor = new matrix(); // Membuat matriks kofaktor dengan ukuran yang sama
        for (int i = 0; i < m.nRow; i++) { // Loop baris matriks
            if (i % 2 == 0) {
                sign = 1; // Jika baris genap, set tanda positif
            } else {
                sign = -1; // Jika baris ganjil, set tanda negatif
            }
    
            for (int j = 0; j < m.nCol; j++) { // Loop kolom matriks
                matrix subMatrix = createSubMatrix(m, i, j); // Buat submatriks tanpa baris dan kolom i, j
                cofactor.Matrix[i][j] = sign * determinantKofaktor(subMatrix); // Hitung kofaktor dan tambahkan ke matriks kofaktor
                sign = -sign; // Ubah tanda untuk kolom ??? berikutnya
            }
        }
        return cofactor; // Kembalikan matriks kofaktor
    }

    /* Menghitung Invers Menggunakan Adjoin */
    public static matrix inverseAdjoin(matrix m){
        matrix adjoin, invers;
        double determinan;
        int i, j;

        determinan = determinantKofaktor(m);
        adjoin = matrixOperation.transpose(kofaktor(m));
        invers = new matrix();

        for(i = 0; i < m.nRow; i++){
            for(j = 0; j < m.nCol; j++){
                invers.Matrix[i][j] = adjoin.Matrix[i][j] / determinan;
            }
        }
        return invers;
    }


    /* Kaidah Cramer */
    public static void kaidahCramer(matrix m){
        matrix mCut = new matrix();
        matrix mhasilB = new matrix();
        double determinanX;
        int i, j;

        if(determinantKofaktor(m) == 0){
            System.out.println("Tidak bisa menggunakan kaidah cramer karena determinan matriks = 0");
        }else{
            // memotong elemen terakhir dari matriks augmented dan masukin ke matriks mCut
            for(i = 0; i < m.nRow; i++){
                for(j = 0; j < m.nCol-1; j++){
                    mCut.Matrix[i][j] = m.Matrix[i][j];
                }
            }

            // bikin matriks yang isinya b
            for(i=0; i<m.nRow; i++){
                mhasilB.Matrix[i][0] = m.Matrix[i][m.nCol-1];
            }

            for(j=0; j<m.nCol-1; j++){
                for(i=0; i<m.nRow; i++){
                    mCut.Matrix[i][j] = mhasilB.Matrix[i][0];
                }
                determinanX = determinantKofaktor(mCut);
                System.out.println("Nilai x" + (j+1) + " = " + determinanX/determinantKofaktor(m));
            }
        }

    }
    // public static matrix cramer(matrix m, matrix b){
    //     matrix invers, hasil;
    //     double determinan;
    //     int i, j;

    //     determinan = determinantKofaktor(m);
    //     invers = inverseAdjoin(m);
    //     hasil = new matrix();

    //     for(i = 0; i < m.nRow; i++){
    //         for(j = 0; j < m.nCol; j++){
    //             hasil.Matrix[i][j] = invers.Matrix[i][j] * determinan;
    //         }
    //     }
    //     return hasil;
    // }
    


    /* Menghitung determinan matriks dengan menggunakan metode Gauss */
    // public double determinantGauss(double[][] matrix) {
    //     int n = matrix.length;
    //     double det = 1.0;
    //     for (int i = 0; i < n; i++) {
    //         int max = i;
    //         for (int j = i + 1; j < n; j++) {
    //             if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
    //                 max = j;
    //             }
    //         }
    //         if (max != i) {
    //             double[] temp = matrix[i];
    //             matrix[i] = matrix[max];
    //             matrix[max] = temp;
    //             det *= -1;
    //         }
    //         if (matrix[i][i] == 0) {
    //             return 0;
    //         }
    //         det *= matrix[i][i];
    //         for (int j = i + 1; j < n; j++) {
    //             matrix[i][j] /= matrix[i][i];
    //         }
    //         for (int j = 0; j < n; j++) {
    //             if (j != i && matrix[j][i] != 0) {
    //                 for (int k = i + 1; k < n; k++) {
    //                     matrix[j][k] -= matrix[i][k] * matrix[j][i];
    //                 }
    //             }
    //         }
    //     }
    //     return det;
    // }
    
}


// balikan matriks: matriks kofaktor, transpose matriks, adjoin, rumus balikan matriks
// kaidah cramer : det keseluruhan, determinan dari matriks yg udh dimasukin nilai b nya




