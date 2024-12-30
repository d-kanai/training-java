package features.user.application;

import features.user.domain.Email;
import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.UserSignupInput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//・Value Object
//・不変条件
//・集約
//・const, cohesion

public class UserSignupUsecase {

    public User run(UserSignupInput input) {
        User user = User.signup(input.getEmail());
        new UserRepository().save(user);
        return user;
    }

}
