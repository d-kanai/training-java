package features.product.presentation;

import java.util.UUID;

public class ProductPublishInput {
    private final UUID id;

    public ProductPublishInput(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
