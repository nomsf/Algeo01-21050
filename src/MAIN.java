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
                    input = input(read); 
                    switch(input){
                        case 1: // elim gauss
                            gauss.gaussdriver(read);
                            break;

                        case 2: // elim gauss jordan
                            gaussjordan.gaussjordandriver(read);
                            break;
                        
                        case 3: // balikan
                            SplBalikan.splbalikanDriver(read);
                            break;

                        case 4: // cramer
                            SplCramer.splcramerDriver(read);
                            break;

                        default:
                            System.out.println("-----  Masukan salah!  -----");
                            break;
                    }
                    break;
                case 2: // Determinan
                    menuDeterminan();
                    input = input(read);
                    switch(input){
                        case 1: // reduksi baris
                            DeterminantReduction.reductionDriver(read);
                            break;
                        case 2: // ekspansi kofaktor
                            Cofactor.cofactorDriver(read);
                            break; 

                        default:
                            System.out.println("-----  Masukan salah!  -----");
                            break;
                    }
                    break;

                case 3: // Matriks Balikan
                        menuBalikan();
                        input = input(read);
                        switch(input){
                            case 1: // adjoin
                                InverseAdjoin.adjoinDriver(read);
                                break;
                            case 2: // elim gauss jordan
                                InverseGauss.inverseGaussDriver(read);
                                break;
                            default:
                                System.out.println("-----  Masukan salah!  -----");
                                break;
                    }
                    break;

                case 4: 
                    Interpolasipolinom.Interpolasipolinomdriver(read);// interpolasi polinom
                    break;
                      
                case 5: // interpolasi bicubic
                    break;

                case 6: // regresi linier berganda
                    regresi.regresidriver(read);
                    break;

                case 7: // exit
                    exit = true;
                    break;
                
                default:
                    System.out.println("-----  Masukan salah!  -----");
                    break;
            }

            System.out.println("-----  Apakah ingin melanjutkan perhitungan lain?  -----");
            System.out.println("       1. Yes");
            System.out.println("       2. No");
            int opt = read.nextInt();

            if (opt == 1){
                exit = false;
            }
            else{
                exit = true;
            }
        }
        
        System.out.println("-----  TERIMAKASIH  -----");

    }



}