package features.product.domain;


import features.money.domain.Money;
import features.money.domain.Monies;

import java.util.UUID;

public class Product implements Cloneable {
    public final UUID id;
    public final UUID userId;
    public final String name;
    public final int price;
    public ProductStatus status;


    protected Product(UUID id, UUID userId, ProductStatus status, String name, int price) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.price = price;
    }

    public static Product reconstruct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        return new Product(
                id,
                userId,
                status,
                name,
                price
        );
    }


    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }

    public Money purchase(UUID loginUserId, Monies monies) {
        if (monies.hasEnoughMoney(this)) throw new RuntimeException("チャージ残高が足りません");
        return Money.use(loginUserId, this);
    }
}
