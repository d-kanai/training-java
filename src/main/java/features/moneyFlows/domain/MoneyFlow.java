package features.moneyFlows.domain;

import features.product.domain.Product;

import java.util.UUID;

public class MoneyFlow {

    private final UUID id;
    private final int value;

    public MoneyFlow(int value) {
        this.id = UUID.randomUUID();
        this.value = value;
    }

    public static MoneyFlow charge(int value) {
        if (value < 0) {
            throw new RuntimeException("マイナス額はチャージできません");
        }
        return new MoneyFlow(value);
    }

    public UUID id() {
        return id;
    }

    public int value() {
        return value;
    }
}
