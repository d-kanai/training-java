package features.moneyFlows.domain;

import shared.SqliteDatabase;

public class MoneyFlowRepository {
    public void save(MoneyFlow moneyFlow) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into moneyFlows (id, value) values ('%s', '%d');",
                moneyFlow.id(),
                moneyFlow.value()
        ));
    }
}
