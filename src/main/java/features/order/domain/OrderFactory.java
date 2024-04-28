package features.order.domain;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlows;
import features.product.domain.Product;
import features.product.domain.PublishedProduct;
import features.user.domain.User;

import java.util.UUID;

public class OrderFactory {

    public static OrderFactory.OrderResult newOrder(User loginUser, PublishedProduct product, MoneyFlows moneyFlows) {
        if (moneyFlows.hasEnoughMoney(product)) throw new RuntimeException("チャージ残高が足りません");
        PublishedProduct.DiscountedPrice discountedPrice = product.discountedPrice(loginUser.userPlan);
        MoneyFlow usedMoneyFlow = MoneyFlow.order(loginUser.id, discountedPrice);
        Order purchasedOrder = new Order(
                UUID.randomUUID(),
                loginUser.id,
                product,
                discountedPrice
        );
        return new OrderFactory.OrderResult(usedMoneyFlow, purchasedOrder);

    }

    public static Order reconstruct(UUID uuid, User user, PublishedProduct product) {
        return new Order(uuid, user.id, product, product.discountedPrice(user.userPlan));

    }

    public static class OrderResult {

        public final MoneyFlow usedMoneyFlow;
        public final Order newOrder;

        public OrderResult(MoneyFlow usedMoneyFlow, Order newOrder) {
            this.usedMoneyFlow = usedMoneyFlow;
            this.newOrder = newOrder;
        }
    }

}
