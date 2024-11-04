package features.order.domain;

import features.product.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID productId;
    private final UUID userId;
    public List<DomainEvent> domainEvents = new ArrayList<>();

    private Order(UUID id, UUID userId, UUID productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    static Order create(UUID userId, Product product) {
        Order order = new Order(UUID.randomUUID(), userId, product.id());
        order.domainEvents.add(new DomainEvent("OrderCreate", order));
        return order;
    }

    public UUID id() {
        return id;
    }
    public UUID userId() {
        return userId;
    }


    public UUID productId() {
        return productId;
    }
}
