package features.order.domain;

import java.util.UUID;

public class Order {

    public final UUID id;
    public final UUID userId;
    public final UUID productId;


    private Order(UUID id, UUID userId, UUID productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public static Order purchase(UUID userId, UUID productId) {
        return new Order(
                UUID.randomUUID(),
                userId,
                productId
        );
    }

}
