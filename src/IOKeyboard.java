import java.util.Scanner; 
public class IOKeyboard{
    public static double[][] readMatrix(){
        Scanner read = new Scanner(System.in); // buat obj read dari Scanner class
        System.out.print("Masukan baris matriks: ");
        int row = 0, col = 0;
        if (read.hasNext()){
            row = read.nextInt();  // nextInt = method dr Scanner buat ngebaca integer
        }
        System.out.print("Masukan kolom matriks: ");
        if (read.hasNext()){
            col = read.nextInt();  // nextInt = method dr Scanner buat ngebaca integer
        }
        double[][] mat = new double[row][col]; // assign ukuran array
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                System.out.print("Masukan elemen Matriks [" + i +"][" + j + "] : ");
                mat[i][j] = read.nextDouble();
            }
        }
        read.close();
        return mat;
    }
    public static double[][] readMatrixSPL(){
        Scanner read1 = new Scanner(System.in); // buat obj read dari Scanner class
        System.out.print("Masukan baris matriks: ");
        int row = read1.nextInt();  // nextInt = method dr Scanner buat ngebaca integer
        System.out.print("Masukan kolom matriks: ");
        int col = read1.nextInt();
        double[][] mat = new double[row][col]; // assign ukuran array
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if(j == col - 1){
                    System.out.print("Masukan b[" + i + "]: ");
                    mat[i][j] = read1.nextDouble();
                }
                else{
                    System.out.print("Masukan koefisien a[" + i +"][" + j + "]: ");
                    mat[i][j] = read1.nextDouble();
                }
            }
        }
        read1.close();
        return mat;
    }
    static void printMatrix(double[][] mat){
        int ROW = MatrixOp.getRow(mat);
        int COL = MatrixOp.getCol(mat);
        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}