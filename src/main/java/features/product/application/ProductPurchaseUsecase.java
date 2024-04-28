package features.product.application;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.domain.MoneyFlows;
import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductPurchaseInput;

import java.util.UUID;

public class ProductPurchaseUsecase {

    private ProductRepository productRepository;
    private MoneyFlowRepository moneyFlowRepository;
    private OrderRepository orderRepository;

    public ProductPurchaseUsecase() {
        productRepository = new ProductRepository();
        moneyFlowRepository = new MoneyFlowRepository();
        orderRepository = new OrderRepository();
    }

    public void run(UUID loginUserId, ProductPurchaseInput input) {
        Product product = productRepository.findById(input.productId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserId(loginUserId);
        Order.OrderResult orderResult = Order.newOrder(loginUserId, product, moneyFlows);
        moneyFlowRepository.save(orderResult.usedMoneyFlow);
        orderRepository.save(orderResult.newOrder);
    }
}
