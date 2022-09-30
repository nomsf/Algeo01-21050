import java.util.Scanner;


public class SplBalikan {
    
    static void splInverse(double[][] m){
        int mrow = MatrixOp.getRow(m);
        int mcol = MatrixOp.getCol(m);

        int k = 0, l = 0;


        double[][] mA = new double[mrow][mrow];
        double[][] mB = new double[mrow][1];

        for (int i = 0; i < mrow; i++ ){// buat split matrixnya jadi matrix a dan b
            for (int j = 0; j < mcol; j++){
                if(j == mcol - 1){
                    mB[k][0] = m[i][j];
                }
                else{
                    if(j == 0){
                        l = 0;
                    }
                    mA[k][l] = m[i][j];
                }
                l++;
            }
            k++;
        } 

        IOKeyboard.printMatrix(mA);
        IOKeyboard.printMatrix(mB);

        double[][] minv = InverseGauss.inverseGauss(mA);

        IOKeyboard.printMatrix(minv);

        double[][] result = MatrixOp.matrixTimes(minv, mB);
        
        for(int i = 0; i < MatrixOp.getRow(result); i++){
            System.out.println("X" + (i+1) + "= " + result[i][0]);
        }
    }

    public static void main(String[] args){
        // double[][] m = IOKeyboard.readMatrix();
        Scanner read = new Scanner(System.in);

        double[][] m = IOKeyboard.readMatrix(read);

        splInverse(m);
        // splInverse(m);
    }
}
