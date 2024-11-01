package features.moneyFlow;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlowRepository;
import shared.SqliteDatabase;

import java.util.UUID;

public class MoneyFlowDataBuilder {

    private SqliteDatabase sqliteDatabase = new SqliteDatabase();

    public MoneyFlowDataBuilder(UUID userId) {
        this.userId = userId;
    }

    private UUID userId;
    private int price = 10000;

    public MoneyFlowDataBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public MoneyFlow please() {
        MoneyFlow moneyFlow = MoneyFlow.reconstruct(
                UUID.randomUUID(),
                userId,
                price
        );
        sqliteDatabase.execute(String.format(
                "INSERT INTO moneyFlows (id, userId, value) VALUES ('%s', '%s', '%d')",
                moneyFlow.id(),
                moneyFlow.userId(),
                moneyFlow.value()
        ));
        return moneyFlow;
    }

}

