package features.user;

import features.user.domain.User;
import features.user.domain.UserRepository;

import java.util.UUID;

public class UserDataBuilder {

    private User.Plan plan = User.Plan.NORMAL;

    public User please() {
        User user = User.reconstruct(UUID.randomUUID(), "kanai@test.com", plan);
        new UserRepository().save(user);
        return user;
    }

    public UserDataBuilder plan(User.Plan plan) {
        this.plan = plan;
        return this;
    }
}
