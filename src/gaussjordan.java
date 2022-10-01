import java.util.Scanner;

public class gaussjordan{
    /* Gauss Jordan Elimination */
    public static double[][] gaussel(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        int pivotcol = 0;
        int idx = 0;
        while(pivotcol < col && idx < row){
            // pivotcol adalah indeks kolom yang sedang diproses
            // idx indeks baris yang sedang diproses
            if(MatrixOp.isColZero2(mat, pivotcol)){
                pivotcol++; // jika kolom 0 semua, skip kolom
            }
            else{
                if(mat[idx][pivotcol] == 0){
                    // jika elemen yang sedang diperiksa = 0, 
                    // pertukarkan baris dengan baris yang elemennya 
                    // (pada kolom yang sama) bernilai tidak 0
                    int i = idx + 1;
                    boolean foundtoswap = false;
                    while (i < row && !foundtoswap){
                        if (mat[i][pivotcol] != 0){
                            foundtoswap = true;
                            for (int j = 0; j < col; j++){
                                double temp = mat[idx][j];
                                mat[idx][j] = mat[i][j];
                                mat[i][j] = temp;
                            }
                        }
                        i++;
                    }
                }
                else{
                    // jika elemen yang sedang diperiksa != 0,
                    // bagi semua elemen pada baris yang sama dengan elemen
                    // yang sedang diperiksa, agar terbentuk leading 1
                    double pivotelmt = mat[idx][pivotcol];
                    for (int i = pivotcol; i < col; i++){
                        mat[idx][i] = mat[idx][i]/pivotelmt;
                    }
                    int i = idx + 1;
                    // cek elemen-elemen di bawah (sekolom) leading 1 yang 
                    // sudah terbentuk, jika nilainya != 0, 
                    // kurangi dengan perkalian baris yang sudah leading 1 agar elemen menjadi 0
                    while (i < row){
                        if (mat[i][pivotcol] == 0){
                            i++;
                        }
                        else{
                            double factor = mat[i][pivotcol];
                            for (int j = pivotcol; j < col; j++){
                                mat[i][j] -= (factor * mat[idx][j]);
                            }
                            i++;
                        }
                    }
                    i = 0;
                    // cek elemen-elemen di atas (sekolom) leading 1 yang 
                    // sudah terbentuk, jika nilainya != 0, 
                    // kurangi dengan perkalian baris yang sudah leading 1 agar elemen menjadi 0
                    while (i < row) {
                        if (mat[i][pivotcol] == 0 || (i == idx)) {
                            i++;
                        }
                        else{
                            double factor = mat[i][pivotcol];
                            for (int j = pivotcol; j < col; j++) {
                                mat[i][j] -= factor * mat[idx][j];
                            }
                            i++;
                        }
                    }
                }
                pivotcol++;
            }
            idx++;
        }
        return mat;
    }
    /* Kelompok SPL Solver */
    public static String[] splsolver(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        if(isnosol(mat)){
            String[] res = {"SPL tidak memiliki solusi"};
            System.out.println("SPL tidak memiliki solusi");
            return res;
        }
        else{
            mat = cutmat(mat);
            row = mat.length;
            col = mat[0].length;
            if(row == col - 1){
                String[] res = uniqsolver(mat);
                MatrixOp.displayStrArr(res);
                return res;
            }
            else if(row < (col - 1)){
                String[] res = paramsolver(mat);
                MatrixOp.displayStrArr(res);
                return res;
            }
            else{
                String[] res = {"SPL tidak memiliki solusi"};
                System.out.println("SPL tidak memiliki solusi");
                return res; 
            }
        }
    }
    public static boolean isnosol(double[][] mat){
        // true jika terdapat baris yang seluruhnya 0 kecuali elemen terakhir
        boolean foundrow = false;
        int i = 0;
        while(!foundrow && i < mat.length){
            int j = 0;
            int count = 0;
            while(j < mat[i].length - 1){
                if(mat[i][j] == 0){
                    count++;
                }
                j++;
            }
            if(count == mat[i].length - 1 && mat[i][mat[i].length - 1] != 0){
                foundrow = true;
            }
            i++;
        }
        return foundrow;
    }
    public static double[][] cutmat(double[][] mat){
        int countrowzero = 0;
        for(int i = 0; i < mat.length; i++) {
            int countzero = 0;
            for(int j = 0; j < mat[i].length; j++){
                if(mat[i][j] == 0) countzero++;
            }
            if(countzero == mat[i].length) countrowzero++;
        }
        if(countrowzero != 0) {
            int row = mat.length - countrowzero;
            int col = mat[0].length;
            double[][] tempmat = new double[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    tempmat[i][j] = mat[i][j];
                }
            }
            mat = tempmat;
        }
        return mat;
    }
    public static String[] uniqsolver(double[][] mat){
        // menyelesaikan SPL solusi unik
        int row = mat.length;
        int col = mat[0].length;
        String[] ans = new String[col - 1];
        double[] res = new double[col - 1];
        for (int i = row - 1; i >= 0; i--) {
            res[i] = mat[i][col - 1];
            for (int j = (col - 2); j > i; j--) {
                res[i] -= mat[i][j] * res[j];
            }
            ans[i] = "x" + (i + 1) + " = " + res[i];
        }
        return ans;
    }
    public static int idxlo(double[][] mat, int col){
        // mengembalikan indeks leading 1 yang terdapat pada sebuah kolom
        int i, idx;
        boolean cek = true;
        idx = -1;
        for (i = 0; i < mat.length; i++) {
            if ((mat[i][col] != 0) && (mat[i][col] != 1)) break;
            if (mat[i][col] == 1) {
                if (cek) {
                    idx = i;
                    cek = false;
                } 
                else {
                    break;
                }
            }
        }
        return idx;
    }
    public static String[] paramsolver(double[][] mat){
        // menyelesaikan SPL solusi parameter
        double[][] matnew = gaussjordan.gaussel(mat);
        int col = matnew[0].length;
        String[] result = new String[col - 1];
        int[] paramidx = new int[col - 1];
        int count = 0;
        for (int i = 0; i < col - 1; i++) {
            if (idxlo(matnew, i) == -1) {
              paramidx[count] = i;
              count++;
            }
        }
        for (int i = 0; i < col - 1; i++) {
            String res = "";
            if (idxlo(matnew, i) != -1) {
                res += ("x" + (i + 1) + " = " + matnew[idxlo(matnew, i)][col - 1]);
                for (int j = i + 1; j < col - 1; j++) {
                    if (matnew[idxlo(matnew, i)][j] != 0) {
                        res += (" + (" + (-1 * matnew[idxlo(matnew, i)][j]) + ")" + makeparam(j, paramidx, count));
                    }
                }
            } 
            else {
                res = "x" + (i + 1) + " = " + makeparam(i, paramidx, count);
            }
            result[i] = res;
          }
          return result;
    }
    
    private static char makeparam(int idx, int[] paramidx, int count){
        // membuat parameter a..b jika indeks kolom membutuhkan parameter (tidak punya leading 1)
        char param = 'a';
        int i = 0;
        boolean diff = true;
        while(i < count && diff){
            if(paramidx[i] == idx){
                param = (char)(((int) param) + i);
                diff = false;
            }
            i++;
        }
        return param;
    }
    /* Driver */
    public static void gaussjordandriver(Scanner read) {
        int opt = IOKeyboard.InputOption(read);
        String res[];
        if(opt == 1){
            double[][] mat = IOKeyboard.readMatrixSPL(read);
            res = splsolver(gaussel(cutmat(mat)));
        }
        else{
            String fileName = IOFile.InputFileName(read);
            int row = IOFile.RowCounter(fileName);
            int col = IOFile.ColCounter(fileName);
            double[][] mat = IOFile.readFile(fileName, row, col);
            res = splsolver(gaussel(cutmat(mat)));
        }
        int optw = IOKeyboard.WritetoFileOption(read);
        if(optw == 1){
            String fileName_write = IOFile.InputFileName(read);
            boolean iswritesc = IOFile.createFile(fileName_write);
            if(iswritesc){
                IOFile.writeSolGauss(fileName_write, res);
            }
        }
    }
}