package io.github.msingle;

public abstract class Price {
    abstract double getCharge(int daysRented);

    int getPoints( int daysRented) {
        return 1;
    }
}
