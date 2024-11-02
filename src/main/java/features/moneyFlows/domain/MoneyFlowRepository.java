package features.moneyFlows.domain;

import shared.Records;
import shared.SqliteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MoneyFlowRepository {

    SqliteDatabase db = new SqliteDatabase();

    public void save(MoneyFlow moneyFlow) {
        db.execute(String.format("insert into moneyFlows (id, value) values ('%s', '%d');",
                moneyFlow.id(),
                moneyFlow.value()
        ));
    }

    public MoneyFlows findAll() {
        Records records = db.find("select * from moneyFlows");
        List<MoneyFlow> moneyFlowList = new ArrayList<>();
        records.items.forEach(record -> {
            Map mapRecord = (Map) record;
            moneyFlowList.add(MoneyFlow.reconstruct
                    (UUID.fromString((String) mapRecord.get("id")),
                            (Integer) mapRecord.get("value")
                    )
            );
        });
        return new MoneyFlows(moneyFlowList);
    }
}
