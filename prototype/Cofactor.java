public class Cofactor extends Proto{

    static double[][] minorEntry(double[][] m, int a, int b){
        double[][] me = new double[getRow(m)-1][getRow(m)-1];
        int x = 0, y = 0;

        for(int i = 0; i < getRow(m); i++){
            x++;
            y = 0;
            for(int j = 0; j < getRow(m); j++){
                if( i != a && j != b){

                    me[x][y] = m[i][j];
                    y++;
                }
                if(y == getRow(me)-1){
                    x++;
                    y = 0;
                }
                    
            }
        }

        System.out.println("minor entry:");
        printMatrix(me);
        return me;
    } 

    static double determinantKofaktor(double[][] m){
        double det = 0;
        int p = 1;
        double[][] me;

        // kofaktor adalah determinan dari elemen kecuali yang di kofaktorin.

        if (isRowZero(m) || isColZero(m)){
            return 0;
        }
        else if(m.length == 1){
            return m[0][0];
        }
        else {
            for(int i = 0; i < getRow(m); i++){
                me = minorEntry(m, 0, i);
                det += m[0][i] * p * determinantKofaktor(me); // warning
                p *= -1;
            }
        }

        return det;
    }

    public static void main(String[] args) {
        double[][] m;
        
        m = readMatrix();

        printMatrix(m);

        System.out.println(determinantKofaktor(m));
    }
}
