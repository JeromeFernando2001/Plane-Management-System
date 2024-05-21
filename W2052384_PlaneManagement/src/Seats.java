import java.util.InputMismatchException;

public class Seats {

    static String[][] seatArray = {
            {"O", "O", "O", "O", "O", "O", "O", " ", "O", "O", "O", "O", "O", "O", "O"},  //Row A
            {"O", "O", "O", "O", "O", "O", "O", " ", "O", "O", "O", "O", "O"},          //Row B
            {"O", "O", "O", "O", "O", "O", "O", " ", "O", "O", "O", "O", "O"},          //Row c
            {"O", "O", "O", "O", "O", "O", "O", " ", "O", "O", "O", "O", "O", "O", "O"}  //Row D
    };

    public static void printPlaneSeats() {

        //Here we are printing the headers
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t\t8\t9\t10\t11\t12\t13\t14");

        //Here printing the jagged array(seatArray)
        char rowLabel = 'A';

        for (String[] strings : seatArray) {
            System.out.print(rowLabel++ + "\t"); //printing the row label
            for (String string : strings) {
                System.out.print(string + "\t");
            }
            System.out.println();
        }

        //Displaying the seat details
        System.out.println("\n\tFirst Class     \tEconomy       \tSecond Class");
        System.out.println("\tA1-D5             \tA6-D9         \tA10-D14");
        System.out.println("\t$200              \t$150          \t$180");
    }

    public static Object[] userSelectSeat() {
        char row = ' '; //to get the letter from the user
        int rowNum = 0; //to get the row number
        int seatNum = 0; //to get the seat number
        //to validate the user entered letter
        char[] row_letters = {'A', 'B', 'C', 'D'};

        try {
            boolean validRow = false;
            while (!validRow) {
                System.out.print("\nEnter Row Letter [A,B,C,D]: ");
                //Taking the users input
                String input = PlaneManagement.sc.next().toUpperCase();
                //Check if input is a char
                if (input.length() == 1) {
                    row = input.charAt(0);
                    //check if the input row letter is one of the valid row options
                    for (char letter : row_letters) {
                        if (row == letter) {
                            validRow = true;
                            break; //exiting loop when input is valid
                        }
                    }
                    if (!validRow) {
                        System.out.println("Enter a valid row letter.");
                    }

                        /*
                        Here I am doing is, for the selected character
                        assigning an Integer to rowNum this is an enhanced switch
                        case statement (ref. quick access reformatted the code)
                        */
                    rowNum = switch (row) {
                        case 'A' -> 1;
                        case 'B' -> 2;
                        case 'C' -> 3;
                        case 'D' -> 4;
                        default -> -1; //any invalid code
                    };
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!");
            PlaneManagement.sc.next();
        }

        while (true) {
            try {
                boolean validSeat = false;
                while (!validSeat) {
                    System.out.print("\nEnter Seat number [1-14]: ");
                    seatNum = PlaneManagement.sc.nextInt();

                    if ((seatNum >= 8) && (seatNum <= 14)) {
                        seatNum += 1;
                        validSeat = true;
                        break;
                    } else if ((seatNum > 0) && (seatNum < 15)) {
                        validSeat = true;
                        break;
                    } else {
                        System.out.println("Enter valid seat number");
                        validSeat = false;

                    }
                }

                // Check if the seat is not one of the restricted seats
                if ((rowNum == 2 && (seatNum - 1 == 13 || seatNum - 1 == 14)) || (rowNum == 3 && (seatNum - 1 == 13 || seatNum - 1 == 14))) {
                    System.out.println("Seat not available for this row and seat number. Please select another one.");
                    // Prompt the user to enter another row and seat number
                    return userSelectSeat();
                }

                if (seatNum >= 8) {
                    System.out.println("Selected seat: " + row + (seatNum - 1));
                } else {
                    System.out.println("Selected seat: " + row + (seatNum));
                }
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!");
                PlaneManagement.sc.next();
            }
        }
        Object[] result = new Object[3];
        result[0] = rowNum;
        result[1] = seatNum;
        result[2] = row;
        return result;
    }

    //show seating
    public static void copySeatArray() {

        //Here we are printing the headers
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t\t8\t9\t10\t11\t12\t13\t14");

        //Here printing the jagged array(seatArray)
        char rowLabel = 'A';

        for (int i = 0; i < seatArray.length; i++) {
            System.out.print(rowLabel++ + "\t"); //printing the row label
            for (int j = 0; j < seatArray[i].length; j++) {
                if (seatArray[i][j].equals("1")) {
                    //Change the value to "X"
                    seatArray[i][j] = "X";
                }
                System.out.print(seatArray[i][j] + "\t");
            }
            System.out.println();
        }

    }

    public static int[] showFirstAvailableSeat() {
        // Iterate over each row of the seatArray
        for (int i = 0; i < seatArray.length; i++) {
            // Iterate over each seat in the current row
            for (int j = 0; j < seatArray[i].length; j++) {
                // Check if the current seat is available (indicated by "O")
                if (seatArray[i][j].equals("O")) {
                    // Return the indices of the first available seat
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
