package features.order;

import features.order.domain.Order;
import features.order.domain.OrderFactory;
import features.order.domain.OrderRepository;
import features.product.domain.PublishedProduct;
import features.user.domain.User;
import shared.SqliteDatabase;

import java.util.UUID;

public class OrderDataBuilder {

    private final User user;
    private final PublishedProduct product;

    private SqliteDatabase db = new SqliteDatabase();

    public OrderDataBuilder(User user, PublishedProduct product) {
        this.user = user;
        this.product = product;
    }

    public Order please() {
        Order order = OrderFactory.reconstruct(
                UUID.randomUUID(),
                user,
                product
        );
        db.execute(String.format(
                "INSERT INTO orders (id, userId, productId) VALUES ('%s', '%s', '%s')",
                order.id(),
                order.userId(),
                order.product().id()
        ));
        return order;
    }

}

