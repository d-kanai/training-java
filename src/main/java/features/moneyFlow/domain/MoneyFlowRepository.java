package features.moneyFlow.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MoneyFlowRepository {
    public static List<MoneyFlow> records = new ArrayList();

    public boolean save(MoneyFlow moneyFlow) {
        records.add(moneyFlow);
        return true;
    }

    public MoneyFlows findByUserId(UUID loginUserId) {
        List<MoneyFlow> list = records.stream().filter(o -> o.userId() == loginUserId).collect(Collectors.toList());
        return new MoneyFlows(list);
    }
}
