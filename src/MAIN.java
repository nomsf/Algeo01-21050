import java.util.Scanner; 

public class MAIN extends Menu{

    public static void main(String[] args){

        Scanner read = new Scanner(System.in);
        boolean exit = false;
        int input;

        while (!exit){

            menuMain();

            
            input = input(read);

            switch (input){
                case 1: // spl
                    menuSpl();
                    input = input(); 
                    switch(input){
                        case 1: // elim gauss
                            break;

                        case 2: // elim gauss jordan
                            break;
                        
                        case 3: // balikan
                            break;

                        case 4: // cramer
                            break;

                        default:
                            System.out.println("-----  Masukan salah!  -----")
                            break;
                    }
                    break;
                case 2: // Determinan
                    menuDeterminan();
                    input = input();
                    switch(input){
                        case 1: // reduksi baris

                            break;
                        case 2: // ekspansi kofaktor

                            break; 

                        default:
                            System.out.println("-----  Masukan salah!  -----")
                            break;
                    }
                    break;

                case 3: // Matriks Balikan
                        menuBalikan();
                        input = input();
                        switch(input){
                            case 1: // adjoin
                                break;
                            case 2: // elim gauss jordan
                                break;
                            default:
                                System.out.println("-----  Masukan salah!  -----")
                                break;
                    }
                    break;

                case 4: // interpolasi polinom
                    break;
                      
                case 5: // interpolasi bicubic
                    break;

                case 6: // regresi linier berganda
                    break;

                case 7: // exit
                    exit = true;
                    break;
                
                default:
                    System.out.println("-----  Masukan salah!  -----")
                    break;
            }
        }
        

    }



}