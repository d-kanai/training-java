package features.order;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.order.application.NewOrderUsecase;
import features.order.domain.Order;
import features.product.domain.Product;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderHistoryUsecaseTest {

    @Test
    void 購入履歴を取得する() {
        //given
        User loginUser = TestDataFactory.createUser();
        Product product = TestDataFactory.createPublishedProduct(loginUser.id);
        TestDataFactory.createOrder(loginUser.id, product);
        TestDataFactory.createOrder(loginUser.id, product);
        //when
        List<Order> actual = new OrderHistoryUsecase().run(loginUser.id);
        //then
        assertEquals(2, actual.size());
        assertEquals("book", actual.get(0).product.name);
        assertEquals(1000, actual.get(0).product.price);
    }


}
