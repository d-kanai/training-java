package features.product;

import features.product.application.ProductCreateUsecase;
import features.product.presentation.ProductCreateInput;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProductCreateUsecaseTest extends BaseTest {

    @Test
    void 商品登録() {
        //given
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
        new ProductCreateUsecase().run(input);
        //then
        Records records = db.find("select * from products");
        assertEquals(1, records.size());
        assertEquals(1000, records.first().get("price"));
    }

    @Test
    void マイナスは登録できない() {
        //given
        ProductCreateInput input = new ProductCreateInput("book", -1000);
        //when
        try {
            new ProductCreateUsecase().run(input);
        } catch (RuntimeException e) {
            //then
            Records records = db.find("select * from products");
            assertEquals(0, records.size());
            return;
        }
        fail("unexpected reached");
    }

}

