package features.moneyFlows.infra;

import shared.SqliteDatabase;
import features.moneyFlows.domain.MoneyFlow;

public class MoneyFlowRepository {
    public void save(MoneyFlow moneyFlow) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into moneyFlows (id, value) values ('%s', '%d');",
                moneyFlow.id(),
                moneyFlow.value()
        ));
    }
}
