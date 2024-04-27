package features.product;

import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import org.junit.jupiter.api.Test;
import product.application.ProductCreateUsecase;
import product.domain.Product;
import product.domain.ProductRepository;
import product.domain.ProductStatus;
import product.presentation.ProductCreateInput;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPublishUsecaseTest {

    @Test
    void 商品を公開する() throws CloneNotSupportedException {
        //given
        User loginUser = User.signup(new SignupInput("tanaka"));
        UserRepository.records = Arrays.asList(loginUser);
        Product product = Product.draft(UserRepository.records.get(0).id, new ProductCreateInput("book", 1000));
        ProductRepository.records = Arrays.asList(product.clone());
        ProductPublishInput input = new ProductPublishInput(product.id);
        //when
        Product actual = new ProductPublishUsecase().run(loginUser, input);
        //then
        assertEquals(ProductStatus.PUBLISHED, actual.status);
        assertEquals(1, ProductRepository.records.size());
        assertEquals(ProductStatus.PUBLISHED, ProductRepository.records.get(0).status);
    }

}

