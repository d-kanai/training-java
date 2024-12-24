package features.order.application;

import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.order.presentation.OrderCreateInput;

//・受け入れテストで全体間の理解
//・middle assertion
//・feature base dir
//・base test. data from 0
//・1 class 1 public method

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Order order = new Order(input.getProductId());
        new OrderRepository().save(order);
    }
}
