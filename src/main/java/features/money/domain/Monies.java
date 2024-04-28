package features.money.domain;

import java.util.List;

public class Monies {

    private final List<Money> items;

    public Monies(List<Money> items) {

        this.items = items;
    }

    public int totalValue() {
        return items.stream()
                .mapToInt(Money::value)
                .sum();
    }
}
