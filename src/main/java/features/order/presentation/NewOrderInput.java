package features.order.presentation;

import java.util.UUID;

public class NewOrderInput {
    public final UUID productId;

    public NewOrderInput(UUID productId) {
        this.productId = productId;
    }
}
