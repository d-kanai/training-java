package features.order.domain;

import features.product.domain.PublishedProduct;

import java.util.UUID;

public class Order {
    private final UUID id;
    private final UUID userId;
    private final PublishedProduct product;
    private PublishedProduct.DiscountedPrice orderedPrice;

    Order(UUID id, UUID userId, PublishedProduct product, PublishedProduct.DiscountedPrice price) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.orderedPrice = price;
    }

    public UUID id() {
        return id;
    }
    public UUID userId() {
        return userId;
    }
    public PublishedProduct product() {
        return product;
    }
    public PublishedProduct.DiscountedPrice orderedPrice() {
        return orderedPrice;
    }
}
