package features.order.domain;

import features.product.domain.Product;

import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID productId;

    public Order(UUID id, UUID productId) {
        this.id = id;
        this.productId = productId;
    }

    public static Order create(Product product, int availableFunds) {
        if (availableFunds < product.price()) {
            throw new RuntimeException("Insufficient funds to create order.");
        }
        return new Order(UUID.randomUUID(), product.id());
    }

    public UUID id() {
        return id;
    }

    public UUID productId() {
        return productId;
    }
}