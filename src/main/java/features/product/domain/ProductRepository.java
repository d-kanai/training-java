package features.product.domain;

import shared.SqliteDatabase;

public class ProductRepository {
    SqliteDatabase db = new SqliteDatabase();

    public void save(Product product) {
        db.execute(String.format("insert into products (id, name, price) values ('%s', '%s', %d);",
                product.id(),
                product.name(),
                product.price()
        ));
    }

}
