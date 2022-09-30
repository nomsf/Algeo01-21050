import java.util.Scanner;

public class SplCramer{

    static void solveCramer(double[][] m, Scanner read){

        int mrow = MatrixOp.getRow(m), mcol = MatrixOp.getCol(m);

        int k = 0, l = 0;

        double[][] mA = new double[mrow][mcol-1];
        double[][] mB = new double[mrow][1]; 
        double[][] mtemp = new double[mrow][mrow];

        for (int i = 0; i < mrow; i++ ){    // buat split matrixnya jadi matrix a dan b
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

        if(!MatrixOp.isSquare(mA)){
            System.out.println("-----  Matriks A Tidak Berbentuk Square!  -----");
        }

        else if (Cofactor.determinantKofaktor(mA) == 0){
            System.out.println("-----  Matriks Tidak Mempunyai Balikan!  -----");
        }

        else{

            double det = Cofactor.determinantKofaktor(mA);
            double dettemp;

            int marow = MatrixOp.getRow(mA);
            int macol = MatrixOp.getCol(mA);

            String rlist = "";

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

                rlist += "X" + String.valueOf(count+1) + " = " + (String.valueOf(dettemp/det) + "   ");
            }

            System.out.println(rlist);

            int opt = IOKeyboard.WritetoFileOption(read);

            if(opt == 1){
                String fname = IOFile.InputFileName(read);
                IOFile.writeFile_1(fname, rlist);
            }

        }

    }

    public static void splcramerDriver(Scanner read){

        double[][] m;
        String file;

        
        int opt = IOKeyboard.InputOption(read);
        
        if(opt == 1){
            m = IOKeyboard.readMatrixSPL(read);            
        }
            
        else{
            file = IOFile.InputFileName(read);
            int row = IOFile.RowCounter(file);
            int col = IOFile.ColCounter(file);

            m = IOFile.readFile(file, row, col); 
        }

        solveCramer(m, read);


    }


    public static void main(String[] args){

        Scanner read = new Scanner(System.in);

        splcramerDriver(read);

    }

    
}
