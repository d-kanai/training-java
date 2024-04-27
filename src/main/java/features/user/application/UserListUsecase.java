package features.user.application;

import features.user.domain.User;
import features.user.domain.UserRepository;

import java.util.List;

public class UserListUsecase {

    public List<User> run() {
        return new UserRepository().findAll();
    }
}
