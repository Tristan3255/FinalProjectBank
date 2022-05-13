public class Person {

    private String name;
    private int age;
    private int pin;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
        setPin(0);
    }


    public String getName() {return name;}

    public int getAge() {return age;}

    public int getPin() {return pin;}



    public void setName(String name){this.name = name;}

    public void setAge(int age) {this.age = age;}

    public void setPin(int pin) {this.pin = pin;}
}
