package features.order.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.order.domain.Order;
import features.order.domain.OrderRepository;
import features.order.presentation.OrderCreateInput;
import shared.Records;
import shared.SqliteDatabase;

import java.util.Map;
import java.util.UUID;

// Develop from "0" not "-1" by Test
// reconstruct Domain Model in Repository
// Test Data Builder
// Test Case By Intention
// Design By Type

public class OrderCreateUsecase {

    SqliteDatabase db = new SqliteDatabase();

    public void run(OrderCreateInput input) {
        Records products = db.find(String.format("select * from products where id = '%s'", input.getProductId()));
        Map product = products.first();

        Order order = new Order(UUID.randomUUID(), input.getProductId());
        new OrderRepository().save(order);

        MoneyFlow moneyFlow = new MoneyFlow(UUID.randomUUID(), -(Integer) product.get("price"));
        new MoneyFlowRepository().save(moneyFlow);
    }
}
