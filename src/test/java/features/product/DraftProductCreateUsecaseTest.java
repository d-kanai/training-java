package features.product;

import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;
import product.application.ProductCreateUsecase;
import product.domain.Product;
import product.domain.ProductRepository;
import product.presentation.ProductCreateInput;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DraftProductCreateUsecaseTest {

    @Test
    void Draft登録() {
        //given
        User loginUser = TestDataFactory.createUser();
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
        Product product = new ProductCreateUsecase().run(loginUser.id, input);
        //then
        assertEquals("book", product.name);
        assertEquals(1, ProductRepository.records.size());
    }

}

