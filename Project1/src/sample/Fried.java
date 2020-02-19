package sample;

public enum Fried {
    SMALL(2.50),
    MEDIUM(3.70),
    LARGE(4.20);

    private final double price;
    Fried(double price) {
        this.price=price;
    }

    public double getPrice() {
        return price;
    }
}
