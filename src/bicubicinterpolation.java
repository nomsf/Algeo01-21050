public class bicubicinterpolation 
{
    public static double estimateteres (double[] koefisien, int a , int b)
    { 
     
        int result = 0;
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
        int i=0;
        int j=0;
        int s = 0 ;
        int z=0;  
        double[][] newmatrix = new double[16][16];
        for(;y<3;y++)  
        {  
            for (;x<3;x++)
            {
                for(;i<4;i++)
                {
                    for(;j<4;j++)
                    {
                        newmatrix[i][j]= (x^i) * (y^j);
                    }
                }
            }
        } 
        double[][] matrixinverse= InverseAdjoin.inverseAdjoin(newmatrix);
        double[][] matrixvalue = new double[16][1]; 
        for(;i<4;i++)
                {
                    for(;j<4;j++)
                    {
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
                while (nol)
                { 
                    if (matrixkoef[z][1]==0) 
                    {
                        nol=false; 
                    }
                    else if (matrixkoef[z][1]>0 )
                    {
                        System.out.printf("+ %f",matrixkoef[z][1]); 
                    }
                    else
                    { 
                        System.out.printf("- %f",matrixkoef[z][1]); 
                    }
                    if (z!=0) 
                    {
                        System.out.printf("X^%d",z); 
                    }

                    z++ ; 
                } 
            }
        } 
        double[] matrixbicubic = new double[matrixkoef.length]; 
        for (int m=0 ; m< matrixkoef.length;m++ )
        { 
            matrixbicubic[m] = matrixkoef[m][1]; 
        }
        return matrixbicubic; 
    }   
} 