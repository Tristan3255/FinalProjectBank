import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Save{

    public Save(){};

    public void Save(ArrayList<Bank> BANKS){
            try {
                // this method will create the file if it does not exist, if it does exist, it does nothing
                FileWriter fw = new FileWriter("src/person.data");
                for(int i = 0; i < BANKS.size(); i++) {
                    fw.write(BANKS.get(i).getUser().getName() + "\n");
                    fw.write(BANKS.get(i).getUser().getAge() + "\n");
                    fw.write(BANKS.get(i).getUser().getPin() + "\n");
                    fw.write(BANKS.get(i).getBalance() + "\n");
                }

                fw.close();
            }
            catch (IOException e) {
                System.out.println("Unable to create file");
                e.printStackTrace();
            }
        }

}
