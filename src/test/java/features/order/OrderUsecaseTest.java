package features.order;

import features.moneyFlow.MoneyFlowDataBuilder;
import features.order.application.OrderUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.ProductDataBuilder;
import features.product.domain.Product;
import features.user.UserDataBuilder;
import features.user.domain.User;
import helpers.BaseTest;
import helpers.FakeMailSender;
import org.junit.jupiter.api.Test;
import shared.Records;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderUsecaseTest extends BaseTest {

    FakeMailSender mailSender = new FakeMailSender();

    OrderUsecase orderUsecase = new OrderUsecase(mailSender);

    @Test
    void 購入が積まれる() {
        //given
        User loginUser = new UserDataBuilder().please();
        Product product = new ProductDataBuilder().price(1000).please();
        new MoneyFlowDataBuilder(loginUser.id()).value(1000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        orderUsecase.run(loginUser.id(), input);
        //then
        Records orders = db.find("select * from orders");
        assertEquals(1, orders.size());
        assertEquals(product.id().toString(), orders.first().get("productId"));
        assertEquals(loginUser.id().toString(), orders.first().get("userId"));
        assertEquals(0, mailSender.callCount);
    }

    @Test
    void VIPの場合メールが飛ぶ() {
        //given
        User loginUser = new UserDataBuilder().plan(User.Plan.VIP).please();
        Product product = new ProductDataBuilder().price(1000).please();
        new MoneyFlowDataBuilder(loginUser.id()).value(1000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        orderUsecase.run(loginUser.id(), input);
        //then
        assertEquals(1, mailSender.callCount);
        assertEquals(loginUser.email(), mailSender.argsEmail);
    }

    @Test
    void お金が減る() {
        //given
        User loginUser = new UserDataBuilder().please();
        Product product = new ProductDataBuilder().price(2000).please();
        new MoneyFlowDataBuilder(loginUser.id()).value(2000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        orderUsecase.run(loginUser.id(), input);
        //then
        Records moneyFlows = db.find("select * from moneyFlows");
        assertEquals(2, moneyFlows.size());
        assertEquals(-2000, ((Map) moneyFlows.items.get(1)).get("value"));
        assertEquals(loginUser.id().toString(), ((Map) moneyFlows.items.get(1)).get("userId"));
    }

    @Test
    void お金が足りないと購入できない() {
        //given
        User loginUser = new UserDataBuilder().please();
        User anotherUser = new UserDataBuilder().please();
        Product product = new ProductDataBuilder().price(2000).please();
        new MoneyFlowDataBuilder(loginUser.id()).value(1999).please();
        new MoneyFlowDataBuilder(anotherUser.id()).value(2000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        try {
            new OrderUsecase(mailSender).run(loginUser.id(), input);
        } catch (RuntimeException e) {
            //then
            Records moneyFlows = db.find("select * from moneyFlows");
            assertEquals(2, moneyFlows.size());
            assertEquals("お金が足りません", e.getMessage());
            return;
        }
        fail("unexpected reached");
    }

    @Test
    void 非公開商品は買えない() {
        //given
        User loginUser = new UserDataBuilder().please();
        Product product = new ProductDataBuilder().price(2000).status(Product.Status.DRAFT).please();
        new MoneyFlowDataBuilder(loginUser.id()).value(2000).please();
        //when
        OrderCreateInput input = new OrderCreateInput(product.id());
        try {
            new OrderUsecase(mailSender).run(loginUser.id(), input);
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

