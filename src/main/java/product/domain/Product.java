package product.domain;

import product.presentation.ProductCreateInput;

import java.util.UUID;

public class Product implements Cloneable {
    public final UUID id;
    public final UUID userId;
    public final String name;
    private final int price;
    public ProductStatus status;


    private Product(UUID id, UUID userId, ProductStatus status, String name, int price) {
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

    public void publish(UUID loginUserId) {
        if (loginUserId != userId) throw new RuntimeException("商品が存在しません");
        if (status == ProductStatus.PUBLISHED) throw new RuntimeException("すでに公開済みです");
        this.status = ProductStatus.PUBLISHED;
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
