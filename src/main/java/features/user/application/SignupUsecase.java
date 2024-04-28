package features.user.application;

import features.user.presentation.SignupInput;
import features.user.domain.User;
import features.user.domain.UserRepository;

public class SignupUsecase {

    private UserRepository userRepository;

    public SignupUsecase() {
        userRepository = new UserRepository();
    }

    public User run(SignupInput signupInput) {
        User user = User.signup(signupInput);
        userRepository.save(user);
        return user;
    }
}
