package features.order;

import features.moneyFlow.MoneyFlowDataBuilder;
import features.order.application.NewOrderForVipUsecase;
import features.product.ProductDataBuilder;
import features.product.domain.PublishedProduct;
import features.order.presentation.NewOrderInput;
import features.user.UserDataBuilder;
import features.user.domain.User;
import features.user.domain.UserPlan;
import helpers.FakeMailSender;
import helpers.TestBase;
import org.junit.jupiter.api.Test;
import shared.Records;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NewOrderForVipUsecaseTest extends TestBase {

    FakeMailSender mailSender = new FakeMailSender();
    NewOrderForVipUsecase newOrderForVipUsecase = new NewOrderForVipUsecase(mailSender);

    @Test
    void VIPユーザが商品を購入すると1割引き() {
        //given
        User loginUser = new UserDataBuilder().setUserPlan(UserPlan.VIP).please();
        new MoneyFlowDataBuilder(loginUser.id()).please();
        PublishedProduct product = new ProductDataBuilder(loginUser.id()).pleaseAsPublished();
        NewOrderInput input = new NewOrderInput(product.id());
        //when
        newOrderForVipUsecase.run(loginUser.id(), input);
        //then
        Records moneyFlows = db.find("select * from moneyFlows");
        assertEquals(2, moneyFlows.items.size());
        assertEquals(-900, ((Map) moneyFlows.items.get(1)).get("value"));
        Records orders = db.find("select * from orders");
        assertEquals(1, orders.size());

        assertEquals(1, mailSender.callCountSend);
        assertEquals("VIPへの特別商品ご案内", mailSender.argsTitle);
    }

}
