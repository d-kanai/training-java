package helpers;

import org.junit.jupiter.api.BeforeEach;
import shared.SqliteDatabase;

public class BaseTest {

    public SqliteDatabase db = new SqliteDatabase();

    @BeforeEach
    void setup() {
        SqliteDatabase db = new SqliteDatabase();
        db.execute("delete from products;");
        db.execute("delete from orders;");
        db.execute("delete from moneyFlows;");
    }
}
