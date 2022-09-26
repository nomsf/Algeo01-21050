public class gauss extends Proto{
   /* public static double[][] readMatrix(){
        Scanner read = new Scanner(System.in); // buat obj read dari Scanner class
        System.out.print("Masukan baris matriks: ");
        int row = read.nextInt();  // nextInt = method dr Scanner buat ngebaca integer
        System.out.print("Masukan kolom matriks: ");
        int col = read.nextInt();
        double[][] mat = new double[row][col]; // assign ukuran array
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                mat[i][j] = read.nextDouble();
            }
        }
        read.close();
        return mat;
    }*/
    /* Gauss Elimination */
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
        /*else{
            displayStrArr(paramsolver(mat));
        }*/
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
        boolean found = false;
        int idx = -1;
        int i = 0;
        while(i < mat.length && !found){
            if(mat[i][col] == 1){
                idx = i;
                found = true;
            }
            i++;
        }
        return idx;
    }
    public static String[] paramsolver(double[][] mat){
        String[] result = new String[mat[0].length - 1];
        int row = mat.length;
        int col = mat[0].length;
        char param = 'a';
        int count = 0;
        for (int i = 0; i < col - 1; i++) {
            String res = "";
            if (idxlo(mat, i) != -1) {
                res += ("x" + (i + 1) + " = " + mat[idxlo(mat, i)][col - 1]);
                for (int j = (i + 1); j < col - 1; j++) {
                    if (mat[idxlo(mat, i)][j] != 0) {
                        res += (" + (" + (-1 * mat[idxlo(mat, i)][j]) + ")" + (char)(((int) param) + count));
                        count++;
                    }
                }
            } else {
                res = "x" + (i + 1) + " = " + (char)(((int) param) + count);
                count++;
            }
            result[i] = res;
          }
          return result;
    }
    /*public static String[] paramsolver(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        char param = 'a';
        int count = 0;
        char[] solx = new char[col];
        for(int i = 0; i < col; i++){
            solx[i] = '.';
        }
        for(int i = 0; i < row; i++){
            boolean haslo = false;
            int j = 0;
            while(!haslo && j < col){
                if(mat[i][j] == 1){
                    haslo = true;
                }
                j++;
            }
            if(!haslo){
                solx[j - 1] = (char)(((int) param) + count);
                count++;
            }
            System.out.println(solx[j - 1]);
        }
        String[] ans = new String[col - 1];
        double[] res = new double[col - 1];
        String[] resstr = new String[col];
        for(int i = 0; i < col; i++){
            resstr[i] = ".";
        }
        for (int i = row - 1; i >= 0; i--) {
            res[i] = mat[i][col - 1];
            boolean isparam = false;
            for (int j = (col - 2); j > i; j--) {
                if(solx[j] == '.'){
                    res[i] -= mat[i][j] * res[j];
                }
                else{
                    isparam = true;
                    resstr[i] += mat[i][j] + solx[j] + "+";
                }
            }
            System.out.println(resstr[i]);
            System.out.println(isparam);
            if(isparam){
                ans[i] = "x" + (i + 1) + " = " + resstr[i];
            }
            else{
                ans[i] = "x" + (i + 1) + " = " + res[i];
            }
        }
        return ans;

        boolean found = false;
        int idx = -1;
        int i = 0;
        while(i < mat.length && !found){
            if(mat[i][col] == 1){
                idx = i;
                found = true;
            }
            i++;
        }
        return idx;
      }*/
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
    /*public static void displayMat(double[][] mat){
        for (int i = 0; i < mat.length; i++) {
          for (int j = 0; j < mat[i].length; j++){
            System.out.print(mat[i][j] + " ");
          }
          System.out.println();
        }
    }*/
    public static void displayStrArr(String[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void main(String[] args) {
        double[][] mat;
        mat = gaussel(readMatrix());
        printMatrix(mat);
        splsolver(mat);
    }
    /*public static double[][] multiplyrow (double[][] mat, int row, int x){
        for(int i = 0; i < mat[row].length; i++){
            mat[row][i] *= x;
        }
        return mat;
    }*/
}