package features.user.domain;

import features.product.domain.DraftProduct;
import features.product.domain.Product;
import shared.Records;
import shared.SqliteDatabase;

import java.util.Map;
import java.util.UUID;

public class UserRepository {

    SqliteDatabase db = new SqliteDatabase();

    public void save(User user) {
        db.execute(String.format(
                "insert into users (id, email, plan) values ('%s', '%s', '%s') " +
                        "ON CONFLICT(id) DO " +
                        "UPDATE SET email = excluded.email, plan = excluded.plan;",
                user.id(),
                user.email(),
                user.plan()
        ));
    }

    public User findById(UUID loginUserId) {
        Records records = db.find(String.format("select * from users where id = '%s'", loginUserId));
        Map record = records.first();
        return User.reconstruct(
                UUID.fromString((String) record.get("id")),
                (String) record.get("name"),
                User.Plan.fromString((String) record.get("plan"))
        );

    }
}
