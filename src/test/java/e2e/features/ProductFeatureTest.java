package e2e.features;

import e2e.steps.UserContext;
import features.money.CurrentMoneyUsecase;
import features.money.application.MoneyChargeUsecase;
import features.money.presentation.MoneyChargeInput;
import features.product.application.ProductPurchaseUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.domain.ProductStatus;
import features.product.presentation.ProductPurchaseInput;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static e2e.steps.ProductSteps.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductFeatureTest {


    @Test
    void ユーザが商品を公開する() {
        ユーザが新規登録してログイン();
        ユーザが商品をドラフトで登録();
        ユーザが商品を公開();
        商品が公開して登録されていること();
    }

    @Test
    void ユーザが商品を購入する() {
        ユーザが新規登録してログイン();
        _5000円の商品が登録されている();
        ユーザが10000万円チャージする();
//        ユーザが商品を一覧を見る（）;
//        ユーザが商品を選択する（）;
        ユーザが購入する();
//        商品在庫が0になっている();
        ユーザのチャージ残高が5000円になっている();
    }

    private void ユーザのチャージ残高が5000円になっている() {
        assertEquals(5000, new CurrentMoneyUsecase().run(UserContext.loginUserId));
    }

    private void ユーザが購入する() {
        new ProductPurchaseUsecase().run(UserContext.loginUserId, new ProductPurchaseInput(ProductRepository.records.get(0).id));
    }

    private void ユーザが10000万円チャージする() {
        new MoneyChargeUsecase().run(UserContext.loginUserId, new MoneyChargeInput(10000));
    }

    private void _5000円の商品が登録されている() {
        ProductRepository.records = Arrays.asList(Product.reconstruct(UUID.randomUUID(), UserContext.loginUserId, ProductStatus.PUBLISHED, "book", 5000));
    }

}
