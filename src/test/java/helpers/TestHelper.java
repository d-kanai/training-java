package helpers;

import shared.SqliteDatabase;

public class TestHelper {

    public static void resetTables() {
        SqliteDatabase sqliteDatabase = new SqliteDatabase();
        sqliteDatabase.execute("delete from moneyFlows;");
        sqliteDatabase.execute("delete from products;");
        sqliteDatabase.execute("delete from users;");
        sqliteDatabase.execute("delete from orders;");
    }
}
