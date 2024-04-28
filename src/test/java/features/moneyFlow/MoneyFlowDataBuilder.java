package features.moneyFlow;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlowRepository;

import java.util.UUID;

public class MoneyFlowDataBuilder {

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
        MoneyFlowRepository.records.add(moneyFlow);
        return moneyFlow;
    }

}

