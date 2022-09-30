import java.util.Scanner;

public class SplCramer{

    static void solveCramer(double[][] m){

        int mrow = MatrixOp.getRow(m), mcol = MatrixOp.getCol(m);

        int k = 0, l = 0;

        double[][] mA = new double[mrow][mrow];
        double[][] mB = new double[mrow][1]; 
        double[][] mtemp = new double[mrow][mrow];

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

        // solve

        double det = Cofactor.determinantKofaktor(mA);
        double dettemp;

        int marow = MatrixOp.getRow(mA);
        int macol = MatrixOp.getCol(mA);

        for (int count = 0; count < macol ; count++){
            for (int i = 0; i < marow; i++ ){
                for (int j = 0; j < macol; j++){

                    if( j == count ){
                        mtemp[i][j] = mB[i][0];
                    }
                    else{
                        mtemp[i][j] = mA[i][j];
                    }

                }
            }

            dettemp = Cofactor.determinantKofaktor(mtemp);

            System.out.println("x" + count + " = " + (dettemp/det));
        }
        



    }


    public static void main(String[] args){

        Scanner read = new Scanner(System.in);

        double[][] m = IOKeyboard.readMatrix(read);

        solveCramer(m);

    }

    
}
