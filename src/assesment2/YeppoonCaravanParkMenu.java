package assesment2;
//header comments

/**
 * @author Romina Ingallina Presilla
 * @studentID 12161869
 * @subject COIT20245 - Introduction to Programming
 * @lectureTutor Bruce Mckenzie
 * @tutor Khaleel Petrus
 */
import java.util.Scanner;

//Menu class
public class YeppoonCaravanParkMenu {

    final int ENTER_BOOKING = 1;
    final int DISPLAY_BOOKINGS = 2;
    final int DISPLAY_STATISTICS = 3;
    final int SEARCH_BOOKINGS = 4;
    final int SORT_BOOKINGS = 5;
    final int EXIT = 6;

    //additional constants 
    final int MAXIMUM_BOOKING = 10; // number of allowed booking
    final int NIGHTS_FOR_DISCOUNT = 5;// minimun for promo price per booking
    final int NIGHTS_EXTENDED_FOR_DISCOUNT = 10; // condition for discount after 10 nights

    final double STANDARD_NIGHT_PRICE = 14.50; //standard price per night
    final double STANDARD_NIGHT_GUEST = 4.95; // standard price per guest per night

    final double DISCOUNTED_NIGHT_PRICE = 12.50; // price promo after 5 night
    final double DISCOUNTED_NIGHT_GUEST = 3.95; // price promo  per guest after 5 nights

    final double OVER_DISCOUNT_NIGHT_PRICE = 10.50; // price promo after 10 nights
    final double OVER_DISCOUNT_NIGHT_GUEST = 2.95; // price promo  per guest after 10 nights

    //array for booking order
    Booking[] bookings = new Booking[MAXIMUM_BOOKING];

    Scanner inText = new Scanner(System.in); 	// Scanner object for reading text
    Scanner inNumber = new Scanner(System.in);	// Scanner object for reading numbers
    
    // variable for the current order entered 
    int INDEX_BOOKING = 0;

    Scanner inputMenuChoice = new Scanner(System.in);

    private int getMenuItem() {
        System.out.println("\nPlease select from the following");
        System.out.println(ENTER_BOOKING + ". Enter booking name and number of nights and guests");
        System.out.println(DISPLAY_BOOKINGS + ". Display all booking names, number of nights and guests and charges");
        System.out.println(DISPLAY_STATISTICS + ". Display Statistics");
        System.out.println(SEARCH_BOOKINGS + ". Search for booking");
        System.out.println(SORT_BOOKINGS + ". Sort the bookings");
        System.out.println(EXIT + ". Exit the application");
        System.out.print("Enter choice==> ");

        String choice = inputMenuChoice.nextLine();
        while (choice.equals("") || !isStringNumeric(choice)) {
            System.out.println("Error - Menu selection name cannot be blank and must be numeric");

            System.out.print("Enter choice==> ");

            choice = inputMenuChoice.nextLine();
        }

        return Integer.parseInt(choice);
    }

    //Function validate if str parameter is numeric
    // and will return boolean based on validation (true/false)
    // added validation to return false if the parameter is empty
    private boolean isStringNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    //process selection 
    private void processOrders() {
        int choice = getMenuItem();
        while (choice != EXIT) {
            switch (choice) {
                case ENTER_BOOKING:
                    enterBooking();
                    break;
                case DISPLAY_BOOKINGS:
                    displayAllBookings();
                    break;
                case DISPLAY_STATISTICS:
                    displayStatistics();
                    break;
                case SEARCH_BOOKINGS:
                    searchBookings();
                    break;
                case SORT_BOOKINGS:
                    sortBookings();
                    break;
                default:
                    System.out.println("ERROR choice not recognised");
            }
            choice = getMenuItem();
        }
    }

    
    private void enterBooking() {
        String customerName = "";
        int numNights, numGuest;
        
        // checking if maximum booking has been entered
        if (INDEX_BOOKING >= MAXIMUM_BOOKING) {
            System.out.println("Error - maximun bookings to be entered has been reached");
            return;
        }

        System.out.println();
        System.out.print("Please enter booking name ==> ");
        customerName = inText.nextLine();
        while (customerName.equals("") || customerName.equals(" ")) // validation loop
        {
            System.out.println("Error - Booking name cannot be blank");
            System.out.print("Please enter booking name ==> ");
            customerName = inText.nextLine();
        }

        System.out.println();
        System.out.print("Please enter the number of nights ==> ");
        numNights = inNumber.nextInt();
        while (numNights <= 0) // validation loop
        {
            System.out.println("Error - Number of nights must be at least 1");
            System.out.print("Please enter the number of nights ==> ");
            numNights = inNumber.nextInt();
        }

        System.out.println();
        System.out.print("Please enter the number of guests ==> ");
        numGuest = inNumber.nextInt();
        while (numGuest <= 0) // validation loop
        {
            System.out.println("Error - Number of guests must be at least 1");
            System.out.print("Please enter the number of guests ==> ");
            numGuest = inNumber.nextInt();
        }

        // add bookings
        bookings[INDEX_BOOKING] = new Booking(customerName, numNights, numGuest);

        System.out.printf("\nDetails for booking %s entered\n", INDEX_BOOKING + 1);

        displayHeading();
        displayBooking(INDEX_BOOKING);

        INDEX_BOOKING++;
    }

    // displaying information once the entries were validated
    private void displayHeading() {
        System.out.printf("%-30s%-11s%-11s%-6s\n", "Booking Name", "Nights", "Guests", "Charge");
    }

    // displaying booking detailed information
    private void displayBooking(int index) {
        System.out.printf("%-30s %-11s%-11s$%-6.2f\n",
                bookings[index].getBookingName(),
                bookings[index].getNights(),
                bookings[index].getGuests(),
                bookings[index].calculateBooking());
    }

    // displaying all bookings entered so far 
    private void displayAllBookings() {

        if (INDEX_BOOKING > 0) {
            displayHeading();
            for (int i = 0; i < INDEX_BOOKING; i++) {
                displayBooking(i);
            }
        } else {
            System.out.println("There are not bookings entered yet.");
        }
    }

    // displaying statistics, validating there is at least 1 booking
    private void displayStatistics() {

        if (INDEX_BOOKING == 0) {
            System.out.println("There are not bookings entered yet.");
            return;
        }

        int minCustomer = 0, maxCustomer = 0, totalNights = 0;
        double averageNights = 0.0, totalCharges = 0.0;

        for (int i = 0; i < INDEX_BOOKING; i++) {
            if (bookings[i].getNights() < bookings[minCustomer].getNights()) {
                minCustomer = i;
            }
            if (bookings[i].getNights() > bookings[maxCustomer].getNights()) {
                maxCustomer = i;
            }
            totalNights += bookings[i].getNights();
            totalCharges += bookings[i].calculateBooking();
        }

        // print statistical information
        System.out.println("\n\nStatistics for Yeppoon Caravan Park\n");
        System.out.println(bookings[maxCustomer].getBookingName() + " has the maximum number of " + bookings[maxCustomer].getNights() + " nights");
        System.out.println(bookings[minCustomer].getBookingName() + " has the minimum number of " + bookings[minCustomer].getNights() + " nights");

        averageNights = (double) totalNights / INDEX_BOOKING;
        System.out.print("Average number of nights per booking is ");
        System.out.printf("$%.2f", averageNights);
        System.out.println(" Nights ");

        System.out.print("The total charges are $");
        System.out.printf("%.2f\n\n", totalCharges);
    }

    //method for search customer and their details
    private void searchBookings() {

        if (INDEX_BOOKING == 0) {
            System.out.println("There are not bookings entered yet.");
            return;
        }

        System.out.println();
        System.out.print("\nPlese enter the booking name ==>");
        String bookingName = inText.nextLine();

        for (int i = 0; i < INDEX_BOOKING; i++) {
            if (bookings[i].getBookingName().equalsIgnoreCase(bookingName)) {
                System.out.println("Booking found");
                displayHeading();
                displayBooking(i);
                System.out.println();
                return;
            }
        }

        System.out.printf("\n%s not found\n", bookingName);
        return;
    }

    // ordering names alphabetically 
    private void sortBookings() {
        if (INDEX_BOOKING < 1) {
            System.out.println("There are not bookings entered yet.");
            return;
        }

        int n = INDEX_BOOKING;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (bookings[j].getBookingName().compareToIgnoreCase(bookings[min_idx].getBookingName()) < 0) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first
            // element
            Booking temp = bookings[min_idx];
            bookings[min_idx] = bookings[i];
            bookings[i] = temp;
        }

        System.out.println();
        displayAllBookings();
    }

    //welcome to the programm 
    private void welcomeMessage() {

        System.out.println("Welcome to the Yeppoon Caravan Park Management System");
        System.out.println();
        System.out.println();
    }

    //exit to the programm 
    private void exitMessage() {

        System.out.println("Thank you for using the Yeppoon Caravan Park Management System");
        System.out.println("Program written by: 12161869, Romina Ingallina");
        System.out.println();
        System.out.println();
    }

    // execution of main method 
    public static void main(String[] args) {
        YeppoonCaravanParkMenu app = new YeppoonCaravanParkMenu();
        app.welcomeMessage();
        app.processOrders();
        app.exitMessage();
    }
}
