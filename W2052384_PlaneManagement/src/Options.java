import java.util.InputMismatchException;
import java.util.Objects;

public class Options {
    //Here I am taking a standard array assuming a maximum of 100 tickets
    private static final Ticket[] tickets = new Ticket[100];
    private static double totalPrice = 0;
    private static int ticketCount = 0;

    public static boolean checkSeatAvailability(Object[] userSeats) {
        Object row = userSeats[0];
        Object seat = userSeats[1];

        //checking if seat is available for booking
        boolean available = false;

        if (Objects.equals(Seats.seatArray[(int) row - 1][(int) seat - 1], "O")) {
            available = true;
            System.out.println("Seat is available. \n");

        } else {
            System.out.println("Seat already taken.");
        }
        return available;
    }

    //Method to Buy a seat
    public static boolean buySeat(boolean available, Object[] userSeats, Person person) {
        int row = (int) userSeats[0] - 1;
        int seat = (int) userSeats[1];
        int displaySeat = seat;
        String choice;
        boolean repeat = false;
        char rowLetter;

        //adjusting the seat to match the element of the seat array
        if (displaySeat >= 8) {
            displaySeat -= 1;
        }

        //converting row number to match row letter
        rowLetter = switch (row) {
            case 0 -> 'A';
            case 1 -> 'B';
            case 2 -> 'C';
            case 3 -> 'D';
            default -> ' ';
        };

        if (available) {
            //creating the new ticket
            Ticket ticket = new Ticket(rowLetter, displaySeat, person);

            //Adding the ticket to the array
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] == null) {
                    tickets[i] = ticket;
                    break;
                }
            }

            //Updating total price and ticket count
            totalPrice += ticket.getPrice();
            ticketCount++;

            //Printing ticket info
            ticket.printTicketInfo();

            //Buying the seat in array
            Seats.seatArray[row][seat - 1] = "1";
            System.out.println("Seat Booked!");

            //Save Ticket
            //Ticket saveTicket = new Ticket(rowLetter, displaySeat);
            ticket.saveTicket();
        }


        while (true) {
            try {
                System.out.print("\nBuy another seat? [yes/no]: ");
                choice = PlaneManagement.sc.next().toLowerCase();

                if (choice.equals("yes")) {
                    repeat = true;
                    break;
                } else if (choice.equals("no")) {
                    break;
                } else {
                    System.out.print("Please enter yes or no.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                PlaneManagement.sc.next();
            }
        }
        return repeat;
    }

    //Method to get user details
    public static Person getDetails() {

        System.out.println("Please enter below details");
        System.out.println("--------------------------------");

        String firstname;
        String surname;
        String email;

        //clearing input buffer
        PlaneManagement.sc.nextLine();
        System.out.print("First name: ");
        firstname = PlaneManagement.sc.nextLine();

        System.out.print("Surname: ");
        surname = PlaneManagement.sc.nextLine();

        System.out.print("Email: ");
        email = PlaneManagement.sc.nextLine();

        //Creating a Person Object
        return new Person(firstname, surname, email);
    }

    //Method to print all the ticket information
    public static void printAllTickets() {
        System.out.println("All ticket information");
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.printTicketInfo();
            }
        }
    }

    //Method to get total price
    public static double getTotalPrice() {
        return totalPrice;
    }

    //Method to get ticket count
    public static int getTicketCount() {
        return ticketCount;
    }

    //Method to Cancel a Seat
    public static void cancelSeat(Object[] userSeats) {
        int row = (int) userSeats[0];
        int seat = (int) userSeats[1];
        char rowName = (char) userSeats[2];

        for (int i = 0; i < tickets.length; i++) {
            Ticket ticket = tickets[i];
            if ((ticket != null) && (ticket.getRow() == rowName) && (ticket.getSeat() == seat)) {
                //Cancel the ticket by setting the array element to null
                tickets[i] = null;

                //Updating total price and ticket count
                totalPrice -= ticket.getPrice();
                ticketCount--;

                //update the seats array
                Seats.seatArray[row - 1][seat - 1] = "O";
                System.out.println("Seat " + rowName + seat + " is cancelled.");
                return; //Exit the method after cancelling the ticket
            }
        }
        System.out.println("This seat has not been booked. You can not cancel a seat that is not booked.");

    }

    //Method to show the seating plan
    public static void showSeatingPlan() {
        Seats.copySeatArray();
        System.out.println();
    }

    //Method to find the first available seat
    public static void findFirstAvailableSeat(int[] findSeat) {
        int row = findSeat[0];
        int col = findSeat[1];
        char rowLetter;

        rowLetter = switch (row) {
            case 0 -> 'A';
            case 1 -> 'B';
            case 2 -> 'C';
            case 3 -> 'D';
            default -> ' ';
        };
        System.out.println("\nSeat at: " + rowLetter + (col + 1) + " is the first seat available.\n");
    }

    //Method to search for a ticket
    public static void searchTicket(Object[] userSearchedSeat) {
        int seat = (int) userSearchedSeat[1];
        char rowLetter = (char) userSearchedSeat[2];
        boolean foundTicket = false;
        int displaySeat = seat;

        //adjusting the seat to match the element of the seat array
        if (displaySeat >= 8) {
            displaySeat -= 1;
        }

        //looping through the array searching for a matching row and seat
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == displaySeat) {
                System.out.println("Ticket found!");
                ticket.printTicketInfo();
                foundTicket = true;
                break;
            }
        }

        //If ticket is not found
        if (!foundTicket) {
            System.out.println("Ticket not found for Row " + rowLetter + " and seat " + displaySeat);
        }
    }
}
