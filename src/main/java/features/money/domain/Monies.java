package features.money.domain;

import features.product.domain.Product;

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

    public boolean hasEnoughMoney(Product product) {
        return totalValue() < product.price;
    }
}
