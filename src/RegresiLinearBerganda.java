import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class RegresiLinearBerganda {
    public static void main(String[] args) {
        RLB(); // Panggil metode RLB() dari metode main()
    }
    public static void RLB(){
        /*Kamus lokal */
        int m,n, userchoice;
        double x;
        m=0;n=0;
        matrix mtx = new matrix();
        mtx.nCol = 100;mtx.nRow=100;
        matrix mtxforoperate = new matrix();
        mtxforoperate.nCol=16;mtxforoperate.nRow=1;
    

        /*Algoritma */
        //Input
        Scanner input = new Scanner(System.in);
        BufferedReader inputfile = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nPilih metode masukan: ");
        System.out.println("1. Dari keyboard");
        System.out.println("2. Dari file");
        System.out.println("Pilih : ");
        userchoice = input.nextInt();
        while (userchoice !=1 && userchoice != 2){
            System.out.println("Pilihan tidak valid! Silahkan Pilih ulang!\nPilih : ");
            userchoice = input.nextInt();
        }

        if (userchoice==1){
            System.out.println("Masukkan jumlah data yang akan dihitung : ");
            m = input.nextInt();
            System.out.println("Notes : Untuk jumlah variabel, variabel y tidak diperhitungkan\nMasukkan jumlah variabel : ");
            n = input.nextInt();
            matrix mtxdata = new matrix(); mtxdata.nRow=n+1;mtxdata.nCol=m;
            matrix mtxinput = new matrix(); mtxinput.nRow=1;mtxinput.nCol=n;
            System.out.println("Notes : Masukkan x dan y dengan urutan x1 x2 x3 ... xn y");

            for (int j = 0; j < m; j++) {
                String urutan = Integer.toString(j + 1);
                System.out.printf("Masukkan titik-titik ke-%s: \n", urutan);
            
                String line = input.nextLine(); // Baca satu baris input sebagai string
                String[] values = line.split(" "); // Pisahkan angka-angka dengan spasi
            
                boolean valid = true;
            
                if (values.length == n + 2) {
                    // Baris pertama harus sesuai dengan n + 2 elemen (x1, x2, x3, y)
                    for (int i = 0; i < n + 2; i++) {
                        try {
                            x = Double.parseDouble(values[i]);
                            matrix.setElement(mtxdata, i, j, x);
                        } catch (NumberFormatException e) {
                            System.err.println("Format angka tidak valid: " + values[i]);
                            valid = false;
                            break; // Keluar dari loop jika ada kesalahan format
                        }
                    }
                } else {
                    valid = false; // Jumlah elemen tidak sesuai
                }
            
                if (!valid) {
                    System.err.println("Format baris tidak valid: " + line);
                }
            }
            
            

            System.out.println("Notes : Masukkan x dan y dengan urutan x1 x2 x3 ... xn y");
            System.out.println("Masukkan data yang ingin dihampiri nilainya : ");
            for (int i=0;i<n;i++){
                    x = input.nextDouble();
                    matrix.setElement(mtxinput, 0,i,x);
            }
            mtx = mtxdata;
            mtxforoperate = mtxinput;
            
                
        } else{
            Boolean finding = false;
            while(!finding){
                finding = true;
                String namafile = "";
                System.out.println("Masukkan nama file : ");
                try{
                    namafile = inputfile.readLine();

                }catch (IOException err){
                    System.err.println("Terjadi kesalahan I/O: " + err.getMessage());
                }

                try{
                    Scanner foundedfile = new Scanner(new File("../test/"+namafile));
                    m=0;
                    int cek;
                    while(foundedfile.hasNextLine()){
                        n++;
                        cek= foundedfile.nextLine().split(" ").length;
                        if(cek>m){
                            m = cek;
                        }
                    }
                    n--;
                    int replace = n;
                    n=m;
                    m= replace;
                    foundedfile.close();

                    matrix mtxdata = new matrix();
                    mtxdata.nRow=n;mtxdata.nCol=m;
                    matrix mtxinput = new matrix();
                    mtxdata.nRow=1;mtxdata.nCol=n;

                    foundedfile = new Scanner(new File("../test/"+namafile));
                    for(int i = 0; i < m; i++){
                        for(int j = 0; j< n; j++){
                            x=foundedfile.nextDouble();
                            matrix.setElement(mtxdata,j, i, x);
                        }
                    }

                    for(int i = 0; i < n-1; i++){
                        x = foundedfile.nextDouble();
                        matrix.setElement(mtxinput,0, i, x);
                    }

                    foundedfile.close();
                    mtx = mtxdata;
                    mtxforoperate = mtxinput;

                }catch (IOException err){
                    System.err.println("Terjadi kesalahan I/O: " + err.getMessage());
                    err.printStackTrace();
                    finding = false;
                }
            }
        }


        //Solve Regression with Regresi Linear Berganda
        int i,j,k;
        i=0;k=0;
        double temp,sum;
        matrix M = new matrix(); //menyimpan matriks yang akan digunakan dalam proses perhitungan regresi linear berganda
        M.nCol=M.nRow+1;

        for(j=0;j<M.nRow+1;j++){
            if(j==0){
                matrix.setElement(M, i, j, M.nCol);
            }else{
                sum=0;
                for(k=0;k<M.nCol;k++){
                    temp = matrix.getElement(M, j-1, k);
                    sum+=temp;
                }
                matrix.setElement(M, i, j, sum);

            }
        }

        for(i=1;i<M.nRow;i++){
            for (j=0;j<M.nRow+1;j++){
                if(j==0){
                    sum=0;
                    for(k=0;k<M.nCol;k++){
                        temp = matrix.getElement(mtx, i-1, k);
                        sum+=temp;
                    }
                    matrix.setElement(M, i,j, sum);
                } else{
                    sum=0;
                    for(k=0;k<M.nCol;k++){
                        temp = matrix.getElement(mtx, i-1, k)*matrix.getElement(mtx, j-1, k);
                        sum+=temp;
                    }
                    matrix.setElement(M, i, j, sum);
                }
            }
        }

        SPL solusi = new SPL();
        matrixOperation.gaussJordan(M);
        String simpan;
        double taksiranY,currentvalue;
        taksiranY =0;
        System.out.println("Sistem persamaan regresi linear yang terbentuk sebagai berikut\n");
        System.out.println("f(x) = ");

        for(i=0;i<solusi.nEff;i++){
            currentvalue = solusi.x[i];
            if (i!=0) {
                currentvalue = currentvalue*matrix.getElement(mtxforoperate, 0, i-1);
            }
            taksiranY+=currentvalue;
        }

        for(i=0;i<solusi.nEff;i++){
            simpan = " ";
            if(i!=0 && solusi.x[i]>=0){
                simpan += " + ";
            }
            if(i!=0){
                simpan+=" x "+Integer.toString(i);
            }
            System.out.println(simpan);
        }
        String answer = Double.toString(taksiranY);

        System.out.println("\n");
        System.out.println("Hasil hampiran (taksiran) nilai regresi nya adalah "+answer+".\n");

        //Penyimpanan ke file
        System.out.println("\nSimpan jawaban ke file?: ");
        System.out.println("1. Boleh");
        System.out.println("2. Tidak Perlu");
        System.out.println("Pilih : ");
        while(userchoice != 1 && userchoice != 2){
            System.out.println("Pilihan tidak valid! Silahkan Pilih ulang!\nPilih :  ");
            userchoice = input.nextInt();
        }
        if(userchoice==1){
            String namafile = "";
            System.out.println("Masukkan nama file : ");
                try{
                    namafile = inputfile.readLine();

                }catch (IOException err){
                    System.err.println("Terjadi kesalahan I/O: " + err.getMessage());
                }

                try{
                    FileWriter foundedfile = new FileWriter("../test/"+namafile);
                    foundedfile.write("Sistem persamaan regresi linear yang terbentuk sebagai berikut\nf(x) = ");
                    for(i=0;i<solusi.nEff;i++){
                        simpan =" ";
                        if(i!=0&&((solusi.x[i])>0)){
                            simpan += " + ";
                        }

                        simpan+=Double.toString((solusi.x[i]));
                        if(i!=0){
                            simpan+= " x "+Integer.toString(i);
                        }
                        foundedfile.write(simpan);
                        foundedfile.close();
                        
                    }
                    foundedfile.write("\n");
                    foundedfile.write("Hasil hampiran (taksiran) nilai regresi nya adalah "+answer+".\n");
                    foundedfile.close();

                    }catch(IOException err){
                    System.err.println("Terjadi kesalahan I/O: " + err.getMessage());
                    err.printStackTrace();
                    }
    }
    
}}
