package features.product;

import features.product.application.ProductPublishUsecase;
import features.product.domain.Product;
import features.product.presentation.ProductPublishInput;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProductPublishUsecaseTest extends BaseTest {

    @Test
    void 商品公開() {
        //given
        Product product = new ProductDataBuilder().status(Product.Status.DRAFT).please();
        ProductPublishInput input = new ProductPublishInput(product.id());
        //when
        new ProductPublishUsecase().run(input);
        //then
        Records records = db.find("select * from products");
        assertEquals(Product.Status.PUBLISHED.toString(), records.first().get("status"));
    }

    @Test
    void 公開ずみは公開できない() {
        //given
        Product product = new ProductDataBuilder().status(Product.Status.PUBLISHED).please();
        ProductPublishInput input = new ProductPublishInput(product.id());
        //when
        try {
            new ProductPublishUsecase().run(input);
        } catch (RuntimeException e) {
            //then
            assertEquals("すでに公開済みです", e.getMessage());
            return;
        }
        fail("unexpected reached");
    }


}

