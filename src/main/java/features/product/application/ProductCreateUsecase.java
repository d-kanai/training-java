package features.product.application;

import features.product.domain.DraftProduct;
import features.product.presentation.ProductCreateInput;
import features.product.domain.Product;
import features.product.domain.ProductRepository;

import java.util.UUID;

public class ProductCreateUsecase {
    public Product run(UUID loginUserId, ProductCreateInput input) {
        Product product = DraftProduct.create(loginUserId, input);
        new ProductRepository().save(product);
        return product;
    }
}
