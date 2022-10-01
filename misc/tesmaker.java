import java.util.*;

public class tesmaker {

    public static void main(String[] args){

        int n = 10;
        double p = 0;
        double[][] m = new double[n][n];

        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                m[i][j] = 1 / (((double) j+1)+p);
            }
            p++;
        }

        Scanner read = new Scanner(System.in);
        

        String filename = IOFile.InputFileName(read);
        IOFile.writeMatrix(filename, m);
    
    }
    
}
