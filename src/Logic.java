import java.util.ArrayList;
import java.util.Scanner;
public class Logic {

    public Logic(){
    }

    private ArrayList<Bank> UserInformation = new ArrayList<>();
    private Bank currentUser;
    Scanner input = new Scanner(System.in);
    Save s = new Save();


    public void simulate() {
        LoadFiles();
        String choice = "";
        System.out.println("Welcome to the International Bank");
        System.out.println("Would you like to create a bank account? Y/N (exit)");
        choice = input.nextLine();
        if(choice.equals("exit")){
            System.exit(0);
        }
        if (choice.contains("y") || choice.contains("Y")) {
            CreateAccount();
            BankInteraction();
            s.Save(UserInformation);
        }
        else {
            System.out.println("Would you like to log in? Y/N (exit)");
            String i = input.nextLine();
            if(i.equals("exit")){
                System.exit(0);
            }
            if(i.contains("y") || i.contains("Y")){
                LoginAccount();
                BankInteraction();
                s.Save(UserInformation);

            }
            else{
                System.out.println("Returning back to the Main menu");
                simulate();
            }
        }


    }

    private void LoadFiles(){
        ArrayList<Bank> user = s.Load();
        try {
            for (int i = 0; i < user.size(); i++) {
                UserInformation.add(user.get(i));
            }
        }
        catch(Exception e){

        }
    }


    //This method allows the user to create an account, which would create a new bank object that stores
    //the
    public void CreateAccount() {
        //Asking for user credentials;
        boolean validName = false;
        String answer = "";
        while(!(validName)) {
            System.out.println("What is your name? (return)");
            answer = input.nextLine();
            if (answer.equals("return")) {
                System.out.println("Returning to the Main Menu");
                simulate();
            }

            boolean found = false;
            for(int i = 0; i < UserInformation.size(); i++){
                if(answer.equals(UserInformation.get(i).getUser().getName())){
                    System.out.println("This user already exists, please try another name");
                    found = true;
                }
            }
            if(!(found)){
                validName = true;
            }
        }

        int answer2 = 0;

        boolean validAge = false;
        while (!(validAge)) {
            try {
                System.out.println("What is your age? You must be above the age of 18 to apply for an account.(\"return\")");
                String s = input.nextLine();
                if(s.equals("return")) {
                    System.out.println("Returning to Main Menu");
                    simulate();
                }
                else {
                    answer2 = Integer.parseInt(s);
                    validAge = true;
                }
            }
            catch (Exception e) {
                System.out.println("That is not a valid age");
            }
        }
        if (validAge && answer2 < 18) {
            System.out.println("You are not old enough to apply for an account.");
        }
        else {
            boolean validBalance = false;
            Person user = new Person(answer, answer2);
            double answer3 = 0.0;
            while (!validBalance) {
                try {
                    System.out.println("A minimum of $100 is required to start a bank account, how much would you like to deposit initially? (return)");
                    String s = input.nextLine();
                    if(s.equals("return")) {
                        System.out.println("Returning to Main Menu");
                        simulate();
                    }
                    else {
                        answer3 = Double.parseDouble(s);
                        validBalance = true;
                    }

                } catch (Exception e) {
                    System.out.println("That is not a valid balance");
                }
            }
            if (answer3 < 100) {
                System.out.println("You have not met the required minimum amount of $100");
            }
            else {
                System.out.println("We would now like to ask you to create a 4 digit pin number for future access to you account");
                System.out.println("Make sure to REMEMBER your pin number");
                boolean validPin = false;
                while (!validPin) {
                    System.out.print("Enter a 4 digit pin number (return) : ");
                    String pin = input.nextLine();
                    if(pin.equals("return")) {
                        System.out.println("Returning to Main Menu");
                        simulate();
                    }
                    else {
                        if (pin.length() != 4) {
                            System.out.println("The pin you have entered does not contain the correct amount of digits");
                        } else {
                            try {
                                Bank userBankAcc = new Bank(user, Integer.parseInt(pin), answer3);
                                System.out.println("Your pin number has been successfully created");
                                UserInformation.add(userBankAcc);
                                currentUser = UserInformation.get(UserInformation.size()-1);
                                System.out.println("You have successfully created a new Bank Account!");
                                validPin = true;
                            } catch (Exception e) {
                                System.out.println("Your attempted pin number contains other characters, please try again.");
                            }
                        }
                    }
                }
            }
        }
    }


    // This method allows the user to login to an already existing account
    public void LoginAccount() {
        System.out.println("Here are the current list of users: ");
        for (int i = 0; i < UserInformation.size(); i++) {
            System.out.println(UserInformation.get(i).getUser().getName());
        }
        System.out.println();
        boolean validUser = false;

        while (!(validUser)) {
            System.out.println("What is your name?");
            String name = input.nextLine();
            for (int i = 0; i < UserInformation.size(); i++) {
                if (UserInformation.get(i).getUser().getName().equals(name)) {
                    validUser = true;
                    currentUser = UserInformation.get(i);
                    break;
                }
            }
            if (!(validUser)) {
                System.out.println("That is not a valid user, please try again.");
            }

        }

        boolean correctPin = false;
        if (validUser) {
            int count = 5;
            while (!(correctPin)) {
                if (count == 0) {
                    System.out.println("You have used all of your attempts. Returning back to the main menu");
                    System.out.println();
                    simulate();

                } else {
                    System.out.println("Please enter your 4 digit pin number, you have " + count + " attempts.");
                    String pin = input.nextLine();
                    if (pin.length() != 4) {
                        System.out.println("The pin you have entered does not contain the correct amount of digits.");
                    } else {
                        try {
                            int pinNum = Integer.parseInt(pin);
                            if (pinNum == currentUser.getPin()) {
                                System.out.println("You have successfully logged in");
                                correctPin = true;
                            } else {
                                System.out.println("That is an incorrect pin number");
                                count--;
                            }
                        } catch (Exception e) {
                            System.out.println("The pin you have entered contains other characters, please try again");
                        }
                    }
                }
            }
        }
    }

    public void BankInteraction(){
        String userInput = "";

        while(!(userInput.equals("return"))){
            System.out.println();
            System.out.println("Welcome to the International Bank, here is a list of current actions");

        }
    }

    public void Runner(){

    }

    public void Commands(){
        
    }

    public void Deposit(){

    }

    public void Withdraw(){

    }

    public void ChangeName(){

    }

    public void ChangePin(){

    }



}
