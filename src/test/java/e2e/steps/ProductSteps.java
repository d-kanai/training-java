package e2e.steps;

import features.moneyFlows.application.ChargeMoneyUsecase;
import features.moneyFlows.presentation.ChargeMoneyInput;
import features.order.application.OrderUsecase;
import features.order.presentation.OrderCreateInput;
import features.product.application.ProductCreateAsDraftUsecase;
import features.product.application.ProductPublishUsecase;
import features.product.domain.Product;
import features.product.presentation.ProductCreateInput;
import features.product.presentation.ProductPublishInput;
import shared.Records;
import shared.SqliteDatabase;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSteps {


    public static void ユーザが新規登録してログイン() {
    }

    public static void ユーザが商品をドラフトで登録() {
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        new ProductCreateAsDraftUsecase().run(input);
    }

    public static void ユーザが商品を公開() {
        Records products = new SqliteDatabase().find("select * from products");
        ProductPublishInput input = new ProductPublishInput(UUID.fromString((String) products.first().get("id")));
        new ProductPublishUsecase().run(input);
    }

    public static void 商品が公開して登録されていること() {
        Records products = new SqliteDatabase().find("select * from products");
        assertEquals(products.first().get("status"), Product.Status.PUBLISHED.toString());
    }

    public static void ユーザのチャージ残高が5000円になっている() {
    }

    public static void ユーザが購入する() {
        Records products = new SqliteDatabase().find("select * from products");
        new OrderUsecase().run(new OrderCreateInput(UUID.fromString((String) products.first().get("id"))));
        Records orders = new SqliteDatabase().find("select * from orders");
        assertEquals(1, orders.size()); // middle assertion
        Records moneyFlows = new SqliteDatabase().find("select * from moneyFlows");
        assertEquals(2, moneyFlows.size()); // middle assertion
    }

    public static void ユーザが10000万円チャージする() {
        ChargeMoneyInput input = new ChargeMoneyInput(10000);
        new ChargeMoneyUsecase().run(input);
    }

    public static void ユーザが商品を一覧を見る() {
    }

    public static void ユーザが商品を選択する() {
    }

    public static void _5000円の商品が登録されている() {
        new ProductCreateAsDraftUsecase().run(new ProductCreateInput("book", 5000));
        Records products = new SqliteDatabase().find("select * from products");
        ProductPublishInput input = new ProductPublishInput(UUID.fromString((String) products.first().get("id")));
        new ProductPublishUsecase().run(input);
    }

    public static void 購入履歴一覧に商品が表示されている() {

    }

}
