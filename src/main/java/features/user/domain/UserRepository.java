package features.user.domain;

import shared.SqliteDatabase;

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
}
