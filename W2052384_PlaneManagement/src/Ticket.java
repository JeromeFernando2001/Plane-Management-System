import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    public Ticket() {

    }

    public Ticket(char rowLetter, int displaySeat) {
        this.row = rowLetter;
        this.seat = displaySeat;
    }

    //Constructor
    public Ticket(char row, int seat, Person person) {
        this.row = row;
        this.seat = seat;
        this.person = person;

        //setting the price for the selected seat
        if (row >= 'A' && row <= 'D') {
            if (seat >= 1 && seat <= 5) {
                this.price = 200.0; //First class
            } else if (seat >= 6 && seat <= 9) {
                this.price = 150.0; // Economy                
            } else if (seat >= 10 && seat <= 14) {
                this.price = 180.0; // Second Class
            }
        }
    }

    //Getters and Setters
    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //Method to print ticket information
    public void printTicketInfo() {
        System.out.println("Person Information:");
        person.printPersonInfo();

        System.out.println("\nTicket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: $" + price);
        System.out.println("------------------------------");
    }

    public void saveTicket() {
        String fileName = row + String.valueOf(seat) + ".txt";

        try {
            FileWriter myWritter = getFileWriter(fileName);
            myWritter.close();

            // System.out.println("Write success");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file " + fileName + "\n");
        }
    }

    private FileWriter getFileWriter(String fileName) throws IOException {
        FileWriter myWritter = new FileWriter(fileName);

        myWritter.write("Person Information\n");
        myWritter.write("---------------------------------\n");
        myWritter.write(person.getPersonInfo());
        myWritter.write("\n");

        myWritter.write("Ticket Information\n");
        myWritter.write("---------------------------------\n");
        myWritter.write("Seat Booked: " + "Row " + row + " Seat " + seat + "\n");
        myWritter.write("Price: $" + price + "\n");

        myWritter.write("\n=== This Ticket has been auto-generated ===\n");
        return myWritter;
    }
}
