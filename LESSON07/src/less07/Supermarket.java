package less07;

import java.util.ArrayList;
import java.util.List;

public class Supermarket {

    private static final int CASHIERS_COUNT = 1; //Количество касс

    private List<Cashier> cashiers;

    public Supermarket() {
        cashiers = new ArrayList<>();
        for (int i = 0; i < CASHIERS_COUNT; i++) {
            cashiers.add(new Cashier());
        }
    }

    public Cashier getFreeCashier() {
        if (cashiers.stream().noneMatch(Cashier::isOpen)) {
            throw new IllegalStateException("Все кассы закрыты!");
        }
        return cashiers.stream()
                .filter(Cashier::isOpen)
                .filter(Cashier::isFree)
                .findAny()
                .orElse(null);
    }
}