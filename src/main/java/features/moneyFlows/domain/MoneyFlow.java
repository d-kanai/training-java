package features.moneyFlows.domain;

import features.product.domain.Product;

import java.util.UUID;

public class MoneyFlow {

    private final UUID id;
    private final int value;

    public MoneyFlow(UUID id, int value) {
        this.id = id;
        this.value = value;
    }

    public static MoneyFlow charge(int value) {
        if (value < 0) {
            throw new RuntimeException("マイナス額はチャージできません");
        }
        return new MoneyFlow(UUID.randomUUID(), value);
    }

    public UUID id() {
        return id;
    }

    public int value() {
        return value;
    }
}
