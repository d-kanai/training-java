package features.product;

import features.product.application.ProductCreateUsecase;
import features.product.presentation.ProductCreateInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Records;
import shared.SqliteDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCreateUsecaseTest {

    public SqliteDatabase db = new SqliteDatabase();

    @BeforeEach
    void setup() {
        db.execute("delete from products;");
    }

    @Test
    void 商品登録() {
        //given
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
        new ProductCreateUsecase().run(input);
        //then
        Records records = db.find("select * from products");
        assertEquals(1, records.size());
        assertEquals(1000, records.first().get("price"));
    }


}

