package e2e.steps;

import features.order.application.OrderCreateUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.application.ProductCreateUsecase;
import features.product.presentation.ProductCreateInput;
import shared.Records;
import shared.SqliteDatabase;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSteps {


    public static void ユーザが新規登録してログイン() {
    }

    public static void ユーザが商品をドラフトで登録() {
    }

    public static void ユーザが商品を公開() {
    }

    public static void 商品が公開して登録されていること() {
    }

    public static void ユーザのチャージ残高が5000円になっている() {
    }

    public static void ユーザが購入する() {
        Records products = new SqliteDatabase().find("select * from products");
        new OrderCreateUsecase().run(new OrderCreateInput(UUID.fromString((String) products.first().get("id"))));
        Records orders = new SqliteDatabase().find("select * from orders");
        assertEquals(1, orders.size()); // middle assertion
    }

    public static void ユーザが10000万円チャージする() {
    }

    public static void ユーザが商品を一覧を見る() {
    }

    public static void ユーザが商品を選択する() {
    }

    public static void _5000円の商品が登録されている() {
        new ProductCreateUsecase().run(new ProductCreateInput("book", 5000));
    }

    public static void 購入履歴一覧に商品が表示されている() {

    }

}
