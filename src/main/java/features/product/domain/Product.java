package features.product.domain;


import features.moneyFlow.domain.MoneyFlow;
import features.moneyFlow.domain.MoneyFlows;
import features.order.domain.Order;

import java.util.UUID;

public class Product implements Cloneable {
    public final UUID id;
    public final UUID userId;
    public final String name;
    public final int price;
    public ProductStatus status;


    protected Product(UUID id, UUID userId, ProductStatus status, String name, int price) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.price = price;
    }

    public static Product reconstruct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        return new Product(
                id,
                userId,
                status,
                name,
                price
        );
    }


    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }

    public PurchaseResult purchase(UUID loginUserId, MoneyFlows moneyFlows) {
        if (moneyFlows.hasEnoughMoney(this)) throw new RuntimeException("チャージ残高が足りません");
        MoneyFlow usedMoneyFlow = MoneyFlow.use(loginUserId, this);
        Order purchasedOrder = Order.purchase(loginUserId, this.id);
        return new PurchaseResult(usedMoneyFlow, purchasedOrder);
    }

    public class PurchaseResult {

        public final MoneyFlow usedMoneyFlow;
        public final Order purchasedOrder;

        public PurchaseResult(MoneyFlow usedMoneyFlow, Order purchasedOrder) {
            this.usedMoneyFlow = usedMoneyFlow;
            this.purchasedOrder = purchasedOrder;
        }
    }
}
