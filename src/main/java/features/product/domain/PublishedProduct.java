package features.product.domain;

import features.user.domain.UserPlan;

import java.util.UUID;

public class PublishedProduct extends Product {

    protected PublishedProduct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        super(id, userId, status, name, price);
    }

    public static PublishedProduct reconstruct(UUID id, UUID userId, ProductStatus status, String name, int price) {
        return new PublishedProduct(
                id,
                userId,
                status,
                name,
                price
        );
    }

    public DiscountedPrice discountedPrice(UserPlan userPlan) {
        return new DiscountedPrice((int) Math.floor(this.price() * userPlan.discountRate()));
    }


    @Override
    public PublishedProduct clone() throws CloneNotSupportedException {
        return (PublishedProduct) super.clone();
    }

    public class DiscountedPrice {
        public final int value;

        public DiscountedPrice(int value) {
            this.value = value;
        }
    }
}
