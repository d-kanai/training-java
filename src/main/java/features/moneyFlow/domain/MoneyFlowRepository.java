package features.moneyFlow.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.*;

public class MoneyFlowRepository {
    private SqliteDatabase db = new SqliteDatabase();

    public boolean create(MoneyFlow moneyFlow) {
        db.execute(String.format(
                "INSERT INTO moneyFlows (id, userId, value) VALUES ('%s', '%s', '%d')",
                moneyFlow.id(),
                moneyFlow.userId(),
                moneyFlow.value()
        ));
        return true;
    }

    public MoneyFlows findByUserId(UUID loginUserId) {
        Records records = db.find(String.format(
                "select * from moneyFlows where userId = '%s'",
                loginUserId
        ));
        List<MoneyFlow> list = new ArrayList<>();
        records.items.forEach(record -> {
            Map map = (Map) record;
            list.add(MoneyFlow.reconstruct(
                    UUID.fromString((String) map.get("id")),
                    UUID.fromString((String) map.get("userId")),
                    (Integer) map.get("value"))
            );

        });
        return new MoneyFlows(list);
    }
}
