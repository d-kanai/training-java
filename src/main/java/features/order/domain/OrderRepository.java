package features.order.domain;

import features.product.domain.ProductStatus;
import features.product.domain.PublishedProduct;
import features.user.domain.User;
import features.user.domain.UserPlan;
import shared.Records;
import shared.SqliteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderRepository {
    private SqliteDatabase db = new SqliteDatabase();

    public void create(Order order) {
        db.execute(String.format(
                "INSERT INTO orders (id, userId, productId) VALUES ('%s', '%s', '%s')",
                order.id(),
                order.userId(),
                order.product().id()
        ));
    }

    public List<Order> findByUserId(UUID loginUserId) {
        Records records = db.find(String.format(
                "select * from orders " +
                        "join users on users.id = orders.userId " +
                        "join products on products.id = orders.productId; ",
                loginUserId
        ));
        List<Order> list = new ArrayList<>();
        records.items.forEach(record -> {
            Map recordAsMap = (Map) record;
            list.add(OrderFactory.reconstruct(
                            UUID.fromString((String) recordAsMap.get("id")),
                            User.reconstruct(
                                    UUID.fromString((String) recordAsMap.get("id")),
                                    (String) recordAsMap.get("email"),
                                    UserPlan.fromString((String) recordAsMap.get("memberShip"))
                            ),
                            PublishedProduct.reconstruct(
                                    UUID.fromString((String) recordAsMap.get("id")),
                                    UUID.fromString((String) recordAsMap.get("id")),
                                    ProductStatus.PUBLISHED,
                                    (String) recordAsMap.get("name"),
                                    (Integer) recordAsMap.get("price")

                            )
                    )
            );
        });
        return list;
    }
}
