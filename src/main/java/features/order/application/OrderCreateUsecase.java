package features.order.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.moneyFlows.domain.MoneyFlows;
import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Product product = new ProductRepository().findById(input.getProductId());

        MoneyFlows moneyFlows = new MoneyFlowRepository().findAll();
        if (moneyFlows.sum() < product.price()) {
            throw new RuntimeException("お金が足りません");
        }

        Order order = Order.create(product);
        new OrderRepository().save(order);

        MoneyFlow moneyFlow = MoneyFlow.order(product);
        new MoneyFlowRepository().save(moneyFlow);
    }

}
