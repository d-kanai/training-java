package features.order.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;

import java.util.List;

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Product product = new ProductRepository().findById(input.getProductId());

        List<MoneyFlow> moneyFlows = new MoneyFlowRepository().findAll();
        int sum = moneyFlows
                .stream()
                .mapToInt(MoneyFlow::value)
                .sum();

        if (sum < product.price()) throw new RuntimeException("お金が足りません");

        Order order = Order.create(product);
        MoneyFlow moneyFlow = MoneyFlow.order(product);

        new OrderRepository().save(order);
        new MoneyFlowRepository().save(moneyFlow);
    }


}
