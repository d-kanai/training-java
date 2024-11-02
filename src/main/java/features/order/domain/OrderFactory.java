package features.order.domain;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlows;
import features.product.domain.Product;

public class OrderFactory {
    private final MoneyFlows moneyFlows;
    private final Product product;

    public OrderFactory(MoneyFlows moneyFlows, Product product) {
        this.moneyFlows = moneyFlows;
        this.product = product;
    }

    public Ordered run() {
        if (moneyFlows.sum() < product.price()) {
            throw new RuntimeException("お金が足りません");
        }
        Order order = Order.create(product);
        MoneyFlow moneyFlow = MoneyFlow.order(product);
        return new Ordered(order, moneyFlow);
    }
}
