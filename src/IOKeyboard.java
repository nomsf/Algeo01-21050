import java.util.Scanner; 
public class IOKeyboard{
    
    public static int InputOption(Scanner read){
        Menu.menuInput();
        int i = read.nextInt();
        return i;
    }

    public static int WritetoFileOption(Scanner read){
        Menu.menuWritetoFile();
        int i = read.nextInt();
        return i;
    }

    public static double[][] readMatrix(Scanner read){
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
        return mat;
    }

    public static double[] readArrTaksir(Scanner read, int n){
        System.out.println("Masukan data yang ingin ditaksir: ");
        double[] xtaksir = new double[n]; // assign ukuran array
        for (int i = 0; i < n; i++){
            System.out.print("Masukan data x" + (i + 1) +": ");
            xtaksir[i] = read.nextDouble();
        }
        return xtaksir;
    }
    
    public static double[][] readMatrixSPL(Scanner read){
        System.out.print("Masukan baris matriks: ");
        int row = read.nextInt();  // nextInt = method dr Scanner buat ngebaca integer
        System.out.print("Masukan kolom matriks: ");
        int col = read.nextInt();
        double[][] mat = new double[row][col]; // assign ukuran array
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if(j == col - 1){
                    System.out.print("Masukan b[" + i + "]: ");
                    mat[i][j] = read.nextDouble();
                }
                else{
                    System.out.print("Masukan koefisien a[" + i +"][" + j + "]: ");
                    mat[i][j] = read.nextDouble();
                }
            }
        }
        return mat;
    }
    public static double[][] readMatrixRegresi(Scanner read){
        System.out.print("Masukan jumlah peubah: ");
        int col = read.nextInt();  // nextInt = method dr Scanner buat ngebaca integer
        System.out.print("Masukan jumlah sampel: ");
        int row = read.nextInt();
        double[][] mat = new double[row][col + 1]; // assign ukuran array
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col + 1; j++){
                if(j == col){
                    System.out.print("Masukan nilai y sampel ke-" + (i + 1) + ": ");
                    mat[i][j] = read.nextDouble();
                }
                else{
                    System.out.print("Masukan nilai x" + (j + 1) +" sampel ke-" + (i + 1) + ": ");
                    mat[i][j] = read.nextDouble();
                }
            }
        }
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