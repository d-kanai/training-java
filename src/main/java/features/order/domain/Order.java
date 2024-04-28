package features.order.domain;

import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlows;
import features.product.domain.Product;

import java.util.UUID;

public class Order {

    public final UUID id;
    public final UUID userId;
    public final UUID productId;


    private Order(UUID id, UUID userId, UUID productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public static OrderResult newOrder(UUID loginUserId, Product product, MoneyFlows moneyFlows) {
        if (moneyFlows.hasEnoughMoney(product)) throw new RuntimeException("チャージ残高が足りません");
        MoneyFlow usedMoneyFlow = MoneyFlow.use(loginUserId, product);
        Order purchasedOrder = new Order(
                UUID.randomUUID(),
                loginUserId,
                product.id
        );
        return new OrderResult(usedMoneyFlow, purchasedOrder);

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
