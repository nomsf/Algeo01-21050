
public class InverseAdjoin {

    static double[][] inverseAdjoin(double[][] m){
        // menghasilkan inverse dari matriks dengan metode adjoin
        double[][] ma;

        ma = Cofactor.matrixCofactor(m);

        MatrixOp.transpose(ma); // menghasilkan adjoin dengan transpose matriks kofaktor

        MatrixOp.timesVal(ma, (1/Cofactor.determinantKofaktor(m))); // mengkalikan dengan 1/det(m)

        return ma;
    }

    public static void main(String[] args){
        double[][] m;

        m = IOKeyboard.readMatrix();

        IOKeyboard.printMatrix(m);

        IOKeyboard.printMatrix(inverseAdjoin(m));
    }
}
