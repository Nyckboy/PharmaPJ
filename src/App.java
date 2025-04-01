import database.DataBaseManager;
import controller.DisplayController;

public class App {
    public static void main(String[] args) throws Exception {
        DataBaseManager.createTables();
        DisplayController.start();
    }
}
