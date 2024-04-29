package features.order;

import features.order.application.OrderHistoryUsecase;
import features.order.domain.Order;
import features.product.domain.PublishedProduct;
import features.user.domain.User;
import features.product.ProductDataBuilder;
import features.user.UserDataBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderHistoryUsecaseTest {

    @Test
    void 購入履歴を取得する() {
        //given
        User loginUser = new UserDataBuilder().please();
        PublishedProduct product = new ProductDataBuilder(loginUser.id()).pleaseAsPublished();
        new OrderDataBuilder(loginUser, product).please();
        new OrderDataBuilder(loginUser, product).please();
        //when
        List<Order> actual = new OrderHistoryUsecase().run(loginUser.id());
        //then
        assertEquals(2, actual.size());
        assertEquals("book", actual.get(0).product.name);
        assertEquals(1000, actual.get(0).product.price);
        assertEquals(1000, actual.get(0).orderedPrice.value);
    }


}
