package features.user.application;

import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.UserSignupInput;

public class UserSignupUsecase {


    public void run(UserSignupInput input) {
        User user = User.signup(input.getEmail());
        new UserRepository().save(user);
    }
}
