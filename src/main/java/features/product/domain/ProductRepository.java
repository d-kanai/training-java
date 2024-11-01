package features.product.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.*;

public class ProductRepository {
    private SqliteDatabase db = new SqliteDatabase();

    public void update(Product product) {
        db.execute(String.format(
                "update products set price = '%d', name = '%s', status = '%s' where id = '%s'",
                product.price(),
                product.name(),
                product.status().toString(),
                product.id().toString()
        ));
    }

    public boolean create(Product product) {
        db.execute(String.format(
                "insert into products (id, userId, price, name, status) VALUES ('%s', '%s', %d, '%s', '%s')",
                product.id().toString(),
                product.userId().toString(),
                product.price(),
                product.name(),
                product.status()
        ));
        return true;
    }

    public DraftProduct findDraftById(UUID productId) {
        Records records = db.find(String.format(
                "select * from products where id = '%s'",
                productId.toString()
        ));
        if (records.size() == 0) {
            throw new RuntimeException("商品が存在しません");
        }
        Map record = (Map) records.items.get(0);
        return DraftProduct.reconstruct(
                UUID.fromString((String) record.get("id")),
                UUID.fromString((String) record.get("userId")),
                ProductStatus.valueOf((String) record.get("status")),
                (String) record.get("name"),
                (Integer) record.get("price")
        );
    }

    public PublishedProduct findPublishedById(UUID productId) {
        Records records = db.find(String.format(
                "select * from products where id = '%s'",
                productId.toString()
        ));
        if (records.size() == 0) {
            throw new RuntimeException("商品が存在しません");
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

}
