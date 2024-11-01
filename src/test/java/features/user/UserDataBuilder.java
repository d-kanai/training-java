package features.user;

import features.user.domain.User;
import features.user.domain.UserPlan;
import features.user.domain.UserRepository;
import shared.SqliteDatabase;

import java.util.UUID;

public class UserDataBuilder {

    private SqliteDatabase sqliteDatabase = new SqliteDatabase();

    private UserPlan userPlan = UserPlan.STANDARD;


    public UserDataBuilder setUserPlan(UserPlan userPlan) {
        this.userPlan = userPlan;
        return this;
    }

    public User please() {
        User user = User.reconstruct(
                UUID.randomUUID(),
                "tanaka",
                userPlan
        );
        sqliteDatabase.execute(String.format(
                "INSERT INTO users (id, email, memberShip) VALUES ('%s', '%s', '%s')",
                user.id().toString(),
                user.name(),
                user.userPlan()
        ));
        return user;
    }

}

