package features.money.application;

import features.money.domain.Monies;
import features.money.domain.MoneyRepository;

import java.util.UUID;

public class CurrentMoneyUsecase {
    public int run(UUID loginUserId) {
        Monies monies = new MoneyRepository().findByUserId(loginUserId);
        return monies.totalValue();

    }
}
