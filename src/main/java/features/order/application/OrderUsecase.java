package features.order.application;

import features.order.domain.OrderService;
import features.order.presentation.OrderCreateInput;
import features.user.domain.User;
import features.user.domain.UserRepository;
import shared.IMailSender;

import java.util.UUID;

public class OrderUsecase {

    OrderService orderService;

    public OrderUsecase(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run(UUID loginUserId, OrderCreateInput input) {
        orderService.run(loginUserId, input.getProductId());
    }
}
