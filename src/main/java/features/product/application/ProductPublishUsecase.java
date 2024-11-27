package features.product.application;

import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductPublishInput;

// ・status transition, nooooooooooo setter
// ・manage "update"
// ・1 class 1 public method => thin test. what about Logic Class?
// ・save == upsert

public class ProductPublishUsecase {

    ProductRepository productRepository = new ProductRepository();

    public void run(ProductPublishInput input) {
        Product product = productRepository.findById(input.getId());
        if (product.status() == Product.Status.PUBLISHED) {
            throw new RuntimeException("すでに公開済みです");
        }
        product.setStatus(Product.Status.PUBLISHED);
        productRepository.save(product);
    }
}
