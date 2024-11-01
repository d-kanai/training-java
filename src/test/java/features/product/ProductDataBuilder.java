package features.product;

import features.product.domain.*;

import java.util.UUID;

public class ProductDataBuilder {

    private final UUID userId;

    public ProductDataBuilder(UUID userId) {
        this.userId = userId;
    }

    public DraftProduct pleaseAsDraft() {
        DraftProduct product = DraftProduct.reconstruct(
                UUID.randomUUID(),
                userId,
                ProductStatus.DRAFT,
                "book",
                1000
        );
        new ProductRepository().create(product);
        return product;
    }

    public PublishedProduct pleaseAsPublished() {
        PublishedProduct product = PublishedProduct.reconstruct(
                UUID.randomUUID(),
                userId,
                ProductStatus.PUBLISHED,
                "book",
                1000
        );
        new ProductRepository().create(product);
        return product;
    }

}

