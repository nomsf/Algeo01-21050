
import java.util.Scanner; // package buat ngebaca input user
// dia ngeimport class Scanner trus aksesnya harus bikin object dulu kayanya


public class MatrixOp {


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

    static void copyRow(double[] rcopy, double[] rori,int n){
        for (int i = 0; i < n; i++){
            rcopy[i] = rori[i];
        }
    }

    static int findVal(int[] arr, int val){
        // return index dari value yg ditemukan
        for(int i = 0; i < arr.length; i++){

        }
        return 0;
    }

    static void timesVal(double[][] m , double val){
        // kali setiap elemen dengan suatu value
        for(int i = 0; i < getRow(m); i++){
            for(int j = 0; j < getCol(m); j++){
                m[i][j] = m[i][j] * val;
            } 
        }

    }

    static void transpose(double[][] m){
        double[][] temp = copyMatrix(m);

        for(int i = 0; i < getRow(m); i++){
            for(int j = 0; j < getCol(m); j++){
                m[j][i] = temp[i][j];
            }
        }
    }

}
