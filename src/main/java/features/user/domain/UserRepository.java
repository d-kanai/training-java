package features.user.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.*;

public class UserRepository {

    private SqliteDatabase db = new SqliteDatabase();

    public boolean create(User user) {
        db.execute(String.format(
                "insert into users (id, email, memberShip) VALUES ('%s', '%s', '%s')",
                user.id().toString(),
                user.email(),
                user.userPlan().toString()
        ));
        return true;
    }

    public boolean update(User user) {
        db.execute(String.format(
                "update users set email = '%s', memberShip = '%s'",
                user.email(),
                user.userPlan().toString()
        ));
        return true;
    }

    public StandardUser findStandardById(UUID loginUserId) {
        Records records = db.find(String.format(
                "select * from users where id = '%s'",
                loginUserId.toString()
        ));
        if (records.size() == 0) {
            throw new RuntimeException("STANDARD ユーザが存在しません");
        }
        Map o = (Map) records.items.get(0);
        return StandardUser.reconstruct(UUID.fromString((String) o.get("id")), (String) o.get("email"), UserPlan.fromString((String) o.get("memberShip")));
    }

    public VipUser findVipById(UUID loginUserId) {
        Records records = db.find(String.format(
                "select * from users where id = '%s'",
                loginUserId.toString()
        ));
        if (records.size() == 0) {
            throw new RuntimeException("VIP ユーザが存在しません");
        }
        Map o = (Map) records.items.get(0);
        return VipUser.reconstruct(UUID.fromString((String) o.get("id")), (String) o.get("email"), UserPlan.fromString((String) o.get("memberShip")));
    }
}
