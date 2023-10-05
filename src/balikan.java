public class balikan {
    // Fungsi untuk implementasi inverse identitas
    public static matrix inverseIdentitas(matrix mIn){
        matrix temp = new matrix();
        matrix mOut = new matrix();
        double cache = 0;
        int lenNonZero = 0;
        int kolom = 0;
        int kolom2 = 0;
        int colSearch = 0;
        int baris = 0;
        int i = 0;
        int j = 0;
        boolean adaNonZero;

        temp = matrixOperation.cloneMatrix(mIn);

        // Buat matriks identitas
        mOut.nRow = mIn.nRow;
        mOut.nCol = mIn.nCol;
        for (i = 0; i <mOut.nRow; i++){
            for (j = 0; j < mOut.nCol; j++){
                if (i == j){
                    mOut.Matrix[i][j] = 1;
                }
                else {
                    mOut.Matrix[i][j] = 0;
                }
            }
        }

        // Gunakan metode eliminasi gauss
        while ((lenNonZero < temp.nRow) && (kolom < temp.nCol)) {
            adaNonZero = false;
            if (temp.Matrix[lenNonZero][kolom] == 0) {
                colSearch = lenNonZero + 1;
                while ((colSearch < temp.nRow) && (!adaNonZero)) {
                    if (temp.Matrix[colSearch][kolom] != 0) {
                        adaNonZero = true;
                        temp = matrixOperation.rowSwap(temp, colSearch, lenNonZero);
                        mOut = matrixOperation.rowSwap(mOut, colSearch, lenNonZero);
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

        kolom = 0;
        baris = 0;
        while (kolom < temp.nCol) {
            if (temp.Matrix[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = baris + 1; i < temp.nRow; i++){
                    cache = temp.Matrix[i][kolom]/temp.Matrix[baris][kolom];
                    temp = matrixOperation.minKaliBaris(temp, i, baris, cache);
                    mOut = matrixOperation.minKaliBaris(mOut, i, baris, cache);
                }

                cache = 1/temp.Matrix[baris][kolom];

                temp = matrixOperation.rowXConst(temp, baris, cache);
                mOut = matrixOperation.rowXConst(mOut, baris, cache);

                lenNonZero = 0;
                kolom2 = 0;
                colSearch = 0;
                while ((lenNonZero < temp.nRow) && (kolom2 < temp.nCol)) {
                    adaNonZero = false;
                    if (temp.Matrix[lenNonZero][kolom2] == 0) {
                        colSearch = lenNonZero + 1;
                        while ((colSearch < temp.nRow) && (!adaNonZero)) {
                            if (temp.Matrix[colSearch][kolom2] != 0) {
                                adaNonZero = true;
                                temp = matrixOperation.rowSwap(temp, colSearch, lenNonZero);
                                mOut = matrixOperation.rowSwap(mOut, colSearch, lenNonZero);
                                lenNonZero += 1;
                            }
                            else{
                                colSearch += 1;
                            }
                        }
                        if (!adaNonZero) {
                            kolom2 += 1;
                        }
                    }
                    else{
                        lenNonZero += 1;
                    }
                }
                kolom += 1;
                baris += 1;
            }
        }

        // Jordan
        kolom = 0;
        baris = 0;
        while (kolom < temp.nCol) {
            if (temp.Matrix[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = 0; i < baris; i++){
                    if (i != baris){
                        cache = temp.Matrix[i][kolom]/temp.Matrix[baris][kolom];
                        temp = matrixOperation.minKaliBaris(temp, i, baris, cache);
                        mOut = matrixOperation.minKaliBaris(mOut, i, baris, cache);
                    }
                }
                kolom += 1;
                baris += 1;
            }
        }

        return mOut;
    }

    // Fungsi untuk implementasi inverse adjoint
    public static matrix inverseAdjoint(matrix mIn){
        // PREKONDISI: mIn matriks persegi, DET mIn != 0
        matrix mOut = new matrix();
        mOut = matrixOperation.transpose(SPL.matrixCof(mIn));
        for (int i = 0; i < mIn.nCol; i++){
            for (int j = 0; j < mIn.nRow; j++){
                mOut.Matrix[i][j] /= determinan.detOBE(mIn);
            }
        }
        return mOut;
    }
}
