public class BankInformation {

    private double balance;
    private PersonInfo user;
    private int pin;

    public BankInformation(PersonInfo person, int pin, double balance){
        user = person;
        this.balance = balance;
        this.pin = pin;
    }

    public PersonInfo getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double bal){
        balance = bal;
    }

    public int getPin(){
        return pin;
    }

    public void listInfo(){
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Balance: " + balance);
        System.out.println("Pin " + pin);
    }

    public void withdraw(double currency){
        balance = balance - currency;
    }

    public void deposit(double currency){
        balance = balance + currency;
    }

    public void setPin(int pin){
        this.pin = pin;
    }


}
