package features.moneyFlow;

import features.moneyFlows.domain.MoneyFlow;
import features.moneyFlows.domain.MoneyFlowRepository;

public class MoneyFlowDataBuilder {
    private int value = 1000;

    public MoneyFlow please() {
        MoneyFlow moneyFlow = MoneyFlow.charge(value);
        new MoneyFlowRepository().save(moneyFlow);
        return moneyFlow;
    }

    public MoneyFlowDataBuilder value(int value) {
        this.value = value;
        return this;
    }
}
