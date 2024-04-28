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
        Money usedMoney = product.purchase(loginUserId, monies);
        new MoneyRepository().save(usedMoney);
    }
}
