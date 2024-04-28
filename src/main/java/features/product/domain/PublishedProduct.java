package features.product.domain;

import java.util.UUID;

public class PublishedProduct extends Product {

    protected PublishedProduct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        super(id, userId, status, name, price);
    }

    public static PublishedProduct reconstruct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        return new PublishedProduct(
                id,
                userId,
                status,
                name,
                price
        );
    }

    @Override
    public PublishedProduct clone() throws CloneNotSupportedException {
        return (PublishedProduct) super.clone();
    }
}
