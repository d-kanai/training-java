package features.product.application;

import features.product.domain.DraftProduct;
import features.product.presentation.ProductCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;

import java.util.UUID;

public class ProductCreateUsecase {

    private ProductRepository productRepository;

    public ProductCreateUsecase() {
        productRepository = new ProductRepository();
    }

    public Product run(UUID loginUserId, ProductCreateInput input) {
        Product product = DraftProduct.newDraft(loginUserId, input);
        productRepository.save(product);
        return product;
    }
}
