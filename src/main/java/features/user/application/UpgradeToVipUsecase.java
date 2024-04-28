package features.user.application;

import features.user.domain.User;
import features.user.domain.UserRepository;

import java.util.UUID;

public class UpgradeToVipUsecase {

    private UserRepository userRepository;

    public UpgradeToVipUsecase() {
        userRepository = new UserRepository();
    }

    public void run(UUID loginUserId) {
        User user = userRepository.findById(loginUserId);
        user.upgradeToVip();
        userRepository.save(user);
    }
}
