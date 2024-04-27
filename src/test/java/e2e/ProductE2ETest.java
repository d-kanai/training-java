package e2e;

import features.user.application.SignupUsecase;
import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import org.junit.jupiter.api.Test;
import product.application.ProductCreateUsecase;
import product.domain.Product;
import product.presentation.ProductCreateInput;

public class ProductE2ETest {

    @Test
    void ユーザが商品を公開する() {
        ユーザを登録();
        ユーザが商品をドラフトで登録する();
        //Then ユーザが商品を公開する

    }

    private Product ユーザが商品をドラフトで登録する() {
        User loginUser = UserRepository.records.get(0);
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        return new ProductCreateUsecase().run(loginUser.id, input);
    }

    private void ユーザを登録() {
        SignupInput signupInput = new SignupInput("d.kanai");
        new SignupUsecase().run(signupInput);
    }
}
