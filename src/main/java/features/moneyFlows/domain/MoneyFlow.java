package features.moneyFlows.domain;

import features.product.domain.Product;

import java.util.UUID;

public class MoneyFlow {

    private final UUID id;
    private final int value;

    private MoneyFlow(UUID id, int value) {
        this.id = id;
        this.value = value;
    }

    public static MoneyFlow charge(int value) {
        if (value < 0) {
            throw new RuntimeException("マイナス額はチャージできません");
        }
        return MoneyFlow.create(value);
    }

    private static MoneyFlow create(int value) {
        return new MoneyFlow(UUID.randomUUID(), value);
    }

    public static MoneyFlow order(Product product) {
        return MoneyFlow.create(-product.price());
    }

    public static MoneyFlow reconstruct(UUID id, int value) {
        return new MoneyFlow(id, value);
    }

    public UUID id() {
        return id;
    }

    public int value() {
        return value;
    }
}
