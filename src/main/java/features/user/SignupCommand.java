package features.user;

public class SignupCommand {

    public boolean run(SignupInput signupInput) {
        User user = User.signup(signupInput);
        return new UserRepository().save(user);
    }
}
