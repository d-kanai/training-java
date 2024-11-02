package features.order.domain;

import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID productId;

    private Order(UUID id, UUID productId) {
        this.id = id;
        this.productId = productId;
    }

    public static Order create(UUID productId) {
        return new Order(UUID.randomUUID(), productId);
    }

    public UUID id() {
        return id;
    }

    public UUID productId() {
        return productId;
    }
}
