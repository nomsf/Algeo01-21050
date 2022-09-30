/*          String fileName = IOFile.InputFileName(read);
            int row = IOFile.RowCounter(fileName);
            int col = IOFile.ColCounter(fileName);
            double[][] mat = IOFile.readFile(fileName, row, col);*/
import java.io.*;
import java.util.*;


public class IOFile{

    public static String InputFileName(Scanner read){
        System.out.print("Masukan nama file tanpa extension: ");
        String fileName = "../test/";
        fileName += read.next();
        fileName += ".txt";
        return fileName;
    }
    public static int RowCounter(String fileName){
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan!");
        }
        try (Scanner readf = new Scanner(fr)) {
            int rowCount = 0;
            while (readf.hasNextLine()) {
                rowCount++;
                readf.nextLine();
            }
            return rowCount;
        }
    }

    
    public static int ColCounter(String fileName){
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan!");
        }
        try (Scanner readf = new Scanner(fr)) {
            int colCount = 0;
            System.out.println(readf.hasNextDouble());
            while (readf.hasNextDouble()) {
                colCount++;
                readf.nextDouble();
            }
            return colCount/RowCounter(fileName);
        }
    }
    public static double[][] readFile(String fileName, int row, int col) {
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan!");
        }
        try (Scanner readf = new Scanner(fr)) {
            double[][] mat = new double[row][col];
            System.out.println("Membaca file '" + fileName + "'");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++){
                    if (readf.hasNextDouble()){
                        double el = readf.nextDouble();
                        mat[i][j] = el;
                    }
                }
            }
            return mat;
        }
    }

    public static boolean createFile(String filename){
        // return true kalo filenya berhasil di buat
        // false kalo udh ada file yg namanya sama

        File file = new File(filename);
        boolean saved = false;
        try{
            if(file.createNewFile()){
                System.out.println(" File telah dibuat.");
                saved = true;
            }
            else{
                System.out.println("Sudah ada file dengan nama yang sama. ");
                saved = false;
            }
        } catch(IOException x){
            System.out.println("Error dalam membuat file. ");
            x.printStackTrace();
        }
        
        return saved;

    }

    public static void writeFile_1(Scanner read){
        // skema write file yang naufal pake

        System.out.println("Apakah ingin menyimpan hasil ke dalam file? (y/n)");
        String input = read.nextLine();

        if(input == "y"){
            
        }
    }
}