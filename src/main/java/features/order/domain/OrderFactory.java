package features.order.domain;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlows;
import features.product.domain.PublishedProduct;

import java.util.UUID;

public class OrderFactory {

    public Ordered create(UUID loginUserId, MoneyFlows moneyFlows, PublishedProduct product) {
        if (moneyFlows.sum() < product.price()) throw new RuntimeException("お金が足りません");
        Order order = Order.create(loginUserId, product);
        MoneyFlow moneyFlow = MoneyFlow.order(loginUserId, product);
        return new Ordered(order, moneyFlow);
    }
}
