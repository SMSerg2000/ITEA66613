package less07;

public class Supermarket {

    private Cashier cashiers;

    public Supermarket() {
        cashiers = new Cashier();
    }

    public Cashier getFreeCashier() {
        if (!cashiers.isOpen()) {
            throw new IllegalStateException();
        }
        return cashiers;
    }
}