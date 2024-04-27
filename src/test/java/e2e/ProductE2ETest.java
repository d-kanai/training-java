package e2e;

import features.user.application.SignupUsecase;
import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import org.junit.jupiter.api.Test;
import features.product.application.ProductCreateUsecase;
import features.product.application.ProductPublishUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.domain.ProductStatus;
import features.product.presentation.ProductCreateInput;
import features.product.presentation.ProductPublishInput;

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
