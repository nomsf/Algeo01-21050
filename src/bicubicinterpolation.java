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
    public static double[] bicubiceq(double[][] startmatrix)
    {
        int x=-1;
        int y=-1;
        
       
        int s = 0 ;
        int z=0;  
        double[][] newmatrix = new double[16][16];
        int count =0; 
        for(int j=0;j<4;j++)
        {
            for( int i=0;i<4;i++)
            {
                double a= x^i; 
                double b= y^j; 
                newmatrix[i][j]= a * b;
                count +=1; 
                if (count%16==0) {
                    y+=1; 
                }
                if (count%64==0 ){ 
                    x+=1 ;
                    y=-1; 
                }
            }
        }
            
        
        IOKeyboard.printMatrix(newmatrix);
        double[][] matrixinverse= InverseGauss.inverseGauss(newmatrix);
        double[][] matrixvalue = new double[16][1]; 
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)  {
                matrixvalue[s][1] = startmatrix[i][j];
                s++; 
            }
        }
        double[][] matrixkoef = MatrixOp.matrixTimes(matrixinverse, matrixvalue);
        boolean nol=true;
        for(int xt=0 ;xt<4; xt++ )
        {
            for (int yt = 0;yt<4; yt++ )
            {
                while (nol && z<16)
                { 
                    if (matrixkoef[z][0]==0) 
                    {
                        nol=false; 
                        System.out.print(""); 
                    }
                    else if (matrixkoef[z][0]>0 )
                    {
                        if (xt==0 && yt==0)
                        {
                            System.out.printf(String.valueOf(matrixkoef[z][0])); 
                        }
                        else 
                        { 
                            System.out.printf("+"+String.valueOf(matrixkoef[z][0])) ; 
                        } 
                    }
                    else
                    { 
                        System.out.printf("-"+String.valueOf(matrixkoef[z][0])); 
                    }
                    if (matrixkoef[z][0]!=0) 
                    {
                        System.out.printf("X^"+String.valueOf(xt)+"Y^"+ String.valueOf(yt)) ; 
                    }

                    z++ ; 
                } 
            }
        } 
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
            double[][] initmatrixkeyboard2 = new double[initmatrixkeyboard1.length-1][initmatrixkeyboard1[0].length]; 
            double x0 =initmatrixkeyboard1[initmatrixkeyboard1.length-1][0]; 
            double x01 =initmatrixkeyboard1[initmatrixkeyboard1.length-1][1]; 
            for (int i=0;i<initmatrixkeyboard1.length-1;i++) 
            {
                for (int j=0 ; j<initmatrixkeyboard1[0].length;j++)
                {
                    initmatrixkeyboard2[i][j]=initmatrixkeyboard1[i][j]; 
                        
                }
            }
            double[] koefisien1=bicubiceq(initmatrixkeyboard2);
            double result1=estimateres(koefisien1, x0 ,x01);
                
            int inputYT1 = IOKeyboard.WritetoFileOption(read);  
 
            if (inputYT1==1 ) { 
                String namafile1 = read.next() ;
                String bicubiceq1 = "f("+String.valueOf(x0)+","+String.valueOf(x01) +")= " + String.valueOf(result1); 
                IOFile.writeFile_1(namafile1,bicubiceq1);
            }
        } 
    
    }
}     