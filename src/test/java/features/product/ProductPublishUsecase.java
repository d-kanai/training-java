package features.product;

import features.user.domain.User;
import product.domain.Product;
import product.domain.ProductRepository;

import java.util.Optional;

public class ProductPublishUsecase {
    public Product run(User loginUser, ProductPublishInput input) {
        ProductRepository productRepository = new ProductRepository();
        Optional<Product> product = productRepository.findById(input.productId);
        product.get().publish();
//        productRepository.save(product.get());
        return product.get();
    }
}
