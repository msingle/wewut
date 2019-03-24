package io.github.msingle;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    Customer john, steve, pat, david;
    String johnName = "John",
            steveName = "Steve",
            patName = "Pat",
            davidName = "David";
    Customer[] customers;

    @Before
    public void setup() {
        david = ObjectMother
                .customerWithNoRentals(
                        davidName
                );
        john = ObjectMother
                .customerWithOneNewRelease(
                        johnName
                );
        pat = ObjectMother
                .customerWithOneOfEachRentalType(
                        patName
                );
        steve = ObjectMother
                .customerWithOneNewReleaseAndOneRegular(
                        steveName
                );
        customers = new Customer[]{david, john, steve, pat};
    }

    @Test
    public void getName() {
        assertEquals(davidName, david.getName());
        assertEquals(johnName, john.getName());
        assertEquals(steveName, steve.getName());
        assertEquals(patName, pat.getName());
    }

    @Test
    public void davidStatement() {
        assertEquals(
                "Rental record for David\nAmount " +
                        "owed is 0.0\n" +
                        "You earned 0 frequent " +
                        "renter points",
                david.statement());

    }

    @Test
    public void johnStatement() {
        assertEquals(
                "Rental record for John\n\t" +
                        "Godfather 4\t9.0\n" +
                        "Amount owed is 9.0\n" +
                        "You earned 2 frequent renter points",
                john.statement());
    }

    @Test
    public void patStatement() {
        assertEquals(
                "Rental record for Pat\n\t" +
                        "Godfather 4\t9.0\n" +
                        "\tScarface\t3.5\n" +
                        "\tLion King\t1.5\n" +
                        "%sAmount owed is 14.0\n" +
                        "You earned 4 frequent renter points",
                pat.statement());
    }

    @Test
    public void steveStatement() {
        assertEquals(
                "Rental record for Steve\n\t" +
                        "Godfather 4\t9.0\n" +
                        "\tScarface\t3.5\n" +
                        "%sAmount owed is 12.5\n" +
                        "You earned 3 frequent renter points",
                steve.statement());
    }

    @Test
    public void htmlStatement() {
        for (int i = 0; i < customers.length; i++) {
            assertEquals(expStatement(
                    "<h1>Rental record for " +
                            "<em>%s</em></h1>\n%s" +
                            "<p>Amount owed is <em>%s</em>" +
                            "</p>\n<p>You earned <em>%s" +
                            " frequent renter points</em></p>",
                    customers[i],
                    rentalInfo(
                            "<p>", "</p>",
                            customers[i].getRentals())),
                    customers[i].htmlStatement());
        }
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
