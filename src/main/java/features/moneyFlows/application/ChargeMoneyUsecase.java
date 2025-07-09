package features.moneyFlows.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.infra.MoneyFlowRepository;
import features.moneyFlows.presentation.ChargeMoneyInput;

public class ChargeMoneyUsecase {
    public void run(ChargeMoneyInput input) {
        MoneyFlow moneyFlow = MoneyFlow.charge(input.getValue());
        new MoneyFlowRepository().save(moneyFlow);
    }
}