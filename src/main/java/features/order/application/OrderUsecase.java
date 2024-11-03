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

public class OrderUsecase {

    private final IMailSender mailSender;

    public OrderUsecase(IMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void run(UUID loginUserId, OrderCreateInput input) {
        User user = new UserRepository().findById(loginUserId);
        UUID productId = input.getProductId();

        PublishedProduct product = new ProductRepository().findPublishedById(productId);
        MoneyFlows moneyFlows = new MoneyFlowRepository().findByUserId(loginUserId);

        Ordered ordered = new OrderFactory().create(loginUserId, moneyFlows, product);

        new OrderRepository().save(ordered.order);
        new MoneyFlowRepository().save(ordered.moneyFlow);

        if (user.plan() == User.Plan.VIP) {
            mailSender.send(user.email(), "for VIP");
        }
    }


}
