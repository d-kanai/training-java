package features.order.domain;

import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID productId;

    public Order(UUID id, UUID productId) {
        this.id = id;
        this.productId = productId;
    }

    public UUID id() {
        return id;
    }

    public UUID productId() {
        return productId;
    }
}
