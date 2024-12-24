package features.order.application;

import features.moneyFlows.domain.MoneyFlowRepository;
import features.moneyFlows.domain.MoneyFlows;
import features.order.domain.OrderFactory;
import features.order.domain.OrderRepository;
import features.order.domain.Ordered;
import features.order.presentation.OrderCreateInput;
import features.product.domain.ProductRepository;
import features.product.domain.PublishedProduct;
import features.user.domain.User;
import features.user.domain.UserRepository;
import shared.IMailSender;

import java.util.UUID;

//・No Nested Usecase (Logic!!)
//・Service Class(使わないで良いなら使わない）
//・High Cohesion
//・Composition Over Inheritance(継承より委譲)

public class OrderUsecase {

    private final IMailSender mailSender;
    private UserRepository userRepository = new UserRepository();
    private ProductRepository productRepository = new ProductRepository();
    private OrderRepository orderRepository = new OrderRepository();
    private MoneyFlowRepository moneyFlowRepository = new MoneyFlowRepository();

    public OrderUsecase(IMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void run(UUID loginUserId, OrderCreateInput input) {
        User user = userRepository.findById(loginUserId);
        UUID productId = input.getProductId();

        PublishedProduct product = productRepository.findPublishedById(productId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserId(loginUserId);

        Ordered ordered = new OrderFactory().create(loginUserId, moneyFlows, product);

        orderRepository.save(ordered.order);
        moneyFlowRepository.save(ordered.moneyFlow);

        if (user.plan() == User.Plan.VIP) {
            mailSender.send(user.email(), "for VIP");
        }
    }


}
