package features.user.domain;

import features.user.presentation.SignupInput;

public class User {
    public final String name;

    public User(String name) {
        this.name = name;
    }

    public static User signup(SignupInput signupInput) {
        return new User(signupInput.name);
    }
}
