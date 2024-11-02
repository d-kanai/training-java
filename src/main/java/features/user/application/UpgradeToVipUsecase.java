package features.user.application;

import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.UserSignupInput;

import java.util.UUID;

public class UpgradeToVipUsecase {

    UserRepository userRepository = new UserRepository();

    public void run(UUID loginUserId) {
        User user = userRepository.findById(loginUserId);
        user.upgradeToVip();
        userRepository.save(user);
    }
}
