public class MatrixOp {
    static int getRow(double[][] m){
        return m.length;
    }

    static int getCol(double[][] m){
        return m[0].length;
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
    public static boolean iscolzero(double[][] mat, int col){
        boolean colzero = true;
        int i = 0;
        while (colzero && i < mat.length){
            if (mat[i][col] != 0){
                colzero = false;
            }
            i++;
        }
        return colzero;
    }
    public static void displayStrArr(String[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
    static boolean isRowZero(double[][] mat){
        // cek apakah ada baris yang seluruhnya 0
        boolean zeroRow = false;

        for (int i = 0; i < MatrixOp.getRow(mat); i++){
            for (int j = 0; j < MatrixOp.getRow(mat); j++){
                if (mat[i][j] != 0){
                    break;
                }
                else if (mat[i][j] == 0 && j == MatrixOp.getRow(mat)-1){
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

        for (int i = 0; i < MatrixOp.getRow(mat); i++){
            for (int j = 0; j < MatrixOp.getRow(mat); j++){
                if (mat[j][i] != 0){
                    break;
                }
                else if (mat[j][i] == 0 && j == MatrixOp.getRow(mat)-1){
                    zeroCol = true;
                    break;
                }
            }
        }
        return zeroCol;
    }

    static double[][] matrixTimes(double[][] m1, double[][] m2){

        double[][] mr = new double[getRow(m1)][getCol(m2)]; 

        for (int i = 0; i < getRow(m2); i++){
            for (int j = 0; j < getCol(m2); j++){
                for(int k = 0; k < getRow(m2); k++){
                    mr[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return mr;

    }
}
