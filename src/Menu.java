
import java.util.Scanner;


public class Menu{

    public static void menuMain(){

        System.out.println("-----  Selamat datang di Kalkulator Matrix  -----");
        System.out.println("");
        System.out.println("                      MENU                       ");
        System.out.println("  1.  Sistem Persamaan Linear");
        System.out.println("  2.  Determinan    ");
        System.out.println("  3.  Matriks Balikan");
        System.out.println("  4.  Interpolasi Polinom  ");
        System.out.println("  5.  Interpolasi Bicubic");
        System.out.println("  6.  Regresi Linier Berganda ");
        System.out.println("  7.  Exit");
    }

    public static void menuSpl(){

        System.out.println("-----  SISTEM PERSAMAAN LINEAR  -----");
        System.out.println("");
        System.out.println("  1. Metode Eliminasi Gauss");
        System.out.println("  2. Metode Eliminasi Gauss-Jordan");
        System.out.println("  3. Metode Matriks Balikan");
        System.out.println("  4. Kaidah Cramer");
    }

    public static void menuDeterminan(){
        System.out.println("-----  DETERMINAN  -----");
        System.out.println("");
        System.out.println("  1. Metode Reduksi Baris");
        System.out.println("  2. Metode Ekspansi Kofaktor");

    }

    public static void menuBalikan(){
        System.out.println("-----  MATRIKS BALIKAN  -----");
        System.out.println("");
        System.out.println("  1. Metode Matriks Adjoin");
        System.out.println("  2. Metode Eliminasi Gauss-Jordan");
    }

    public static void menuInput(){
        System.out.println("-----  INPUT  -----");
        System.out.println("");
        System.out.println("  1. Input Keyboard");
        System.out.println("  2. Input File");
    }
    public static void menuWritetoFile(){
        System.out.println("-----  INGIN MENYIMPAN HASIL KE FILE?  -----");
        System.out.println("");
        System.out.println("  1. ya");
        System.out.println("  2. tidak");
    }


    public static int input(Scanner read){

        System.out.print("Masukan pilihan anda: ");
        int x = read.nextInt();

        return x;
    }
}   
