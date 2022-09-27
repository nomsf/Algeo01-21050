

public class Interpolasipolinom extends Proto{ 
    public static double approach(double[] koefisien,double X)
    {
        int nkoef = koefisien.length; 
        int hasil = 0;  
        for (int i=0; i<nkoef;i++)
        {
            hasil+=Math.pow(X, i) *koefisien[i]; 
        }
        return hasil;
    }
    public static double[] polynomEq(double[][] initmatrix) 
    {
        int nrows = initmatrix.length;
        int ncolumns = initmatrix.length + 1; 
        int i,j;
        double[][] matrixinterpolasi = new double[nrows][ncolumns]; 
        for (i=0;i<nrows;i++) 
        {
            for (j=0;j<ncolumns;j++) 
            { 
                if (j==0) 
                {
                    matrixinterpolasi[i][0]=1; 
                }
                else if (j==ncolumns-1) 
                { 
                    matrixinterpolasi[i][j] = initmatrix[i][1]; 
                }
                else 
                { 
                    matrixinterpolasi[i][j]=Math.pow(initmatrix[i][0], j);
                }
            }
        }
        double[][] matrixpolynom = gaussjordan.gaussel(matrixinterpolasi); 
        double[] koefisien = new double[nrows];
        for (i=0;i<nrows;i++)
        { 
            koefisien[i]=matrixpolynom[i][ncolumns-1]; 
        }
        System.out.print("Persamaan polinomial yang dihasilkan adalah: \ny=  ");
        boolean nol=true;
        for (int z = koefisien.length-1;z>=0; z--)
        {
            while (nol)
            { 
                if (koefisien[z]==0) 
                {
                    nol=false; 
                }
                else if (koefisien[z]>0 )
                {
                    System.out.printf("+ %f",koefisien[z]); 
                }
                else
                { 
                    System.out.printf("- %f",koefisien[z]); 
                }
                if (z!=0) 
                {
                    System.out.printf("X^%d",z); 
                }
            } 
        }
        return koefisien; 
    }
}