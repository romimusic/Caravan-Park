package assesment2;

/**
 *
 * @author Romina Ingallina Presilla
 */
public class Booking {

    private String bookingName;
    private int nights;
    private int guests;

    //consturctor default 
    Booking() {
        bookingName = "";
        nights = 0;
        guests = 0;
    }
    
    // patametrized constructor
    Booking(String bookingName, int nights, int guests) {
        this.bookingName = bookingName;
        this.nights = nights;
        this.guests = guests;
    }


    // customerName Getter
    public String getBookingName() {
        return bookingName;
    }

    // customerName setter
    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    // night Getter
    public int getNights() {
        return nights;
    }

    // night setter
    public void setNights(int nights) {
        this.nights = nights;
    }

    // guests Getter
    public int getGuests() {
        return guests;
    }

    // guests Setter
    public void setGuests(int guests) {
        this.guests = guests;
    }

    // Method for booking  calculation
    public double calculateBooking() {

        YeppoonCaravanParkMenu main = new YeppoonCaravanParkMenu();
        double charge = 0.0;
        int normalNights, discountNights, extraNights;

        if (nights > main.NIGHTS_EXTENDED_FOR_DISCOUNT) {
            extraNights = nights - main.NIGHTS_EXTENDED_FOR_DISCOUNT;
            discountNights = main.NIGHTS_EXTENDED_FOR_DISCOUNT - main.NIGHTS_FOR_DISCOUNT;
            normalNights = main.NIGHTS_FOR_DISCOUNT;
        } else if (nights > main.NIGHTS_FOR_DISCOUNT) {
            extraNights = 0;
            discountNights = nights - main.NIGHTS_FOR_DISCOUNT;
            normalNights = main.NIGHTS_FOR_DISCOUNT;
        } else {
            extraNights = 0;
            discountNights = 0;
            normalNights = nights;
        }

        // base case
        charge = normalNights * main.STANDARD_NIGHT_PRICE + (guests * normalNights * main.STANDARD_NIGHT_GUEST);
        // case discount leve1 1
        charge += discountNights * main.DISCOUNTED_NIGHT_PRICE + (guests * discountNights * main.DISCOUNTED_NIGHT_GUEST);
        // case discount leve1 2
        charge += extraNights * main.OVER_DISCOUNT_NIGHT_PRICE + (guests * extraNights * main.OVER_DISCOUNT_NIGHT_GUEST);

        return charge;
    }

}
