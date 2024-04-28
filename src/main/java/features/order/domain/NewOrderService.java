package features.order.domain;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.domain.MoneyFlows;
import features.product.domain.ProductRepository;
import features.product.domain.PublishedProduct;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.User;

import java.util.UUID;

public class NewOrderService {
    private ProductRepository productRepository;
    private MoneyFlowRepository moneyFlowRepository;
    private OrderRepository orderRepository;

    public NewOrderService() {
        productRepository = new ProductRepository();
        moneyFlowRepository = new MoneyFlowRepository();
        orderRepository = new OrderRepository();
    }

    public void run(UUID productId, User user) {
        PublishedProduct product = productRepository.findPublishedById(productId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserId(user.id);

        OrderFactory.OrderResult orderResult = OrderFactory.newOrder(user, product, moneyFlows);

        moneyFlowRepository.save(orderResult.usedMoneyFlow);
        orderRepository.save(orderResult.newOrder);
    }

}
