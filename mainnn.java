import java.util.Scanner;

/*
Ini buat main programnya
Inputan masuk ke variabel input
*/

public class mainnn {
    static Scanner in = new Scanner (System.in);
    public static void main(String[] args) {
        boolean run = true;
        int input = 0;
        String line;
        String[] row = new String[100];

        // System.out.println("||   / |  / / //   / /  / /        //   ) )  //   ) ) /|    //| |     //   / /       // ");
        // System.out.println("||  /  | / / //____    / /        //        //   / / //|   // | |    //____         //  ");
        // System.out.println("|| / /||/ / / ____    / /        //        //   / / // |  //  | |   / ____         //   ");
        // System.out.println("||/ / |  / //        / /        //        //   / / //  | //   | |  //                   ");
        // System.out.println("|  /  | / //____/ / / /____/ / ((____/ / ((___/ / //   |//    | | //____/ /      //     ");
        // System.out.println("                                                  ");
        // System.out.println("                                                  ");

        // System.out.println(" _    _ _____ _     _____ ________  ___ _____   _ ");
        // System.out.println("| |  | |  ___| |   /  __ \\  _  |  \\/  ||  ___| | |");
        // System.out.println("| |  | | |__ | |   | /  \\/ | | | .  . || |__   | |");
        // System.out.println("| |/\\| |  __|| |   | |   | | | | |\\/| ||  __|  | |");
        // System.out.println("\\  /\\  / |___| |___| \\__/\\ \\_/ / |  | || |___  |_|");
        // System.out.println(" \\/  \\/\\____/\\_____/\\____/\\___/\\_|  |_/\\____/  (_)");
        // System.out.println("                                                  ");
        // System.out.println("                                                  ");

        // Pilih menu
        while (run){
            System.out.println("  ________________________________");
            System.out.println(" / \\                               \\");
            System.out.println("|   | ------------ MENU ----------  |");
            System.out.println(" \\_ |                               |");
            System.out.println("    | 1. Sistem Persamaan Linear    |");
            System.out.println("    | 2. Determinan                 |");
            System.out.println("    | 3. Matriks Balikan            |");
            System.out.println("    | 4. Interpolasi Polinom        |");
            System.out.println("    | 5. Interpolasi Bicubic Spline |");
            System.out.println("    | 6. Regresi Linear Berganda    |");
            System.out.println("    | 7. Keluar                     |");
            System.out.println("    |   ____________________________|___");
            System.out.println("    |  /                               /");
            System.out.println("    \\_/_______________________________/");

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

                case 2:
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

                case 3:
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
}