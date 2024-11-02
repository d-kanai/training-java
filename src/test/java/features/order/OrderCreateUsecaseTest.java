package features.order;

import features.moneyFlow.MoneyFlowDataBuilder;
import features.order.application.OrderCreateUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.ProductDataBuilder;
import features.product.domain.Product;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderCreateUsecaseTest extends BaseTest {

    @Test
    void 購入が積まれる() {
        //given
        Product product = new ProductDataBuilder().price(1000).please();
        new MoneyFlowDataBuilder().value(1000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        new OrderCreateUsecase().run(input);
        //then
        Records orders = db.find("select * from orders");
        assertEquals(1, orders.size());
        assertEquals(product.id().toString(), orders.first().get("productId"));
    }

    @Test
    void お金が減る() {
        //given
        Product product = new ProductDataBuilder().price(2000).please();
        new MoneyFlowDataBuilder().value(2000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        new OrderCreateUsecase().run(input);
        //then
        Records moneyFlows = db.find("select * from moneyFlows");
        assertEquals(2, moneyFlows.size());
        assertEquals(-2000, ((Map) moneyFlows.items.get(1)).get("value"));
    }

    @Test
    void お金が足りないと購入できない() {
        //given
        Product product = new ProductDataBuilder().price(2000).please();
        new MoneyFlowDataBuilder().value(1999).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        try {
            new OrderCreateUsecase().run(input);
        } catch (RuntimeException e) {
            //then
            Records moneyFlows = db.find("select * from moneyFlows");
            assertEquals(1, moneyFlows.size());
            assertEquals("お金が足りません", e.getMessage());
            return;
        }
        fail("unexpected reached");
    }

}

