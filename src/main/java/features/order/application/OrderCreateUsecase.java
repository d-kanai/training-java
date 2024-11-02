package features.order.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import shared.Records;
import shared.SqliteDatabase;

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Order order = Order.create(input.getProductId());
        new OrderRepository().save(order);

        Product product = new ProductRepository().findById(input.getProductId());

        MoneyFlow moneyFlow = MoneyFlow.order(product);
        new MoneyFlowRepository().save(moneyFlow);
    }
}
