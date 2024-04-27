package helpers;

import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.SignupInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductCreateInput;

import java.util.Arrays;
import java.util.UUID;

public class TestDataFactory {

    public static Product createProduct(UUID userId) {
        //TODO: DBにして、オブジェクトの制約なく登録するか
        Product product = Product.draft(userId, new ProductCreateInput("book", 1000));
        ProductRepository.records = Arrays.asList(product);
        return product;
    }

    public static User createUser() {
        User user = User.signup(new SignupInput("tanaka"));
        UserRepository.records.add(user);
        return user;
    }
}
