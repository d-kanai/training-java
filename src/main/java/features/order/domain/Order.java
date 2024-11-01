package features.order.domain;

import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID productId;

    public Order(UUID productId) {
        this.id = UUID.randomUUID();
        this.productId = productId;
    }

    public UUID id() {
        return id;
    }

    public UUID productId() {
        return productId;
    }
}
