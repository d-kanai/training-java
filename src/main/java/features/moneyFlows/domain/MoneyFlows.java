package features.moneyFlows.domain;

import java.util.List;

public class MoneyFlows {
    private final List<MoneyFlow> items;

    public MoneyFlows(List<MoneyFlow> items) {
        this.items = items;
    }

    public int sum() {
        return items
                .stream()
                .mapToInt(MoneyFlow::value)
                .sum();
    }
}
