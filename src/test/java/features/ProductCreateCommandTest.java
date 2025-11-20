package features;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Records;
import shared.SqliteDatabase;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCreateCommandTest {
    private SqliteDatabase db;
    private ProductCreateCommand command;

    @BeforeEach
    public void setUp() {
        db = new SqliteDatabase();
        command = new ProductCreateCommand();
    }

    @Test
    public void 商品を作成するとDRAFTステータスで登録される() {
        // given input
        String sessionUserId = "user-123";
        ProductCreateCommandInput input = new ProductCreateCommandInput(
            "Test Product",
            1000
        );

        // when
        String productId = command.execute(sessionUserId, input);

        // then response
        assertNotNull(productId);
        assertFalse(productId.isEmpty());

        // then db change
        Records results = db.find("SELECT * FROM products WHERE id = '" + productId + "';");
        assertEquals(1, results.size());
        Map<String, Object> product = results.first();
        assertEquals(productId, product.get("id"));
        assertEquals(sessionUserId, product.get("userId"));
        assertEquals(input.productName, product.get("name"));
        assertEquals(input.price, product.get("price"));
        assertEquals("DRAFT", product.get("status"));
    }

}
