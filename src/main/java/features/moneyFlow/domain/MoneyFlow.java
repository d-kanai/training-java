package features.moneyFlow.domain;

import features.product.domain.PublishedProduct;

import java.util.UUID;

public class MoneyFlow {

    private UUID id;
    private final UUID userId;

    private final int value;

    private MoneyFlow(UUID id, UUID userId, int value) {
        if (value > 30000) throw new RuntimeException("1度に3万円までしかチャージできません");
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public UUID id() {
        return id;
    }

    public UUID userId() {
        return userId;
    }

    public static MoneyFlow charge(UUID userId, int value) {
        return new MoneyFlow(UUID.randomUUID(), userId, value);
    }

    public static MoneyFlow newByOrder(UUID loginUserId, PublishedProduct.DiscountedPrice price) {
        return new MoneyFlow(UUID.randomUUID(), loginUserId, -price.value);
    }

    public static MoneyFlow reconstruct(UUID id, UUID userId, int value) {
        return new MoneyFlow(id, userId, value);

    }

    public int value() {
        return this.value;
    }
}
