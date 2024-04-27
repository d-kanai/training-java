package features.product.presentation;

import java.util.UUID;

public class ProductPublishInput {
    public final UUID productId;

    public ProductPublishInput(UUID productId) {
        this.productId = productId;
    }
}
