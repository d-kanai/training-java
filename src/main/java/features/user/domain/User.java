package features.user.domain;

import features.user.presentation.SignupInput;

import java.util.UUID;

public class User {
    private UUID id;
    private final String name;
    private UserPlan userPlan;
    private String email;

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public UserPlan userPlan() {
        return userPlan;
    }

    public String email() {
        return email;
    }

    protected User(UUID id, String name, UserPlan userPlan) {
        this.id = id;
        this.name = name;
        this.email = name;
        this.userPlan = userPlan;
    }

    public static User signup(SignupInput signupInput) {
        return new User(
                UUID.randomUUID(),
                signupInput.name,
                UserPlan.STANDARD
        );
    }

    public static User reconstruct(UUID uuid, String name, UserPlan userPlan) {
        return new User(
                uuid,
                name,
                userPlan
        );
    }


}
