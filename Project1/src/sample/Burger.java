package sample;

public enum Burger {
    REGULAR(4.10),
    WITHCHEESE(4.79),
    WITHBACON(4.79),
    CHEESEANDBACON(5.20);

    private final double price;
    Burger(double price) {
        this.price = price;
    }


    public double getPrice() {
        return price;
    }
}
