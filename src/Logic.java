import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Logic {


    public Logic() {
    }

    ArrayList<Bank> UserInformation = new ArrayList<Bank>();
    Scanner input = new Scanner(System.in);
    Save s = new Save();

    public void simulate() {
        Load();

        String choice = "";
        System.out.println("Welcome to the International Bank");
        System.out.println("Would you like to create a bank account? Y/N");
        choice = input.nextLine();
        if (choice.contains("y") || choice.contains("Y")) {
            createAccount();
            s.Save(UserInformation);
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

    public void Load() throws FileNotFoundException{
        try{
            String name = "";
            int age = 0;


            File myFile = new File("src/person.data");
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()){
                String info = 
            }
        }
        catch(FileNotFoundException e){
            System.out.println("error");
            e.printStackTrace();
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
                    UserInformation.add(userBankAcc);
                }
            }
        }


    }
}
