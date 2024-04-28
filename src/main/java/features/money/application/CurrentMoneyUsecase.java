package features.money.application;

import features.money.domain.Monies;
import features.money.domain.MoneyRepository;

import java.util.UUID;

public class CurrentMoneyUsecase {

    private MoneyRepository moneyRepository;

    public CurrentMoneyUsecase() {
        moneyRepository = new MoneyRepository();
    }

    public int run(UUID loginUserId) {
        Monies monies = moneyRepository.findByUserId(loginUserId);
        return monies.totalValue();

    }
}
