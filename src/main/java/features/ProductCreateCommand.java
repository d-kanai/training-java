package features;

import shared.SqliteDatabase;

import java.util.UUID;

public class ProductCreateCommand {
    private final SqliteDatabase db;

    public ProductCreateCommand() {
        this.db = new SqliteDatabase();
    }

    public String execute(String sessionUserId, ProductCreateCommandInput input) {
        String productId = UUID.randomUUID().toString();
        String sql = String.format(
            "INSERT INTO products (id, userId, name, price, status) VALUES ('%s', '%s', '%s', %d, 'DRAFT');",
            productId, sessionUserId, input.productName, input.price
        );
        db.execute(sql);
        return productId;
    }
}
