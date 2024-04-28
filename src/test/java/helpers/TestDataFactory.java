package helpers;

import features.money.domain.Money;
import features.money.domain.MoneyRepository;
import features.product.domain.ProductStatus;
import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;

import java.util.Arrays;
import java.util.UUID;

public class TestDataFactory {

    public static Product createDraftProduct(UUID userId) {
        Product product = Product.reconstruct(
                UUID.randomUUID(),
                userId,
                ProductStatus.DRAFT,
                "book",
                1000
        );
        ProductRepository.records = Arrays.asList(product);
        return product;
    }
    public static Product createPublishedProduct(UUID userId) {
        Product product = Product.reconstruct(
                UUID.randomUUID(),
                userId,
                ProductStatus.PUBLISHED,
                "book",
                1000
        );
        ProductRepository.records = Arrays.asList(product);
        return product;
    }

    public static User createUser() {
        User user = User.signup(new SignupInput("tanaka"));
        UserRepository.records.add(user);
        return user;
    }

    public static boolean createMoney(UUID userId) {
        Money money = Money.reconstruct(
                UUID.randomUUID(),
                userId,
                10000
        );
        return MoneyRepository.records.add(money);
    }
}
