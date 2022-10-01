import java.util.Scanner;
public class Interpolasipolinom{ 
    public static double[][] scanfile(Scanner read)
    {
        String namefile; 
        namefile=IOFile.InputFileName(read);
        int nR = IOFile.RowCounter(namefile);
        int nC = IOFile.ColCounter(namefile); 
        double[][] initmatrix= IOFile.readFile(namefile, nR, nC) ;
        return initmatrix; 
    }
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
        Menu.menuInput();
        int input=IOKeyboard.InputOption(read); 
        
        switch(input) {
            case 1: 
                double[][] initmatrixkeyboard = IOKeyboard.readMatrix(read); 
                int x = read.nextInt(); 
                double[] koefisien= polynomEq(initmatrixkeyboard);
                double result=approach(koefisien, x);
                Menu.menuWritetoFile();
                int inputYT = IOKeyboard.InputOption(read);  

                switch(inputYT) {
                    case 1: 
                        String namafile = read.next("masukkan nama file:") ;
                        String poleq = equation(koefisien, result); 
                        IOFile.writeFile_1(namafile,poleq);
                }
            case 2: 
                double[][] initmatrixkeyboard1 = scanfile(read); 
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
                Menu.menuWritetoFile();
                int inputYT1 = IOKeyboard.InputOption(read);  

                switch(inputYT1) {
                    case 1: 
                        String namafile1 = read.next("masukkan nama file:") ;
                        String poleq1 = equation(koefisien1, result1); 
                        IOFile.writeFile_1(namafile1,poleq1);
                }
        } 
    }
}
