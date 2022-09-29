public class Cofactor extends Proto{

    static double[][] minorEntry(double[][] m, int a, int b){
        double[][] me = new double[MatrixOp.getRow(m)-1][MatrixOp.getRow(m)-1];
        int x = 0, y = 0;

        for(int i = 0; i < MatrixOp.getRow(m); i++){
            for(int j = 0; j < MatrixOp.getRow(m); j++){
                if( i != a && j != b){

                    me[x][y] = m[i][j];
                    y++;
                }
                if(y == MatrixOp.getRow(me)){
                    x++;
                    y = 0;
                }
                    
            }
        }

        return me;
    } 

    static double[][] matrixCofactor(double[][] m){
        // menghasilkan matriks kofaktor
        double[][] mc = new double[MatrixOp.getRow(m)][MatrixOp.getRow(m)];
        int p = 1;

        for(int i = 0; i < MatrixOp.getRow(m); i++){
            for(int j = 0; j < MatrixOp.getRow(m); j++){
                mc[i][j] = p * determinantKofaktor(minorEntry(m, i, j));
                p *= -1;
            }
        }
        return mc;
    }

    static double determinantKofaktor(double[][] m){
        double det = 0;
        int p = 1;
        double[][] me;

        // kofaktor adalah determinan dari elemen kecuali yang di kofaktorin.

        if (MatrixOp.isRowZero(m) || MatrixOp.isColZero(m)){
            return 0;
        }
        else if(m.length == 1){
            return m[0][0];
        }
        else {
            for(int i = 0; i < MatrixOp.getRow(m); i++){
                me = minorEntry(m, 0, i);
                det += m[0][i] * p * determinantKofaktor(me); // warning
                p *= -1;
            }
        }

        return det;
    }

    public static void main(String[] args) {
        double[][] m;
        
        m = IOKeyboard.readMatrix();

        IOKeyboard.printMatrix(m);

        IOKeyboard.printMatrix(matrixCofactor(m));
    }
}
