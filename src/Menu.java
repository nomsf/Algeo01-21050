import java.util.Scanner; 
public class Menu{
    public static int InputOption(){
        System.out.println("Pilih masukan melalui:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        Scanner read = new Scanner(System.in);
        int i = read.nextInt();
        read.close();
        return i;
    }
}