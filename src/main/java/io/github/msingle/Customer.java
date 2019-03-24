package io.github.msingle;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public double getTotalCharge() {
        double total = 0;
        for (Rental rental : rentals) {
            total += rental.getCharge();
        }
        return total;
    }

    public int getTotalPoints() {
        int total = 0;
        for (Rental rental : rentals) {
            total += rental.getPoints();
        }
        return total;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    public String statement() {
        String result = "Rental record for " + getName() + "\n";
        for (Rental rental : rentals) {
            result += "\t" + rental.getLineItem() + "\n";
        }
        result += "Amount owed is " + getTotalCharge() +
                "\n" + "You earned " +
                getTotalPoints() +
                " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        String result = "<h1>Rental record for <em>" +
                getName() + "</em></h1>\n";
        for (Rental rental : rentals) {
            result += "<p>" + rental.getLineItem() +
                    "</p>\n";
        }
        result += "<p>Amount owed is <em>" +
                getTotalCharge() +"</em></p>\n" +
                "<p>You earned <em>" +
                getTotalPoints() +
                " frequent renter points</em></p>";
        return result;
    }
}
