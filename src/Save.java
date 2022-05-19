import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Save{

    public Save(){};

    public void Save(ArrayList<Bank> BANKS){
            try {
                // this method will create the file if it does not exist, if it does exist, it does nothing
                FileWriter fw = new FileWriter("src/person.data");
                    for (int i = 0; i < BANKS.size(); i++) {
                        fw.write(BANKS.get(i).getUser().getName() + "|");
                        fw.write(BANKS.get(i).getUser().getAge() + "|");
                        fw.write(BANKS.get(i).getPin() + "|");
                        fw.write(BANKS.get(i).getBalance() + "");
                        fw.write("\n");
                    }
                fw.close();
            }
            catch (IOException e) {
                System.out.println("Unable to create file");
                e.printStackTrace();
            }
    }


    public ArrayList<Bank> Load() {
        try {
            ArrayList<Bank> Users = new ArrayList<Bank>();
            File myFile = new File("src/person.data");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] userInfo = data.split("\\|");
                Person p = new Person(userInfo[0], Integer.parseInt(userInfo[1]));
                Bank b = new Bank(p, Integer.parseInt(userInfo[2]), Double.parseDouble(userInfo[3]));
                Users.add(b);
            }
            return Users;

        } catch (FileNotFoundException e) {
            ArrayList<Bank> Users = new ArrayList<Bank>();
            e.printStackTrace();
        }
        return null;
    }


}
