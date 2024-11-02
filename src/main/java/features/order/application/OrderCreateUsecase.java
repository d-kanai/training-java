package features.order.application;

import features.moneyFlows.domain.MoneyFlowRepository;
import features.moneyFlows.domain.MoneyFlows;
import features.order.domain.OrderFactory;
import features.order.domain.OrderRepository;
import features.order.domain.Ordered;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Product product = new ProductRepository().findById(input.getProductId());
        MoneyFlows moneyFlows = new MoneyFlowRepository().findAll();

        Ordered ordered = new OrderFactory(moneyFlows, product).create();

        new OrderRepository().save(ordered.order);
        new MoneyFlowRepository().save(ordered.moneyFlow);
    }


}
