package features.order.domain;

import shared.EventPublisher;
import shared.IEventPublisher;
import shared.SqliteDatabase;

public class OrderRepository {

    IEventPublisher eventPublisher;

    public OrderRepository(IEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void save(Order order) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into orders (id, userId, productId) values ('%s', '%s', '%s');",
                order.id(),
                order.userId(),
                order.productId()
        ));
        order.domainEvents.forEach(domainEvent -> {
            eventPublisher.publish(domainEvent);
        });
    }

}
