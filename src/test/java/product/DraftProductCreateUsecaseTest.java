package product;

import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import org.junit.jupiter.api.Test;
import product.application.ProductCreateUsecase;
import product.domain.ProductRepository;
import product.presentation.ProductCreateInput;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DraftProductCreateUsecaseTest {

    @Test
    void Draft登録() {
        //given
        User loginUser = User.signup(new SignupInput("tanaka"));
        UserRepository.records = Arrays.asList(loginUser);
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
        boolean actual = new ProductCreateUsecase().run(loginUser.id, input);
        //then
        assertEquals(true, actual);
        assertEquals(1, ProductRepository.records.size());
    }

}

