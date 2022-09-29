public class regresi{
    public static double[][] regresisolver(double[][] mat, double[] y){
        int row = mat.length;
        int col = mat[0].length;
        double[][] aug = new double[row][col + 2];
        for (int i = 0; i < row; i++) {
            aug[i][0] = 1;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                aug[i][j] = mat[i][j - 1];
            }
        }
        for (int i = 0; i < row; i++) {
            aug[i][col + 1] = y[i];
        }
        double[][] NEE = new double[row][col + 2];
        for(int i = 0; i < col; i++){
            for(int j = 0; j < col + 1; j++){
                NEE[i][j] = 0;
                for(int k = 0; k < row; k++){
                    NEE[i][j] += aug[k][i] * aug[k][j];
                }
            }
        }
        double[][] res = gaussjordan.gaussel(NEE);
        return res;
    }
    public static void printpers(double[][] res){
        int row = res.length;
        int col = res[0].length;
        System.out.printf("y = %f", res[0][col]);
        for (int i = 1; i < row; i++) {
            System.out.printf(" + (%f) x%d", res[i][col], i);
        }
    }
    public static void taksir(double[] vartaksir, double[][] res){
        int row = res.length;
        int col = res[0].length;
        double result = res[0][col];
        for (int i = 0; i < row - 1; i++) {
            result += res[i + 1][col] * vartaksir[i];
        }
        System.out.println(result);
    }
}