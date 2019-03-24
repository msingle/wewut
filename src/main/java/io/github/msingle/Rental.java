package io.github.msingle;

public class Rental {

    Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;

    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double getCharge() {
        return movie.getCharge(daysRented);
    }

    public String getLineItem() {
        return movie.getTitle() + " " + getCharge();
    }

    public int getPoints() {
        return movie.getPoints(daysRented);
    }
}
