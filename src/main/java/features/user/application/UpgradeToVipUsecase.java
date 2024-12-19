package features.user.application;

import features.user.domain.User;
import features.user.domain.UserRepository;

import java.util.UUID;

//・model test ...? test strategy
//・Design by Type MORE!!
//・Each Status Class
//・validation ? no, make Class. その振る舞いは誰ができるのか？

public class UpgradeToVipUsecase {

    UserRepository userRepository = new UserRepository();

    public void run(UUID loginUserId) {
        User user = userRepository.findById(loginUserId);
        user.upgradeToVip();
        userRepository.save(user);
    }
}
