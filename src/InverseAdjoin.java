import java.util.Scanner;
import java.util.Random;

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
        double[][] m = new double[15][15];
        Random rand = new Random();
        Scanner read = new Scanner(System.in);

        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                m[i][j] = rand.nextInt(15);
            }
        }


        IOKeyboard.printMatrix(m);

        System.out.println("hasil inverse: ");

        IOKeyboard.printMatrix(inverseAdjoin(m));
    }
}
