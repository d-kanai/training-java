package features.product.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepository {
    public static List<Product> records = new ArrayList();

    public boolean save(Product product) {
        if (product.id() == null) {
            records.add(product);
        } else {
            update(product);
        }
        return true;
    }

    private void update(Product product) {
        //Arrays.asListで作成したリストが変更不可なので、listを作り直す
        ArrayList<Product> newRecords = new ArrayList<>(records);
        newRecords.removeIf(o -> o.id() == product.id());
        newRecords.add(product);
        records = newRecords;
    }

    public DraftProduct findDraftById(UUID productId) {
        // TODO: アドレス比較のせい？なのか状態遷移が引き継がれてしまっているのでclone.　DB使うようにしてしまうか
        Optional<Product> first = records.stream().filter(product -> product.id() == productId && product.status() == ProductStatus.DRAFT).findFirst();
        if (first.isPresent()) {
            try {
                Product clone = first.get().clone();
                return DraftProduct.reconstruct(
                        clone.id(),
                        clone.userId(),
                        clone.status(),
                        clone.name(),
                        clone.price()
                );
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("商品が存在しません");
        }
    }

    public PublishedProduct findPublishedById(UUID productId) {
        // TODO: アドレス比較のせい？なのか状態遷移が引き継がれてしまっているのでclone.　DB使うようにしてしまうか
        Optional<Product> first = records.stream().filter(product -> product.id() == productId && product.status() == ProductStatus.PUBLISHED).findFirst();
        if (first.isPresent()) {
            try {
                Product clone = first.get().clone();
                return PublishedProduct.reconstruct(
                        clone.id(),
                        clone.userId(),
                        clone.status(),
                        clone.name(),
                        clone.price()
                );
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("商品が存在しません");
        }

    }
}
