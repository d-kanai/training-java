package features.order;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.order.application.OrderCreateUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;
import shared.SqliteDatabase;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCreateUsecaseTest extends BaseTest {

    @Test
    void 購入() {
        //given
        Product product = new Product("book", 1000);
        new ProductRepository().save(product);
        MoneyFlow moneyFlow = new MoneyFlow(1500);
        new MoneyFlowRepository().save(moneyFlow);
        OrderCreateInput input = new OrderCreateInput(product.id());
        //when
        new OrderCreateUsecase().run(input);
        //then
        Records orders = db.find("select * from orders");
        assertEquals(1, orders.size());
        assertEquals(product.id().toString(), orders.first().get("productId"));
        Records moneyFlows = db.find("select * from moneyFlows");
        assertEquals(2, moneyFlows.size());
        assertEquals(-1000, ((Map) moneyFlows.items.get(1)).get("value"));
    }

}

