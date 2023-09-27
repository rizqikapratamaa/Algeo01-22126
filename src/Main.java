import java.util.Scanner;

/*
Ini buat main programnya
Inputan masuk ke variabel input
*/

public class Main {
    static Scanner in = new Scanner (System.in);
    public static void main(String[] args) {
        boolean run = true;
        int input = 0;
        String line;
        String[] row = new String[100];

        // Pilih menu
        while (run){
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic Spline");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Keluar");

            do {
                line = in.nextLine();
                row = line.split(" ");
                try{
                    input = Integer.parseInt(row[0]);

                }catch (NumberFormatException e){
                    input = 0;
                }
                if (input <= 0 || input > 7){
                    System.out.println("Input tidak valid!");
                }
            }while(input <= 0 || input > 7);

            switch(input){
                case 1:
                System.out.println("Sistem Persamaan Linear");
                System.out.println("\nPILIH METODE");
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println("5. Kembali Ke Menu");
                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try{
                        input = Integer.parseInt(row[0]);
    
                    }catch(NumberFormatException e){
                        input = 0;
                    }
                    if (input <= 0 || input > 5){
                        System.out.println("Input tidak valid!");
                    }
                }while(input <= 0 || input > 5);

                switch(input){
                    // Metode eliminasi gauss
                    case 1:
                    SPLGauss();
                    break;
                    
                    // Metode eliminasi Gauss-Jordan
                    case 2:
                    SPLGaussJordan();
                    break;

                    // Metode matriks balikan
                    case 3:
                    SPLInverse();
                    break;

                    // Kaidah Cramer
                    case 4:
                    SPLCramer();
                    break;

                    // Back to main menu
                    case 5:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
                break;

                case 2:
                System.out.println("Determinan");
                System.out.println("\nPilih Metode:");
                System.out.println("1. Metode OBE (Segitiga)");
                System.out.println("2. Metode Kofaktor Baris 0");
                System.out.println("3. Metode Kofaktor Kolom 0");
                System.out.println("4. Kembali Ke Menu.");
                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try{
                        input = Integer.parseInt(row[0]);
    
                    }catch(NumberFormatException e){
                        input = 0;
                    }
                    if (input <= 0 || input > 5){
                        System.out.println("Input tidak valid!");
                    }
                }while(input <= 0 || input > 5);

                switch(input){
                    // Metode eliminasi gauss
                    case 1:
                    // Ntar taro disini aja fungsinya EliminasiGauss()
                    break;
                    
                    // Metode eliminasi Gauss-Jordan
                    case 2:
                    // Ntar taro disini aja fungsinya EliminasiGauss()
                    break;

                    case 3:
                    break;

                    case 4:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
                break;

                case 3:
                System.out.println("MATRIKS BALIKAN");
                System.out.println("\nPilih Metode: ");
                System.out.println("1. Metode Identitas");
                System.out.println("2. Metode Adjoint");
                System.out.println("3. Kembali Ke Menu");
                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try{
                        input = Integer.parseInt(row[0]);
    
                    }catch(NumberFormatException e){
                        input = 0;
                    }
                    if (input <= 0 || input > 5){
                        System.out.println("Input tidak valid!");
                    }
                }while(input <= 0 || input > 5);

                switch(input){
                    // Metode eliminasi gauss
                    case 1:
                    // Ntar taro disini aja fungsinya EliminasiGauss()
                    break;
                    
                    // Metode eliminasi Gauss-Jordan
                    case 2:
                    // Ntar taro disini aja fungsinya EliminasiGauss()
                    break;

                    case 3:
                    break;

                    case 4:
                    break;

                    case 5:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
                break;
            }
        }

    }

    public static void SPLGauss(){
        String line;
        String[] row;
        matrix M = new matrix();
        int baris, kolom, input = 0;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan: ");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");

        do{
            line = in.nextLine();
            row = line.split(" ");
            try{
                input = Integer.parseInt(row[0]);
            }catch (NumberFormatException e){
                input = 0;
            }
            if (input <= 0 || input > 2){
                System.out.println("Input tidak valid!");
            }
        }while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama (.txt): ");
            String filename = in.nextLine();
            filename = "./test/" + filename;
            M.readFileMatrix(filename);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try{
                    baris = Integer.parseInt(row[0]);
                }catch (NumberFormatException e){
                    baris = 0;
                }
                if (baris <= 0){
                    System.out.println("Input tidak valid!");
                }
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try{
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e){
                    kolom = 0;
                }
                if (kolom <= 0){
                    System.out.println("Input tidak valid!");
                }
            } while (kolom <= 0);
            kolom = kolom + 1;

            System.out.print("Masukkan nilai dan hasil dari tiap variabel di tiap persamaan: \n");
            M.readMatrix(baris, kolom);
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            M = matrixOperation.gauss(M);
            SPL.solveSPL(M);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try{
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e){
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst){
                    System.out.println("Input tidak valid!");
                }
                notFirst = true;
            } while(input <= 0 || input > 2);

            switch (input){
                case 1:
                SPL.solveSPLFile(M);
                break;
                
                case 2:
                System.out.println("\nOK! Kembali ke menu utama");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void SPLGaussJordan(){
        String line;
        String[] row;
        matrix M = new matrix();
        int baris, kolom, input;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan: ");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");

        do{
            line = in.nextLine();
            row = line.split(" ");
            try{
                input = Integer.parseInt(row[0]);
            }catch (NumberFormatException e){
                input = 0;
            }
            if (input <= 0 || input > 2){
                System.out.println("Input tidak valid!");
            }
        }while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama (.txt): ");
            String filename = in.nextLine();
            filename = "./test/" + filename;
            M.readFileMatrix(filename);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try{
                    baris = Integer.parseInt(row[0]);
                }catch (NumberFormatException e){
                    baris = 0;
                }
                if (baris <= 0){
                    System.out.println("Input tidak valid!");
                }
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try{
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e){
                    kolom = 0;
                }
                if (kolom <= 0){
                    System.out.println("Input tidak valid!");
                }
            } while (kolom <= 0);
            kolom = kolom + 1;

            System.out.print("Masukkan nilai dan hasil dari tiap variabel di tiap persamaan: \n");
            M.readMatrix(baris, kolom);
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            M = matrixOperation.gaussJordan(M);
            SPL.solveSPL(M);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try{
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e){
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst){
                    System.out.println("Input tidak valid!");
                }
                notFirst = true;
            } while(input <= 0 || input > 2);

            switch (input){
                case 1:
                SPL.solveSPLFile(M);
                break;
                
                case 2:
                System.out.println("\nOK! Kembali ke menu utama");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void SPLInverse(){
        String line;
        String[] row;
        matrix M = new matrix();
        int baris, kolom, input;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan: ");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");

        do{
            line = in.nextLine();
            row = line.split(" ");
            try{
                input = Integer.parseInt(row[0]);
            }catch (NumberFormatException e){
                input = 0;
            }
            if (input <= 0 || input > 2){
                System.out.println("Input tidak valid!");
            }
        }while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama (.txt): ");
            String filename = in.nextLine();
            filename = "./test/" + filename;
            M.readFileMatrix(filename);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try{
                    baris = Integer.parseInt(row[0]);
                }catch (NumberFormatException e){
                    baris = 0;
                }
                if (baris <= 0){
                    System.out.println("Input tidak valid!");
                }
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try{
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e){
                    kolom = 0;
                }
                if (kolom <= 0){
                    System.out.println("Input tidak valid!");
                }
            } while (kolom <= 0);
            kolom = kolom + 1;

            System.out.print("Masukkan nilai dan hasil dari tiap variabel di tiap persamaan: \n");
            M.readMatrix(baris, kolom);
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            SPL.solveWithInverse(M);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try{
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e){
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst){
                    System.out.println("Input tidak valid!");
                }
                notFirst = true;
            } while(input <= 0 || input > 2);

            switch (input){
                case 1:
                // SPL.solveInverseFile(M);
                break;
                
                case 2:
                System.out.println("\nOK! Kembali ke menu utama");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void SPLCramer(){
        String line;
        String[] row;
        matrix M = new matrix();
        int input, baris, kolom;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
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
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.readFileMatrix(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    baris = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    baris = 0;
                }
                if (baris <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    kolom = 0;
                }
                if (kolom <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (kolom <= 0);
            kolom = kolom + 1;
    
            System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
            M.readMatrix(baris, kolom);
            break;
        }


        if (M.nRow > 0 && M.nCol > 0){
            SPL.solveCramer(M);

            System.out.println("Apakah ingin dalam bentuk file?");
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
                if ((input <= 0 || input > 2) && notFirst) {
                    System.out.println("Input tidak valid");
                }
                notFirst = true;
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                // SPL.SolveCramerFile(M);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }
}