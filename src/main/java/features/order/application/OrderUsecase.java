package features.order.application;

import features.order.domain.OrderService;
import features.order.presentation.OrderCreateInput;
import features.user.domain.User;
import features.user.domain.UserRepository;
import shared.IMailSender;

import java.util.UUID;

public class OrderUsecase {

    private final IMailSender mailSender;
    UserRepository userRepository = new UserRepository();
    OrderService orderService = new OrderService();

    public OrderUsecase(IMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void run(UUID loginUserId, OrderCreateInput input) {
        User user = userRepository.findById(loginUserId);
        orderService.run(loginUserId, input.getProductId());
        if (user.plan() == User.Plan.VIP) {
            mailSender.send(user.email(), "for VIP");
        }
    }
}
