import java.util.ArrayList;
import java.util.Scanner;
public class BankLogic {

    public BankLogic(){
    }

    private ArrayList<BankInformation> UserInformation = new ArrayList<>();
    private BankInformation currentUser;
    Scanner input = new Scanner(System.in);
    Save s = new Save();
    Clear c = new Clear();


    public void simulate() {
        try{
            Thread.sleep(700);
            c.clearScreen();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(UserInformation.isEmpty()){
            loadFiles();
        }
        String choice = "";
        System.out.println("Welcome to the International Bank");
        System.out.println("Would you like to (create) a bank account or (login)(exit)");
        choice = input.nextLine();
        if(choice.equals("exit")){
            System.exit(0);
        }
        if (choice.equalsIgnoreCase("create")){
            createAccount();
            bankInteraction();
        }
        else if (choice.equalsIgnoreCase("login")){
                loginAccount();
                bankInteraction();
        }
        else{
        System.out.println("Returning back to the Main menu");
        simulate();
        }

    }

    private void loadFiles(){
        ArrayList<BankInformation> user = s.Load();
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
    public void createAccount() {
        //Asking for user credentials;
        boolean validName = false;
        String answer = "";
        //Checks if the name entered exists
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
                System.out.println("What is your age? You must be above the age of 18 to apply for an account.(return)");
                String s = input.nextLine();
                if(s.equals("return")) {
                    System.out.println("Returning to Main Menu");
                    simulate();
                }
                else {
                    if (validAge && answer2 < 18) {
                        System.out.println("You are not old enough to apply for an account.");
                    }
                    else {
                        answer2 = Integer.parseInt(s);
                        validAge = true;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("That is not a valid age");
            }
        }

            boolean validBalance = false;
            PersonInfo user = new PersonInfo(answer, answer2);
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
                        if (answer3 < 100) {
                            System.out.println("You have not met the required minimum amount of $100");
                        }
                        else{
                            validBalance = true;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("That is not a valid balance");
                }
            }

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
                                if(Integer.parseInt(pin) < 0 || Integer.parseInt(pin) > 9999) {
                                    System.out.println("The pin you have entered contains other characters, please try again.");
                                }
                                else{
                                    BankInformation userBankAcc = new BankInformation(user, Integer.parseInt(pin), answer3);
                                    System.out.println("Your pin number has been successfully created");
                                    UserInformation.add(userBankAcc);
                                    s.Save(UserInformation);
                                    currentUser = UserInformation.get(UserInformation.size() - 1);
                                    System.out.println("You have successfully created a new Bank Account!");
                                    validPin = true;
                                }
                            } catch (Exception e) {
                                System.out.println("Your attempted pin number contains other characters, please try again.");
                            }
                        }
                    }
                }
            }




    // This method allows the user to login to an already existing account
    public void loginAccount() {
        System.out.println("Here are the current list of users: ");
        for (int i = 0; i < UserInformation.size(); i++) {
            System.out.println(UserInformation.get(i).getUser().getName());
        }
        System.out.println();
        boolean validUser = false;

        while (!(validUser)) {
            System.out.println("What is your name? (return)");
            String name = input.nextLine();
            if(name.equals("return")) {
                System.out.println("Returning to Main Menu");
                simulate();
            }
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
                    System.out.println("Please enter your 4 digit pin number, you have " + count + " attempts. (return)");
                    String pin = input.nextLine();
                    if(pin.equals("return")) {
                        System.out.println("Returning to Main Menu");
                        simulate();
                    }
                    if (pin.length() != 4) {
                        System.out.println("The pin you have entered does not contain the correct amount of digits.");
                    } else {
                        try {
                            if(Integer.parseInt(pin) < 0 || Integer.parseInt(pin) > 9999) {
                                System.out.println("The pin you have entered contains other characters, please try again.");
                            }
                            else {
                                int pinNum = Integer.parseInt(pin);
                                if (pinNum == currentUser.getPin()) {
                                    System.out.println("You have successfully logged in");
                                    correctPin = true;
                                } else {
                                    System.out.println("That is an incorrect pin number");
                                    count--;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("The pin you have entered contains other characters, please try again");
                        }
                    }
                }
            }
        }
    }

    public void bankInteraction(){
        try{
            Thread.sleep(700);
            c.clearScreen();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String userInput = "";
        while(!(userInput.equals("return"))){
            try{
                Thread.sleep(700);
                c.clearScreen();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println();
            currentUser.listInfo();
            System.out.println();
            System.out.println("Here is a list of current actions:");
            commands();
            userInput = input.nextLine();
            Runner(userInput);
        }
    }

    public void commands(){
        System.out.println("-------------------");
        System.out.println("-Deposit-");
        System.out.println("-Withdraw-");
        System.out.println("-Change (Name)-");
        System.out.println("-Change (Pin)-");
        System.out.println("-Delete Account-");
        System.out.println("-Return-");
        System.out.println("-------------------");
    }

    public void Runner(String choice){
        String lChoice = choice.toLowerCase();
        if (lChoice.contains("deposit")){
            depositBal();
        }
        else if(lChoice.contains("withdraw")){
            withdrawBal();
        }
        else if(lChoice.contains("name")){
            changeName();
        }
        else if(lChoice.contains("pin")){
            changePin();
        }
        else if (lChoice.contains("delete")){
            deleteAcc();
        }
        else if(lChoice.contains("return")){
            System.out.println("Returning to the Main Menu");
            simulate();
        }
        else{
            System.out.println("That is not a valid command, please try again");
        }

    }


    public void depositBal(){
        boolean validDeposit = false;
        double deposit;
        while(!(validDeposit)) {
            System.out.print("Enter the amount of money you would like to deposit (return) : ");
            try {
                String dep = input.nextLine();
                if(dep.equals("return")){
                    System.out.println("Returning back to the action Menu");
                    bankInteraction();
                }
                else{
                    deposit = Double.parseDouble(dep);
                    if(deposit > 9999999.99){
                        System.out.println("The amount you have entered exceeds the amount you are allowed to deposit");
                    }
                    else{
                        currentUser.deposit((Math.round(deposit * 100)) / 100.0);
                        System.out.println("Money successfully deposited");
                        validDeposit = true;
                    }
                }
            } catch (Exception e) {
                System.out.print("That is not a valid number, please try again");
                System.out.println();
            }
        }
        s.Save(UserInformation);
    }

    public void withdrawBal(){
        boolean validWithdraw = false;
        double withdraw;
        while(!(validWithdraw)) {
            System.out.print("Enter the amount of money you would like to withdraw (return) (all) : ");
            try {
                String wit = input.nextLine();
                if(wit.equals("return")){
                    System.out.println("Returning back to the action Menu");
                    bankInteraction();
                }
                if(wit.equals("all")){
                    currentUser.setBalance(0.0);
                    validWithdraw = true;
                }
                else{
                    withdraw = Double.parseDouble(wit);
                    if(withdraw > currentUser.getBalance()){
                        System.out.println("You cannot withdraw money you do not have.");
                    }
                    if(withdraw  < 0.01){
                        System.out.println("That is not a valid input");
                    }
                    else {
                        currentUser.withdraw((Math.round(withdraw * 100)) / 100.0);
                        System.out.println("Money successfully withdrawn");
                        validWithdraw = true;
                    }
                }
            } catch (Exception e) {
                System.out.print("That is not a valid number, please try again");
                System.out.println();
            }
        }
        s.Save(UserInformation);
    }

    public void changeName(){
        System.out.println("What would you like to change your name to be?");
        String name = input.nextLine();
        if(name.equals("return")){
            System.out.println("Returning to the Action Menu");
        }
        else{
            currentUser.getUser().setName(name);
            System.out.println("Name successfully changed");
        }
        s.Save(UserInformation);
    }

    public void changePin() {
        boolean validPin = false;
        while (!(validPin)) {
            System.out.println("What would you like to change your 4 digit pin number to? (return)");
            String pin = input.nextLine();
            if (pin.equals("return")) {
                System.out.println("Returning to Main Menu");
                bankInteraction();
            }
            if (pin.length() != 4) {
                System.out.println("The new pin you have entered does not contain the correct amount of digits.");
            } else {
                try {
                    int pinNum = Integer.parseInt(pin);
                    boolean validConfirm = false;
                    while(!(validConfirm)) {
                        System.out.println("Re-enter your new pin for conformation (return)");
                        String confirm = input.nextLine();
                        if(confirm.equals("return")){
                            bankInteraction();
                        }
                        int confirmPinNum = Integer.parseInt(confirm);
                        if (pinNum == confirmPinNum) {
                            currentUser.setPin(confirmPinNum);
                            System.out.println("Your pin number has been successfully changed. Remember this number");
                            validPin = true;
                            validConfirm = true;
                        } else {
                            System.out.println("The pin you have entered does match the previous,please try again");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("The pin you have entered contains other characters, please try again");
                }

            }
        }
        s.Save(UserInformation);
    }

    public void deleteAcc(){
        System.out.println("Deleting an account would return the cash to the user and this account will be lost forever");
        System.out.println("Are you sure you want to delete this account? Yes or No");
        String answer = input.nextLine();
        answer.toLowerCase();
        if(answer.contains("yes")){
            boolean correctPin = false;
            while(!(correctPin)) {
                System.out.println("To delete your account please enter your pin number (return) : ");
                String pin = input.nextLine();
                if(pin.equals("return")){
                    System.out.println("Returning to the action Menu");
                    bankInteraction();
                }
                if(pin.length() != 4){
                    System.out.println("The pin you have entered does not contain the correct amount of digits");
                }
                else{
                    try{
                        int pinNum = Integer.parseInt(pin);
                        if(pinNum == currentUser.getPin()){
                            System.out.println("Account is being deleted");
                            int index = 0;
                            for(int i = 0; i < UserInformation.size(); i++){
                                if(UserInformation.get(i).getUser().getName().equals(currentUser.getUser().getName())){
                                    index = i;
                                }
                            }
                            UserInformation.remove(index);
                            s.Save(UserInformation);
                            correctPin = true;
                            simulate();
                        }
                        else{
                            System.out.println("That is the incorrect pin number please try again");
                        }

                    }
                    catch(Exception e){
                        System.out.println("The pin you have entered contains other characters, please try again");
                    }
                }

            }
        }
        else{
            System.out.println("Returning back to the action Menu");
            bankInteraction();
        }

    }



}
