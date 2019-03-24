package io.github.msingle;

public class Movie {

    public enum Type {
        REGULAR, NEW_RELEASE, CHILDREN, UNKNOWN;
    }

    private String title;
    Price price;

    public Movie(String title, Movie.Type priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    private void setPriceCode(Movie.Type priceCode) {
        switch (priceCode) {
            case CHILDREN:
                price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case REGULAR:
                price = new RegularPrice();
                break;
                default:
                    throw new IllegalArgumentException("invalid price code");
        }
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getPoints(int daysRented) {
        return price.getPoints(daysRented);
    }
}
