package features.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {
    public static List<User> records = new ArrayList();

    public boolean save(User user) {
        if (user.id == null) {
            records.add(user);
        } else {
            update(user);
        }
        return true;
    }

    private void update(User user) {
        //Arrays.asListで作成したリストが変更不可なので、listを作り直す
        ArrayList<User> newRecords = new ArrayList<>(records);
        newRecords.removeIf(o -> o.id == user.id);
        newRecords.add(user);
        records = newRecords;
    }

    public User findById(UUID loginUserId) {
        //TODO: メモリ保持問題が起きている
        Optional<User> first = records.stream().filter(user -> user.id == loginUserId).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        throw new RuntimeException("ユーザが存在しません");
    }
    public StandardUser findStandardById(UUID loginUserId) {
        //TODO: メモリ保持問題が起きている
        Optional<User> first = records.stream().filter(user -> user.id == loginUserId && user.userPlan == UserPlan.STANDARD).findFirst();
        if (first.isPresent()) {
            User user = first.get();
            return StandardUser.reconstruct(user.id, user.name, user.userPlan);
        }
        throw new RuntimeException("STANDARD ユーザが存在しません");
    }
}
