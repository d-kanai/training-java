package features.order.presentation;

import java.util.UUID;

public class OrderCreateInput {
    private final UUID productId;

    public OrderCreateInput(UUID productId) {
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }
}
