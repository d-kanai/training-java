package features.order.domain;

import features.product.domain.Product;

import java.util.UUID;

public class Order {

    public final UUID id;
    public final UUID userId;
    public final Product product;
    public final int orderedPrice;


    Order(UUID id, UUID userId, Product product, int price) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.orderedPrice = price;
    }

}
