package features.order.domain;

import features.moneyFlows.domain.MoneyFlowRepository;
import features.moneyFlows.domain.MoneyFlows;
import features.product.domain.ProductRepository;
import features.product.domain.PublishedProduct;
import shared.EventPublisher;

import java.util.UUID;

public class OrderService {
    ProductRepository productRepository = new ProductRepository();
    MoneyFlowRepository moneyFlowRepository = new MoneyFlowRepository();
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(UUID loginUserId, UUID productId) {
        PublishedProduct product = productRepository.findPublishedById(productId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserId(loginUserId);

        Ordered ordered = new OrderFactory(loginUserId, moneyFlows, product).create();

        orderRepository.save(ordered.order);
        moneyFlowRepository.save(ordered.moneyFlow);

    }
}
