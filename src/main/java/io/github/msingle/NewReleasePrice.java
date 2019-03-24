package io.github.msingle;

public class NewReleasePrice extends Price {

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getPoints(int daysRented) {
        if (daysRented > 1) {
            return 2;
        }
        return 1;
    }
}
