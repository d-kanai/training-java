package features.product.domain;

import features.user.domain.UserPlan;

import java.util.UUID;

public class Product implements Cloneable {
    private final UUID id;
    private final UUID userId;
    private final String name;
    private final int price;
    private ProductStatus status;

    public UUID id() {
        return id;
    }

    public UUID userId() {
        return userId;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }

    public ProductStatus status() {
        return status;
    }

    protected Product(UUID id, UUID userId, ProductStatus status, String name, int price) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.price = price;
    }



    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
