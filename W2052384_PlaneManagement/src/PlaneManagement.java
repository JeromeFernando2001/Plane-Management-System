import java.util.Scanner;

public class PlaneManagement {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Welcome message
        System.out.println("-- Welcome to the Plane Management Application --");

        boolean quit;

        do{
            //Display the menu
            Menu menu = new Menu();
            menu.displayMenu();
            //Here we get the users input (1-6)
            int userInput = menu.getUserInput();
            //Here we are displaying the users selected option from the array
            quit = menu.userOptionProcess(userInput);
            //Here we are passing the user input to execute the options via switch-case block
            menu.executeOptions(userInput);
        }while (quit);

       // System.out.println("We came here");
    }
}