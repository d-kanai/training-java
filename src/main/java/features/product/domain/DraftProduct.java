package features.product.domain;

import features.product.presentation.ProductCreateInput;

import java.util.UUID;

public class DraftProduct extends Product {

    private DraftProduct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        super(
                id,
                userId,
                status,
                name,
                price
        );
    }

    public static DraftProduct newDraft(UUID loginUserId, ProductCreateInput input) {
        return new DraftProduct(
                UUID.randomUUID(),
                loginUserId,
                ProductStatus.DRAFT,
                input.name,
                input.price

        );
    }

    public static DraftProduct reconstruct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        return new DraftProduct(
                id,
                userId,
                status,
                name,
                price
        );
    }

    public Product publish(UUID loginUserId) {
        if (loginUserId != userId) throw new RuntimeException("商品が存在しません");
        return new Product(
                this.id,
                this.userId,
                ProductStatus.PUBLISHED,
                this.name,
                this.price
        );
    }

    @Override
    public DraftProduct clone() throws CloneNotSupportedException {
        return (DraftProduct) super.clone();
    }
}
