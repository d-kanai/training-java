package features.moneyFlow.domain;

import features.product.domain.ProductStatus;
import features.product.domain.PublishedProduct;
import shared.Records;
import shared.SqliteDatabase;

import java.util.*;
import java.util.stream.Collectors;

public class MoneyFlowRepository {
    public static List<MoneyFlow> records = new ArrayList();
    private SqliteDatabase db = new SqliteDatabase();

    public boolean save(MoneyFlow moneyFlow) {
        db.execute(String.format(
                "INSERT INTO moneyFlows (id, userId, value) VALUES ('%s', '%s', '%d')",
                moneyFlow.id(),
                moneyFlow.userId(),
                moneyFlow.value()
        ));
        records.add(moneyFlow);
        return true;
    }

    public MoneyFlows findByUserId(UUID loginUserId) {
        List<MoneyFlow> list = records.stream().filter(o -> o.userId().equals(loginUserId)).collect(Collectors.toList());
        return new MoneyFlows(list);
    }

    public MoneyFlows findByUserIdFromDb(UUID loginUserId) {
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
