//Belum Bener :(
import java.io.*;
import java.util.*;
public class IOFile{
    public static String InputFileName(){
        Scanner read = new Scanner(System.in);
        System.out.print("Masukan nama file: ");
        String fileName = read.nextLine();
        read.close();
        return fileName;
    }
    public static int RowCounter(String fileName){
        FileReader fr = null;
        try{
            fr = new FileReader(fileName);
        } catch(FileNotFoundException fe) {
            System.out.println("File tidak ditemukan!");
        }
        int rowCount = 0;
        Scanner rowScanner = new Scanner(fr);
        while (rowScanner.hasNextLine()) {
          rowCount++;
          rowScanner.nextLine();
        }
        rowScanner.close();
        return rowCount;
    }
    public static int ColCounter(String fileName){
        FileReader fr = null;
        try{
            fr = new FileReader(fileName);
        } catch(FileNotFoundException fe) {
            System.out.println("File tidak ditemukan!");
        }
        int colCount = 0;
        Scanner colScanner = new Scanner(fr);
        while (colScanner.hasNextDouble()) {
          colCount++;
          colScanner.nextDouble();
        }
        colScanner.close();
        return colCount;
    }
    public static double[][] readFile(String fileName, int row, int col) {
        double[][] mat = new double[row][col];
        System.out.println("Membaca file..." + fileName);
        FileReader fr = null;
        try{
            fr = new FileReader(fileName);
        } catch(FileNotFoundException fe) {
            System.out.println("File tidak ditemukan!");
        }
        Scanner FileScanner = new Scanner(fr);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = FileScanner.nextDouble();
            }
        }
        FileScanner.close();
        return mat;
    }
}