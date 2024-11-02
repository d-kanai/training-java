package features.moneyFlows.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.moneyFlows.presentation.ChargeMoneyInput;

public class ChargeMoneyUsecase {
    public void run(ChargeMoneyInput input) {
        MoneyFlow moneyFlow = new MoneyFlow(input.getValue());
        new MoneyFlowRepository().save(moneyFlow);
    }
}