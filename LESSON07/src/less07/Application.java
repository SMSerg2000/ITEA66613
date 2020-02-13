package less07;

public class Application {

    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket();
        for (int i = 1; i < 6; i++) {
            RandomUtils.sleepRandomTime(300, 500);
            Customer customer = new Customer("customer-" + i, supermarket);
            new Thread(customer).start();
        }
    }
}