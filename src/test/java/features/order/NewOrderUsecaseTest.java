package features.order;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.product.domain.PublishedProduct;
import features.user.domain.UserPlan;
import helpers.FakeMailSender;
import features.order.domain.OrderRepository;
import features.order.application.NewOrderUsecase;
import features.product.domain.Product;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.User;
import features.moneyFlow.MoneyFlowDataBuilder;
import features.product.ProductDataBuilder;
import features.user.UserDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NewOrderUsecaseTest {

    FakeMailSender mailSender = new FakeMailSender();
    NewOrderUsecase newOrderUsecase = new NewOrderUsecase(mailSender);

    @BeforeEach
    void beforeAll() {
        MoneyFlowRepository.records = new ArrayList<>();
        OrderRepository.records = new ArrayList<>();
    }

    @Test
    void 商品を購入する() {
        //given
        User loginUser = new UserDataBuilder().please();
        PublishedProduct product = new ProductDataBuilder(loginUser.id).pleaseAsPublished();
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        new MoneyFlowDataBuilder(loginUser.id).please();
        //when
        newOrderUsecase.run(loginUser.id, input);
        //then
        assertEquals(2, MoneyFlowRepository.records.size());
        assertEquals(-1000, MoneyFlowRepository.records.get(1).value());
        assertEquals(1, OrderRepository.records.size());
        assertEquals(0, mailSender.callCountSend);
    }

    @Test
    void VIPユーザが商品を購入すると1割引き() {
        //given
        User loginUser = new UserDataBuilder().setUserPlan(UserPlan.VIP).please();
        new MoneyFlowDataBuilder(loginUser.id).please();
        PublishedProduct product = new ProductDataBuilder(loginUser.id).pleaseAsPublished();
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        //when
        newOrderUsecase.run(loginUser.id, input);
        //then
        assertEquals(2, MoneyFlowRepository.records.size());
        assertEquals(-900, MoneyFlowRepository.records.get(1).value());
        assertEquals(1, OrderRepository.records.size());
        assertEquals(1, mailSender.callCountSend);
        assertEquals("VIPへの特別商品ご案内", mailSender.argsTitle);
    }

    @Test
    void 残高不足で購入できない() {
        //given
        User loginUser = new UserDataBuilder().please();
        Product product = new ProductDataBuilder(loginUser.id).pleaseAsPublished();
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        //when
        try {
            newOrderUsecase.run(loginUser.id, input);
        } catch (RuntimeException e) {
            //then
            assertEquals("チャージ残高が足りません", e.getMessage());
            assertEquals(0, MoneyFlowRepository.records.size());
            return;
        }
        fail("unexpected test fail");
    }

}
