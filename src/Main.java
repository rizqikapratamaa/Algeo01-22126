import java.util.Scanner;
public class Main {
    static Scanner in = new Scanner (System.in);
    public static void main(String[] args) {
        boolean run = true;
        int input = 0;
        String line;
        String[] row = new String[100];

        // Pilih menu
        while (run){
            System.out.print("\033[H\033[2J");
            System.out.print("Kalkulator Matriks by Mandala Jaya\n");
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic Spline");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Keluar");
            
            do {
                System.out.print(">>");
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
                System.out.print("\033[H\033[2J");
                System.out.println("Sistem Persamaan Linear");
                System.out.println("\nPILIH METODE");
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println("5. Kembali Ke Menu");
                do{
                    System.out.print(">>");
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
                    System.out.print("\033[H\033[2J");
                    SPLGauss();
                    break;
                    
                    // Metode eliminasi Gauss-Jordan
                    case 2:
                    System.out.print("\033[H\033[2J");
                    SPLGaussJordan();
                    break;

                    // Metode matriks balikan
                    case 3:
                    System.out.print("\033[H\033[2J");
                    SPLInverse();
                    break;

                    // Kaidah Cramer
                    case 4:
                    System.out.print("\033[H\033[2J");
                    SPLCramer();
                    break;

                    // Back to main menu
                    case 5:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
                break;

                case 2:
                System.out.print("\033[H\033[2J");
                System.out.println("Determinan");
                System.out.println("\nPilih Metode:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Metode Kofaktor");
                System.out.println("3. Kembali Ke Menu");
                do{
                    System.out.print(">>");
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
                    // Metode OBE
                    case 1:
                    System.out.print("\033[H\033[2J");
                    DeterminanOBE();
                    break;
                    
                    // Kofaktor
                    case 2:
                    System.out.print("\033[H\033[2J");
                    DeterminanKofaktor();
                    break;

                    case 3:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
                break;

                case 3:
                System.out.print("\033[H\033[2J");
                System.out.println("Matriks Balikan");
                System.out.println("\nPilih Metode: ");
                System.out.println("1. Metode Identitas");
                System.out.println("2. Metode Adjoint");
                System.out.println("3. Kembali Ke Menu");
                do{
                    System.out.print(">>");
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
                    System.out.print("\033[H\033[2J");
                    InverseIdentitas();
                    break;
                    
                    // Metode inverse adjoint
                    case 2:
                    System.out.print("\033[H\033[2J");
                    InverseAdjoint();
                    break;

                    case 3:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
                break;

                case 4:
                System.out.print("\033[H\033[2J");
                InterpolasiPolinom();
                break;

                case 5:
                break;

                case 6:
                RLB();
                break;

                case 7:
                run = false;
                break;

            }
        }

    }

    /* FUNGSI ANTARA */
    public static void SPLGauss(){
        String line;
        String[] row;
        matrix M = new matrix();
        int baris, kolom, input = 0;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan: ");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");

        do{
            System.out.print(">>");
            line = in.nextLine();
            row = line.split(" ");
            try{
                input = Integer.parseInt(row[0]);
            }catch (NumberFormatException e){
                input = 0;
            }
            if (input <= 0 || input > 3){
                System.out.println("Input tidak valid!");
            }
        }while (input <= 0 || input > 3);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama (.txt): ");
            String filename = in.nextLine();
            filename = "../test/" + filename;
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

            case 3:
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            M = matrixOperation.gauss(M);
            SPL.solveSPL(M);

            System.out.println("Maneh mau filenya ga?");
            System.out.println("1. sok");
            System.out.println("2. g dulu");

            do{
                System.out.print(">>");
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
                System.out.println("\nKembali ke menu utama");
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
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");

        do{
            System.out.print(">>");
            line = in.nextLine();
            row = line.split(" ");
            try{
                input = Integer.parseInt(row[0]);
            }catch (NumberFormatException e){
                input = 0;
            }
            if (input <= 0 || input > 3){
                System.out.println("Input tidak valid!");
            }
        }while (input <= 0 || input > 3);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama (.txt): ");
            String filename = in.nextLine();
            filename = "../test/" + filename;
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

            case 3:
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            M = matrixOperation.gaussJordan(M);
            SPL.solveSPL(M);

            System.out.println("Maneh mau filenya ga?");
            System.out.println("1. sok");
            System.out.println("2. g dulu");

            do{
                System.out.print(">>");
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
                System.out.println("\nKembali ke menu utama");
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
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");

        do{
            line = in.nextLine();
            row = line.split(" ");
            try{
                input = Integer.parseInt(row[0]);
            }catch (NumberFormatException e){
                input = 0;
            }
            if (input <= 0 || input > 3){
                System.out.println("Input tidak valid!");
            }
        }while (input <= 0 || input > 3);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama (.txt): ");
            String filename = in.nextLine();
            filename = "../test/" + filename;
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

            case 3:
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            SPL.solveWithInverse(M);

            System.out.println("Maneh mau filenya ga?");
            System.out.println("1. sok");
            System.out.println("2. g dulu");

            do{
                System.out.print(">>");
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
                SPL.solveWithInverseFile(M);
                break;
                
                case 2:
                System.out.println("\nKembali ke menu utama");
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
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 3) {
                System.out.println("Input tidak valid!");
            } 
        } while (input <= 0 || input > 3);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "../test/" + namaFile;
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
                    System.out.println("Input tidak valid!");
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
                    System.out.println("Input tidak valid!");
                } 
            } while (kolom <= 0);
            kolom = kolom + 1;
    
            System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
            M.readMatrix(baris, kolom);
            break;

            case 3:
            break;
        }


        if (M.nRow > 0 && M.nCol > 0){
            SPL.solveCramer(M);

            System.out.println("Maneh mau filenya ga?");
            System.out.println("1. sok");
            System.out.println("2. g dulu");

            do{
                System.out.print(">>");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst) {
                    System.out.println("Input tidak valid!");
                }
                notFirst = true;
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                SPL.solveCramerFile(M);

                case 2:
                System.out.println("\nKembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }
    
    public static void DeterminanOBE(){
        String line;
        String[] row;
        matrix M = new matrix();
        int dimensi, input;
        double det;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");
        
        do{
            System.out.print(">>");
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 3) {
                System.out.println("Input tidak valid!");
            } 
        } while (input <= 0 || input > 3);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "../test/" + namaFile;
            M.readFileMatrix(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid!");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.readMatrix(dimensi, dimensi);
            break;

            case 3:
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            det = matrixOperation.detOBE(M);
            System.out.print("\nDeterminannya adalah: ");
            System.out.println(det);

            System.out.println("Maneh mau filenya ga?");
            System.out.println("1. sok");
            System.out.println("2. g dulu");
            do{
                System.out.print(">>");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 2) {
                    System.out.println("Input tidak valid!");
                } 
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                matrixOperation.detFile(M, det);

                case 2:
                System.out.println("\nKembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void DeterminanKofaktor(){
        String line;
        String[] row;
        matrix M = new matrix();
        int dimensi, input;
        double det;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");
        
        do{
            System.out.print(">>");
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 4) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 4);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "../test/" + namaFile;
            M.readFileMatrix(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.readMatrix(dimensi, dimensi);
            break;

            case 3:
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            det = matrixOperation.determinanKofaktor(M);
            System.out.print("\nDeterminannya adalah: ");
            System.out.println(det);

            System.out.println("Maneh mau filenya ga?");
            System.out.println("1. sok");
            System.out.println("2. g dulu");
            do{
                System.out.print(">>");
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
                matrixOperation.detFile(M, det);

                case 2:
                System.out.println("\nKembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }
    
    public static void InverseIdentitas(){
        String line;
        String[] row;
        matrix M = new matrix();
        matrix inverse = new matrix();
        int dimensi, input = 0;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");
        
        do{
            System.out.print(">>");
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 3) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 3);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "../test/" + namaFile;
            M.readFileMatrix(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.readMatrix(dimensi, dimensi);
            break;

            case 3:
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            if (matrixOperation.determinanKofaktor(M) == 0){
                System.out.println("Matriks tidak memiliki balikan.");
            }
            else{
                inverse = matrixOperation.inverseIdentitas(M);
                System.out.print("\nBalikannya adalah: \n");
                inverse.writeMatrix();
        
                System.out.println("Maneh mau filenya ga?");
                System.out.println("1. sok");
                System.out.println("2. g dulu");
                do{
                    System.out.print(">>");
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
                    inverse.writeMatrixFile(inverse);
        
                    case 2:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void InverseAdjoint(){
        String line;
        String[] row;
        matrix M = new matrix();
        matrix inverse = new matrix();
        int dimensi, input = 0;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");
        
        do{
            System.out.print(">>");
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 3) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 3);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "../test/" + namaFile;
            M.readFileMatrix(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.readMatrix(dimensi, dimensi);
            break;

            case 3:
            break;
        }

        if (M.nRow > 0 && M.nCol > 0){
            if (matrixOperation.determinanKofaktor(M) == 0){
                System.out.println("Matriks tidak memiliki balikan.");
            }
            else{
                inverse = matrixOperation.inverseAdjoint(M);
                System.out.print("\nBalikannya adalah: \n");
                inverse.writeMatrix();
        
                System.out.println("Maneh mau filenya ga?");
                System.out.println("1. sok");
                System.out.println("2. g dulu");
                do{
                    System.out.print(">>");
                    line = in.nextLine();
                    row = line.split(" ");
                    try {
                        input = Integer.parseInt(row[0]);
                    } catch (NumberFormatException e) {
                        input = 0;
                    }
                    if (input <= 0 || input > 2) {
                        System.out.println("Input tidak valid!");
                    } 
                } while (input <= 0 || input > 2);
        
                switch (input){
                    case 1:
                    inverse.writeMatrixFile(inverse);
        
                    case 2:
                    System.out.println("\nKembali ke menu utama...");
                    break;
                }
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void InterpolasiPolinom(){
        String line;
        String[] row;

        /* Memilih metode input */
        System.out.println("\nINTERPOLASI POLINOM");
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");

        int input;
        matrix mIn = new matrix();

        do{
            System.out.print(">>");
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 3) {
                System.out.println("Input tidak valid!");
            } 
        } while (input <= 0 || input > 3);

        /* Memberi masukan */
        switch (input) {
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            String pathFile = "../test/" + namaFile;
            mIn.readFileMatrixBolong(pathFile, 1);
            break;

            case 2:
            mIn = InterpolasiPolinom.inputMatrix();
            break;

            case 3:
            break;
        }

        /* Proses */
        if (!(mIn.nRow == 0 || mIn.nCol == 0)){
            matrix ai = InterpolasiPolinom.ai(InterpolasiPolinom.xi(InterpolasiPolinom.x(mIn)),InterpolasiPolinom.fx(mIn));
            double a = InterpolasiPolinom.a(mIn);

            /* Output file */
            System.out.println("\nHasil Perhitungan Interpolasi Polinom");
            System.out.println("Penjabaran f(x):");
            System.out.println(InterpolasiPolinom.fxString(ai));
            System.out.println("Hasil substitusi dengan nilai x dari masukan:");
            System.out.println("f("+ a +") = " + InterpolasiPolinom.fa(ai, a));
            
            /* Output file */
            System.out.println("\nManeh mau filenya ga?");
            System.out.println("1. sok");
            System.out.println("2. g dulu");
            do{
                System.out.print(">>");
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
                InterpolasiPolinom.IPFile(ai, a);
                break;
        
                case 2:
                System.out.println("\nKembali ke menu utama...");
                break;
            }

        } else {
            System.out.println("\nOperasi gagal, kembali ke menu utama...");
        }
    }

    //Regresi Linear Berganda
    public static void RLB() {
        matrix mtxinput = new matrix();
        int input;
        String mark;
        String[] baris;

        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");

        do{
            System.out.println("Pilih : ");
            mark = in.nextLine();
            baris = mark.split(" ");
            try {
                input = Integer.parseInt(baris[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Pilihan Anda tidak valid, Silahkan Ulangi!");
            } 
        } while (input <= 0 || input > 2);


        switch (input) {
            case 1:
            System.out.print("\nPastikan file yang Anda masukkan sudah berada di dalam folder test.");
            System.out.print("\nNama file harus dalam format (.txt): ");
            String filename = in.nextLine();
            String filespath = "../test/" + filename;
            mtxinput.readFileMatrixBolong(filespath, 1);
            break;

            case 2:
            mtxinput = RegresiLinearBerganda.mtxfromkeyboard();
            break;
        }

        /* Proses */
        if (!(mtxinput.nRow == 0 || mtxinput.nRow == 0)) {
            matrix koefreg = RegresiLinearBerganda.koefreg(mtxinput);
            matrix AI = RegresiLinearBerganda.AI(RegresiLinearBerganda.varbebas(mtxinput), RegresiLinearBerganda.variabelterikat(mtxinput));
            
            //output terminal biasa
            System.out.println("\nHasil Perhitungan Regresi Linear Berganda");
            System.out.println("Persamaan regresi linear berganda f(x):");
            System.out.println(RegresiLinearBerganda.hasilfxStringvers (AI));
            System.out.println("Hampiran (taksiran) nilai f(x):");
            System.out.println("f(x) = " + RegresiLinearBerganda.hasilfx(RegresiLinearBerganda.koefreg(mtxinput), RegresiLinearBerganda.AI(RegresiLinearBerganda.varbebas(mtxinput), RegresiLinearBerganda.variabelterikat(mtxinput))));
            
           //Output File
            System.out.println("\nSimpan dalam bentuk file?");
            System.out.println("1. Boleh");
            System.out.println("2. Tidak perlu");
            
            do{
                System.out.println("Pilih : ");
                mark = in.nextLine();
                baris = mark.split(" ");
                try {
                    input = Integer.parseInt(baris[0]);
                } catch (NumberFormatException e) {
                    input = 0;                    }
                    if (input <= 0 || input > 2) {
                        System.out.println("Input tidak valid");
                    } 
            } while (input <= 0 || input > 2);
    
            switch (input){
                case 1:
                RegresiLinearBerganda.RLBFile(koefreg, AI);;
                break;
    
                case 2:
                System.out.println("\n Silahkan ke menu utama!");
                break;
            }
        } else {
            System.out.println("\nUps! operasi Regresi Linear Berganda tidak dapat dijalankan, Silahkan ke menu utama!");
        }
    }

    /* Bicubic Interpolation */
    public static void BicubicInterpolation(){
        String line;
        String[] row;

        /* Memilih metode input */
        System.out.println("\nBICUBIC INTERPOLATION");
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        System.out.println("\n3. Kembali Ke Menu");

        int input; 
        matrix mInput = new matrix();
    
        /* Menerima masukan dari file */
        System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
        System.out.print("\nNama file (.txt): ");
        String namaFile = in.nextLine();
        String pathFile = "../test/" + namaFile;
        mInput.readFileMatrixBolong(pathFile, 2); //???

        /* Proses */
        if (!(mInput.nRow == 0 || mInput.nCol == 0)) {
            matrix mAij = bicubicInterpolation.mAij(bicubicInterpolation.m16x1(bicubicInterpolation.m4x4(mInput)));
            double a = bicubicInterpolation.getA(mInput); // ???
            double b = bicubicInterpolation.getB(mInput); // ???

            /* Output terminal */
            System.out.println("\nHasil Bicubic Interpolation");
            System.out.println("Hasil substitusi dengan nilai x dan y dari masukan:");
            System.out.println("f(" + a + "," + b + ") = " + bicubicInterpolation.getFab(mAij, a, b));
            
            /* Output file */
            System.out.println("\nApakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                System.out.print(">>");
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
                bicubicInterpolation.bicubicInterFile(mAij, a, b);
        
                case 2:
                System.out.println("\nKembali ke menu utama...");
                break;
            }

        } else {
            System.out.println("\nOperasi gagal, kembali ke menu utama...");
        }
    }

}