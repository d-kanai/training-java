package features.product.presentation;

import java.util.UUID;

public class ProductPurchaseInput {
    public final UUID productId;

    public ProductPurchaseInput(UUID productId) {
        this.productId = productId;
    }
}
