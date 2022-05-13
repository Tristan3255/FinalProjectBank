public class Bank {

    private double balance;
    private Person user;

    public Bank(Person person, double balance){
        user = person;
        this.balance = balance;
    }

    public void withdraw(double currency){
        balance = balance - currency;
    }

    public void deposit(double currency){
        balance = balance + currency;
    }

    public void createPinNum(int pin){
        user.setPin(pin);
    }


}
