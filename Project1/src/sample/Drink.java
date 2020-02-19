package sample;

public enum Drink {
    COKE(2.00),
    FANTA(2.00),
    PEPSI(2.00);

    private final double price;
    Drink(double price) {
        this.price=price;
    }

    public double getPrice() {
        return price;
    }
}
