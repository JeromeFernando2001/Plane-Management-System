import java.util.InputMismatchException;

public class Menu {
    //saving the user options in an array called options. This is a standard array
    static String[] options = {
            "  1) Buy a seat",
            "  2) Cancel a seat",
            "  3) Find first available seat",
            "  4) Show seating plan",
            "  5) Print ticket information and total sales",
            "  6) Search ticket",
            "  0) Quit"
    };

    public void displayMenu() {

        //Displaying the menu header with *
        for (int i = 1; i <= 50; i++) {
            System.out.print("*");
        }
        System.out.println("\n*                 MENU OPTIONS                   *");

        for (int i = 1; i <= 50; i++) {
            System.out.print("*");
        }
        System.out.print("\n");

        //printing the user options using an enhanced array
        for (String option : options) {
            System.out.println(option);
        }

        //printing the star line
        for (int i = 1; i <= 50; i++) {
            System.out.print("*");
        }
    }

    //Here we are getting the user valid input
    public int getUserInput() {
        int userOption;
        while (true) {
            try {
                //getting user input & assigning it to uo variable
                System.out.print("\nPlease select an option: ");
                userOption = PlaneManagement.sc.nextInt();

                //validating the user input if input in range 0-6
                while (userOption > 6) {
                    System.out.print("Please select a valid option (0-6): ");
                    userOption = PlaneManagement.sc.nextInt(); //assigning the input to uo
                }
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a valid number.");
                PlaneManagement.sc.next(); //clearing the buffer for uo
            }
        }
        //PlaneManagement.sc.close(); //close the scanner
        return userOption;
    }

    //Here we are displaying the users selected option from the options array
    public boolean userOptionProcess(int selectedOption) {
        boolean exitCode = true;
        if (selectedOption > 0 && selectedOption < options.length) {
            System.out.println("Option: " + options[selectedOption - 1]);
        } else if (selectedOption == 0) {
            System.out.println("\n***\nThank you for using our service. \nWe hope to see you again!\n***");  //we can handle the exit code from here
            exitCode = false;
        } else {
            System.out.println("Invalid option");
        }
        return exitCode;
    }

    //Here we are calling the code block to be executed for the related user selection
    public void executeOptions(int selectedOption) {
        switch (selectedOption) {
            case 1:
                boolean repeat;

                do {
                    //get user details
                    Person person = Options.getDetails();

                    //Display the available seats
                    Seats.printPlaneSeats();

                    //Get user selected seat
                    Object[] userSeats = Seats.userSelectSeat();

                    //check if seat available or not
                    boolean availability = Options.checkSeatAvailability(userSeats);
                    //buy seat
                    repeat = Options.buySeat(availability, userSeats, person);

                } while (repeat);
                break;

            case 2:
                //Get user selected seat
                Object[] userSeats = Seats.userSelectSeat();

                //boolean availability = Options.checkSeatAvailability(userSeats);
                Options.cancelSeat(userSeats);
                break;

            case 3:
                int[] findSeat = Seats.showFirstAvailableSeat();

                Options.findFirstAvailableSeat(findSeat);
                break;

            case 4:
                //creating an object of class options
                Options.showSeatingPlan();
                break;
            case 5:
                Options.printAllTickets();
                double totalPrice = Options.getTotalPrice();
                int ticketCount = Options.getTicketCount();
                System.out.println("Total Tickets: " + ticketCount);
                System.out.println("Total price: $" + totalPrice);

                break;
            case 6:
                //Get user selected seat
                Object[] searchSeat = Seats.userSelectSeat();
                Options.searchTicket(searchSeat);
                break;
        }
    }
}


