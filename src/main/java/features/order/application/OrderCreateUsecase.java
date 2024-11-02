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

import java.util.Map;
import java.util.stream.Collectors;

public class OrderCreateUsecase {

    public void run(OrderCreateInput input) {
        Product product = new ProductRepository().findById(input.getProductId());

        SqliteDatabase db = new SqliteDatabase();
        Records records = db.find("select * from moneyFlows");
        int sum = 0;
        for (Object item : records.items) {
            sum += (int)((Map)item).get("value");
        }
        if(sum< product.price()) {
            throw new RuntimeException("お金が足りません");
        }

        Order order = Order.create(product);
        new OrderRepository().save(order);

        MoneyFlow moneyFlow = MoneyFlow.order(product);
        new MoneyFlowRepository().save(moneyFlow);
    }
}
