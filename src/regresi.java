import java.util.Scanner;

public class regresi{
    public static double[][] regresisolver(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        double[][] X = new double[row][col];
        double[][] Y = new double[row][1];
        for(int i = 0; i < row; i++) {
            X[i][0] = 1;
        }
        for(int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                X[i][j] = mat[i][j - 1];
            }
        }
        for(int i = 0; i < row; i++){
            Y[i][0] = mat[i][col - 1];
        }
        double[][] NEE = new double[col][col + 1];
        double[][] XtX = MatrixOp.matrixTimes2(MatrixOp.ftranspose(X), X);
        double[][] XtY = MatrixOp.matrixTimes2(MatrixOp.ftranspose(X), Y);
        for(int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                NEE[i][j] = XtX[i][j];
            }
        }
        for (int i = 0; i < col; i++) {
            NEE[i][col] = XtY[i][0];
        }
        double[][] res = gaussjordan.gaussel(NEE);
        return res;
    }
    public static void printpers(double[][] res){
        int row = res.length;
        int col = res[0].length;
        if(res[0][col - 1] != 0) System.out.printf("y = %f", res[0][col - 1]);
        for (int i = 1; i < row; i++) {
            if(res[i][col - 1] == 0) continue;
            System.out.printf(" + (%f)x%d", res[i][col - 1], i);
        }
        System.out.println();
    }
    public static double taksir(double[] xtaksir, double[][] res){
        int row = res.length;
        int col = res[0].length;
        double result = res[0][col - 1];
        for (int i = 0; i < row - 1; i++) {
            result += res[i + 1][col - 1] * xtaksir[i];
        }
        System.out.printf("y = %f", result);
        System.out.println();
        return result;
    }
    /* Driver */
    public static void regresidriver(Scanner read) {
        int opt = IOKeyboard.InputOption(read);
        double[][] res;
        double restaksir;
        if(opt == 1){
            double[][] mat = IOKeyboard.readMatrixRegresi(read);
            res = regresisolver(mat);
            printpers(res);
            double[] xtaksir = IOKeyboard.readArrTaksir(read, res.length - 1);
            restaksir = taksir(xtaksir, res);
        }
        else{
            String fileName = IOFile.InputFileName(read);
            int row = IOFile.RowCounter(fileName);
            int col = IOFile.ColCounter(fileName);
            /*
            interpretasi penulisan nilai x1..xn (peubah) adalah pada kolom-kolom 
            bagian kiri sedangkan kolom terakhir berisi nilai y.
            
            interpretasi penulisan xk yang akan ditaksir
            adalah pada baris terakhir mulai kolom pertama sampai kolom - 1.
            kolom terakhir berisi elemen penanda -1.
            
            */
            double[][] matandtaksir = IOFile.readFile(fileName, row, col); 
            double[][] mat = new double[row - 1][col];
            for(int i = 0; i < row - 1; i++){
                for(int j = 0; j < col; j++){
                    mat[i][j] = matandtaksir[i][j];
                }
            }
            double[] xtaksir = new double[col - 1];
            for(int i = 0; i < col - 1; i++){
                xtaksir[i] = matandtaksir[row - 1][i];
            }
            res = regresisolver(mat);
            printpers(res);
            restaksir = taksir(xtaksir, res);
        }
        int optw = IOKeyboard.WritetoFileOption(read);
        if(optw == 1){
            String fileName_write = IOFile.InputFileName(read);
            boolean iswritesc = IOFile.createFile(fileName_write);
            if(iswritesc){
                IOFile.writeRegresi(fileName_write, res, restaksir);
            }
        }
    }
}