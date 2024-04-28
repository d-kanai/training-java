package features.order;

import features.order.domain.Order;
import features.order.domain.OrderFactory;
import features.order.domain.OrderRepository;
import features.product.domain.PublishedProduct;
import features.user.domain.User;

import java.util.UUID;

public class OrderDataBuilder {

    private final User user;
    private final PublishedProduct product;

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
        OrderRepository.records.add(order);
        return order;
    }

}

