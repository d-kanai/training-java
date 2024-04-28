package features.order;

import features.order.domain.Order;
import features.order.domain.OrderRepository;

import java.util.List;
import java.util.UUID;

public class OrderHistoryUsecase {
    public List<Order> run(UUID loginUserId) {
        return new OrderRepository().findByUserId(loginUserId);

    }
}
