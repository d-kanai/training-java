
package features.order.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.infra.MoneyFlowRepository;
import features.order.domain.Order;
import features.order.infra.OrderRepository;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.infra.ProductRepository;

public class OrderCreateUsecase {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MoneyFlowRepository moneyFlowRepository;

    public OrderCreateUsecase() {
        this.productRepository = new ProductRepository();
        this.orderRepository = new OrderRepository();
        this.moneyFlowRepository = new MoneyFlowRepository();
    }

    public OrderCreateUsecase(ProductRepository productRepository, 
                             OrderRepository orderRepository, 
                             MoneyFlowRepository moneyFlowRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.moneyFlowRepository = moneyFlowRepository;
    }

    public void run(OrderCreateInput input) {
        Product product = productRepository.findById(input.getProductId());
        int availableFunds = moneyFlowRepository.getTotalFunds();
        
        Order order = Order.create(product, availableFunds);
        orderRepository.save(order);

        MoneyFlow moneyFlow = MoneyFlow.order(product);
        moneyFlowRepository.save(moneyFlow);
    }
}
