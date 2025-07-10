package features.moneyFlows.infra;

import shared.SqliteDatabase;
import features.moneyFlows.domain.MoneyFlow;
import shared.Records;

public class MoneyFlowRepository {
    public void save(MoneyFlow moneyFlow) {
        SqliteDatabase db = new SqliteDatabase();
        db.execute(String.format("insert into moneyFlows (id, value) values ('%s', '%d');",
                moneyFlow.id(),
                moneyFlow.value()
        ));
    }

    
    public int getTotalFunds() {
        SqliteDatabase db = new SqliteDatabase();
        // Logic to calculate total funds, assuming there's a method to get the totals from the database
        Records totalMoneyFlow = new SqliteDatabase().find("select sum(value) from moneyFlows");
        return (int) totalMoneyFlow.first().get("sum(value)");
    }
}
