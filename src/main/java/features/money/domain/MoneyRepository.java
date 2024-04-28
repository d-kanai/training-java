package features.money.domain;

import java.util.ArrayList;
import java.util.List;

public class MoneyRepository {
    public static List<Money> records = new ArrayList();

    public boolean save(Money money) {
        records.add(money);
        return true;
    }

}
