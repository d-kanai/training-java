package features.order.domain;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.domain.MoneyFlows;
import features.product.domain.ProductRepository;
import features.product.domain.PublishedProduct;
import features.user.domain.User;

import java.util.UUID;

public class NewOrder {
    private ProductRepository productRepository;
    private MoneyFlowRepository moneyFlowRepository;
    private OrderRepository orderRepository;

    public NewOrder() {
        productRepository = new ProductRepository();
        moneyFlowRepository = new MoneyFlowRepository();
        orderRepository = new OrderRepository();
    }

    public void run(UUID productId, User loginUser) {
        PublishedProduct product = productRepository.findPublishedById(productId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserId(loginUser.id());

        Order orderResult = OrderFactory.newOrder(loginUser, product, moneyFlows);
        MoneyFlow usedMoneyFlow = MoneyFlow.order(loginUser.id(), product.discountedPrice(loginUser.userPlan()));

        moneyFlowRepository.save(usedMoneyFlow);
        orderRepository.save(orderResult);
    }

}
