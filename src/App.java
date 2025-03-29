import database.DataBaseManager;
import controller.MedecineController;
import controller.DisplayController;
import models.Medecine;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DataBaseManager.createTables();
        int i = 0;

        while (true) {
            // System.out.println("\nPharmacy Management System");
            // System.out.println("1. Add Medicine");
            // System.out.println("2. View Medicines");
            // System.out.println("3. Edit all Medecine");
            // System.out.println("4. Exit");
            // System.out.print("Choose an option: ");
            DisplayController.menuDisplay();
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    MedecineController.addMedecine("test22", "cattest", 123, 12, "11-11-1111");
                    break;
            
                case 2:
                    DisplayController.clearScreen();
                    MedecineController.getAllMedecines().forEach(System.out::println);
                    scanner.nextLine();
                    break;
                case 3:
                    // MedecineController.getAllMedecines().forEach(medecine -> 
                    //     MedecineController.updateMedecine(medecine)
                    // );
                    List<Medecine> medecines = MedecineController.getAllMedecines(); 
                    medecines.forEach(medecine -> medecine.setName("test" + i));
                    medecines.forEach(System.out::println);
                    medecines.forEach(medecine -> MedecineController.updateMedecine(medecine));
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
