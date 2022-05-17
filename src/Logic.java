import java.util.Scanner;
public class Logic {


    public Logic() {
    }

    Save s = new Save();

    Scanner input = new Scanner(System.in);

    public void simulate() {
        String choice = "";
        System.out.println("Welcome to the International Bank");
        System.out.println("Would you like to create a bank account? Y/N");
        choice = input.nextLine();
        if (choice.contains("y") || choice.contains("Y")) {
            createAccount();
        } else {
            System.out.print("Would you like to log in?");
        }

        while (choice != "exit") {

        }
    }

    public void runner(String choice) {
        while (choice != "exit") {
            if (choice.contains("create account")) {

            }
        }
    }


    public void createAccount() {
        //Asking for user credentials;
        boolean validAge = false;
        System.out.println("What is your name?");
        String answer = input.nextLine();
        int answer2 = 0;
        while(!(validAge)){
            System.out.println("What is your age? You must be above the age of 18 to apply for an account.");
            try{
               answer2 = input.nextInt();
               validAge = true;
            }
            catch(Exception e){
                System.out.println("That is not a valid age");
            }
        }
        if (answer2 < 18) {
            System.out.println("You are not old enough to apply for an account.");
        } else {
            Person user = new Person(answer, answer2);
            System.out.println("A minimum of $100 is required to start a bank account, how much would you like to deposit initially?");
            double answer3 = input.nextDouble();
            if (answer3 < 100) {
                System.out.println("You have not met the required minimum amount of $100");
            } else {
                System.out.println("You have successfully created a bank account");
                Bank userBankAcc = new Bank(user, answer3);

                System.out.println("We would now like to ask you to create a 4 digit pin number for future access to you account");
                System.out.println("Make sure to REMEMBER your pin number");
                boolean validPin = false;
                while (!validPin) {
                    System.out.print("Enter a 4 digit pin number: ");
                    String pin = input.next();
                    if (pin.length() != 4) {
                        System.out.println("The pin you have entered does not contain the correct amount of digits");
                    }
                    else{
                        try{
                            System.out.println("Your pin number has been successfully created");
                            userBankAcc.createPinNum((Integer.parseInt(pin)));
                            validPin = true;
                        }
                        catch(Exception e){
                            System.out.println("Your attempted pin number contains letters, please try again.");
                        }
                    }
                    s.Save(userBankAcc);
                }

            }

        }

        ;
    }
}
