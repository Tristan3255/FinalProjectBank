public class Clear {

    public Clear(){}

    public void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
