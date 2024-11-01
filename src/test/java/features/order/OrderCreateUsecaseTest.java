package features.order;

import features.order.application.OrderCreateUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;
import shared.SqliteDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCreateUsecaseTest extends BaseTest {

    @Test
    void 購入() {
        //given
        Product product = new Product("book", 1000);
        new ProductRepository().save(product);
        OrderCreateInput input = new OrderCreateInput(product.id());
        //when
        new OrderCreateUsecase().run(input);
        //then
        Records records = db.find("select * from orders");
        assertEquals(1, records.size());
        assertEquals(product.id().toString(), records.first().get("productId"));
    }

}

