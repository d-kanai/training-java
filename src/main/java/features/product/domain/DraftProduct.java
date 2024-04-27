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

    public static DraftProduct create(UUID loginUserId, ProductCreateInput input) {
        return new DraftProduct(
                UUID.randomUUID(),
                loginUserId,
                ProductStatus.DRAFT,
                input.name,
                input.price

        );
    }

    public static DraftProduct reconstruct(Product product) {
        return new DraftProduct(
                product.id,
                product.userId,
                product.status,
                product.name,
                product.price
        );
    }

    public void publish(UUID loginUserId) {
        if (loginUserId != userId) throw new RuntimeException("商品が存在しません");
        if (status == ProductStatus.PUBLISHED) throw new RuntimeException("すでに公開済みです"); //TODO: DraftProductによって不要になったはず？
        this.status = ProductStatus.PUBLISHED;
    }

    @Override
    public DraftProduct clone() throws CloneNotSupportedException {
        return (DraftProduct) super.clone();
    }
}
