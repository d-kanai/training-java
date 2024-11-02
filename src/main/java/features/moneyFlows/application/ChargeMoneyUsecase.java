package features.moneyFlows.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.moneyFlows.presentation.ChargeMoneyInput;

public class ChargeMoneyUsecase {
    public void run(ChargeMoneyInput input) {
        int value = input.getValue();
        if (value < 0) {
            throw new RuntimeException("マイナス額はチャージできません");
        }
        MoneyFlow moneyFlow = new MoneyFlow(value);
        new MoneyFlowRepository().save(moneyFlow);
    }
}