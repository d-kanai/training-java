package product.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepository {
    public static List<Product> records = new ArrayList();

    public boolean save(Product product) {
        if (product.id == null) {
            records.add(product);
        } else {
            update(product);
        }
        return true;
    }

    private void update(Product product) {
        //Arrays.asListで作成したリストが変更不可なので、listを作り直す
        ArrayList<Product> newRecords = new ArrayList<>(records);
        newRecords.removeIf(o -> o.id == product.id);
        newRecords.add(product);
        records = newRecords;
    }

    public Product findById(UUID loginUserId, UUID productId) {
        // TODO: アドレス比較のせい？なのか状態遷移が引き継がれてしまっているのでclone.　DB使うようにしてしまうか
        Optional<Product> first = records.stream().filter(product -> product.id == productId).findFirst();
        if (first.isPresent()) {
            try {
                return first.get().clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("商品が存在しません");
        }
    }
}
