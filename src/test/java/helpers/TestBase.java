package helpers;

import org.junit.jupiter.api.BeforeEach;
import shared.SqliteDatabase;

public class TestBase {
    public SqliteDatabase db = new SqliteDatabase();

    @BeforeEach
    void setup() {
        TestHelper.resetTables();
    }

}
