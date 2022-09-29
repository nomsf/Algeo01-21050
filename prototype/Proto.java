// ini file buat ngetes algoritma nya aja


public class Proto {

    // static boolean isCorrect(double[][] m){
    //     // apakah bentuknya udah bener
    //     int zCount = 0;
    //     boolean corr = true;

    //     for (int i = 0; i < getRow(m); i++){
    //         zCount = 0;
    //         for (int j = 0; j < getRow(m); j++){
    //             if (m[i][j] == 0){
    //                 zCount ++;
    //             }
                
    //             if (zCount >= i){
    //                 corr = false;
    //             }

    //         }
    //     }
    // }

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

    static boolean isRowZero(double[][] mat){
        // cek apakah ada baris yang seluruhnya 0
        boolean zeroRow = false;

        for (int i = 0; i < MatrixOp.getRow(mat); i++){
            for (int j = 0; j < MatrixOp.getRow(mat); j++){
                if (mat[i][j] != 0){
                    break;
                }
                else if (mat[i][j] == 0 && j == MatrixOp.getRow(mat)-1){
                    zeroRow = true;
                    break;
                }
            }
        }
        return zeroRow;
    }

    static boolean isColZero(double[][] mat){
        // cek apakah ada kolom yang seluruhnya 0
        boolean zeroCol = false;

        for (int i = 0; i < MatrixOp.getRow(mat); i++){
            for (int j = 0; j < MatrixOp.getRow(mat); j++){
                if (mat[j][i] != 0){
                    break;
                }
                else if (mat[j][i] == 0 && j == MatrixOp.getRow(mat)-1){
                    zeroCol = true;
                    break;
                }
            }
        }
        return zeroCol;
    }



    static double determinanUt(double[][] m){
        // hitung determinan dengan metode upper triangle.
        // ERROR : Kalau mat[0][0] / mat[1][1] = 0 hasilnya salah.
        double det = 1;
        int i,j,k;
        double ratio;
        double[][] mcopy = MatrixOp.copyMatrix(m);
        
        if (isRowZero(m) || isColZero(m)){
            return 0;
        }
        else{

            for (i = 0; i < MatrixOp.getRow(m); i++){
                for (j = 0; j < MatrixOp.getRow(m); j++){
                    mcopy[i][j] =  m[i][j];
                }
            }

            int p = correctionPos(mcopy);
            MatrixOp.printMatrix(mcopy);
    
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

            MatrixOp.printMatrix(mcopy);

        return p * det;
        }

    }




        public static void main(String[] args) {

            double[][] mat = MatrixOp.readMatrix();
            
            MatrixOp.printMatrix(mat); 
            

            System.out.println(determinanUt(mat));
    }
}