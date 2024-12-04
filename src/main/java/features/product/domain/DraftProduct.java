package features.product.domain;

import java.util.UUID;

public class DraftProduct extends Product {

    protected DraftProduct(UUID id, String name, int price, Status status) {
        super(id, name, price, status);
        if (status == Status.PUBLISHED) throw new RuntimeException("すでに公開済みです");
    }

    //NOTE: 永続データからの再構成用途
    public static DraftProduct reconstruct(UUID id, String name, Integer price, Status status) {
        return new DraftProduct(id, name, price, status);
    }

    public static DraftProduct create(String name, int price) {
        return new DraftProduct(UUID.randomUUID(), name, price, Status.DRAFT);
    }

    public PublishedProduct publish() {
        //状態遷移 by 型交換
        return PublishedProduct.fromDraft(this);
    }
}
