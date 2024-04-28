package features.product;

import features.money.domain.MoneyRepository;
import features.product.application.ProductPurchaseUsecase;
import features.product.domain.Product;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProductPurchaseUsecaseTest {

    @Test
    void 商品を購入する() {
        //given
        User loginUser = TestDataFactory.createUser();
        Product product = TestDataFactory.createPublishedProduct(loginUser.id);
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        TestDataFactory.createMoney(loginUser.id);
        //when
        new ProductPurchaseUsecase().run(loginUser.id, input);
        //then
        assertEquals(2, MoneyRepository.records.size());
        assertEquals(-1000, MoneyRepository.records.get(1).value());
    }

    @Test
    void 残高不足で購入できない() {
        //given
        User loginUser = TestDataFactory.createUser();
        Product product = TestDataFactory.createPublishedProduct(loginUser.id);
        ProductPurchaseInput input = new ProductPurchaseInput(product.id);
        //when
        try {
            new ProductPurchaseUsecase().run(loginUser.id, input);
        } catch (RuntimeException e) {
            //then
            assertEquals("チャージ残高が足りません", e.getMessage());
            assertEquals(0, MoneyRepository.records.size());
            return;
        }
        fail("unexpected test fail");
    }

}
