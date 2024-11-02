package features.product.domain;

import java.util.UUID;

public class Product {

    private final UUID id;
    private final String name;
    private final int price;

    private Product(UUID id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //NOTE: 永続データからの再構成用途
    public static Product reconstruct(UUID id, String name, Integer price) {
        return new Product(id, name, price);
    }

    public static Product create(String name, int price) {
        if (price < 0) {
            throw new RuntimeException("マイナスは登録できません");
        }
        return new Product(UUID.randomUUID(), name, price);
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }
}
