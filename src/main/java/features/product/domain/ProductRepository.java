package features.product.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.*;

public class ProductRepository {
    public static List<Product> records = new ArrayList();

    private SqliteDatabase db = new SqliteDatabase();

    public void update2(Product product) {
        db.execute(String.format(
                "update products set userId = '%s', price = '%d', name = '%s', status = '%s' where id = '%s'",
                product.userId().toString(),
                product.price(),
                product.name(),
                product.status().toString(),
                product.id().toString()
        ));
    }

    public boolean save(Product product) {
        db.execute(String.format(
                "insert into products (id, userId, price, name, status) VALUES ('%s', '%s', %d, '%s', '%s')",
                product.id().toString(),
                product.userId().toString(),
                product.price(),
                product.name(),
                product.status()
        ));

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

    public PublishedProduct findPublishedByIdFromDb(UUID productId) {
        Records records = db.find(String.format(
                "select * from products where id = '%s'",
                productId.toString()
        ));
        if (records.size() == 0) {
            throw new RuntimeException("No Product");
        }
        Map record = (Map) records.items.get(0);
        return PublishedProduct.reconstruct(
                UUID.fromString((String) record.get("id")),
                UUID.fromString((String) record.get("userId")),
                ProductStatus.valueOf((String) record.get("status")),
                (String) record.get("name"),
                (Integer) record.get("price")
        );
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
