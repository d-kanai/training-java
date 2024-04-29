package features.product;

import features.user.domain.User;
import features.user.UserDataBuilder;
import org.junit.jupiter.api.Test;
import features.product.application.ProductCreateUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DraftProductCreateUsecaseTest {

    @Test
    void Draft登録() {
        //given
        User loginUser = new UserDataBuilder().please();
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
        Product product = new ProductCreateUsecase().run(loginUser.id(), input);
        //then
        assertEquals("book", product.name());
        assertEquals(1, ProductRepository.records.size());
    }

}

