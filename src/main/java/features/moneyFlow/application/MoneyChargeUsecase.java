package features.moneyFlow.application;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlowRepository;
import features.moneyFlow.presentation.MoneyChargeInput;

import java.util.UUID;

public class MoneyChargeUsecase {

    private MoneyFlowRepository moneyFlowRepository;

    public MoneyChargeUsecase() {
        moneyFlowRepository = new MoneyFlowRepository();
    }

    public void run(UUID userId, MoneyChargeInput input) {
        MoneyFlow charge = MoneyFlow.charge(userId, input.value);
        moneyFlowRepository.save(charge);
    }
}
