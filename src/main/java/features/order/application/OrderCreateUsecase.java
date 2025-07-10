package features.order.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.infra.MoneyFlowRepository;
import features.order.domain.Order;
import features.order.infra.OrderRepository;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.infra.ProductRepository;
import shared.Records;
import shared.SqliteDatabase;

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Product product = new ProductRepository().findById(input.getProductId());
        
        // Check if there's enough money to afford the product
        int availableFunds = new MoneyFlowRepository().getTotalFunds();
        if (availableFunds < product.price()) {
            throw new RuntimeException("Insufficient funds to create order.");
        }

        Order order = Order.create(product);
        new OrderRepository().save(order);

        MoneyFlow moneyFlow = MoneyFlow.order(product);
        new MoneyFlowRepository().save(moneyFlow);
    }
}