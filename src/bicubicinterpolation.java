import java.util.Scanner;

public class bicubicinterpolation 
{
    public static double estimateres (double[] koefisien, Double a , Double b)
    { 
     
        double result = 0;
        int k =0 ;
        for(int i=0 ; i<4 ; i ++)
        { 
            for (int j=0 ; j<4 ;j++ )
            { 
                result += ( Math.pow(a, i) ) * (Math.pow(b, j)) * (koefisien[k]); 
                k++ ;
            }
        }
        return result; 
    } 



    public static double[][] matriksinit (){ 
        double[][] newmatrix=new double[16][16]; 
        for(int y=-1; y<3 ; y++ )
        {
            for(int x=-1;x<3;x++ )
            { 
                for(int j=0 ;j<4;j++ )
                { 
                    for(int i= 0;i<4; i++ )
                    { 
                        double a = Math.pow(x, i);
                        double b = Math.pow(y, j); 
                        newmatrix[i][j] = a * b; 

                    }
                }
            }
        }
        return newmatrix; 
    }
    public static double[] bicubiceq(double[][] startmatrix)
    {
        
        
       
        int s = 0 ;
        
        
        double[][] newmatrix= bicubicinterpolation.matriksinit() ; 
        

        double[][] matrixinverse= InverseGauss.inverseGauss(newmatrix);
        double[][] matrixvalue = new double[16][1]; 
        for(int j=0;j<=3;j++)
        {
            for(int i=0;i<=3;i++)  {
                matrixvalue[s][0] = startmatrix[j][i];
                s++; 
            }
        }
        double[][] matrixkoef = MatrixOp.matrixTimes(matrixinverse, matrixvalue);
        
        double[] matrixbicubic = new double[matrixkoef.length]; 
        for (int m=0 ; m< matrixkoef.length;m++ )
        { 
            matrixbicubic[m] = matrixkoef[m][0]; 
        }
        return matrixbicubic; 
    }
    
    public static void bicubicinterpolationdriver (Scanner read)
    { 
        int input=IOKeyboard.InputOption(read); 
        
        if(input==1) {
            double[][] initmatrixkeyboard = IOKeyboard.readMatrix(read); 
            Double x1 = read.nextDouble(); 
            Double x2 = read.nextDouble(); 
            double[] matrikskoef = bicubiceq(initmatrixkeyboard); 
            Double hasilbicubic=estimateres(matrikskoef, x1, x2); 
            System.out.print("\n");
            int inputYT=IOKeyboard.WritetoFileOption(read); 

            if (inputYT==1)
            {
 
                System.out.print("Masukkan nama file : "); 
                String namafile = read.next(); 
                String bicubiceq = "f("+String.valueOf(x1)+","+String.valueOf(x2)+")= " + String.valueOf(hasilbicubic); 
                IOFile.writeFile_1(namafile, bicubiceq);
            }
        } 
        else if(input== 2) 
        {
            String fileName = IOFile.InputFileName(read);
            int row = IOFile.RowCounter(fileName);
            int col = IOFile.ColCounter(fileName);
        
            double[][] initmatrixkeyboard1 = IOFile.readFile(fileName,row,col);
            IOKeyboard.printMatrix(initmatrixkeyboard1);
            
            System.out.print("x0 = ");
            double x0 =read.nextDouble();
            System.out.print("x01 = ");
            double x01 =read.nextDouble(); 
            
            double[] koefisien1=bicubiceq(initmatrixkeyboard1);
            double result1=estimateres(koefisien1, x0 ,x01);
                
            int inputYT1 = IOKeyboard.WritetoFileOption(read);  
 
            if (inputYT1==1 ) { 
                String namafile1 = read.next() ;
                String bicubiceq1 = "f("+String.valueOf(x0)+","+String.valueOf(x01) +")= " + String.valueOf(result1); 
                IOFile.writeFile_1(namafile1,bicubiceq1);
                String bicubiceq = "f("+String.valueOf(x0)+","+String.valueOf(x01)+")= " + String.valueOf(result1); 
                System.out.println(bicubiceq);
            }
        } 
    
    }
}     