package features.order.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import shared.DomainException;

import java.util.List;

//・No for, Use declarative by pipeline
//・First Class Collection
//・Factory Class
//・Package Private

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Product product = new ProductRepository().findById(input.getProductId());

        List<MoneyFlow> moneyFlows = new MoneyFlowRepository().findAll();

        int sum = 0;
        for (MoneyFlow moneyFlow : moneyFlows) {
            sum += moneyFlow.value();
        }

        if (sum < product.price()) throw new DomainException("お金が足りません");

        Order order = Order.create(product);
        MoneyFlow moneyFlow = MoneyFlow.order(product);

        new OrderRepository().save(order);
        new MoneyFlowRepository().save(moneyFlow);
    }


}
