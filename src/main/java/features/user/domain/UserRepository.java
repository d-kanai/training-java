package features.user.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.*;

public class UserRepository {
    public static List<User> records = new ArrayList();

    private SqliteDatabase db = new SqliteDatabase();

    public boolean save(User user) {
        if (user.id() == null) {
            records.add(user);
        } else {
            update(user);
        }
        return true;
    }

    private void update(User user) {
        //Arrays.asListで作成したリストが変更不可なので、listを作り直す
        ArrayList<User> newRecords = new ArrayList<>(records);
        newRecords.removeIf(o -> o.id() == user.id());
        newRecords.add(user);
        records = newRecords;
    }

    public User findById(UUID loginUserId) {
        //TODO: メモリ保持問題が起きている
        Optional<User> first = records.stream().filter(user -> user.id() == loginUserId).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        throw new RuntimeException("ユーザが存在しません");
    }

    public StandardUser findStandardByIdFromDb(UUID loginUserId) {
        Records records = db.find(String.format(
                "select * from users where '%s'",
                loginUserId.toString()
        ));
        if (records.size() == 0) {
            throw new RuntimeException("STANDARD ユーザが存在しません");
        }
        Map o = (Map) records.items.get(0);
        return StandardUser.reconstruct(UUID.fromString((String) o.get("id")), (String) o.get("email"), UserPlan.fromString((String) o.get("memberShip")));
    }

    public StandardUser findStandardById(UUID loginUserId) {
        //TODO: メモリ保持問題が起きている
        Optional<User> first = records.stream().filter(user -> user.id() == loginUserId && user.userPlan() == UserPlan.STANDARD).findFirst();
        if (first.isPresent()) {
            User user = first.get();
            return StandardUser.reconstruct(user.id(), user.name(), user.userPlan());
        }
        throw new RuntimeException("STANDARD ユーザが存在しません");
    }

    public VipUser findVipById(UUID loginUserId) {
        //TODO: メモリ保持問題が起きている
        Optional<User> first = records.stream().filter(user -> user.id() == loginUserId && user.userPlan() == UserPlan.VIP).findFirst();
        if (first.isPresent()) {
            User user = first.get();
            return VipUser.reconstruct(user.id(), user.name(), user.userPlan());
        }
        throw new RuntimeException("VIP ユーザが存在しません");
    }
}
