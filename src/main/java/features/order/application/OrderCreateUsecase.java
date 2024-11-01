package features.order.application;

import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.order.presentation.OrderCreateInput;

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Order order = new Order(input.getProductId());
        new OrderRepository().save(order);
    }
}
