package features.order.domain;

import shared.SqliteDatabase;

public class OrderRepository {
    public void save(Order order) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into orders (id, productId) values ('%s', '%s');",
                order.id(),
                order.productId()
        ));
    }
}
