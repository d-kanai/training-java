package e2e.steps;

import features.moneyFlow.application.CurrentMoneyUsecase;
import features.moneyFlow.application.MoneyChargeUsecase;
import features.moneyFlow.presentation.MoneyChargeInput;
import features.product.domain.PublishedProduct;
import features.order.application.OrderHistoryUsecase;
import features.order.domain.Order;
import features.product.application.ProductCreateUsecase;
import features.product.application.ProductPublishUsecase;
import features.order.application.NewOrderForStandardUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.domain.ProductStatus;
import features.product.presentation.ProductCreateInput;
import features.product.presentation.ProductPublishInput;
import features.product.presentation.NewOrderInput;
import features.user.application.SignupUsecase;
import features.user.domain.User;
import features.user.presentation.SignupInput;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSteps {

    public static void ユーザが新規登録してログイン() {
        SignupInput signupInput = new SignupInput("d.kanai");
        User user = new SignupUsecase().run(signupInput);
        UserContext.loginUserId = user.id();
    }

    public static void ユーザが商品をドラフトで登録() {
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        new ProductCreateUsecase().run(UserContext.loginUserId, input);
    }

    public static void ユーザが商品を公開() {
        Product product = ProductRepository.records.get(0);
        new ProductPublishUsecase().run(UserContext.loginUserId, new ProductPublishInput(product.id()));
    }

    public static void 商品が公開して登録されていること() {
        assertEquals(ProductStatus.PUBLISHED, ProductRepository.records.get(0).status());
    }

    public static void ユーザのチャージ残高が5000円になっている() {
        assertEquals(5000, new CurrentMoneyUsecase().run(UserContext.loginUserId));
    }

    public static void ユーザが購入する() {
        new NewOrderForStandardUsecase().run(UserContext.loginUserId, new NewOrderInput(ProductRepository.records.get(0).id()));
    }

    public static void ユーザが10000万円チャージする() {
        new MoneyChargeUsecase().run(UserContext.loginUserId, new MoneyChargeInput(10000));
    }

    public static void _5000円の商品が登録されている() {
        ProductRepository.records = Arrays.asList(PublishedProduct.reconstruct(UUID.randomUUID(), UserContext.loginUserId, ProductStatus.PUBLISHED, "book", 5000));
    }

    public static void 購入履歴一覧に商品が表示されている() {
        List<Order> run = new OrderHistoryUsecase().run(UserContext.loginUserId);
        assertEquals("book", run.get(0).product().name());

    }

}
