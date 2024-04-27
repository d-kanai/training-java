package e2e;

import features.user.application.SignupUsecase;
import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import org.junit.jupiter.api.Test;
import product.application.ProductCreateUsecase;
import product.application.ProductPublishUsecase;
import product.domain.Product;
import product.domain.ProductRepository;
import product.domain.ProductStatus;
import product.presentation.ProductCreateInput;
import product.presentation.ProductPublishInput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductE2ETest {

    @Test
    void ユーザが商品を公開する() {
        ユーザが新規登録();
        ユーザが商品をドラフトで登録();
        ユーザが商品を公開();
    }

    private void ユーザが新規登録() {
        SignupInput signupInput = new SignupInput("d.kanai");
        new SignupUsecase().run(signupInput);
    }

    private Product ユーザが商品をドラフトで登録() {
        User loginUser = UserRepository.records.get(0);
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        return new ProductCreateUsecase().run(loginUser.id, input);
    }

    private void ユーザが商品を公開() {
        User loginUser = UserRepository.records.get(0);
        Product product = ProductRepository.records.get(0);
        new ProductPublishUsecase().run(loginUser.id, new ProductPublishInput(product.id));
        assertEquals(ProductStatus.PUBLISHED, ProductRepository.records.get(0).status);
    }

}
