package features.order.domain;

import features.moneyFlows.domain.MoneyFlow;

public class Ordered {
    public final Order order;
    public final MoneyFlow moneyFlow;

    public Ordered(Order order, MoneyFlow moneyFlow) {
        this.order = order;
        this.moneyFlow = moneyFlow;
    }
}
