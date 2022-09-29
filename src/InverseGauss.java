public class InverseGauss {
    

    static double[][] createIdentity(int row, int col){
        
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


    static double[][] addIdentity(double[][] m){
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

    static void gaussJordan(double[][] m){

        m = gaussjordan.gaussel(m);
    }

    static double[][] inverseGauss(double[][] m){
        int k = 0, l = 0;
        double[][] mr = addIdentity(m);
        gaussJordan(mr);

        double[][] minv = new double[MatrixOp.getRow(m)][MatrixOp.getCol(m)];
        
        for (int i = 0; i < MatrixOp.getRow(mr); i++){
            for(int j = 0; j < MatrixOp.getCol(mr); j++){

                if(i >= MatrixOp.getRow(m)){
                    // minv[k][l] ;
                }

            }
        }
        
    
    }

    public static void main(String[] args){
        
        double[][] m = MatrixOp.readMatrix();

        MatrixOp.printMatrix(m);

        System.out.println();

        double[][] mr = addIdentity(m);

        MatrixOp.printMatrix(mr);

        System.out.println();

        gaussJordan(mr);

        MatrixOp.printMatrix(mr);
    }
}