package features.product.domain;

import java.util.UUID;

public class Product {

    private final UUID id;
    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
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
