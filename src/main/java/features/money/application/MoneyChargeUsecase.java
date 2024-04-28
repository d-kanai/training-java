package features.money.application;

import features.money.domain.Money;
import features.money.domain.MoneyRepository;
import features.money.presentation.MoneyChargeInput;
import features.product.domain.Product;

import java.util.UUID;

public class MoneyChargeUsecase {
    public Product run(UUID userId, MoneyChargeInput input) {
        Money charge = Money.charge(userId, input.value);
        new MoneyRepository().save(charge);
        return null;
    }
}
