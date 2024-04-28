package features.money.application;

import features.money.domain.Money;
import features.money.domain.MoneyRepository;
import features.money.presentation.MoneyChargeInput;

import java.util.UUID;

public class MoneyChargeUsecase {

    private MoneyRepository moneyRepository;

    public MoneyChargeUsecase() {
        moneyRepository = new MoneyRepository();
    }

    public void run(UUID userId, MoneyChargeInput input) {
        Money charge = Money.charge(userId, input.value);
        moneyRepository.save(charge);
    }
}
