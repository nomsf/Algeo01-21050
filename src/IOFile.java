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

    public static void writeFile_1(String filename, String val){
        // skema write file yang naufal pake

        try{
            FileWriter write = new FileWriter(filename);
            write.write(val);
            write.close();
            System.out.println("-----  " + "Berhasil menuliskan pada " + filename +"  -----");

        }
        catch(IOException x){
            System.out.println("Error dalam menyimpan jawaban. ");
            x.printStackTrace();
        }
        
        
        
    }

    public static void writeRegresi(String fileName, double[][] res, double restaksir){
        int row = res.length;
        int col = res[0].length;
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("Persamaan regresi:\n");
            if(res[0][col - 1] != 0) myWriter.write("y = " + res[0][col - 1] + " ");
            for (int i = 1; i < row; i++) {
                if(res[i][col - 1] != 0){
                    myWriter.write(" + (" + res[i][col - 1] + ")" + "x" + i + " ");
                }
            }
            myWriter.write("\n");
            myWriter.write("Hasil Taksiran:\n");
            myWriter.write(restaksir + "\n");
            myWriter.write("\n");
            myWriter.close();
            System.out.println("-----  " + "Berhasil menuliskan pada " + fileName +"  -----");
            } catch (IOException e) {
                System.out.println("Error!");
            }
    }

    public static void writeSolGauss(String fileName, String[] res){
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("Solusi SPL:\n");
            for (int i = 0; i < res.length; i++) {
                myWriter.write(res[i] + "\n");
            }
            myWriter.write("\n");
            myWriter.close();
            System.out.println("-----  " + "Berhasil menuliskan pada " + fileName +"  -----");
            } catch (IOException e) {
                System.out.println("Error!");
            }
    }

    public static void writeMatrix(String filename, double[][] m){
        try{

            FileWriter write = new FileWriter(filename);
            
            for(int i = 0; i < MatrixOp.getRow(m); i++){
                for(int j = 0; j < MatrixOp.getCol(m); j++){
                    write.write(m[i][j] + " ");
                }
                write.write("\n");
            }
            write.close();
            System.out.println("-----  " + "Berhasil menuliskan pada " + filename +"  -----");

        } catch(IOException x){
            System.out.println("Error dalam menyimpan jawaban. ");
            x.printStackTrace();
        }
    }

    public static void writeList(String filename, String[] list){

        try{

            FileWriter write = new FileWriter(filename);
            
            for(int i = 0; i < list.length; i++){
                write.write(list[i] + " ");
                write.write("\n");
            }
            
            write.close();
            System.out.println("-----  " + "Berhasil menuliskan pada " + filename +"  -----");

        } catch(IOException x){
            System.out.println("Error dalam menyimpan jawaban. ");
            x.printStackTrace();
        }
    }
}