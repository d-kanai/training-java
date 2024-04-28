package e2e.steps;

import features.money.application.CurrentMoneyUsecase;
import features.money.application.MoneyChargeUsecase;
import features.money.presentation.MoneyChargeInput;
import features.product.application.ProductCreateUsecase;
import features.product.application.ProductPublishUsecase;
import features.product.application.ProductPurchaseUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.domain.ProductStatus;
import features.product.presentation.ProductCreateInput;
import features.product.presentation.ProductPublishInput;
import features.product.presentation.ProductPurchaseInput;
import features.user.application.SignupUsecase;
import features.user.domain.User;
import features.user.presentation.SignupInput;

import java.util.Arrays;
import java.util.UUID;

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

    public static void ユーザのチャージ残高が5000円になっている() {
        assertEquals(5000, new CurrentMoneyUsecase().run(UserContext.loginUserId));
    }

    public static void ユーザが購入する() {
        new ProductPurchaseUsecase().run(UserContext.loginUserId, new ProductPurchaseInput(ProductRepository.records.get(0).id));
    }

    public static void ユーザが10000万円チャージする() {
        new MoneyChargeUsecase().run(UserContext.loginUserId, new MoneyChargeInput(10000));
    }

    public static void _5000円の商品が登録されている() {
        ProductRepository.records = Arrays.asList(Product.reconstruct(UUID.randomUUID(), UserContext.loginUserId, ProductStatus.PUBLISHED, "book", 5000));
    }

}
