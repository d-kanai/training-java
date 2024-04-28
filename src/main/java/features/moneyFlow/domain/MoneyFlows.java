package features.moneyFlow.domain;

import features.product.domain.Product;

import java.util.List;

public class MoneyFlows {

    private final List<MoneyFlow> items;

    public MoneyFlows(List<MoneyFlow> items) {
        this.items = items;
    }

    public int currentValue() {
        return items.stream()
                .mapToInt(MoneyFlow::value)
                .sum();
    }

    public boolean hasEnoughMoney(Product product) {
        return currentValue() < product.price;
    }
}
