package features.product.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.UUID;

public class ProductRepository {
    public void save(Product product) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into products (id, name, price) values ('%s', '%s', %d);",
                product.id(),
                product.name(),
                product.price()
        ));
    }

    public Product findById(UUID productId) {
        Records records = new SqliteDatabase().find("select * from products");
        int price = (int) records.first().get("price");
        return new Product((String) records.first().get("name"), price);
    }
}
