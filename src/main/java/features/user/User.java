package features.user;

public class User {
    public final String name;

    public User(String name) {
        this.name = name;
    }

    public static User signup(SignupInput signupInput) {
        return new User(signupInput.name);
    }
}
