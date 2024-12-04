package features.product.domain;

import java.util.UUID;

public class PublishedProduct extends Product {

    protected PublishedProduct(UUID id, String name, int price, Status status) {
        super(id, name, price, status);
        if (status != Status.PUBLISHED) throw new RuntimeException("invalid state");
    }

    //NOTE: 永続データからの再構成用途
    public static PublishedProduct reconstruct(UUID id, String name, Integer price, Status status) {
        return new PublishedProduct(id, name, price, status);
    }

    public static PublishedProduct fromDraft(DraftProduct draftProduct) {
        return new PublishedProduct(draftProduct.id, draftProduct.name, draftProduct.price, Status.PUBLISHED);
    }
}
