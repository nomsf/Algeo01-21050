import java.util.Scanner;

public class InverseGauss {
    

    public static double[][] createIdentity(int row, int col){
        
        double[][] mi = new double[row][col];

        for (int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(i == j){
                    mi[i][j] = 1;
                }
                else{
                    mi[i][j] = 0;
                }
            }
        }

        return mi;
    }


    public static double[][] addIdentity(double[][] m){
        int k = 0, l = 0;
        double[][] mc = new double[MatrixOp.getRow(m)][MatrixOp.getCol(m) * 2];
        double[][] mi = createIdentity(MatrixOp.getRow(m), MatrixOp.getCol(m));


        for (int i = 0; i < MatrixOp.getRow(mc); i++){
            for(int j = 0; j < MatrixOp.getCol(mc); j++){

                if(j < MatrixOp.getCol(m)){
                    if(j == 0){                        // buat akses tiap col m dr nol sementara mc nya jalan terus
                        l = 0;
                    }

                    mc[i][j] = m[k][l];
                    l++;
                }
                else{
                    if(j == MatrixOp.getCol(m)){
                        l = 0;
                    }
                    
                    mc[i][j] = mi[k][l];
                    l++;
                }
            }
            k++;
        }
        return mc;
    }

    public static void gaussJordan(double[][] m){

        m = gaussjordan.gaussel(m);
    }

    public static double[][] inverseGauss(double[][] m){
        int k = 0, l = 0;
        double[][] mr = addIdentity(m);
        gaussJordan(mr);

        double[][] minv = new double[MatrixOp.getRow(m)][MatrixOp.getCol(m)];
        
        for (int i = 0; i < MatrixOp.getRow(mr); i++){
            for(int j = 0; j < MatrixOp.getCol(mr); j++){
                if(j >= MatrixOp.getCol(m)){
                    if(j == MatrixOp.getCol(m)){
                        l = 0;
                    }
                    minv[k][l] = mr[i][j];
                    l++;
                }

            }
            k++;
        }
        
        return minv;
    }

    public static void inverseGaussDriver(Scanner read){

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
            double[][] minv = inverseGauss(m);
        

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

    public static void main(String[] args){
        
        Scanner read = new Scanner(System.in);

        inverseGaussDriver(read);
    }
}
