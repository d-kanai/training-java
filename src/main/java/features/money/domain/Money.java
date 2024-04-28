package features.money.domain;

import features.product.domain.Product;

import java.util.UUID;

//TODO: 入出金両方なのでMoney出ない名前な気がする
public class Money {

    public UUID id;
    public final UUID userId;
    private final int value;

    private Money(UUID id, UUID userId, int value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public static Money charge(UUID userId, int value) {
        if (value > 10000) throw new RuntimeException("1度に１万円までしかチャージできません");
        return new Money(UUID.randomUUID(), userId, value);
    }

    public static Money use(UUID loginUserId, Product product) {
        return new Money(UUID.randomUUID(), loginUserId, -product.price);

    }

    public static Money reconstruct(UUID id, UUID userId, int value) {
        return new Money(id, userId, value);

    }

    public int value() {
        return this.value;
    }
}
