import java.util.Scanner;
public class Logic {


    public Logic(){}

    public void runner(String choice){
        while(choice != "exit"){
            if(choice.contains("create account")){

            }
        }
    }
    Scanner input = new Scanner(System.in);

    public void createAccount(){
        //Asking for user credentials;
        boolean created = false;
        System.out.println("What is your name?");
        String answer = input.nextLine();
        System.out.println("What is your age? You must be above the age of 18 to apply for an account.");
        int answer2 = input.nextInt();
        if(answer2 < 18){
            System.out.println("You are not old enough to apply for an account.");
        }
        else {
            Person user = new Person(answer, answer2);
            System.out.print("A minimum of $100 is required to start a bank account, how much would you like to deposit initially?");
            double answer3 = input.nextDouble();
            if(answer3 < 100){
                System.out.print("You have not met the required minimum amount of $100");
            }
            else{
                System.out.print("You have successfully created a bank account");
                Bank userBankAcc = new Bank(user,answer3);
                created = true;
            }
        }

        




    }


}
