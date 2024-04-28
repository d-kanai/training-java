package features.money.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MoneyRepository {
    public static List<Money> records = new ArrayList();

    public boolean save(Money money) {
        records.add(money);
        return true;
    }

    public Monies findByUserId(UUID loginUserId) {
        List<Money> list = records.stream().filter(o -> o.userId == loginUserId).collect(Collectors.toList());
        return new Monies(list);
    }
}
