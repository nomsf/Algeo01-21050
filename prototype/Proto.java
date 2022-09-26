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

    // static boolean isCorrect(double[][] m){
    //     // apakah bentuknya udah bener
    //     int zCount = 0;
    //     boolean corr = true;

    //     for (int i = 0; i < getRow(m); i++){
    //         zCount = 0;
    //         for (int j = 0; j < getRow(m); j++){
    //             if (m[i][j] == 0){
    //                 zCount ++;
    //             }
                
    //             if (zCount >= i){
    //                 corr = false;
    //             }

    //         }
    //     }
    // }

    static int correctionPos(double[][] m){
        // tuker tuker baris biar valid pas dimasukin ke determinanUt
        // juga menghasilkan konstanta p untuk nentuin pos/neg determinant nya
        int n = getRow(m);
        int zero = 0;
        int p = 1;
        boolean swap;
        double[] temp = new double[n];

        for (int i = 0; i < n; i++){
            zero = 0;
            swap = false;
            for (int j = 0; j < n; j++){
                if(m[i][j] == 0){
                    zero++;
                    swap = true;
                }
                else{
                    break;
                }
            }
            if (swap == true && i != zero){
                temp = m[zero];
                m[zero] = m[i];
                m[i] = temp;
                p *= -1;
            }
        }

        if (m[0][0] == 0){
            p *= correctionPos(m);
        }
    return p;
    }

    static boolean isRowZero(double[][] mat){
        // cek apakah ada baris yang seluruhnya 0
        boolean zeroRow = false;

        for (int i = 0; i < getRow(mat); i++){
            for (int j = 0; j < getRow(mat); j++){
                if (mat[i][j] != 0){
                    break;
                }
                else if (mat[i][j] == 0 && j == getRow(mat)-1){
                    zeroRow = true;
                    break;
                }
            }
        }
        return zeroRow;
    }

    static boolean isColZero(double[][] mat){
        // cek apakah ada kolom yang seluruhnya 0
        boolean zeroCol = false;

        for (int i = 0; i < getRow(mat); i++){
            for (int j = 0; j < getRow(mat); j++){
                if (mat[j][i] != 0){
                    break;
                }
                else if (mat[j][i] == 0 && j == getRow(mat)-1){
                    zeroCol = true;
                    break;
                }
            }
        }
        return zeroCol;
    }



    static double determinanUt(double[][] m){
        // hitung determinan dengan metode upper triangle.
        // ERROR : Kalau mat[0][0] / mat[1][1] = 0 hasilnya salah.
        double det = 1;
        int i,j,k;
        double ratio;
        double[][] mcopy = copyMatrix(m);
        
        if (isRowZero(m) || isColZero(m)){
            return 0;
        }
        else{

            for (i = 0; i < getRow(m); i++){
                for (j = 0; j < getRow(m); j++){
                    mcopy[i][j] =  m[i][j];
                }
            }

            int p = correctionPos(mcopy);
            printMatrix(mcopy);
    
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

            printMatrix(mcopy);

        return p * det;
        }

    }

    // static double determinanKofaktor(double[][] mat){

    // }




        public static void main(String[] args) {

            double[][] mat = readMatrix();
            
            printMatrix(mat); 
            

            System.out.println(determinanUt(mat));
    }
}