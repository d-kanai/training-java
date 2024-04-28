package features.order;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.product.application.ProductPurchaseUsecase;
import features.product.domain.Product;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderHistoryUsecaseTest {

    @Test
    void 購入履歴を取得する() {
        //given
        User loginUser = TestDataFactory.createUser();
        Product product = TestDataFactory.createPublishedProduct(loginUser.id);
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        TestDataFactory.createMoneyFlow(loginUser.id);
        //when
        new ProductPurchaseUsecase().run(loginUser.id, input);
        //then
        assertEquals(2, MoneyFlowRepository.records.size());
        assertEquals(-1000, MoneyFlowRepository.records.get(1).value());
    }


}
