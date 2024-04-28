package features.user.domain;

import features.user.presentation.SignupInput;

import java.util.UUID;

public class User {
    public UUID id;
    public final String name;
    public UserPlan userPlan;

    private User(UUID id, String name, UserPlan userPlan) {
        this.id = id;
        this.name = name;
        this.userPlan = userPlan;
    }

    public static User signup(SignupInput signupInput) {
        return new User(
                UUID.randomUUID(),
                signupInput.name,
                UserPlan.STANDARD
        );
    }

    public void upgradeToVip() {
        userPlan = UserPlan.VIP;
    }
}
