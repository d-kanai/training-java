package features.product.application;

import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductPublishInput;

public class ProductPublishUsecase {

    ProductRepository productRepository = new ProductRepository();

    public void run(ProductPublishInput input) {
        Product product = productRepository.findById(input.getId());
        product.publish();
        productRepository.save(product);
    }
}
