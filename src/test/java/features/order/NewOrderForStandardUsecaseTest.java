package features.order;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.product.domain.PublishedProduct;
import helpers.FakeMailSender;
import features.order.domain.OrderRepository;
import features.order.application.NewOrderForStandardUsecase;
import features.product.domain.Product;
import features.product.presentation.NewOrderInput;
import features.user.domain.User;
import features.moneyFlow.MoneyFlowDataBuilder;
import features.product.ProductDataBuilder;
import features.user.UserDataBuilder;
import helpers.TestBase;
import helpers.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Records;
import shared.SqliteDatabase;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NewOrderForStandardUsecaseTest extends TestBase {

    FakeMailSender mailSender = new FakeMailSender();
    NewOrderForStandardUsecase newOrderForStandardUsecase = new NewOrderForStandardUsecase();

    @Test
    void 商品を購入する() {
        //given
        User loginUser = new UserDataBuilder().please();
        PublishedProduct product = new ProductDataBuilder(loginUser.id()).pleaseAsPublished();
        NewOrderInput input = new NewOrderInput(product.id());
        new MoneyFlowDataBuilder(loginUser.id()).please();
        //when
        newOrderForStandardUsecase.run(loginUser.id(), input);
        //then
        Records moneyFlows = db.find("select * from moneyFlows");
        assertEquals(2, moneyFlows.size());
        assertEquals(-1000, ((Map) moneyFlows.items.get(1)).get("value"));
        Records orders = db.find("select * from orders");
        assertEquals(1, orders.size());
        assertEquals(0, mailSender.callCountSend);
    }

    @Test
    void 残高不足で購入できない() {
        //given
        User loginUser = new UserDataBuilder().please();
        Product product = new ProductDataBuilder(loginUser.id()).pleaseAsPublished();
        NewOrderInput input = new NewOrderInput(product.id());
        //when
        try {
            newOrderForStandardUsecase.run(loginUser.id(), input);
        } catch (RuntimeException e) {
            //then
            assertEquals("チャージ残高が足りません", e.getMessage());
            assertEquals(0, MoneyFlowRepository.records.size());
            return;
        }
        fail("unexpected test fail");
    }

}
