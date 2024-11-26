package features.moneyFlows.application;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;
import features.moneyFlows.presentation.ChargeMoneyInput;

//・Static Factory
//・No no name constructor
//・completed constructor
//・domain exception. domain validation. not usecase knowledge
//・not transaction script => domain model

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