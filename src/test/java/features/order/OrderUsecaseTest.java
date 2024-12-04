package features.order;

import features.moneyFlow.MoneyFlowDataBuilder;
import features.order.application.OrderUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.ProductDataBuilder;
import features.product.domain.Product;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderUsecaseTest extends BaseTest {

    @Test
    void 購入が積まれる() {
        //given
        Product product = new ProductDataBuilder().price(1000).please();
        new MoneyFlowDataBuilder().value(1000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        new OrderUsecase().run(input);
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
        new OrderUsecase().run(input);
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
            new OrderUsecase().run(input);
        } catch (RuntimeException e) {
            //then
            Records moneyFlows = db.find("select * from moneyFlows");
            assertEquals(1, moneyFlows.size());
            assertEquals("お金が足りません", e.getMessage());
            return;
        }
        fail("unexpected reached");
    }

    @Test
    void 非公開商品は買えない() {
        //given
        Product product = new ProductDataBuilder().price(2000).status(Product.Status.DRAFT).please();
        new MoneyFlowDataBuilder().value(2000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        try {
            new OrderUsecase().run(input);
        } catch (RuntimeException e) {
            //then
            Records orders = db.find("select * from orders");
            assertEquals(0, orders.size());
            assertEquals("商品が存在しません", e.getMessage());
            return;
        }
        fail("unexpected reached");
    }

}

