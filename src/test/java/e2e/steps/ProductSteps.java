package e2e.steps;

import features.product.application.ProductCreateUsecase;
import features.product.application.ProductPublishUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.domain.ProductStatus;
import features.product.presentation.ProductCreateInput;
import features.product.presentation.ProductPublishInput;
import features.user.application.SignupUsecase;
import features.user.domain.User;
import features.user.presentation.SignupInput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSteps {

    public static void ユーザが新規登録してログイン() {
        SignupInput signupInput = new SignupInput("d.kanai");
        User user = new SignupUsecase().run(signupInput);
        UserContext.loginUserId = user.id;
    }

    public static void ユーザが商品をドラフトで登録() {
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        new ProductCreateUsecase().run(UserContext.loginUserId, input);
    }

    public static void ユーザが商品を公開() {
        Product product = ProductRepository.records.get(0);
        new ProductPublishUsecase().run(UserContext.loginUserId, new ProductPublishInput(product.id));
    }

    public static void 商品が公開して登録されていること() {
        assertEquals(ProductStatus.PUBLISHED, ProductRepository.records.get(0).status);
    }

}
