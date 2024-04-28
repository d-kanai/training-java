package features.order;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.order.domain.OrderRepository;
import features.order.application.NewOrderUsecase;
import features.product.domain.Product;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NewOrderUsecaseTest {

    @Test
    void 商品を購入する() {
        //given
        User loginUser = TestDataFactory.createUser();
        Product product = TestDataFactory.createPublishedProduct(loginUser.id);
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        TestDataFactory.createMoneyFlow(loginUser.id);
        //when
        new NewOrderUsecase().run(loginUser.id, input);
        //then
        assertEquals(2, MoneyFlowRepository.records.size());
        assertEquals(-1000, MoneyFlowRepository.records.get(1).value());
        assertEquals(1, OrderRepository.records.size());
    }

    @Test
    void 残高不足で購入できない() {
        //given
        User loginUser = TestDataFactory.createUser();
        Product product = TestDataFactory.createPublishedProduct(loginUser.id);
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        //when
        try {
            new NewOrderUsecase().run(loginUser.id, input);
        } catch (RuntimeException e) {
            //then
            assertEquals("チャージ残高が足りません", e.getMessage());
            assertEquals(0, MoneyFlowRepository.records.size());
            return;
        }
        fail("unexpected test fail");
    }

}
