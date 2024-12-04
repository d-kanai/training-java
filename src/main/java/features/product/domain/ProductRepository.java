package features.product.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.Map;
import java.util.UUID;

public class ProductRepository {
    SqliteDatabase db = new SqliteDatabase();

    public void save(Product product) {
        db.execute(String.format(
                "insert into products (id, name, price, status) values ('%s', '%s', %d, '%s') " +
                "ON CONFLICT(id) DO " +
                "UPDATE SET price = excluded.price, name = excluded.name, status = excluded.status;",
                product.id(),
                product.name(),
                product.price(),
                product.status()
        ));
    }

    public DraftProduct findDraftById(UUID productId) {
        Records records = db.find(String.format("select * from products where id = '%s'", productId));
        if (records.first().get("status") == Product.Status.PUBLISHED.toString()) {
            throw new RuntimeException("すでに公開済みです");
        }
        Map record = records.first();
        return DraftProduct.reconstruct(
                UUID.fromString((String) record.get("id")),
                (String) record.get("name"),
                (Integer) record.get("price"),
                Product.Status.fromString((String) record.get("status"))
        );
    }

    public PublishedProduct findPublishedById(UUID productId) {
        Records records = db.find(String.format("select * from products where id = '%s' and status = 'PUBLISHED'", productId));
        if (records.size() == 0) throw new RuntimeException("商品が存在しません");
        Map record = records.first();
        return PublishedProduct.reconstruct(
                UUID.fromString((String) record.get("id")),
                (String) record.get("name"),
                (Integer) record.get("price"),
                Product.Status.fromString((String) record.get("status"))
        );
    }
}
