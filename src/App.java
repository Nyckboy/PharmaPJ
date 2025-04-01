import database.DataBaseManager;
import controller.MedecineController;
import controller.DisplayController;
import models.Medecine;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        DataBaseManager.createTables();
        DisplayController.start();
    }
}
