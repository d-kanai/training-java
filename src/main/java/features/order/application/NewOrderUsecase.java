package features.order.application;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.domain.MoneyFlows;
import features.order.domain.OrderFactory;
import features.order.domain.OrderRepository;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.User;
import features.user.domain.UserRepository;

import java.util.UUID;

public class NewOrderUsecase {

    private ProductRepository productRepository;
    private MoneyFlowRepository moneyFlowRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    public NewOrderUsecase() {
        productRepository = new ProductRepository();
        moneyFlowRepository = new MoneyFlowRepository();
        orderRepository = new OrderRepository();
        userRepository = new UserRepository();
    }

    public void run(UUID loginUserId, ProductPurchaseInput input) {
        User user = userRepository.findById(loginUserId);
        Product product = productRepository.findById(input.productId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserId(loginUserId);

        OrderFactory.OrderResult orderResult = OrderFactory.newOrder(user, product, moneyFlows);

        moneyFlowRepository.save(orderResult.usedMoneyFlow);
        orderRepository.save(orderResult.newOrder);
    }
}