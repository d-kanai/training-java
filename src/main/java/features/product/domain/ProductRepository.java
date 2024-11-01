package features.product.domain;

import features.product.presentation.ProductCreateInput;
import shared.SqliteDatabase;

import java.util.UUID;

public class ProductRepository {
    public void save(ProductCreateInput input) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into products (id, name, price) values ('%s', '%s', %d);", UUID.randomUUID(), input.name, input.price));
    }
}
