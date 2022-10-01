import java.util.Scanner;

public class InverseAdjoin {

    public static double[][] inverseAdjoin(double[][] m){
        // menghasilkan inverse dari matriks dengan metode adjoin
        double[][] ma;

        ma = Cofactor.matrixCofactor(m);

        MatrixOp.transpose(ma); // menghasilkan adjoin dengan transpose matriks kofaktor

        MatrixOp.timesVal(ma, (1/Cofactor.determinantKofaktor(m))); // mengkalikan dengan 1/det(m)

        return ma;
    }

    public static void adjoinDriver(Scanner read){

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

        if(Cofactor.determinantKofaktor(m) != 0){
            double[][] minv = inverseAdjoin(m);
        

            System.out.println("-----  Hasil Inverse Determinan  -----");
            System.out.println("");
            IOKeyboard.printMatrix(minv);
            
            opt = IOKeyboard.WritetoFileOption(read);

            if(opt == 1){
                String filename = IOFile.InputFileName(read);

                IOFile.writeMatrix(filename, minv);
            }
        }
        else{
            System.out.println("-----  Matriks Tidak Punya Inverse  -----");
        }

    }

    
}
