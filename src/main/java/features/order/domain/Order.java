package features.order.domain;

import features.product.domain.PublishedProduct;

import java.util.UUID;

public class Order {

    public final UUID id;
    public final UUID userId;
    public final PublishedProduct product;
    public PublishedProduct.DiscountedPrice orderedPrice;


    Order(UUID id, UUID userId, PublishedProduct product, PublishedProduct.DiscountedPrice price) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.orderedPrice = price;
    }

}
