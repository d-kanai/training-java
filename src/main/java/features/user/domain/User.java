package features.user.domain;

import features.user.presentation.SignupInput;

import java.util.UUID;

public class User {
    public UUID id;
    public final String name;

    private User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static User signup(SignupInput signupInput) {
        return new User(
                UUID.randomUUID(),
                signupInput.name
        );
    }
}
