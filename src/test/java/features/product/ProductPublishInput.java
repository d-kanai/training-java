package features.product;

import product.presentation.ProductCreateInput;

import java.util.UUID;

public class ProductPublishInput {
    public final UUID productId;

    public ProductPublishInput(UUID productId) {
        this.productId = productId;
    }
}
