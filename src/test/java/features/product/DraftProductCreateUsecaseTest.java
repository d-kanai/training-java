package features.product;

import features.user.domain.User;
import features.user.UserDataBuilder;
import helpers.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import features.product.application.ProductCreateUsecase;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;
import shared.Records;
import shared.SqliteDatabase;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DraftProductCreateUsecaseTest {

    SqliteDatabase sqliteDatabase = new SqliteDatabase();

    @BeforeEach
    void setup() {
        TestHelper.resetTables();
    }

    @Test
    void Draft登録() {
        //given
        User loginUser = new UserDataBuilder().please();
        ProductCreateInput input = new ProductCreateInput("book", 1000);
        //when
        Product product = new ProductCreateUsecase().run(loginUser.id(), input);
        //then
        Records records = sqliteDatabase.find("select * from products");
        assertEquals(1, records.size());
        assertEquals(1000, records.first().get("price"));

        //TODO: 削除
        assertEquals("book", product.name());
        assertEquals(1, ProductRepository.records.size());
    }

}

