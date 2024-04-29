package features.order;

import features.moneyFlow.MoneyFlowDataBuilder;
import features.moneyFlow.domain.MoneyFlowRepository;
import features.order.application.NewOrderForVipUsecase;
import features.order.domain.OrderRepository;
import features.product.ProductDataBuilder;
import features.product.domain.PublishedProduct;
import features.product.presentation.NewOrderInput;
import features.user.UserDataBuilder;
import features.user.domain.User;
import features.user.domain.UserPlan;
import helpers.FakeMailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NewOrderForVipUsecaseTest {

    FakeMailSender mailSender = new FakeMailSender();
    NewOrderForVipUsecase newOrderForVipUsecase = new NewOrderForVipUsecase(mailSender);

    @BeforeEach
    void beforeAll() {
        MoneyFlowRepository.records = new ArrayList<>();
        OrderRepository.records = new ArrayList<>();
    }

    @Test
    void VIPユーザが商品を購入すると1割引き() {
        //given
        User loginUser = new UserDataBuilder().setUserPlan(UserPlan.VIP).please();
        new MoneyFlowDataBuilder(loginUser.id()).please();
        PublishedProduct product = new ProductDataBuilder(loginUser.id()).pleaseAsPublished();
        NewOrderInput input = new NewOrderInput(product.id);
        //when
        newOrderForVipUsecase.run(loginUser.id(), input);
        //then
        assertEquals(2, MoneyFlowRepository.records.size());
        assertEquals(-900, MoneyFlowRepository.records.get(1).value());
        assertEquals(1, OrderRepository.records.size());
        assertEquals(1, mailSender.callCountSend);
        assertEquals("VIPへの特別商品ご案内", mailSender.argsTitle);
    }

}
