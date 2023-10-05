public class bicinter {
    //INTERPOLASI BICUBIC
    public static void BicInter() {
        String line;
        String[] row;
        int input;

        matriks stdInput = new matriks();
    
        /* Menerima masukan dari file */
        System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
        System.out.print("\nNama file (.txt): ");
        String namaFile = in.nextLine();
        String pathFile = "./test/" + namaFile;
        stdInput.bacaFileMatriksBolong(pathFile, 2);

        /* Proses */
        if (!(stdInput.jumlahBaris == 0 || stdInput.jumlahKolom == 0)) {
            matriks aij = BicubicInterpolation.aij(BicubicInterpolation.fxy(BicubicInterpolation.fxy4x4(stdInput)));
            double a = BicubicInterpolation.a(stdInput);
            double b = BicubicInterpolation.b(stdInput);

            /* Output terminal */
            System.out.println("\nHasil Bicubic Interpolation");
            System.out.println("Hasil substitusi dengan nilai x dan y dari masukan:");
            System.out.println("f(" + a + "," + b + ") = " + BicubicInterpolation.bicIntpol(aij, a, b));
            
            /* Output file */
            System.out.println("\nApakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 || input > 2);
        
            switch (input){
                case 1:
                BicubicInterpolation.BIFile(aij, a, b);
                break;
        
                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }

        } else {
            System.out.println("\nOperasi gagal, kembali ke menu utama...");
        }
    }
}
