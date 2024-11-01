package features.product;

import features.product.domain.DraftProduct;
import features.user.domain.User;
import features.user.UserDataBuilder;
import helpers.TestBase;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import features.product.application.ProductPublishUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.domain.ProductStatus;
import features.product.presentation.ProductPublishInput;
import shared.Records;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProductPublishUsecaseTest extends TestBase {

    @Test
    void 商品を公開する() {
        //given
        User loginUser = new UserDataBuilder().please();
        DraftProduct product = new ProductDataBuilder(loginUser.id()).pleaseAsDraft();
        ProductPublishInput input = new ProductPublishInput(product.id());
        //when
        Product actual = new ProductPublishUsecase().run(loginUser.id(), input);
        //then
        assertEquals(ProductStatus.PUBLISHED, actual.status());
        Records records = db.find("select * from products");
        assertEquals(1, records.size());
        assertEquals(ProductStatus.PUBLISHED.toString(), records.first().get("status"));
    }

    @Nested
    class 異常パターン {

        @Test
        void 存在しない商品() {
            //given
            User loginUser = new UserDataBuilder().please();
            ProductPublishInput input = new ProductPublishInput(UUID.randomUUID());
            //when
            try {
                new ProductPublishUsecase().run(loginUser.id(), input);
            } catch (RuntimeException e) {
                //then
                assertEquals("商品が存在しません", e.getMessage());
                return;
            }
            fail("unexpected test fail");
        }

        @Test
        void 人の商品は公開できない() {
            //given
            User loginUser = new UserDataBuilder().please();
            Product product = new ProductDataBuilder(loginUser.id()).pleaseAsPublished();
            User anotherUser = new UserDataBuilder().please();
            ProductPublishInput input = new ProductPublishInput(product.id());
            //when
            try {
                new ProductPublishUsecase().run(anotherUser.id(), input);
            } catch (RuntimeException e) {
                //then
                assertEquals("商品が存在しません", e.getMessage());
                return;
            }
            fail("unexpected test fail");
        }

    }
}

