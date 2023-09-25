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
                        input = 0
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
                        input = 0
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
                        input = 0
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

}