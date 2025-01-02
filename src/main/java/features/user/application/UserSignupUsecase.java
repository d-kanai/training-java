package features.user.application;

import features.user.domain.User;
import features.user.domain.UserFactory;
import features.user.domain.UserRepository;
import features.user.presentation.UserSignupInput;

//・Value Object
//・不変条件
//・集約
//・const, cohesion

public class UserSignupUsecase {

    public User run(UserSignupInput input) {
        User user = new UserFactory().signup(input.getEmail());
        new UserRepository().save(user);
        return user;
    }

}
