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
        assertEquals(expStatement(
                "Rental record for %s\n" +
                        "%sAmount owed is %s\n" +
                        "You earned %s frequent " +
                        "renter points",
                david,
                rentalInfo(
                        "\t", "",
                        david.getRentals())),
                david.statement());

    }

    @Test
    public void johnStatement() {
        assertEquals(expStatement(
                "Rental record for %s\n" +
                        "%sAmount owed is %s\n" +
                        "You earned %s frequent " +
                        "renter points",
                john,
                rentalInfo(
                        "\t", "",
                        john.getRentals())),
                john.statement());
    }

    @Test
    public void patStatement() {
        assertEquals(expStatement(
                "Rental record for %s\n" +
                        "%sAmount owed is %s\n" +
                        "You earned %s frequent " +
                        "renter points",
                pat,
                rentalInfo(
                        "\t", "",
                        pat.getRentals())),
                pat.statement());
    }

    @Test
    public void steveStatement() {
        assertEquals(expStatement(
                "Rental record for %s\n" +
                        "%sAmount owed is %s\n" +
                        "You earned %s frequent " +
                        "renter points",
                steve,
                rentalInfo(
                        "\t", "",
                        steve.getRentals())),
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
