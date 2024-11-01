package features.product.domain;

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
}
