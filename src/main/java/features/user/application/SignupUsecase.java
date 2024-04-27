package features.user.application;

import features.user.presentation.SignupInput;
import features.user.domain.User;
import features.user.domain.UserRepository;

public class SignupUsecase {

    public boolean run(SignupInput signupInput) {
        User user = User.signup(signupInput);
        return new UserRepository().save(user);
    }
}
