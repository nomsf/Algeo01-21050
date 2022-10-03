import java.util.Scanner;


public class Interpolasipolinom{ 
    
    public static double approach(double[] koefisien,double X)
    {
        int nkoef = koefisien.length; 
        double hasil=0;
        for (int i=0; i<nkoef;i++)
        {
            
            hasil+=Math.pow(X, i) *koefisien[i]; 
            System.out.println(hasil);
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
    public static double[] polynomEq(double[][] matrix) {
        

        int nrow = matrix.length;
        int ncol = matrix.length + 1;
        double[][] matriksPolinom = new double[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (j == (ncol - 1)) {
                    matriksPolinom[i][j] = matrix[i][1];
                } else if (j == 0) {
                    matriksPolinom[i][0] = 1.00;
                } else {
                    matriksPolinom[i][j] = Math.pow(matrix[i][0], j);
                }
            }

        }
        
        double[][] newMatrix = gaussjordan.gaussel(matriksPolinom);
        
        double[] koefisien = new double[nrow];
        for (int i = 0; i < nrow; i++) {
            koefisien[i] = newMatrix[i][ncol - 1];
        }

        
        boolean nul = true;
        System.out.print("Persamaan polinomial yang dihasilkan\ny =");
        for (int i = koefisien.length - 1; i >= 0; i--) {
            if (koefisien[i]!=0) {
                if (nul)
                    nul = false;
                else if (koefisien[i] > 0 && !nul)
                    System.out.print(" +");
                    System.out.printf( " %f", koefisien[i]);
                if (i != 0) {
                    System.out.printf(" x^%d", i);
                }
            }
        }
        System.out.println();

        return koefisien;

    }
    
    
    public static String equation (double[] koefisien,double result,double x)
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
        
        
        
        
        reseq+= "\nf("+String.valueOf(x)+")="+String.valueOf(result); 
        return reseq; 
    
    }
    
   
               

    public static void Interpolasipolinomdriver(Scanner read)
    {
        
        int input=IOKeyboard.InputOption(read); 
        
    
        if (input==1) 
        {
            double[][] initmatrixkeyboard = IOKeyboard.readMatrix(read);
            IOKeyboard.printMatrix(initmatrixkeyboard);
            

            System.out.print("masukkan nilai x:");
            
            
            double x =read.nextDouble(); 
            
            double[] koefisien= Interpolasipolinom.polynomEq(initmatrixkeyboard);
            
            double result=approach(koefisien, x);
            System.out.printf("\nf(%.2f)=%.2f\n",x,result);
            int inputYT = IOKeyboard.WritetoFileOption(read);  

            if (inputYT==1)  
            {
                System.out.print("Masukkan nama file: ");
                String namafile = read.next() ;
                String poleq = equation(koefisien, result,x); 
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
            
            double x1 =read.nextDouble(); 

            
            double[] koefisien1= Interpolasipolinom.polynomEq(initmatrixkeyboard1);
            
            double result1=approach(koefisien1, x1);
            System.out.printf("\nf(%f)=%f\n",x1,result1);
            int inputYT1 = IOKeyboard.WritetoFileOption(read);  


            if (inputYT1==1)
            { 
                String namafile1 = read.next() ;
                String poleq1 = Interpolasipolinom.equation(koefisien1,result1,x1); 
                IOFile.writeFile_1(namafile1,poleq1);
                
                
            }
        } 
    }
}

