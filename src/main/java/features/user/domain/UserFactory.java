package features.user.domain;

import java.util.UUID;

public class UserFactory {

    public User signup(String email) {
        return User.signup(new Email(email));
    }

    public User reconstruct(UUID id, String email, User.Plan plan) {
        return new User(id, new Email(email), plan);
    }

}
