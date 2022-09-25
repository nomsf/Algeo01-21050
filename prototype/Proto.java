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
                System.out.print("Masukan elemen Matriks [" + i +"][" + j + "] : ");
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
            System.out.println();
            for (int j = 0; j < COL; j++){
                System.out.print(mat[i][j] + " ");
            }
        }

    }
    public static void main(String[] args) {

        double[][] mat = readMatrix();
        
        printMatrix(mat);
    }
}