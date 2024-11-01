package features.order;

import features.order.application.OrderCreateUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import shared.Records;
import shared.SqliteDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCreateUsecaseTest {

    @Test
    void 購入() {
        SqliteDatabase db = new SqliteDatabase();
        db.execute("delete from orders");
        db.execute("delete from products");
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

