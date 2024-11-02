package features.user.presentation;

public class UserSignupInput {
    private final String email;

    public String getEmail() {
        return email;
    }

    public UserSignupInput(String email) {
        this.email = email;
    }
}
