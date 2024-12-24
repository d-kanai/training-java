package features.order.domain;

import features.moneyFlows.domain.MoneyFlowRepository;
import features.product.domain.ProductRepository;

public class OrderService {
    ProductRepository productRepository = new ProductRepository();
    MoneyFlowRepository moneyFlowRepository = new MoneyFlowRepository();
    OrderRepository orderRepository = new OrderRepository();

}
