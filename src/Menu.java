import java.util.Scanner; 
public class Menu{
    public static int InputOption(){
        Scanner read = new Scanner(System.in);
        System.out.println("Pilih masukan melalui:");
        System.out.println("1. File");
        System.out.println("2. Keyboard");
        int i = read.nextInt();
        return i;
    }
}