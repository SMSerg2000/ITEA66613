package less07;

public class Cashier {

    private boolean open;
    private boolean free;

    public Cashier() {
        open = true;
        free = true;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isFree() {
        return free;
    }

    public synchronized void serveCustomer(Customer customer) {
        free = false;
        if (!open) {
            System.out.println(customer.getName() + " This cashier is closed!");
            return;
        }
        System.out.println("Serving a customer " + customer.getName());
        RandomUtils.sleepRandomTime(3_000, 5_000);
        if (RandomUtils.rollTheDice(30)) {
            System.out.println("Closing...");
            open = false;
        }
        free = true;
    }
}