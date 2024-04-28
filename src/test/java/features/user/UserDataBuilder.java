package features.user;

import features.user.domain.User;
import features.user.domain.UserPlan;
import features.user.domain.UserRepository;

import java.util.UUID;

public class UserDataBuilder {

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
        UserRepository.records.add(user);
        return user;
    }

}

