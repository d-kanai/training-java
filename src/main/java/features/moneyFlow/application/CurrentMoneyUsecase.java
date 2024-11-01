package features.moneyFlow.application;

import features.moneyFlow.domain.MoneyFlows;
import features.moneyFlow.domain.MoneyFlowRepository;

import java.util.UUID;

public class CurrentMoneyUsecase {

    private MoneyFlowRepository moneyFlowRepository;

    public CurrentMoneyUsecase() {
        moneyFlowRepository = new MoneyFlowRepository();
    }

    public int run(UUID loginUserId) {
        MoneyFlows moneyFlows = moneyFlowRepository.findByUserIdFromDb(loginUserId);
        return moneyFlows.currentValue();

    }
}
