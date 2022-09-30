import java.util.Scanner;

public class regresi{
    public static double[][] regresisolver(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        double[][] aug = new double[row][col + 1];
        for (int i = 0; i < row; i++) {
            aug[i][0] = 1;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                aug[i][j] = mat[i][j - 1];
            }
        }
        double[][] NEE = new double[col][col + 1];
        for(int i = 0; i < col; i++){
            for(int j = 0; j < col + 1; j++){
                NEE[i][j] = 0;
                for(int k = 0; k < row; k++){
                    NEE[i][j] += aug[k][i] * aug[k][j];
                }
            }
        }
        //IOKeyboard.printMatrix(NEE);
        double[][] res = gaussjordan.gaussel(NEE);
        return res;
    }
    public static void printpers(double[][] res){
        int row = res.length;
        int col = res[0].length;
        if(res[0][col - 1] != 0) System.out.printf("y = %f", res[0][col - 1]);
        for (int i = 1; i < row; i++) {
            if(res[i][col - 1] == 0) continue;
            System.out.printf(" + (%f) x%d", res[i][col - 1], i);
        }
        System.out.println();
    }
    public static void taksir(double[] vartaksir, double[][] res){
        int row = res.length;
        int col = res[0].length;
        double result = res[0][col - 1];
        for (int i = 0; i < row - 1; i++) {
            result += res[i + 1][col - 1] * vartaksir[i];
        }
        System.out.println(result);
    }
    /* Driver */
    public static void regresidriver() {
        Scanner read = new Scanner(System.in);
        int opt = IOKeyboard.InputOption(read);
        if(opt == 2){
            double[][] mat = IOKeyboard.readMatrixRegresi(read);
            printpers(regresisolver(mat));
        }
        /*else{
            String fileName = IOFile.InputFileName();
            int row = IOFile.RowCounter(fileName);
            int col = IOFile.ColCounter(fileName);
            double[][] mat = IOFile.readFile(fileName, row, col);
            splsolverprint(gaussel(mat));
        }*/
        read.close();
    }
    public static void main(String[] args) {
        regresidriver();
    }
}