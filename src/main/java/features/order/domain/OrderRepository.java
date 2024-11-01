package features.order.domain;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlows;
import features.product.domain.Product;
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

    public List<Order> findByUserIdFromDb(UUID loginUserId) {
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
