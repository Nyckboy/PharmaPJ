package controller;

public class DisplayController {
    public static void menuDisplay(){
        clearScreen();
        System.out.println("\nPharmacy Management System");
        System.out.println("1. Add Medicine");
        System.out.println("2. View Medicines");
        System.out.println("3. Edit all Medecine");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public static final void gotoXY(int x, int y){
        char escCode = 0x1B;
        // int row = 10; int column = 5;
        System.out.print(String.format("%c[%d;%df",escCode,y,x));
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static void main(String[] args) {
        clearScreen();
        gotoXY(10, 5);
        System.out.print("test");
    }
}



