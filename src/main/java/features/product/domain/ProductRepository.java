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

    public Product findById(UUID productId) {
        Records records = db.find(String.format("select * from products where id = '%s'", productId));
        Map record = records.first();
        return Product.reconstruct(
                UUID.fromString((String) record.get("id")),
                (String) record.get("name"),
                (Integer) record.get("price"),
                Product.Status.fromString((String) record.get("status"))
        );
    }
}
