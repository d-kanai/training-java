package product.domain;

import product.presentation.ProductCreateInput;

import java.util.UUID;

public class Product implements Cloneable {
    public final UUID id;
    private final UUID userId;
    public final String name;
    private final int price;
    public ProductStatus status;

    public Product(UUID id, UUID userId, ProductStatus status, String name, int price) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.price = price;
    }

    public static Product draft(UUID loginUserId, ProductCreateInput input) {
        return new Product(
                UUID.randomUUID(),
                loginUserId,
                ProductStatus.DRAFT,
                input.name,
                input.price

        );
    }

    public void publish() {
        this.status = ProductStatus.PUBLISHED;
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
