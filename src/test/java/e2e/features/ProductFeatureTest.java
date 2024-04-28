package e2e.features;

import org.junit.jupiter.api.Test;

import static e2e.steps.ProductSteps.*;

public class ProductFeatureTest {


    @Test
    void ユーザが商品を公開する() {
        ユーザが新規登録してログイン();
        ユーザが商品をドラフトで登録();
        ユーザが商品を公開();
        商品が公開して登録されていること();
    }


}
