package features.user.application;

import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.domain.MoneyFlows;
import features.user.domain.*;

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
        StandardUser user = userRepository.findStandardByIdFromDb(loginUserId);
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserIdFromDb(user.id());
        VipUser vipUser = user.challengeVip(moneyFlows).upgradeToVip();
        userRepository.update2(vipUser);
    }
}
