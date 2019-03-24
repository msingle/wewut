package io.github.msingle;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void noRentalsStatement() {
        assertEquals(
                "Rental record for David\nAmount " +
                        "owed is 0.0\n" +
                        "You earned 0 frequent " +
                        "renter points",
                ObjectMother.customerWithNoRentals("David").statement());

    }

    @Test
    public void oneNewReleaseStatement() {
        assertEquals(
                "Rental record for John\n\t" +
                        "Godfather 4\t9.0\n" +
                        "Amount owed is 9.0\n" +
                        "You earned 2 frequent renter points",
                ObjectMother.customerWithOneNewRelease("John").statement());
    }

    @Test
    public void allRentalTypesStatement() {
        assertEquals(
                "Rental record for Pat\n\t" +
                        "Godfather 4\t9.0\n" +
                        "\tScarface\t3.5\n" +
                        "\tLion King\t1.5\n" +
                        "%sAmount owed is 14.0\n" +
                        "You earned 4 frequent renter points",
                ObjectMother.customerWithOneOfEachRentalType("Pat").statement());
    }

    @Test
    public void newReleaseAndRegularStatement() {
        assertEquals(
                "Rental record for Steve\n\t" +
                        "Godfather 4\t9.0\n" +
                        "\tScarface\t3.5\n" +
                        "%sAmount owed is 12.5\n" +
                        "You earned 3 frequent renter points",
                ObjectMother.customerWithOneNewReleaseAndOneRegular("Steve").statement());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTitle() {
        ObjectMother.customerWithNoRentals("Bob")
                .addRental(
                        new Rental(
                                new Movie("Crazy, Stupid, Love.",
                                        Movie.Type.UNKNOWN),
                                4));
    }

    public static String rentalInfo(
            String startsWith,
            String endswith,
            List<Rental> rentals) {
        String result = "";
        for (Rental rental : rentals) {
            result += String.format(
                    "%s%s\t%s%s\n",
                    startsWith,
                    rental.getMovie().getTitle(),
                    rental.getCharge(),
                    endswith);
        }
        return result;
    }

    public static String expStatement(
            String formatStr,
            Customer customer,
            String rentalInfo) {
        return String.format(
                formatStr,
                customer.getName(),
                rentalInfo,
                customer.getTotalCharge(),
                customer.getTotalPoints());
    }
}
