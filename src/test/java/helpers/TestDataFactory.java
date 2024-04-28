package helpers;

import features.product.domain.ProductStatus;
import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;

import java.util.Arrays;
import java.util.UUID;

public class TestDataFactory {

    public static Product createProduct(UUID userId) {
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

    public static User createUser() {
        User user = User.signup(new SignupInput("tanaka"));
        UserRepository.records.add(user);
        return user;
    }
}
