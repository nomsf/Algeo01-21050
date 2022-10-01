import java.util.Scanner;


public class DeterminantReduction {


    static int correctionPos(double[][] m){
        // tuker tuker baris biar valid pas dimasukin ke determinanUt
        // juga menghasilkan konstanta p untuk nentuin pos/neg determinant nya
        int n = MatrixOp.getRow(m);
        int zero = 0;
        int p = 1;
        boolean swap;
        double[] temp = new double[n];

        for (int i = 0; i < n; i++){
            zero = 0;
            swap = false;
            for (int j = 0; j < n; j++){
                if(m[i][j] == 0){
                    zero++;
                    swap = true;
                }
                else{
                    break;
                }
            }
            if (swap == true && i != zero){
                temp = m[zero];
                m[zero] = m[i];
                m[i] = temp;
                p *= -1;
            }
        }

        if (m[0][0] == 0){
            p *= correctionPos(m);
        }
    return p;
    }


    static double determinanUt(double[][] m){
        // hitung determinan dengan metode upper triangle.
        // ERROR : Kalau mat[0][0] / mat[1][1] = 0 hasilnya salah.
        double det = 1;
        int i,j,k;
        double ratio;
        double[][] mcopy = MatrixOp.copyMatrix(m);
        
        if (MatrixOp.isRowZero(m) || MatrixOp.isColZero(m)){
            return 0;
        }
        else{

            for (i = 0; i < MatrixOp.getRow(m); i++){
                for (j = 0; j < MatrixOp.getRow(m); j++){
                    mcopy[i][j] =  m[i][j];
                }
            }

            int p = correctionPos(mcopy);
            IOKeyboard.printMatrix(mcopy);
    
            for (i = 0; i < MatrixOp.getRow(m); i++){
                for (j = i+1; j < MatrixOp.getRow(m); j++){
                    ratio = mcopy[j][i] / mcopy[i][i];
    
                    for (k = 0; k < MatrixOp.getRow(m); k++){
                        mcopy[j][k] = mcopy[j][k] - (ratio * mcopy[i][k]);
                    }
                }
            }
            for (i = 0; i < MatrixOp.getRow(m); i++){
                det = det * mcopy[i][i];
            }

            IOKeyboard.printMatrix(mcopy);

        return p * det;
        }

    }

    public static void reductionDriver(Scanner read){

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

        if(MatrixOp.isSquare(m)){
            double det = determinanUt(m);
            String result = "Determinan Matriks adalah " + (String.valueOf(det));

            System.out.println("-----  Hasil Perhitungan Determinan  -----");
            System.out.println(result);
            
            opt = IOKeyboard.WritetoFileOption(read);

            if(opt == 1){
                String filename = IOFile.InputFileName(read);

                IOFile.writeFile_1(filename, result);
            }
            else {
                System.out.println("-----  Matriks Tidak Berbentuk Square  -----");
            }
        }

            
    }




        public static void main(String[] args) {

            Scanner read = new Scanner(System.in);

            reductionDriver(read);
     }
}