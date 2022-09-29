public class gaussjordan{
    /* Gauss Jordan Elimination */
    public static double[][] gaussel(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        int pivotcol = 0;
        int idx = 0;
        while(pivotcol < col && idx < row){
            if(iscolzero(mat, pivotcol)){
                pivotcol++;
            }
            else{
                if(mat[idx][pivotcol] == 0){
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
                    double pivotelmt = mat[idx][pivotcol];
                    for (int i = pivotcol; i < col; i++){
                        mat[idx][i] = mat[idx][i]/pivotelmt;
                    }
                    int i = idx + 1;
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
                    while (i < row) {
                        if (mat[i][pivotcol] == 0 || (i == idx)) {
                            i++;
                        }
                        else{
                            double factor = mat[i][pivotcol];
                            for (int j = pivotcol; j < col; j++) {
                                mat[i][j] -= factor * mat[idx][j];
                            }
                        }
                        i++;
                    }
                }
                pivotcol++;
            }
            idx++;
        }
        return mat;
    }
    /* Kelompok SPL Solver */
    public static void splsolver(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        if(isnosol(mat) || row > col - 1){
            System.out.println("SPL tidak memiliki solusi");
        }
        else if(row == col - 1 && !manysol(mat)){
            displayStrArr(uniqsolver(mat));
        }
        else{
            displayStrArr(paramsolver(mat));
        }
    }
    public static boolean isnosol(double[][] mat){
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
    public static boolean manysol(double[][] mat){
        boolean foundrow = false;
        int i = 0;
        while(!foundrow && i < mat.length){
            int j = 0;
            int count = 0;
            while(j < mat[i].length){
                if(mat[i][j] == 0){
                    count++;
                }
                j++;
            }
            if(count == mat[i].length){
                foundrow = true;
            }
            i++;
        }
        return foundrow;
    }
    public static String[] uniqsolver(double[][] mat){
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
        int i, idx;
        boolean cek = true;
        idx = -1;
        for (i = 0; i < mat.length; i++) {
            if ((mat[i][col] != 0) && (mat[i][col] != 1))
                return -1;
            if (mat[i][col] == 1) {
                if (cek) {
                    idx = i;
                    cek = false;
                } 
                else {
                    return -1;
                }
            }
        }
        return idx;
    }
    public static String[] paramsolver(double[][] mat){
        int col = mat[0].length;
        String[] result = new String[col - 1];
        int[] paramidx = new int[col - 1];
        int count = 0;
        for (int i = 0; i < col - 1; i++) {
            if (idxlo(mat, i) == -1) {
              paramidx[count] = i;
              count++;
            }
        }
        for (int i = 0; i < col - 1; i++) {
            String res = "";
            if (idxlo(mat, i) != -1) {
                res += ("x" + (i + 1) + " = " + mat[idxlo(mat, i)][col - 1]);
                for (int j = i + 1; j < col - 1; j++) {
                    if (mat[idxlo(mat, i)][j] != 0) {
                        res += (" + (" + (-1 * mat[idxlo(mat, i)][j]) + ")" + makeparam(j, paramidx, count));
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
    /* Kelompok primitif tambahan */
    public static boolean iscolzero(double[][] mat, int col){
        boolean colzero = true;
        int i = 0;
        while (colzero && i < mat.length){
            if (mat[i][col] != 0){
                colzero = false;
            }
            i++;
        }
        return colzero;
    }
    public static void displayStrArr(String[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}