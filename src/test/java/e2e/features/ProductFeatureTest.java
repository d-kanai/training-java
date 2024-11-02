package e2e.features;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.SqliteDatabase;

import static e2e.steps.ProductSteps.*;

public class ProductFeatureTest {

    public SqliteDatabase db = new SqliteDatabase();

    @BeforeEach
    void setup() {
        SqliteDatabase db = new SqliteDatabase();
        db.execute("delete from products;");
        db.execute("delete from orders;");
    }

    @Test
    void ユーザが商品を登録する() {
//        ユーザが新規登録してログイン();
//        ユーザが商品をドラフトで登録();
//        ユーザが商品を公開();
//        商品が公開して登録されていること();
    }

    @Test
    void ユーザが商品を購入する() {
//        ユーザが新規登録してログイン();
        _5000円の商品が登録されている();
//        ユーザが10000万円チャージする();
//        ユーザが商品を一覧を見る();
//        ユーザが商品を選択する();
        ユーザが購入する();
//        ユーザのチャージ残高が5000円になっている();
//        購入履歴一覧に商品が表示されている();
    }


}
