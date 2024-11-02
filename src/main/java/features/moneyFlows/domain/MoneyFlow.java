package features.moneyFlows.domain;

import java.util.UUID;

public class MoneyFlow {

    private final UUID id;
    private final int value;

    public MoneyFlow(int value) {
        this.id = UUID.randomUUID();
        this.value = value;
    }

    public UUID id() {
        return id;
    }

    public int value() {
        return value;
    }
}
