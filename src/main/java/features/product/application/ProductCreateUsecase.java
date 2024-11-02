package features.product.application;

import features.product.presentation.ProductCreateInput;
import shared.SqliteDatabase;

import java.util.UUID;

public class ProductCreateUsecase {

    public void run(ProductCreateInput input) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into products (id, name, price) values ('%s', '%s', %d);",
                UUID.randomUUID(),
                input.name,
                input.price
        ));
    }
}
