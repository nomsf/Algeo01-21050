// ini file buat ngetes algoritma nya aja

import java.util.Scanner; // package buat ngebaca input user
// dia ngeimport class Scanner trus aksesnya harus bikin object dulu kayanya

public class Proto {

    public static double[][] readMatrix(){
        Scanner read = new Scanner(System.in); // buat obj read dari Scanner class

        System.out.print("Masukan baris matriks: ");
        int row = read.nextInt();  // nextInt = method dr Scanner buat ngebaca integer

        System.out.print("Masukan kolom matriks: ");
        int col = read.nextInt();

        double[][] mat = new double[row][col]; // assign ukuran array

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                //System.out.print("Masukan elemen Matriks [" + i +"][" + j + "] : ");
                mat[i][j] = read.nextDouble();
            }
        }
        read.close();
        return mat;
    }

    static int getRow(double[][] m){
        return m.length;
    }

    static int getCol(double[][] m){
        return m[0].length;
    }

    static void printMatrix(double[][] mat){
        int ROW = getRow(mat);
        int COL = getCol(mat);

        System.out.println("Baris: " + ROW);
        System.out.println("Kolom: " + COL);

        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

    }

    static double[][] copyMatrix(double[][] m){
        double[][] mcopy = new double[getRow(m)][getCol(m)];
        
        for(int i = 0; i < getRow(m); i++){
            for(int j = 0; j < getCol(m); j++){
                mcopy[i][j] = m[i][j];
            }
        }
        return mcopy;
    }

    static int correctionPos(double[][] m){
        // tuker tuker baris biar valid pas dimasukin ke determinanUt
        // juga menghasilkan konstanta p untuk nentuin pos/neg determinant nya
        int n = getRow(m);
        int p = 1;

        int[] zero = new int[n];

        // ngitung jumlah 0 pertama di tiap baris
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (m[i][j] == 0){
                    zero[i] += 1;
                }
                else {
                    continue;
                }
            }
        }

        return p;
    }

    static double determinanUt(double[][] m){
        // hitung determinan dengan metode upper triangle.
        // ERROR : Kalau mat[0][0] / mat[1][1] = 0 hasilnya salah.
        double det = 1;
        int i,j,k;
        double ratio;
        double[][] mcopy = copyMatrix(m);


        for (i = 0; i < getRow(m); i++){
            for (j = 0; j < getRow(m); j++){
                mcopy[i][j] =  m[i][j];
            }
        }

        for (i = 0; i < getRow(m); i++){
            for (j = i+1; j < getRow(m); j++){
                ratio = mcopy[j][i] / mcopy[i][i];

                for (k = 0; k < getRow(m); k++){
                    mcopy[j][k] = mcopy[j][k] - (ratio * mcopy[i][k]);
                }
            }
        }
        for (i = 0; i < getRow(m); i++){
            det = det * mcopy[i][i];
        }
            return det;

    }

    // static double determinanKofaktor(double[][] mat){

    // }




    public static void main(String[] args) {

            double[][] mat = readMatrix();
            
            printMatrix(mat); 

            System.out.println(determinanUt(mat));
    }
}