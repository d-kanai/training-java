package features.user.application;

import features.user.presentation.SignupInput;
import features.user.domain.User;
import features.user.domain.UserRepository;

public class SignupUsecase {

    public User run(SignupInput signupInput) {
        User user = User.signup(signupInput);
        new UserRepository().save(user);
        return user;
    }
}
