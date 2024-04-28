package features.user.application;

import features.user.domain.User;
import features.user.domain.UserRepository;

import java.util.List;

public class UserListUsecase {

    private UserRepository userRepository;

    public UserListUsecase() {
        userRepository = new UserRepository();
    }

    public List<User> run() {
        return userRepository.findAll();
    }
}
