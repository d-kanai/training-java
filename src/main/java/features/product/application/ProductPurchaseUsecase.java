package features.product.application;

import features.money.domain.Money;
import features.money.domain.MoneyRepository;
import features.money.domain.Monies;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductPurchaseInput;

import java.util.UUID;

public class ProductPurchaseUsecase {
    public void run(UUID loginUserId, ProductPurchaseInput input) {
        Product product = new ProductRepository().findById(input.productId);
        Monies monies = new MoneyRepository().findByUserId(loginUserId);
        if (monies.hasEnoughMoney(product)) throw new RuntimeException("チャージ残高が足りません");
        Money usedMoney = Money.use(loginUserId, product);
        new MoneyRepository().save(usedMoney);
    }
}
