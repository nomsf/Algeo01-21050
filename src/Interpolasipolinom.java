import java.util.Scanner;


public class Interpolasipolinom{ 
    
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
    public static double[][] matrixinterpol(double[][] firstmatrix)
    {
        int nrows = firstmatrix.length;
        int ncolumns = firstmatrix.length + 1; 
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
                    matrixinterpolasi[i][j] = firstmatrix[i][1]; 
                }
                else 
                { 
                    matrixinterpolasi[i][j]=Math.pow(firstmatrix[i][0], j);
                }
            }
        }
        return matrixinterpolasi;
    }
    public static double[] polynomEq(double[][] initmatrix) 
    {
        double[][] matrixinterpolasi=Interpolasipolinom.matrixinterpol(initmatrix); 
        int nrows=matrixinterpolasi.length;
        int ncolumns=matrixinterpolasi[0].length; 
        double[][] matrixpolynom = gaussjordan.gaussel(matrixinterpolasi); 
        double[] koefisien = new double[nrows];
        for (int i=0;i<nrows;i++)
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
    
    
    public static String equation (double[] koefisien,double result)
    {
        String reseq ="Persamaan polinomial yang dihasilkan adalah: \nf(x)=  ";
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
                    if (z==koefisien.length-1)
                    { 
                        reseq+=String.valueOf(koefisien[z]);
                    }  
                    else 
                    {
                        reseq+= " + "+String.valueOf(koefisien[z]);  
                    }
                }
                else
                {
                    reseq+= " - "+String.valueOf(koefisien[z]);      
                }
                if (z!=0) 
                {
                    reseq+="X^"+String.valueOf(z) ; 
                }
            } 
        }
        Scanner read = new Scanner(System.in); 
        read.close();
        int X = read.nextInt(); 
        
        reseq+= "\nf("+String.valueOf(X)+")="+String.valueOf(result); 
        return reseq; 
    
    }
        


    public static void Interpolasipolinomdriver(Scanner read)
    {
        
        int input=IOKeyboard.InputOption(read); 
        
    
        if (input==1) 
        {
            double[][] initmatrixkeyboard = IOKeyboard.readMatrix(read); 
            System.out.println("masukkan nilai x:");
            double x=0;
            boolean ada=true; 
            if(ada)
            {
                x=read.nextDouble();
                ada= false; 
            }
            double[] koefisien= polynomEq(initmatrixkeyboard);
            double result=approach(koefisien, x);
            System.out.printf("\nf(%.2f)=%.2f\n",x,result);
            int inputYT = IOKeyboard.WritetoFileOption(read);  

            if (inputYT==1)  
            {
                System.out.print("Masukkan nama file: ");
                String namafile = read.next() ;
                String poleq = equation(koefisien, result); 
                IOFile.writeFile_1(namafile,poleq);
            }
        }
        if(input==2)
        { 
            String fileName = IOFile.InputFileName(read);
            int row = IOFile.RowCounter(fileName);
            int col = IOFile.ColCounter(fileName);
        
            double[][] initmatrixkeyboard1 = IOFile.readFile(fileName,row,col); 
            IOKeyboard.printMatrix(initmatrixkeyboard1);
            double[][] initmatrixkeyboard2 = new double[initmatrixkeyboard1.length-1][initmatrixkeyboard1[0].length]; 
            double x1 =initmatrixkeyboard1[initmatrixkeyboard1.length-1][0]; 
            for (int i=0;i<initmatrixkeyboard1.length-1;i++ )
            {
                for (int j=0 ; j<initmatrixkeyboard1[0].length;j++)
                {
                    initmatrixkeyboard2[i][j]=initmatrixkeyboard1[i][j]; 
                        
                }
            }
            double[] koefisien1= polynomEq(initmatrixkeyboard2);
            double result1=approach(koefisien1, x1);
            int inputYT1 = IOKeyboard.WritetoFileOption(read);  


            if (inputYT1==1)
            { 
                String namafile1 = read.next() ;
                String poleq1 = equation(koefisien1, result1); 
                IOFile.writeFile_1(namafile1,poleq1);
            }
        } 
    }
}

