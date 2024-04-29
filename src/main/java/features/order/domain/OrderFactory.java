package features.order.domain;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlows;
import features.product.domain.PublishedProduct;
import features.user.domain.User;

import java.util.UUID;

public class OrderFactory {

    public static Order newOrder(User loginUser, PublishedProduct product, MoneyFlows moneyFlows) {
        if (moneyFlows.hasEnoughMoney(product)) throw new RuntimeException("チャージ残高が足りません");
        PublishedProduct.DiscountedPrice discountedPrice = product.discountedPrice(loginUser.userPlan);
        return new Order(
                UUID.randomUUID(),
                loginUser.id,
                product,
                discountedPrice
        );

    }

    public static Order reconstruct(UUID uuid, User user, PublishedProduct product) {
        return new Order(uuid, user.id, product, product.discountedPrice(user.userPlan));

    }
}
