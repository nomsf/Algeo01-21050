import java.util.Scanner;


public class SplBalikan {
    
    public static void splInverse(double[][] m, Scanner read){
        int mrow = MatrixOp.getRow(m);
        int mcol = MatrixOp.getCol(m);

        int k = 0, l = 0;


        double[][] mA = new double[mrow][mcol-1];
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

        // IOKeyboard.printMatrix(mA);
        // IOKeyboard.printMatrix(mB);


        if(!MatrixOp.isSquare(mA)){
            System.out.println("-----  Matriks A Tidak Berbentuk Square!  -----");
        }

        else if (Cofactor.determinantKofaktor(mA) == 0){
            System.out.println("-----  Matriks Tidak Mempunyai Balikan!  -----");
        }

        else{
            double[][] minv = InverseGauss.inverseGauss(mA);
            
            // IOKeyboard.printMatrix(minv);

            double[][] result = MatrixOp.matrixTimes(minv, mB);
            String r = "";
            String[] listr = new String[MatrixOp.getRow(result)];

            System.out.println("-----  Hasil Perhitungan  -----");
            for(int i = 0; i < MatrixOp.getRow(result); i++){
                r = "X" +(String.valueOf(i+1)) + " = " + (String.valueOf(result[i][0] + "   "));
                listr[i] = r;
                System.out.println(r);
                
            }
            

            int opt = IOKeyboard.WritetoFileOption(read);

            if(opt == 1){
                String fname = IOFile.InputFileName(read);
                IOFile.writeList(fname, listr);
            }

        }
    }

    public static void splbalikanDriver(Scanner read){
        
    
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
        splInverse(m, read);


    }


}
