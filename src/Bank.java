public class Bank {

    private double balance;
    private Person user;
    private int pin;

    public Bank(Person person, int pin, double balance){
        user = person;
        this.balance = balance;
        this.pin = pin;
    }

    public Person getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }

    public int getPin(){
        return pin;
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
