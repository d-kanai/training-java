package features.order.domain;

import shared.SqliteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderRepository {
    public static List<Order> records = new ArrayList();

    private SqliteDatabase db = new SqliteDatabase();

    public void save(Order order) {
        db.execute(String.format(
                "INSERT INTO orders (id, userId, productId) VALUES ('%s', '%s', '%s')",
                order.id(),
                order.userId(),
                order.product().id()
        ));
        records.add(order);
    }

    public List<Order> findByUserId(UUID loginUserId) {
        return records.stream().filter(order -> order.userId() == loginUserId).collect(Collectors.toList());
    }
}
