package features.product.application;

import features.product.domain.DraftProduct;
import features.product.domain.Product;
import features.product.domain.ProductRepository;
import features.product.presentation.ProductPublishInput;

import java.util.UUID;

public class ProductPublishUsecase {

    private final ProductRepository productRepository;

    public ProductPublishUsecase() {
        productRepository = new ProductRepository();
    }

    public Product run(UUID loginUserId, ProductPublishInput input) {
        DraftProduct draftProduct = productRepository.findDraftByIdFromDb(input.productId);
        Product product = draftProduct.publish(loginUserId);
        productRepository.update(product);
        return product;
    }
}
