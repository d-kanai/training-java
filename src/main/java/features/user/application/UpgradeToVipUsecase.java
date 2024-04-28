package features.user.application;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.domain.MoneyFlows;
import features.user.domain.User;
import features.user.domain.UserRepository;

import java.util.UUID;

public class UpgradeToVipUsecase {

    private final MoneyFlowRepository moneyFlowRepository;
    private final UserRepository userRepository;

    public UpgradeToVipUsecase() {
        userRepository = new UserRepository();
        //TODO: 相互依存だけど・・・どうする
        moneyFlowRepository = new MoneyFlowRepository();
    }

    public void run(UUID loginUserId) {
        User user = userRepository.findById(loginUserId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserId(user.id);

        user.upgradeToVip(moneyFlows);

        userRepository.save(user);
    }
}
