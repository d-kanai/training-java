package product.application;

import features.user.domain.User;
import product.domain.Product;
import product.domain.ProductRepository;
import product.presentation.ProductPublishInput;

import java.util.UUID;

public class ProductPublishUsecase {

    private final ProductRepository productRepository;

    public ProductPublishUsecase() {
        productRepository = new ProductRepository();
    }

    public Product run(UUID loginUserId, ProductPublishInput input) {
        Product product = productRepository.findById(loginUserId, input.productId);
        product.publish(loginUserId);
        productRepository.save(product);
        return product;
    }
}
