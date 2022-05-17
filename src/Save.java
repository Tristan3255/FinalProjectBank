import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Save{

    public Save(){};
    public void Save(Bank bank){
        try {
            File f = new File("src/bank.data");
            f.createNewFile(); // this method will create the file if it does not exist, if it does exist, it does nothing
            FileWriter fw = new FileWriter("src/person.data");
            fw.write(bank.getUser().getName() + "\n");
            fw.write(bank.getUser().getAge() + "\n");
            fw.write(bank.getUser().getPin() + "\n");
            fw.write(bank.getBalance() + "\n");

            fw.close();
        }
        catch (IOException e) {
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
    }
}
