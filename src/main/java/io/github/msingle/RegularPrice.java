package io.github.msingle;

public class RegularPrice extends Price {

    @Override
    double getCharge(int daysRented) {
        double amount = 2;
        if (daysRented > 2) {
            amount += (daysRented - 2) * 1.5;
        }
        return amount;
    }
}
