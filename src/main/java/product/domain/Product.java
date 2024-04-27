package product.domain;

import product.presentation.ProductCreateInput;

import java.util.UUID;

public class Product {
    private final UUID userId;
    private final String name;
    private final int price;
    private ProductStatus status;

    public Product(UUID userId, ProductStatus status, String name, int price) {
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.price = price;
    }

    public static Product draft(UUID loginUserId, ProductCreateInput input) {
        return new Product(
                loginUserId,
                ProductStatus.DRAFT,
                input.name,
                input.price

        );
    }
}
